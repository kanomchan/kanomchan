package org.kanomchan.core.common.web.struts.validator.validators;

import com.opensymphony.xwork2.validator.ValidationException;

public class RegexFieldValidator extends com.opensymphony.xwork2.validator.validators.RegexFieldValidator {
	String byPassFront;
	String byPassBack;
	
	@Override
	public void validate(Object object) throws ValidationException {
		if(byPassBack == null || "false".equals(byPassBack)){
			super.validate(object);
		}
		
	}

	public String getByPassBack() {
		return byPassBack;
	}
	public void setByPassBack(String byPassBack) {
		this.byPassBack = byPassBack;
	}
	public String getByPassFront() {
		return byPassFront;
	}
	public void setByPassFront(String byPassFront) {
		this.byPassFront = byPassFront;
	}

}
