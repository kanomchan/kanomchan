package org.kanomchan.core.common.bean;

import java.math.BigDecimal;

public interface WebBoConfig {

	public Long getIdWebBoConfig();
	public void setIdWebBoConfig(Long idWebBoConfig);
	
	public Long getIdRegion();
	public void setIdRegion(Long idRegion);
	
	public Long getIdCountry();
	public void setIdCountry(Long idCountry);
	
	public Long getIdZone();
	public void setIdZone(Long idZone);
	
	public Long getIdProvince();
	public void setIdProvince(Long idProvince);
	
	public Long getIdCity();
	public void setIdCity(Long idCity);
	
	public String getPage();
	public void setPage(String page);
	
	public String getModule();
	public void setModule(String module);
	
	public String getField();
	public void setField(String field);
	
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
