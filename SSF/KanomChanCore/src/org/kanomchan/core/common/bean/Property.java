package org.kanomchan.core.common.bean;

import java.lang.reflect.Method;

public class Property {
	private Method methodGet;
	private Method methodSet;
	private ColumnType columnType;
	public Method getMethodGet() {
		return methodGet;
	}
	public void setMethodGet(Method methodGet) {
		this.methodGet = methodGet;
	}
	public Method getMethodSet() {
		return methodSet;
	}
	public void setMethodSet(Method methodSet) {
		this.methodSet = methodSet;
	}
	public ColumnType getColumnType() {
		return columnType;
	}
	public void setColumnType(ColumnType columnType) {
		this.columnType = columnType;
	}

	
	
}
