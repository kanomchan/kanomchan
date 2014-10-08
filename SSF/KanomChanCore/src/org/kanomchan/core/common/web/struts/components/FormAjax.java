
package org.kanomchan.core.common.web.struts.components;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.views.annotations.StrutsTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@StrutsTag(
    name="formajax",
    tldTagClass="org.apache.struts2.views.jsp.ui.FormTag",
    description="Renders an input formajx",
    allowDynamicAttributes=true)
public class FormAjax extends org.apache.struts2.components.Form {
    protected String editview;

    public FormAjax(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    @Override
    protected void evaluateExtraParams() {
        super.evaluateExtraParams();
   
        if (editview != null) {
            addParameter("editview", findString(editview));
        }
    }

    public String getEditview() {
		return editview;
	}
    
    public void setEditview(String editview) {
		this.editview = editview;
	}    
    
}
