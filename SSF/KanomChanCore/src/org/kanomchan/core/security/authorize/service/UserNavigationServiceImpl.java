package org.kanomchan.core.security.authorize.service;

import org.apache.log4j.Logger;

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
import org.kanomchan.core.security.authorize.io.NavigationBean;
import org.kanomchan.core.security.authorize.service.UserNavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

public class UserNavigationServiceImpl implements UserNavigationService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserNavigationServiceImpl.class);

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
	public ServiceResult<NavigationBean> generateNavigationList(
			String namespace, String actionName, String url) throws NonRollBackException, RollBackException {
		ActionBean currentAction = actionDao.getActionByUrl(url);
		NavigationBean navigationBean = new NavigationBean();
		if(currentAction == null){
			currentAction = actionDao.getActionByNamespaceAndActionName(namespace, actionName);
		}
		if(currentAction!=null){
			List<Menu> menuList = userMenuDao.findAllForNavigation();
			List<Menu> currentMenuList = userMenuDao.findAllByMenuId(currentAction.getMenuId());
			LinkedList<MenuBean> result = new LinkedList<MenuBean>();
			if(currentMenuList != null && currentMenuList.size() > 0){
				Long parent = currentMenuList.get(0).getParentId();
				HashMap<Long, MenuBean> lookup = new HashMap<Long, MenuBean>();
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
							menuBean.setActionBean(actionBean);
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
					try{
						for(int i=0;i<currentMenuList.get(0).getLevel();i++){
							result.addFirst(lookup.get(parent));
							parent = lookup.get(parent).getParentId();
							if(parent==null){break;}
						}
					}catch(Exception e){
						logger.error("(check praentID menu IN table: SYS_M_MENU )currentMenuList.get(0).getMenuId() :"+currentMenuList.get(0).getMenuId(),e);
					}
				}
			}
			navigationBean.setNavigationMenuList(result);
			navigationBean.setCurrentAction(currentAction);
			return new ServiceResult<NavigationBean>(navigationBean);
		}
		navigationBean.setNavigationMenuList(new ArrayList<MenuBean>());
		navigationBean.setCurrentAction(currentAction);
		return new ServiceResult<NavigationBean>(navigationBean);
	}

	@Override
	@TriggersRemove(cacheName="generateNavigationList", removeAll=true)
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
