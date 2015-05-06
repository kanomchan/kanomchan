package org.kanomchan.projectname.security.authorize.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.kanomchan.core.common.bean.ActionBean;
import org.kanomchan.core.common.bean.MenuVO;
import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.security.authorize.bean.Menu;
import org.kanomchan.core.security.authorize.bean.MenuBean;
import org.kanomchan.core.security.authorize.dao.ActionDao;
import org.kanomchan.core.security.authorize.dao.UserAuthorizeDao;
import org.kanomchan.core.security.authorize.dao.UserMenuDao;
import org.kanomchan.core.security.authorize.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class UserMenuServiceImpl implements UserMenuService {

	private UserMenuDao userMenuDao;
	@Autowired
	@Required
	public void setUserMenuDao(UserMenuDao userMenuDao) {
		this.userMenuDao = userMenuDao;
	}
	
	private ActionDao actionDao;
	
	@Autowired
	@Required
	public void setActionDao(ActionDao actionDao) {
		this.actionDao = actionDao;
	}
	
	private UserAuthorizeDao userAuthorizeDao;
	@Autowired
	@Required
	public void setUserAuthorizeDao(UserAuthorizeDao userAuthorizeDao) {
		this.userAuthorizeDao = userAuthorizeDao;
	}
	
	@Override
	public ServiceResult<MenuVO> generateMenuList(UserBean userBean) throws NonRollBackException, RollBackException {
		return generateMenuList(userBean.getPrivileges());
	}
	@Override
	public ServiceResult<MenuVO> generateMenuList(Set<String> privileges) throws NonRollBackException, RollBackException {
		List<Menu> menuList = userMenuDao.findAll();
		
		List<MenuBean> result = new LinkedList<MenuBean>();//output main list
		HashMap<Long, MenuBean> lookup = new HashMap<Long, MenuBean>();//lookup bean parent { for bean chide find bean parent }
		if(menuList!=null&&menuList.size()!=0){
//			Set<String> privileges = userBean.getPrivileges();
			if(privileges!=null){
//				
				for (Menu menu : menuList) {
					if(privileges.contains(menu.getObjId())){
						MenuBean menuBean = new MenuBean();
						
						ActionBean actionBean = actionDao.getActionByActionId(menu.getActionId());
						if(actionBean!=null){
							if("U".equalsIgnoreCase(actionBean.getType()) ){
								menuBean.setUrl(actionBean.getUrl());
							}else{
								menuBean.setUrl(actionBean.getNameSpace()+"/"+actionBean.getActionName()+".htm");
							}
						}
						menuBean.setMenuName(menu.getMenuName());
						menuBean.setLevel(menu.getLevel());
						menuBean.setParentId(menu.getParentId());
						menuBean.setMenuId(menu.getMenuId());
						menuBean.setType(menu.getMenuType());
//						menuBean.setUrl(menu.getUrl());
//						Add menu item to parent
						if(menu.getParentId()==null){//
//							case root or main list
							result.add(menuBean);
						}else{
//							case sub menu create sublist
							MenuBean parent = lookup.get(menu.getParentId());
							if (parent!=null) {
								parent.addChildMenu(menuBean);
							} else {
//								ignore case invalid parent m
							}
						}
						lookup.put(menu.getMenuId(), menuBean);// set parent lookup
					}
				}
			}
		}
		MenuVO menuVO = new MenuVO();
		menuVO.setMenuBeans(result);
		menuVO.setLookupMap(lookup);
		return new ServiceResult<MenuVO>(menuVO);
	}
	@Override
	public ServiceResult<MenuVO> generateMenuGuest() throws NonRollBackException, RollBackException {
		Set<String> privileges = userAuthorizeDao.getUserPrivilegesByRoleId("3");
		return generateMenuList(privileges);
	}

}
