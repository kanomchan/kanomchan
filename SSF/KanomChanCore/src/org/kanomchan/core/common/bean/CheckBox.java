package org.kanomchan.core.common.bean;

public class CheckBox<T> {
	
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
