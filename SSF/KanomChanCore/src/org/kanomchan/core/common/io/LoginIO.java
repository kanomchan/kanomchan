package org.kanomchan.core.common.io;

import java.util.Map;

import org.kanomchan.core.common.bean.MenuVO;
import org.kanomchan.core.common.bean.UserBean;

public interface LoginIO {
	
	public UserBean getUserBean();
	public void setUserBean(UserBean userBean);
	public MenuVO getMenuVO();
	public void setMenuVO(MenuVO menuVO);
	public Map<String, Object> getSession();
	public void setSession(Map<String, Object> session);
	

}
