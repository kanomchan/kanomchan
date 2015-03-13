package org.kanomchan.core.openid.service;

import java.util.Map;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.openid.bean.AuthRequestBean;
import org.mitre.openid.connect.client.model.IssuerServiceResponse;
import org.mitre.openid.connect.config.ServerConfiguration;

public interface OpenIdClientService {

	public ServiceResult<UserBean> loadUserBean(String subject, String issuer,ServerConfiguration serverConfiguration,String idTokenValue, String accessTokenValue, String refreshTokenValue) throws RollBackException,NonRollBackException;
	public ServiceResult<AuthRequestBean> handleAuthorizationRequest(String redirectUri, String identifier) throws RollBackException,NonRollBackException;
//	public ServiceResult<IssuerServiceResponse> getIssuer(String identifier) throws RollBackException,NonRollBackException;
	public ServiceResult<UserBean> handleAuthorizationCodeResponse(String authorizationCode,String state,Map<String, Object> session) throws RollBackException,NonRollBackException;
}

