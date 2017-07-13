package com.shinefy.project.service;
/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.service    
 * @ClassName: TestService     
 * @Description:  测试接口
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */
import java.util.List;

import com.shinefy.project.entity.TestTable;

public interface TestService {
	public List<TestTable> getTestTableList();
	
	public List<TestTable> getListByDataSource();
	
	public boolean transactionalTest(TestTable table);
	
	public List<TestTable> getListByDataSource2() ;
	
	
	public List<TestTable> getListByDataSource10() ;
	
	public List<TestTable> getListByDataSource11() ;
	
	public List<TestTable> getListByDataSource12() ;
	
	public List<TestTable> getListByDataSource13() ;
	
	public List<TestTable> getListByDataSource14() ;
}
