package org.kanomchan.core.common.bean;

import java.util.List;
import java.util.Map;

import org.kanomchan.core.security.authorize.bean.MenuBean;

public class MenuVO {
	
	private List<MenuBean> menuBeans;
	private Map<Integer, MenuBean> lookupMap;
	public List<MenuBean> getMenuBeans() {
		return menuBeans;
	}
	public void setMenuBeans(List<MenuBean> menuBeans) {
		this.menuBeans = menuBeans;
	}
	public Map<Integer, MenuBean> getLookupMap() {
		return lookupMap;
	}
	public void setLookupMap(Map<Integer, MenuBean> lookupMap) {
		this.lookupMap = lookupMap;
	}
	
	
	

}
