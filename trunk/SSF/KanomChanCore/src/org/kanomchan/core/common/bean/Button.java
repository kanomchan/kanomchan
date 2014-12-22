package org.kanomchan.core.common.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Button implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9126382511405110455L;
	private String action;
	private String namespace;
	private String url;
	private String name;
	private Map<String, Object> para = new HashMap<String, Object>();
//	private String paramValue;
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
	
	public void addPara(String name,Object value){
		para.put(name, value);
	}
//	public String getParamName() {
//		return paramName;
//	}
//	public void setParamName(String paramName) {
//		this.paramName = paramName;
//	}
//	public String getParamValue() {
//		return paramValue;
//	}
//	public void setParamValue(String paramValue) {
//		this.paramValue = paramValue;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Map<String, Object> getPara() {
		return para;
	}
	public void setPara(Map<String, Object> para) {
		this.para = para;
	}
	@Override
	public String toString() {
		return "Button [action=" + action + ", namespace=" + namespace
				+ ", url=" + url + ", name=" + name + "]";
	}
}
