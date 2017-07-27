
package org.kanomchan.core.common.web.struts.view.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
//import org.apache.struts2.components.Set;
import org.apache.struts2.views.jsp.ui.AbstractClosingTag;
import org.apache.struts2.views.jsp.ui.FormTag;
import org.kanomchan.core.common.web.struts.components.Paging;

import com.opensymphony.xwork2.util.ValueStack;


/**
 */
public class PagingTag extends FormTag {

    private static final long serialVersionUID = -5074213926790716974L;

    protected String pagingBean;
    protected String isAjax;
    protected String ajaxFunction;
    protected String defaultShowPerPage;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return  new Paging(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();
        Paging paging = (Paging) component;
        paging.setPagingBean(pagingBean);
        paging.setIsAjax(isAjax);
        paging.setAjaxFunction(ajaxFunction);
        paging.setDefaultShowPerPage(defaultShowPerPage);
    }

    
    public void setPagingBean(String pagingBean) {
		this.pagingBean = pagingBean;
	}
    
    public void setIsAjax(String isAjax) {
		this.isAjax = isAjax;
	}
    
    public void setAjaxFunction(String ajaxFunction) {
		this.ajaxFunction = ajaxFunction;
	}
    
    public void setDefaultShowPerPage(String defaultShowPerPage) {
		this.defaultShowPerPage = defaultShowPerPage;
	}
    
}
