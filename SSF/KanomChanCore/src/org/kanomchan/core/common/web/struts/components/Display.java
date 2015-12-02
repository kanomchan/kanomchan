package org.kanomchan.core.common.web.struts.components;

import com.opensymphony.xwork2.ObjectFactory;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.RuntimeConfiguration;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.config.entities.InterceptorMapping;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptorUtil;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.validator.ActionValidatorManager;
import com.opensymphony.xwork2.validator.FieldValidator;
import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.ValidationInterceptor;
import com.opensymphony.xwork2.validator.Validator;
import com.opensymphony.xwork2.validator.ValidatorContext;
import com.opensymphony.xwork2.validator.validators.VisitorFieldValidator;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.components.ClosingUIBean;
import org.apache.struts2.components.Div;
import org.apache.struts2.components.Form;
import org.apache.struts2.components.If;
import org.apache.struts2.components.UrlRenderer;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.Writer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    	// TODO Auto-generated method stub
//    	name = "display";
    	
    	super.evaluateParams();
    }
    
    @Override
    protected void evaluateExtraParams() {
    	// TODO Auto-generated method stub
    	super.evaluateExtraParams();
    	this.id = escape("display_"+seq);
    	addParameter("id", this.id);
        Map<String, Object> context = stack.getContext();
//        Boolean ifResult = (Boolean) context.get(If.ANSWER);
        context.put(KEY, id);
//        context.remove(If.ANSWER);
        
        
    	
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
    	// TODO Auto-generated method stub
    	super.populateComponentHtmlId(form);
    }
    
    public void setSeq(int seq) {
		this.seq = seq;
	}
}
