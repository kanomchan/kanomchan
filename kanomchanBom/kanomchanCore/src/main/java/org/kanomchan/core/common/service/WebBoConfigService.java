package org.kanomchan.core.common.service;

import java.util.Map;

import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;


public interface WebBoConfigService {
	
	public Map<Object, Object> getWebBoConfigByGeographyPageName(Long idRegion, Long idCountry, Long idZone, Long idProvince, Long idCity)throws RollBackException ,NonRollBackException;
	
	public boolean getWebBoConfigMapList()throws RollBackException ,NonRollBackException;
	
	public boolean getIsDisplay(Long idRegion, Long idCountry, Long idZone, Long idProvince, Long idCity, String page, String field)throws RollBackException ,NonRollBackException;
}
