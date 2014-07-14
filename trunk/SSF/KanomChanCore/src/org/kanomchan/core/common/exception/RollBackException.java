package org.kanomchan.core.common.exception;

import java.util.List;

import org.kanomchan.core.common.constant.MessageCode;

public abstract class RollBackException extends Exception implements BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6834608324076085601L;
	private MessageCode messageCode;
	private List<String> para;
	private Throwable throwable;
	
	public RollBackException() {
		this(null);
	}
	
	public RollBackException(MessageCode  messageCode) {
		this(messageCode,null);
	}
	
	public RollBackException(MessageCode  messageCode,List<String> para) {
		this(messageCode, null, para);
	}
	public RollBackException(MessageCode  messageCode,Throwable throwable,List<String> para) {
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
