package org.kanomchan.projectname.authen.action.web;

import java.util.List;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.common.web.struts.action.BaseAction;
import org.kanomchan.core.security.authen.service.LoginService;
import org.kanomchan.core.security.authorize.bean.MenuBean;
import org.kanomchan.core.security.authorize.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class LoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2275327612858235569L;
	private String user;
	private String password;
	
	private LoginService loginService;
	@Autowired
	@Required
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	private UserMenuService userMenuService;
	@Autowired
	@Required
	public void setUserMenuService(UserMenuService userMenuService) {
		this.userMenuService = userMenuService;
	}
	
	@Override
	public String init() throws Exception {
		
		return "loginPage";
	}
	
	public String login() throws Exception{
		ServiceResult<UserBean> serviceResult = loginService.performLogin(user, password);
		
		if(serviceResult.isSuccess()){
			UserBean userBean = serviceResult.getResult();
			ServiceResult<List<MenuBean>> serviceResultMenu = userMenuService.generateMenuList(userBean);
			if(serviceResultMenu.isSuccess()){
				session.put(CommonConstant.SESSION.MENU_BEAN_KEY, serviceResultMenu.getMessages());
			}
			session.put(CommonConstant.SESSION.USER_BEAN_KEY, userBean);
			return "FORCE_TO_LOGGEDIN_WELCOME_PAGE";
		}else{
			messageList = serviceResult.getMessages();
			return "loginPage";
		}
		
		
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
