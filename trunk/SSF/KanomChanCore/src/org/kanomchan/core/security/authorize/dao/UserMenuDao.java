package org.kanomchan.core.security.authorize.dao;

import java.util.List;

import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.security.authorize.bean.Menu;


public interface UserMenuDao {

	public List<Menu> findAll()throws NonRollBackException,RollBackException;
	public List<Menu> findAllForNavigation()throws NonRollBackException,RollBackException;

	public List<Menu> findAllByAction(Integer actionId)throws NonRollBackException, RollBackException;
	public List<Menu> findAllByMenuId(Integer menuId)throws NonRollBackException, RollBackException;
}
