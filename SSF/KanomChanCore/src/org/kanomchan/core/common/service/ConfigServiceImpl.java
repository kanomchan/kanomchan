package org.kanomchan.core.common.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.kanomchan.core.common.bean.FieldValidatorBean;
import org.kanomchan.core.common.dao.ConfigDao;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ConfigServiceImpl implements ConfigService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConfigServiceImpl.class);

	private ConfigDao configDao;
	@Autowired
	@Required
	public void setConfigDao(ConfigDao configDao)throws RollBackException ,NonRollBackException {
		this.configDao = configDao;
	}
	
	private Map<String, String> config;
	
	private Map<String, Map<String, List<FieldValidatorBean>>> pageFieldValidatorBeans;
	private Map<String, List<FieldValidatorBean>> pageValidators;
	private Map<String, String> actionInputResult;
	
	@Override
	@PostConstruct
	public synchronized void initConfig(){
		try {
			config = configDao.getConfigMap();
		} catch (RollBackException | NonRollBackException e) {
			// TODO Auto-generated catch block
			logger.error("initConfig()", e);
		}
		try {
			pageFieldValidatorBeans = configDao.getPageFieldValidators();
		} catch (RollBackException | NonRollBackException e) {
			// TODO Auto-generated catch block
			logger.error("initConfig()", e);
		}
		try {
			pageValidators = configDao.getPageValidators();
		} catch (RollBackException | NonRollBackException e) {
			// TODO Auto-generated catch block
			logger.error("initConfig()", e);
		}
		try {
			actionInputResult = configDao.getActionInputResult();
		} catch (RollBackException | NonRollBackException e) {
			// TODO Auto-generated catch block
			logger.error("initConfig()", e);
		}
	}
	
	@Override
	public void refreshConfig()throws RollBackException ,NonRollBackException{
		config = configDao.getConfigMap();
		pageFieldValidatorBeans = configDao.getPageFieldValidators();
		pageValidators = configDao.getPageValidators();
		actionInputResult = configDao.getActionInputResult();
	}
	
	@Override
	public String get(String key) {
		try{
			String returnString = config.get(key);
			return returnString;
		}catch(Exception e){
			logger.error("get(String)", e);
		}
		return null;
	}

	@Override
	public Map<String, List<FieldValidatorBean>> getFieldValidators(String page)throws RollBackException ,NonRollBackException {
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
	
	@Override
	public boolean checkTable(String name) {
		String text   = config.get("NEEDLE_LIST");
		Gson gson = new GsonBuilder().create();
		Type typeOfSrc = new TypeToken<Set<String>>() {}.getType();
		Set<String> setKey = gson.fromJson(text, typeOfSrc );
		return setKey==null?false:setKey.contains(name);
	}

}
