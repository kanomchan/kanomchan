package org.kanomchan.core.common.util;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.kanomchan.core.common.bean.ClassMapper;
import org.kanomchan.core.common.bean.ColumnType;
import org.kanomchan.core.common.bean.Criteria;
import org.kanomchan.core.common.bean.Property;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

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
			Map<String, List<Property>> column = classMapper.getColumn();
			if(column !=null){
				
				for(Entry<String, List<Property>> entry : column.entrySet()) {
				    String key = entry.getKey();
				    List<Property> propertyList = entry.getValue();
				    
				    Object value =null;
				    for (Property property : propertyList) {
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
		}
		
		return criterias;
	}
	public static ClassMapper getClassMapper(Class<?> clazz){
//		BeanInfo info = Introspector.getBeanInfo(clazz);
//		ClassMapper classMapper =mapClass.get(clazz.getName());
		
//		ClassMapper classMapper = null;
		ClassMapper classMapper = mapClass.get(clazz.getName());
		if (classMapper == null) {
			Set<Method> methods = new HashSet<Method>();
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
					methods.add(methodGet);
//				    for ( PropertyDescriptor pd : info.getPropertyDescriptors() )
//				        if (name.equals(pd.getName())) return pd.getWriteMethod();
					Transient transientT = field.getAnnotation(Transient.class);
					if(transientT==null){
						transientT = methodGet.getAnnotation(Transient.class);
						if(transientT!=null){
							continue;
						}
					}
					classMapper = genClassMapper(classMapper, field, methodGet, methodSet);
					
				} catch (NoSuchFieldException | IntrospectionException e) {
//					e.printStackTrace();
				}
			}
			
			for (Method methodGet : clazz.getMethods()) {
				Transient transientT = methodGet.getAnnotation(Transient.class);
				if(transientT!=null){
					continue;
				}
				if(methods.contains(methodGet))
					continue;
				if(methodGet.getReturnType()!=null){
					if(methodGet.getName().startsWith("get")){
						try {
							String filename = methodGet.getName().substring(3,4).toLowerCase()+methodGet.getName().substring(4);
							Method methodSet = ClassUtil.findSetter(clazz, filename);
							
							classMapper = genClassMapper(classMapper, null, methodGet, methodSet);
						} catch (NoSuchFieldException | IntrospectionException e) {
//							e.printStackTrace();
						}
					}
					
					
				}
			}
				mapClass.put(clazz.getName(),classMapper);
		}
		return classMapper;
	}
	
	private static ClassMapper genClassMapper(ClassMapper classMapper,Field field,Method methodGet,Method methodSet){
		try {
			
			
//			Method methodSet = ClassUtil.findSetter(clazz, field.getName());
//			Method methodGet = ClassUtil.findGetter(clazz, field.getName());
//			methods.add(methodGet);
//		    for ( PropertyDescriptor pd : info.getPropertyDescriptors() )
//		        if (name.equals(pd.getName())) return pd.getWriteMethod();
			Column column = null ;
			EmbeddedId embeddedId = null ;
			Id id = null ;
			AttributeOverrides attributeOverrides = null  ;
			JoinColumn joinColumn = null ;
			JoinColumns joinColumns = null ;
			Enumerated enumerated = null ;
			Embedded embedded = null ;
			OneToMany oneToMany = null;
			if(field != null){
				 column = field.getAnnotation(Column.class);
				 embeddedId = field.getAnnotation(EmbeddedId.class);
				 id = field.getAnnotation(Id.class);
				 attributeOverrides  = field.getAnnotation(AttributeOverrides.class);
				 joinColumn = field.getAnnotation(JoinColumn.class);
				 joinColumns = field.getAnnotation(JoinColumns.class);
				 enumerated = field.getAnnotation(Enumerated.class);
				 embedded = field.getAnnotation(Embedded.class);
				 oneToMany = field.getAnnotation(OneToMany.class);
			}
			
			if (column == null)
				column = methodGet.getAnnotation(Column.class);
			if (id == null)
				id = methodGet.getAnnotation(Id.class);
			if (embeddedId == null)
				embeddedId = methodGet.getAnnotation(EmbeddedId.class);
			if (attributeOverrides == null)
				attributeOverrides  = methodGet.getAnnotation(AttributeOverrides.class);
			if (joinColumn == null)
				joinColumn = methodGet.getAnnotation(JoinColumn.class);
			if (joinColumns == null)
				joinColumns = methodGet.getAnnotation(JoinColumns.class);
			if (enumerated == null)
				enumerated = methodGet.getAnnotation(Enumerated.class);
			if (embedded == null)
				embedded = methodGet.getAnnotation(Embedded.class);
			if (oneToMany == null)
				oneToMany = methodGet.getAnnotation(OneToMany.class);
			if (column != null) {
				// not have this column name in classMapper
				if (!classMapper.getColumn().containsKey(column.name())) {
					Property property = new Property();
					property.setMethodGet(methodGet);
					property.setMethodSet(methodSet);
					property.setColumnName(column.name());
					property.setColumnType(ColumnType.column);
//					classMapper.getColumn().put(column.name(), property);//Old version
					List<Property> properties = classMapper.getColumn().get(column.name());
					if(properties==null)
						properties = new ArrayList<Property>();
					properties.add(property);
					classMapper.getColumn().put(column.name(), properties);
					if(enumerated!=null)
						property.setEnumType(enumerated.value());
					if(id!=null){
						property.setColumnType(ColumnType.id);
						classMapper.setPropertyId(property);
					}else if(embeddedId!=null){
						property.setColumnType(ColumnType.embeddedId);
						classMapper.setPropertyId(property);
					}
				}

			}else if(joinColumns!=null){
				Property joinColumnsProperty = new Property();
				joinColumnsProperty.setMethodGet(methodGet);
				joinColumnsProperty.setMethodSet(methodSet);
				joinColumnsProperty.setColumnType(ColumnType.joinColumns);
//				classMapper.getColumn().put(joinColumn.name(), property); // old version
				JoinColumn[] joinColumnsInJoinColumns = joinColumns.value();
				if(joinColumnsInJoinColumns!=null&&joinColumnsInJoinColumns.length>0){
					for (JoinColumn joinColumnNodeInJoinColumns : joinColumnsInJoinColumns) {
						List<Property> properties = classMapper.getColumn().get(joinColumnNodeInJoinColumns.name());
						if(properties==null)
							properties = new ArrayList<Property>();
//						joinColumn2
						String referencedColumnName = joinColumnNodeInJoinColumns.referencedColumnName();
						List<Property> referencedPropertiesList = getClassMapper(methodGet.getReturnType()).getColumn().get(referencedColumnName);
						if(referencedPropertiesList!=null){
							for (Property referencedProperties : referencedPropertiesList) {
								Property embeddedIdProperty = referencedProperties.getEmbeddedId();
								if(embeddedIdProperty!=null){
									if(!embeddedIdProperty.getMethodGet().getDeclaringClass().equals(methodGet.getReturnType())){
										continue;
									}
								}else{
									if(!referencedProperties.getMethodGet().getDeclaringClass().equals(methodGet.getReturnType())){
										continue;
									} 
								}
								
									Property propertyjoinColumnMuti = new Property();
									propertyjoinColumnMuti.setMethodGet(referencedProperties.getMethodGet());
									propertyjoinColumnMuti.setMethodSet(referencedProperties.getMethodSet());
									propertyjoinColumnMuti.setColumnName(joinColumnNodeInJoinColumns.name());
									propertyjoinColumnMuti.setEmbeddedId(referencedProperties.getEmbeddedId());
									propertyjoinColumnMuti.setJoinColumns(joinColumnsProperty);
									propertyjoinColumnMuti.setColumnType(ColumnType.joinColumns);
									properties.add(propertyjoinColumnMuti);
							}
							
							classMapper.getColumn().put(joinColumnNodeInJoinColumns.name(), properties);
							
						}
						
					}
				}
				
			}else if(joinColumn!=null){
				Property property = new Property();
				property.setMethodGet(methodGet);
				property.setMethodSet(methodSet);
				property.setColumnType(ColumnType.joinColumn);
//				classMapper.getColumn().put(joinColumn.name(), property); // old version
				List<Property> properties = classMapper.getColumn().get(joinColumn.name());
				if(properties==null)
					properties = new ArrayList<Property>();
				properties.add(property);
				classMapper.getColumn().put(joinColumn.name(), properties);
				if(id!=null){
					property.setColumnType(ColumnType.id);
					classMapper.setPropertyId(property);
				}else if(embeddedId!=null){
					property.setColumnType(ColumnType.embeddedId);
					classMapper.setPropertyId(property);
				}
			}else if(embedded!=null){
				Property property = new Property();
				property.setMethodGet(methodGet);
				property.setMethodSet(methodSet);
				
				property.setColumnType(ColumnType.embedded);
//				classMapper.setPropertyId(property);
				
				Class<?> returnType = methodGet.getReturnType();
				if(attributeOverrides!=null){
					for (AttributeOverride attributeOverride : attributeOverrides.value()) {
						String name = attributeOverride.name();
						Method methodSetembeddedId= null;
						Method methodGetembeddedId = null;
						try{
							Field fieldembeddedId = returnType.getDeclaredField(name);
							name = fieldembeddedId.getName();
//							methodSetembeddedId = ClassUtil.findSetter(returnType, name);
//							methodGetembeddedId = ClassUtil.findGetter(returnType, fieldembeddedId.getName());
						}catch(Exception e){
							
						}
						
						methodSetembeddedId = ClassUtil.findSetter(returnType, name);
						methodGetembeddedId = ClassUtil.findGetter(returnType, name);
						Column columnEmbeddedId = attributeOverride.column();
						Property propertyEmbeddedId = new Property();
						propertyEmbeddedId.setMethodGet(methodGetembeddedId);
						propertyEmbeddedId.setMethodSet(methodSetembeddedId);
						propertyEmbeddedId.setColumnName(columnEmbeddedId.name());
						propertyEmbeddedId.setEmbeddedId(property);
						propertyEmbeddedId.setColumnType(ColumnType.embedded);//ColumnType.column
						List<Property> propertiesEmbeddedId = classMapper.getColumn().get(columnEmbeddedId.name());
						if(propertiesEmbeddedId==null)
							propertiesEmbeddedId = new ArrayList<Property>();
						propertiesEmbeddedId.add(propertyEmbeddedId);
						classMapper.getColumn().put(columnEmbeddedId.name(), propertiesEmbeddedId);
						
					}
				}
				
			}else if(embeddedId!=null){
				
				Property property = new Property();
				property.setMethodGet(methodGet);
				property.setMethodSet(methodSet);
//				property.setColumnName(field.getName());
//				List<Property> properties = classMapper.getColumn().get(joinColumn.name());
//				if(properties==null)
//					properties = new ArrayList<Property>();
//				properties.add(property);
//				classMapper.getColumn().put(field.getName(), properties);
				property.setColumnType(ColumnType.embeddedId);
				classMapper.setPropertyId(property);
				
				Class<?> returnType = methodGet.getReturnType();
				if(attributeOverrides!=null){
					for (AttributeOverride attributeOverride : attributeOverrides.value()) {
						String name = attributeOverride.name();
						Field fieldembeddedId = returnType.getDeclaredField(name);
						Method methodSetembeddedId = ClassUtil.findSetter(returnType, fieldembeddedId.getName());
						Method methodGetembeddedId = ClassUtil.findGetter(returnType, fieldembeddedId.getName());
						Column columnEmbeddedId = attributeOverride.column();
						Property propertyEmbeddedId = new Property();
						propertyEmbeddedId.setMethodGet(methodGetembeddedId);
						propertyEmbeddedId.setMethodSet(methodSetembeddedId);
						propertyEmbeddedId.setColumnName(columnEmbeddedId.name());
						propertyEmbeddedId.setEmbeddedId(property);
						propertyEmbeddedId.setColumnType(ColumnType.embeddedId);//ColumnType.column
						List<Property> propertiesEmbeddedId = classMapper.getColumn().get(columnEmbeddedId.name());
						if(propertiesEmbeddedId==null)
							propertiesEmbeddedId = new ArrayList<Property>();
						propertiesEmbeddedId.add(propertyEmbeddedId);
						classMapper.getColumn().put(columnEmbeddedId.name(), propertiesEmbeddedId);
						
					}
				}
					
//				Property property = new Property();
//				property.setMethodGet(methodGet);
//				property.setMethodSet(methodSet);
//				property.setColumnName(field.getName());
//				classMapper.getColumn().put(field.getName(), property);
//				property.setColumnType(ColumnType.embeddedId);
//				classMapper.setPropertyId(property);
			}
			
			if(oneToMany!=null){
				Property property = new Property();
				property.setMethodGet(methodGet);
				property.setMethodSet(methodSet);
				property.setColumnName(oneToMany.mappedBy());
				if(oneToMany.fetch() == FetchType.LAZY)
					property.setColumnType(ColumnType.oneToManyLAZY);
				else
					property.setColumnType(ColumnType.oneToManyEAGER);
				classMapper.getOneToManyList().add(property);
			}
		} catch (NoSuchFieldException | IntrospectionException e) {
//			e.printStackTrace();
		}
		return classMapper;
	}
	
	

	private static Map<String, ClassMapper > mapClass = new ConcurrentHashMap<String,ClassMapper>();
	
	public static <T extends Object> RowMapper<T> getRm(final Class<T> clazz){
		return getRm(null,clazz, null);
	}
	
	public static <T extends Object> RowMapper<T> getRm(final Class<T> clazz,final String prefix){
		return getRm(null ,clazz, prefix);
	}
	
	public static <T extends Object> RowMapper<T> getRm(final CacheMetaData cacheMetaData,final Class<T> clazz,final String prefix  ){
		return new RowMapper<T>() {
//			private Map<String, List<Integer>> cacheListTable;

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				T t = null;
				try {
					t = clazz.newInstance();
					ClassMapper classMapper = getClassMapper(clazz);
					Map<String, List<Property>> columns = classMapper.getColumn();
					String tableName;
					if(prefix!=null){
						tableName = prefix;
					}else{
						tableName = classMapper.getTableName();
					}
//					ResultSetMetaData md = rs.getMetaData();
//					String langName="";
					CacheMetaData localcacheMetaData;
					if(cacheMetaData!=null){
						localcacheMetaData = cacheMetaData;
					}else{
						localcacheMetaData = new CacheMetaData(rs);
					}
					
					Map<String, Integer> mapCloum = localcacheMetaData.getMapCloumByTable(tableName);
					if(mapCloum ==null)
						mapCloum = localcacheMetaData.getCacheColumn();
					if(mapCloum !=null){
						for(Entry<String, Integer> entry : mapCloum.entrySet()) {
						    String columnName = entry.getKey();
						    Integer index = entry.getValue();
						    List<Property> properties = null;
						    if(columnName.endsWith("_LANG")){
						    	String langName = columnName.substring(0, columnName.length()-5);
								if(!columns.containsKey(langName))
									continue;
								properties = columns.get(langName);
							}else{
								if(!columns.containsKey(columnName))
									continue;
								properties = columns.get(columnName);
							}
						    if(properties!=null){
								for (Property property : properties) {
									if(property==null)
										continue;
									Method methodSet = property.getMethodSet();
									if(methodSet==null)
										continue;
									try {
										t = mapRow(rs, rowNum, t, index, property);
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
							}
						}
					}else{
						if(logger.isWarnEnabled())
						logger.warn("Table Name :"+tableName);
					}
					
					//Old Loop
//					cacheListTable = new HashMap<String, List<Integer>>();
//						for (int i = 0; i < md.getColumnCount(); i++) {
//							String columnName = md.getColumnName(i+1);
//							if(prefix!=null){
//								if(!columnName.startsWith(prefix)&&!columnName.startsWith(prefix+"|")){
//									if(md.getTableName(i+1).equalsIgnoreCase(prefix)){
//									}else{
//										continue;
//									}
//								}else if(columnName.startsWith(prefix+"|")){
//									columnName = columnName.substring(prefix.length()+1);
//								}else{
//									columnName = columnName.substring(prefix.length());
//								}
//							}
//							List<Property> properties = null;
//							if(langName.equals(columnName))
//								continue;
//							if(columnName.endsWith("_LANG")){
//								langName = columnName.substring(0, columnName.length()-5);
//								if(!columns.containsKey(langName))
//									continue;
//								properties = columns.get(langName);
//							}else{
//								if(!columns.containsKey(columnName))
//									continue;
//								properties = columns.get(columnName);
//							}
//							
////							if(!columns.containsKey(columnName))
////								continue;
////							List<Property> properties = columns.get(columnName);
//							if(properties!=null){
//								for (Property property : properties) {
//									if(property==null)
//										continue;
//									Method methodSet = property.getMethodSet();
//									if(methodSet==null)
//										continue;
//									try {
//										t = mapRow(rs, rowNum, t, i+1, property);
//									} catch (Exception e) {
//										// TODO: handle exception
//									}
//								}
//							}
//							
//						}
					
					
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
					if(ColumnType.embeddedId == property.getColumnType()){
						objectData = property.getEmbeddedId().getMethodGet().invoke(traget);
						if(objectData == null){
							objectData = property.getEmbeddedId().getMethodSet().getParameterTypes()[0].newInstance();
							property.getEmbeddedId().getMethodSet().invoke(traget, objectData);
						}
						putData(rs, columnNum, property, objectData);
						
					}else if(ColumnType.joinColumns == property.getColumnType()){
						objectData = property.getJoinColumns().getMethodGet().invoke(traget);
						if(objectData == null){
							objectData = property.getJoinColumns().getMethodSet().getParameterTypes()[0].newInstance();
							property.getJoinColumns().getMethodSet().invoke(traget, objectData);
						}
							
						if(property.getEmbeddedId()!=null){
							if(property.getEmbeddedId().getMethodGet().getDeclaringClass().isInstance(objectData)){
								Object objectDataEm = property.getEmbeddedId().getMethodGet().invoke(objectData);
								if(objectDataEm==null){
									objectDataEm = property.getEmbeddedId().getMethodSet().getParameterTypes()[0].newInstance();
									property.getEmbeddedId().getMethodSet().invoke(objectData,objectDataEm);
								}
								putData(rs, columnNum, property, objectDataEm);
							}
						}else{
							putData(rs, columnNum, property, objectData);
						}
					}else if(ColumnType.embedded == property.getColumnType()){
						objectData = property.getEmbeddedId().getMethodGet().invoke(traget);
						if(objectData == null){
							objectData = property.getEmbeddedId().getMethodSet().getParameterTypes()[0].newInstance();
							property.getEmbeddedId().getMethodSet().invoke(traget, objectData);
						}
						
						putData(rs, columnNum, property, objectData);
						
						
					}else{
						putData(rs, columnNum, property, traget);
					}
					
				} catch (IllegalArgumentException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
					logger.error("$RowMapper<T>.mapRow(ResultSet, int)", e); //$NON-NLS-1$
				}
				return traget;
			}
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			private void putData(ResultSet rs,int  columnNum ,Property property, Object traget ) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, SQLException{
				Object objectData = new Object[]{null};
				if(property.getMethodSet().getParameterTypes()[0].isAnnotationPresent(Entity.class)){
					ClassMapper classMapperId = getClassMapper(property.getMethodSet().getParameterTypes()[0]);
					objectData = property.getMethodSet().getParameterTypes()[0].newInstance();
					Method methodSetId  = classMapperId.getPropertyId().getMethodSet();
					methodSetId.invoke(objectData,  JdbcUtils.getResultSetValue(rs, columnNum, methodSetId.getParameterTypes()[0]));
				}else{
					if(property.getEnumType()!=null){
						if(EnumType.STRING==property.getEnumType()){
							String EnumValue = (String) JdbcUtils.getResultSetValue(rs, columnNum, String.class);
							Class<Enum> class1 = (Class<Enum>) property.getMethodSet().getParameterTypes()[0];
							objectData = Enum.valueOf(class1, EnumValue);
						}else if(EnumType.ORDINAL==property.getEnumType()){
							Integer EnumValue = (Integer) JdbcUtils.getResultSetValue(rs, columnNum, Integer.class);
							Class<Enum> class1 = (Class<Enum>) property.getMethodSet().getParameterTypes()[0];
							objectData = Enum.valueOf(class1, EnumValue+"");
						}else{
							String EnumValue = (String) JdbcUtils.getResultSetValue(rs, columnNum, String.class);
							Class<Enum> class1 = (Class<Enum>) property.getMethodSet().getParameterTypes()[0];
							objectData = Enum.valueOf(class1, EnumValue);
						}
					}else{
						objectData = JdbcUtils.getResultSetValue(rs, columnNum, property.getMethodSet().getParameterTypes()[0]);
					}
					
				}
				property.getMethodSet().invoke(traget, objectData);
			}
			
//			move to use JdbcUtils
//			@SuppressWarnings({ "hiding", "unchecked" })move to use JdbcUtils
//			private <T extends Object> T getObject(ResultSet rs,int  columnNum ,Class<T> clazz ){
//				Object objectData = new Object[]{null};
//				try{
//					if(clazz.equals(Date.class)){
//						objectData = new Date(rs.getTimestamp(columnNum).getTime());
//					}else
//					objectData = rs.getObject(columnNum, clazz);
//				}catch(Exception e){
//					if(clazz.equals(Integer.class)){
//						try {
//							objectData =  rs.getInt(columnNum);
//						} catch (SQLException e1) {
////							logger.error("getObject(ResultSet, String, Class<T>)", e1); //$NON-NLS-1$
//							try {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: Integer Error"+rs.getMetaData().getColumnName(columnNum), e1);
//							} catch (SQLException e2) {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: Integer Error", e2);
//							}
//						}
//					}else if(clazz.equals(Short.class)){
//						try {
//							objectData =  rs.getShort(columnNum);
//						} catch (SQLException e1) {
////							logger.error("getObject(ResultSet, String, Class<T>)", e1); //$NON-NLS-1$
//							try {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: Short Error"+rs.getMetaData().getColumnName(columnNum), e1);
//							} catch (SQLException e2) {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: Short Error", e2);
//							}
//						}
//					}else if(clazz.equals(Long.class)){
//						try {
//							objectData =  rs.getLong(columnNum);
//						} catch (SQLException e1) {
////							logger.error("getObject(ResultSet, String, Class<T>)", e1); //$NON-NLS-1$
//							try {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: Long Error"+rs.getMetaData().getColumnName(columnNum), e1);
//							} catch (SQLException e2) {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: Long Error", e2);
//							}
//						}
//					}else if(clazz.equals(Double.class)){
//						try {
//							objectData =  rs.getDouble(columnNum);
//						} catch (SQLException e1) {
////							logger.error("getObject(ResultSet, String, Class<T>)", e1); //$NON-NLS-1$
//							try {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: Double Error"+rs.getMetaData().getColumnName(columnNum), e1);
//							} catch (SQLException e2) {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: Double Error", e2);
//							}
//						}
//					}else if(clazz.equals(String.class)){
//						try {
//							objectData =  rs.getString(columnNum);
//						} catch (SQLException e1) {
////							logger.error("getObject(ResultSet, String, Class<T>)", e1); //$NON-NLS-1$
//							try {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: String Error"+rs.getMetaData().getColumnName(columnNum), e1);
//							} catch (SQLException e2) {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: String Error", e2);
//							}
//						}
//					}else if(clazz.equals(Date.class)){
//						try {
//							objectData =  rs.getDate(columnNum);
//						} catch (SQLException e1) {
//							try {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: Date Error"+rs.getMetaData().getColumnName(columnNum), e1);
//							} catch (SQLException e2) {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: Date Error", e2);
//							}
//						}
//					}else if(clazz.equals(Time.class)){
//						try {
//							objectData =  rs.getTime(columnNum);
//						} catch (SQLException e1) {
////							logger.error("getObject(ResultSet, String, Class<T>)", e1); //$NON-NLS-1$
//							try {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: Time Error"+rs.getMetaData().getColumnName(columnNum), e1);
//							} catch (SQLException e2) {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: Time Error", e2);
//							}
//						}
//					}else if(clazz.equals(Timestamp.class)){
//						try {
//							objectData =  rs.getTimestamp(columnNum);
//						} catch (SQLException e1) {
////							logger.error("getObject(ResultSet, String, Class<T>)", e1); //$NON-NLS-1$
//							try {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: Timestamp Error"+rs.getMetaData().getColumnName(columnNum), e1);
//							} catch (SQLException e2) {
//								logger.error("getObject(ResultSet, String, Class<T>) Type: Timestamp Error", e2);
//							}
//						}
//					}else{
//						logger.error("column :"+columnNum+" type: "+clazz.getSimpleName());
//					}
//				}
//				return (T) objectData;
//			}

		};
	}
	

}
