package com.shinefy.project.config;
/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.config    
 * @ClassName: WebAppConfigurer     
 * @Description:  springboot的web配置
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.shinefy.project.interceptor.InterceptorTest;
@Configuration
@EnableWebMvc
public class WebAppConfigurer extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {

//	private static final Logger logger = LoggerFactory.getLogger(WebAppConfigurer.class);


	/**
	 * 用于添加拦截器设置
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new InterceptorTest()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
	/**
	 * 访问资源
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

		super.addResourceHandlers(registry);
	}
	

}
