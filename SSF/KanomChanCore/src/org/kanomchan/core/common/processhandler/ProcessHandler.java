package org.kanomchan.core.common.processhandler;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;

import javax.json.Json;
import javax.persistence.JoinTable;

import org.aopalliance.intercept.MethodInvocation;
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
import org.kanomchan.core.common.exception.RollBackProcessException;
import org.kanomchan.core.common.exception.RollBackTechnicalException;
import org.kanomchan.core.common.exception.TechnicalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Joiner;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Aspect
public class ProcessHandler {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProcessHandler.class);
	private static final Logger logger2 = Logger.getLogger("org.kanomchan.core.common.processhandler.ProcessHandler.error.view.level");
	
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
//	private final Gson gson = new GsonBuilder().create();
//	private final Gson gson2 = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
//		@Override
//		public boolean shouldSkipField(FieldAttributes fa) {
//			String text = "[\"username\",\"password\",\"email\"]";
//			Type typeOfSrc = new TypeToken<Set<String>>() {}.getType();
//			Set<String> setKey = gson.fromJson(text, typeOfSrc );
//			return setKey!=null && setKey.contains(fa.getName()) ? false: true;
////			return true;
//		}
//		@Override
//		public boolean shouldSkipClass(Class<?> arg0) {
//			return true;
//		}
//	}  ).create();
	
	public Object doAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		logger.info("[Service Start]\tcall:" +proceedingJoinPoint.getSignature().toShortString() );
		
		ProcessContext processContext = CurrentThread.getProcessContext();
		MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
		Method targetInterfaceMethod = methodSignature.getMethod();
		
//		if(logger2.isDebugEnabled()){
//			Method method = methodSignature.getMethod();
//			String paraType = Joiner.on(", ").join(method.getParameterTypes());
//			String paraValue= Joiner.on(", ").skipNulls().join(proceedingJoinPoint.getArgs());
//			logger2.debug("[Service debug] method Name: "+method.getDeclaringClass().getSimpleName()+"." + method.getName() + " Parameter Type:[" + Joiner.on(", ").join(method.getParameterTypes()).getClass().getSimpleName() + "] Parameter Value:[" + gson.toJson(proceedingJoinPoint.getArgs())+"]");
//			logger2.debug("[Service debug] method Name: "+method.getDeclaringClass().getSimpleName()+"." + method.getName() + " Parameter :["+paraType+"] Value ["+paraValue+"]");
//		}
		
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
				if(logger2.isDebugEnabled())
					logger2.debug("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+te.getMessageCode()+"messageText :"+te.getMessage(), te.getThrowable());
				else
					logger2.error("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+te.getMessageCode()+"messageText :"+te.getMessage());
				
			}else if(e instanceof ProcessException){
				ProcessException se =  (ProcessException) e;
				if(logger2.isDebugEnabled())
					logger2.debug("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+se.getMessageCode()+"messageText :"+se.getMessage(), se.getThrowable());
				else
					logger2.error("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+se.getMessageCode()+"messageText :"+se.getMessage());
				
			}else{
				logger2.error("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " : "+e.getMessage(), e);
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


	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object afterProcess(Object returnValue,ProcessContext processContext, boolean isTxnProcess,boolean fristProcess) {
		if(isTxnProcess){
			transactionHandler.commitTxn(processContext);
//			transactionHandler.unProxy(returnValue, isTxnProcess);
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
