package org.kanomchan.core.security.authen.service;

import java.util.Map;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.io.LoginIO;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.openid.bean.AuthRequestBean;

public interface LoginService {

	public ServiceResult<UserBean> performLogin(String username, String password)throws NonRollBackException,RollBackException;
	public ServiceResult<LoginIO> performLoginAndPutDataSession(String username, String password)throws NonRollBackException,RollBackException;
	public ServiceResult<LoginIO> performLoginAndPutDataSession(String authorizationCode,String state,Map<String, Object> session)throws NonRollBackException,RollBackException;
	public ServiceResult<AuthRequestBean> startSSO()throws NonRollBackException,RollBackException;
	public ServiceResult<AuthRequestBean> startSSO(String identifier)throws NonRollBackException,RollBackException;
	public ServiceResult<AuthRequestBean> startSSO(String identifier,String redirectUri)throws NonRollBackException,RollBackException;

}
