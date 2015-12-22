<<<<<<< HEAD
package org.kanomchan.projectname.security.authen.service;

import org.kanomchan.core.common.bean.MenuVO;
import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonMessageCode;
import org.kanomchan.core.common.cookie.bean.CookieOrm;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.exception.RollBackProcessException;
import org.kanomchan.core.common.io.LoginIO;
import org.kanomchan.core.common.io.LoginIOBean;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.common.service.ConfigService;
import org.kanomchan.core.common.service.LocationService;
import org.kanomchan.core.openid.service.OpenIdClientService;
import org.kanomchan.core.security.authen.service.AuthenService;
import org.kanomchan.core.security.authen.service.LoginService;
import org.kanomchan.core.security.authorize.service.UserAuthorizeService;
import org.kanomchan.core.security.authorize.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired(required=false)
	@Qualifier("loginService")
	private LoginService self;
	public void setSelf(LoginService self) {
		this.self = self;
	}
	@Autowired
	private AuthenService authenService;
	@Autowired
	private UserAuthorizeService userAuthorizeService;
//	@Autowired
//	private UserMenuService userMenuService;
	@Autowired
	private OpenIdClientService openIdClientService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private ConfigService configService;

	
	@Override
	public ServiceResult<UserBean> performLogin(String username, String password) throws NonRollBackException, RollBackException {
		
		ServiceResult<UserBean> serviceResult = authenService.login(username, password);
		return  userAuthorizeService.addRolesUser(serviceResult.getResult());
	}

	@Override
	public ServiceResult<LoginIO> performLoginAndPutDataSession(String username, String password,CookieOrm cookieOrm) throws NonRollBackException, RollBackException {
		LoginIO loginIO = new LoginIOBean();
		ServiceResult<UserBean> serviceResult = self.performLogin(username, password);
		UserBean userBean = serviceResult.getResult();
		loginIO.setUserBean(userBean);
//		ServiceResult<MenuVO> serviceResultMenu = userMenuService.generateMenuList(serviceResult.getResult());
//		loginIO.setMenuVO(serviceResultMenu.getResult());
		return new ServiceResult<LoginIO>(loginIO);
	}

	@Override
	public ServiceResult<LoginIO> performLoginWithOutPasswordAndPutDataSession(String username,CookieOrm cookieOrm) throws NonRollBackException, RollBackException {
		throw new RollBackProcessException(CommonMessageCode.ATC2001);
	}

	@Override
	public ServiceResult<LoginIO> performLoginWithOutPasswordAndPutDataSession(Long userId, CookieOrm cookieOrm) throws NonRollBackException,RollBackException {
		throw new RollBackProcessException(CommonMessageCode.ATC2001);
	}

}
=======
package org.kanomchan.projectname.security.authen.service;

import org.kanomchan.core.common.bean.MenuVO;
import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonMessageCode;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.exception.RollBackProcessException;
import org.kanomchan.core.common.io.LoginIO;
import org.kanomchan.core.common.io.LoginIOBean;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.common.service.ConfigService;
import org.kanomchan.core.common.service.LocationService;
import org.kanomchan.core.openid.service.OpenIdClientService;
import org.kanomchan.core.security.authen.bean.IUserDefault;
import org.kanomchan.core.security.authen.service.AuthenService;
import org.kanomchan.core.security.authen.service.LoginService;
import org.kanomchan.core.security.authorize.service.UserAuthorizeService;
import org.kanomchan.core.security.authorize.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginServiceImpl implements LoginService {

	@Autowired
	private AuthenService authenService;
	@Autowired
	private UserAuthorizeService userAuthorizeService;
	@Autowired
	private UserMenuService userMenuService;
	@Autowired
	private OpenIdClientService openIdClientService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private ConfigService configService;

	
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
	public ServiceResult<LoginIO> performLoginWithOutPasswordAndPutDataSession(String username) throws NonRollBackException, RollBackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult<LoginIO> performLoginAndPutDataSession(UserBean userBean, IUserDefault userDefault)throws NonRollBackException, RollBackException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ServiceResult<LoginIO> performLoginAndPutDataSession(String authorizationCode, String state, Map<String, Object> session) throws NonRollBackException, RollBackException {
//		LoginIO loginIO = new LoginIOBean();
//		ServiceResult<UserBean> serviceResult = openIdClientService.handleAuthorizationCodeResponse(authorizationCode, state, session);
//		serviceResult = userAuthorizeService.addRolesUser(serviceResult.getResult());
//		if(serviceResult.isSuccess()){
//			UserBean userBean = serviceResult.getResult();
//			ServiceResult<MenuVO> serviceResultMenu = userMenuService.generateMenuList(serviceResult.getResult());
//			if(serviceResultMenu.isSuccess()){
//				loginIO.setUserBean(userBean);
//				loginIO.setMenuVO(serviceResultMenu.getResult());
//				return new ServiceResult<LoginIO>(loginIO);
//			}
//		}
//		throw new RollBackProcessException(CommonMessageCode.ATC2001);
//	}
//
//	@Override
//	public ServiceResult<AuthRequestBean> startSSO() throws NonRollBackException, RollBackException {
//		return  openIdClientService.handleAuthorizationRequest(configService.get("SSO_REDIRECTURI"), configService.get("SSO_IDENTIFIER"));
//	}
//
//	@Override
//	public ServiceResult<AuthRequestBean> startSSO(String identifier) throws NonRollBackException, RollBackException {
//		return  openIdClientService.handleAuthorizationRequest(configService.get("SSO_REDIRECTURI"), identifier);
//	}
//
//	@Override
//	public ServiceResult<AuthRequestBean> startSSO(String identifier, String redirectUri) throws NonRollBackException, RollBackException {
//		return  openIdClientService.handleAuthorizationRequest(redirectUri, identifier);
//	}
//
//	@Override
//	public ServiceResult<AuthRequestBean> handleAuthorizationRequest(String redirectUri, String identifier) throws NonRollBackException, RollBackException {
//		return openIdClientService.handleAuthorizationRequest(redirectUri, identifier);
//	}
}
>>>>>>> refs/heads/v1.3.0
