package org.kanomchan.core.common.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClassMapper {
	
//	private Map<String, Property> mapCol = new ConcurrentHashMap<String, Property>();
	
	
//	public void setMapCol(Map<String, Property> mapCol) {
//		this.mapCol = mapCol;
//	}
	private String tableName;
	private Property propertyId;
	private Map<String, List<Property>> column = new ConcurrentHashMap<String, List<Property>>();
	private List<Property> oneToManyList = new ArrayList<Property>(); 
	public Property getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Property propertyId) {
		this.propertyId = propertyId;
	}
	public Map<String, List<Property>> getColumn() {
		return column;
	}
	public void setColumn(Map<String, List<Property>> column) {
		this.column = column;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<Property> getOneToManyList() {
		return oneToManyList;
	}
	public void setOneToManyList(List<Property> oneToManyList) {
		this.oneToManyList = oneToManyList;
	}
	
//	public void putMethodSet(String name, Method methodSet) {
//		mapSet.put(name, methodSet);
//	}
//	public boolean containsKeyMethodSet(String name) {
//		return mapSet.containsKey(name);
//	}
//	public Method getMethodSet(String columnName) {
//		return mapSet.get(columnName);
//	}
//	public int size() {
//		return mapSet.size();
//	}
//	public boolean isEmpty() {
//		return mapSet.isEmpty();
//	}
//	public boolean containsKey(Object key) {
//		return mapSet.containsKey(key);
//	}
//	public boolean containsValue(Object value) {
//		return mapSet.containsValue(value);
//	}
//	public Property get(Object key) {
//		return mapSet.get(key);
//	}
//	public Property put(String key, Property value) {
//		return mapSet.put(key, value);
//	}
//	public Property remove(Object key) {
//		return mapSet.remove(key);
//	}
//	public void putAll(Map<? extends String, ? extends Property> m) {
//		mapSet.putAll(m);
//	}
//	public void clear() {
//		mapSet.clear();
//	}
//	public Set<String> keySet() {
//		return mapSet.keySet();
//	}
//	public Collection<Property> values() {
//		return mapSet.values();
//	}
//	public Set<java.util.Map.Entry<String, Property>> entrySet() {
//		return mapSet.entrySet();
//	}
	

}
