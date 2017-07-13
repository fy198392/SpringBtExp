package com.shinefy.project.entity.user;
/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.entity.user     
 * @ClassName: userinfo     
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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "userinfo")
public class UserInfo  implements Serializable {
	
//	@GeneratedValue(generator = "paymentableGenerator")    
//	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//交给数据库处理
	private Integer id;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="pwd")
	private String pwd;	
	
	@Column(name="realname")
	private String realName;
	
	@Column(name="userphone")
	private String userPhone;
	
	@Column(name="Usertel")
	private String userTel;
	
	@Column(name="Userdep")
	private String userDep;
	
	@Column(name="state")
	private String state;
	
	@Column(name="adduser")
	private Integer addUser;
	
	@Column(name="addtime")
	private Date addTime;
	
	@Column(name="edituser")
	private Integer  editUser;
	
	@Column(name="edittime")
	private Date editTime;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "User_Id") }, inverseJoinColumns = {
			@JoinColumn(name = "Role_Id") })
	private List<Role> roleList;// 一个用户具有多个角色
	
	public UserInfo() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserDep() {
		return userDep;
	}

	public void setUserDep(String userDep) {
		this.userDep = userDep;
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
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Transient
	public Set<String> getRolesName() {
		List<Role> roles = getRoleList();
		Set<String> set = new HashSet<String>();
		for (Role role : roles) {
			set.add(role.getRoleName());
		}
		return set;
	}
	
}
