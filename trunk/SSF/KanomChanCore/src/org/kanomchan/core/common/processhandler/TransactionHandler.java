package org.kanomchan.core.common.processhandler;

import org.aspectj.lang.ProceedingJoinPoint;

public interface TransactionHandler {

	boolean isTxnProcess(ProceedingJoinPoint proceedingJoinPoint);

	void beginTxn(ProcessContext processContext);

	void commitTxn(ProcessContext processContext);

	void rollbackTxn(ProcessContext processContext);

	<T> T unProxy(T returnValue, boolean fristProcess);

}
