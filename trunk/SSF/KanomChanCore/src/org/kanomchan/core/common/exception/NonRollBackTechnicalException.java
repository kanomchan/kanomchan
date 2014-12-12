package org.kanomchan.core.common.exception;

import org.kanomchan.core.common.constant.MessageCode;

public class NonRollBackTechnicalException extends NonRollBackException implements TechnicalException {

	
	public NonRollBackTechnicalException(MessageCode messageCode) {
		super(messageCode);
	}

	public NonRollBackTechnicalException(MessageCode messageCode, Throwable throwable) {
		super(messageCode, throwable, null);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -7422945695911223572L;

}
