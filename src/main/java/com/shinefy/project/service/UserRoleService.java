package com.shinefy.project.service;
/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.service    
 * @ClassName: UserRoleService     
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

import org.springframework.transaction.annotation.Transactional;

import com.shinefy.project.entity.user.Model;
import com.shinefy.project.entity.user.Permission;
import com.shinefy.project.entity.user.Role;
import com.shinefy.project.entity.user.UserInfo;


public interface UserRoleService {
	/**
	 * 
	 * @param username
	 * @return
	 */
	public UserInfo searchUserInfoByLogin(String username,String  pwd);
	
	
	
	public UserInfo searchUserInfoByName(String username);
	/**
	 * 
	 * @return
	 */
	public List<UserInfo> getUserInfoList();
	
	/**
	 * 获取权限
	 * @return
	 */
	public List<Role> getRoleList(int pageNo,int pageSize);
	
	/**
	 * 查询模型
	 * @return
	 */
	public List<Model> getModelList();
	
	public Role saveRole(Role role);
	
	public Permission savePermission(Permission permission);
	
	public int updateRole(Role role);
	
	public int updateModel(Model model);

	List<Permission> savePermissionList(List<Permission> permissions);
	
	Role getRoleById(int id);

	void deletePermission(Permission permission);
	
	void deleteRole(int id);
	
	
}
