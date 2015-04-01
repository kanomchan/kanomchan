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
import org.kanomchan.core.common.service.ConfigService;
import org.kanomchan.core.common.service.LocationService;
import org.kanomchan.core.openid.bean.AuthRequestBean;
import org.kanomchan.core.openid.service.OpenIdClientService;
import org.kanomchan.core.security.authen.service.AuthenService;
import org.kanomchan.core.security.authen.service.LoginSSOService;
import org.kanomchan.core.security.authen.service.LoginService;
import org.kanomchan.core.security.authorize.service.UserAuthorizeService;
import org.kanomchan.core.security.authorize.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class LoginServiceImpl implements LoginSSOService {

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

	@Override
	public ServiceResult<AuthRequestBean> startSSO() throws NonRollBackException, RollBackException {
		return  openIdClientService.handleAuthorizationRequest(configService.get("SSO_REDIRECTURI"), configService.get("SSO_IDENTIFIER"));
	}

	@Override
	public ServiceResult<AuthRequestBean> startSSO(String identifier) throws NonRollBackException, RollBackException {
		return  openIdClientService.handleAuthorizationRequest(configService.get("SSO_REDIRECTURI"), identifier);
	}

	@Override
	public ServiceResult<AuthRequestBean> startSSO(String identifier, String redirectUri) throws NonRollBackException, RollBackException {
		return  openIdClientService.handleAuthorizationRequest(redirectUri, identifier);
	}

	@Override
	public ServiceResult<AuthRequestBean> handleAuthorizationRequest(String redirectUri, String identifier) throws NonRollBackException, RollBackException {
		return openIdClientService.handleAuthorizationRequest(redirectUri, identifier);
	}
}
