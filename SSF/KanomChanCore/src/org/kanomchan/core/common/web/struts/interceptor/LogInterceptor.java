package org.kanomchan.core.common.web.struts.interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletConfigInterceptor;

import com.opensymphony.xwork2.ActionInvocation;

public class LogInterceptor extends ServletConfigInterceptor {
	
	
	private static final Logger logger = Logger.getLogger(LogInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getProxy().getActionName();
		String namespace = invocation.getProxy().getNamespace();
		String method = invocation.getProxy().getMethod();
		String action = namespace+"/"+actionName;
		logger.info("[Action  Start]\tCall Action:" +action);
		long start = System.currentTimeMillis();
		try{
			
		}catch(Exception e){
			long end = System.currentTimeMillis();
			logger.error("[Action  Error]\tCall Action:" +action+ "\tTIME:\t" + (end - start),e);
			throw e;
		}
		String s = invocation.invoke();
		long end = System.currentTimeMillis();
		logger.info("[Action  End  ]\tCall Action:" +action+ "\tTIME:\t" + (end - start));
		return s;
	}
}
