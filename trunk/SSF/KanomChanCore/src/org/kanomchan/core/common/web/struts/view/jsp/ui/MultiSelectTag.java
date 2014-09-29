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

	
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new MultiSelect(stack, req, res);
    }
	
	protected void populateParams() {
        super.populateParams();

        MultiSelect multiSelect = ((MultiSelect) component);
    }
}
