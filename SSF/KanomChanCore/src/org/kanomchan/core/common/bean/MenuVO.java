package org.kanomchan.core.common.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.kanomchan.core.security.authorize.bean.MenuBean;

public class MenuVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8621079534551775788L;
	private List<MenuBean> menuBeans;
	private Map<Long, MenuBean> lookupMap;
	public List<MenuBean> getMenuBeans() {
		return menuBeans;
	}
	public void setMenuBeans(List<MenuBean> menuBeans) {
		this.menuBeans = menuBeans;
	}
	public Map<Long, MenuBean> getLookupMap() {
		return lookupMap;
	}
	public void setLookupMap(Map<Long, MenuBean> lookupMap) {
		this.lookupMap = lookupMap;
	}
	
	
	

}
