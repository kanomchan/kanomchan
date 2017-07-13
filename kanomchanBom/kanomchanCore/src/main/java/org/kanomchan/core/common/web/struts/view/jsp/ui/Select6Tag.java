package org.kanomchan.core.common.web.struts.view.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;
import org.kanomchan.core.common.web.struts.components.Select6;

import com.opensymphony.xwork2.util.ValueStack;

public class Select6Tag extends AbstractUITag {
	
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
    protected String groupListLabel;
    protected String subNameLabel;
    protected String singleSelect;
    protected String nameToSaveKey;
    protected String minusOneOnRemove;
    protected String nameInitSingle;

	
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Select6(stack, req, res);
    }
	
	protected void populateParams() {
        super.populateParams();

        Select6 Select6 = ((Select6) component);
        Select6.setEmptyOption(emptyOption);
        Select6.setHeaderKey(headerKey);
        Select6.setHeaderValue(headerValue);
        Select6.setMultiple(multiple);
        Select6.setSize(size);
        Select6.setLabel(label);
        Select6.setNameList(nameList);
        Select6.setNameKey(nameKey);
        Select6.setNameParentKey(nameParentKey);
        Select6.setNameProfiKey(nameProfiKey);
        Select6.setNameProfiValue(nameProfiValue);
        Select6.setNameValue(nameValue);
        Select6.setGroupList(groupList);
        Select6.setGroupListKey(groupListKey);
        Select6.setGroupListValue(groupListValue);
        Select6.setProfiList(profiList);
        Select6.setProfiListKey(profiListKey);
        Select6.setProfiListValue(profiListValue);
        Select6.setSearchGroupUrl(searchGroupUrl);
        Select6.setSearchGroupUrlKey(searchGroupUrlKey);
        Select6.setSubUrl(subUrl);
        Select6.setSubAllGroupUrl(subAllGroupUrl);
        Select6.setSubName(subName);
        Select6.setSubNameValue(subNameValue);
        Select6.setSubNameKey(subNameKey);
        Select6.setSubNameParentKey(subNameParentKey);
        Select6.setNameStatusInitKey(nameStatusInitKey);
        Select6.setNameStatus(nameStatus);
        Select6.setNameStatusKey(nameStatusKey);
        Select6.setGroupListLabel(groupListLabel);
        Select6.setSubNameLabel(subNameLabel);
        Select6.setSingleSelect(singleSelect);
        Select6.setNameToSaveKey(nameToSaveKey);
        Select6.setMinusOneOnRemove(minusOneOnRemove);
        Select6.setNameInitSingle(nameInitSingle);
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

	public String getMinusOneOnRemove() {
		return minusOneOnRemove;
	}

	public void setMinusOneOnRemove(String minusOneOnRemove) {
		this.minusOneOnRemove = minusOneOnRemove;
	}

	public String getNameInitSingle() {
		return nameInitSingle;
	}

	public void setNameInitSingle(String nameInitSingle) {
		this.nameInitSingle = nameInitSingle;
	}

	
    
	
    
}
