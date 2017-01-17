/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.kanomchan.core.common.web.struts.view.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTagAttribute;
import org.apache.struts2.views.jsp.ui.AbstractRequiredListTag;
import org.kanomchan.core.common.web.struts.components.Select2Ext;

import com.opensymphony.xwork2.util.ValueStack;


/**
 * @see Select2Ext
 */
public class Select2ExtTag extends AbstractRequiredListTag {

    private static final long serialVersionUID = 6121715260335609618L;

    protected String emptyOption;
    protected String headerKey;
    protected String headerValue;
    protected String multiple;
    protected String size;
    protected String ajax;
    protected String ajaxURL;
    protected String ajaxDelay;
    protected String ajaxTermName;
    protected String ajaxPageName;
    protected String ajaxParentName;
    protected String ajaxDataResultName;
    protected String ajaxDataResultValueName;
    protected String ajaxDataResultLabelName;
    protected String ajaxDataResultParentName;
    protected String selectionShortName;
    protected String selectionShortNameLength;
    protected String ajaxItemNo;
    protected String ajaxTotalCountName;
    protected String templateResultCustom;
    protected String templateResultCustomFncName;
    protected String templateSelectionCustom;
    protected String templateSelectionCustomFncName;
    protected String other;
    protected String otherName;
    protected String closeOnSelect;
    protected String otherSuggest;
    protected String otherSuggestWord;
    protected String nameOfName;
    protected String nameOfValue;
    protected String nameParentValue;
    protected String nameToSave;
    protected String noResults;
    protected String placeholder;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Select2Ext(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();

        Select2Ext select = ((Select2Ext) component);
        select.setEmptyOption(emptyOption);
        select.setHeaderKey(headerKey);
        select.setHeaderValue(headerValue);
        select.setMultiple(multiple);
        select.setSize(size);
        select.setAjax(ajax);
        select.setAjaxURL(ajaxURL);
        select.setAjaxDelay(ajaxDelay);
        select.setAjaxPageName(ajaxPageName);
        select.setAjaxParentName(ajaxParentName);
        select.setAjaxDataResultName(ajaxDataResultName);
        select.setAjaxDataResultValueName(ajaxDataResultValueName);
        select.setAjaxDataResultLabelName(ajaxDataResultLabelName);
        select.setAjaxDataResultParentName(ajaxDataResultParentName);
        select.setSelectionShortName(selectionShortName);
        select.setSelectionShortNameLength(selectionShortNameLength);
        select.setAjaxTermName(ajaxTermName);
        select.setAjaxItemNo(ajaxItemNo);
        select.setAjaxTotalCountName(ajaxTotalCountName);
        select.setTemplateResultCustom(templateResultCustom);
        select.setTemplateResultCustomFncName(templateResultCustomFncName);
        select.setTemplateSelectionCustom(templateSelectionCustom);
        select.setTemplateSelectionCustomFncName(templateSelectionCustomFncName);
        select.setOther(other);
        select.setOtherName(otherName);
        select.setCloseOnSelect(closeOnSelect);
        select.setOtherSuggest(otherSuggest);
        select.setOtherSuggestWord(otherSuggestWord);
        select.setNameOfName(nameOfName);
        select.setNameOfValue(nameOfValue);
        select.setNameParentValue(nameParentValue);
        select.setNameToSave(nameToSave);
        select.setNoResults(noResults);
        select.setPlaceholder(placeholder);
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
    
    public String getAjax() {
		return ajax;
	}
    
    public void setAjax(String ajax) {
		this.ajax = ajax;
	}
    
    public String getAjaxURL() {
		return ajaxURL;
	}
    
    public void setAjaxURL(String ajaxURL) {
		this.ajaxURL = ajaxURL;
	}
    
    public String getAjaxDelay() {
		return ajaxDelay;
	}
    
    public void setAjaxDelay(String ajaxDelay) {
		this.ajaxDelay = ajaxDelay;
	}
    
    public String getOther() {
		return other;
	}
    
    public void setOther(String other) {
		this.other = other;
	}
    
    public String getOtherName() {
		return otherName;
	}
    
    public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
    
    public String getCloseOnSelect() {
		return closeOnSelect;
	}
    
    public void setCloseOnSelect(String closeOnSelect) {
		this.closeOnSelect = closeOnSelect;
	}
    
    public String getOtherSuggest() {
		return otherSuggest;
	}
    
    public void setOtherSuggest(String otherSuggest) {
		this.otherSuggest = otherSuggest;
	}
    
    public String getOtherSuggestWord() {
		return otherSuggestWord;
	}
    
    public void setOtherSuggestWord(String otherSuggestWord) {
		this.otherSuggestWord = otherSuggestWord;
	}
    
    public String getNameOfName() {
		return nameOfName;
	}
    
    public void setNameOfName(String nameOfName) {
		this.nameOfName = nameOfName;
	}
    
    public String getNameOfValue() {
		return nameOfValue;
	}
    
    public void setNameOfValue(String nameOfValue) {
		this.nameOfValue = nameOfValue;
	}
    
    public String getNameParentValue() {
		return nameParentValue;
	}
    
    public void setNameParentValue(String nameParentValue) {
		this.nameParentValue = nameParentValue;
	}
    
    public String getNameToSave() {
		return nameToSave;
	}
    
    public void setNameToSave(String nameToSave) {
		this.nameToSave = nameToSave;
	}
    
    public String getNoResults() {
		return noResults;
	}
    
    public void setNoResults(String noResults) {
		this.noResults = noResults;
	}

    public String getPlaceholder() {
		return placeholder;
	}
    
    public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getAjaxTermName() {
		return ajaxTermName;
	}

	public void setAjaxTermName(String ajaxTermName) {
		this.ajaxTermName = ajaxTermName;
	}

	public String getAjaxPageName() {
		return ajaxPageName;
	}

	public void setAjaxPageName(String ajaxPageName) {
		this.ajaxPageName = ajaxPageName;
	}

	public String getAjaxItemNo() {
		return ajaxItemNo;
	}

	public void setAjaxItemNo(String ajaxItemNo) {
		this.ajaxItemNo = ajaxItemNo;
	}

	public String getAjaxTotalCountName() {
		return ajaxTotalCountName;
	}

	public void setAjaxTotalCountName(String ajaxTotalCountName) {
		this.ajaxTotalCountName = ajaxTotalCountName;
	}

	public String getTemplateResultCustom() {
		return templateResultCustom;
	}

	public void setTemplateResultCustom(String templateResultCustom) {
		this.templateResultCustom = templateResultCustom;
	}

	public String getTemplateResultCustomFncName() {
		return templateResultCustomFncName;
	}

	public void setTemplateResultCustomFncName(String templateResultCustomFncName) {
		this.templateResultCustomFncName = templateResultCustomFncName;
	}

	public String getTemplateSelectionCustom() {
		return templateSelectionCustom;
	}

	public void setTemplateSelectionCustom(String templateSelectionCustom) {
		this.templateSelectionCustom = templateSelectionCustom;
	}

	public String getTemplateSelectionCustomFncName() {
		return templateSelectionCustomFncName;
	}

	public void setTemplateSelectionCustomFncName(String templateSelectionCustomFncName) {
		this.templateSelectionCustomFncName = templateSelectionCustomFncName;
	}

	public String getAjaxDataResultName() {
		return ajaxDataResultName;
	}

	public void setAjaxDataResultName(String ajaxDataResultName) {
		this.ajaxDataResultName = ajaxDataResultName;
	}

	public String getAjaxDataResultValueName() {
		return ajaxDataResultValueName;
	}

	public void setAjaxDataResultValueName(String ajaxDataResultValueName) {
		this.ajaxDataResultValueName = ajaxDataResultValueName;
	}

	public String getAjaxDataResultLabelName() {
		return ajaxDataResultLabelName;
	}

	public void setAjaxDataResultLabelName(String ajaxDataResultLabelName) {
		this.ajaxDataResultLabelName = ajaxDataResultLabelName;
	}

	public String getSelectionShortName() {
		return selectionShortName;
	}

	public void setSelectionShortName(String selectionShortName) {
		this.selectionShortName = selectionShortName;
	}

	public String getSelectionShortNameLength() {
		return selectionShortNameLength;
	}

	public void setSelectionShortNameLength(String selectionShortNameLength) {
		this.selectionShortNameLength = selectionShortNameLength;
	}

	public String getAjaxDataResultParentName() {
		return ajaxDataResultParentName;
	}

	public void setAjaxDataResultParentName(String ajaxDataResultParentName) {
		this.ajaxDataResultParentName = ajaxDataResultParentName;
	}

	public String getAjaxParentName() {
		return ajaxParentName;
	}

	public void setAjaxParentName(String ajaxParentName) {
		this.ajaxParentName = ajaxParentName;
	}
    
}
