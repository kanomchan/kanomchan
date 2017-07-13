package org.kanomchan.core.security.authen.service;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.bean.UserSocialNetworkBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;

public interface AuthenWithSocialNetworkService {
	/**login with email for social network :tong**/
	//public ServiceResult<UserBean> loginWithFacebook(String email, String socialprofileid) throws NonRollBackException, RollBackException;
	
	public UserSocialNetworkBean checkUserSocialNetwork(UserSocialNetworkBean userSocial) throws NonRollBackException, RollBackException;
	
	public ServiceResult<UserBean> loginWithFacebook(UserSocialNetworkBean userSocial) throws NonRollBackException, RollBackException;
	
	
}
