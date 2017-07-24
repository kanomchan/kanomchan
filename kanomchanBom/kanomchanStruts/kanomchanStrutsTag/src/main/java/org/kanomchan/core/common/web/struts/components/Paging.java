package org.kanomchan.core.common.web.struts.components;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Form;
import org.apache.struts2.views.annotations.StrutsTag;
import org.kanomchan.core.common.bean.PagingBean;

import com.opensymphony.xwork2.util.ValueStack;


@StrutsTag(name="paging", tldBodyContent="JSP", tldTagClass="org.kanomchan.core.common.web.struts.view.jsp.PagingTag", description="Assigns a value to a variable in a specified scope")
public class Paging extends Form {

    public static final String OPEN_TEMPLATE = "paging";
    public static final String TEMPLATE = "paging-close";
    
    
    protected String pagingBean;
    protected String isAjax;
    protected String ajaxFunction;
    protected String defaultShowPerPage;
    
	public Paging(ValueStack stack, HttpServletRequest request,HttpServletResponse response) {
		super(stack, request, response);
	}

	public boolean start(Writer writer) {
	        boolean result = super.start(writer);

	        
	        PagingBean pagingBeanValue = new PagingBean();
	        pagingBeanValue =(PagingBean) findValue(pagingBean);
            if (pagingBeanValue == null) {
            	pagingBeanValue = new PagingBean();
            }
            addParameter("currentPage", pagingBeanValue.getCurrentPage());
            addParameter("rowsPerPage", pagingBeanValue.getRowsPerPage());
            addParameter("pageCount", pagingBeanValue.getPageCount());
            addParameter("previousPage", pagingBeanValue.getPreviousPage());
            addParameter("nextPage", pagingBeanValue.getNextPage());
            addParameter("totalRows", pagingBeanValue.getTotalRows());
            addParameter("orderBy", pagingBeanValue.getOrderBy());
            addParameter("orderMode", pagingBeanValue.getOrderMode());
            addParameter("displayPage", pagingBeanValue.getDisplayPage());
            addParameter("isAjax", isAjax);
            addParameter("ajaxFunction", ajaxFunction);
            addParameter("defaultShowPerPage", defaultShowPerPage);
	        return result;
	}

	
	@Override
	protected void evaluateExtraParams() {
		super.evaluateExtraParams();

	}
	
    @Override
    public String getDefaultOpenTemplate() {
        return OPEN_TEMPLATE;
    }

    @Override
    protected String getDefaultTemplate() {
        return TEMPLATE;
    }
    
	public String getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(String pagingBean) {
		this.pagingBean = pagingBean;
	}
	
	public String getIsAjax() {
		return isAjax;
	}
	
	public void setIsAjax(String isAjax) {
		this.isAjax = isAjax;
	}
	
	public String getAjaxFunction() {
		return ajaxFunction;
	}
	
	public void setAjaxFunction(String ajaxFunction) {
		this.ajaxFunction = ajaxFunction;
	}
	
	public void setDefaultShowPerPage(String defaultShowPerPage) {
		this.defaultShowPerPage = defaultShowPerPage;
	}
	
}