
package org.kanomchan.core.common.web.struts.view.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
//import org.apache.struts2.components.Set;
import org.apache.struts2.views.jsp.ContextBeanTag;
import org.kanomchan.core.common.web.struts.components.Div;
import org.kanomchan.core.common.web.struts.components.SetValueByString;

import com.opensymphony.xwork2.util.ValueStack;


/**
 * @see Set
 */
public class DivTag extends org.apache.struts2.views.jsp.ui.DivTag {


    /**
	 * 
	 */
	private static final long serialVersionUID = -5321662025466435738L;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Div(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();
    }
    
}
