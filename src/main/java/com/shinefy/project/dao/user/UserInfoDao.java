package com.shinefy.project.dao.user;
/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.dao.user     
 * @ClassName: UserInfoDao     
 * @Description:  接口，JPA标准操作数据库
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import com.shinefy.project.entity.user.UserInfo;

public interface UserInfoDao extends PagingAndSortingRepository<UserInfo,Integer>, JpaSpecificationExecutor<UserInfo> {
	/**
	 * 根据名称获得用户信息
	 * @param name
	 * @return
	 */
	@Query(value="select u from UserInfo u where username=:name")
	UserInfo getUserInfoByName(@Param("name") String name) ;
	/**
	 * 根据用户名和密码获取状态
	 * @param name
	 * @param pwd
	 * @return
	 */
	@Query(value="select u from UserInfo u where username=:name and pwd=:pwd")
	UserInfo getUserInfoLogin(@Param("name") String name,@Param("pwd") String pwd) ;
	
	/**
	 * 获取所有用户
	 * @return
	 */
	@Query("select u from UserInfo u ")
	List<UserInfo> getUserInfoList();
	
	

}
