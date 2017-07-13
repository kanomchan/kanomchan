package org.kanomchan.core.common.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kanomchan.core.common.bean.Message;
import org.kanomchan.core.common.constant.MessageCode;

public interface MessageService {

	public void load();
	public void clearCache();
	public Message getMessage( MessageCode messageCode ,String[] para  );
	public Message getMessage( MessageCode messageCode, Locale locale ,String[] para);
	public Message getMessage( MessageCode messageCode, String lang ,String[] para);
	public Message getMessage( String messageCode , String[] para );
	public Message getMessage( String messageCode, Locale locale ,String[] para);
	public Message getMessage( String messageCode, String lang ,String[] para);
	

	public List<Message> getMessageList();
	public List<Message> getMessageList(String lang);
	public Map<String,Message> getMessageMap(String lang);
	public List<Message> getMessageList(String lang,String messageType);
}
