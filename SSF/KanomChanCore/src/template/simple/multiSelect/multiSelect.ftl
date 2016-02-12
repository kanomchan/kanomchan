<#--
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
-->
<#setting number_format="#.#####">
<style>
	<#include "css/select2.css">
</style>
<style>
	<#include "css/multiSelect.css">
</style>
<#if parameters.color?? || parameters.fontColor??>
	<#if parameters.id??>
		<style>
			.${parameters.id} .select2-container-multi .select2-choices .select2-search-choice {
				background: ${parameters.color?default("#5F64C0")?html} !important;
				color: ${parameters.fontColor?default("#EEE")?html} !important;
				border-radius: 5px !important;
			}
		</style>
		<#elseif parameters.name??>
		<style>
			.${parameters.name} .select2-container-multi .select2-choices .select2-search-choice {
				background: ${parameters.color?default("#5F64C0")?html} !important;
				color: ${parameters.fontColor?default("#EEE")?html} !important;
				border-radius: 5px !important;
			}
		</style>
	</#if>
<#else>
	<#if parameters.id??>
		<style>
			.${parameters.id} .select2-container-multi .select2-choices .select2-search-choice {
				background: ${parameters.color?default("#5F64C0")?html} !important;
				color: ${parameters.fontColor?default("#EEE")?html} !important;
				border-radius: 5px !important;
			}
		</style>
		<#elseif parameters.name??>
		<style>
			.${parameters.name} .select2-container-multi .select2-choices .select2-search-choice {
				background: #5F64C0 !important;
				color: #EEE !important;
				border-radius: 5px !important;
			}
		</style>
	</#if>
</#if>
<#if parameters.id??>
<span class="${parameters.id}">
<#elseif parameters.name??>
<span class="${parameters.name}">
</#if>
<#if parameters.isChoice?default("false") == "true">
	<#if stack.findValue(parameters.choiceName+".choice"+parameters.id)??>
	<#assign checkChoiceKey = stack.findValue(parameters.choiceName+".choice"+parameters.id)>
	<#else>
	<#assign checkChoiceKey = "">
	</#if>
	<div class="radio radio-inline"><input type="radio" 
	<#if parameters.choiceName??>
	name="${parameters.choiceName}.choice${parameters.id}"
	<#else>
	name="${parameters.id}-option"
	</#if>
	 id="${parameters.id}-option1"
	 value="I"
	 <#if checkChoiceKey != "">
	 	<#if checkChoiceKey == "I">
		 	checked="checked"
	 	</#if>
	 	<#else>
	 	<#if parameters.choiceValue?default("I") == "I">
	 		checked="checked"
	 	</#if>
	 </#if>
 	 
	><label for="${parameters.id}-option1" class=""><@s.text name="MULTI_SELECT_SPECIFIC_VALUE"></@s.text></label></div></lt>

	<div class="radio radio-inline"><input type="radio" 
	<#if parameters.choiceName??>
	name="${parameters.choiceName}.choice${parameters.id}"
	<#else>
	name="${parameters.id}-option"
	</#if>
	 id="${parameters.id}-option2"
	 value="E"
	 <#if checkChoiceKey != "">
		 <#if checkChoiceKey == "E">
		 	checked="checked"
		 </#if>
	 	<#else>
	 	<#if parameters.choiceValue?default("I") == "E">
	 		checked="checked"
	 	</#if>
	 </#if>
	><label for="${parameters.id}-option2" class=""><@s.text name="MULTI_SELECT_EXCEPT_VALUE"></@s.text></label></div></lt>
	 
	<div class="radio radio-inline"><input type="radio"
	<#if parameters.choiceName??>
	name="${parameters.choiceName}.choice${parameters.id}"
	<#else>
	name="${parameters.id}-option"
	</#if>
 	id="${parameters.id}-option3"
 	value="A"
 	 <#if checkChoiceKey != "">
 	 	<#if checkChoiceKey == "A">
			checked="checked"
		</#if>
	 	<#else>
	 	<#if parameters.choiceValue?default("I") == "A">
	 		checked="checked"
	 	</#if>
	 </#if>
	><label for="${parameters.id}-option3" class=""><@s.text name="MULTI_SELECT_ALL"></@s.text></label></div></lt>

</#if>
<select<#rt/>
 name="${parameters.name?default("")?html}"<#rt/>
<#if parameters.get("size")??>
 size="${parameters.get("size")?html}"<#rt/>
</#if>
<#if parameters.disabled?default(false)>
 disabled="disabled"<#rt/>
