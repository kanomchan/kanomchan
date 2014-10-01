package org.kanomchan.core.common.bean;

public class FieldValidatorDefault implements FieldValidator {
	
	private String page;
	private String field;
	private String type;
	private String parameter;
	private String message;
	private String messageKey;
	private String messageParameter;
	
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageParameter() {
		return messageParameter;
	}
	public void setMessageParameter(String messageParameter) {
		this.messageParameter = messageParameter;
	}
	public String getMessageKey() {
		return messageKey;
	}
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	
	
	
}
