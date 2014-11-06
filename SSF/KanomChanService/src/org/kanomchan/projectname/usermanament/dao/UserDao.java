package org.kanomchan.projectname.usermanament.dao;

import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.projectname.usermanament.bean.User;


public interface UserDao {

	public User findUserByUsername(String username) throws NonRollBackException, RollBackException;

	public User addUser(User user) throws RollBackException, NonRollBackException;

	public User findUserByIdUser(Long id) throws RollBackException, NonRollBackException;
	
	public User changePassword(User userBean) throws RollBackException, NonRollBackException;

	public User update(User user) throws RollBackException, NonRollBackException;

}
