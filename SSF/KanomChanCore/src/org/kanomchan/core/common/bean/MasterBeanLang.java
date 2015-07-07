package org.kanomchan.core.common.bean;

import java.io.Serializable;

public class MasterBeanLang<T> implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4533404070391824591L;
	private T beanEng;
	private T beanOtherLang;
	private MasterBeanLang<T> masterBeanLang;
	private String langCode;
	
	public T getBeanEng() {
		return beanEng;
	}
	public void setBeanEng(T beanEng) {
		this.beanEng = beanEng;
	}
	public T getBeanOtherLang() {
		return beanOtherLang;
	}
	public void setBeanOtherLang(T beanOtherLang) {
		this.beanOtherLang = beanOtherLang;
	}
	public MasterBeanLang<T> getMasterBeanLang() {
		return masterBeanLang;
	}
	public void setMasterBeanLang(MasterBeanLang<T> masterBeanLang) {
		this.masterBeanLang = masterBeanLang;
	}
	public String getLangCode() {
		return langCode;
	}
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	
}
