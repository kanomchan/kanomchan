package org.kanomchan.core.common.exception;

import org.kanomchan.core.common.constant.MessageCode;

public class NonRollBackProcessException extends NonRollBackException implements ProcessException {

	public NonRollBackProcessException(MessageCode messageCode) {
		super(messageCode);
	}

	public NonRollBackProcessException(MessageCode messageCode, Throwable throwable) {
		super(messageCode, throwable, null);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1823439133074727668L;

}
