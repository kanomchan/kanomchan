package org.kanomchan.core.common.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.kanomchan.core.common.constant.MessageCode;

public class MessageDefault implements Message ,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3760566974597580046L;
	/**
	 * 
	 */
	private String messageCode;
	private String messageLang;
	private String displayText;
	private String messageDesc;
	private String messageType;
	private String solution;
	private Map<String, String>  maps = new HashMap<String, String>();
	public MessageDefault() {
		maps.put("SUCCESS", "alert-success");
		maps.put("INFO", "alert-info");
		maps.put("WARNING", "alert-warning");
		maps.put("TECH_ERROR", "alert-danger");
		maps.put("PRO_ERROR", "alert-danger");
	}
	
	public String getMessageCode() {
		maps.put("", "");
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public String getMessageLang() {
		return messageLang;
	}
	public void setMessageLang(String messageLang) {
		this.messageLang = messageLang;
	}
	public String getDisplayText() {
		return displayText;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	public String getMessageDesc() {
		return messageDesc;
	}
	public void setMessageDesc(String messageDesc) {
		this.messageDesc = messageDesc;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	@Override
	public void setMessageCode(MessageCode messageCode) {
		this.messageCode = messageCode.getCode();
	}
	@Override
	public String getMessageTypeCss() {
		return messageType==null?"":maps.get(messageType.toUpperCase());
	}
	
}
