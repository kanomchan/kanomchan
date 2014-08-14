package org.kanomchan.core.common.web.struts.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.ServletConfigInterceptor;
import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.security.authorize.Authorize;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

public class SessionInterceptor extends ServletConfigInterceptor  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7298251278758717925L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
//		Map<String, Object> session = ActionContext.getContext().getSession();
//		 
//		if(session.get(CommonConstant.SESSION.USER_BEAN_KEY)==null){
//			return "FORCE_TO_LOGIN_PAGE";
//		}else{
//			UserBean userBean = (UserBean) session.get(CommonConstant.SESSION.USER_BEAN_KEY);
//			Set<String> privileges = userBean.getPrivileges();
//			String methodStr = invocation.getProxy().getMethod();
//			Set<String> codes = getcode(invocation.getAction().getClass(), methodStr);
//			if(!privileges.containsAll(codes)){
//				return "FORCE_TO_LOGGEDIN_WELCOME_PAGE";
//			}
//			
//		}
		
		
			
		return super.intercept(invocation);
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
