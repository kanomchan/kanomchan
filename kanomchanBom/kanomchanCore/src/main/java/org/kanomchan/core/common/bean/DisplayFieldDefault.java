package org.kanomchan.core.common.bean;

import java.io.Serializable;

public class DisplayFieldDefault implements DisplayField,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3826169993152618713L;
	private Long idCountry;
	private String page;
	private String isDisplay;
	
	@Override
	public Long getIdCountry() {
		return idCountry;
	}

	@Override
	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}

	@Override
	public String getPage() {
		return page;
	}

	@Override
	public void setPage(String page) {
		this.page = page;
	}

	@Override
	public String getIsDisplay() {
		return isDisplay;
	}

	@Override
	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}


	
}
