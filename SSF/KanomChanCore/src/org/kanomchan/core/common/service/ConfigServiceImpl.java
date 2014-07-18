package org.kanomchan.core.common.service;

import java.util.Map;

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
	
	@Override
	public synchronized void initConfig(){
		config = configDao.getConfigMap();
	}
	
	@Override
	public void refreshConfig(){
		config = configDao.getConfigMap();
	}
	
	@Override
	public String get(String key) {
		return config.get(key);
	}

}
