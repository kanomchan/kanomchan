package org.kanomchan.core.common.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.kanomchan.core.common.bean.FieldValidatorBean;
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
	
	private Map<String, Map<String, List<FieldValidatorBean>>> pageFieldValidatorBeans;
	private Map<String, List<FieldValidatorBean>> pageValidators;
	private Map<String, String> actionInputResult;
	
	@Override
	@PostConstruct
	public synchronized void initConfig(){
		config = configDao.getConfigMap();
		pageFieldValidatorBeans = configDao.getPageFieldValidators();
		pageValidators = configDao.getPageValidators();
		actionInputResult = configDao.getActionInputResult();
	}
	
	@Override
	public void refreshConfig(){
		config = configDao.getConfigMap();
		config = configDao.getConfigMap();
		pageFieldValidatorBeans = configDao.getPageFieldValidators();
		pageValidators = configDao.getPageValidators();
		actionInputResult = configDao.getActionInputResult();
	}
	
	@Override
	public String get(String key) {
		return config.get(key);
	}

	@Override
	public Map<String, List<FieldValidatorBean>> getFieldValidators(String page) {
		return pageFieldValidatorBeans.get(page);
	}
	@Override
	public List<FieldValidatorBean> getPageValidators(String page) {
		return pageValidators.get(page);
	}

	@Override
	public String getInputResultName(String namespace, String name) {
		return actionInputResult.get((namespace==null?"":namespace)+"/"+(name==null?"":name));
	}
	
	

}
