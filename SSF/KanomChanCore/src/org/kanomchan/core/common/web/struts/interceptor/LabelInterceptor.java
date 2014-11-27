package org.kanomchan.core.common.web.struts.interceptor;

import java.util.Map;

import org.kanomchan.core.common.service.LabelService;
import org.kanomchan.core.common.web.struts.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LabelInterceptor extends AbstractInterceptor {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2374188586405898080L;
	private LabelService labelService;
	@Autowired
	@Required
	public void setLabelService(LabelService labelService) {
		this.labelService = labelService;
	}
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Object action  = invocation.getAction();
		if(action instanceof BaseAction){
			BaseAction baseAction = (BaseAction) action;
			ActionContext context = invocation.getInvocationContext();
//			Map<String, Object> sessions = context.getSession();
			Map<String, String> label = labelService.getLabel(context.getLocale().getISO3Language().toUpperCase());
			baseAction.setLabel(label);
		}
		String s = invocation.invoke();
		
		return s;
	}

}