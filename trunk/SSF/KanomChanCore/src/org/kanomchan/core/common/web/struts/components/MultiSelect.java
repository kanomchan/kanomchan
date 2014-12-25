package org.kanomchan.core.common.web.struts.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.ListUIBean;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(
    name="selectCountry",
    tldTagClass="org.kanomchan.core.common.web.struts.view.jsp.ui.MultiSelectTag",
    description="Render a select country element",
    allowDynamicAttributes=true)
public class MultiSelect extends ListUIBean {
    final public static String TEMPLATE = "multiSelect/multiSelect";

    protected String emptyOption;
    protected String headerKey;
    protected String headerValue;
    protected String multiple;
    protected String size;

    protected String beanName;
    protected String nameKey;
    protected String setStatus;
    protected String color;
    protected String fontColor;
    protected String placeholder;
    protected String choiceValue;
    protected String choiceName;

    public MultiSelect(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }

    public void evaluateExtraParams() {
        super.evaluateExtraParams();

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
        
        if (beanName != null) {
        	addParameter("beanName", findString(beanName));
        	addParameter("itemList", findValue(beanName));
        }
        if (nameKey != null) {
        	addParameter("nameKey", findString(name+"."+nameKey));
        }else{
        	addParameter("nameKey", findString(name));
        }
        if (setStatus != null) {
        	addParameter("setStatus", findString(setStatus));
        }
        if (color != null) {
        	addParameter("color", findString(color));
        }
        if (fontColor != null) {
        	addParameter("fontColor", findString(fontColor));
        }
        if (choiceValue != null) {
        	addParameter("choiceValue", findString(choiceValue));
        }
        if (choiceName != null) {
        	addParameter("choiceName", findString(choiceName));
        }
        if (placeholder != null) {
        	addParameter("placeholder", findString(placeholder));
        }
    }
    public String getChoiceValue() {
		return choiceValue;
	}
    public void setChoiceValue(String choiceValue) {
		this.choiceValue = choiceValue;
	}
    public String getChoiceName() {
		return choiceName;
	}
    public void setChoiceName(String choiceName) {
		this.choiceName = choiceName;
	}
    public String getPlaceholder() {
		return placeholder;
	}
    public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
    public String getColor() {
		return color;
	}
    public void setColor(String color) {
		this.color = color;
	}
    public String getFontColor() {
		return fontColor;
	}
    public String getSetStatus() {
		return setStatus;
	}
    public void setSetStatus(String setStatus) {
		this.setStatus = setStatus;
	}
    public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
    public String getBeanName() {
		return beanName;
	}
    public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
    public String getNameKey() {
		return nameKey;
	}
    public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
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
   
}
