package org.kanomchan.core.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserBeanDefault implements UserBean ,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3718443802521098725L;
	private String userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private Set<String> roles;
	private Set<String> privileges;
	private String status;
//	private String roleName;
	private String userImagePath;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public Set<String> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<String> privileges) {
		this.privileges = privileges;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String getName() {
		return firstName + " "+lastName;
	}
	@Override
	public String getRole() {
		List<String> list = new ArrayList<String>(roles);
		return list.get(list.size()-1);
	}
	@Override
	public void setRole(String role) {
		if(roles==null)
			roles = new HashSet<String>();
		roles.add(role);
	}
	@Override
	public String getRoleName() {
		List<String> list = new ArrayList<String>(roles);
		return list.get(list.size()-1);
	}
	
	@Override
	public void setRoleName(String userRoleName) {
//		this.rolesName = userRoleName;
	}
	@Override
	public String getUserImagePath() {
		return userImagePath;
	}
	@Override
	public void setUserImagePath(String userImagePath) {
		this.userImagePath = userImagePath;
	}

	
	

}
