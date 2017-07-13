package com.shinefy.project.mapper;

import java.util.List;

import com.shinefy.project.entity.TestTable;

import tk.mybatis.mapper.common.Mapper;

//@Repository("testTableMapper")

public interface TestTableMapper extends Mapper<TestTable>{

	public List<TestTable> likeName(String name);
	
	public TestTable getById(int id);
	
	public int add(TestTable stu);
	
	public String getNameById(int id);

}
