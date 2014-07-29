package org.kanomchan.usermanament.dao;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;

public interface UserDao {

	public UserBean findUserByUsername(String username) throws NonRollBackException, RollBackException;

	public UserBean addUser(UserBean comUser) throws RollBackException, NonRollBackException;

	public UserBean findUserByIdUser(Long id) throws RollBackException, NonRollBackException;

}
