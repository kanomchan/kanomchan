package org.kanomchan.core.common.bean;

public class LocationBean {
	private String lang;
	private Long zone;
	private Long country;
	private Long region;
	private Long province;
	private Long city;
	private Long county;
	private Long postal;
	private Long language;
	private Long station;
	private Long currency;
	
	
	public Long getZone() {
		return zone;
	}
	public void setZone(Long zone) {
		this.zone = zone;
	}
	public Long getCountry() {
		return country;
	}
	public void setCountry(Long country) {
		this.country = country;
	}
	public Long getRegion() {
		return region;
	}
	public void setRegion(Long region) {
		this.region = region;
	}
	public Long getProvince() {
		return province;
	}
	public void setProvince(Long province) {
		this.province = province;
	}
	public Long getCity() {
		return city;
	}
	public void setCity(Long city) {
		this.city = city;
	}
	public Long getCounty() {
		return county;
	}
	public void setCounty(Long county) {
		this.county = county;
	}
	public Long getPostal() {
		return postal;
	}
	public void setPostal(Long postal) {
		this.postal = postal;
	}
	public Long getLanguage() {
		return language;
	}
	public void setLanguage(Long language) {
		this.language = language;
	}
	public Long getStation() {
		return station;
	}
	public void setStation(Long station) {
		this.station = station;
	}
	public Long getCurrency() {
		return currency;
	}
	public void setCurrency(Long currency) {
		this.currency = currency;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public void setLanguage(String nString) {
		if (nString != null)
			try {
				this.language = Long.parseLong(nString);
			} catch (Exception e) {
			}
	}
	public void setCity(String nString) {
		if (nString != null)
			try {
				this.city = Long.parseLong(nString);
			} catch (Exception e) {
			}
	}
	public void setCountry(String nString) {
		if (nString != null)
			try {
				this.country = Long.parseLong(nString);
			} catch (Exception e) {
			}
	}
	public void setCounty(String nString) {
		if (nString != null)
			try {
				this.county = Long.parseLong(nString);
			} catch (Exception e) {
			}
	}
	public void setCurrency(String nString) {
		if (nString != null)
			try {
				this.currency = Long.parseLong(nString);
			} catch (Exception e) {
			}
	}
	public void setPostal(String nString) {
		if (nString != null)
			try {
				this.postal = Long.parseLong(nString);
			} catch (Exception e) {
			}
	}
	public void setProvince(String nString) {
		if (nString != null)
			try {
				this.province = Long.parseLong(nString);
			} catch (Exception e) {
			}
	}
	public void setRegion(String nString) {
		if (nString != null)
			try {
				this.region = Long.parseLong(nString);
			} catch (Exception e) {
			}
	}
	public void setStation(String nString) {
		if (nString != null)
			try {
				this.station = Long.parseLong(nString);
			} catch (Exception e) {
			}
	}
	public void setZone(String nString) {
		if (nString != null)
			try {
				this.zone = Long.parseLong(nString);
			} catch (Exception e) {
			}
	}

	
	
}
