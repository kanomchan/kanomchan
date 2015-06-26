//package org.kanomchan.core.common.util;
//
//import java.beans.IntrospectionException;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.LinkedHashSet;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.persistence.Id;
//
//import org.hibernate.collection.PersistentCollection;
//import org.hibernate.proxy.HibernateProxy;
//import org.hibernate.proxy.LazyInitializer;
//
//public class HibernateUtil {
//	
//	public static <T> T clearUnproxy(T entity)  {
//	    if (entity == null) {
//	    	return entity;
//	    }
//	    if(entity instanceof List){
//	    	Collection out = new LinkedList();
//	    	Collection collection = (Collection) entity;
//	    	if(collection.size()==0)
//	    		return null;
//	    	for (Object object : collection) {
//	    		out.add(clearUnproxy(object));
//			}
//	    	return (T) out;
//	    }
//	    
//	    if(entity instanceof Set){
//	    	Set out = new LinkedHashSet();
//	    	Set collection = (Set) entity;
//	    	if(collection.size()==0)
//	    		return null;
//	    	for (Object object : collection) {
//	    		out.add(clearUnproxy(object));
//			}
//	    	return (T) out;
//	    }
//	    
//	    if(entity instanceof Map){
//	    	Map out = new HashMap();
//	    	Map collection = (Map) entity;
//	    	if(collection.size()==0)
//	    		return null;
//	    	
//	    	for (Object	key : collection.keySet()) {
//	    		out.put(key, clearUnproxy(collection.get(key)));
//			}
//	    	return (T) out;
//	    }
//	    if (entity instanceof HibernateProxy) {
//	    	HibernateProxy hibernateProxy =((HibernateProxy) entity);
//	    	
//	    	LazyInitializer lazyInitializer = hibernateProxy.getHibernateLazyInitializer();
//	    	if(lazyInitializer.isUninitialized()){
//	    		T out = null;
//	    		try {
//	    			Class cls = Class.forName(lazyInitializer.getEntityName());
//					out = (T) cls.newInstance();
//					Field[] fs = cls.getDeclaredFields();
//					for (Field field : fs) {
//						if(field.isAnnotationPresent(Id.class)){
//							Method methodSet = ClassUtil.findSetter(cls, field.getName());
//							methodSet.invoke(out, lazyInitializer.getIdentifier());
//							break;
//						}else{
//							Method methodSet = ClassUtil.findSetter(cls, field.getName());
//							if(methodSet.isAnnotationPresent(Id.class)){
//								methodSet.invoke(out, lazyInitializer.getIdentifier());
//								break;
//							}else{
//								Method methodGet = ClassUtil.findGetter(cls, field.getName());
//								if(methodGet.isAnnotationPresent(Id.class)){
//									methodSet.invoke(out, lazyInitializer.getIdentifier());
//									break;
//								}
//							}
//							
//						}
//					}
//				} catch (ClassNotFoundException | InstantiationException | NoSuchFieldException | IntrospectionException | SecurityException |IllegalAccessException | IllegalArgumentException | InvocationTargetException  e) {
////					e.printStackTrace();
//				}
//	    		return out;
//	    	}else{
//	    		entity = (T) lazyInitializer.getImplementation();
//	    	}
//	    }
//	    
//	    Class<? extends Object> class1   = entity.getClass();
//		Field[] fields = entity.getClass().getDeclaredFields();
//		if(fields!=null){
//			for (Field field : fields) {
//				if(java.lang.reflect.Modifier.isStatic(field.getModifiers()))
//					continue;
//				try {
//					if(!field.isAccessible()){
//						Method methodGet = ClassUtil.findGetter(class1, field.getName());
//						Method methodSet = ClassUtil.findSetter(class1, field.getName());
//						
//						Object o = methodGet.invoke(entity);
//						if(o instanceof HibernateProxy ){
//							Object objClear = clearUnproxy(o);
//							methodSet.invoke(entity, objClear);
//						}else if (o instanceof PersistentCollection) {
//							PersistentCollection persistentCollection = (PersistentCollection) o;
////							persistentCollection.
//							if(!persistentCollection.wasInitialized()){
//								methodSet.invoke(entity,new Object[]{ null });
//							}else{
//								methodSet.invoke(entity,clearUnproxy(persistentCollection.getValue()));
//								
//							}
////							Object objClear = clearUnproxy(o);
//							;
//						}
//					}
//				} catch (NoSuchFieldException | IntrospectionException | SecurityException |IllegalAccessException | IllegalArgumentException | InvocationTargetException  e) {
////					e.printStackTrace();
//				}
//				
//				
//			}
//		}
//	    
//	    return entity;
////		return entity;
//	}
//
//}
