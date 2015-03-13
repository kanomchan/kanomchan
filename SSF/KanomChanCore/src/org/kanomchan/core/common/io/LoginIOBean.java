package org.kanomchan.core.common.io;

import java.util.HashMap;
import java.util.Map;

import org.kanomchan.core.common.bean.MenuVO;
import org.kanomchan.core.common.bean.UserBean;

public class LoginIOBean  implements LoginIO{
	
	private UserBean userBean;
	private MenuVO menuVO;
	private Map<String, Object> session = new HashMap<String, Object>();
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public MenuVO getMenuVO() {
		return menuVO;
	}
	public void setMenuVO(MenuVO menuVO) {
		this.menuVO = menuVO;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	

}
