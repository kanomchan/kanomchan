package org.kanomchan.core.common.service;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.kanomchan.core.common.bean.LocationBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

public class LocationServiceImpl implements LocationService {

	private LookupService lookupService;


	@Override
	public ServiceResult<LocationBean> getLocation(String ipAddress) throws RollBackException,NonRollBackException {
//		if(lookupService==null)
//			init();
		Location location = lookupService.getLocation(ipAddress);
		if(location == null){
			location = lookupService.getLocationV6(ipAddress);
		}
		LocationBean locationBean = new LocationBean();
		return new ServiceResult<LocationBean>(locationBean );
	}

	@PostConstruct
	public void init() {
		try {
			lookupService = new LookupService("/GeoLiteCity.dat",LookupService.GEOIP_MEMORY_CACHE );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void destroy() {
		lookupService.close();
	}
}
