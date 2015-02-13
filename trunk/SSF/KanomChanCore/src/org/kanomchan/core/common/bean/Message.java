package org.kanomchan.core.common.bean;

import java.io.Serializable;

import org.kanomchan.core.common.constant.MessageCode;

public interface Message extends Serializable {

	
	public String getMessageCode();
	public void setMessageCode(String messageCode);
	public void setMessageCode(MessageCode messageCode);
	public void setPara(String...para);
	public String[] getPara();
	public String getMessageLang();
	public String getDisplayText();
	public void setDisplayText(String displayText);
	public String getMessageDesc();
	public String getMessageType();
	public String getMessageTypeCss();
	public String getSolution();
	
	
}
