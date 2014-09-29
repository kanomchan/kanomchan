package org.kanomchan.core.common.web.struts.view.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.CheckboxList;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractRequiredListTag;
import org.kanomchan.core.common.web.struts.components.CheckboxListWithAll;

import com.opensymphony.xwork2.util.ValueStack;


/**
 * @see CheckboxList
 */
public class CheckboxListWithAllTag extends AbstractRequiredListTag {

	    
    private static final long serialVersionUID = 4023034029558150010L;

    protected String beanName;
    protected String checkName;
    
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new CheckboxListWithAll(stack, req, res);
    }
    
    protected void populateParams() {
    	super.populateParams();
    	CheckboxListWithAll checkboxListWithAll = (CheckboxListWithAll) component;
    	checkboxListWithAll.setBeanName(beanName);
    	checkboxListWithAll.setCheckName(checkName);
    }

    public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
    
}
