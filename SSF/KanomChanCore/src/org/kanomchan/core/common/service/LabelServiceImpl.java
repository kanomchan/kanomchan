package org.kanomchan.core.common.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.kanomchan.core.common.dao.ConfigDao;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

public class LabelServiceImpl implements LabelService  {

	private ConfigDao configDao;
	
	@Autowired
	@Required
	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
	}
	
	
	private Map<String, Map<String, String>> labelMap = new HashMap<String, Map<String,String>>();
	@Override
	@Cacheable(cacheName = "labelService.getLabel")
	public Map<String, String> getLabel(String lang) {
		Map<String, String> map = labelMap.get(lang);
		if(map==null)
			map = labelMap.get("ENG");
		return map;
	}
	
	@Override
	@Cacheable(cacheName = "labelService.getLabelByPage")
	public Map<String, String> getLabelByPage(String lang, String page) {
		Map<String, String> labels = new HashMap<String, String>();
		Map<String, String> mapLang = labelMap.get(lang);
		if(mapLang == null)
			mapLang = labelMap.get("ENG");
		for (Entry<String, String> map : mapLang.entrySet()) {
			if(map.getKey().startsWith(page)){
				labels.put(map.getKey(), map.getValue());
			}
		}
		return labels;
	}
	
	@Override
	@TriggersRemove(cacheName={"labelService.getLabel","labelService.getLabelByPage"},removeAll=true)
	public void refresh()throws RollBackException ,NonRollBackException {
		labelMap = configDao.getLabelStrMap();
	}

}
