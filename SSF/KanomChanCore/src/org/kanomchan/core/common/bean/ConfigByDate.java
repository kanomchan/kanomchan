package org.kanomchan.core.common.bean;

public class ConfigByDate {
	
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
