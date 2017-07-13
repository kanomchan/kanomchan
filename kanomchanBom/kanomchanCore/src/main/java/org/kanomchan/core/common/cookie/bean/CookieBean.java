package org.kanomchan.core.common.cookie.bean;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.Cookie;

import org.kanomchan.core.common.io.LoginIO;

public class CookieBean implements Serializable {
	
	private CookieOrm cookieOrm;
	private List<Cookie> cookies;
	private LoginIO loginIO;
	public CookieOrm getCookieOrm() {
		return cookieOrm;
	}
	public void setCookieOrm(CookieOrm cookieOrm) {
		this.cookieOrm = cookieOrm;
	}
	public LoginIO getLoginIO() {
		return loginIO;
	}
	public void setLoginIO(LoginIO loginIO) {
		this.loginIO = loginIO;
	}
	public List<Cookie> getCookies() {
		return cookies;
	}
	public void setCookies(List<Cookie> cookies) {
		this.cookies = cookies;
	}
	
	
	
}
