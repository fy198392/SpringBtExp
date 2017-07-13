package com.shinefy.project.dao;
/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.dao     
 * @ClassName: TestDao     
 * @Description:  接口，jdbc操作数据库类示例
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

public interface TestDao {
	/**
	 * 
	 * @return
	 */
	public List<TestTable> getTestTableList();
	
	/**
	 * 
	 * @return
	 */
	public List<TestTable> getListByDataSource();
	
	/**
	 * 
	 * @param table
	 * @return
	 */
	public boolean insertTestTable(TestTable table);
	

	/**
	 * 
	 * @param table
	 * @return
	 */
	public boolean deleteTestTable(TestTable table) ;
}
