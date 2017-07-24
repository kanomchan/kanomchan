package org.kanomchan.core.common.web.struts.components;

import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.ClosingUIBean;
import org.apache.struts2.components.Form;
import org.apache.struts2.views.annotations.StrutsTag;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(
    name="display",
    tldTagClass="org.kanomchan.core.common.web.struts.view.jsp.ui.DisplayTag",
    description="Renders an input display",
    allowDynamicAttributes=true)
public class Display extends ClosingUIBean {
    public static final String TEMPLATE = "display";
    public static final String KEY = "KC_DISPLAY_KEY";
    public static final String TEMPLATE_CLOSE = "display-close";
    public static final String COMPONENT_NAME = Display.class.getName();
    
    protected int seq;
    private Object target;

    public Display(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }
    
    
    @Override
    public void evaluateParams() {
    	super.evaluateParams();
    }
    
    @Override
    protected void evaluateExtraParams() {
    	super.evaluateExtraParams();
    	this.id = escape("display_"+seq);
    	addParameter("id", this.id);
        Map<String, Object> context = stack.getContext();
        context.put(KEY, id);
    }
    
    @Override
    public boolean start(Writer writer) {
    	target = findValue(value);
    	if(target!=null)
    	stack.push(target);
    	return super.start(writer);
    }
    
    @Override
    public boolean end(Writer writer, String body) {
    	if(target!=null)
    	stack.pop();
    	return super.end(writer, body);
    }

    public String getDefaultOpenTemplate() {
        return TEMPLATE;
    }

    protected String getDefaultTemplate() {
        return TEMPLATE_CLOSE;
    }
    
//    @Override
    protected void populateComponentHtmlId(Form form) {
    	super.populateComponentHtmlId(form);
    }
    
    public void setSeq(int seq) {
		this.seq = seq;
	}
}
