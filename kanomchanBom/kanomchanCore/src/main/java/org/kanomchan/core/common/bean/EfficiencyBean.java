package org.kanomchan.core.common.bean;

import java.io.Serializable;
import java.util.Date;

public class EfficiencyBean<T extends Object> implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8481671809471012389L;
	private T bean;
	private Date efficiencyDate;
	private EfficiencyBean<T> nextEfficiencyBean;
	
	public T getBean() {
		return bean;
	}
	public void setBean(T bean) {
		this.bean = bean;
	}
	public Date getEfficiencyDate() {
		return efficiencyDate;
	}
	public void setEfficiencyDate(Date efficiencyDate) {
		this.efficiencyDate = efficiencyDate;
	}
	public EfficiencyBean<T> getNextEfficiencyBean() {
		return nextEfficiencyBean;
	}
	public void setNextEfficiencyBean(EfficiencyBean<T> nextEfficiencyBean) {
		this.nextEfficiencyBean = nextEfficiencyBean;
	}

	
	
	
	
}
