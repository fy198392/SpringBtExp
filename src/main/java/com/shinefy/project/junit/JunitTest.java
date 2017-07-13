package com.shinefy.project.junit;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.shinefy.project.ShinefySpringBootApplication;
import com.shinefy.project.entity.TestTable;
import com.shinefy.project.entity.user.Role;
import com.shinefy.project.service.TestService;
import com.shinefy.project.service.TestServiceMbatisService;
import com.shinefy.project.service.UserRoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShinefySpringBootApplication.class)
@WebAppConfiguration
public class JunitTest {
	private static final Logger logger =LoggerFactory.getLogger(JunitTest.class);
	@Autowired
	private TestServiceMbatisService testServiceMbatisService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private TestService testService;

	
//	@Test(timeout = 10000000) 
//	 public void getRoleList(){
//		
//		
//		Thread a=new Thread(){
//			 public void run()  
//			    {  
////					for(int i=0;i<100;i++){
//						List<TestTable> l=testService.getListByDataSource();
//						logger.debug(l.size()+"====================================================");
//					
//						List<TestTable> l2=testService.getTestTableList() ;
//						logger.debug(l2.size()+"++++++++++++++++++++++++++++++++++++++++++++++++++");
//						
//						
//						List<TestTable> l4=testService.getListByDataSource2();
//						logger.debug(l4.size()+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//						
//						List<TestTable> l5=testService.getListByDataSource10();
//						logger.debug(l5.size()+"#######################################");
//						
//						List<TestTable> l11=testService.getListByDataSource11();
//						logger.debug(l11.size()+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//						
//						List<TestTable> l12=testService.getListByDataSource12();
//						logger.debug(l12.size()+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//						
//						List<TestTable> l15=testService.getTestTableList() ;
//						logger.debug(l15.size()+"(((((((((((((((((((((((((((((((((((((((((((((((((((((((((");
//						
//						
//						List<TestTable> l13=testService.getListByDataSource13();
//						logger.debug(l13.size()+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//						List<TestTable> l14=testService.getListByDataSource14();
//						logger.debug(l14.size()+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//						
//						
//						
//					} 
////			    }  
//		};
//		
//		Thread b=new Thread(){
//			 public void run()  
//			    {  
//					for(int i=0;i<100;i++){
//						List<TestTable> l=testService.getListByDataSource();
//						logger.debug(l.size()+"====================================================");
//					
//						List<TestTable> l2=testService.getTestTableList() ;
//						logger.debug(l2.size()+"++++++++++++++++++++++++++++++++++++++++++++++++++");
//						
//						
//						List<TestTable> l4=testService.getListByDataSource2();
//						logger.debug(l4.size()+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//						
//						List<TestTable> l5=testService.getListByDataSource10();
//						logger.debug(l5.size()+"#######################################");
//						
//						List<TestTable> l11=testService.getListByDataSource11();
//						logger.debug(l11.size()+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//						
//						List<TestTable> l12=testService.getListByDataSource12();
//						logger.debug(l12.size()+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//						
//						List<TestTable> l15=testService.getTestTableList() ;
//						logger.debug(l15.size()+"(((((((((((((((((((((((((((((((((((((((((((((((((((((((((");
//						
//						
//						List<TestTable> l13=testService.getListByDataSource13();
//						logger.debug(l13.size()+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//						List<TestTable> l14=testService.getListByDataSource14();
//						logger.debug(l14.size()+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//						
//						
//						
//					} 
//			    }  
//		};
//		
//		Thread c=new Thread(){
//			 public void run()  
//			    {  
//					for(int i=0;i<100;i++){
//						List<TestTable> l=testService.getListByDataSource();
//						logger.debug(l.size()+"====================================================");
//					
//						List<TestTable> l2=testService.getTestTableList() ;
//						logger.debug(l2.size()+"++++++++++++++++++++++++++++++++++++++++++++++++++");
//						
//						
//						List<TestTable> l4=testService.getListByDataSource2();
//						logger.debug(l4.size()+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//						
//						List<TestTable> l5=testService.getListByDataSource10();
//						logger.debug(l5.size()+"#######################################");
//						
//						List<TestTable> l11=testService.getListByDataSource11();
//						logger.debug(l11.size()+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//						
//						List<TestTable> l12=testService.getListByDataSource12();
//						logger.debug(l12.size()+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//						
//						List<TestTable> l15=testService.getTestTableList() ;
//						logger.debug(l15.size()+"(((((((((((((((((((((((((((((((((((((((((((((((((((((((((");
//						
//						
//						List<TestTable> l13=testService.getListByDataSource13();
//						logger.debug(l13.size()+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//						List<TestTable> l14=testService.getListByDataSource14();
//						logger.debug(l14.size()+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//						
//						
//						
//					} 
//			    }  
//		};
//		a.start();
////		b.start();
////		c.start();
//	 }
	
	
	@Test 
    public void MultiRequestsTest() { 
                // 构造一个Runner 
//        Runnable runner = new Runnable() { 
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				for(int i=0;i<100;i++){
//					System.out.println(i);
//				}
//			} 
//        }; 
//        int runnerCount = 100; 
//                //Rnner数组，想当于并发多少个。 
//        Runnable[] trs = new Runnable[runnerCount]; 
//        for (int i = 0; i < runnerCount; i++) { 
//            trs[i] = runner; 
//            trs[i].run();
//        } 
//                // 用于执行多线程测试用例的Runner，将前面定义的单个Runner组成的数组传入 
		
		for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                	TestMain();
                	TestMain2();
                }
            }).start();

		}
    } 
	public  void TestMain2(){
		for(int i=0;i<100;i++){
//			List<TestTable> l=testService.getListByDataSource();
//			logger.debug(l.size()+"====================================================");
		
			List<TestTable> l2=testService.getTestTableList() ;
			logger.debug(l2.size()+"++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
	}
	
	
	public  void TestMain(){
		for(int i=0;i<100;i++){
			List<TestTable> l=testService.getListByDataSource();
			logger.debug(l.size()+"====================================================");
		
			List<TestTable> l2=testService.getTestTableList() ;
			logger.debug(l2.size()+"++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			
//			List<TestTable> l4=testService.getListByDataSource2();
//			logger.debug(l4.size()+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//			
//			List<TestTable> l5=testService.getListByDataSource10();
//			logger.debug(l5.size()+"#######################################");
//			
//			List<TestTable> l11=testService.getListByDataSource11();
//			logger.debug(l11.size()+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//			
//			List<TestTable> l12=testService.getListByDataSource12();
//			logger.debug(l12.size()+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//			
//			List<TestTable> l15=testService.getTestTableList() ;
//			logger.debug(l15.size()+"(((((((((((((((((((((((((((((((((((((((((((((((((((((((((");
//			
//			
//			List<TestTable> l13=testService.getListByDataSource13();
//			logger.debug(l13.size()+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//			List<TestTable> l14=testService.getListByDataSource14();
//			logger.debug(l14.size()+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//			
			
			
		} 
	}
	
}
