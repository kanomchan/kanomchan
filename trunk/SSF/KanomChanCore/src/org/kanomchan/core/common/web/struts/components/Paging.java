package org.kanomchan.core.common.web.struts.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Form;
import org.apache.struts2.views.annotations.StrutsTag;

import com.opensymphony.xwork2.util.ValueStack;


@StrutsTag(name="paging", tldBodyContent="JSP", tldTagClass="org.kanomchan.core.common.web.struts.view.jsp.PagingTag", description="Assigns a value to a variable in a specified scope")
public class Paging extends Form {

    public static final String OPEN_TEMPLATE = "paging";
    public static final String TEMPLATE = "paging-close";
	
	public Paging(ValueStack stack, HttpServletRequest request,HttpServletResponse response) {
		super(stack, request, response);
	}

}
