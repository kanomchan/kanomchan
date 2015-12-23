package org.kanomchan.core.common.io;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.kanomchan.core.common.bean.MenuVO;
import org.kanomchan.core.common.bean.UserBean;

public class LoginIOBean  implements LoginIO ,Serializable{
	
	private UserBean userBean;
	private MenuVO menuVO;

	private String gotoPage;

	public String getGotoPage() {
		return gotoPage;
	}
	public void setGotoPage(String gotoPage) {
		this.gotoPage = gotoPage;
	}

	private Map<String, Object> session = new HashMap<String, Object>();
	private List<Cookie> cookies = new ArrayList<Cookie>();
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
	public List<Cookie> getCookies() {
		return cookies;
	}
	public void setCookies(List<Cookie> cookies) {
		this.cookies = cookies;
	}

}
