package com.shinefy.project.loader;
/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.loader    
 * @ClassName: ProjectLoader     
 * @Description:  项目启动的初始化配置
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;


@Component
@Order(value=2)   //启动时执行顺序排序
public class ProjectLoader implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(ProjectLoader.class);
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		logger.info(">>>>>>>>>>>>>>>服务启动执行 <<<<<<<<<<<<<");
	}

}
