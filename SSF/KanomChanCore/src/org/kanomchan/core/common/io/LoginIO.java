package org.kanomchan.core.common.io;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.kanomchan.core.common.bean.MenuVO;
import org.kanomchan.core.common.bean.UserBean;

public interface LoginIO {
	
	public UserBean getUserBean();
	public void setUserBean(UserBean userBean);
	public MenuVO getMenuVO();
	public void setMenuVO(MenuVO menuVO);
	public Map<String, Object> getSession();
	public void setSession(Map<String, Object> session);
	public List<Cookie> getCookies();
	public void setCookies(List<Cookie> cookies);
	public String getGotoPage();
	public void setGotoPage(String gotoPage);

}