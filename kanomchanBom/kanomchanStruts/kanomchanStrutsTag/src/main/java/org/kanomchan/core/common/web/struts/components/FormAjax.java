
package org.kanomchan.core.common.web.struts.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.components.Form;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.views.annotations.StrutsTag;

import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.validator.FieldValidator;
import com.opensymphony.xwork2.validator.Validator;
import com.opensymphony.xwork2.validator.validators.VisitorFieldValidator;

@StrutsTag(
    name="formajax",
    tldTagClass="org.kanomchan.core.common.web.struts.view.jsp.ui.FormAjaxTag",
    description="Renders an input formajx",
    allowDynamicAttributes=true)
public class FormAjax extends Form {
    public static final String OPEN_TEMPLATE = "formajax";
    public static final String TEMPLATE = "formajax-close";
    
    private String displayId;
	
	
    protected String editview;

    public FormAjax(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    @Override
    protected void evaluateExtraParams() {
        super.evaluateExtraParams();
        Map context = stack.getContext();
//        Boolean ifResult = (Boolean) context.get(If.ANSWER);
//
//        context.remove(If.ANSWER);

        if (editview != null) {
        	
            addParameter("editview",findValue(editview, Boolean.class));
            
            if(displayId == null){
            	displayId =  (String) context.get(Display.KEY);
                context.remove(Display.KEY);
                addParameter("displayId", displayId);
            }else{
            	addParameter("displayId", displayId);
            }
//            displayId =  (String) context.get(Display.KEY);
//            context.remove(Display.KEY);
//            addParameter("displayId", displayId);
        }
        
        
    }
    
    @Override
    public List getValidators(String name) {
        Class actionClass = (Class) getParameters().get("actionClass");
        if (actionClass == null) {
            return Collections.EMPTY_LIST;
        }

        String formActionValue = findString(action);
        ActionMapping mapping = actionMapper.getMappingFromActionName(formActionValue);
        String actionName = mapping.getName();

        List<Validator> actionValidators = actionValidatorManager.getValidators(actionClass, actionName);
        List<Validator> validators = new ArrayList<Validator>();

        findFieldValidators(name, actionClass, actionName, actionValidators, validators, "");

        return validators;
    }

    private void findFieldValidators(String name, Class actionClass, String actionName,
            List<Validator> validatorList, List<Validator> retultValidators, String prefix) {

    	 for (Validator validator : validatorList) {
             if (validator instanceof FieldValidator) {
                 FieldValidator fieldValidator = (FieldValidator) validator;
                 String fieldName = (fieldValidator.getFieldName() != null ? fieldValidator.getFieldName() : "");
                 if (validator instanceof VisitorFieldValidator) {
                     VisitorFieldValidator vfValidator = (VisitorFieldValidator) fieldValidator;
                     Class clazz = getVisitorReturnType(actionClass, vfValidator.getFieldName());
                     if (clazz == null) {
                         continue;
                     }

                     List<Validator> visitorValidators = actionValidatorManager.getValidators(clazz, actionName);
                     String vPrefix = prefix + (vfValidator.isAppendPrefix() ? vfValidator.getFieldName() + "." : "");
                     findFieldValidators(name, clazz, actionName, visitorValidators, retultValidators, vPrefix);
//                 } else if (Pattern.compile(prefix + fieldName).matcher(name).matches()) {
                 } else if ((prefix + fieldValidator.getFieldName()).equals(name)) {
                     if (StringUtils.isNotBlank(prefix)) {
                         //fixing field name for js side
                         FieldVisitorValidatorWrapper wrap = new FieldVisitorValidatorWrapper(fieldValidator, prefix);
                         retultValidators.add(wrap);
                     } else {
                         retultValidators.add(fieldValidator);
                     }
                 }
             }
         }
    }


    public String getEditview() {
		return editview;
	}
    
    public void setEditview(String editview) {
		this.editview = editview;
	}    
    @Override
    public String getDefaultOpenTemplate() {
        return OPEN_TEMPLATE;
    }

    @Override
    protected String getDefaultTemplate() {
        return TEMPLATE;
    }
}
