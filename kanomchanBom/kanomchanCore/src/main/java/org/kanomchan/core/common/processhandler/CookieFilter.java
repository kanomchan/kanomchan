package org.kanomchan.core.common.processhandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.context.ApplicationContextUtil;
import org.kanomchan.core.common.context.CurrentThread;
import org.kanomchan.core.common.cookie.bean.CookieBean;
import org.kanomchan.core.common.cookie.bean.CookieOrm;
import org.kanomchan.core.common.cookie.service.CookieService;
import org.kanomchan.core.common.io.LoginIO;

public class CookieFilter  implements Filter  {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CookieFilter.class);
	
	public static final String KEY_MID = "MID";
	public static final String KEY_UID = "UID";
	public static final String KEY_KID = "KID";
	public static final String KEY_TID = "TID";
	
	public static final int LIFE = 5184000;


	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		SessionMap<String, Object> session = new SessionMap<String, Object>(httpServletRequest);
		if(session.get(CommonConstant.SESSION.USER_BEAN_KEY)==null &&httpServletRequest.getCookies()!=null){
			List<Cookie> cookiesOld = Arrays.asList(httpServletRequest.getCookies());
			CookieOrm cookieOrm = new CookieOrm();
			for (Cookie cookie : cookiesOld) {
				if(KEY_MID.equals(cookie.getName())){
					cookieOrm.setMachineId(cookie.getValue());
				}else if(KEY_UID.equals(cookie.getName())){
					cookieOrm.setUserId(cookie.getValue());
				}else if(KEY_KID.equals(cookie.getName())){
					cookieOrm.setKeyId(cookie.getValue());
				}else if(KEY_TID.equals(cookie.getName())){
					cookieOrm.setTokenId(cookie.getValue());
				}
			}
			try{

				CookieService cookieService = ApplicationContextUtil.getBean(CookieService.class);
				ServiceResult<CookieBean> serviceResult = cookieService.checkCookie(cookieOrm);
				if(serviceResult.isSuccess()){
					CookieBean cookieBean = serviceResult.getResult();
					cookieOrm = cookieBean.getCookieOrm();
					List<Cookie> cookies = cookieBean.getCookies();
					if(cookies!=null){
						for (Cookie cookie : cookies) {
							if(cookie!=null){
								if(KEY_MID.equals(cookie.getName())){
									if(cookie.getPath()==null)
										cookie.setPath(getPathCookie(httpServletRequest));
									cookie.setHttpOnly(true);
									httpServletResponse.addCookie(cookie);
								}else if(KEY_TID.equals(cookie.getName())){
									if(cookie.getPath()==null)
										cookie.setPath(getPathCookie(httpServletRequest));
									cookie.setHttpOnly(true);
									httpServletResponse.addCookie(cookie);
								}
								
							}
						}
					}
					LoginIO loginIO = cookieBean.getLoginIO();
					if(loginIO!=null){
						
						session.put(CommonConstant.SESSION.USER_BEAN_KEY, loginIO.getUserBean());
						session.put(CommonConstant.SESSION.MENU_BEAN_KEY, loginIO.getMenuVO().getMenuBeans());
						session.put(CommonConstant.SESSION.MENU_BEAN_MAP_KEY, loginIO.getMenuVO().getLookupMap());
						for (Cookie cookie : loginIO.getCookies()) {
							if(cookie.getPath()==null)
								cookie.setPath(getPathCookie(httpServletRequest));
							cookie.setHttpOnly(true);
							httpServletResponse.addCookie(cookie);
						}
						if(session.get(CommonConstant.SESSION.NEXT_URL_KEY)!=null){
							String nextUrl = (String) session.get(CommonConstant.SESSION.NEXT_URL_KEY);
							session.remove(CommonConstant.SESSION.NEXT_URL_KEY);
							httpServletResponse.sendRedirect(nextUrl);
						}
					}
					
				}
			}catch(Exception e){
			}finally{
				ProcessContext processContext = CurrentThread.getProcessContext();
				processContext.clearStage();
			}
		}
		chain.doFilter(request,response);
	}



	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public static String getPathCookie(HttpServletRequest httpServletRequest){
//		return httpServletRequest.getContextPath();
		return "/";
	}
	

	@Override
	public void destroy() {
		
	}
		
}
