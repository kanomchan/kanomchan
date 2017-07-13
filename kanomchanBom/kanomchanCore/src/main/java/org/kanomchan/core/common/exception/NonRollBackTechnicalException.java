package org.kanomchan.core.common.exception;

import java.util.List;

import org.kanomchan.core.common.constant.MessageCode;

public class NonRollBackTechnicalException extends NonRollBackException implements TechnicalException {

	
	public NonRollBackTechnicalException() {
		super();
	}
	
	public NonRollBackTechnicalException(MessageCode  messageCode) {
		super(messageCode, null,(String) null);
	}
	public NonRollBackTechnicalException(MessageCode  messageCode,String message) {
		super(messageCode, null, message);
	}
	
	public NonRollBackTechnicalException(MessageCode  messageCode,List<String> para) {
		super(messageCode, para,(String) null);
	}
	
	public NonRollBackTechnicalException(MessageCode  messageCode ,List<String> para,String message){
		super(messageCode, para,message);		
	}
	
	public NonRollBackTechnicalException(MessageCode  messageCode,List<String> para,Throwable throwable) {
		super(messageCode, para, throwable, null);
	}
	public NonRollBackTechnicalException(MessageCode  messageCode, Throwable throwable) {
		super(messageCode, null, throwable, null);
	}
	public NonRollBackTechnicalException(MessageCode  messageCode ,List<String> para,Throwable throwable,String message) {
		super(messageCode,para,throwable,message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7422945695911223572L;

}
