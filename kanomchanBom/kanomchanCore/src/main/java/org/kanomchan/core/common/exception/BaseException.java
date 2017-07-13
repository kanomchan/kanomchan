package org.kanomchan.core.common.exception;

import java.util.List;

import org.kanomchan.core.common.constant.MessageCode;

public interface BaseException {
	
	
	public MessageCode getMessageCode();
	public List<String> getPara();
	public Throwable getThrowable();
	public String getMessage();
	
}
