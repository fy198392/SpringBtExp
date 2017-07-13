package com.shinefy.project.dao.impl;
/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.dao.impl     
 * @ClassName: TestDaoImpl     
 * @Description:  TestDao接口的实现类，该类为JDBC操作数据库示例。
 *                @TargetDataSource(name="数据源名称")用于指定数据源，不使用该注解即为使用默认数据源，事务控制由service层完成
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

//import com.shinefy.project.config.datasource.TargetDataSource;
import com.shinefy.project.dao.BaseJDBCDao;
import com.shinefy.project.dao.TestDao;
import com.shinefy.project.datasource.DynamicDataSourceContextHolder;
import com.shinefy.project.datasource.TargetDataSource;
import com.shinefy.project.entity.TestTable;

@Component("testDao")
public class TestDaoImpl  extends BaseJDBCDao implements TestDao{
	private static final Logger logger = LoggerFactory.getLogger(TestDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 使用默认的数据源进行jdbc测试
	 */
	@Override
	public List<TestTable> getTestTableList() {
		logger.debug("查询开始");
		// TODO Auto-generated method stub
		String sql = "SELECT *   FROM test_table";
		return (List<TestTable>) jdbcTemplate.query(sql, new RowMapper<TestTable>(){

			@Override
			public TestTable mapRow(ResultSet rs, int rowNum) throws SQLException {
				TestTable stu = new TestTable();
				stu.setId(rs.getInt("Id"));
				stu.setAge(rs.getInt("Age"));
				stu.setName(rs.getString("Name"));
				stu.setSumScore(rs.getString("Score_Sum"));
				stu.setAvgScore(rs.getString("Score_Avg"));
				return stu;
			}
			
		});
	
	}
	
	/**
	 * 
	 */
	public boolean deleteTestTable(TestTable table){
		
		int flag=jdbcTemplate.update("delete  from test_table where Id="+table.getId());
		logger.info("-------------------------------"+flag);
		return true;
	}
	
	public boolean insertTestTable(TestTable table)  {
		
		int flag=jdbcTemplate.update("insert into test_table(Name)values('李晖')");
		logger.info("-------------------------------"+flag);
		return true;
	}
	
	
	
	/**
	 * 使用指定的数据源
	 */
	@Override
//	@TargetDataSource(name="ds2")
	public List<TestTable> getListByDataSource() {
		
		// TODO Auto-generated method stub
		String sql = "SELECT *   FROM test_table2";
		return (List<TestTable>) jdbcTemplate.query(sql, new RowMapper<TestTable>(){
			
			@Override
			public TestTable mapRow(ResultSet rs, int rowNum) throws SQLException {
				TestTable stu = new TestTable();
				stu.setId(rs.getInt("ID"));
				stu.setAge(rs.getInt("AGE"));
				stu.setName(rs.getString("NAME"));
				stu.setSumScore(rs.getString("SCORE_SUM"));
				stu.setAvgScore(rs.getString("SCORE_AVG"));
				return stu;
			}
			
		});
	}



}
