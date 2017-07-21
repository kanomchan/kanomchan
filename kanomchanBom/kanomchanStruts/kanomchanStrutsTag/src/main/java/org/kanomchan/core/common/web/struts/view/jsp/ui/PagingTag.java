
package org.kanomchan.core.common.web.struts.view.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
//import org.apache.struts2.components.Set;
import org.apache.struts2.views.jsp.ui.AbstractClosingTag;
import org.kanomchan.core.common.web.struts.components.Paging;

import com.opensymphony.xwork2.util.ValueStack;


/**
 * @see Set
 */
public class PagingTag extends AbstractClosingTag {

    private static final long serialVersionUID = -5074213926790716974L;

    protected String action;
    protected String target;
    protected String enctype;
    protected String method;
    protected String namespace;
    protected String validate;
    protected String onsubmit;
    protected String onreset;
    protected String portletMode;
    protected String windowState;
    protected String acceptcharset;
    protected String focusElement;
    protected boolean includeContext = true;
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
        paging.setAction(action);
        paging.setTarget(target);
        paging.setEnctype(enctype);
        paging.setMethod(method);
        paging.setNamespace(namespace);
        paging.setValidate(validate);
        paging.setOnreset(onreset);
        paging.setOnsubmit(onsubmit);
        paging.setPortletMode(portletMode);
        paging.setWindowState(windowState);
        paging.setAcceptcharset(acceptcharset);
        paging.setFocusElement(focusElement);
        paging.setIncludeContext(includeContext);
        paging.setPagingBean(pagingBean);
        paging.setIsAjax(isAjax);
        paging.setAjaxFunction(ajaxFunction);
        paging.setDefaultShowPerPage(defaultShowPerPage);
    }

    
    public void setAction(String action) {
        this.action = action;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setEnctype(String enctype) {
        this.enctype = enctype;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public void setOnsubmit(String onsubmit) {
        this.onsubmit = onsubmit;
    }

    public void setOnreset(String onreset) {
        this.onreset = onreset;
    }

    public void setPortletMode(String portletMode) {
        this.portletMode = portletMode;
    }

    public void setWindowState(String windowState) {
        this.windowState = windowState;
    }

    public void setAcceptcharset(String acceptcharset) {
        this.acceptcharset = acceptcharset;
    }

    public void setFocusElement(String focusElement) {
        this.focusElement = focusElement;
    }

    public void setIncludeContext(boolean includeContext) {
        this.includeContext = includeContext;
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
