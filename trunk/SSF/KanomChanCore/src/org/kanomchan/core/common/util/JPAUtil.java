package org.kanomchan.core.common.util;

import org.apache.log4j.Logger;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.kanomchan.core.common.bean.ClassMapper;
import org.kanomchan.core.common.bean.ColumnType;
import org.kanomchan.core.common.bean.Criteria;
import org.kanomchan.core.common.bean.Property;
import org.springframework.jdbc.core.RowMapper;

public class JPAUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JPAUtil.class);
	
	public static List<Criteria> beanToParamterList(Object traget){
		List<Criteria> criterias = null;
		
		if(traget!=null){
			criterias = new LinkedList<Criteria>();
			Class<?> clazz = traget.getClass();
			ClassMapper classMapper = JPAUtil.getClassMapper(clazz);
			if(classMapper==null)
				return null;
			Map<String, Property> column = classMapper.getColumn();
			if(column !=null){
				
				for(Entry<String, Property> entry : column.entrySet()) {
				    String key = entry.getKey();
				    Property property = entry.getValue();
				    
				    Object value =null;
					try {
						value = property.getMethodGet().invoke(traget);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					}
				    if(value!=null){
				    	criterias.add(new Criteria(key, value));
				    }
				}
			}
		}
		
		return criterias;
	}
	public static ClassMapper getClassMapper(Class<?> clazz){
		
		ClassMapper classMapper =mapClass.get(clazz.getName());
		
//		classMapper = null;
//			ClassMapper classMapper = mapClass.get(clazz.getName());
		if (classMapper == null) {
			classMapper = new ClassMapper();
			Table table = clazz.getAnnotation(Table.class);
			if(table!=null)
				classMapper.setTableName(table.name());
			else
				classMapper.setTableName(clazz.getSimpleName());
			for (Field field : clazz.getDeclaredFields()) {
				try {
					Method methodSet = ClassUtil.findSetter(clazz, field.getName());
					Method methodGet = ClassUtil.findGetter(clazz, field.getName());
					Column column = field.getAnnotation(Column.class);
					Id id = field.getAnnotation(Id.class);
					JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
					if (column == null)
						column = methodGet.getAnnotation(Column.class);
					if (id == null)
						id = methodGet.getAnnotation(Id.class);
					if (joinColumn == null)
						joinColumn = methodGet.getAnnotation(JoinColumn.class);
					if (column != null) {
						if (!classMapper.getColumn().containsKey(column.name())) {
							Property property = new Property();
							property.setMethodGet(methodGet);
							property.setMethodSet(methodSet);
							property.setColumnName(column.name());
							property.setColumnType(ColumnType.column);
							classMapper.getColumn().put(column.name(), property);
							if(id!=null){
								property.setColumnType(ColumnType.id);
								classMapper.setPropertyId(property);
							}
						}

					}else if(joinColumn!=null){
						Property property = new Property();
						property.setMethodGet(methodGet);
						property.setMethodSet(methodSet);
						property.setColumnType(ColumnType.joinColumn);
						classMapper.getColumn().put(joinColumn.name(), property);
						if(id!=null){
							property.setColumnType(ColumnType.id);
							classMapper.setPropertyId(property);
						}
					}
				} catch (NoSuchFieldException | IntrospectionException e) {
					e.printStackTrace();
				}
			}
				mapClass.put(clazz.getName(),classMapper);
		}
		return classMapper;
	}
	
	

	private static Map<String, ClassMapper > mapClass = new ConcurrentHashMap<String,ClassMapper>();
	
	public static <T extends Object> RowMapper<T> getRm(final Class<T> clazz){
		return getRm(clazz, null);
	}
	
	
	
	public static <T extends Object> RowMapper<T> getRm(final Class<T> clazz,final String prefix){
		return new RowMapper<T>() {

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				T t = null;
				try {
					t = clazz.newInstance();
					ClassMapper classMapper = getClassMapper(clazz);
					Map<String, Property> columns = classMapper.getColumn();
					ResultSetMetaData md = rs.getMetaData();
//					if (md.getColumnCount() < columns.size()) {
						for (int i = 0; i < md.getColumnCount(); i++) {
							String columnName = md.getColumnName(i+1);
//							String columnNameMap=null;
							if(prefix!=null){
//								if(!columnName.startsWith(prefix)&&!md.getTableName(i+1).equalsIgnoreCase(prefix))
								if(!columnName.startsWith(prefix)){
									if(md.getTableName(i+1).equalsIgnoreCase(prefix)){
									}else{
										continue;
									}
								}else{
									columnName = columnName.substring(prefix.length());
								}
							}
							
							if(!columns.containsKey(columnName))
								continue;
							Property property = columns.get(columnName);
							if(property==null)
								continue;
							Method methodSet = property.getMethodSet();
							if(methodSet==null)
								continue;
							try {
								t = mapRow(rs, rowNum, t, i+1, property);
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
//					} else {
//						for ( Entry<String, Property> column : columns.entrySet()) {
//							try {
//								String columnName=null;
//								if (prefix!=null) {
//									columnName = prefix+ column.getKey();
//								} else {
//									columnName = column.getKey();
//								}
//								
//								rs.findColumn(columnName);
//								t = mapRow(rs, rowNum, t, columnName, column.getValue());
//							} catch (Exception e) {
//								// TODO: handle exception
//							}
//						}
//					}

					
					
				} catch (InstantiationException | IllegalAccessException e) {
					logger.error("$RowMapper<T>.mapRow(ResultSet, int)", e); //$NON-NLS-1$
				}
				return t;
			}
			
			private T mapRow(ResultSet rs , int rowNum,T traget, int columnNum ,Property property ) throws SQLException {
				
				
				Method methodSet = property.getMethodSet();
				if(methodSet==null)
					return traget;
				try {
					Object objectData = new Object[]{null};
					if(methodSet.getParameterTypes()[0].isAnnotationPresent(Entity.class)){
						ClassMapper classMapperId = getClassMapper(methodSet.getParameterTypes()[0]);
						objectData = methodSet.getParameterTypes()[0].newInstance();
						Method methodSetId  = classMapperId.getPropertyId().getMethodSet();
						methodSetId.invoke(objectData,  getObject(rs, columnNum, methodSetId.getParameterTypes()[0]));
					}else{
						objectData = getObject(rs, columnNum, methodSet.getParameterTypes()[0]);
					}
					methodSet.invoke(traget, objectData);
				} catch (IllegalArgumentException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
					logger.error("$RowMapper<T>.mapRow(ResultSet, int)", e); //$NON-NLS-1$
				}
				return traget;
			}
			
			private <T> T getObject(ResultSet rs,int  columnNum ,Class<T> clazz ){
				Object objectData = new Object[]{null};
				try{
					if(clazz.equals(Date.class)){
						objectData = new Date(rs.getTimestamp(columnNum).getTime());
					}else
					objectData = rs.getObject(columnNum, clazz);
				}catch(Exception e){
					if(clazz.equals(Integer.class)){
						try {
							objectData =  rs.getInt(columnNum);
						} catch (SQLException e1) {
							logger.error("getObject(ResultSet, String, Class<T>)", e1); //$NON-NLS-1$
						}
					}else if(clazz.equals(Short.class)){
						try {
							objectData =  rs.getShort(columnNum);
						} catch (SQLException e1) {
							logger.error("getObject(ResultSet, String, Class<T>)", e1); //$NON-NLS-1$
						}
					}else if(clazz.equals(Long.class)){
						try {
							objectData =  rs.getLong(columnNum);
						} catch (SQLException e1) {
							logger.error("getObject(ResultSet, String, Class<T>)", e1); //$NON-NLS-1$
						}
					}else if(clazz.equals(Double.class)){
						try {
							objectData =  rs.getDouble(columnNum);
						} catch (SQLException e1) {
							logger.error("getObject(ResultSet, String, Class<T>)", e1); //$NON-NLS-1$
						}
					}else if(clazz.equals(String.class)){
						try {
							objectData =  rs.getString(columnNum);
						} catch (SQLException e1) {
							logger.error("getObject(ResultSet, String, Class<T>)", e1); //$NON-NLS-1$
						}
					}else if(clazz.equals(Date.class)){
						try {
							objectData =  rs.getDate(columnNum);
						} catch (SQLException e1) {
							logger.error("getObject(ResultSet, String, Class<T>)", e1); //$NON-NLS-1$
						}
						
					}else if(clazz.equals(Time.class)){
						try {
							objectData =  rs.getTime(columnNum);
						} catch (SQLException e1) {
							logger.error("getObject(ResultSet, String, Class<T>)", e1); //$NON-NLS-1$
						}
					}else if(clazz.equals(Timestamp.class)){
						try {
							objectData =  rs.getTimestamp(columnNum);
						} catch (SQLException e1) {
							logger.error("getObject(ResultSet, String, Class<T>)", e1); //$NON-NLS-1$
						}
					}else{
						logger.error("column :"+columnNum+" type: "+clazz.getSimpleName());
					}
				}
				return (T) objectData;
			}

		};
	}
	

}
