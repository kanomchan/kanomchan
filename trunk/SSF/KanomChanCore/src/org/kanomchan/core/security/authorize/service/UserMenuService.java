package org.kanomchan.core.security.authorize.service;

import java.util.List;
import java.util.Set;

import org.kanomchan.core.common.bean.MenuVO;
import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.security.authorize.bean.MenuBean;

public interface UserMenuService {
	public ServiceResult<MenuVO> generateMenuList(UserBean userBean)throws NonRollBackException , RollBackException;
	public ServiceResult<MenuVO> generateMenuList(Set<String> privileges)throws NonRollBackException , RollBackException;
	public ServiceResult<MenuVO> generateMenuGuest()throws NonRollBackException , RollBackException;

}
