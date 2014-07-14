package org.kanomchan.core.common.exception;

import java.util.List;

import org.kanomchan.core.common.constant.MessageCode;

public abstract class NonRollBackException extends Exception implements BaseException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1266763797662867327L;
	private MessageCode messageCode;
	private List<String> para;
	private Throwable throwable;
	
	public NonRollBackException() {
		this(null);
	}
	
	public NonRollBackException(MessageCode  messageCode) {
		this(messageCode,null);
	}
	
	public NonRollBackException(MessageCode  messageCode,List<String> para) {
		this(messageCode, null, para);
	}
	public NonRollBackException(MessageCode  messageCode,Throwable throwable,List<String> para) {
		this.messageCode = messageCode;
		this.para = para;
		this.throwable = throwable;
	}
	@Override
	public MessageCode getMessageCode() {
		return messageCode;
	}

	@Override
	public List<String> getPara() {
		return para;
	}

	@Override
	public Throwable getThrowable() {
		return throwable;
	}
}
