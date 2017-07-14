package org.kanomchan.core.common.bean;

import java.io.Serializable;

public class ConfigByCountry  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8433123814451017813L;
	private String key;
	private String value;
	private Long idCountry;
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
	public Long getIdCountry() {
		return idCountry;
	}
	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}
}
