package org.kanomchan.core.common.bean;

import java.io.Serializable;

public class LabelDefault implements Label,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5598389849410431495L;
	private String language;
	private String page;
	private String label;
	private String displayText;
	private String status;
	
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDisplayText() {
		return displayText;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
