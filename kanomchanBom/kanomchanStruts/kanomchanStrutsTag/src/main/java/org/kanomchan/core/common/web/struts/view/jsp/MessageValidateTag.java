package org.kanomchan.core.common.web.struts.view.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;
import org.kanomchan.core.common.web.struts.components.MessageValidate;

import com.opensymphony.xwork2.util.ValueStack;


/**
 * @see MessageValidate
 */
public class MessageValidateTag extends AbstractUITag {

    private static final long serialVersionUID = 4008321310097730458L;

    protected String forAttr;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new MessageValidate(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();

        ((MessageValidate) component).setFor(forAttr);
    }

    public void setFor(String aFor) {
        this.forAttr = aFor;
    }
}
