package com.shinefy.project.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;

/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.security    
 * @ClassName: ShiroManageRealm     
 * @Description:  验证框架的自定义验证方法，
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.shinefy.project.entity.user.Role;
import com.shinefy.project.entity.user.UserInfo;
import com.shinefy.project.service.UserRoleService;

@Component("shiroManageRealm")
public class ShiroManageRealm extends AuthorizingRealm{
	private static final Logger logger = LoggerFactory.getLogger(ShiroManageRealm.class);
	
	@Autowired
    @Qualifier("userRoleService")
	private UserRoleService userRoleService;
	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		
		UserInfo user = (UserInfo)arg0.getPrimaryPrincipal();
		
		//判断用户是否为存在
		UserInfo userinfo=userRoleService.searchUserInfoByName(user.getUserName());
		
		if(userinfo!=null){
			
			SimpleAuthorizationInfo authorInfo=new SimpleAuthorizationInfo();
//			Set<String> set2=new HashSet<String>();
//			set2.add("user");
//			//用户的角色集合
//			authorInfo.setRoles(set2);
//			Set<String> set=new HashSet<String>();
//			
//
//			authorInfo.addStringPermission("edit");
////			authorInfo.addStringPermission("user:edit");
//			authorInfo.addStringPermission("add");
		    //根据实际项目情况添加权限
			List<Role> roleList=userinfo.getRoleList();
			for (Role role : roleList) {
				authorInfo.addStringPermissions(role.getPermissionsName());
			}

			return authorInfo;
		}
		
		
		
		return null;
	}
	
	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		UsernamePasswordToken token=(UsernamePasswordToken) arg0;
		String username=token.getUsername();
		String pwd=String.valueOf(token.getPassword());
	
		UserInfo user=userRoleService.searchUserInfoByLogin(username,pwd);
		/**
		 * 此处 应该查询数据库验证
		 */
		if(user!=null){
			logger.info(username+"登录成功");
			SecurityUtils.getSubject().getSession().setTimeout(3600000);
			// 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
			return new SimpleAuthenticationInfo(user, user.getPwd(), getName());
		}
		logger.info(username+"登录失败");
		return null;
	}

	
	// 清除缓存
		public void clearCached() {
			PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
			super.clearCache(principals);
		}

}
