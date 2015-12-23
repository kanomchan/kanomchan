package org.kanomchan.core.security.authorize.io;

import java.io.Serializable;
import java.util.List;

import org.kanomchan.core.common.bean.ActionBean;
import org.kanomchan.core.security.authorize.bean.MenuBean;

public class NavigationBean implements Serializable {
	
	private List<MenuBean> navigationMenuList;
	private ActionBean currentAction;
	public List<MenuBean> getNavigationMenuList() {
		return navigationMenuList;
	}
	public void setNavigationMenuList(List<MenuBean> navigationMenuList) {
		this.navigationMenuList = navigationMenuList;
	}
	public ActionBean getCurrentAction() {
		return currentAction;
	}
	public void setCurrentAction(ActionBean currentAction) {
		this.currentAction = currentAction;
	}
	
	

}
