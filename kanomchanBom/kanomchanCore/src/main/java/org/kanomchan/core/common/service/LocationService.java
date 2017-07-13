package org.kanomchan.core.common.service;

import org.kanomchan.core.common.bean.LocationBean;
import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;

public interface LocationService {

	public ServiceResult<LocationBean> getLocation(String ipadd)throws RollBackException,NonRollBackException;

	public void refresh();

	public ServiceResult<LocationBean> getLocationByUserBean(UserBean userBean)throws RollBackException,NonRollBackException;
}
