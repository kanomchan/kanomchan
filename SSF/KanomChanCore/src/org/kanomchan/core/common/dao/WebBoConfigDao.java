package org.kanomchan.core.common.dao;

import java.util.List;
import java.util.Map;

import org.kanomchan.core.common.bean.Label;
import org.kanomchan.core.common.bean.WebBoConfig;

public interface WebBoConfigDao {
	public List<Label> getLabelList();
	public Map<String,List<Label>> getLabelMap();
	public Map<String, Map<String, String>> getLabelStrMap();
	
	public List<WebBoConfig> getWebBoConfigList();
	public Map<Object,List<WebBoConfig>> getWebBoConfigMap();
//	public Map<Object, Object> getWebBoConfigObjectMap();
	public Map<Object, Object> getDisplayObjectMap();
	public Map<Object, Object> getMandatoryObjectMap();
	public Map<Object, Object> getMatchObjectMap();
	public Map<Object, Object> getWeightObjectMap();
	public Map<Object, Object> getWeightPercentObjectMap();
//	public Map<Object, Map<Object, Object>> getWebBoConfigStrMap();
}
