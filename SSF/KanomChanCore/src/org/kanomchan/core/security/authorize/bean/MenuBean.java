package org.kanomchan.core.security.authorize.bean;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.kanomchan.core.common.bean.ActionBean;

public class MenuBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6924183244041019540L;
	/**
	 * 
	 */

	private String url;
	private String menuName;
	private List<MenuBean> childMenu;
	private Integer level;
	private Integer menuId;
	private Integer parentId;
	private String type;
	private ActionBean actionBean;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<MenuBean> getChildMenu() {
		return childMenu;
	}
	public void setChildMenu(List<MenuBean> childMenu) {
		this.childMenu = childMenu;
	}
	public void addChildMenu(MenuBean menuBean) {
		if(childMenu == null)
			childMenu= new LinkedList<MenuBean>();
			childMenu.add(menuBean);
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ActionBean getActionBean() {
		return actionBean;
	}
	public void setActionBean(ActionBean actionBean) {
		this.actionBean = actionBean;
	}
	@Override
	public String toString() {
		return "MenuBean [url=" + url + ", menuName=" + menuName
				+ ", childMenu=" + childMenu + ", level=" + level + ", menuId="
				+ menuId + ", parentId=" + parentId + ", type=" + type + "]";
	}
	

	
	
}
