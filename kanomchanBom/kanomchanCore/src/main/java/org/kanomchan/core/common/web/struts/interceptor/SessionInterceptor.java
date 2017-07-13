package org.kanomchan.core.common.web.struts.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletConfigInterceptor;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.security.authorize.Authorize;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

public class SessionInterceptor extends ServletConfigInterceptor  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7298251278758717925L;

	private List<String> bypass = new ArrayList<String>();
	
	public void setBypass(List<String> bypass) {
		this.bypass = bypass;
	}
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
//		if(bypass==null){
//			bypass = new ArrayList<String>();
//			bypass.add("login-");
//		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
//		 
		if(session.getAttribute(CommonConstant.SESSION.USER_BEAN_KEY)==null){
			String queryString = request.getQueryString();
			String url = request.getRequestURL().toString()+((queryString==null||"null".equals(queryString)||"".equals(queryString))?"":"?"+queryString);
			boolean f = true;
			for (String iterable_element :  bypass) {
				f = url.indexOf(iterable_element) == -1;
				if(!f)
					break;
			}
			if(f)
				session.setAttribute(CommonConstant.SESSION.NEXT_URL_KEY, url);
			return "FORCE_TO_LOGIN_PAGE";
		}else{
//			 UserBean userBean = (UserBean)
//			 session.get(CommonConstant.SESSION.USER_BEAN_KEY);
//			 Set<String> privileges = userBean.getPrivileges();
//			 String methodStr = invocation.getProxy().getMethod();
//			 Set<String> codes = getcode(invocation.getAction().getClass(),
//			 methodStr);
//			 if(!privileges.containsAll(codes)){
//				 return "FORCE_TO_LOGGEDIN_WELCOME_PAGE";
//			 }
			
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
