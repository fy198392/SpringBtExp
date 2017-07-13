package com.shinefy.project.entity;
/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.entity     
 * @ClassName: TestTable     
 * @Description:  javabean，对应test_table表，测试表
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "test_table")
public class TestTable implements Serializable{
	
	private static final long serialVersionUID = 2120869894112984147L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //交给数据库处理
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="Score_Sum")
	private String sumScore;
	@Column(name="Score_Avg")
	private String avgScore;
	@Column(name="Age")
	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSumScore() {
		return sumScore;
	}

	public void setSumScore(String sumScore) {
		this.sumScore = sumScore;
	}

	public String getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(String avgScore) {
		this.avgScore = avgScore;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

