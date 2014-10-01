package org.kanomchan.core.common.service;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kanomchan.core.common.bean.MessageDefault;
import org.kanomchan.core.common.bean.Message;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.constant.MessageCode;
import org.kanomchan.core.common.context.CurrentThread;
import org.kanomchan.core.common.dao.ConfigDao;
import org.kanomchan.core.common.processhandler.ProcessContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

public class MessageServiceImpl implements MessageService {

	private ConfigDao configDao;
	@Autowired
	@Required
	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
	}
	


	@Override
	public void load() {
		configDao.getMessageMap();
	}

	@Override
	public void clearCache() {
		
	}



	@Override
	public List<Message> getMessageList() {
		return getMessageList( null, null );
	}


	@Override
	public List<Message> getMessageList(String lang) {
		return getMessageList(lang,null);
	}
	
	@Override
	public List<Message> getMessageList(String lang, String messageType) {
		return configDao.getMessageList(messageType, lang);
	}
	
	
	@Override
	public Message getMessage(String messageCode, Locale locale) {
		if(locale == null){locale = CommonConstant.DEFAULT_LOCALE;}
		return getMessage(messageCode, locale.getISO3Language().toUpperCase());
	}

	@Override
	public Message getMessage(String messageCode, String lang) {
		if(lang == null){lang = CommonConstant.DEFAULT_LOCALE.getISO3Language().toUpperCase();}
		Map<String,Message> messageMap = configDao.getMessageMap();	
		Message message = messageMap.get(messageCode+"_"+lang);
		if(message == null){
			message = messageMap.get(messageCode+"_"+CommonConstant.DEFAULT_LOCALE.getISO3Language().toUpperCase());
			if(message == null){
				MessageDefault message2  = new MessageDefault();
				message2.setMessageCode(messageCode);
				return message2;
			}
		}
		return message;
	}
	@Override
	public Message getMessage(String messageCode) {
		ProcessContext processContext = CurrentThread.getProcessContext();
		Locale locale = processContext.getLocale();
		if(locale==null){
			locale = CommonConstant.DEFAULT_LOCALE;
		}
		return getMessage(messageCode,locale);
	}

	@Override
	public Message getMessage(MessageCode messageCode) {
		return getMessage(messageCode.getCode());
	}

	@Override
	public Message getMessage(MessageCode messageCode, Locale locale) {
		return getMessage(messageCode.getCode(),locale);
	}

	@Override
	public Message getMessage(MessageCode messageCode, String lang) {
		return getMessage(messageCode.getCode(),lang);
	}

	
	public String getMessageListToJson(){
		
//		Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC).create();
		return"";
	}



	@Override
	public Map<String, Message> getMessageMap(String lang) {
		List<Message> list = getMessageList(lang);
		HashMap<String, Message> map = new HashMap<String, Message>();
		if(list!=null){
			for (Message message : list) {
				map.put(message.getMessageCode(), message);
			}
		}
		
		return map;
	}


}
