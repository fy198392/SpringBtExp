package com.shinefy.project.config;
/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.config    
 * @ClassName: ShiroConfiguration     
 * @Description: 安全框架配置，loadShiroFilterChain、shiroFilter用于配置验证信息
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.shinefy.project.security.ShiroManageRealm;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.context.embedded.FilterRegistrationBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
public class ShiroConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);
	
	@Bean
    public EhCacheManager getEhCacheManager() {  
		
        EhCacheManager em = new EhCacheManager();  
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");  
        
        return em;  
    }
	
	@Bean(name = "shiroManageRealm")
	public ShiroManageRealm shiroManageRealm(EhCacheManager cacheManager) {  
		ShiroManageRealm realm = new ShiroManageRealm(); 

		realm.setCacheManager(cacheManager);
		return realm;
	}  
	

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		//  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理  
		filterRegistration.addInitParameter("targetFilterLifecycle", "true");
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");
		return filterRegistration;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(ShiroManageRealm shiroManageRealm) {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(shiroManageRealm);
		
//		<!-- 用户授权/认证信息Cache, 采用EhCache 缓存 --> 
		dwsm.setCacheManager(getEhCacheManager());
		dwsm.setRememberMeManager(rememberMeManager());
		return dwsm;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return aasa;
	}
	
	/**
	 * 参照
	 * http://www.cppblog.com/guojingjia2006/archive/2014/05/14/206956.html
	 * @param shiroFilterFactoryBean
	 */
	private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean){
		/////////////////////// 下面这些规则配置最好配置到配置文件中 ///////////////////////
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter

		// anon：它对应的过滤器里面是空的,什么都没做
		logger.info("##################从数据库读取权限规则，加载到shiroFilter中##################");
		
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/static", "anon");
		filterChainDefinitionMap.put("/index", "authc");
		filterChainDefinitionMap.put("/", "authc");
		filterChainDefinitionMap.put("/manage/**", "authc,perms");// 系统设置模块
		filterChainDefinitionMap.put("/info/**", "authc,perms");// 系统设置模块
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
	}


	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager  
		shiroFilterFactoryBean.setSecurityManager(securityManager);
	
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.html"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的连接
		shiroFilterFactoryBean.setSuccessUrl("/index");//登录成功
		shiroFilterFactoryBean.setUnauthorizedUrl("/error403");//无权限
		
		loadShiroFilterChain(shiroFilterFactoryBean);
		return shiroFilterFactoryBean;
	}
	
	
	@Bean  
	public SimpleCookie rememberMeCookie(){  
	  
	       System.out.println("ShiroConfiguration.rememberMeCookie()");  
	       //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe  
	       SimpleCookie simpleCookie = new SimpleCookie("rememberMe");  
	       //<!-- 记住我cookie生效时间30天 ,单位秒;-->  
	       simpleCookie.setMaxAge(259200);  
	       return simpleCookie;  
	}  
	/**  
	  * cookie管理对象;  
	  * @return  
	  */  
	@Bean  
	public CookieRememberMeManager rememberMeManager(){  
	  
	       System.out.println("ShiroConfiguration.rememberMeManager()");  
	       CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();  
	       cookieRememberMeManager.setCookie(rememberMeCookie());  
	       return cookieRememberMeManager;  
	}  
	
}
