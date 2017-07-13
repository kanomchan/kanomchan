package org.kanomchan.core.common.processhandler;

import org.kanomchan.core.common.exception.BaseException;

public interface MessageHandler {

	<T extends Object> ServiceResult<T> addMessage(ServiceResult<T> returnValue);

	<T extends Object> ServiceResult<T> addMessage(ServiceResult<T> returnValue,BaseException baseException);

}
