package org.kanomchan.core.common.web.struts.view.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;
import org.kanomchan.core.common.web.struts.components.TimePicker;

import com.opensymphony.xwork2.util.ValueStack;

public class TimePickerTag extends AbstractUITag {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6935066783816732675L;
	protected String emptyOption;
    protected String headerKey;
    protected String headerValue;
    protected String multiple;
    protected String size;
    protected String label;
    protected String buttonText;
    protected String autoClose;

	
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new TimePicker(stack, req, res);
    }
	
	protected void populateParams() {
        super.populateParams();

        TimePicker timePicker = ((TimePicker) component);
        timePicker.setEmptyOption(emptyOption);
        timePicker.setHeaderKey(headerKey);
        timePicker.setHeaderValue(headerValue);
        timePicker.setMultiple(multiple);
        timePicker.setSize(size);
        timePicker.setLabel(label);
        timePicker.setButtonText(buttonText);
        timePicker.setAutoClose(autoClose);
    }
	
	public void setEmptyOption(String emptyOption) {
        this.emptyOption = emptyOption;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public void setSize(String size) {
        this.size = size;
    }
    
    public void setLabel(String label) {
    	this.label = label;
    }

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public String getAutoClose() {
		return autoClose;
	}

	public void setAutoClose(String autoClose) {
		this.autoClose = autoClose;
	}

	
	
    
	
    
}
