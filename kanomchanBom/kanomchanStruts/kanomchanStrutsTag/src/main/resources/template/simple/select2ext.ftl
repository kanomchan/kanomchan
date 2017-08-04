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
 id="${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}"<#rt/>
</#if>
<#include "/${parameters.templateDir}/${parameters.expandTheme}/css.ftl" />
<#if parameters.title??>
 title="${parameters.title?html}"<#rt/>
</#if>
<#if parameters.multiple?default(false)>
 multiple="multiple"<#rt/>
</#if>
<#include "/${parameters.templateDir}/${parameters.expandTheme}/scripting-events.ftl" />
<#include "/${parameters.templateDir}/${parameters.expandTheme}/common-attributes.ftl" />
<#include "/${parameters.templateDir}/${parameters.expandTheme}/dynamic-attributes.ftl" />
>
<#if parameters.headerKey?? && parameters.headerValue??>
    <option value="${parameters.headerKey?html}"
    <#if tag.contains(parameters.nameValue, parameters.headerKey) == true>
    selected="selected"
    </#if>
    ><@s.text name="${parameters.headerValue?html}"/></option>
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

<#if parameters.multiple?default(false)>
  <#if (parameters.id?? && parameters.name??)>
    <input type="hidden" id="__multiselect_${parameters.id?replace(".","_")}" name="__multiselect_${parameters.name?html}" value=""<#rt/>
  </#if>
  <#if (parameters.id?? && !parameters.name??)>
    <input type="hidden" id="__multiselect_${parameters.id?replace(".","_")}" name="__multiselect_${parameters.id?replace(".","_")}" value=""<#rt/>
  </#if>
  <#if ( !parameters.id?? && parameters.name??)>
    <input type="hidden" id="__multiselect_${parameters.id?replace(".","_")}" name="__multiselect_${parameters.id?replace(".","_")}" value=""<#rt/>
  </#if>
   <#if ( !parameters.id?? && !parameters.name??)>
     <input type="hidden" id="" name="" value="" <#rt/>
  </#if>
  
<#if parameters.disabled?default(false)>
 disabled="disabled"<#rt/>
</#if>
 />
</#if>
<#assign nameAtt = "${parameters.name}">
<#assign names = nameAtt?split(".")>
<#assign nameId = names[names?size - 1]>
<#if parameters.otherName??>
    <#assign name = parameters.otherName>
<#else>
	<#assign name = '${nameAtt?replace(nameId,"name")}'>
</#if>

