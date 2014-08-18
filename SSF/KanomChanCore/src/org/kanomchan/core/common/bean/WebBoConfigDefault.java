package org.kanomchan.core.common.bean;

import java.math.BigDecimal;

public class WebBoConfigDefault implements WebBoConfig {

	private Long idWebBoConfig;
	private Long idRegion;
	private Long idCountry;
	private Long idZone;
	private Long idProvince;
	private Long idCity;
	private String page;
	private String module;
	private String field;
	private String isDisplay;
	private String isMandatory;
	private String isMatch;
	private String isWeight;
	private BigDecimal WeightPercent;
	private String description;
	
	@Override
	public Long getIdWebBoConfig() {
		return idWebBoConfig;
	}

	@Override
	public void setIdWebBoConfig(Long idWebBoConfig) {
		this.idWebBoConfig = idWebBoConfig;
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

	@Override
	public String getPage() {
		return page;
	}

	@Override
	public void setPage(String page) {
		this.page = page;
	}

	@Override
	public String getModule() {
		return module;
	}

	@Override
	public void setModule(String module) {
		this.module = module;
	}

	@Override
	public String getField() {
		return field;
	}

	@Override
	public void setField(String field) {
		this.field = field;
	}

	@Override
	public String getIsDisplay() {
		return isDisplay;
	}

	@Override
	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	@Override
	public String getIsMandatory() {
		return isMandatory;
	}

	@Override
	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
	}

	@Override
	public String getIsMatch() {
		return isMatch;
	}

	@Override
	public void setIsMatch(String isMatch) {
		this.isMatch = isMatch;
	}

	@Override
	public String getIsWeight() {
		return isWeight;
	}

	@Override
	public void setIsWeight(String isWeight) {
		this.isWeight = isWeight;
	}

	@Override
	public BigDecimal getWeightPercent() {
		return WeightPercent;
	}

	@Override
	public void setWeightPercent(BigDecimal weightPercent) {
		this.WeightPercent = weightPercent;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

}
