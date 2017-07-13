package org.kanomchan.core.common.web.struts.view.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;
import org.kanomchan.core.common.web.struts.components.Select5r;

import com.opensymphony.xwork2.util.ValueStack;

public class Select5rTag extends AbstractUITag {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6935066783816732675L;
	protected String emptyOption;
    protected String headerKey;
    protected String headerValue;
    protected String multiple;
    protected String size;
    protected String label;
    protected String nameList;
    protected String nameKey;
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
	
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Select5r(stack, req, res);
    }
	
	protected void populateParams() {
        super.populateParams();

        Select5r select5r = ((Select5r) component);
        select5r.setEmptyOption(emptyOption);
        select5r.setHeaderKey(headerKey);
        select5r.setHeaderValue(headerValue);
        select5r.setMultiple(multiple);
        select5r.setSize(size);
        select5r.setLabel(label);
        select5r.setNameList(nameList);
        select5r.setNameKey(nameKey);
        select5r.setNameParentKey(nameParentKey);
        select5r.setNameProfiKey(nameProfiKey);
        select5r.setNameProfiValue(nameProfiValue);
        select5r.setNameValue(nameValue);
        select5r.setGroupList(groupList);
        select5r.setGroupListKey(groupListKey);
        select5r.setGroupListValue(groupListValue);
        select5r.setProfiList(profiList);
        select5r.setProfiListKey(profiListKey);
        select5r.setProfiListValue(profiListValue);
        select5r.setSearchGroupUrl(searchGroupUrl);
        select5r.setSearchGroupUrlKey(searchGroupUrlKey);
        select5r.setSubUrl(subUrl);
        select5r.setSubAllGroupUrl(subAllGroupUrl);
        select5r.setSubName(subName);
        select5r.setSubNameValue(subNameValue);
        select5r.setSubNameKey(subNameKey);
        select5r.setSubNameParentKey(subNameParentKey);
        select5r.setNameStatusInitKey(nameStatusInitKey);
        select5r.setNameStatus(nameStatus);
        select5r.setNameStatusKey(nameStatusKey);
    }
	
	public void setEmptyOption(String emptyOption) {
        this.emptyOption = emptyOption;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

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
    
	
    
}
