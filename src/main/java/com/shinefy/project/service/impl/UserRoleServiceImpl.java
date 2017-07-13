package com.shinefy.project.service.impl;

/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.service.impl    
 * @ClassName: UserRoleServiceImpl     
 * @Description:  用户权限测试类
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shinefy.project.dao.user.ModelInfoDao;
import com.shinefy.project.dao.user.PermissionInfoDao;
import com.shinefy.project.dao.user.RoleInfoDao;
import com.shinefy.project.dao.user.UserInfoDao;
import com.shinefy.project.entity.user.Model;
import com.shinefy.project.entity.user.Permission;
import com.shinefy.project.entity.user.Role;
import com.shinefy.project.entity.user.UserInfo;
import com.shinefy.project.service.UserRoleService;

@Service("userRoleService")

public class UserRoleServiceImpl implements UserRoleService {
	private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);
	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private RoleInfoDao roleInfoDao;

	@Autowired
	private PermissionInfoDao permissionInfoDao;

	@Autowired
	private ModelInfoDao modelInfoDao;

	// /**
	// * 缓存的key
	// */
	// public static final String cache_key = "\"cache_key\"";
	// /**
	// * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
	// */
	// public static final String CACHE_NAME = "sqlCache";

	@Override
	public UserInfo searchUserInfoByLogin(String username, String pwd) {
		// TODO Auto-generated method stub
		try {

			UserInfo userinfo = userInfoDao.getUserInfoLogin(username, pwd);

			return userinfo;
		} catch (Exception ex) {
			// 出错的处理
			ex.printStackTrace();

		}
		return null;
	}

	// @Cacheable(value = CACHE_NAME, key = cache_key)
	@Override
	public List<UserInfo> getUserInfoList() {
		// TODO Auto-generated method stub
		try {
			List<UserInfo> userinfo_list = userInfoDao.getUserInfoList();

			return userinfo_list;
		} catch (Exception ex) {
			// 出错的处理
			ex.printStackTrace();
		}
		return new ArrayList<UserInfo>();
	}

	// @Cacheable(value = "sqlCache", key = "#username") // 缓存，记得在编辑或删除时进行缓存清楚
	@Override
	public UserInfo searchUserInfoByName(String username) {
		// TODO Auto-generated method stub
		try {

			UserInfo userinfo = userInfoDao.getUserInfoByName(username);

			return userinfo;
		} catch (Exception ex) {
			// 出错的处理
			ex.printStackTrace();

		}
		return null;
	}

	public List<Role> getRoleList(int pageNo, int pageSize) {
		try {
			pageNo = 0;
			pageSize = 10;

			PageRequest pageRequest = new PageRequest(pageNo, pageSize);

			Page<Role> page = roleInfoDao.findAll(pageRequest);

			List<Role> role_list = page.getContent();

			logger.debug(role_list.size() + "---------0000000000000000000000000000000");

			return role_list;
		} catch (Exception ex) {
			// 出错的处理
			ex.printStackTrace();
			logger.error("UserRoleServiceImpl中的getRoleList(),获得权限列表出错");

		}
		return new ArrayList<Role>();
	}

	@Override
	public List<Model> getModelList() {
		Order order = new Order(Sort.Direction.ASC, "sortValue");
		Sort sort = new Sort(order);
		List<Model> modelList = modelInfoDao.getByParentIdIsNull(sort);
		// modelList = modelInfoDao.getByParentIdIsNullAndSort(sort);
		return modelList;
	}

	@Override
	public Role saveRole(Role role) {
		return roleInfoDao.save(role);
	}

	@Override
	public Permission savePermission(Permission permission) {
		return permissionInfoDao.save(permission);
	}

	@Override
	public List<Permission> savePermissionList(List<Permission> permissions) {
		return (List<Permission>) permissionInfoDao.save(permissions);
	}

	@Transactional
	@Override
	public int updateRole(Role role) {
		return roleInfoDao.updateRole(role.getState(), role.getRoleName(), role.getId());
	}

	@Transactional
	@Override
	public int updateModel(Model model) {
		Integer view = model.getView();
		Integer edit = model.getEdit();
		Integer add = model.getAdd();
		Integer del = model.getDel();
		Integer print = model.getPrint();
		Integer id = model.getId();
		return roleInfoDao.updateModel(view, edit, add, del, print, id);
	}

	/**
	 * 根据id来获得权限
	 */
	@Override
	public Role getRoleById(int id) {
		return roleInfoDao.getRoleById(id);
	}

	@Override
	public void deletePermission(Permission permission) {
		permissionInfoDao.delete(permission);
	}

	@Override
	public void deleteRole(int id) {
		roleInfoDao.delete(id);
	}

}
