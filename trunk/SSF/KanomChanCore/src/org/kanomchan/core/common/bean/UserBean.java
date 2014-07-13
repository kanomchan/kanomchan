package org.kanomchan.core.common.bean;

import java.util.Set;

public interface UserBean {
	public String getUserId();
	public void setUserId(String userId);
	
	public String getUserName();
	public void setUserName(String userName);
	
	public String getPassword();
	public void setPassword(String password);
	
	public String getFirstName();
	public void setFirstName(String firstName);
	
	public String getLastName();
	public void setLastName(String lastName);
	
	public String getName();
	
	public Set<String> getRoles();
	public void setRoles(Set<String> roles);
	
	public String getRole();
	public String getRoleName();
	public void setRole(String role);
	
	public Set<String> getPrivileges();
	public void setPrivileges(Set<String> Privileges);
	
	public String getStatus();
	public void setStatus(String status);
	public void setRoleName(String userRoleName);
}
