package org.kanomchan.core.security.authen.dao;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;

public interface UserDao {

	public ServiceResult<UserBean> findUserByUsername(String username) throws NonRollBackException, RollBackException;

}
