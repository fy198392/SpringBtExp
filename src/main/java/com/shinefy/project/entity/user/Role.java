package com.shinefy.project.entity.user;

/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.entity.user     
 * @ClassName: role     
 * @Description:  javabean，对应role表，角色表，该表可做扩展，对应用户，一个用户下可有多个权限
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "role")
public class Role implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 交给数据库处理
	private Integer id;

	@Column(name = "state")
	private String state;

	@Column(name = "rolename")
	private String roleName;

	@Column(name = "adduser")
	private Integer addUser;

	@Column(name = "remark")
	private String remark;

	@Column(name = "addtime")
	private Date addTime;

	@Column(name = "edituser")
	private Integer editUser;

	@Column(name = "edittime")
	private Date editTime;

	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
	private List<Permission> permissionList;// 一对多
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "Role_Id") }, inverseJoinColumns = {
			@JoinColumn(name = "User_Id") })
	private List<UserInfo> userInfoList;// 一个对应多个用户

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public Integer getEditUser() {
		return editUser;
	}

	public void setEditUser(Integer editUser) {
		this.editUser = editUser;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

	/**
	 * 该类用于获得角色对应的url和对url的操作，具体的根据项目来更改此方法
	 * 
	 * @return
	 */
	@Transient // 表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性.

	public Set<String> getPermissionsName() {
		Set<String> set = new HashSet<String>();
		List<Permission> perlist = getPermissionList();
		for (Permission per : perlist) {
			set.add(per.getPermission_Action());
		}
		return set;
	}
}