</#if>
<#if parameters.tabindex??>
 tabindex="${parameters.tabindex?html}"<#rt/>
</#if>
<#if parameters.id??>
 id="${parameters.id?html}"<#rt/>
</#if>
<#include "/${parameters.templateDir}/${parameters.expandTheme}/css.ftl" />
<#if parameters.title??>
 title="${parameters.title?html}"<#rt/>
</#if>
<#if parameters.cssClass??>
 class="form-control ${parameters.cssClass?html}"
<#else>
 class="form-control"
</#if>
 multiple
<#include "/${parameters.templateDir}/${parameters.expandTheme}/scripting-events.ftl" />
<#include "/${parameters.templateDir}/${parameters.expandTheme}/common-attributes.ftl" />
<#include "/${parameters.templateDir}/${parameters.expandTheme}/dynamic-attributes.ftl" />
>
<#if parameters.headerKey?? && parameters.headerValue??>
    <option value="${parameters.headerKey?html}"
    <#if tag.contains(parameters.nameValue, parameters.headerKey) == true>
    selected="selected"
    </#if>
    >${parameters.headerValue?html}</option>
</#if>
<#if parameters.emptyOption?default(false)>
    <option value=""></option>
</#if>
<@s.iterator value="parameters.list">
        <#if parameters.listKey??>
            <#if stack.findValue(parameters.listKey)??>
              <#assign itemKey = stack.findValue(parameters.listKey)/>
              <#assign itemKeyStr = stack.findString(parameters.listKey)/>
            <#else>
              <#assign itemKey = ''/>
              <#assign itemKeyStr = ''/>
            </#if>
        <#else>
            <#assign itemKey = stack.findValue('top')/>
            <#assign itemKeyStr = stack.findString('top')>
        </#if>
        <#if parameters.listValue??>
            <#if stack.findString(parameters.listValue)??>
              <#assign itemValue = stack.findString(parameters.listValue)/>
            <#else>
              <#assign itemValue = ''/>
            </#if>
        <#else>
            <#assign itemValue = stack.findString('top')/>
        </#if>
        <#if parameters.listCssClass??>
            <#if stack.findString(parameters.listCssClass)??>
              <#assign itemCssClass= stack.findString(parameters.listCssClass)/>
            <#else>
              <#assign itemCssClass = ''/>
            </#if>
        </#if>
        <#if parameters.listCssStyle??>
            <#if stack.findString(parameters.listCssStyle)??>
              <#assign itemCssStyle= stack.findString(parameters.listCssStyle)/>
            <#else>
              <#assign itemCssStyle = ''/>
            </#if>
        </#if>
        <#if parameters.listTitle??>
            <#if stack.findString(parameters.listTitle)??>
              <#assign itemTitle= stack.findString(parameters.listTitle)/>
            <#else>
              <#assign itemTitle = ''/>
            </#if>
        </#if>
    <option value="${itemKeyStr?html}"<#rt/>
        <#if tag.contains(parameters.nameValue, itemKey) == true>
 selected="selected"<#rt/>
        </#if>
        <#if itemCssClass?if_exists != "">
 class="${itemCssClass?html}"<#rt/>
        </#if>
        <#if itemCssStyle?if_exists != "">
 style="${itemCssStyle?html}"<#rt/>
        </#if>
        <#if itemTitle?if_exists != "">
 title="${itemTitle?html}"<#rt/>
        </#if>
    >${itemValue?html}</option><#lt/>
</@s.iterator>

<#include "/${parameters.templateDir}/${parameters.expandTheme}/optgroup.ftl" />

</select>
<#--
<#if parameters.isChoice?default("false") == "true">
<label class="inline pull-right" style="color:#C9C9C9;">* If you want to select all value please leave it blank </label>
</#if>
-->
<#if stack.findValue('status')??>
<#assign itemStatus = stack.findValue('status')/>
<#else>
	<#assign itemStatus = 'D'/>
</#if>
<span>
<script>
	<#include "js/select2.js">
	
