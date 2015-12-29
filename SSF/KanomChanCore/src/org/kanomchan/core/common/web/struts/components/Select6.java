package org.kanomchan.core.common.web.struts.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.ListUIBean;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(
    name="select6",
    tldTagClass="org.kanomchan.core.common.web.struts.view.jsp.ui.Select6Tag",
    description="Render a select country element",
    allowDynamicAttributes=true)
public class Select6 extends ListUIBean {
    final public static String TEMPLATE = "selectgrouplevel/select6";

    protected String emptyOption;
    protected String headerKey;
    protected String headerValue;
    protected String multiple;
    protected String size;
    protected String label;
    protected String nameList;
    protected String nameKey;
    protected String nameToSaveKey;
    protected String nameParentKey;
    protected String nameProfiKey;
    protected String nameProfiValue;
    protected String nameValue;
    protected String nameStatusInitKey;
    protected String nameStatus;
    protected String nameStatusKey;
    protected String groupList;
    protected String groupListKey;
    protected String groupListValue;
    protected String groupListLabel;
    protected String profiList;
    protected String profiListKey;
    protected String profiListValue;
    protected String searchGroupUrl;
    protected String searchGroupUrlKey;
    protected String subUrl;
    protected String subAllGroupUrl;
    protected String subName;
    protected String subNameValue;
    protected String subNameKey;
    protected String subNameParentKey;
    protected String subNameLabel;
    protected String singleSelect;

    public Select6(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
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

        if(nameList != null && (nameList != null)){
        	addParameter("nameList", findString(nameList));
        }
        else if((boolean) findValue(singleSelect)){
        	if(nameValue != null){
    			addParameter("nameValue_Single", findString(name)+"."+ findString(nameValue));
    		}
    		if(nameValue != null){
    			addParameter("nameKey_Single", findString(name)+"."+ findString(nameKey));
    		}
    		if(nameValue != null){
    			addParameter("nameParentKey_Single", findString(name)+"."+ findString(nameParentKey));
    		}
    		if(nameToSaveKey != null){
    			addParameter("nameToSaveKey_Single", findString(name)+"."+ findString(nameToSaveKey));
    		}
        }
        	
        if(nameKey != null){
        	addParameter("nameKey", findString(nameKey));
        }
    	
	    if(nameParentKey != null){
	    	addParameter("nameParentKey", findString(nameParentKey));
	    }
		
		if(nameProfiKey != null){
			addParameter("nameProfiKey", findString(nameProfiKey));
		}
		
		if(nameProfiValue != null){
			addParameter("nameProfiValue", findString(nameProfiValue));
		}
		
		if(nameValue != null){
			addParameter("nameValue", findString(nameValue));
		}
		
		if(groupList != null){
			addParameter("groupList", findString(groupList));
		}
		
		if(groupListKey != null){
			addParameter("groupListKey", findString(groupListKey));
		}
		
		if(groupListValue != null){
			addParameter("groupListValue", findString(groupListValue));
		}
		
		if(profiList != null){
			addParameter("profiList", findString(profiList));
		}
		
		if(profiListKey != null){
			addParameter("profiListKey", findString(profiListKey));
		}
		
		if(profiListValue != null){
			addParameter("profiListValue", findString(profiListValue));
		}
		
		if(searchGroupUrl != null){
			addParameter("searchGroupUrl", findString(searchGroupUrl));
		}
		
		if(searchGroupUrlKey != null){
			addParameter("searchGroupUrlKey", findString(searchGroupUrlKey));
		}
		
		if(subName != null){
			addParameter("subName", findString(subName));
		}
		
		if(subUrl != null){
			addParameter("subUrl", findString(subUrl));
		}
		
		if(subAllGroupUrl != null){
			addParameter("subAllGroupUrl", findString(subAllGroupUrl));
		}
		
		if(subNameValue != null){
			addParameter("subNameValue", findString(subNameValue));
		}
		
		if(subNameKey != null){
			addParameter("subNameKey", findString(subNameKey));
		}
		
		if(subNameParentKey != null){
			addParameter("subNameParentKey", findString(subNameParentKey));
		}
		
	    if(nameStatusInitKey != null){
			addParameter("nameStatusInitKey", findString(nameStatusInitKey));
		}
	    
	    if(nameStatus != null){
			addParameter("nameStatus", findString(nameStatus));
		}
	    
	    if(nameStatusKey != null){
			addParameter("nameStatusKey", findString(nameStatusKey));
		}  

	    if(groupListLabel != null){
			addParameter("groupListLabel", findString(groupListLabel));
		} 
	    
	    if(subNameLabel != null){
			addParameter("subNameLabel", findString(subNameLabel));
		} 
	    
	    if(singleSelect != null){
			addParameter("singleSelect", findString(singleSelect));
		} 
	    
	    if(nameToSaveKey != null){
			addParameter("nameToSaveKey", findString(nameToSaveKey));
		} 
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

	public String getNameList() {
		return nameList;
	}

	public void setNameList(String nameList) {
		this.nameList = nameList;
	}

	public String getNameKey() {
		return nameKey;
	}

	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}

