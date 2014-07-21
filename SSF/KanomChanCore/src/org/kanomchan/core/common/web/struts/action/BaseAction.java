package org.kanomchan.core.common.web.struts.action;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.kanomchan.core.common.bean.Button;
import org.kanomchan.core.common.bean.Message;
import org.springframework.beans.factory.BeanNameAware;

import com.opensymphony.xwork2.ActionSupport;


public abstract class BaseAction extends ActionSupport implements RequestAware,SessionAware,ServletRequestAware,ServletResponseAware,PrincipalAware,BeanNameAware {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2290712190657083231L;
	
	protected static final String MESSAGE = "message";
	protected HttpServletRequest httpServletRequest;
	protected HttpServletResponse httpServletResponse;
	protected Map<String, Object> session;
	protected Map<String, Object> request;
	protected List<Message> messageList;
	protected List<Button> buttonList;
	protected Map<String, String> label;
	
	
	protected String beanName;
	
	protected PrincipalProxy principalProxy;
	
	public abstract String init()throws Exception;
	
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		httpServletRequest =arg0;
	}
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		httpServletResponse = arg0;
	}
	
	@Override
	public void setBeanName(String arg0) {
		beanName = arg0;
	}

	public String getActionName(){
		return beanName.replaceAll("Action", "");
	}
	
	
	@Override
	public void setPrincipalProxy(PrincipalProxy arg0) {
		principalProxy = arg0;
	}
	
	
	public void clearSessionValue(){
		for (Enumeration<String> e = httpServletRequest.getSession().getAttributeNames();e.hasMoreElements();) {
			String key = e.nextElement();
			if(key.indexOf("class")>=0){
				httpServletRequest.getSession().removeAttribute(key);
			}
		}
	}
	
	public String start()throws Exception{
		return ActionSupport.SUCCESS;
	}
	
	
	public String end() throws Exception{
		clearSessionValue();
		return ActionSupport.SUCCESS;
	}
	
	public List<Message> getMessageList() {
		return messageList;
	}
	
	public Message getMessage(){
		return (messageList==null||messageList.size()<=0?null:messageList.get(messageList.size()-1));
	}
	
	public List<Button> getButtonList() {
		return buttonList;
	}
	public void setButtonList(List<Button> buttonList) {
		this.buttonList = buttonList;
	}

	public void setLeftButton(Button nextButton) {
		LinkedList<Button> buttonList=null;
		if(this.buttonList == null){
			buttonList = new LinkedList<Button>();
		}else{
			buttonList = new LinkedList<Button>(this.buttonList);
		}
		buttonList.addFirst(nextButton);
		this.buttonList = buttonList;
	}

	public void setRightButton(Button backButton) {
		LinkedList<Button> buttonList=null;
		if(this.buttonList == null){
			buttonList = new LinkedList<Button>();
		}else{
			buttonList = new LinkedList<Button>(this.buttonList);
		}
		buttonList.addLast(backButton);
		this.buttonList = buttonList;
	}


	public Map<String, String> getLabel() {
		return label;
	}


	public void setLabel(Map<String, String> label) {
		this.label = label;
	}
	
	
	
}