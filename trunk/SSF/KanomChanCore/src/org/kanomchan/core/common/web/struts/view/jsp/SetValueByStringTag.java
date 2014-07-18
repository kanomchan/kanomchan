
package org.kanomchan.core.common.web.struts.view.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
//import org.apache.struts2.components.Set;
import org.apache.struts2.views.jsp.ContextBeanTag;
import org.kanomchan.core.common.web.struts.components.SetValueByString;

import com.opensymphony.xwork2.util.ValueStack;


/**
 * @see Set
 */
public class SetValueByStringTag extends ContextBeanTag {

    private static final long serialVersionUID = -5074213926790716974L;

    protected String scope;
    protected String value;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new SetValueByString(stack);
    }

    protected void populateParams() {
        super.populateParams();

        SetValueByString set = (SetValueByString) component;
        set.setScope(scope);
        set.setValue(value);
    }

    public void setName(String name) {
       setVar(name);
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
