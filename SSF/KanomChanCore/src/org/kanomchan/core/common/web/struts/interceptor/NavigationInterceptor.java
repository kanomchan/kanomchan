package org.kanomchan.core.common.web.struts.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.security.authorize.bean.MenuBean;
import org.kanomchan.core.security.authorize.service.UserNavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class NavigationInterceptor extends AbstractInterceptor  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3069606784491620284L;

	private UserNavigationService userNavigationService;
	@Autowired
	@Required
	public void setUserNavigationService(
			UserNavigationService userNavigationService) {
		this.userNavigationService = userNavigationService;
	}
	List<MenuBean> NavigationList;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		try{
				String namespace = invocation.getProxy().getNamespace();
				String actionName = invocation.getProxy().getActionName();
				HttpServletRequest request = ServletActionContext.getRequest();
				String url = request.getRequestURI().substring(request.getContextPath().length())+((request.getQueryString()==null||"null".equals(request.getQueryString())||"".equals(request.getQueryString()))?"":"?"+request.getQueryString());
				
				ServiceResult<List<MenuBean>> serviceResultNavigation = userNavigationService.generateNavigationList(namespace, actionName,url);
				if(serviceResultNavigation.isSuccess()){
					session.put(CommonConstant.SESSION.NAVIGATION_BEAN_KEY, serviceResultNavigation.getResult());
				}else session.put(CommonConstant.SESSION.NAVIGATION_BEAN_KEY, null);
		}catch(Exception e){
			
		}
		
		return invocation.invoke();
	}

}
