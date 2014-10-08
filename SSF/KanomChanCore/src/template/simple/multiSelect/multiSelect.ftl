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
				background: ${parameters.color?default("#3ECC4F")?html} !important;
				color: ${parameters.fontColor?default("#EEE")?html} !important;
				border-radius: 5px !important;
			}
		
		</style>
		<#elseif parameters.name??>
		<style>
			.${parameters.name} .select2-container-multi .select2-choices .select2-search-choice {
				background: ${parameters.color?default("#3ECC4F")?html} !important;
				color: ${parameters.fontColor?default("#EEE")?html} !important;
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
<span>
<script>
	<#include "js/select2.min.js"> 
</script>
<script>
    $("#${parameters.id}").select2({
    	placeholder: "${parameters.placeholder}",
    })<#if parameters.itemList??>.select2("val", 
         [<@s.iterator value="parameters.itemList" var="item" status="count">"${stack.findValue(parameters.nameValue)}"<@s.if test="#count.last == true"></@s.if><@s.else>,</@s.else></@s.iterator>]);<#else>;</#if>
	$(".${parameters.id}-hidden").remove();
    var split${parameters.id} = $("#${parameters.id}").select2('val');
    for(var i=0;i<split${parameters.id}.length;i++){
		$("#${parameters.id}").append("<input type='hidden' value='"+ split${parameters.id}[i] +"' name='${parameters.beanName}["+ i +"].${parameters.name}' class='${parameters.id}-hidden'/>");
    }
    $("#${parameters.id}").change(function() {
	    $(".${parameters.id}-hidden").remove();
	    var split${parameters.id} = $("#${parameters.id}").select2('val');
	    for(var i=0;i<split${parameters.id}.length;i++){
			$("#${parameters.id}").append("<input type='hidden' value='"+ split${parameters.id}[i] +"' name='${parameters.beanName}["+ i +"].${parameters.name}' class='${parameters.id}-hidden'/>");
	    }
	});
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

