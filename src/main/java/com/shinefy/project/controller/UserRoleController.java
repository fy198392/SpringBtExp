package com.shinefy.project.controller;
/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.controller    
 * @ClassName: UserRoleController     
 * @Description: 测试controller
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shinefy.project.entity.user.Model;
import com.shinefy.project.entity.user.ModelList;
import com.shinefy.project.entity.user.Permission;
import com.shinefy.project.entity.user.Role;
import com.shinefy.project.entity.user.UserInfo;
import com.shinefy.project.service.UserRoleService;
import com.shinefy.project.util.Function;
import com.shinefy.project.util.MD5;

@Controller
public class UserRoleController {
	private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;

	/**
	 * 权限列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/manage/role")
	// @RequiresPermissions("")
	public ModelAndView userRole() {
		List<Role> role_list = userRoleService.getRoleList(1, 20);

		ModelAndView mv = new ModelAndView("/page/manage/role_list");
		mv.addObject("role_list", role_list);
		return mv;
	}

	/**
	 * 用户列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/manage/user")
	// @RequiresPermissions("")
	public ModelAndView userInfo() {

		ModelAndView mv = new ModelAndView("/page/manage/user_list");

		return mv;
	}

	/**
	 * 给角色分配模块页面
	 * 
	 * @return
	 */
	@RequestMapping("/manage/role_add")
	// @RequiresPermissions("")
	public ModelAndView roleAdd() {
		List<Model> modelList = userRoleService.getModelList();
		ModelAndView mv = new ModelAndView("/page/manage/role_add");
		mv.addObject("modelList", modelList);
		return mv;
	}

	/**
	 * 给角色分配模块实现
	 * 
	 * @param request
	 * @param role
	 * @param modelList
	 * @return
	 */
	@RequestMapping(value = "/manage/role_addContent", method = RequestMethod.POST)
	// @RequiresPermissions("")
	public String role_addContent(HttpServletRequest request, Role role, ModelList modelList) {
		// 保存角色
		Role saveRole = userRoleService.saveRole(role);
		// 模块列表
		List<Model> models = modelList.getModel();
		// 保存角色、模块关联表
		List<Permission> permissions = new ArrayList<>();
		for (Model model : models) {
			if (model != null) {
				List<Model> children = model.getChildren();
				if (children != null) {
					for (Model child : children) {
						if (child.getAdd() == 1) {
							Permission permission = new Permission();
							permission.setModel(child);
							permission.setRole(saveRole);
							permission.setPermission_Action(child.getKey() + "_Add");
							permissions.add(permission);
						}
						if (child.getDel() == 1) {
							Permission permission = new Permission();
							permission.setModel(child);
							permission.setRole(saveRole);
							permission.setPermission_Action(child.getKey() + "_Del");
							permissions.add(permission);
						}
						if (child.getEdit() == 1) {
							Permission permission = new Permission();
							permission.setModel(child);
							permission.setRole(saveRole);
							permission.setPermission_Action(child.getKey() + "_Edit");
							permissions.add(permission);
						}
						if (child.getPrint() == 1) {
							Permission permission = new Permission();
							permission.setModel(child);
							permission.setRole(saveRole);
							permission.setPermission_Action(child.getKey() + "_Print");
							permissions.add(permission);
						}
						if (child.getView() == 1) {
							Permission permission = new Permission();
							permission.setModel(child);
							permission.setRole(saveRole);
							permission.setPermission_Action(child.getKey() + "_View");
							permissions.add(permission);
						}
					}
				} else {
					if (model.getAdd() == 1) {
						Permission permission = new Permission();
						permission.setModel(model);
						permission.setRole(saveRole);
						permission.setPermission_Action(model.getKey() + "_Add");
						permissions.add(permission);
					}
					if (model.getDel() == 1) {
						Permission permission = new Permission();
						permission.setModel(model);
						permission.setRole(saveRole);
						permission.setPermission_Action(model.getKey() + "_Del");
						permissions.add(permission);
					}
					if (model.getEdit() == 1) {
						Permission permission = new Permission();
						permission.setModel(model);
						permission.setRole(saveRole);
						permission.setPermission_Action(model.getKey() + "_Edit");
						permissions.add(permission);
					}
					if (model.getPrint() == 1) {
						Permission permission = new Permission();
						permission.setModel(model);
						permission.setRole(saveRole);
						permission.setPermission_Action(model.getKey() + "_Print");
						permissions.add(permission);
					}
					if (model.getView() == 1) {
						Permission permission = new Permission();
						permission.setModel(model);
						permission.setRole(saveRole);
						permission.setPermission_Action(model.getKey() + "_View");
						permissions.add(permission);
					}
				}
			}
		}
		// 批量保存
		userRoleService.savePermissionList(permissions);
		return "redirect:/manage/role";
	}

