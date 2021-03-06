package org.kanomchan.core.common.processhandler;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kanomchan.core.common.bean.LocationBean;
import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.context.ApplicationContextUtil;
import org.kanomchan.core.common.context.CurrentThread;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.service.LocationService;


public class ProcessContextFilter  implements Filter  {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProcessContextFilter.class);


	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		ProcessContext processContext = CurrentThread.getProcessContext();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession httpSession = httpServletRequest.getSession(true);
		MDC.put(CommonConstant.LOG.CONTEXT_PATH, (String)((HttpServletRequest) request).getContextPath());
		MDC.put(CommonConstant.LOG.SERVER_NAME,(String) request.getServerName());
		MDC.put(CommonConstant.LOG.SERVER_PORT, request.getServerPort());
		MDC.put(CommonConstant.LOG.SERVER_INSTANCE_SERVER_NAME, InetAddress.getLocalHost().getHostName());
		MDC.put(CommonConstant.LOG.SERVER_INSTANCE_NAME, System.getProperty("com.sun.aas.instanceName"));
		MDC.put(CommonConstant.LOG.SERVER_INSTANCE_IP, InetAddress.getLocalHost().getHostAddress());
		MDC.put(CommonConstant.LOG.SESSION_ID, ((HttpServletRequest) request).getSession().getId());
		MDC.put(CommonConstant.LOG.USER_ID, httpSession.getAttribute(CommonConstant.SESSION.USER_ID)==null?"guest"+getRealIp(request):httpSession.getAttribute(CommonConstant.SESSION.USER_ID));
		MDC.put(CommonConstant.LOG.USER_NAME, httpSession.getAttribute(CommonConstant.SESSION.USER_NAME)==null?"guest"+getRealIp(request):httpSession.getAttribute(CommonConstant.SESSION.USER_NAME));
		
		if (logger.isDebugEnabled()) {
			logger.debug("[RequestURI Start]\t" + httpServletRequest.getRequestURI());
		}
		if(processContext == null){

			processContext = new ProcessContext();
			
			UserBean userBean = (UserBean) httpSession.getAttribute(CommonConstant.SESSION.USER_BEAN_KEY);
			processContext.userBean = (userBean);
			
			String corpId = String.valueOf(httpSession.getAttribute("SESSION_CORP_ID_KEY"));
			processContext.setString("SESSION_CORP_ID_KEY", corpId);
			String appId = String.valueOf(httpSession.getAttribute("SESSION_APP_ID_KEY"));
			processContext.setString("SESSION_APP_ID_KEY", appId);
			String shopId = String.valueOf(httpSession.getAttribute("SESSION_SHOP_ID_KEY"));
			processContext.setString("SESSION_SHOP_ID_KEY", shopId);
			String nativeLang = httpServletRequest.getParameter("native_lang");
//			if(nativeLang != null){
//				Object languageDao = ApplicationContextUtil.getBean("languageDao");
//				
//				Object nativeLocale=null;
//				try {
//					Method method = Class.forName("com.jobsmatcher.jm.usermanament.dao.LanguageDao").getMethod("getLocaleByCode3", String.class);
//					nativeLocale = method.invoke(languageDao, nativeLang);
//				} catch (NoSuchMethodException | SecurityException| ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//					logger.error("doFilter(ServletRequest, ServletResponse, FilterChain)", e); //$NON-NLS-1$
//					nativeLocale = Locale.ENGLISH;
//				}
//				
//				httpSession.setAttribute("SESSION_NATIVE_LANG_KEY", nativeLocale);
//				processContext.setNativeLocale((Locale) nativeLocale);
//			}else{
//				Object nativeLocale = (Locale)httpSession.getAttribute("SESSION_NATIVE_LANG_KEY");
//				if(nativeLocale == null)
//					nativeLocale = Locale.ENGLISH;
//				processContext.setNativeLocale((Locale) nativeLocale);
//			}
			CurrentThread.setProcessContext(processContext);
			
			String userId = processContext.userBean==null?"guest"+getRealIp(request):processContext.userBean.getUserId()==null?"guest"+getRealIp(request):processContext.userBean.getUserId();
			String userName = processContext.userBean==null?"guest"+getRealIp(request):processContext.userBean.getUserName()==null?"guest"+getRealIp(request):processContext.userBean.getUserName();
			httpSession.setAttribute(CommonConstant.SESSION.USER_ID, userId);
			httpSession.setAttribute(CommonConstant.SESSION.USER_NAME, userName);
			MDC.put(CommonConstant.LOG.USER_ID, userId);
			MDC.put(CommonConstant.LOG.USER_NAME, userName);
			
		}

		getSession(httpSession,request);
		chain.doFilter(request,response);
		if (logger.isDebugEnabled()) {
			logger.debug("[RequestURI End  ]\t" + httpServletRequest.getRequestURI());
		}
		CurrentThread.remove();
	}

	private void getSession(HttpSession httpSession,ServletRequest request){
		ProcessContext processContext = CurrentThread.getProcessContext();
		if(httpSession.getAttribute(CommonConstant.SESSION.LOCATION_BEAN_KEY)!=null){
			LocationBean lb = (LocationBean) httpSession.getAttribute(CommonConstant.SESSION.LOCATION_BEAN_KEY);
			processContext.setLocationBean(lb);
		}else if(httpSession.getAttribute(CommonConstant.SESSION.USER_BEAN_KEY)!=null){
			Long region =(Long) httpSession.getAttribute("SESSION_REGION_ID_KEY");
			Long country =(Long) httpSession.getAttribute("SESSION_COUNTRY_ID_KEY");
			Long county =(Long) httpSession.getAttribute("SESSION_COUNTY_ID_KEY");
			Long station =(Long) httpSession.getAttribute("SESSION_STATION_ID_KEY");
			Long postal =(Long) httpSession.getAttribute("SESSION_POSTAL_KEY");
			String lang =(String) httpSession.getAttribute("SESSION_LANG_CODE_KEY");
			Long city =(Long) httpSession.getAttribute("SESSION_CITY_ID_KEY");
			Long currency =(Long) httpSession.getAttribute("SESSION_CURRENCY_ID_KEY");
			Long zone =(Long) httpSession.getAttribute("SESSION_ZONE_ID_KEY");
			Long province =(Long) httpSession.getAttribute("SESSION_PROVINCE_ID_KEY");
			processContext.setLocation(lang, zone, country, region, province, city, county, postal, station, currency);
		}
		else{
			getPOS(request);
		}
		
	}
	
	private void getPOS(ServletRequest request){
		
		String ipAddress = getRealIp(request);
//		if (ipAddress == null) {
//			ipAddress = request.getRemoteAddr();
//		}
		ProcessContext processContext = CurrentThread.getProcessContext();
			
				try {
					LocationService locationService = (LocationService) ApplicationContextUtil.getBean("locationService");
					ServiceResult<LocationBean> serviceResult = locationService.getLocation(ipAddress);
					if(serviceResult.isSuccess()){
						processContext.setLocationBean(serviceResult.getResult());
						((HttpServletRequest) request).getSession(true).setAttribute(CommonConstant.SESSION.LOCATION_BEAN_KEY,serviceResult.getResult());
					}else{
						processContext.setLocation("THA",0L,0L,0L,0L,0L,0L,0L,0L,0L);
					}
				} catch (RollBackException | NonRollBackException  e) {
					logger.error("[getPOS:",e);
					processContext.setLocation("THA",0L,0L,0L,0L,0L,0L,0L,0L,0L);
				} catch (Exception e) {
					logger.error("[getPOS2:",e);
					processContext.setLocation("THA",0L,0L,0L,0L,0L,0L,0L,0L,0L);
				}
	}



	@Override
	public void destroy() {
//		ProcessContext processContext = CurrentThread.getProcessContext();
//		System.out.println("session destroy by user Id :"+processContext==null?null:processContext.getUserId());
		
	}


	private String getRealIp(ServletRequest request){
		String ipAddress = null;
		ipAddress = ((HttpServletRequest) request).getHeader("X-FORWARDED-FOR");
		if (ipAddress == null)
			ipAddress = ((HttpServletRequest) request).getHeader("HTTP_X_FORWARDED_FOR");
		if (ipAddress == null)
			ipAddress = request.getRemoteAddr();
		
		return ipAddress;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
}
