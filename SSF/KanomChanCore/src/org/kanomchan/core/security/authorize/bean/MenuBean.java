package org.kanomchan.core.security.authorize.bean;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MenuBean [");
		if (url != null) {
			builder.append("url=");
			builder.append(url);
			builder.append(", ");
		}
		if (menuName != null) {
			builder.append("menuName=");
			builder.append(menuName);
			builder.append(", ");
		}
		if (childMenu != null) {
			builder.append("childMenu=");
			builder.append(childMenu);
			builder.append(", ");
		}
		if (level != null) {
			builder.append("level=");
			builder.append(level);
			builder.append(", ");
		}
		if (menuId != null) {
			builder.append("menuId=");
			builder.append(menuId);
			builder.append(", ");
		}
		if (parentId != null) {
			builder.append("parentId=");
			builder.append(parentId);
		}
		builder.append("]");
		return builder.toString();
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
	
	

	
	
}
