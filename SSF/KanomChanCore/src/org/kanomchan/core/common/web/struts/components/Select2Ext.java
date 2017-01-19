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

package org.kanomchan.core.common.web.struts.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.ListUIBean;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * <!-- START SNIPPET: javadoc -->
 *
 * Render an HTML input tag of type Select2Ext.
 *
 * <!-- END SNIPPET: javadoc -->
 *
 * <p/> <b>Examples</b>
 * <pre>
 * <!-- START SNIPPET: example -->
 *
 * &lt;s:Select2Ext label="Pets"
 *        name="petIds"
 *        list="petDao.pets"
 *        listKey="id"
 *        listValue="name"
 *        multiple="true"
 *        size="3"
 *        required="true"
 *        value="%{petDao.pets.{id}}"
 * /&gt;
 *
 * &lt;s:Select2Ext label="Months"
 *        name="months"
 *        headerKey="-1" headerValue="Select Month"
 *        list="#{'01':'Jan', '02':'Feb', [...]}"
 *        value="selectedMonth"
 *        required="true"
 * /&gt;
 *
 * // The month id (01, 02, ...) returned by the getSelectedMonth() call
 * // against the stack will be auto-selected
 *
 * <!-- END SNIPPET: example -->
 * </pre>
 *
 * <p/>
 *
 * <!-- START SNIPPET: exnote -->
 *
 * Note: For any of the tags that use lists (select probably being the most ubiquitous), which uses the OGNL list
 * notation (see the "months" example above), it should be noted that the map key created (in the months example,
 * the '01', '02', etc.) is typed. '1' is a char, '01' is a String, "1" is a String. This is important since if
 * the value returned by your "value" attribute is NOT the same type as the key in the "list" attribute, they
 * WILL NOT MATCH, even though their String values may be equivalent. If they don't match, nothing in your list
 * will be auto-Select2Exted.<p/>
 *
 * <!-- END SNIPPET: exnote -->
 *
 */
@StrutsTag(
    name="Select2Ext",
    tldTagClass="org.kanomchan.core.common.web.struts.view.jsp.ui.Select2ExtTag",
    description="Render a Select2Ext element",
    allowDynamicAttributes=true)
public class Select2Ext extends ListUIBean {
    final public static String TEMPLATE = "Select2Ext";

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
    protected String ajaxRowsPerPage;
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
    protected String nameOfValue;
    protected String nameOfName;
    protected String nameParentValue;
    protected String nameToSave;
    protected String noResults;
    protected String placeholder;

