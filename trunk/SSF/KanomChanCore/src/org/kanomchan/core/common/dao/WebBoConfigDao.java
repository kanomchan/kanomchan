package org.kanomchan.core.common.dao;

import java.util.List;
import java.util.Map;

import org.kanomchan.core.common.bean.Label;
import org.kanomchan.core.common.bean.WebBoConfig;
import org.kanomchan.core.common.bean.WebBoConfigGeography;
import org.kanomchan.core.common.bean.WebBoConfigPageModule;

public interface WebBoConfigDao {
	public List<Label> getLabelList();
	public Map<String,List<Label>> getLabelMap();
	public Map<String, Map<String, String>> getLabelStrMap();
	
	public boolean getWebBoConfigDefault();
	
	public List<WebBoConfigGeography> getWebBoConfigGeographyList();
	public List<WebBoConfigPageModule> getWebBoConfigPageModuleList();
	public List<WebBoConfig> getWebBoConfigList();
	
	public Map<Long, List<WebBoConfigGeography>> getWebBoConfigGeographyMap();
	public Map<Long, List<WebBoConfigGeography>> getWebBoConfigGeographyRegionMap();
	
	public Map<Long, Long> getLongMap();
	public Map<Object, Object> objectMap();
	public Map<Object,List<Object>> getObjectMap();
		
	public WebBoConfigGeography getWebBoConfigGeography(Long idRegion, Long idCountry, Long idZone, Long idProvince, Long idCity);
	public WebBoConfigPageModule getWebBoConfigPageModule(String page, String field);
	public WebBoConfig getWebBoConfig(Long idWebBoConfigGeography, Long idWebBoConfigPageModule);
	
	
	
//	public Map<Object, Object> getWebBoConfigObjectMap();
//	public Map<Object, Object> getDisplayObjectMap();
//	public Map<Object, Object> getMandatoryObjectMap();
//	public Map<Object, Object> getMatchObjectMap();
//	public Map<Object, Object> getWeightObjectMap();
//	public Map<Object, Object> getWeightPercentObjectMap();
//	public Map<Object, Map<Object, Object>> getWebBoConfigStrMap();

}
