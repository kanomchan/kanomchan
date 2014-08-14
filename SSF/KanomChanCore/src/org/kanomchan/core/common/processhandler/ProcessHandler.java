package org.kanomchan.core.common.processhandler;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.constant.CommonMessageCode;
import org.kanomchan.core.common.context.CurrentThread;
import org.kanomchan.core.common.exception.BaseException;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.ProcessException;
import org.kanomchan.core.common.exception.RollBackTechnicalException;
import org.kanomchan.core.common.exception.TechnicalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

@Aspect
public class ProcessHandler {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProcessHandler.class);
	
	private TransactionHandler transactionHandler;
	@Autowired
	@Required
	public void setTransactionHandler(TransactionHandler transactionHandler) {
		this.transactionHandler = transactionHandler;
	}
	private MessageHandler messageHandler;
	@Autowired
	@Required
	public void setMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}
	
	public Object doAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		logger.info("[Service Start]\tcall:" +proceedingJoinPoint.getSignature().toShortString() );
		
		ProcessContext processContext = CurrentThread.getProcessContext();
		MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
		Method targetInterfaceMethod = methodSignature.getMethod();
		boolean fristProcess = false;
		boolean isTxnProcess = false;
		if(processContext!=null&&!processContext.startProcess&&ServiceResult.class.equals(targetInterfaceMethod.getReturnType())){
			fristProcess = true;
			processContext.startProcess = true;
			
		}
		;
		if(processContext!=null&&processContext.txnStatus==null){
			isTxnProcess = transactionHandler.isTxnProcess(proceedingJoinPoint);
		}
		Object returnValue = null;
		long start = System.currentTimeMillis();
		try{
			beforeProcess(processContext, isTxnProcess);
			
			returnValue = proceedingJoinPoint.proceed();
			
			afterProcess( returnValue, processContext, isTxnProcess ,fristProcess );
		} catch (Throwable e) {
			if(e instanceof TechnicalException){
				TechnicalException te = (TechnicalException) e;
				if(logger.isDebugEnabled())
					logger.debug("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+te.getMessageCode(), te.getThrowable());
				else
					logger.error("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+te.getMessageCode());
				
			}else if(e instanceof ProcessException){
				ProcessException se =  (ProcessException) e;
				if(logger.isDebugEnabled())
					logger.debug("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+se.getMessageCode(), se.getThrowable());
				else
					logger.error("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+se.getMessageCode());
				
			}else{
				logger.error("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " :", e);
			}
			processContext = onException(e, processContext, isTxnProcess);
			if (fristProcess&&ServiceResult.class.equals(targetInterfaceMethod.getReturnType())) {

				ServiceResult<Object> serviceResult = new ServiceResult<Object>();

				serviceResult.setStatus(CommonConstant.SERVICE_STATUS_FAIL);
				processContext.status=CommonConstant.SERVICE_STATUS_FAIL;
				if(e instanceof BaseException)
					returnValue = messageHandler.addMessage(serviceResult, (BaseException)e);
				else
					returnValue = messageHandler.addMessage(serviceResult,new RollBackTechnicalException(CommonMessageCode.COM4999, e));
			} else {
				throw e;
			}
		}
		if(fristProcess){
			processContext.startProcess = false;
		}
		long end = System.currentTimeMillis();
		logger.info("[Service End  ]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + "\tTIME:\t" + (end - start));
		return returnValue;
		
	}

	
	private ProcessContext onException(Throwable e,ProcessContext processContext, boolean isTxnProcess) {
		if(isTxnProcess){
			if(e instanceof NonRollBackException){
				transactionHandler.commitTxn(processContext);
//			}else if(e instanceof ){
//				rollbackTxn(serviceContext);
			}else{
				transactionHandler.rollbackTxn(processContext);
			}
			processContext.txnStatus = (null);
		}
		return processContext;
	}


	private Object afterProcess(Object returnValue,ProcessContext processContext, boolean isTxnProcess,boolean fristProcess) {
		if(isTxnProcess){
			transactionHandler.commitTxn(processContext);
			transactionHandler.unProxy(returnValue, fristProcess);
		}
			
		if(returnValue!=null &&returnValue instanceof ServiceResult ){
			
			returnValue = messageHandler.addMessage((ServiceResult) returnValue);
			
		}
		return returnValue;
	}


	private void beforeProcess(ProcessContext processContext,boolean isTxnProcess) {
		if( isTxnProcess ){
			transactionHandler.beginTxn(processContext);
		}
	}
	
}
