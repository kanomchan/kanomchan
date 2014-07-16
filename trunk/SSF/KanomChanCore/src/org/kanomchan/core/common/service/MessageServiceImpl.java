package org.kanomchan.core.common.service;

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

public class MessageServiceImpl implements MessageService {

	private ConfigDao configDao;
	@Autowired
	@Required
	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
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
	public void load() {
		configDao.getMessageMap();
	}

	@Override
	public void clearCache() {
		
	}

	@Override
	public Message getMessage(String messageCode, Locale locale) {
		if(locale == null){locale = CommonConstant.DEFAULT_LOCALE;}
		return getMessage(messageCode, locale.getLanguage().toUpperCase());
	}

	@Override
	public Message getMessage(String messageCode, String lang) {
		if(lang == null){lang = CommonConstant.DEFAULT_LOCALE.getLanguage().toUpperCase();}
		Map<String,Message> messageMap = configDao.getMessageMap();	
		Message message = messageMap.get(messageCode+"_"+lang);
		if(message == null){
			message = messageMap.get(messageCode+"_EN");
			if(message == null){
				MessageDefault message2  = new MessageDefault();
				message2.setMessageCode(messageCode);
				return message2;
			}
		}
		return message;
	}

	@Override
	public List<Message> getMessageList() {
		return getMessageList( null, null );
	}

	@Override
	public List<Message> getMessageList(Locale locale) {
		return getMessageList( null, locale );
	}

	@Override
	public List<Message> getMessageList(String messageType) {
		return getMessageList( messageType, null );
	}

	@Override
	public List<Message> getMessageList(String messageType, Locale locale) {
		if( locale == null ){
			locale = CommonConstant.DEFAULT_LOCALE;
		}
		return configDao.getMessageList(messageType, locale.getLanguage().toUpperCase());
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



}
