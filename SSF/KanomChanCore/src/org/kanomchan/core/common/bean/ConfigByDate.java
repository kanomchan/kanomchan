package org.kanomchan.core.common.bean;

import java.io.Serializable;

public class ConfigByDate implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5708878230844329013L;
	private String key;
	private String value;
	private Long idRefData;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getIdRefData() {
		return idRefData;
	}
	public void setIdRefData(Long idRefData) {
		this.idRefData = idRefData;
	}
}
