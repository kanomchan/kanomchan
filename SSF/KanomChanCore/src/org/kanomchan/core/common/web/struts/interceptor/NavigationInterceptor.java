package org.kanomchan.core.common.web.struts.interceptor;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ParameterAware;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.common.util.StrutsUtil;
import org.kanomchan.core.common.web.struts.AjaxOut;
import org.kanomchan.core.security.authorize.bean.MenuBean;
import org.kanomchan.core.security.authorize.io.NavigationBean;
import org.kanomchan.core.security.authorize.service.UserNavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class NavigationInterceptor extends AbstractInterceptor implements ParameterAware  {

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
				String paramName = "";
				
				if(actionName.indexOf("-")!=-1){
					String method = actionName.substring(actionName.indexOf("-")+1);
					if(invocation.getAction().getClass().getMethod(method).isAnnotationPresent(AjaxOut.class)){
						return invocation.invoke();
					}
				}
				
				HttpServletRequest request = ServletActionContext.getRequest();
//				String queryString = request.getQueryString();
				String queryString = "";
				Map<String, String[]> parameters = request.getParameterMap();
				for(Map.Entry<String,  String[]> entry : parameters.entrySet()){
					String[] obje = entry.getValue();
					if((entry.getKey().equals("tableName") || entry.getKey().equals("idAddressType")) && obje!=null && !"".equals(obje))
					paramName = entry.getKey() + "=" + obje[0];
				}
				queryString += paramName;
 				String url =request.getRequestURI().substring(request.getContextPath().length())+((queryString==null||"null".equals(queryString)||"".equals(queryString))?"":"?"+queryString).replaceAll("[?]request_locale=[A-Z]{3}&", "?").replaceAll("[&?]request_locale=[A-Z]{3}", "");
				ServiceResult<NavigationBean> serviceResultNavigation = userNavigationService.generateNavigationList(namespace, actionName,url);
				if(serviceResultNavigation.isSuccess()){
					if(serviceResultNavigation.getResult().getNavigationMenuList()!=null)
					session.put(CommonConstant.SESSION.NAVIGATION_BEAN_KEY, serviceResultNavigation.getResult().getNavigationMenuList());
					if(serviceResultNavigation.getResult().getCurrentAction()!=null){
						session.put(CommonConstant.SESSION.ACT_CUR_KEY, serviceResultNavigation.getResult().getCurrentAction());
					}
				}else session.put(CommonConstant.SESSION.NAVIGATION_BEAN_KEY, null);
		}catch(Exception e){
			
		}
		return invocation.invoke();
	}



	@Override
	public void setParameters(Map<String, String[]> arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
