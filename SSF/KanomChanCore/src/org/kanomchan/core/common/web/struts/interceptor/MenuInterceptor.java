package org.kanomchan.core.common.web.struts.interceptor;

import java.util.List;
import java.util.Map;

import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.security.authorize.bean.MenuBean;
import org.kanomchan.core.security.authorize.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MenuInterceptor extends AbstractInterceptor {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1340998841043618009L;
	private UserMenuService userMenuService;
	@Autowired
	@Required
	public void setUserMenuService(UserMenuService userMenuService) {
		this.userMenuService = userMenuService;
	}
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
        
		
		String s = invocation.invoke();
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		try{
			if(session.get(CommonConstant.SESSION.MENU_BEAN_KEY) == null){
				ServiceResult<List<MenuBean>> serviceResultMenu = userMenuService.generateMenuGuest();
				if(serviceResultMenu.isSuccess()){
					session.put(CommonConstant.SESSION.MENU_BEAN_KEY, serviceResultMenu.getResult());
				}
			}
			
		}catch(Exception e){
			
		}
		
		return s;
	}
}
