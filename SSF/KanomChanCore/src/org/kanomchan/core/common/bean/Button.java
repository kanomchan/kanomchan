package org.kanomchan.core.common.bean;

import java.io.Serializable;

public class Button implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9126382511405110455L;
	private String action;
	private String namespace;
	private String url;
	private String name;
	private String paramName;
	private String paramValue;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Button [action=" + action + ", namespace=" + namespace
				+ ", url=" + url + ", name=" + name + ", paramName="
				+ paramName + ", paramValue=" + paramValue + "]";
	}
}
