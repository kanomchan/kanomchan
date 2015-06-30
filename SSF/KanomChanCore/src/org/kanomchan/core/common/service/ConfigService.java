package org.kanomchan.core.common.service;

import java.util.List;
import java.util.Map;

import org.kanomchan.core.common.bean.FieldValidatorBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;


public interface ConfigService {
	
	public String get(String key);

	public void refreshConfig()throws RollBackException ,NonRollBackException;
	
	public Map<String, List<FieldValidatorBean>> getFieldValidators(String page)throws RollBackException ,NonRollBackException;

	public void initConfig();

	public List<FieldValidatorBean> getPageValidators(String page);

	public String getInputResultName(String namespace, String name);
	
	public boolean checkTable(String name) throws RollBackException, NonRollBackException;
}
