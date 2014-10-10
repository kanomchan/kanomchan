package org.kanomchan.core.common.web.struts.validator.validators;

import java.util.List;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.Validator;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class RequiredFieldValidator extends FieldValidatorSupport {
	
	Long id;
	Long preCon;
	boolean impl;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPreCon() {
		return preCon;
	}
	
	public void setPreCon(Long preCon) {
		this.preCon = preCon;
	}
	
	public boolean isImpl() {
		return impl;
	}
	
	public void setImpl(boolean impl) {
		this.impl = impl;
	}

    public void validate(Object object) throws ValidationException {
        String fieldName = getFieldName();
        Object value = this.getFieldValue(fieldName, object);

        if (value == null || value.equals("")) {
            addFieldError(fieldName, object);
        }
    }
}
