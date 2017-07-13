package com.shinefy.project.config;
/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.config    
 * @ClassName: MyBatisConfiguration     
 * @Description:  mybatis框架的配置类
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.pagehelper.PageHelper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
public class MyBatisConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(MyBatisConfiguration.class);

	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
		scannerConfigurer.setBasePackage("com.shinefy.project.mapper");
		Properties props = new Properties();
		props.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
		props.setProperty("IDENTITY", "MYSQL");
		props.setProperty("notEmpty", "true");
		scannerConfigurer.setProperties(props);
		return scannerConfigurer;
	}
	/**
	 * 分页插件
	 * @param dataSource
	 * @return
	 */
	@Bean
	public PageHelper pageHelper(DataSource dataSource) {
		logger.info("注册MyBatis分页插件PageHelper");
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "true");
		pageHelper.setProperties(p);
		return pageHelper;
	}
}
