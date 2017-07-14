package org.kanomchan.core.common.service;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.bean.UserFacebook;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;

 

public interface SocialNetworkService {
	public ServiceResult<UserFacebook> getProfileFacebook(String urlGetAccess,String access_token,String expiry) throws RollBackException,NonRollBackException;
	public ServiceResult<UserBean> checkUser(UserFacebook userFacebook) throws RollBackException,NonRollBackException;
}
