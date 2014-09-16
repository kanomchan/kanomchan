package org.kanomchan.core.common.dao;

import org.kanomchan.core.common.bean.LocationBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;

public interface LocationDao {

	public LocationBean getLocation(String countryCode,String countryName,String region,String city,String postalCode )throws NonRollBackException,RollBackException;

	public void refresh();

	public LocationBean getLocation(String userId)throws NonRollBackException,RollBackException;

}
