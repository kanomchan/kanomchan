package org.kanomchan.core.common.bean;

import java.math.BigDecimal;

public interface WebBoConfig {

	public Long getIdWebBoConfig();
	public void setIdWebBoConfig(Long idWebBoConfig);
	
	public Long getIdWebBoConfigGeography();
	public void setIdWebBoConfigGeography(Long idWebBoConfigGeography);

	public Long getIdWebBoConfigPageModule();
	public void setIdWebBoConfigPageModule(Long idWebBoConfigPageModule);
	
	public String getIsDisplay();
	public void setIsDisplay(String isDisplay);
	
	public String getIsMandatory();
	public void setIsMandatory(String isMandatory);
	
	public String getIsMatch();
	public void setIsMatch(String isMatch);
	
	public String getIsWeight();
	public void setIsWeight(String isWeight);
	
	public BigDecimal getWeightPercent();
	public void setWeightPercent(BigDecimal weightPercent);
	
	public String getDescription();
	public void setDescription(String description);
}
