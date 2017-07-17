package org.kanomchan.core.common.web.struts.validator.validators;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.RangeValidatorSupport;

public class IntRangeFieldValidator extends RangeValidatorSupport<Integer> {
	boolean byPassFront;
	boolean byPassBack;
	
    public IntRangeFieldValidator() {
        super(Integer.class);
    }
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
