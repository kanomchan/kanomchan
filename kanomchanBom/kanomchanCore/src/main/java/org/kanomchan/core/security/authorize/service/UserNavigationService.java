package org.kanomchan.core.security.authorize.service;

import java.util.List;

import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.security.authorize.bean.MenuBean;
import org.kanomchan.core.security.authorize.io.NavigationBean;

public interface UserNavigationService {
	public ServiceResult<NavigationBean> generateNavigationList(String namespace, String actionName, String url)throws NonRollBackException , RollBackException;

	public void refresh();
}
