package com.shinefy.project.service.impl;
/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.service.impl    
 * @ClassName: TestServiceImpl     
 * @Description:  测试类
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shinefy.project.dao.TestDao;
import com.shinefy.project.datasource.DynamicDataSourceContextHolder;
import com.shinefy.project.entity.TestTable;
import com.shinefy.project.service.TestService;
@Service("testService")
public class TestServiceImpl implements  TestService{
	private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
    @Autowired
    @Qualifier("testDao")
    private TestDao testDao;
	
    
    
	@Override
	public List<TestTable> getTestTableList() {
		// TODO Auto-generated method stub
	
		List<TestTable> testTable_list=testDao.getTestTableList();
		
		return testTable_list;
	}
	
	/**
	 * 事务
	 * @param table
	 * @return
	 */
	@Transactional(value = "")
	public boolean transactionalTest(TestTable table){
		
		
		testDao.insertTestTable(table);
//		testDao.deleteTestTable(table);
		logger.debug("==============================================");
//		if(table.getId()==1){
//		throw new RuntimeException("测试事务异常回滚");
//		}
		
		return true;
		
	}
	
	@Override
	public List<TestTable> getListByDataSource() {
		// TODO Auto-generated method stub
		DynamicDataSourceContextHolder.setDataSourceType("ds2");
		List<TestTable> testTable_list=testDao.getListByDataSource();
		DynamicDataSourceContextHolder.clearDataSourceType();
		return testTable_list;
	
	}
	
	@Override
	public List<TestTable> getListByDataSource2() {
		// TODO Auto-generated method stub
		DynamicDataSourceContextHolder.setDataSourceType("ds5");
		List<TestTable> testTable_list=testDao.getListByDataSource();
		DynamicDataSourceContextHolder.clearDataSourceType();
		return testTable_list;
	
	}
	
	
	@Override
	public List<TestTable> getListByDataSource10() {
		// TODO Auto-generated method stub
		DynamicDataSourceContextHolder.setDataSourceType("ds10");
		List<TestTable> testTable_list=testDao.getListByDataSource();
		DynamicDataSourceContextHolder.clearDataSourceType();
		return testTable_list;
	
	}
	
	@Override
	public List<TestTable> getListByDataSource11() {
		// TODO Auto-generated method stub
		DynamicDataSourceContextHolder.setDataSourceType("ds11");
		List<TestTable> testTable_list=testDao.getListByDataSource();
		DynamicDataSourceContextHolder.clearDataSourceType();
		return testTable_list;
	
	}
	
	@Override
	public List<TestTable> getListByDataSource12() {
		// TODO Auto-generated method stub
		DynamicDataSourceContextHolder.setDataSourceType("ds12");
		List<TestTable> testTable_list=testDao.getListByDataSource();
		DynamicDataSourceContextHolder.clearDataSourceType();
		return testTable_list;
	
	}
	
	
	@Override
	public List<TestTable> getListByDataSource13() {
		// TODO Auto-generated method stub
		DynamicDataSourceContextHolder.setDataSourceType("ds13");
		List<TestTable> testTable_list=testDao.getListByDataSource();
		DynamicDataSourceContextHolder.clearDataSourceType();
		return testTable_list;
	
	}
	
	@Override
	public List<TestTable> getListByDataSource14() {
		// TODO Auto-generated method stub
		DynamicDataSourceContextHolder.setDataSourceType("ds14");
		List<TestTable> testTable_list=testDao.getListByDataSource();
		DynamicDataSourceContextHolder.clearDataSourceType();
		return testTable_list;
	
	}

}
