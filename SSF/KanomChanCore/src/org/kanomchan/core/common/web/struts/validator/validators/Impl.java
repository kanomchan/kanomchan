package org.kanomchan.core.common.web.struts.validator.validators;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.opensymphony.xwork2.ValidationAwareSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.Validator;
import com.opensymphony.xwork2.validator.ValidatorContext;
import com.opensymphony.xwork2.validator.validators.ValidatorSupport;

public class Impl extends ValidatorSupport {
	boolean byPassFront;
	boolean byPassBack;
	Long id;
	List<Long> preCon;
	List<Long> postCon;
	List<Validator> validatePre;
	boolean impl;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Long> getPreCon() {
		return preCon;
	}
	
	public void setPreCon(List<Long> preCon) {
		this.preCon = preCon;
	}
	
	public List<Long> getPostCon() {
		return postCon;
	}
	
	public void setPostCon(List<Long> postCon) {
		this.postCon = postCon;
	}
	
	public boolean isImpl() {
		return impl;
	}
	
	public void setImpl(boolean impl) {
		this.impl = impl;
	}

    public void validate(Object object) throws ValidationException {
    	ValidatorContext validatorContext = new ValidatorContext() {
			
			@Override
			public Locale getLocale() {
				return null;
			}
			
			@Override
			public boolean hasKey(String key) {
				return false;
			}
			
			@Override
			public ResourceBundle getTexts(String bundleName) {
				return null;
			}
			
			@Override
			public ResourceBundle getTexts() {
				return null;
			}
			
			@Override
			public String getText(String key, String defaultValue, String[] args,
					ValueStack stack) {
				return null;
			}
			
			@Override
			public String getText(String key, String defaultValue, List<?> args,
					ValueStack stack) {
				return null;
			}
			
			@Override
			public String getText(String key, String defaultValue, String[] args) {
				return null;
			}
			
			@Override
			public String getText(String key, String defaultValue, List<?> args) {
				return null;
			}
			
			@Override
			public String getText(String key, String defaultValue, String obj) {
				return null;
			}
			
			@Override
			public String getText(String key, String[] args) {
				return null;
			}
			
			@Override
			public String getText(String key, List<?> args) {
				return null;
			}
			
			@Override
			public String getText(String key, String defaultValue) {
				return null;
			}
			
			@Override
			public String getText(String key) {
				return null;
			}
			
			@Override
			public void setFieldErrors(Map<String, List<String>> errorMap) {
				
			}
			
			@Override
			public void setActionMessages(Collection<String> messages) {
				
			}
			
			@Override
			public void setActionErrors(Collection<String> errorMessages) {
				
			}
			
			@Override
			public boolean hasFieldErrors() {
				return false;
			}
			
			@Override
			public boolean hasErrors() {
				return false;
			}
			
			@Override
			public boolean hasActionMessages() {
				return false;
			}
			
			@Override
			public boolean hasActionErrors() {
				return false;
			}
			
			@Override
			public Map<String, List<String>> getFieldErrors() {
				return null;
			}
			
			@Override
			public Collection<String> getActionMessages() {
				return null;
			}
			
			@Override
			public Collection<String> getActionErrors() {
				return null;
			}
			
			@Override
			public void addFieldError(String fieldName, String errorMessage) {
				
			}
			
			@Override
			public void addActionMessage(String aMessage) {
				
			}
			
			@Override
			public void addActionError(String anErrorMessage) {
				
			}
			
			@Override
			public String getFullFieldName(String fieldName) {
				return null;
			}
		};
    	for (Validator validate : validatePre) {
    		
			validate.setValidatorContext(validatorContext);
			validate.validate(object);
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
