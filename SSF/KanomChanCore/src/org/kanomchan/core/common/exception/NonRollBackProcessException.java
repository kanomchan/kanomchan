package org.kanomchan.core.common.exception;

import java.util.List;

import org.kanomchan.core.common.constant.MessageCode;

public class NonRollBackProcessException extends NonRollBackException implements ProcessException {

	public NonRollBackProcessException() {
		super();
	}
	
	public NonRollBackProcessException(MessageCode  messageCode) {
		super(messageCode, null,(String) null);
	}
	public NonRollBackProcessException(MessageCode  messageCode,String message) {
		super(messageCode, null, message);
	}
	
	public NonRollBackProcessException(MessageCode  messageCode,List<String> para) {
		super(messageCode, para,(String) null);
	}
	
	public NonRollBackProcessException(MessageCode  messageCode ,List<String> para,String message){
		super(messageCode, para,message);		
	}
	
	public NonRollBackProcessException(MessageCode  messageCode,List<String> para,Throwable throwable) {
		super(messageCode, para, throwable, null);
	}
	public NonRollBackProcessException(MessageCode  messageCode, Throwable throwable) {
		super(messageCode, null, throwable, null);
	}
	public NonRollBackProcessException(MessageCode  messageCode ,List<String> para,Throwable throwable,String message) {
		super(messageCode,para,throwable,message);
	}
	public NonRollBackProcessException(MessageCode messageCode, String message, Throwable throwable) {
		super(messageCode,null,throwable,message);
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1823439133074727668L;

}
