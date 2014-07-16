package org.kanomchan.core.security.authorize.service;

import java.util.List;

import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.security.authorize.bean.MenuBean;

public interface UserNavigationService {
	public ServiceResult<List<MenuBean>> generateNavigationList(String namespace, String actionName)throws NonRollBackException , RollBackException;

	public void refresh();
}
