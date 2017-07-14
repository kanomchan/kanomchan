package org.kanomchan.core.common.exception;

import java.util.List;

import org.kanomchan.core.common.constant.MessageCode;


public class RollBackTechnicalException extends RollBackException implements TechnicalException {



	public RollBackTechnicalException() {
		super();
	}
	
	public RollBackTechnicalException(MessageCode  messageCode) {
		super(messageCode, null,(String) null);
	}
	public RollBackTechnicalException(MessageCode  messageCode,String message) {
		super(messageCode, null, message);
	}
	
	public RollBackTechnicalException(MessageCode  messageCode,List<String> para) {
		super(messageCode, para,(String) null);
	}
	
	public RollBackTechnicalException(MessageCode  messageCode ,List<String> para,String message){
		super(messageCode, para,message);		
	}
	
	public RollBackTechnicalException(MessageCode  messageCode,List<String> para,Throwable throwable) {
		super(messageCode, para, throwable, null);
	}
	public RollBackTechnicalException(MessageCode  messageCode, Throwable throwable) {
		super(messageCode, null, throwable, null);
	}
	public RollBackTechnicalException(MessageCode  messageCode ,List<String> para,Throwable throwable,String message) {
		super(messageCode,para,throwable,message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4848197984055240410L;

}
