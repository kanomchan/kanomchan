package org.kanomchan.core.common.dao;

import java.util.List;
import java.util.Map;

import org.kanomchan.core.common.bean.Label;
import org.kanomchan.core.common.bean.WebBoConfig;
import org.kanomchan.core.common.bean.WebBoConfigGeography;
import org.kanomchan.core.common.bean.WebBoConfigPageModule;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;

public interface WebBoConfigDao {
	public List<Label> getLabelList()throws RollBackException ,NonRollBackException;
	public Map<String,List<Label>> getLabelMap()throws RollBackException ,NonRollBackException;
	public Map<String, Map<String, String>> getLabelStrMap()throws RollBackException ,NonRollBackException;
	
	public boolean getWebBoConfigDefault()throws RollBackException ,NonRollBackException;
	
	public List<WebBoConfigGeography> getWebBoConfigGeographyList()throws RollBackException ,NonRollBackException;
	public List<WebBoConfigPageModule> getWebBoConfigPageModuleList()throws RollBackException ,NonRollBackException;
	public List<WebBoConfig> getWebBoConfigList()throws RollBackException ,NonRollBackException;
	
	public Map<Long, List<WebBoConfigGeography>> getWebBoConfigGeographyMap()throws RollBackException ,NonRollBackException;
	public Map<Long, List<WebBoConfigGeography>> getWebBoConfigGeographyRegionMap()throws RollBackException ,NonRollBackException;
	
	public Map<Long, Long> getLongMap()throws RollBackException ,NonRollBackException;
	public Map<Object, Object> objectMap()throws RollBackException ,NonRollBackException;
	public Map<Object,List<Object>> getObjectMap()throws RollBackException ,NonRollBackException;
		
	public WebBoConfigGeography getWebBoConfigGeography(Long idRegion, Long idCountry, Long idZone, Long idProvince, Long idCity)throws RollBackException ,NonRollBackException;
	public WebBoConfigPageModule getWebBoConfigPageModule(String page, String field)throws RollBackException ,NonRollBackException;
	public WebBoConfig getWebBoConfig(Long idWebBoConfigGeography, Long idWebBoConfigPageModule)throws RollBackException ,NonRollBackException;
	
	
	
//	public Map<Object, Object> getWebBoConfigObjectMap();
//	public Map<Object, Object> getDisplayObjectMap();
//	public Map<Object, Object> getMandatoryObjectMap();
//	public Map<Object, Object> getMatchObjectMap();
//	public Map<Object, Object> getWeightObjectMap();
//	public Map<Object, Object> getWeightPercentObjectMap();
//	public Map<Object, Map<Object, Object>> getWebBoConfigStrMap();

}
