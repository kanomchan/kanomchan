package org.kanomchan.core.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class WebBoConfigDefault implements WebBoConfig ,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3319268189289575378L;
	private Long idWebBoConfig;
	private Long idWebBoConfigGeography;
	private Long idWebBoConfigPageModule;
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
	public Long getIdWebBoConfigGeography() {
		return idWebBoConfigGeography;
	}

	@Override
	public void setIdWebBoConfigGeography(Long idWebBoConfigGeography) {
		this.idWebBoConfigGeography = idWebBoConfigGeography;
	}
	
	@Override
	public Long getIdWebBoConfigPageModule() {
		return idWebBoConfigPageModule;
	}

	@Override
	public void setIdWebBoConfigPageModule(Long idWebBoConfigPageModule) {
		this.idWebBoConfigPageModule = idWebBoConfigPageModule;
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
