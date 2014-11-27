package org.kanomchan.core.common.web.struts.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.service.ActionService;
import org.kanomchan.core.security.authorize.Authorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorizeInterceptor extends AbstractInterceptor {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3298276170229094546L;
	private ActionService actionService;
	@Autowired
	@Required
	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserBean userBean = (UserBean) session.get(CommonConstant.SESSION.USER_BEAN_KEY);
		Set<String> privileges = userBean.getPrivileges();
		String methodStr = invocation.getProxy().getMethod();
		String actionName = invocation.getProxy().getActionName();
		if(methodStr==null||"".equals(methodStr)){
			methodStr = actionName.substring(actionName.indexOf("-"));
		}
		Set<String> codes = getcode(invocation.getAction().getClass(), methodStr);
		String namespace = invocation.getProxy().getNamespace();
		Set<String> c = actionService.getAuthorizeCodeByAction(namespace, actionName);
		codes.addAll(c);
		if (!privileges.containsAll(codes)) {
			return "FORCE_TO_LOGGEDIN_WELCOME_PAGE";
		}
		return invocation.invoke();
	}
	
	Set<String> getcode(Class<?> class1 , String methodStr) throws NoSuchMethodException, SecurityException{
		Set<String> codes = new HashSet<String>();
		Authorize authorize;
		
		//getcode in class
		if(class1.isAnnotationPresent(Authorize.class)){
			authorize =   class1.getAnnotation(Authorize.class);
			codes.addAll(Arrays.asList(authorize.code()));
		}
		
		
		//getcode in method
		Method method = class1.getMethod(methodStr);
		if(method.isAnnotationPresent(Authorize.class)){
			authorize = method.getAnnotation(Authorize.class);
			codes.addAll(Arrays.asList(authorize.code()));
		}
		
		
		return codes;
	}
}