	/**
	 * 编辑角色分配模块页面
	 * 
	 * @return
	 */
	@RequestMapping("/manage/role_edit/{id}")
	// @RequiresPermissions("")
	public ModelAndView roleEdit(@PathVariable int id) {
		Role role = userRoleService.getRoleById(id);
		List<Model> modelList = userRoleService.getModelList();
		List<Permission> permissionList = role.getPermissionList();
		for (Permission permission : permissionList) {
			if (permission != null) {
				Model model = permission.getModel();
				Integer permissionModelId = model.getId();
				String action = permission.getPermission_Action().split("_")[1];
				for (Model mo : modelList) {
					if (mo != null) {
						if (mo.getId().equals(permissionModelId)) {
							if (action.contains("View")) {
								mo.setView(mo.getView() + 1);
								break;
							}
							if (action.contains("Del")) {
								mo.setDel(mo.getDel() + 1);
								break;
							}
							if (action.contains("Add")) {
								mo.setAdd(mo.getAdd() + 1);
								break;
							}
							if (action.contains("Edit")) {
								mo.setEdit(mo.getEdit() + 1);
								break;
							}
							if (action.contains("Print")) {
								mo.setPrint(mo.getPrint() + 1);
								break;
							}
						}
					}
					List<Model> children = mo.getChildren();
					for (Model child : children) {
						if (child != null) {
							if (child.getId().equals(permissionModelId)) {
								if (action.contains("View")) {
									child.setView(child.getView() + 1);
									break;
								}
								if (action.contains("Del")) {
									child.setDel(child.getDel() + 1);
									break;
								}
								if (action.contains("Add")) {
									child.setAdd(child.getAdd() + 1);
									break;
								}
								if (action.contains("Edit")) {
									child.setEdit(child.getEdit() + 1);
									break;
								}
								if (action.contains("Print")) {
									child.setPrint(child.getPrint() + 1);
									break;
								}
							}
						}
					}
				}
			}
		}
		ModelAndView mv = new ModelAndView("/page/manage/role_edit");
		mv.addObject("modelList", modelList);
		mv.addObject("role", role);
		return mv;
	}

	/**
	 * 编辑角色分配模块实现
	 * 
	 * @param request
	 * @param role
	 * @param modelList
	 * @return
	 */
	@RequestMapping(value = "/manage/role_editContent", method = RequestMethod.POST)
	// @RequiresPermissions("")
	public String role_editContent(HttpServletRequest request, Role role, ModelList modelList) {
		// 编辑角色
		int updateRole = userRoleService.updateRole(role);
		Role roleById = userRoleService.getRoleById(role.getId());
		List<Permission> permissionList = roleById.getPermissionList();
		for (Permission permission : permissionList) {
			if (permission != null) {
				userRoleService.deletePermission(permission);
			}
		}
		// 模块列表
		List<Model> models = modelList.getModel();
		// 保存角色、模块关联表
		List<Permission> permissions = new ArrayList<>();
		for (Model model : models) {
			if (model != null) {
				List<Model> children = model.getChildren();
				if (children != null) {
					for (Model child : children) {
						if (child.getAdd() == 1) {
							Permission permission = new Permission();
							permission.setModel(child);
							permission.setRole(role);
							permission.setPermission_Action(child.getKey() + "_Add");
							permissions.add(permission);
						}
						if (child.getDel() == 1) {
							Permission permission = new Permission();
							permission.setModel(child);
							permission.setRole(role);
							permission.setPermission_Action(child.getKey() + "_Del");
							permissions.add(permission);
						}
						if (child.getEdit() == 1) {
							Permission permission = new Permission();
							permission.setModel(child);
							permission.setRole(role);
							permission.setPermission_Action(child.getKey() + "_Edit");
							permissions.add(permission);
						}
						if (child.getPrint() == 1) {
							Permission permission = new Permission();
							permission.setModel(child);
							permission.setRole(role);
							permission.setPermission_Action(child.getKey() + "_Print");
							permissions.add(permission);
						}
						if (child.getView() == 1) {
							Permission permission = new Permission();
							permission.setModel(child);
							permission.setRole(role);
							permission.setPermission_Action(child.getKey() + "_View");
							permissions.add(permission);
						}
					}
				} else {
					if (model.getAdd() == 1) {
						Permission permission = new Permission();
						permission.setModel(model);
						permission.setRole(role);
						permission.setPermission_Action(model.getKey() + "_Add");
						permissions.add(permission);
					}
					if (model.getDel() == 1) {
						Permission permission = new Permission();
						permission.setModel(model);
						permission.setRole(role);
						permission.setPermission_Action(model.getKey() + "_Del");
						permissions.add(permission);
					}
					if (model.getEdit() == 1) {
						Permission permission = new Permission();
						permission.setModel(model);
						permission.setRole(role);
						permission.setPermission_Action(model.getKey() + "_Edit");
						permissions.add(permission);
					}
					if (model.getPrint() == 1) {
						Permission permission = new Permission();
						permission.setModel(model);
						permission.setRole(role);
						permission.setPermission_Action(model.getKey() + "_Print");
						permissions.add(permission);
					}
					if (model.getView() == 1) {
						Permission permission = new Permission();
						permission.setModel(model);
						permission.setRole(role);
						permission.setPermission_Action(model.getKey() + "_View");
						permissions.add(permission);
					}
				}
			}
		}
		// 批量保存
		userRoleService.savePermissionList(permissions);
		return "redirect:/manage/role";
	}

