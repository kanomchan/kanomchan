package org.kanomchan.core.security.authorize.service;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;

public interface UserAuthorizeService {
	public ServiceResult<UserBean> addRolesUser(UserBean userBean) throws NonRollBackException , RollBackException;
}
