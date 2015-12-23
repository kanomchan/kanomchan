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
    protected String nameKey;

    public CheckboxListWithAll(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }
    
    public boolean start(Writer writer) {
        boolean result = super.start(writer);
        
        beanName = (String) findString(beanName);
        
        if(name != null && nameKey != null){
        	addParameter("nameKey", findString("." + name + "." + nameKey));
        }
        else if(name != null){
        	addParameter("nameKey", findString("." + name));
        }
        else if(nameKey != null){
        	addParameter("nameKey", findString("." + nameKey));
        }
        
        if(name != null && checkName != null){
        	checkName = (String) findString("." + name + "." + checkName);
        	addParameter("checkName", checkName);
        }
        else if(name != null){
        	checkName = (String) findString("." + name);
        	addParameter("checkName", checkName);
        }
        else if(checkName != null){
        	checkName = (String) findString("." + checkName);
        	addParameter("checkName", checkName);
        }
        
        addParameter("beanName", beanName);
		return result;
        
    }
    public String getNameKey() {
		return nameKey;
	}
    public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
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
