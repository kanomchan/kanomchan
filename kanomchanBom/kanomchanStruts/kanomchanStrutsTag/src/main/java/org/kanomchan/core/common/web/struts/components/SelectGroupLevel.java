package org.kanomchan.core.common.web.struts.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.ListUIBean;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(
    name="selectGroupLevel",
    tldTagClass="org.kanomchan.core.common.web.struts.view.jsp.ui.SelectGroupLevelTag",
    description="Render a select country element",
    allowDynamicAttributes=true)
public class SelectGroupLevel extends ListUIBean {
    final public static String TEMPLATE = "selectgrouplevel/selectgrouplevel";

    protected String emptyOption;
    protected String headerKey;
    protected String headerValue;
    protected String multiple;
    protected String size;
    protected String label;
    protected String ajaxUrl;
    protected String chooseLimit;
    protected String beanName;
    protected String subName;
    protected String subBeanName;
    protected String placeholder;
    protected String itemKey;
    protected String itemName;
    protected String itemParentId;
    protected String typeOfIgnore;
    protected String idParentIgnore;
    protected String parentLevel;
    protected String levelIgnore;
    protected String beanId;
    protected String labelLeft;
    protected String labelRight;

    public SelectGroupLevel(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }

    public void evaluateExtraParams() {
        super.evaluateExtraParams();
        
        addParameter("listTitle", "desc.CODE3");

        if (emptyOption != null) {
            addParameter("emptyOption", findValue(emptyOption, Boolean.class));
        }

        if (multiple != null) {
            addParameter("multiple", findValue(multiple, Boolean.class));
        }

        if (size != null) {
            addParameter("size", findString(size));
        }

        if ((headerKey != null) && (headerValue != null)) {
            addParameter("headerKey", findString(headerKey));
            addParameter("headerValue", findString(headerValue));
        }
        
        if(label != null)
        	addParameter("label", findString(label));
        if(ajaxUrl != null)
        	addParameter("ajaxUrl", findString(ajaxUrl));
        if(chooseLimit != null)
        	addParameter("chooseLimit", findString(chooseLimit));
        if(beanName != null)
        	addParameter("beanName", findString(beanName));
        if(subName != null)
        	addParameter("subName", findString(subName));
        if(subName != null)
        	addParameter("subBeanName", findString(subBeanName));
        if(placeholder != null)
        	addParameter("placeholder", findString(placeholder));
        if(itemName != null)
        	addParameter("itemName", findString(itemName));
        if(itemKey != null)
        	addParameter("itemKey", findString(itemKey));
        if(itemParentId != null)
        	addParameter("itemParentId", findString(itemParentId));
        if(typeOfIgnore != null)
        	addParameter("typeOfIgnore", findString(typeOfIgnore));
        if(idParentIgnore != null)
        	addParameter("idParentIgnore", findString(idParentIgnore));
        if(parentLevel != null)
        	addParameter("parentLevel", findString(parentLevel));
        if(levelIgnore != null)
        	addParameter("levelIgnore", findString(levelIgnore));
        if(beanId != null)
        	addParameter("beanId", findString(beanId));
        if(labelLeft != null)
        	addParameter("labelLeft", findString(labelLeft));
        if(labelRight != null)
        	addParameter("labelRight", findString(labelRight));
    }

    @StrutsTagAttribute(description="Whether or not to add an empty (--) option after the header option", type="Boolean", defaultValue="false")
    public void setEmptyOption(String emptyOption) {
        this.emptyOption = emptyOption;
    }

    @StrutsTagAttribute(description=" Key for first item in list. Must not be empty! '-1' and '' is correct, '' is bad.")
    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    @StrutsTagAttribute(description="Value expression for first item in list")
    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }

    @StrutsTagAttribute(description=" Creates a multiple select. The tag will pre-select multiple values" +
                " if the values are passed as an Array or a Collection(of appropriate types) via the value attribute. If one of the keys equals" +
                " one of the values in the Collection or Array it wil be selected", type="Boolean", defaultValue="false")
    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    @StrutsTagAttribute(description="Size of the element box (# of elements to show)", type="Integer")
    public void setSize(String size) {
        this.size = size;
    }
    
    public void setLabel(String label) {
    	this.label = label;
    }
    
    public void setAjaxUrl(String ajaxUrl) {
		this.ajaxUrl = ajaxUrl;
	}
    
    public void setChooseLimit(String chooseLimit) {
		this.chooseLimit = chooseLimit;
	}
    
    public void setSubBeanName(String subBeanName) {
		this.subBeanName = subBeanName;
	}
    
    public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
    
    public void setSubName(String subName) {
		this.subName = subName;
	}
    
    public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
    
	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}
    
    public void setItemName(String itemName) {
		this.itemName = itemName;
	}
    
   public void setItemParentId(String itemParentId) {
	this.itemParentId = itemParentId;
}
    
    public void setTypeOfIgnore(String typeOfIgnore) {
		this.typeOfIgnore = typeOfIgnore;
	}
    
    public void setIdParentIgnore(String idParentIgnore) {
    	this.idParentIgnore = idParentIgnore;
    }
    
    public void setLevelIgnore(String levelIgnore) {
		this.levelIgnore = levelIgnore;
	}
    
    public void setParentLevel(String parentLevel) {
		this.parentLevel = parentLevel;
	}
    
    public void setBeanId(String beanId) {
		this.beanId = beanId;
	}
    
    public void setLabelLeft(String labelLeft) {
		this.labelLeft = labelLeft;
	}
    
    public void setLabelRight(String labelRight) {
		this.labelRight = labelRight;
	}
}
