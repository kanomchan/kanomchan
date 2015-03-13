package org.kanomchan.core.openid.bean;

import java.util.Map;

public class AuthRequestBean {
	private String redirectUrl;
	private String nonce;
	private String state;
	private Map<String, Object> session;
	public void sendRedirect(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


}
