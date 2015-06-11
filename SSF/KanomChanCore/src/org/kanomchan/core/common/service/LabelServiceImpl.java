package org.kanomchan.core.common.service;

import java.util.HashMap;
import java.util.Map;

import org.kanomchan.core.common.dao.ConfigDao;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class LabelServiceImpl implements LabelService  {

	private ConfigDao configDao;
	
	@Autowired
	@Required
	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
	}
	
	
	private Map<String, Map<String, String>> labelMap = new HashMap<String, Map<String,String>>();
	@Override
	public Map<String, String> getLabel(String lang) {
		return labelMap.get(lang);
	}
	@Override
	public void refresh()throws RollBackException ,NonRollBackException {
		labelMap = configDao.getLabelStrMap();
	}

}
