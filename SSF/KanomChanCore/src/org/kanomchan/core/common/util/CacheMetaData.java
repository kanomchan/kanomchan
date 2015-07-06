package org.kanomchan.core.common.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CacheMetaData {

	private Map<String, Map<String, Integer>> cacheTable= new HashMap<String, Map<String,Integer>>();
	private Map<String,Integer> cacheColumn= new HashMap<String,Integer>();
	
	public CacheMetaData(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		for (int i = 0; i < md.getColumnCount(); i++) {
			String columnName = md.getColumnName(i+1);
			//option |
			if(columnName.indexOf("|")!=-1){
				String prefixName = columnName.substring(columnName.indexOf("|"));
				String columnNameOption = columnName.substring(columnName.indexOf("|"), columnName.length());
				Map<String, Integer> columnNameOptionMap = cacheTable.get(prefixName);
				if(columnNameOptionMap==null){
					columnNameOptionMap = new HashMap<String, Integer>();
					cacheTable.put(prefixName, columnNameOptionMap);
				}
				columnNameOptionMap.put(columnNameOption, i+1);
			}else{
				String tableName = md.getTableName(i+1);
				Map<String, Integer> cloumMap = cacheTable.get(tableName);
				if(cloumMap == null){
					cloumMap = new HashMap<String, Integer>();
					cacheTable.put(tableName, cloumMap);
				}
				cloumMap.put(columnName, i+1);
			}
			
			cacheColumn.put(columnName, i+1);
		}
	}
	
	public Map<String, Integer> getMapCloumByTable(String tableName){
		return cacheTable.get(tableName);
	}
	
	public Integer getFindCloumIndexByTableNameAndCloum(String tableName,String cloumName){
		if(!cacheTable.containsKey(tableName))
			return null;
		Map<String, Integer> cloumMap = cacheTable.get(tableName);
		if(!cloumMap.containsKey(cloumName))
			return null;
		return cloumMap.get(cloumName);
	}
	
	public Map<String, Integer> getCacheColumn() {
		return cacheColumn;
	}
}
