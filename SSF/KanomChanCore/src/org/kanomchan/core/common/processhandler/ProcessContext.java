package org.kanomchan.core.common.processhandler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.kanomchan.core.common.bean.Message;
import org.kanomchan.core.common.bean.MessageDefault;
import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.bean.UserBeanDefault;
import org.kanomchan.core.common.constant.MessageCode;
import org.springframework.transaction.TransactionStatus;

public class ProcessContext {
	
	protected String transId;

	protected TransactionStatus txnStatus;

	protected UserBean userBean;
	
	protected List<Message> messageList;
	
	protected String lang;
	
	protected boolean startProcess = false;
	protected String sessionId;

	protected String status;

	protected Locale locale;
	
	public UserBean getUserBean() {
		if(userBean==null){
//			userMenuService = ApplicationContextUtil.getBean("userMenuService");
			userBean= new UserBeanDefault();
			userBean.setRole("GUEST");
			HashSet<String> pri = new HashSet<String>();
			pri.add("COM000000");
			userBean.setPrivileges(pri);
//			ServiceResult<List<MenuBean>> serviceResultMenu = userMenuService.generateMenuList(serviceResult.getResult());
		}
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public TransactionStatus getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(TransactionStatus txnStatus) {
		this.txnStatus = txnStatus;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void addMessage(MessageCode messageCode){
		if(messageList == null){
			messageList = new LinkedList<Message>();
		}
		MessageDefault message  = new MessageDefault();
		message.setMessageCode(messageCode);
		messageList.add(message );
	}
	
	public void addMessage(String messageCode){
		if(messageList == null){
			messageList = new LinkedList<Message>();
		}
		MessageDefault message  = new MessageDefault();
		message.setMessageCode(messageCode);
		messageList.add(message );
	}
	List<Message> getMessageList() {
		return messageList;
	}
	public void setLocale(Locale locale) {
		this.locale =locale;
	}
	public Locale getLocale() {
		return locale;
	}
	
	
	public String getSessionId() {
		return sessionId;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getUserName(){
		if(userBean==null||userBean.getUserName()==null||"".equals(userBean.getUserName()))
			return "GUEST";
		return userBean.getUserName();
	}
}
