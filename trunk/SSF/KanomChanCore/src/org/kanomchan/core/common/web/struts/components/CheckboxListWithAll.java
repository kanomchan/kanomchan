package org.kanomchan.core.common.web.struts.components;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.ListUIBean;
import org.apache.struts2.views.annotations.StrutsTag;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="checkboxlistWithAll", tldTagClass="org.kanomchan.core.common.web.struts.view.jsp.ui.CheckboxListWithAllTag", description="Render a list of checkboxes")
public class CheckboxListWithAll extends ListUIBean {
    final public static String TEMPLATE = "checkboxlistwithall";
    protected String beanName;
    protected String checkName;
    

    public CheckboxListWithAll(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }
    
    public boolean start(Writer writer) {
        boolean result = super.start(writer);
        
        beanName = (String) findString(beanName);
        checkName = (String) findString(checkName);
        addParameter("beanName", beanName);
        addParameter("checkName", checkName);
		return result;
        
    }
    
    
    public String getBeanName() {
		return beanName;
	}
    public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
    public String getCheckName() {
		return checkName;
	}
    public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
}
