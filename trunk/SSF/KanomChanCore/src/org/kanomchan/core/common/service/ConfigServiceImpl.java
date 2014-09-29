package org.kanomchan.core.common.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.kanomchan.core.common.bean.FieldValidator;
import org.kanomchan.core.common.dao.ConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class ConfigServiceImpl implements ConfigService {

	private ConfigDao configDao;
	@Autowired
	@Required
	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
	}
	
	private Map<String, String> config;
	
	private Map<String, Map<String, List<FieldValidator>>> pageFieldValidators;
	private Map<String, List<FieldValidator>> pageValidators;
	private Map<String, String> actionInputResult;
	
	@Override
	@PostConstruct
	public synchronized void initConfig(){
		config = configDao.getConfigMap();
		pageFieldValidators = configDao.getPageFieldValidators();
		pageValidators = configDao.getPageValidators();
		actionInputResult = configDao.getActionInputResult();
	}
	
	@Override
	public void refreshConfig(){
		config = configDao.getConfigMap();
	}
	
	@Override
	public String get(String key) {
		return config.get(key);
	}

	@Override
	public Map<String, List<FieldValidator>> getFieldValidators(String page) {
		return pageFieldValidators.get(page);
	}
	@Override
	public List<FieldValidator> getPageValidators(String page) {
		return pageValidators.get(page);
	}

	@Override
	public String getInputResultName(String namespace, String name) {
		return actionInputResult.get((namespace==null?"":namespace)+(name==null?"":name));
	}

}
