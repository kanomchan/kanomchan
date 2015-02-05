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
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean hasKey(String key) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public ResourceBundle getTexts(String bundleName) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ResourceBundle getTexts() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getText(String key, String defaultValue, String[] args,
					ValueStack stack) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getText(String key, String defaultValue, List<?> args,
					ValueStack stack) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getText(String key, String defaultValue, String[] args) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getText(String key, String defaultValue, List<?> args) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getText(String key, String defaultValue, String obj) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getText(String key, String[] args) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getText(String key, List<?> args) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getText(String key, String defaultValue) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getText(String key) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void setFieldErrors(Map<String, List<String>> errorMap) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setActionMessages(Collection<String> messages) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setActionErrors(Collection<String> errorMessages) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean hasFieldErrors() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean hasErrors() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean hasActionMessages() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean hasActionErrors() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Map<String, List<String>> getFieldErrors() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Collection<String> getActionMessages() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Collection<String> getActionErrors() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void addFieldError(String fieldName, String errorMessage) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addActionMessage(String aMessage) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addActionError(String anErrorMessage) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String getFullFieldName(String fieldName) {
				// TODO Auto-generated method stub
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
