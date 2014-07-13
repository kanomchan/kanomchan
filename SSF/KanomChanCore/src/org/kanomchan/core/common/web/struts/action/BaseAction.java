package org.kanomchan.core.common.web.struts.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.BeanNameAware;

import com.opensymphony.xwork2.ActionSupport;


public abstract class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,PrincipalAware,BeanNameAware {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2290712190657083231L;
	protected HttpServletRequest httpServletRequest;
	protected HttpServletResponse httpServletResponse;
	
	protected String beanName;
	
	protected PrincipalProxy principalProxy;
	
	public abstract String init()throws Exception;
	
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		httpServletRequest =arg0;
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
}
