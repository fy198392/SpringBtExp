package com.shinefy.project.service.impl;
import java.util.List;

/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.service.impl    
 * @ClassName: TestServiceMbatisServiceImpl     
 * @Description:  mybatis的测试类 
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.shinefy.project.datasource.TargetDataSource;
import com.shinefy.project.entity.TestTable;
import com.shinefy.project.mapper.TestTableMapper;
import com.shinefy.project.service.TestServiceMbatisService;
@Service("testServiceMbatisService")
public class TestServiceMbatisServiceImpl implements TestServiceMbatisService{

	
	@Autowired
	private TestTableMapper testTableMapper;
	
	@TargetDataSource(name="ds2")
	@Override
	public boolean addTestTableByMabtis() {
		// TODO Auto-generated method stub
		TestTable ttable = new TestTable();
		ttable.setAge(20);
		ttable.setSumScore("378.5");
		ttable.setAvgScore("28.5");
		ttable.setName("王刚");
		testTableMapper.add(ttable);
		return false;
	}
	@Cacheable (value = "sqlCache")//缓存，记得在编辑或删除时进行缓存清楚
	@Override
	public List<TestTable> likeName(String name){
		return testTableMapper.likeName(name);
	}
	
	
	@CacheEvict (value = "sqlCache")//缓存，记得在编辑或删除时进行缓存清楚
	@Override
	public void test(String username) {
		// TODO Auto-generated method stub
		try{
			
			
		}catch(Exception ex){
			//出错的处理
			ex.printStackTrace();
			
		}
	
	} 
	
}
