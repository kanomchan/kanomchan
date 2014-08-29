package org.kanomchan.core.common.service;

import java.util.Map;


public interface WebBoConfigService {
	public Map<Object, Object> getWebBoConfigByGeographyPageName(Long idRegion, Long idCountry, Long idZone, Long idProvince, Long idCity);
	public boolean getWebBoConfigMapList();
	public boolean getIsDisplay(Long idRegion, Long idCountry, Long idZone, Long idProvince, Long idCity, String page, String field);
}
