package org.kanomchan.core.common.util;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.kanomchan.core.common.bean.ClassMapper;
import org.kanomchan.core.common.bean.ColumnType;
import org.kanomchan.core.common.bean.Criteria;
import org.kanomchan.core.common.bean.Property;
import org.springframework.jdbc.core.RowMapper;

public class JPAUtil {
	
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
		return new RowMapper<T>() {

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				T t = null;
				try {
					t = clazz.newInstance();
					ClassMapper classMapper = getClassMapper(clazz);
					ResultSetMetaData md = rs.getMetaData();
					for (int i = 0; i < md.getColumnCount(); i++) {
						String columnName = md.getColumnName(i);
						Method methodSet = classMapper.getColumn().get(columnName).getMethodSet();
						
						try {
							methodSet.invoke(t, rs.getObject(columnName, methodSet.getParameterTypes()[0]));
						} catch (IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
					}
					
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				return t;
			}
		};
	}
}
