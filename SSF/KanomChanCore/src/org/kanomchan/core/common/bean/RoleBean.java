package org.kanomchan.core.common.bean;

public interface RoleBean {
	public Long getId();
	public void setId(Long id);
	
	public String getRoleName();
	public void setRoleName(String userName);
	
	public String getDescription();
	public void setDescription(String description);
	
	public String getStatus();
	public void setStatus(String status);
}
