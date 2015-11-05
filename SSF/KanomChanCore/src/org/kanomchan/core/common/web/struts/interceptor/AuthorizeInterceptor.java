package org.kanomchan.core.common.web.struts.interceptor;

import org.apache.log4j.Logger;

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
import org.kanomchan.core.common.bean.Button;
import org.kanomchan.core.common.bean.Message;
import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.constant.CommonMessageCode;
import org.kanomchan.core.common.service.ActionService;
import org.kanomchan.core.common.service.ConfigService;
import org.kanomchan.core.common.service.MessageService;
import org.kanomchan.core.common.web.struts.action.BaseAction;
import org.kanomchan.core.security.authorize.Authorize;
import org.kanomchan.core.security.authorize.dao.UserAuthorizeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorizeInterceptor extends ServletConfigInterceptor {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AuthorizeInterceptor.class);
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3298276170229094546L;
	private MessageService messageService;
	@Autowired
	@Required
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	private ActionService actionService;
	@Autowired
	@Required
	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}
	
	private UserAuthorizeDao userAuthorizeDao;
	@Autowired
	@Required
	public void setUserAuthorizeDao(UserAuthorizeDao userAuthorizeDao) {
		this.userAuthorizeDao = userAuthorizeDao;
	}
	@Autowired
	private ConfigService configService;
	
	protected static final String MESSAGE = "message";
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserBean userBean = (UserBean) session.get(CommonConstant.SESSION.USER_BEAN_KEY);
		Set<String> privileges;
		if(userBean != null){
			privileges = userBean.getPrivileges();
		}else{
			String idGuest = "7";
			try{
				idGuest = configService.get("GUEST_ID");
			}catch(Exception e){
				
			}
			privileges = userAuthorizeDao.getUserPrivilegesByRoleId(idGuest);
		}
		
		
		String methodStr = invocation.getProxy().getMethod();
		String actionName = invocation.getProxy().getActionName();
		if(methodStr==null||"".equals(methodStr)){
			methodStr = actionName.substring(actionName.indexOf("-"));
		}
		Set<String> codes = getcode(invocation.getAction().getClass(), methodStr);
		String namespace = invocation.getProxy().getNamespace();
		Set<String> c = actionService.getAuthorizeCodeByAction(namespace, actionName);
		if(c != null && !c.isEmpty()){
			codes.addAll(c);
			if (!privileges.containsAll(codes)) {
				logger.error("privileges:"+privileges.toString()+"codes:"+codes.toString());
				return authFail(invocation,userBean);
			}
		}else{
			logger.error("C is empty ");
			return authFail(invocation,userBean);
		}
		return invocation.invoke();
	}
	
	private String authFail(ActionInvocation invocation,UserBean userBean){
		if(userBean != null){
			if(invocation.getAction() instanceof BaseAction){
				BaseAction baseAction = (BaseAction) invocation.getAction();
				Message message = messageService.getMessage(CommonMessageCode.ATZ2001,null);
				List<Message> messageList = new ArrayList<Message>();
				List<Button> buttonList = new ArrayList<Button>();
				messageList.add(message);
				baseAction.setMessageList(messageList);
				Button e = new Button();
				e.setAction("home-end");
				e.setNamespace("/home");
				e.setName("OK");
				buttonList.add(e);
				baseAction.setButtonList(buttonList);
				return MESSAGE;
			}else{
				return "FORCE_TO_LOGIN_WELCOME_PAGE";
			}
		}else{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			String queryString = request.getQueryString();
			String url = request.getRequestURL().toString()+((queryString==null||"null".equals(queryString)||"".equals(queryString))?"":"?"+queryString);
			session.setAttribute(CommonConstant.SESSION.NEXT_URL_KEY, url);
			if(invocation.getAction() instanceof BaseAction){
				BaseAction baseAction = (BaseAction) invocation.getAction();
				Message message = messageService.getMessage(CommonMessageCode.ATZ2001,null);
				List<Message> messageList = new ArrayList<Message>();
				List<Button> buttonList = new ArrayList<Button>();
				messageList.add(message);
				baseAction.setMessageList(messageList);
				Button e = new Button();
				e.setAction("login-end");
				e.setNamespace("/login");
				e.setName("OK");
				buttonList.add(e);
				baseAction.setButtonList(buttonList);
				return MESSAGE;
			}else{
				return "FORCE_TO_LOGIN_PAGE";
			}
		}
	}
	
	private Set<String> getcode(Class<?> class1 , String methodStr) throws NoSuchMethodException, SecurityException{
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