	public String getNameParentKey() {
		return nameParentKey;
	}

	public void setNameParentKey(String nameParentKey) {
		this.nameParentKey = nameParentKey;
	}

	public String getNameProfiKey() {
		return nameProfiKey;
	}

	public void setNameProfiKey(String nameProfiKey) {
		this.nameProfiKey = nameProfiKey;
	}

	public String getNameProfiValue() {
		return nameProfiValue;
	}

	public void setNameProfiValue(String nameProfiValue) {
		this.nameProfiValue = nameProfiValue;
	}

	public String getNameValue() {
		return nameValue;
	}

	public void setNameValue(String nameValue) {
		this.nameValue = nameValue;
	}

	public String getGroupList() {
		return groupList;
	}

	public void setGroupList(String groupList) {
		this.groupList = groupList;
	}
	
	public String getGroupListKey() {
		return groupListKey;
	}

	public void setGroupListKey(String groupListKey) {
		this.groupListKey = groupListKey;
	}

	public String getGroupListValue() {
		return groupListValue;
	}

	public void setGroupListValue(String groupListValue) {
		this.groupListValue = groupListValue;
	}

	public String getProfiList() {
		return profiList;
	}

	public void setProfiList(String profiList) {
		this.profiList = profiList;
	}

	public String getProfiListKey() {
		return profiListKey;
	}

	public void setProfiListKey(String profiListKey) {
		this.profiListKey = profiListKey;
	}

	public String getProfiListValue() {
		return profiListValue;
	}

	public void setProfiListValue(String profiListValue) {
		this.profiListValue = profiListValue;
	}

	public String getSearchGroupUrl() {
		return searchGroupUrl;
	}

	public void setSearchGroupUrl(String searchGroupUrl) {
		this.searchGroupUrl = searchGroupUrl;
	}

	public String getSearchGroupUrlKey() {
		return searchGroupUrlKey;
	}

	public void setSearchGroupUrlKey(String searchGroupUrlKey) {
		this.searchGroupUrlKey = searchGroupUrlKey;
	}

	public String getSubUrl() {
		return subUrl;
	}

	public void setSubUrl(String subUrl) {
		this.subUrl = subUrl;
	}

	public String getSubAllGroupUrl() {
		return subAllGroupUrl;
	}

	public void setSubAllGroupUrl(String subAllGroupUrl) {
		this.subAllGroupUrl = subAllGroupUrl;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getSubNameValue() {
		return subNameValue;
	}

	public void setSubNameValue(String subNameValue) {
		this.subNameValue = subNameValue;
	}

	public String getSubNameKey() {
		return subNameKey;
	}

	public void setSubNameKey(String subNameKey) {
		this.subNameKey = subNameKey;
	}

	public String getSubNameParentKey() {
		return subNameParentKey;
	}

	public void setSubNameParentKey(String subNameParentKey) {
		this.subNameParentKey = subNameParentKey;
	}

	public String getNameStatusInitKey() {
		return nameStatusInitKey;
	}

	public void setNameStatusInitKey(String nameStatusInitKey) {
		this.nameStatusInitKey = nameStatusInitKey;
	}

	public String getNameStatus() {
		return nameStatus;
	}

	public void setNameStatus(String nameStatus) {
		this.nameStatus = nameStatus;
	}

	public String getNameStatusKey() {
		return nameStatusKey;
	}

	public void setNameStatusKey(String nameStatusKey) {
		this.nameStatusKey = nameStatusKey;
	}

	public String getGroupListLabel() {
		return groupListLabel;
	}

	public void setGroupListLabel(String groupListLabel) {
		this.groupListLabel = groupListLabel;
	}

	public String getSubNameLabel() {
		return subNameLabel;
	}

	public void setSubNameLabel(String subNameLabel) {
		this.subNameLabel = subNameLabel;
	}

	public String getSingleSelect() {
		return singleSelect;
	}

	public void setSingleSelect(String singleSelect) {
		this.singleSelect = singleSelect;
	}

	public String getNameToSaveKey() {
		return nameToSaveKey;
	}

	public void setNameToSaveKey(String nameToSaveKey) {
		this.nameToSaveKey = nameToSaveKey;
	}
	
	
}
