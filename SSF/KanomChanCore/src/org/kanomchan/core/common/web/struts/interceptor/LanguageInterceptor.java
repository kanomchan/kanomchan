package org.kanomchan.core.common.web.struts.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletConfigInterceptor;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.context.CurrentThread;
import org.kanomchan.core.common.processhandler.ProcessContext;
import org.kanomchan.core.common.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

public class LanguageInterceptor  extends ServletConfigInterceptor{
	
	private ConfigService configService;
	@Autowired
	@Required
	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}
	
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();
		ProcessContext processContext = CurrentThread.getProcessContext();
		Map<String, Object> sessions = context.getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		String langKey = request.getParameter(CommonConstant.PARAMETER.LANG_KEY);
		if(langKey==null||langKey.equals("")){
			langKey = (String) sessions.get(CommonConstant.SESSION.LANG_KEY)+"";
			if(langKey==null||langKey.equals("")||langKey.equalsIgnoreCase("null")){
				langKey = configService.get(CommonConstant.DEFAULT_LANG_CONFIG);
				langKey = langKey==null||"".equals(langKey)?CommonConstant.DEFAULT_LANG_KEY:langKey;
			}
		}
		processContext.setLang(langKey);
		sessions.put(CommonConstant.SESSION.LANG_KEY,langKey);
		String s = invocation.invoke();
			
		return s;
	}
}