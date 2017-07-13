package com.shinefy.project;


import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
/**
 *
 *
 * @ProjectName: project
 * @Package: com.shinefy.project
 * @ClassName: ShinefySpringBootApplication
 * @Description:  springboot框架的启动类   ，配置一些启动加载信息
 * @Author:  lee
 * @CreateDate:  2016/11/21
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version:  v0.0.1
 *
 */
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.shinefy.project.datasource.DynamicDataSourceRegister;


@SpringBootApplication(exclude = VelocityAutoConfiguration.class)
@EnableTransactionManagement // 支持事务
@Import(DynamicDataSourceRegister.class) // 注册动态多数据源
public class ShinefySpringBootApplication extends SpringBootServletInitializer
		implements EmbeddedServletContainerCustomizer,TransactionManagementConfigurer {

//	private static final Logger logger = LoggerFactory.getLogger(ShinefySpringBootApplication.class);
	/**
	 * 作用与在web.xml中配置负责初始化Spring应用上下文的监听器作用类似
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ShinefySpringBootApplication.class);
	}


	/**
	 * 启动入口
	 * @param args
	 */
	public static void main(String[] args) {
//		SpringApplication.run(ShinefySpringBootApplication.class, args);
		SpringApplication application = new SpringApplication(ShinefySpringBootApplication.class);
//	    application.setBannerMode(Banner.Mode.OFF);

        application.run(args);
	}
	/**
	 * 对容器路径的配置
	 */
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {

		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND , "/error404"));
        container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error500"));
        container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED,"/error403"));
		container.setContextPath("/SpringbootPro");
	}

	 @Resource(name="transactionManager")
	 private PlatformTransactionManager transactionManager;

	/**
	 * 事务
	 * @param dataSource
	 * @return
	 */
	@Bean(name = "txManager1")
	public PlatformTransactionManager txManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}


    // 创建事务管理器2
    @Bean(name = "transactionManager")
    public PlatformTransactionManager txManager2(EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }


	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		// TODO Auto-generated method stub
		return transactionManager;

	}

}
