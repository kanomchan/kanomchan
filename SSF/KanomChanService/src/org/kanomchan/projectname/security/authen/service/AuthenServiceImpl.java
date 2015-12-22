<<<<<<< HEAD
package org.kanomchan.projectname.security.authen.service;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.constant.CommonMessageCode;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.exception.RollBackProcessException;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.security.authen.service.AuthenService;
import org.kanomchan.projectname.usermanament.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class AuthenServiceImpl  implements AuthenService {

	private UserDao userDao;
	@Autowired
	@Required
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	//login
	@Override
	public ServiceResult<UserBean> login(String username, String password) throws NonRollBackException , RollBackException {
		UserBean userBean = userDao.findUserByUsername(username);
		
		if(userBean==null||!userBean.getPassword().equals(password)){
			throw new RollBackProcessException(CommonMessageCode.ATC2001);
		}
		if(!CommonConstant.ACTIVE.equals(userBean.getStatus())){
			throw new RollBackProcessException(CommonMessageCode.ATC2002);
		}
		
		return new ServiceResult<UserBean>(userBean);
	}


	@Override
	public ServiceResult<UserBean> login(Long userId)
			throws NonRollBackException, RollBackException {
		// TODO Auto-generated method stub
		return null;
	}
}
=======
package org.kanomchan.projectname.security.authen.service;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.constant.CommonMessageCode;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.exception.RollBackProcessException;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.security.authen.service.AuthenService;
import org.kanomchan.projectname.usermanament.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class AuthenServiceImpl  implements AuthenService {

	private UserDao userDao;
	@Autowired
	@Required
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	//login
	@Override
	public ServiceResult<UserBean> login(String username, String password) throws NonRollBackException , RollBackException {
		UserBean userBean = userDao.findUserByUsername(username);
		
		if(userBean==null||!userBean.getPassword().equals(password)){
			throw new RollBackProcessException(CommonMessageCode.ATC2001);
		}
		if(!CommonConstant.ACTIVE.equals(userBean.getStatus())){
			throw new RollBackProcessException(CommonMessageCode.ATC2002);
		}
		
		return new ServiceResult<UserBean>(userBean);
	}


	@Override
	public ServiceResult<UserBean> login(Long userId)
			throws NonRollBackException, RollBackException {
		// TODO Auto-generated method stub
		return null;
	}
}
>>>>>>> branch 'master' of https://github.com/viatoro/kanomchan.git
