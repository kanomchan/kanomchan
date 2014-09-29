package org.kanomchan.core.common.web.struts.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.ListUIBean;
import org.apache.struts2.views.annotations.StrutsTag;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(
    name="selectCountry",
    tldTagClass="org.kanomchan.core.common.web.struts.view.jsp.ui.MultiSelectTag",
    description="Render a select country element",
    allowDynamicAttributes=true)
public class MultiSelect extends ListUIBean {
    final public static String TEMPLATE = "selectCountry/selectcountry";

    public MultiSelect(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }

    public void evaluateExtraParams() {
        super.evaluateExtraParams();
        
    }

   
}
