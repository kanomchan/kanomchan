package org.kanomchan.core.common.context;

import org.kanomchan.core.common.processhandler.ProcessContext;

public class CurrentThread {
	private static final ThreadLocal<ProcessContext> contextThreadLocal = new ThreadLocal<ProcessContext>();
	
	
	public static ProcessContext getProcessContext(){
		return contextThreadLocal.get();
	}
	
	public static void setProcessContext(ProcessContext processContext){
		contextThreadLocal.set(processContext);
	}
	
	
	public static void remove(){
		contextThreadLocal.remove();
	}
}
