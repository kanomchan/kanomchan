package org.kanomchan.projectname.security.authen.service;

import java.util.Map;

import org.kanomchan.core.common.bean.MenuVO;
import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.constant.CommonMessageCode;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.exception.RollBackProcessException;
import org.kanomchan.core.common.io.LoginIO;
import org.kanomchan.core.common.io.LoginIOBean;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.openid.service.OpenIdClientService;
import org.kanomchan.core.security.authen.service.AuthenService;
import org.kanomchan.core.security.authen.service.LoginService;
import org.kanomchan.core.security.authorize.service.UserAuthorizeService;
import org.kanomchan.core.security.authorize.service.UserMenuService;
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
	
	private UserMenuService userMenuService;
	@Autowired
	@Required
	public void setUserMenuService(UserMenuService userMenuService) {
		this.userMenuService = userMenuService;
	}
	
	private OpenIdClientService openIdClientService;
	@Autowired
	@Required
	public void setOpenIdClientService(OpenIdClientService openIdClientService) {
		this.openIdClientService = openIdClientService;
	}
	
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

	@Override
	public ServiceResult<LoginIO> performLoginAndPutDataSession(String username, String password) throws NonRollBackException, RollBackException {
		LoginIO loginIO = new LoginIOBean();
		ServiceResult<UserBean> serviceResult = performLogin(username, password);
		if(serviceResult.isSuccess()){
			UserBean userBean = serviceResult.getResult();
			ServiceResult<MenuVO> serviceResultMenu = userMenuService.generateMenuList(serviceResult.getResult());
			
			if(serviceResultMenu.isSuccess()){
				loginIO.setUserBean(userBean);
				loginIO.setMenuVO(serviceResultMenu.getResult());
				return new ServiceResult<LoginIO>(loginIO);
			}
		}
		throw new RollBackProcessException(CommonMessageCode.ATC2001);
	}

	@Override
	public ServiceResult<LoginIO> performLoginAndPutDataSession(String authorizationCode, String state, Map<String, Object> session) throws NonRollBackException, RollBackException {
		LoginIO loginIO = new LoginIOBean();
		ServiceResult<UserBean> serviceResult = openIdClientService.handleAuthorizationCodeResponse(authorizationCode, state, session);
		serviceResult = userAuthorizeService.addRolesUser(serviceResult.getResult());
		if(serviceResult.isSuccess()){
			UserBean userBean = serviceResult.getResult();
			ServiceResult<MenuVO> serviceResultMenu = userMenuService.generateMenuList(serviceResult.getResult());
			if(serviceResultMenu.isSuccess()){
				loginIO.setUserBean(userBean);
				loginIO.setMenuVO(serviceResultMenu.getResult());
				return new ServiceResult<LoginIO>(loginIO);
			}
		}
		throw new RollBackProcessException(CommonMessageCode.ATC2001);
	}
}
