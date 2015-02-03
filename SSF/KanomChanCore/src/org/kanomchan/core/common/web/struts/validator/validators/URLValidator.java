package org.kanomchan.core.common.web.struts.validator.validators;

import com.opensymphony.xwork2.validator.ValidationException;

public class URLValidator extends com.opensymphony.xwork2.validator.validators.URLValidator {
	boolean byPassFront;
	boolean byPassBack;
	
	@Override
	public void validate(Object object) throws ValidationException {
		if(!byPassBack){
			super.validate(object);
		}
		
	}

	public boolean isByPassFront() {
		return byPassFront;
	}

	public void setByPassFront(boolean byPassFront) {
		this.byPassFront = byPassFront;
	}

	public boolean isByPassBack() {
		return byPassBack;
	}

	public void setByPassBack(boolean byPassBack) {
		this.byPassBack = byPassBack;
	}

}
