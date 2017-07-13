package org.kanomchan.core.common.processhandler;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
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
	private int timeout;
	
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
			logger.error("isTxnProcess(ProceedingJoinPoint)", e);
		} 
		catch (NoSuchMethodException e) {
			logger.error("isTxnProcess(ProceedingJoinPoint)", e);
		}
		
		//if targetClass and targetMethod have no @Transactional -> return false	
		return false;
	}

	@Override
	public void beginTxn(ProcessContext processContext) {
		try {
			if( processContext.txnStatus == null ){
				if (logger.isDebugEnabled()) {
					logger.debug("beginTxn(ProcessContext) - beginTxn");
				}
				DefaultTransactionDefinition txnDefinition = new DefaultTransactionDefinition(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
				if(timeout>0){
					txnDefinition.setTimeout(timeout);
				}
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
			if (logger.isDebugEnabled()) {
				logger.debug("commitTxn(ProcessContext) - commitTxn");
			}
			platformTransactionManager.commit(txnStatus);
			
		}
		
		
	}
	
	@Override
	public  <T> T unProxy(T returnValue, boolean isTxnProcess){
//		if(returnValue!=null &&returnValue instanceof ServiceResult){
//			
//			if(isTxnProcess){
//				ServiceResult serviceResult = (ServiceResult) returnValue;
//				serviceResult.setResult(HibernateUtil.clearUnproxy(serviceResult.getResult()));
//			}
//			
//		}else{
//			
//			if(isTxnProcess){
//				returnValue = HibernateUtil.clearUnproxy(returnValue);
//			}
//		}
		return returnValue;
	}
	
	
	

	@Override
	public void rollbackTxn(ProcessContext processContext){
		TransactionStatus txnStatus = processContext.txnStatus;
		if( txnStatus != null ){
			processContext.txnStatus = (null);
			if (logger.isDebugEnabled()) {
				logger.debug("rollbackTxn(ProcessContext) - rollback");
			}
			platformTransactionManager.rollback(txnStatus);
		}
		
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	

}
