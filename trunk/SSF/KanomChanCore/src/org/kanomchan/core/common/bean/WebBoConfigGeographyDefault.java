package org.kanomchan.core.common.bean;

public class WebBoConfigGeographyDefault implements WebBoConfigGeography {

	private Long idWebBoConfigGeography;
	private Long idRegion;
	private Long idCountry;
	private Long idZone;
	private Long idProvince;
	private Long idCity;
	
	@Override
	public Long getIdWebBoConfigGeography() {
		return idWebBoConfigGeography;
	}

	@Override
	public void setIdWebBoConfigGeography(Long idWebBoConfigGeography) {
		this.idWebBoConfigGeography = idWebBoConfigGeography;
	}

	@Override
	public Long getIdRegion() {
		return idRegion;
	}

	@Override
	public void setIdRegion(Long idRegion) {
		this.idRegion = idRegion;
	}

	@Override
	public Long getIdCountry() {
		return idCountry;
	}

	@Override
	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;		
	}

	@Override
	public Long getIdZone() {
		return idZone;
	}

	@Override
	public void setIdZone(Long idZone) {
		this.idZone = idZone;
	}

	@Override
	public Long getIdProvince() {
		return idProvince;
	}

	@Override
	public void setIdProvince(Long idProvince) {
		this.idProvince = idProvince;
	}

	@Override
	public Long getIdCity() {
		return idCity;
	}

	@Override
	public void setIdCity(Long idCity) {
		this.idCity = idCity;
	}

}
