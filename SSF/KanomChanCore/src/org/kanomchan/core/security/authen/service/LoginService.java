package org.kanomchan.core.security.authen.service;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;

public interface LoginService {

	public ServiceResult<UserBean> performLogin(String username, String password)throws NonRollBackException,RollBackException;

}
