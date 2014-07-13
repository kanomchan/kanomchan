package org.kanomchan.core.common.processhandler;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.kanomchan.core.common.context.CurrentThread;
import org.springframework.transaction.annotation.Transactional;

@Aspect
public class ProcessHandler {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProcessHandler.class);
	
	
	public Object doAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		logger.info("[StartService]\tcall:" +proceedingJoinPoint.getSignature().toShortString() );
		
		ProcessContext processContext = CurrentThread.getProcessContext();
		MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
		Method targetInterfaceMethod = methodSignature.getMethod();
		boolean fristProcess = false;
		boolean isTxnProcess = false;
		if(processContext!=null&&!processContext.startProcess&&ServiceResult.class.equals(targetInterfaceMethod.getReturnType())){
			fristProcess = true;
			processContext.startProcess = true;
			
		}
		if(processContext!=null&&processContext.txnStatus==null){
			isTxnProcess = isTxnProcess(proceedingJoinPoint);
		}
		Object returnValue = null;
		long start = System.currentTimeMillis();
		try{
			beforeProcess(processContext, isTxnProcess);
			
			returnValue = proceedingJoinPoint.proceed();
			
			afterProcess( returnValue, processContext, isTxnProcess ,fristProcess );
		} catch (Throwable e) {
			
		}
		
		long end = System.currentTimeMillis();
		logger.info("[EndService  ]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + "\tTIME:\t" + (end - start));
		return returnValue;
		
	}

	
	private void afterProcess(Object returnValue,ProcessContext processContext, boolean isTxnProcess,boolean fristProcess) {
		// TODO Auto-generated method stub
		
	}


	private void beforeProcess(ProcessContext processContext,boolean isTxnProcess) {
		// TODO Auto-generated method stub
		
	}


	private boolean isTxnProcess(ProceedingJoinPoint joinPoint){
		
		
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
	
}
