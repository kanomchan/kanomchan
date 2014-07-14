package org.kanomchan.core.common.exception;

import org.kanomchan.core.common.constant.MessageCode;


public class RollBackTechnicalException extends RollBackException implements TechnicalException {



	public RollBackTechnicalException(MessageCode messageCode, Throwable throwable) {
		super(messageCode, throwable, null);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4848197984055240410L;

}
