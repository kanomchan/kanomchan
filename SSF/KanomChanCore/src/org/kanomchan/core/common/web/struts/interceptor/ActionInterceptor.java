package org.kanomchan.core.common.web.struts.interceptor;

import org.kanomchan.core.common.bean.ActionBean;
import org.kanomchan.core.common.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ActionInterceptor extends AbstractInterceptor  {
	
	private ActionService actionService;
	@Autowired
	@Required
	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5319046008112997640L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String namespace = invocation.getProxy().getNamespace();
		String methodStr = invocation.getProxy().getMethod();
		String actionName = invocation.getProxy().getActionName();
		if(methodStr!=null&&!"".equals(methodStr)){
			if(actionName.indexOf(methodStr)==-1){
				actionName = actionName+"-"+methodStr;
			}
			
		}
//		ActionBean actionBean = actionService.findAction(namespace,actionName);
		
		return invocation.invoke();
	}

}
