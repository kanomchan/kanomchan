package org.kanomchan.core.security.authen.service;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;

public interface AuthenService {

	public ServiceResult<UserBean> login(String username, String password) throws NonRollBackException, RollBackException;

	public ServiceResult<UserBean> login(Long userId) throws NonRollBackException,RollBackException;
}
