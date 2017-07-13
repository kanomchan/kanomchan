package org.kanomchan.core.security.authorize.service;

import java.util.Set;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonMessageCode;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.exception.RollBackProcessException;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.security.authorize.dao.UserAuthorizeDao;
import org.kanomchan.core.security.authorize.service.UserAuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class UserAuthorizeServiceImpl implements UserAuthorizeService {

	
	private UserAuthorizeDao userAuthorizeDao;
	@Autowired
	@Required
	public void setUserAuthorizeDao(UserAuthorizeDao userAuthorizeDao) {
		this.userAuthorizeDao = userAuthorizeDao;
	}
	@Override
	public ServiceResult<UserBean> addRolesUser(UserBean userBean) throws NonRollBackException, RollBackException {
		Set<String> roles = userAuthorizeDao.getUserRoles(userBean.getUserId());
		if(roles.size()<=0){
			throw new RollBackProcessException(CommonMessageCode.ATZ2002);
		}
		Set<String> privileges = userAuthorizeDao.getUserPrivileges(userBean.getUserId());
		userBean.setRoles(roles);
		userBean.setPrivileges(privileges);
		return new ServiceResult<UserBean>(userBean);
	}

}
