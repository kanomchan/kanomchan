package org.kanomchan.core.common.dao;

import java.util.List;
import java.util.Map;

import org.kanomchan.core.common.bean.DisplayField;
import org.kanomchan.core.common.bean.FieldValidator;
import org.kanomchan.core.common.bean.Label;
import org.kanomchan.core.common.bean.Message;

public interface ConfigDao {
	public Map<String,String> getConfigMap();
	
	
	public Map<String,Message> getMessageMap();
	public List<Message> getMessageList( String messageType, String messageLang );
	
	public List<Label> getLabelList();
	public Map<String,List<Label>> getLabelMap();
	public Map<String, Map<String, String>> getLabelStrMap();
	
	public void clearConfigCache();
	public void clearMessageCache();


	public Map<String, Map<String, List<FieldValidator>>> getPageFieldValidators();


	public Map<String, List<FieldValidator>> getPageValidators();


	public Map<String, String> getActionInputResult();
}
