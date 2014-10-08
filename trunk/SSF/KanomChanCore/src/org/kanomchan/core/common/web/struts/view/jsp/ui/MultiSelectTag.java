package org.kanomchan.core.common.web.struts.view.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractRequiredListTag;
import org.kanomchan.core.common.web.struts.components.MultiSelect;

import com.opensymphony.xwork2.util.ValueStack;

public class MultiSelectTag extends AbstractRequiredListTag {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6935066783816732675L;
	
    protected String emptyOption;
    protected String headerKey;
    protected String headerValue;
    protected String list;
    protected String listKey;
    protected String listValue;
    protected String size;
    protected String multiple;
    protected String beanName;
    protected String nameValue;
    protected String color;
    protected String fontColor;
    protected String placeholder;
	
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new MultiSelect(stack, req, res);
    }
	
	protected void populateParams() {
        super.populateParams();

        MultiSelect multiSelect = ((MultiSelect) component);
        multiSelect.setBeanName(beanName);
        multiSelect.setNameValue(nameValue);
        multiSelect.setPlaceholder(placeholder);
    	multiSelect.setEmptyOption(emptyOption);
    	multiSelect.setHeaderKey(headerKey);
    	multiSelect.setHeaderValue(headerValue);
    	multiSelect.setList(list);
    	multiSelect.setListKey(listKey);
    	multiSelect.setListValue(listValue);
    	multiSelect.setSize(size);
    	multiSelect.setMultiple(multiple);
    	multiSelect.setColor(color);
    	multiSelect.setFontColor(fontColor);
    }
	
	public void setColor(String color) {
		this.color = color;
	}
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public void setNameValue(String nameValue) {
		this.nameValue = nameValue;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
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

    public void setList(String list) {
    	this.list = list;
    }

    public void setListKey(String listKey) {
    	this.listKey = listKey;
    }

    public void setListValue(String listValue) {
    	this.listValue = listValue;
    }

    public void setSize(String size) {
    	this.size = size;
    }

    public void setMultiple(String multiple) {
    	this.multiple = multiple;
    }

	
}