<script>

	var ${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}List = new Array();
	
	var select2name${parameters.id?replace("[.|\\[|\\]]|-","_", "r")} = '${name?html}';
	
	var select2id${parameters.id?replace("[.|\\[|\\]]|-","_", "r")} = '${parameters.name}';
	
	var select2Val${parameters.id?replace("[.|\\[|\\]]|-","_", "r")} = '';
	
	var select2tagName = "";
	var select2option${parameters.id?replace("[.|\\[|\\]]|-","_", "r")} = {
		width: '100%',
		escapeMarkup: function (markup) { return markup; },<#if parameters.maxSelectLength??>maximumSelectionLength: ${parameters.maxSelectLength?html},</#if>
		placeholder: <#if parameters.placeholder??>"${parameters.placeholder?html}"<#else>"<@s.text name='COMMON_INPUT_SELECT' />"</#if><#if parameters.other??!=true><#if parameters.noResults??>,
		"language": {
			"noResults": function(){
				return <#if parameters.noResults??>"${parameters.noResults?html}"<#else>""</#if>;
			}
		}</#if></#if><#if parameters.ajax?default(false)>, 
		ajax: {
	    	url: "${parameters.ajaxURL?html}",
			dataType: 'json',
			delay: <#if parameters.ajaxDelay??>${parameters.ajaxDelay?html}<#else>250</#if>,
			data: function (params) {
				return {
					"<#if parameters.ajaxTermName??>${parameters.ajaxTermName?html}<#else>term</#if>": params.term, // search term
					"<#if parameters.ajaxPageName??>${parameters.ajaxPageName?html}<#else>pagingBean.currentPage</#if>": params.page = params.page || 1<#if parameters.nameParentValue??&&parameters.ajaxDataResultParentName??>,
					<#if parameters.ajaxParentName??>"${parameters.ajaxParentName?html}"<#else>"idParent"</#if>:$("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}").parents("form").find("[name='${parameters.ajaxDataResultParentName?html}']").val()</#if>,
					"pagingBean.rowsPerPage":<#if parameters.ajaxRowsPerPage??>${parameters.ajaxRowsPerPage?html}<#else>5</#if>
				};
			},
			
			processResults: function (data, params) {
				params.page = params.page || 1;
				var results = [];
				if(typeof <#if parameters.ajaxDataResultName??>${parameters.ajaxDataResultName?html}<#else>data.result</#if> !== "undefined" && <#if parameters.ajaxDataResultName??>${parameters.ajaxDataResultName?html}<#else>data.result</#if>){
					$.each(<#if parameters.ajaxDataResultName??>${parameters.ajaxDataResultName?html}<#else>data.result</#if>, function (i, v) {
	                    var o = {};
	                    <#if parameters.showGroup??>
	                    <#if parameters.selectGroup??>
                    	o.id = v.<#if parameters.ajaxDataResultValueName??>${parameters.ajaxDataResultValueName?html}<#else>value</#if>;
						<#else>
						if(<#if parameters.nameParentValue??>v.<#if parameters.ajaxDataResultParentName??>${parameters.ajaxDataResultParentName?html}<#else>idParent</#if></#if> != null){
	                    	o.id = v.<#if parameters.ajaxDataResultValueName??>${parameters.ajaxDataResultValueName?html}<#else>value</#if>;
	                    }
						</#if>
						<#else>
						o.id = v.<#if parameters.ajaxDataResultValueName??>${parameters.ajaxDataResultValueName?html}<#else>value</#if>;
						</#if>
	                    o.text = v.<#if parameters.ajaxDataResultLabelName??>${parameters.ajaxDataResultLabelName?html}<#else>label</#if>;
	                    <#if parameters.nameParentValue??>o.idParent = v.<#if parameters.ajaxDataResultParentName??>${parameters.ajaxDataResultParentName?html}<#else>idParent</#if>;
						<#if parameters.showGroup??></#if>o.disabled = ($.inArray(v.<#if parameters.ajaxDataResultParentName??>${parameters.ajaxDataResultParentName?html}<#else>idParent</#if> + "", ${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}List) != -1 ? true : false);</#if>
	                    results.push(o);
	                })
				}
				return {
					results: results,
					pagination: {
						more: (params.page * <#if parameters.ajaxItemNo??>${parameters.ajaxItemNo?html}<#else>30</#if>) < data.<#if parameters.ajaxTotalCountName??>${parameters.ajaxTotalCountName?html}<#else>pagingBean.totalRows</#if>
					}
				};
			},
			cache: true
		}</#if><#if parameters.templateResultCustom?default(false)>,
		templateResult : <#if parameters.templateResultCustomFncName??>${parameters.templateResultCustomFncName?html}()<#else>function(repo){
			if (repo.loading) return repo.text;
			<#if parameters.showGroup??>
            <#if parameters.selectGroup??>
        	var markup = (repo.idParent == null ? '<span aria-id="' + repo.id + '" class="parent-result">' + repo.text +'</span>' : '<span aria-id="' + repo.id + '" class="child-result" parent="' + repo.idParent + '">' + repo.text +'</span>');
			<#else>
			var markup = (repo.idParent == null ? '<span aria-id="' + repo.id + '" class="parent-result">' + repo.text +'</span>' : '<span aria-id="' + repo.id + '" class="child-result" parent="' + repo.idParent + '">' + repo.text +'</span>');
			</#if>
			<#else>
			var markup = repo.text;
			</#if>
			return markup;
		}</#if></#if><#if parameters.templateSelectionCustom?default(false)>,
		templateSelection : <#if parameters.templateSelectionCustomFncName??>${parameters.templateSelectionCustomFncName?html}()<#else>function(repo){
			var selectionShortName = <#if parameters.selectionShortName??>${parameters.selectionShortName?html}<#else>false</#if>;
			var selectionShortNameLength = <#if parameters.selectionShortNameLength??>${parameters.selectionShortNameLength?html}<#else>20</#if>;
			var selection = repo.text;
			var selectionLength = (typeof selection === typeof undefined ? 0 : selection.length);
			
			if(selectionShortName == true && selectionLength > selectionShortNameLength){
				selection = '<span data-toggle="tooltip" title="' + selection + '">' + selection.substring(0, selectionShortNameLength) + "<span class='select2-selection-short-name-dot active'>...</span></span>";
			}
			return selection;
		}</#if></#if><#if parameters.other?default(false)>,
		tags: true,
		createTag: function (tag) {
			select2tagName = tag.term;
			if($(".js-example-placeholder-multiple").find('[value="'+tag.term+'"]').length > 0){
				return false;
			}
			if($("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}").find('[value="'+tag.term+'"]').length > 0 || $("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}").find("option:contains('"+tag.term+"')").length > 0){
				return false;
			}
			return {
				id: select2tagName,
				text: "<span style='font-weight: bold;'>" + select2tagName + "</span><span class='select2-new-words' style='font-style: italic;font-weight: normal;'><#if parameters.otherSuggest?default(false)><#if parameters.otherSuggestWord?default(false)> ${parameters.otherSuggestWord?html}<#else> (It's new words, Do you want to Add?)</#if></#if></span>",
				isNew : true
			};
		},
		insertTag: function (data, tag) {
			var found = data.some(function (el) {
				return el.id === tag.id || el.text === tag.text;
			});
			if (!found) {
				data.push(tag);
			}
		}</#if><#if parameters.multiple?default(false)>,
		allowClear: true,
		closeOnSelect: false</#if>
	};
	
	$("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}").select2(select2option${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}).on("select2:select", function(e) {
		<#if parameters.other?default(false)>
			<#if parameters.multiple?default(false)>
			if(e.params.data.idParent == null){
				$(".select2-results .select2-results__option .child-result[parent='" + e.params.data.id + "']").parent().attr('aria-disabled', true).removeAttr('aria-selected');
				$(".select2-results .select2-results__option .child-result[parent='" + e.params.data.id + "']").each( function(i, e){
					removeSelected${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}($("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}"),$(e).attr("aria-id"))
				});
			}
			$(this).select2('close');
			if(e.params.data.isNew){
				$(this).find('[value="'+e.params.data.id+'"]').replaceWith('<option selected value="'+e.params.data.id+'">'+e.params.data.id+'</option>');
				var index = ${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}List.length;
				var input = '<input type="hidden" name="${parameters.name?html}[{0}].${parameters.nameOfValue?html}" id="{1}" value="-2" class="${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}Input" /><input type="hidden" name="${parameters.name?html}[{0}].${parameters.nameOfName?html}" value="{2}"/>';
				<#if parameters.nameParentValue??>
					input += '<input type="hidden" name="${parameters.name?html}[{0}].${parameters.nameParentValue?html}" value="{3}"/>';
				</#if>
				var data = "<div id='" + e.params.data.id + "Zone'>" + input.format(index, e.params.data.id, e.params.data.id<#if parameters.nameParentValue??&&ajaxDataResultParentName??>,$("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}").parents("form").find("[name='${parameters.ajaxDataResultParentName?html}']").val() </#if>) + "</div>";
				$("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}HiddenZone").append(data);
				${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}List.push(e.params.data.id.toString());
			}else{
				var index = ${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}List.length;
				var input = '<input type="hidden" name="${parameters.name?html}[{0}].${parameters.nameOfValue?html}" id="{1}" value="{1}" class="${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}Input" /><input type="hidden" name="${parameters.name?html}[{0}].${parameters.nameOfName?html}" value="{2}"/>';
				<#if parameters.nameParentValue??>
					input += '<input type="hidden" name="${parameters.name?html}[{0}].${parameters.nameParentValue?html}" value="{3}"/>';
				</#if> 
				var data = "<div id='" + e.params.data.id + "Zone'>" + input.format(index, e.params.data.id, e.params.data.text<#if parameters.nameParentValue??>,e.params.data.idParent</#if>) + "</div>";
				$("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}HiddenZone").append(data);
				${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}List.push(e.params.data.id.toString());
			}
			select2tagName = "";
			
			$(this).select2('open');
			<#else>
			if(e.params.data.idParent == null){
				$(".select2-results .select2-results__option .child-result[parent='" + e.params.data.id + "']").parent().attr('aria-disabled', true).removeAttr('aria-selected');
				$(".select2-results .select2-results__option .child-result[parent='" + e.params.data.id + "']").each( function(i, e){
					removeSelected${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}($("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}"),$(e).attr("aria-id"))
				});
			}
			if(e.params.data.isNew){
				$(this).find('[value="-2"]').remove();
				$(this).find('[value="'+e.params.data.id+'"]').replaceWith('<option selected value="-2">'+e.params.data.id+'</option>');
				$("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}").val(-2).trigger('change');
				$("[name='${name?html}']").val(e.params.data.id);
			}else{
				$("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}").val(e.params.data.id).trigger('change');
				$("[name='${name?html}']").val(e.params.data.text);
			}
			
			select2tagName = "";
			</#if>
		<#else>
		<#if parameters.multiple?default(false)>
			if(e.params.data.idParent == null){
				$(".select2-results .select2-results__option .child-result[parent='" + e.params.data.id + "']").parent().attr('aria-disabled', true).removeAttr('aria-selected');
				$(".select2-results .select2-results__option .child-result[parent='" + e.params.data.id + "']").each( function(i, e){
					removeSelected${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}($("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}"),$(e).attr("aria-id"))
				});
			}
			$(this).select2('close');
			var index = ${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}List.length;
			var input = '<input type="hidden" name="${parameters.name?html}[{0}].${parameters.nameOfValue?html}" id="{1}" value="{1}" class="${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}Input" /><input type="hidden" name="${parameters.name?html}[{0}].${parameters.nameOfName?html}" value="{2}"/>';
			var data = "<div id='" + e.params.data.id + "Zone'>" + input.format(index, e.params.data.id, e.params.data.text) + "</div>";
			$("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}HiddenZone").append(data);
			${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}List.push(e.params.data.id.toString());
			$(this).select2('open');
		</#if>
		</#if>
		
	}).on("select2:unselect ", function(e) {
		removeSelected${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}($(this), e.params.data.id);
		$(this).select2('close');
		$(this).select2('open');
		if(e.params.data.idParent == null){
			$(".select2-results .select2-results__option .child-result[parent='" + e.params.data.id + "']").parent().attr('aria-selected', false).removeAttr('aria-disabled');
		}
	}).on("select2:open ", function(e) {
		$('[data-toggle="tooltip"]').tooltip({delay: { "show": 100, "hide": 100 }});
		$("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}").next("span").find("input.select2-search__field").attr("placeholder", select2option${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}.placeholder).css({ "width" : "100%" });
	}).on("select2:close ", function(e) {
		$('[data-toggle="tooltip"]').tooltip({delay: { "show": 100, "hide": 100 }});
		$("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}").next("span").find("input.select2-search__field").attr("placeholder", select2option${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}.placeholder).css({ "width" : "100%" });
	});
	
	var removeSelected${parameters.id?replace("[.|\\[|\\]]|-","_", "r")} = function(ele, id){
		var status = $(".${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}Input[id='" + id + "']").parent();
		if(status.children('.status' + id).length > 0){
			status.children('.status' + id).val('I');
		}else{
			status.remove();
		}
		var index = ${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}List.indexOf(id+"");
		if (index > -1) {
		    ${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}List.splice(index, 1);
		}
		ele.find('[value="'+id+'"]').remove();
	}
	
	$(document).ready(function(){
		$("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}").next("span").find("input.select2-search__field").attr("placeholder", select2option${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}.placeholder).css({ "width" : "100%" });
	});
	
</script>
<div id="${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}HiddenZone">
<#if parameters.multiple?default(false)>

<@s.iterator status="sta" value="parameters.nameValue">
<script>
	${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}List.push('<@s.property value='%{${parameters.nameOfValue?html}}' />' + "");
	if($("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")} option[value='<@s.property value='%{${parameters.nameOfValue?html}}' />']").length == 0 && '<@s.property value='%{${parameters.nameOfValue?html}}' />' != ''){
		$("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}").append('<option selected value="<@s.property value='%{${parameters.nameOfValue?html}}' />"><@s.property value="%{${parameters.nameOfName?html}}"/></option>');
	}
</script>
<div id="<@s.property value="%{${parameters.nameOfValue?html}}"/>Zone">
<input type="hidden" name="${parameters.name?html}[<@s.property value="%{#sta.index}"/>].${parameters.nameToSave?html}" value="<@s.property value="%{${parameters.nameToSave?html}}"/>"/>
<input type="hidden" name="${parameters.name?html}[<@s.property value="%{#sta.index}"/>].${parameters.nameOfValue?html}" id="<@s.property value="%{${parameters.nameOfValue?html}}"/>" value="<@s.property value="%{${parameters.nameOfValue?html}}"/>" class="${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}Input" />
<input type="hidden" name="${parameters.name?html}[<@s.property value="%{#sta.index}"/>].${parameters.nameOfName?html}" value="<@s.property value="%{${parameters.nameOfName?html}}"/>"/>
<input type="hidden" name="${parameters.name?html}[<@s.property value="%{#sta.index}"/>].status" class="status<@s.property value="%{${parameters.nameOfValue?html}}"/>" value="<@s.property value="%{status}"/>"/>
<#if parameters.nameParentValue??>
<input type="hidden" name="${parameters.name?html}[<@s.property value="%{#sta.index}"/>].${parameters.nameParentValue?html}" value="<@s.property value="%{${parameters.nameParentValue?html}}"/>"/>
</#if> 
</div>

</@s.iterator> 
<script>
	if(${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}List.length > 0){
		$("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}").val(${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}List).trigger("change");
	}
</script>
<#else>
<#if parameters.other?default(false)>
	<@s.hidden name="${name?html}" id="${parameters.name}Name"/>
</#if>
<#if parameters.name??>
<@s.if test='%{${parameters.name}!=""}'>
<script>
	if($("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")} option[value='<@s.property value='%{${parameters.name}}' />']").length == 0 && '<@s.property value='%{${parameters.name}}' />' != ''){
		$("#${parameters.id?replace("[.|\\[|\\]]|-","_", "r")}").append('<option selected value="<@s.property value='%{${parameters.name}}' />"><@s.property value="%{${name?html}}"/></option>');
	}
</script>
</@s.if>
</#if>
</#if>
</div>