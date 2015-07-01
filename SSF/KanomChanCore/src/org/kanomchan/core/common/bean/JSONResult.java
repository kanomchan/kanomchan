package org.kanomchan.core.common.bean;


import java.io.Serializable;
import java.util.List;

import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.processhandler.ServiceResult;

public class JSONResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3287524195892843408L;
	private T result;
	private String status;
	private List<Message> messages;
	private PagingBean pagingBean;
	
	public Integer getiTotalDisplayRecords() {
		return pagingBean ==null ?null:pagingBean.getRowsPerPage();
	}
	public Long getiTotalRecords() {
		return pagingBean ==null ?null:pagingBean.getTotalRows();
	}
	private JSONResult() {
	}
	
	public JSONResult(String s) {
	}
	
	public JSONResult(ServiceResult serviceResult ) {
		messages = serviceResult.getMessages();
		status = serviceResult.getStatus();
		result = (T) serviceResult.getResult();
		pagingBean = serviceResult.getPagingBean();
	}
	
	public JSONResult(T r,PagingBean pagingBean ) {
		result = (T) r;
		this.pagingBean = pagingBean;
	}
	
	public T getResult() {
		return result;
	}


	public void setResult(T result) {
		this.result = result;
	}
	
//	public T getAaData(){
//		return result;
//	}
//	
	public boolean isSuccess() {
		return CommonConstant.SERVICE_STATUS_SUCCESS.equals(status);
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public Message getMessage(){
		return (messages!=null &&messages.size()>=1)?messages.get(0):null;
	}

	public PagingBean getPagingBean() {
		return pagingBean;
	}

	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
	
	
}