package org.kanomchan.projectname.authen.action.web;

import org.kanomchan.core.common.web.struts.action.BaseAction;

public class LogoutAction extends BaseAction {


	@Override
	public String init() throws Exception {
		return logout();
	}
	
	public String logout() throws Exception{
		httpServletRequest.getSession().invalidate();
		
		return  "FORCE_TO_LOGIN_PAGE";
	}

	

}
