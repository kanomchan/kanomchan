package org.kanomchan.core.common.bean;

import org.kanomchan.core.common.constant.MessageCode;

public interface Message {

	
	public String getMessageCode();
	public void setMessageCode(String messageCode);
	public void setMessageCode(MessageCode messageCode);
	public String getMessageLang();
	public String getDisplayText();
	public String getMessageDesc();
	public String getMessageType();
	public String getMessageTypeCss();
	public String getSolution();
	
	
}
