//package org.kanomchan.core.common.proxy;
//
//import java.beans.IntrospectionException;
//import java.io.Serializable;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//
//import javax.persistence.Column;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//
//import ognl.Ognl;
//import ognl.OgnlException;
//
//import org.kanomchan.core.common.util.ClassUtil;
//import org.springframework.jdbc.core.RowMapper;
//
//public class ProxyEntity<T extends Object> implements Serializable,RowMapper<T>{
//
//	private Class<T> entityClass;
//	private List<Field> fields;
//	private List<Method> methods;
//	private Map<String, ProxyEntity<Object>> mapList;
//	private T proxy;
////	private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();
//
//	
//	public ProxyEntity(Class<T> entityClass) {
//		this.entityClass = entityClass;
////		businessInterface.getDeclaredFields()
//		try {
//			proxy = entityClass.newInstance();
//		} catch (InstantiationException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//	
//	}
//	
//	public void setParamter(Map<String, String[]> para){
//		
//		for (String paraName : para.keySet()) {
//			String[] value = para.get(paraName);
//			try{
//				Ognl.setValue(paraName, proxy, value);
//			}catch(Exception e){
//				try {
//					Ognl.setValue(paraName, proxy, value[0]);
//				} catch (OgnlException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//			
//		}
//		
//	}
//	
//	private String sql;
//	public String getSql() {
//		if(sql!=null)
//			return sql;
//		else{
//			StringBuilder sb = new StringBuilder();
//			sb.append("select * from ");
//			
//			String tableName = entityClass.getSimpleName();
//			Table table = entityClass.getAnnotation(Table.class);
//			if(table!=null){
//				tableName = table.name();
//			}
//			sb.append(tableName);
//			
//			sql = sb.toString();
//			return sql;
//		}
//			
//			
//	}
//	
//	public T mapRow(ResultSet rs, int arg1) throws SQLException {
//		T obj = null;
//		try {
//			obj = entityClass.newInstance();
//		} catch (InstantiationException | IllegalAccessException e1) {
//			e1.printStackTrace();
//		}
//		for (Field field : entityClass.getFields()) {
//			if(field.isAnnotationPresent(Transient.class))
//				continue;
//			String colName = field.getName();
//			
//			try {
//				Method method = ClassUtil.findGetter(entityClass, colName);
//				if(method.isAnnotationPresent(Transient.class))
//					continue;
//				Column c = method.getAnnotation(Column.class);
//				if(c!=null)
//					colName = c.name();
//					method.invoke(obj, rs.getObject(colName, field.getType()));
//				
//			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException |NoSuchFieldException  | IntrospectionException e) {
//				e.printStackTrace();
//			}
//		}
//		return obj;
//	}
//
//	@Override
//	public String toString() {
//		return "ProxyEntity [proxy=" + proxy + ", getSql()=" + getSql() + "]";
//	}
//	
//	
//	
////	public get
//}
