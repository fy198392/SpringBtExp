package com.shinefy.project.config;
/**
 *
 * @ProjectName: project
 * @Package: com.shinefy.project.config
 * @ClassName: CacheConfiguration
 * @Description:  hibernate的缓存配置
 * @Author:  lee
 * @CreateDate:  2016/11/21
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version:  v0.0.1
 *
 */
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


@Configuration
@EnableCaching
public class CacheConfiguration {
	/**
	 * ehcache 主要的管理器
	 */
	@Bean(name = "appEhCacheCacheManager")
	public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
		return new EhCacheCacheManager (bean.getObject ());
	}

	/**
	 * 据shared与否的设置,Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地.
	 */
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
		cacheManagerFactoryBean.setConfigLocation (new ClassPathResource ("ehcache.xml"));
		cacheManagerFactoryBean.setShared (true);

		return cacheManagerFactoryBean;
	}
}