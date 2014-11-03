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
import org.kanomchan.core.common.util.HibernateUtil;
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
//				logger.debug("TransactionStatus.isCompleted "+txnStatus.isCompleted());
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
			System.out.println("commitTxn");
			platformTransactionManager.commit(txnStatus);
			
		}
		
		
	}
	
	@Override
	public  <T> T unProxy(T returnValue, boolean isTxnProcess){
		if(returnValue!=null &&returnValue instanceof ServiceResult){
			
			if(isTxnProcess){
				ServiceResult serviceResult = (ServiceResult) returnValue;
				serviceResult.setResult(HibernateUtil.clearUnproxy(serviceResult.getResult()));
			}
			
		}else{
			
			if(isTxnProcess){
				returnValue = HibernateUtil.clearUnproxy(returnValue);
			}
		}
		return returnValue;
	}
	
	
	

	@Override
	public void rollbackTxn(ProcessContext processContext){
		TransactionStatus txnStatus = processContext.txnStatus;
		if( txnStatus != null ){
			processContext.txnStatus = (null);
			System.out.println("rollback");
			platformTransactionManager.rollback(txnStatus);
		}
		
	}

}
