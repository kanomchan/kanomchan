package org.kanomchan.core.common.web.struts.views.velocity.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.velocity.components.AbstractDirective;
import org.kanomchan.core.common.web.struts.components.SetValueByString;

import com.opensymphony.xwork2.util.ValueStack;

public class SetValueByStringDirective extends AbstractDirective {
    public String getBeanName() {
        return "setValueByString";
    }

    protected Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new SetValueByString(stack);
    }
}
