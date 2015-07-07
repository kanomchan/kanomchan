package org.kanomchan.core.common.bean;

import java.io.Serializable;


public class BeanLang<T> implements Serializable {
	private static final long serialVersionUID = -4533404070391824591L;
	private T engLang;
	private T otherLang;
	private MasterBeanLang<T> BeanLang;
	private String langCode;
	private Long idLang;
	
	public T getEngLang() {
		return engLang;
	}
	public void setEngLang(T engLang) {
		this.engLang = engLang;
	}
	public T getOtherLang() {
		return otherLang;
	}
	public void setOtherLang(T otherLang) {
		this.otherLang = otherLang;
	}
	public MasterBeanLang<T> getBeanLang() {
		return BeanLang;
	}
	public void setBeanLang(MasterBeanLang<T> beanLang) {
		BeanLang = beanLang;
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
}
