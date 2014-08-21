package org.kanomchan.core.common.exception;

import javax.mail.MessagingException;

import org.kanomchan.core.common.constant.MessageCode;

public class RollBackProcessException extends RollBackException implements ProcessException {

	public RollBackProcessException(MessageCode messageCode) {
		super(messageCode);
	}

	public RollBackProcessException(MessageCode messageCode, Throwable throwable) {
		super(messageCode, throwable, null);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2719889245356622566L;

}
