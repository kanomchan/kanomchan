package org.kanomchan.core.common.bean;

import java.util.Date;

public class EfficiencyBean<T extends Object> {

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
