package org.kanomchan.core.common.service;

import java.util.List;
import java.util.Locale;

import org.kanomchan.core.common.bean.Message;
import org.kanomchan.core.common.constant.MessageCode;

public interface MessageService {

	public void load();
	public void clearCache();
	public Message getMessage( MessageCode messageCode );
	public Message getMessage( MessageCode messageCode, Locale locale );
	public Message getMessage( MessageCode messageCode, String lang );
	public Message getMessage( String messageCode );
	public Message getMessage( String messageCode, Locale locale );
	public Message getMessage( String messageCode, String lang );
	

	public List<Message> getMessageList();
	public List<Message> getMessageList(Locale locale);
	public List<Message> getMessageList(String messageType);
	public List<Message> getMessageList(String messageType, Locale locale);
}
