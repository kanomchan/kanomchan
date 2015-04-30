package org.kanomchan.core.common.bean;

import java.lang.reflect.Method;

import javax.persistence.EnumType;

public class Property {
	private Method methodGet;
	private Method methodSet;
	private ColumnType columnType;
	private String columnName;
	private Property EmbeddedId;
	private EnumType enumType;
	private Property joinColumns;
	
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
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Property getEmbeddedId() {
		return EmbeddedId;
	}
	public void setEmbeddedId(Property embeddedId) {
		EmbeddedId = embeddedId;
	}
	public EnumType getEnumType() {
		return enumType;
	}
	public void setEnumType(EnumType enumType) {
		this.enumType = enumType;
	}
	public Property getJoinColumns() {
		return joinColumns;
	}
	public void setJoinColumns(Property joinColumns) {
		this.joinColumns = joinColumns;
	}

	
	
	
}
