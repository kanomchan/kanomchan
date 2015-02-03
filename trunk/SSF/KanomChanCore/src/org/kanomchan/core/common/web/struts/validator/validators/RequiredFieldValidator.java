package org.kanomchan.core.common.web.struts.validator.validators;

import com.opensymphony.xwork2.validator.ValidationException;


public class RequiredFieldValidator extends com.opensymphony.xwork2.validator.validators.RequiredFieldValidator {
	
	boolean jsOnly;
	boolean bypassJs;
	
	@Override
	public void validate(Object object) throws ValidationException {
		if(!jsOnly){
			super.validate(object);
		}
	}


	public boolean isJsOnly() {
		return jsOnly;
	}

	public void setJsOnly(boolean jsOnly) {
		this.jsOnly = jsOnly;
	}

	public boolean isBypassJs() {
		return bypassJs;
	}

	public void setBypassJs(boolean bypassJs) {
		this.bypassJs = bypassJs;
	}
	
	
	
}
//Long id;
//Long preCon;
//boolean impl;
//
//public Long getId() {
//	return id;
//}
//
//public void setId(Long id) {
//	this.id = id;
//}
//
//public Long getPreCon() {
//	return preCon;
//}
//
//public void setPreCon(Long preCon) {
//	this.preCon = preCon;
//}
//
//public boolean isImpl() {
//	return impl;
//}
//
//public void setImpl(boolean impl) {
//	this.impl = impl;
//}
//
//public void validate(Object object) throws ValidationException {
//    String fieldName = getFieldName();
//    Object value = this.getFieldValue(fieldName, object);
//
//    if (value == null || value.equals("")) {
//        addFieldError(fieldName, object);
//    }
//}