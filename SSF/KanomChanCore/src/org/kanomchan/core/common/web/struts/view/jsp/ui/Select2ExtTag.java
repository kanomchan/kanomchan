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
    protected String other;
    protected String otherName;
    protected String closeOnSelect;
    protected String otherSuggest;
    protected String otherSuggestWord;
    protected String nameOfName;
    protected String nameOfValue;
    protected String nameParentValue;
    protected String nameToSave;

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
        select.setOther(other);
        select.setOtherName(otherName);
        select.setCloseOnSelect(closeOnSelect);
        select.setOtherSuggest(otherSuggest);
        select.setOtherSuggestWord(otherSuggestWord);
        select.setNameOfName(nameOfName);
        select.setNameOfValue(nameOfValue);
        select.setNameParentValue(nameParentValue);
        select.setNameToSave(nameToSave);
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

}
