package org.kanomchan.core.common.bean;

import java.util.Date;

public class UserFacebook {
	
	private static final long serialVersionUID = 2275327612858235569L;
	String id;
	String email;
	String first_name;
	String gender;
	String last_name;
	String link;
	String locale;
	String name;
	int timezone;
	Date update_time;
	boolean verified;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getGender() {
		
		if("male".equalsIgnoreCase(gender))
		{	return "1";}
		else{
			if("female".equalsIgnoreCase(gender))
			{	return "2";}
			else{
				return null;
			
			}
		}
		 
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTimezone() {
		return timezone;
	}
	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	
	
}
