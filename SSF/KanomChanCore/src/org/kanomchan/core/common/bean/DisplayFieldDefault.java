package org.kanomchan.core.common.bean;

public class DisplayFieldDefault implements DisplayField {

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
