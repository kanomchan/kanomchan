package org.kanomchan.core.common.processhandler;

import java.util.List;

import org.kanomchan.core.common.bean.Message;
import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.core.common.constant.CommonConstant;

public class ServiceResult<T extends Object> {

	
	private T result;
	private String status;
	private List<Message> messages;
	private PagingBean pagingBean;
	
	public ServiceResult(T t) {
		result = t;
	}
	public ServiceResult(T t,PagingBean pagingBean) {
		this.pagingBean = pagingBean;
		result = t;
	}
	
	public ServiceResult() {
	}
	
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public boolean isSuccess() {
		return CommonConstant.SERVICE_STATUS_SUCCESS.equals(status);
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public Message getMessage(){
		return (messages!=null &&messages.size()>=1)?messages.get(0):null;
	}
	
	void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
}
