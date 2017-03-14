package org.kanomchan.core.security.authorize.bean;

public class Menu {
	private Long menuId;
	private String menuName;
	private Long level;
	private String objId;
	private Long actionId;
	private Long parentId;
	private String MenuType;
	private String status;
	
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

	public Long getActionId() {
		return actionId;
	}
	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
	public void setLevel(Long level) {
		this.level = level;
	}
	public Long getLevel() {
		return level;
	}
	public String getMenuType() {
		return MenuType;
	}
	public void setMenuType(String menuType) {
		MenuType = menuType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
