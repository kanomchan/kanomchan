package org.kanomchan.core.common.service;

import java.util.List;
import java.util.Map;

import org.kanomchan.core.common.bean.FieldValidator;


public interface ConfigService {
	
	public String get(String key);

	public void refreshConfig();
	
	public Map<String, List<FieldValidator>> getFieldValidators(String page);

	public void initConfig();

	public List<FieldValidator> getPageValidators(String page);

	public String getInputResultName(String namespace, String name);
}
