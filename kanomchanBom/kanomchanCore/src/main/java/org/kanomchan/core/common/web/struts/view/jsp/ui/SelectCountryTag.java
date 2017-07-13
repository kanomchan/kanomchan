package org.kanomchan.core.common.web.struts.view.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractRequiredListTag;
import org.kanomchan.core.common.web.struts.components.SelectCountry;

import com.opensymphony.xwork2.util.ValueStack;

public class SelectCountryTag extends AbstractRequiredListTag {
	
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
	
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new SelectCountry(stack, req, res);
    }
	
	protected void populateParams() {
        super.populateParams();

        SelectCountry selectCountry = ((SelectCountry) component);
        selectCountry.setEmptyOption(emptyOption);
        selectCountry.setHeaderKey(headerKey);
        selectCountry.setHeaderValue(headerValue);
        selectCountry.setMultiple(multiple);
        selectCountry.setSize(size);
        selectCountry.setLabel(label);
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
}
