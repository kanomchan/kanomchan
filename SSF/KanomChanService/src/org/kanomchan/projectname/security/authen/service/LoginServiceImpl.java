package org.kanomchan.projectname.security.authen.service;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonMessageCode;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.exception.RollBackProcessException;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.security.authen.service.AuthenService;
import org.kanomchan.core.security.authen.service.LoginService;
import org.kanomchan.core.security.authorize.service.UserAuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class LoginServiceImpl implements LoginService {

	private AuthenService authenService;
	@Autowired
	@Required
	public void setAuthenService(AuthenService authenService) {
		this.authenService = authenService;
	}
	
	private UserAuthorizeService userAuthorizeService;
	@Autowired
	@Required
	public void setUserAuthorizeService(UserAuthorizeService userAuthorizeService) {
		this.userAuthorizeService = userAuthorizeService;
	}
	
//	private UserMenuService userMenuService;
//	@Autowired
//	@Required
//	public void setUserMenuService(UserMenuService userMenuService) {
//		this.userMenuService = userMenuService;
//	}
	
	@Override
	public ServiceResult<UserBean> performLogin(String username, String password) throws NonRollBackException, RollBackException {
		
		ServiceResult<UserBean> serviceResult = authenService.login(username, password);
		if(serviceResult.isSuccess()){
			return  userAuthorizeService.addRolesUser(serviceResult.getResult());
//			if(serviceResult.isSuccess()){
//				return userMenuService.addMenu(serviceResult.getResult());
//			}else{
//				throw new RollBackServiceException(MessageCode.ATC2001);
//			}
		}else{
			throw new RollBackProcessException(CommonMessageCode.ATC2001);
		}
	}
}
