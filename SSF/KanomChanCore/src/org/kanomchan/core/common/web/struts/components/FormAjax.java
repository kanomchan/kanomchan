
package org.kanomchan.core.common.web.struts.components;

import java.util.Map;

import com.opensymphony.xwork2.util.ValueStack;

import org.apache.struts2.components.Form;
import org.apache.struts2.components.If;
import org.apache.struts2.views.annotations.StrutsTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