	/**
	 * 删除角色
	 * 
	 * @param request
	 * @param role
	 * @param modelList
	 * @return
	 */

	@RequestMapping(value = "/manage/role_delete/{id}")
	// @RequiresPermissions("")
	public String role_delete(HttpServletRequest request, @PathVariable int id) {
		Role roleById = userRoleService.getRoleById(id);
		List<Permission> permissionList = roleById.getPermissionList();
		for (Permission permission : permissionList) {
			if (permission != null) {
				userRoleService.deletePermission(permission);
			}
		}
		userRoleService.deleteRole(id);
		return "redirect:/manage/role";
	}

	@RequestMapping("/manage/user_add")
	// @RequiresPermissions("")
	public ModelAndView userAdd() {

		ModelAndView mv = new ModelAndView("/page/manage/user_add");

		return mv;
	}

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("login");
		String message = Function.repalceIt(request.getParameter("message"));
		mv.addObject("message", message);
		logger.info("=======================00000==============" + message);
		return mv;
	}

	@RequestMapping(value = "/user/login_controller", method = RequestMethod.POST)
	public String login_controller(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		// ModelAndView mv = new ModelAndView("/index");
		String username = Function.repalceIt(request.getParameter("username"));
		String pwd = Function.repalceIt(request.getParameter("pwd"));
		UsernamePasswordToken token = new UsernamePasswordToken(username, MD5.getMD5(pwd));
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			logger.info("对用户[" + username + "]进行登录验证..验证开始");
			currentUser.login(token);
			logger.info("对用户[" + username + "]进行登录验证..验证通过");
		} catch (UnknownAccountException uae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
			redirectAttributes.addAttribute("message", "未知账户");
		} catch (IncorrectCredentialsException ice) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
			redirectAttributes.addAttribute("message", "密码不正确");
		} catch (LockedAccountException lae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
			redirectAttributes.addAttribute("message", "账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
			redirectAttributes.addAttribute("message", "用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			ae.printStackTrace();
			redirectAttributes.addAttribute("message", "用户名或密码不正确");
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
			return "redirect:/index";
		} else {
			token.clear();

			return "redirect:/login";

		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		redirectAttributes.addAttribute("message", "您已安全退出");

		try {
			response.sendRedirect(request.getContextPath() + "/login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping("/manage/info")
	@RequiresPermissions("edit")
	public ModelAndView userEdit() {
		ModelAndView mv = new ModelAndView("/page/faq");

		return mv;
	}

	@RequestMapping("/manage/editinfo")
	@RequiresPermissions("add")
	public String cachetest() {
		UserInfo user = userRoleService.searchUserInfoByName("admin");
		logger.debug(user.getUserName() + "---------------------------");

		userRoleService.getUserInfoList();
		logger.debug("-2222222222222222222222--------------------------");
		userRoleService.getUserInfoList();
		logger.debug("-333333333333333333333333333333333--------------------------");
		UserInfo user2 = userRoleService.searchUserInfoByName("aaaa");
		logger.debug(user2.getUserName() + "---------------------------");

		UserInfo user3 = userRoleService.searchUserInfoByName("admin");
		logger.debug(user3.getUserName() + "-11111111111111111--------------------------");
		return "serviceerror";
	}

}