    public Select2Ext(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
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
        
        if (ajax != null) {
            addParameter("ajax", findValue(ajax, Boolean.class));
        }
        
        if(ajaxURL != null){
        	addParameter("ajaxURL", findString(ajaxURL));
        }
        
        if(ajaxDelay != null){
        	addParameter("ajaxDelay", findString(ajaxDelay));
        }
        
        if(ajaxTermName != null){
        	addParameter("ajaxTermName", findString(ajaxTermName));
        }
        
        if(ajaxPageName != null){
        	addParameter("ajaxPageName", findString(ajaxPageName));
        }
        
        if(ajaxParentName != null){
        	addParameter("ajaxParentName", findString(ajaxParentName));
        }
        
        if(ajaxDataResultName != null){
        	addParameter("ajaxDataResultName", findString(ajaxDataResultName));
        }
        
        if(ajaxDataResultValueName != null){
        	addParameter("ajaxDataResultValueName", findString(ajaxDataResultValueName));
        }
        
        if(ajaxDataResultLabelName != null){
        	addParameter("ajaxDataResultLabelName", findString(ajaxDataResultLabelName));
        }
        
        if(ajaxDataResultParentName != null){
        	addParameter("ajaxDataResultParentName", findString(ajaxDataResultParentName));
        }
        
        if(ajaxRowsPerPage != null){
        	addParameter("ajaxRowsPerPage", findString(ajaxRowsPerPage));
        }
        
        if(selectionShortName != null){
        	addParameter("selectionShortName", findString(selectionShortName));
        }
        
        if(selectionShortNameLength != null){
        	addParameter("selectionShortNameLength", findString(selectionShortNameLength));
        }
        
        if(ajaxItemNo != null){
        	addParameter("ajaxItemNo", findString(ajaxItemNo));
        }
        
        if(ajaxTotalCountName != null){
        	addParameter("ajaxTotalCountName", findString(ajaxTotalCountName));
        }
        
        if (templateResultCustom != null) {
            addParameter("templateResultCustom", findValue(templateResultCustom, Boolean.class));
        }
        
        if(templateResultCustomFncName != null){
        	addParameter("templateResultCustomFncName", findString(templateResultCustomFncName));
        }
        
        if (templateSelectionCustom != null) {
            addParameter("templateSelectionCustom", findValue(templateSelectionCustom, Boolean.class));
        }
        
        if(templateSelectionCustomFncName != null){
        	addParameter("templateSelectionCustomFncName", findString(templateSelectionCustomFncName));
        }
        
        if (other != null) {
            addParameter("other", findValue(other, Boolean.class));
        }
        
        if(otherName != null){
        	addParameter("otherName", findString(otherName));
        }
        
        if (closeOnSelect != null) {
            addParameter("closeOnSelect", findValue(closeOnSelect, Boolean.class));
        }
        
        if (otherSuggest != null) {
            addParameter("otherSuggest", findValue(otherSuggest, Boolean.class));
        }
        
        if(otherSuggestWord != null){
        	addParameter("otherSuggestWord", findString(otherSuggestWord));
        }
        
        if(nameOfValue != null){
        	addParameter("nameOfValue", findString(nameOfValue));
        }
        
        if(nameOfName != null){
        	addParameter("nameOfName", findString(nameOfName));
        }
        
        if(nameParentValue != null){
        	addParameter("nameParentValue", findString(nameParentValue));
        }
        
        if(nameToSave != null){
        	addParameter("nameToSave", findString(nameToSave));
        }
        
        if(noResults != null){
        	addParameter("noResults", findString(noResults));
        }
        
        if(placeholder != null){
        	addParameter("placeholder", findString(placeholder));
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
    
    public String getOther() {
		return other;
	}
    
    @StrutsTagAttribute(description="Set the html other attribute on rendered html element", type="Boolean", defaultValue="false")
    public void setOther(String other) {
		this.other = other;
	}
    
    public String getOtherName() {
		return otherName;
	}
    
    @StrutsTagAttribute(description="Set the name of other")
    public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
    
    public String getCloseOnSelect() {
		return closeOnSelect;
	}
    
    @StrutsTagAttribute(description="Set the closeOnSelect", type="Boolean", defaultValue="false")
    public void setCloseOnSelect(String closeOnSelect) {
		this.closeOnSelect = closeOnSelect;
	}
    
    public String getOtherSuggest() {
		return otherSuggest;
	}
    
    @StrutsTagAttribute(description="Set the html otherSuggest", type="Boolean", defaultValue="false")
    public void setOtherSuggest(String otherSuggest) {
		this.otherSuggest = otherSuggest;
	}
    
    public String getOtherSuggestWord() {
		return otherSuggestWord;
	}
    
    @StrutsTagAttribute(description="Set the word of otherSuggestWord")
    public void setOtherSuggestWord(String otherSuggestWord) {
		this.otherSuggestWord = otherSuggestWord;
	}
    
    public String getNameOfValue() {
		return nameOfValue;
	}
    
    @StrutsTagAttribute(description="Set the name Of Value")
    public void setNameOfValue(String nameOfValue) {
		this.nameOfValue = nameOfValue;
	}
    
    public String getNameOfName() {
		return nameOfName;
	}
    
    @StrutsTagAttribute(description="Set the name Of Name")
    public void setNameOfName(String nameOfName) {
		this.nameOfName = nameOfName;
	}
    
    public String getNameParentValue() {
		return nameParentValue;
	}
    
    @StrutsTagAttribute(description="Set the name parent Value")
    public void setNameParentValue(String nameParentValue) {
		this.nameParentValue = nameParentValue;
	}
    
    public String getNameToSave() {
		return nameToSave;
	}
    
    @StrutsTagAttribute(description="Set the name to save")
    public void setNameToSave(String nameToSave) {
		this.nameToSave = nameToSave;
	}
    
    public String getNoResults() {
		return noResults;
	}
    
    @StrutsTagAttribute(description="Set noResults")
    public void setNoResults(String noResults) {
		this.noResults = noResults;
	}
    
    public String getPlaceholder() {
		return placeholder;
	}
    
    @StrutsTagAttribute(description="Set placeholder")
    public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
    
    public String getAjax() {
		return ajax;
	}
    
    @StrutsTagAttribute(description="Set the html ajax attribute on rendered html element", type="Boolean", defaultValue="false")
    public void setAjax(String ajax) {
		this.ajax = ajax;
	}
    
    public String getAjaxURL() {
		return ajaxURL;
	}
    
    @StrutsTagAttribute(description="Set the name of ajaxURL")
    public void setAjaxURL(String ajaxURL) {
		this.ajaxURL = ajaxURL;
	}
    
    public String getAjaxDelay() {
		return ajaxDelay;
	}
    
    @StrutsTagAttribute(description="Set the name of ajaxDelay")
    public void setAjaxDelay(String ajaxDelay) {
		this.ajaxDelay = ajaxDelay;
	}

	public String getAjaxTermName() {
		return ajaxTermName;
	}

	@StrutsTagAttribute(description="Set the name of ajaxTermName")
	public void setAjaxTermName(String ajaxTermName) {
		this.ajaxTermName = ajaxTermName;
	}

	public String getAjaxPageName() {
		return ajaxPageName;
	}

	@StrutsTagAttribute(description="Set the name of ajaxPageName")
	public void setAjaxPageName(String ajaxPageName) {
		this.ajaxPageName = ajaxPageName;
	}

	public String getAjaxItemNo() {
		return ajaxItemNo;
	}

	@StrutsTagAttribute(description="Set the name of ajaxItemNo")
	public void setAjaxItemNo(String ajaxItemNo) {
		this.ajaxItemNo = ajaxItemNo;
	}

	public String getAjaxTotalCountName() {
		return ajaxTotalCountName;
	}

	@StrutsTagAttribute(description="Set the name of ajaxTotalCountName")
	public void setAjaxTotalCountName(String ajaxTotalCountName) {
		this.ajaxTotalCountName = ajaxTotalCountName;
	}

	public String getTemplateResultCustom() {
		return templateResultCustom;
	}

	@StrutsTagAttribute(description="Set the name of templateResultCustom")
	public void setTemplateResultCustom(String templateResultCustom) {
		this.templateResultCustom = templateResultCustom;
	}

	public String getTemplateResultCustomFncName() {
		return templateResultCustomFncName;
	}

	@StrutsTagAttribute(description="Set the name of templateResultCustomFncName")
	public void setTemplateResultCustomFncName(String templateResultCustomFncName) {
		this.templateResultCustomFncName = templateResultCustomFncName;
	}

	public String getTemplateSelectionCustom() {
		return templateSelectionCustom;
	}

	@StrutsTagAttribute(description="Set the name of templateSelectionCustom")
	public void setTemplateSelectionCustom(String templateSelectionCustom) {
		this.templateSelectionCustom = templateSelectionCustom;
	}

	public String getTemplateSelectionCustomFncName() {
		return templateSelectionCustomFncName;
	}

	@StrutsTagAttribute(description="Set the name of templateSelectionCustomFncName")
	public void setTemplateSelectionCustomFncName(String templateSelectionCustomFncName) {
		this.templateSelectionCustomFncName = templateSelectionCustomFncName;
	}

	public String getAjaxDataResultName() {
		return ajaxDataResultName;
	}

	@StrutsTagAttribute(description="Set the name of ajaxDataResultName")
	public void setAjaxDataResultName(String ajaxDataResultName) {
		this.ajaxDataResultName = ajaxDataResultName;
	}

	public String getAjaxDataResultValueName() {
		return ajaxDataResultValueName;
	}

	@StrutsTagAttribute(description="Set the name of ajaxDataResultValueName")
	public void setAjaxDataResultValueName(String ajaxDataResultValueName) {
		this.ajaxDataResultValueName = ajaxDataResultValueName;
	}

	public String getAjaxDataResultLabelName() {
		return ajaxDataResultLabelName;
	}

	@StrutsTagAttribute(description="Set the name of ajaxDataResultLabelName")
	public void setAjaxDataResultLabelName(String ajaxDataResultLabelName) {
		this.ajaxDataResultLabelName = ajaxDataResultLabelName;
	}

	public String getSelectionShortName() {
		return selectionShortName;
	}

	@StrutsTagAttribute(description="Set the name of selectionShortName")
	public void setSelectionShortName(String selectionShortName) {
		this.selectionShortName = selectionShortName;
	}

	public String getSelectionShortNameLength() {
		return selectionShortNameLength;
	}

	@StrutsTagAttribute(description="Set the name of selectionShortNameLength")
	public void setSelectionShortNameLength(String selectionShortNameLength) {
		this.selectionShortNameLength = selectionShortNameLength;
	}

	public String getAjaxDataResultParentName() {
		return ajaxDataResultParentName;
	}

	@StrutsTagAttribute(description="Set the name of ajaxDataResultParentName")
	public void setAjaxDataResultParentName(String ajaxDataResultParentName) {
		this.ajaxDataResultParentName = ajaxDataResultParentName;
	}

	public String getAjaxParentName() {
		return ajaxParentName;
	}

	@StrutsTagAttribute(description="Set the name of ajaxParentName")
	public void setAjaxParentName(String ajaxParentName) {
		this.ajaxParentName = ajaxParentName;
	}

	public String getAjaxRowsPerPage() {
		return ajaxRowsPerPage;
	}

	@StrutsTagAttribute(description="Set the name of ajaxRowsPerPage")
	public void setAjaxRowsPerPage(String ajaxRowsPerPage) {
		this.ajaxRowsPerPage = ajaxRowsPerPage;
	}
    
    
}
