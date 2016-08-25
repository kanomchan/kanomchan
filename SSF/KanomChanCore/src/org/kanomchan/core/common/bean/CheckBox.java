package org.kanomchan.core.common.bean;

import java.io.Serializable;

public class CheckBox<T> implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8198897480488787027L;
	private T value;
	private boolean check;
	public CheckBox(T value) {
		this.value = value;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	
	
	

}
