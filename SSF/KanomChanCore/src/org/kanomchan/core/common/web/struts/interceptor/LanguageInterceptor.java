package org.kanomchan.core.common.web.struts.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.context.ApplicationContextUtil;
import org.kanomchan.core.common.context.CurrentThread;
import org.kanomchan.core.common.processhandler.ProcessContext;
import org.kanomchan.core.common.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LanguageInterceptor  extends AbstractInterceptor{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6649212718361712229L;
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
	//  HttpServletRequest request = ServletActionContext.getRequest();
	//  String langKey = request.getParameter(CommonConstant.PARAMETER.LANG_KEY);
	//  if(langKey==null||langKey.equals("")){
	//   langKey = (String) sessions.get(CommonConstant.SESSION.LANG_KEY)+"";
	//   if(langKey==null||langKey.equals("")||langKey.equalsIgnoreCase("null")){
//	    langKey = configService.get(CommonConstant.DEFAULT_LANG_CONFIG);
//	    langKey = langKey==null||"".equals(langKey)?CommonConstant.DEFAULT_LANG_KEY:langKey;
	//   }
	//  }
	  Locale nativeLocale = ActionContext.getContext().getLocale();
	  processContext.setLang(nativeLocale.getISO3Language());
	  sessions.put(CommonConstant.SESSION.LANG_KEY,nativeLocale.getISO3Language());
	  String s = invocation.invoke();
	   
	  return s;
	 }
}
