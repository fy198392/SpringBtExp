package com.shinefy.project.entity.user;
/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.entity.user     
 * @ClassName: permission_role     
 * @Description:  javabean，对应userinfo表，角色用户表，该表可做扩展
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */
import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToOne;
import javax.persistence.Table;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "permission_role")
public class Permission  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //交给数据库处理
	private Integer id;
	
	@Column(name="permission_Action")
	private String Permission_Action;
	
	@Column(name="adduser")
	private Integer addUser;
	
	@Column(name="addtime")
	private Date addTime;
	
	@ManyToOne
	@JoinColumn(name = "Role_Id")
	private Role role;

    @OneToOne
	@JoinColumn(name="model_id")
    @Fetch(FetchMode.JOIN) 
	private Model model;

	
	
	

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}






	public String getPermission_Action() {
		return Permission_Action;
	}

	public void setPermission_Action(String permission_Action) {
		Permission_Action = permission_Action;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	
	public Integer getAddUser() {
		return addUser;
	}

	public void setAddUser(Integer addUser) {
		this.addUser = addUser;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
