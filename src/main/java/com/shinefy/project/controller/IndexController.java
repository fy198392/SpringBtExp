package com.shinefy.project.controller;
/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.controller    
 * @ClassName: IndexController     
 * @Description: 测试controller
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shinefy.project.entity.TestTable;

import com.shinefy.project.service.TestService;
import com.shinefy.project.service.TestServiceMbatisService;
import com.shinefy.project.service.UserRoleService;

@RestController
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    @Qualifier("testService")
    private TestService testService;
    @Autowired
    @Qualifier("userRoleService")
    private UserRoleService userRoleService;
    
    @Autowired
    @Qualifier("testServiceMbatisService")
    private TestServiceMbatisService testServiceMbatisService;
	
    @RequestMapping("/")
	public ModelAndView Index(){
    	ModelAndView mv = new ModelAndView("/page/index");
    	return mv;
    }
    
    
	@RequestMapping("/index")
	public ModelAndView IndexMethod(){
		 ModelAndView mv = new ModelAndView("/page/index");
		List<TestTable> testTable_list=testService.getTestTableList();
		List<TestTable> testTable_list2=testService.getTestTableList();
		System.out.println("--------------"+testTable_list.size()+"--------"+testTable_list2.size());
		TestTable t=new TestTable();
		t.setId(1);
		testService.transactionalTest(t);
		return mv;
	}
	
	
	@RequestMapping("/mybatis")
	public String mybatis(){
//		TestTable t=new TestTable();
//		testService.transactionalTest(t);
//		testServiceMbatisService.addTestTableByMabtis();
		List<TestTable> l1=testServiceMbatisService.likeName("李晖");
		logger.debug("================================================"+l1.size());
		List<TestTable> l2=testServiceMbatisService.likeName("fuck");
		logger.debug("================================================"+l2.size());
		List<TestTable> l3=testServiceMbatisService.likeName("李晖");
		logger.debug("================================================"+l3.size());
		testServiceMbatisService.test("");
		logger.debug("================================================"+l3.size());
		return "serviceerror";
	}
	
	
}
