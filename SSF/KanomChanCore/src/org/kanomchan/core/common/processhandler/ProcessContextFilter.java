package org.kanomchan.core.common.processhandler;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;
import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.context.CurrentThread;

public class ProcessContextFilter  implements Filter  {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		ProcessContext processContext = CurrentThread.getProcessContext();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		Gson gson = new Gson();
//		System.out.println(gson.toJson(request.getParameterMap()));
		if(processContext == null){
			processContext = new ProcessContext();
			HttpSession httpSession = httpServletRequest.getSession(true);
			UserBean userBean = (UserBean) httpSession.getAttribute(CommonConstant.SESSION.USER_BEAN_KEY);
//			Locale locale = (Locale) httpSession.getAttribute(CommonConstant.SESSION.LOCALE_KEY);
			processContext.userBean = (userBean);
//			serviceContext.setLocale(locale);
			CurrentThread.setProcessContext(processContext);
		}
		MDC.put(CommonConstant.LOG.CONTEXT_PATH, ((HttpServletRequest) request).getContextPath());
		MDC.put(CommonConstant.LOG.SESSION_ID, ((HttpServletRequest) request).getSession().getId());
		MDC.put(CommonConstant.LOG.USER_ID, processContext.userBean==null?"guest":processContext.userBean.getUserId()==null?"":processContext.userBean.getUserId());
		chain.doFilter(request,response);
		CurrentThread.remove();
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
