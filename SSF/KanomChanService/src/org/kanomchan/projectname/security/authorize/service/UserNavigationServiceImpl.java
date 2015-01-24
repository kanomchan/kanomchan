package org.kanomchan.projectname.security.authorize.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.kanomchan.core.common.bean.ActionBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.security.authorize.bean.Menu;
import org.kanomchan.core.security.authorize.bean.MenuBean;
import org.kanomchan.core.security.authorize.dao.ActionDao;
import org.kanomchan.core.security.authorize.dao.UserMenuDao;
import org.kanomchan.core.security.authorize.service.UserNavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

public class UserNavigationServiceImpl implements UserNavigationService{

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
	
	@Override
	@Cacheable(cacheName = "generateNavigationList")
	public ServiceResult<List<MenuBean>> generateNavigationList(
			String namespace, String actionName, String url) throws NonRollBackException, RollBackException {
		ActionBean currentAction = actionDao.getActionByNamespaceAndActionName(namespace, actionName);
		if(currentAction!=null){
			List<Menu> menuList = userMenuDao.findAll();
			List<Menu> currentMenuList = userMenuDao.findAllByAction(currentAction.getActionId());
			Integer parent = currentMenuList.get(0).getParentId();
			LinkedList<MenuBean> result = new LinkedList<MenuBean>();
			HashMap<Integer, MenuBean> lookup = new HashMap<Integer, MenuBean>();
			if(menuList!=null&&menuList.size()!=0){
				for (Menu menu : menuList) {
					MenuBean menuBean = new MenuBean();
					
					ActionBean actionBean = actionDao.getActionByActionId(menu.getActionId());
					if(actionBean!=null){
						if("U".equalsIgnoreCase(actionBean.getType()) ){
							menuBean.setUrl(actionBean.getUrl());
						}else{
							menuBean.setUrl(actionBean.getNameSpace()+"/"+actionBean.getActionName()+".htm");
						}
					}
					else{
						menuBean.setUrl(null);
					}
					menuBean.setMenuName(menu.getMenuName());
					menuBean.setLevel(menu.getLevel());
					menuBean.setParentId(menu.getParentId());
					menuBean.setMenuId(menu.getMenuId());
					lookup.put(menu.getMenuId(), menuBean);
				}
				result.addFirst(lookup.get(currentMenuList.get(0).getMenuId()));
				for(int i=0;i<currentMenuList.get(0).getLevel();i++){
					result.addFirst(lookup.get(parent));
					parent = lookup.get(parent).getParentId();
					if(parent==null){break;}
				}
			}
			return new ServiceResult<List<MenuBean>>(result);
		}
		
		return new ServiceResult<List<MenuBean>>(new ArrayList<MenuBean>());
	}

	@Override
	@TriggersRemove(cacheName="generateNavigationList", removeAll=true)
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
