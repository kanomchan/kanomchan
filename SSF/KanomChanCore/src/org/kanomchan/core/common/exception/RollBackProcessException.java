package org.kanomchan.core.common.exception;

import java.util.List;

import org.kanomchan.core.common.constant.MessageCode;

public class RollBackProcessException extends RollBackException implements ProcessException {

	public RollBackProcessException() {
		super();
	}
	
	public RollBackProcessException(MessageCode  messageCode) {
		super(messageCode, null,(String) null);
	}
	public RollBackProcessException(MessageCode  messageCode,String message) {
		super(messageCode, null, message);
	}
	
	public RollBackProcessException(MessageCode  messageCode,List<String> para) {
		super(messageCode, para,(String) null);
	}
	
	public RollBackProcessException(MessageCode  messageCode ,List<String> para,String message){
		super(messageCode, para,message);		
	}
	
	public RollBackProcessException(MessageCode  messageCode,List<String> para,Throwable throwable) {
		super(messageCode, para, throwable, null);
	}
	public RollBackProcessException(MessageCode  messageCode, Throwable throwable) {
		super(messageCode, null, throwable, null);
	}
	public RollBackProcessException(MessageCode  messageCode ,List<String> para,Throwable throwable,String message) {
		super(messageCode,para,throwable,message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2719889245356622566L;

}
