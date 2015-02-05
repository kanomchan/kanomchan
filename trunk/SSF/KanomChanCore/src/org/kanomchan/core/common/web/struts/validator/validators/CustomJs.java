package org.kanomchan.core.common.web.struts.validator.validators;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class CustomJs extends FieldValidatorSupport {

	private String call;
	
    public void validate(Object object) throws ValidationException {
    	
    }

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}
	
	boolean byPassFront;
	boolean byPassBack;
	
//	@Override
//	public void validate(Object object) throws ValidationException {
//		if(!byPassBack){
//			super.validate(object);
//		}
//		
//	}

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
