package org.kanomchan.core.common.exception;

import org.kanomchan.core.common.constant.MessageCode;

public class RollBackProcessException extends RollBackException implements ProcessException {

	public RollBackProcessException(MessageCode messageCode) {
		super(messageCode);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2719889245356622566L;

}
