package org.kanomchan.core.security.authorize.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
	private Long level;
	private Long menuId;
	private Long parentId;
	private Set<Long> setActiveMenuId;
	private String type;
	private ActionBean actionBean;

//	public boolean isActive() {
//		
//		return active;
//	}
	public boolean isActive(Long menuId) {
		return setActiveMenuId.contains(menuId);
	}

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
		if(setActiveMenuId==null)
			setActiveMenuId = new HashSet<Long>();
		childMenu.add(menuBean);
		setActiveMenuId.add(menuBean.getMenuId());
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
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
		if(setActiveMenuId==null)
			setActiveMenuId = new HashSet<Long>();
		setActiveMenuId.add(menuId);
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
		StringBuilder builder = new StringBuilder();
		builder.append("MenuBean [url=");
		builder.append(url);
		builder.append(", menuName=");
		builder.append(menuName);
		builder.append(", childMenu=");
		builder.append(childMenu);
		builder.append(", level=");
		builder.append(level);
		builder.append(", menuId=");
		builder.append(menuId);
		builder.append(", parentId=");
		builder.append(parentId);
		builder.append(", setActiveMenuId=");
		builder.append(setActiveMenuId);
		builder.append(", type=");
		builder.append(type);
		builder.append(", actionBean=");
		builder.append(actionBean);
		builder.append("]");
		return builder.toString();
	}
	
//	public Set<Long> getSetChildMenuId() {
//		return setChildMenuId;
//	}
//	public void setSetChildMenuId(Set<Long> setChildMenuId) {
//		this.setChildMenuId = setChildMenuId;
//	}

	
	

	
	
}
