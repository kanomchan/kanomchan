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
import org.apache.struts2.components.If;
import org.apache.struts2.components.UIBean;
import org.apache.struts2.components.UrlRenderer;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.util.TextProviderHelper;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <!-- START SNIPPET: javadoc -->
 * <p/>
 * Renders HTML an input form.<p/>
 * <p/>
 * The remote form allows the form to be submitted without the page being refreshed. The results from the form
 * can be inserted into any HTML element on the page.<p/>
 * <p/>
 * NOTE:<p/>
 * The order / logic in determining the posting url of the generated HTML form is as follows:-
 * <ol>
 * <li>
 * If the action attribute is not specified, then the current request will be used to
 * determine the posting url
 * </li>
 * <li>
 * If the action is given, Struts will try to obtain an ActionConfig. This will be
 * successfull if the action attribute is a valid action alias defined struts.xml.
 * </li>
 * <li>
 * If the action is given and is not an action alias defined in struts.xml, Struts
 * will used the action attribute as if it is the posting url, separting the namespace
 * from it and using UrlHelper to generate the final url.
 * </li>
 * </ol>
 * <p/>
 * <!-- END SNIPPET: javadoc -->
 * <p/>
 * <p/> <b>Examples</b>
 * <p/>
 * <pre>
 * <!-- START SNIPPET: example -->
 * <p/>
 * &lt;s:form ... /&gt;
 * <p/>
 * <!-- END SNIPPET: example -->
 * </pre>
 *
 */
@StrutsTag(
    name="display",
    tldTagClass="org.kanomchan.core.common.web.struts.view.jsp.ui.DisplayTextTag",
    description="Renders an input display",
    allowDynamicAttributes=true)
public class DisplayText extends UIBean {
    public static final String TEMPLATE = "displaytext";
    public static final String KEY = "KC_DISPLAY_KEY";
//    public static final String TEMPLATE_CLOSE = "display-close";
    public static final String COMPONENT_NAME = DisplayText.class.getName();

    public DisplayText(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }
    
    
    @Override
    protected void evaluateExtraParams() {
    	// TODO Auto-generated method stub
    	super.evaluateExtraParams();
//        Map context = stack.getContext();
////        Boolean ifResult = (Boolean) context.get(If.ANSWER);
//        context.put(KEY, id);
//        context.remove(If.ANSWER);

//        if (forAttr != null) {
//            addParameter("for", findString(forAttr));
//        }

        // try value, then key, then name (this overrides the default behavior in the superclass)
        if (value == null) {
            value = "top";
        }
        else {
        	value = stripExpressionIfAltSyntax(value);
        }
    	
    	
        if (value != null) {
//        	getStack().findValue(value, String.class, throwExceptionOnELFailure);
            addParameter("nameValue", getStack().findValue(value, String.class, throwExceptionOnELFailure));
            addParameter("value", value);
        } else if (key != null) {
            Object nameValue = parameters.get("nameValue");
            if (nameValue == null || nameValue.toString().length() == 0) {
                // get the label from a TextProvider (default value is the key)
                String providedLabel = TextProviderHelper.getText(key, key, stack);
                addParameter("nameValue", providedLabel);
            }
        } else if (name != null) {
            String expr = completeExpressionIfAltSyntax(name);
            addParameter("nameValue", findString(expr));
        }
    	
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }
//    protected String getDefaultTemplate() {
//        return TEMPLATE_CLOSE;
//    }
}
