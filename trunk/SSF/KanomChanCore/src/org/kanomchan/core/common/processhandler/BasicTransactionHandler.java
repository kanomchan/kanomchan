package org.kanomchan.core.common.processhandler;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.kanomchan.core.common.util.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class BasicTransactionHandler  implements TransactionHandler{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BasicTransactionHandler.class);
	private PlatformTransactionManager platformTransactionManager;
	@Autowired
	@Required
	public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}
	@Override
	public boolean isTxnProcess(ProceedingJoinPoint joinPoint){
		
		
		//== STEP 1 : Check is Class declare @Transactional 
		Class<?> targetInterface = joinPoint.getSignature().getDeclaringType();
		if( targetInterface.isAnnotationPresent(Transactional.class) ){
			return true;
		}
		
		Class<? extends Object> targetImplClass = joinPoint.getTarget().getClass();
		if( targetImplClass.isAnnotationPresent(Transactional.class) ){
			return true;
		}

		//== STEP 2 : Check is Method declare @Transactional
		MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		Method targetInterfaceMethod = methodSignature.getMethod();
				
		if( targetInterfaceMethod != null && targetInterfaceMethod.isAnnotationPresent(Transactional.class) ){
			return true;
		}

		try {
			Method targetImplMethod = targetImplClass.getMethod(targetInterfaceMethod.getName(), targetInterfaceMethod.getParameterTypes());
			if( targetImplMethod != null && targetImplMethod.isAnnotationPresent(Transactional.class) ){
				return true;
			}
		} 
		catch (SecurityException e) {
			e.printStackTrace();
		} 
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		//if targetClass and targetMethod have no @Transactional -> return false	
		return false;
	}

	@Override
	public void beginTxn(ProcessContext processContext) {
		try {
			if( processContext.txnStatus == null ){
				System.out.println("beginTxn");
				TransactionDefinition txnDefinition = new DefaultTransactionDefinition(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
				TransactionStatus txnStatus = platformTransactionManager.getTransaction(txnDefinition);	
				logger.debug("TransactionStatus.isCompleted "+txnStatus.isCompleted());
				processContext.txnStatus = txnStatus;
			}
		} 
		catch( Exception e ) {
			logger.error("beginTxn(ServiceContext)", e); //$NON-NLS-1$
		}
		
	}
	@Override
	public void commitTxn(ProcessContext processContext){
		TransactionStatus txnStatus = processContext.txnStatus;
		if( txnStatus != null ){
			processContext.txnStatus = (null);
//			System.out.println("commitTxn");
			platformTransactionManager.commit(txnStatus);
			
		}
		
		
	}
	
	@Override
	public  <T> T unProxy(T returnValue, boolean isTxnProcess){
		if(returnValue!=null &&returnValue instanceof ServiceResult){
			
			if(isTxnProcess){
				ServiceResult serviceResult = (ServiceResult) returnValue;
				serviceResult.setResult(clearUnproxy(serviceResult.getResult()));
			}
			
		}else{
			
			if(isTxnProcess){
				returnValue = clearUnproxy(returnValue);
			}
		}
		return returnValue;
	}
	
	private <T> T clearUnproxy(T entity)  {
	    if (entity == null) {
	    	return entity;
	    }
	    if(entity instanceof List){
	    	Collection out = new LinkedList();
	    	Collection collection = (Collection) entity;
	    	if(collection.size()==0)
	    		return null;
	    	for (Object object : collection) {
	    		out.add(clearUnproxy(object));
			}
	    	return (T) out;
	    }
	    
	    if(entity instanceof Set){
	    	Set out = new LinkedHashSet();
	    	Set collection = (Set) entity;
	    	if(collection.size()==0)
	    		return null;
	    	for (Object object : collection) {
	    		out.add(clearUnproxy(object));
			}
	    	return (T) out;
	    }
	    
	    if(entity instanceof Map){
	    	Map out = new HashMap();
	    	Map collection = (Map) entity;
	    	if(collection.size()==0)
	    		return null;
	    	
	    	for (Object	key : collection.keySet()) {
	    		out.put(key, clearUnproxy(collection.get(key)));
			}
	    	return (T) out;
	    }
	    if (entity instanceof HibernateProxy) {
	    	HibernateProxy hibernateProxy =((HibernateProxy) entity);
	    	
	    	LazyInitializer lazyInitializer = hibernateProxy.getHibernateLazyInitializer();
	    	if(lazyInitializer.isUninitialized()){
	    		T out = null;
	    		try {
	    			Class cls = Class.forName(lazyInitializer.getEntityName());
					out = (T) cls.newInstance();
					Field[] fs = cls.getDeclaredFields();
					for (Field field : fs) {
						if(field.isAnnotationPresent(Id.class)){
							Method methodSet = ClassUtil.findSetter(cls, field.getName());
							methodSet.invoke(out, lazyInitializer.getIdentifier());
							break;
						}else{
							Method methodSet = ClassUtil.findSetter(cls, field.getName());
							if(methodSet.isAnnotationPresent(Id.class)){
								methodSet.invoke(out, lazyInitializer.getIdentifier());
								break;
							}else{
								Method methodGet = ClassUtil.findGetter(cls, field.getName());
								if(methodGet.isAnnotationPresent(Id.class)){
									methodSet.invoke(out, lazyInitializer.getIdentifier());
									break;
								}
							}
							
						}
					}
				} catch (ClassNotFoundException | InstantiationException | NoSuchFieldException | IntrospectionException | SecurityException |IllegalAccessException | IllegalArgumentException | InvocationTargetException  e) {
//					e.printStackTrace();
				}
	    		return out;
	    	}else{
	    		entity = (T) lazyInitializer.getImplementation();
	    	}
	    }
	    
	    Class<? extends Object> class1   = entity.getClass();
		Field[] fields = entity.getClass().getDeclaredFields();
		if(fields!=null){
			for (Field field : fields) {
				if(java.lang.reflect.Modifier.isStatic(field.getModifiers()))
					continue;
				try {
					if(!field.isAccessible()){
						Method methodGet = ClassUtil.findGetter(class1, field.getName());
						Method methodSet = ClassUtil.findSetter(class1, field.getName());
						
						Object o = methodGet.invoke(entity);
						if(o instanceof HibernateProxy ){
							Object objClear = clearUnproxy(o);
							methodSet.invoke(entity, objClear);
						}else if (o instanceof PersistentCollection) {
							PersistentCollection persistentCollection = (PersistentCollection) o;
//							persistentCollection.
							if(!persistentCollection.wasInitialized()){
								methodSet.invoke(entity,new Object[]{ null });
							}else{
								methodSet.invoke(entity,clearUnproxy(persistentCollection.getValue()));
								
							}
//							Object objClear = clearUnproxy(o);
							;
						}
					}
				} catch (NoSuchFieldException | IntrospectionException | SecurityException |IllegalAccessException | IllegalArgumentException | InvocationTargetException  e) {
//					e.printStackTrace();
				}
				
				
			}
		}
	    
	    return entity;
//		return entity;
	}
	@Override
	public void rollbackTxn(ProcessContext processContext){
		TransactionStatus txnStatus = processContext.txnStatus;
		if( txnStatus != null ){
			processContext.txnStatus = (null);
//			System.out.println("rollback");
			platformTransactionManager.rollback(txnStatus);
		}
		
	}

}
