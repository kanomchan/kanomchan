
package org.kanomchan.core.common.web.struts.view.jsp.ui;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
//import org.apache.struts2.components.Set;
import org.apache.struts2.views.jsp.ContextBeanTag;
import org.kanomchan.core.common.exception.NonRollBackException;
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
	
	protected String displayKey;
	
	private static final long serialVersionUID = -5321662025466435738L;

	@Override
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Div(stack, req, res);
    }

    @Override
    protected void populateParams() {
        super.populateParams();
        
        Div set = (Div) component;
        try {
			set.setDisplayKey(displayKey);
		} catch (RollbackException | NonRollBackException e) {
			e.printStackTrace();
		}
    }

	public void setDisplayKey(String displayKey) {
		this.displayKey = displayKey;
	}
    
    
    
}
