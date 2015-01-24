package org.kanomchan.core.security.authorize.bean;

public class Menu {
	private Integer menuId;
	private String menuName;
	private Integer level;
	private String objId;
	private Integer actionId;
	private Integer parentId;
	private String MenuType;
	
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Integer getActionId() {
		return actionId;
	}
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
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
	
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getLevel() {
		return level;
	}
	public String getMenuType() {
		return MenuType;
	}
	public void setMenuType(String menuType) {
		MenuType = menuType;
	}
	
	

}
