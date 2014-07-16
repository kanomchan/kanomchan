package org.kanomchan.core.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.kanomchan.core.common.bean.Config;
import org.kanomchan.core.common.bean.ConfigDefault;
import org.kanomchan.core.common.bean.MessageDefault;
import org.kanomchan.core.common.bean.Label;
import org.kanomchan.core.common.bean.LabelDefault;
import org.kanomchan.core.common.bean.Message;
import org.springframework.jdbc.core.RowMapper;

public class ConfigJdbcImpl extends JdbcCommonDaoImpl implements ConfigDao {
	
	private static final Logger logger = Logger.getLogger(ConfigJdbcImpl.class);
	
	private static final String  SQL_QUERY_CONFIG = "select config_key, config_value from com_mst_config";
	@Override
	public Map<String, String> getConfigMap() {
		Map<String, String> configMap = new ConcurrentHashMap<String, String>();
		
		List<Config> configList = nativeQuery(SQL_QUERY_CONFIG, CONFIG_MAPPER);//(SQL_QUERY_CONFIG, new configMapper());
		
		for (Config systemConfig : configList) {
			if (logger.isInfoEnabled()) {
				logger.info("Config loading... " + systemConfig);
			}			
			configMap.put(systemConfig.getKey(), systemConfig.getValue());
		}
		return configMap;
	}
	
	private static final ConfigMapper<Config> CONFIG_MAPPER = new ConfigMapper<Config>();
	public static final class ConfigMapper<T extends Config> implements RowMapper<Config> {
	    public Config mapRow(ResultSet rs, int num)throws SQLException {
	    	ConfigDefault configDefault = new ConfigDefault();
	    	configDefault.setKey( rs.getString("config_key"));
	    	configDefault.setValue( rs.getString("config_value"));
	        return configDefault;
	    }
    }

	public static final String SQL_QUERY_MESSAGE = 
			" select message_code, message_lang, display_text, message_desc, " +
			" message_type, solution " +
			" from com_mst_message ";
	@Override
	public Map<String, Message> getMessageMap() {
		Map<String, Message> messageMap = new ConcurrentHashMap<String, Message>();
		
		List<Message> messageList = nativeQuery(SQL_QUERY_MESSAGE, MESSAGE_MAPPER);//(SQL_QUERY_CONFIG, new configMapper());
		
		for (Message message : messageList) {
			if (logger.isInfoEnabled()) {
				logger.info("Message loading... " + message);
			}			
			messageMap.put(message.getMessageCode()+"_"+message.getMessageLang(), message);
		}
		return messageMap;
	}
	
	private static final MessageMapper<Message> MESSAGE_MAPPER = new MessageMapper<Message>();
	public static final class MessageMapper<T extends Message> implements RowMapper<Message> {
	    public Message mapRow(ResultSet rs, int num)throws SQLException {
	    	MessageDefault message = new MessageDefault(); 
	    	message.setMessageCode(rs.getString("message_code"));
	    	message.setMessageLang(rs.getString("message_lang"));
	    	message.setDisplayText(rs.getString("display_text"));
	    	message.setMessageDesc(rs.getString("message_desc"));
	    	message.setMessageType(rs.getString("message_type"));
	    	message.setSolution(rs.getString("solution"));
	        return message;
	    }
    }
	
	private static final LabelMapper<Label> LABEL_MAPPER = new LabelMapper<Label>();
	public static final class LabelMapper<T extends Label> implements RowMapper<Label> {
	    public Label mapRow(ResultSet rs, int num)throws SQLException {
	    	LabelDefault label = new LabelDefault(); 
	    	label.setLabel(rs.getString("label"));
	    	label.setLanguage(rs.getString("language"));
	    	label.setPage(rs.getString("page"));
	    	label.setDisplayText(rs.getString("display_text"));
	        return label;
	    }
    }
	@Override
	public List<Message> getMessageList(String messageType, String messageLang) {
		
		StringBuilder whereClause = new StringBuilder();
		Map<String,Object> params = new ConcurrentHashMap<String, Object>();
		
		whereClause.append(" WHERE 1 = 1 ");
		if( messageType != null && messageType.length() > 0 ){
			whereClause.append(" AND MESSAGE_TYPE LIKE :messageType ");
			params.put("messageType", "%"+messageType+"%");
		}
		if( messageLang != null && messageLang.length() > 0 ){
			whereClause.append(" AND MESSAGE_LANG = :messageLang ");
			params.put("messageLang", messageLang);
		}
		return nativeQuery(SQL_QUERY_MESSAGE+whereClause.toString(), MESSAGE_MAPPER, params);
	}

	
	public static final String SQL_QUERY_LABEL = 
			" select label, page, display_text, language " +
			" from com_mst_label ";
	
	@Override
	public List<Label> getLabelList() {
		
		List<Label> labelList = nativeQuery(SQL_QUERY_LABEL, LABEL_MAPPER);//(SQL_QUERY_CONFIG, new configMapper());
		
		return labelList;
	}

	@Override
	public Map<String, List<Label>> getLabelMap() {
		Map<String, List<Label>> messageMap = new ConcurrentHashMap<String, List<Label>>();
		List<Label> labelList = nativeQuery(SQL_QUERY_LABEL, LABEL_MAPPER);//(SQL_QUERY_CONFIG, new configMapper());
		
		for (Label label : labelList) {
			List<Label> labels = messageMap.get(label.getLanguage());
			if(labels==null){
				labels = new ArrayList<Label>();
				
			}
			labels.add(label);
			messageMap.put(label.getLanguage(), labels);
		}
		return messageMap;
	}
	
	@Override
	public Map<String, Map<String, String>> getLabelStrMap() {
		
		Map<String, Map<String, String>> messageStringMap = new ConcurrentHashMap<String, Map<String, String>>();

		Map<String, List<Label>> messageMap   = getLabelMap();
		Map<String, String> labelMapDefault = new ConcurrentHashMap<String, String>();
		for (Label label : messageMap.get(Label.DEFAULT_LANG)) {
			labelMapDefault.put(label.getPage()+"_"+label.getLabel(), label.getDisplayText());
		}
		Set<String> langSet = messageMap.keySet();
		for (String lang : langSet) {
			if(lang != Label.DEFAULT_LANG){
				Map<String, String> labelMap = new ConcurrentHashMap<String, String>(labelMapDefault);
				for (Label label : messageMap.get(lang)) {
					labelMap.put(label.getPage()+"_"+label.getLabel(), label.getDisplayText());
				}
				messageStringMap.put(lang, labelMap );
			}
			
		}
		return messageStringMap;
	}
	
	@Override
	public void clearConfigCache() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearMessageCache() {
		// TODO Auto-generated method stub
		
	}
}
