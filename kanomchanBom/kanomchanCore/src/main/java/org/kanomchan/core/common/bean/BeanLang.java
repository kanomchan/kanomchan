package org.kanomchan.core.common.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BeanLang<T> implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4533404070391824591L;
	private T beanEng;
	private T beanOtherLang;
	private BeanLang<T> beanLang;
//	private Map<String, T> beanLangMap = new HashMap<String, T>();
	private Map<String, T> beanLangMap;
	private String langCode;
	private Long idLang;
	
	public BeanLang() {
		
	}
	public BeanLang(T beanEng, T beanOtherLang, BeanLang<T> beanLang, String langCode, Long idLang) {
		this.beanEng = beanEng;
		this.beanOtherLang = beanOtherLang;
		this.beanLang = beanLang;
		this.langCode = langCode;
		this.idLang = idLang;
	}
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
	public BeanLang<T> getMasterBeanLang() {
		return beanLang;
	}
	public void setMasterBeanLang(BeanLang<T> masterBeanLang) {
		this.beanLang = masterBeanLang;
	}
	public String getLangCode() {
		return langCode;
	}
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	public Long getIdLang() {
		return idLang;
	}
	public void setIdLang(Long idLang) {
		this.idLang = idLang;
	}
	public void setEngLang(T beanEng) {
		this.beanEng = beanEng;
	}
	public T getEngLang() {
		return this.beanEng;
	}
	public void setOtherLang(T otherLang) {
		this.beanOtherLang = otherLang;
	}
	
	public T getOtherLang() {
		return this.beanOtherLang;
	}
	public Map<String, T> getBeanLangMap() {
		return beanLangMap;
	}
	public void setBeanLangMap(Map<String, T> beanLangMap) {
		this.beanLangMap = beanLangMap;
	}
	public BeanLang<T> getBeanLang() {
		return beanLang;
	}
	public void setBeanLang(BeanLang<T> beanLang) {
		this.beanLang = beanLang;
	}
	
}