</script> 
<script>
    $("#${parameters.id}").select2({
    	<#if parameters.placeholder??>
    	placeholder: "<@s.text name='${parameters.placeholder}'/>",
		</#if>
    })<#if parameters.itemList?? && parameters.nameKey??>.select2("val", 
         [<@s.iterator value="parameters.itemList" var="item" status="count">"${stack.findValue(parameters.nameKey)}"<@s.if test="#count.last == true"></@s.if><@s.else>,</@s.else></@s.iterator>]);<#else>;</#if>
	$(".${parameters.id}-hidden").remove();
    var split${parameters.id} = $("#${parameters.id}").select2('val');
    for(var i=0;i<split${parameters.id}.length;i++){
		<#if parameters.beanName??>
			$("#${parameters.id}").append("<input type='hidden' value='"+ split${parameters.id}[i] +"' name='${parameters.beanName}["+ i +"].${parameters.nameKey}' class='${parameters.id}-hidden'/>"+
											"<input type='hidden' name='${parameters.beanName}["+ i +"].status' value='${itemStatus}' class='${parameters.id}-hidden'/>"+
											"<input type='hidden' name='__pushdataonremove_${parameters.beanName}["+ i +"].status' value='${parameters.setStatus?default("I")}'/> class='__pushdataonremove_${parameters.id}-hidden'");
		<#else>
			$("#${parameters.id}").append("<input type='hidden' value='"+ split${parameters.id}[i] +"' name='${parameters.name}' class='${parameters.id}-hidden'/>"+
											"<input type='hidden' name='${parameters.name}.status' value='${itemStatus}' class='${parameters.id}-hidden'/>"+
											"<input type='hidden' name='__pushdataonremove_${parameters.name}.status' value='${parameters.setStatus?default("I")}' class='__pushdataonremove_${parameters.id}-hidden'/>");
		</#if>
	}
    $("#${parameters.id}").change(function() {
	    $(".${parameters.id}-hidden").remove();
	    var split${parameters.id} = $("#${parameters.id}").select2('val');
	    for(var i=0;i<split${parameters.id}.length;i++){
	    <#if parameters.beanName??>
			$("#${parameters.id}").append("<input type='hidden' value='"+ split${parameters.id}[i] +"' name='${parameters.beanName}["+ i +"].${parameters.nameKey}' class='${parameters.id}-hidden'/>"+
											"<input type='hidden' name='${parameters.beanName}["+ i +"].status' value='${itemStatus}' class='${parameters.id}-hidden'/>"+
											"<input type='hidden' name='__pushdataonremove_${parameters.beanName}["+ i +"].status' value='${parameters.setStatus?default("I")}' class='__pushdataonremove_${parameters.id}-hidden'/>");
		<#else>
			$("#${parameters.id}").append("<input type='hidden' value='"+ split${parameters.id}[i] +"' name='${parameters.name}' class='${parameters.id}-hidden'/>"+
											"<input type='hidden' name='${parameters.name}.status' value='${itemStatus}' class='${parameters.id}-hidden'/>"+
											"<input type='hidden' name='__pushdataonremove_${parameters.name}.status' value='${parameters.setStatus?default("I")}' class='__pushdataonremove_${parameters.id}-hidden'/>");
		</#if>
	    }
	});
	$("#${parameters.id}-option3").click(function() {
		$("#s2id_${parameters.id?html}").fadeOut(200);
	});
	$("#${parameters.id}-option1, #${parameters.id}-option2").click(function() {
		$("#s2id_${parameters.id?html}").fadeIn(200);
	});
	<#if parameters.isChoice?default("false") == "true">
		<#if checkChoiceKey == "A">
			$("#s2id_${parameters.id?html}").hide();
		</#if>
	</#if>
	<#if checkChoiceKey??>
		<#if checkChoiceKey != "">
		 	<#else>
		 	<#if parameters.choiceValue?default("I") == "A">
				$("#s2id_${parameters.id?html}").hide();
			</#if>
		</#if>
	</#if>
	</script>

<#if parameters.multiple?default(false)>
  <#if (parameters.id?? && parameters.name??)>
    <input type="hidden" id="__multiselect_${parameters.id?html}" name="__multiselect_${parameters.name?html}" value=""<#rt/>
  </#if>
  <#if (parameters.id?? && !parameters.name??)>
    <input type="hidden" id="__multiselect_${parameters.id?html}" name="__multiselect_${parameters.id?html}" value=""<#rt/>
  </#if>
  <#if ( !parameters.id?? && parameters.name??)>
    <input type="hidden" id="__multiselect_${parameters.id?html}" name="__multiselect_${parameters.id?html}" value=""<#rt/>
  </#if>
   <#if ( !parameters.id?? && !parameters.name??)>
     <input type="hidden" id="" name="" value="" <#rt/>
  </#if>
  
<#if parameters.disabled?default(false)>
 disabled="disabled"<#rt/>
</#if>
 />
</#if>

