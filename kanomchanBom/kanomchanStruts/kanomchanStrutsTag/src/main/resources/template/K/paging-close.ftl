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

	<div class="row">
	    <div class="col-md-12">
			<div class="inline">
				<label class="control-label">
				<@s.text name="COMMON_PAGING_SHOW"></@s.text> 
				</label>
				<select 
					class="form-control paging-select inline rowsPerPage_${parameters.id}" 
					id="rowsPerPage_select_${parameters.id}" 
					name="rowsPerPage" 
					onchange="rowsPerPage_${parameters.id}_select()">
					<option value="5"
						<#if parameters.rowsPerPage?default(parameters.defaultShowPerPage) == 5>
							selected="selected"
						</#if>
					>5</option>
					<option value="10"
						<#if parameters.rowsPerPage?default(parameters.defaultShowPerPage) == 10>
							selected="selected"
						</#if>
					>10</option>
					<option value="20"
						<#if parameters.rowsPerPage?default(parameters.defaultShowPerPage) == 20>
							selected="selected"
						</#if>
					>20</option>
					<option value="50"
						<#if parameters.rowsPerPage?default(parameters.defaultShowPerPage) == 50>
							selected="selected"
						</#if>
					>50</option>
					<option value="100"
						<#if parameters.rowsPerPage?default(parameters.defaultShowPerPage) == 100>
							selected="selected"
						</#if>
					>100</option>
				</select>
				<script>
					function rowsPerPage_${parameters.id}_select(){
						$('#currentPage').val('1');
						$('#rowsPerPage_${parameters.id}').val($('#rowsPerPage_select_${parameters.id}').val());
						$('#${parameters.id}').submit();
					}
				</script>
				<label class="control-label">
				<@s.text name="COMMON_PAGING_ROWS"></@s.text>
				</label>
			</div>
			<br class="hidden-md hidden-lg">
			<ul class="pagination pull-right inline">
			<#if parameters.currentPage == 1>
				<li><a class="disabled">&laquo;</a></li>
				<#else>
					<li><a href="#" onclick="$('#currentPage').val('${parameters.previousPage?html}');$('#${parameters.id}').submit();">&laquo;</a></li>
			</#if>
			<@s.set var="breakLoop" value="%{false}" />
	  		<#if (parameters.pageCount &lt; 10)>
		  		<@s.iterator var="count" begin="1" end="${parameters.pageCount}">
		  			<@s.if test="!#breakLoop">
		  				<@s.if test="%{${parameters.currentPage} == #count}">
		  					<li><span><@s.property value="${parameters.currentPage}"/></span></li>
		  				</@s.if>
		  				<@s.else>
		  					<li><a href="#" onclick="$('#currentPage').val('<@s.property value="%{#count}"/>');$('#${parameters.id}').submit();"><@s.property value="%{#count}"/></a></li>
						</@s.else>
						<@s.if test="%{#count > 10}">
							<@s.set var="#breakLoop" value="%{true}" />
						</@s.if> 
					</@s.if>
		  		</@s.iterator>
		  	
		  	<#elseif (parameters.currentPage &lt; 6)>
		  		<@s.iterator var="count" begin="1" end="${parameters.pageCount}">
		  			<@s.if test="!#breakLoop">
		  				<@s.if test="%{${parameters.currentPage} == #count}">
		  					<li><span><@s.property value="${parameters.currentPage}"/></span></li>
		  				</@s.if>
		  				<@s.else>
		  					<li><a href="#" onclick="$('#currentPage').val('<@s.property value="%{#count}"/>');$('#${parameters.id}').submit();"><@s.property value="%{#count}"/></a></li>
						</@s.else>
						<@s.if test="%{#count > 6 && ${parameters.pageCount} > 9}">
							<li><a class="paging-hidden">...</a></li>
							<li><a href="#" onclick="$('#currentPage').val('<@s.property value="${parameters.pageCount-1}"/>');$('#${parameters.id}').submit();"><@s.property value="${parameters.pageCount-1}"/></a></li>
							<li><a href="#" onclick="$('#currentPage').val('<@s.property value="${parameters.pageCount}"/>');$('#${parameters.id}').submit();"><@s.property value="${parameters.pageCount}"/></a></li>
							<@s.set var="breakLoop" value="%{true}" />
						</@s.if> 
					</@s.if>		
		  		</@s.iterator>
			<#elseif (parameters.currentPage &gt; parameters.pageCount - 5)>
		  		<@s.iterator var="count" begin="1" end="${parameters.pageCount}">
		  			<@s.if test="!#breakLoop">
		  				<@s.if test="%{#count == 1}">
		  					<li><a href="#" onclick="$('#currentPage').val('1');$('#${parameters.id}').submit();">1</a></li>
							<li><a href="#" onclick="$('#currentPage').val('2');$('#${parameters.id}').submit();">2</a></li>
							<li><a class="paging-hidden">...</a></li>
		  				</@s.if>
		  				<@s.if test="%{${parameters.currentPage} == #count}">
		  					<li><span><@s.property value="${parameters.currentPage}"/></span></li>
		  				</@s.if>
		  				<@s.elseif test="%{#count > (${parameters.pageCount-7}) }">
		  					<li><a href="#" onclick="$('#currentPage').val('<@s.property value="%{#count}"/>');$('#${parameters.id}').submit();"><@s.property value="%{#count}"/></a></li>
						</@s.elseif>
						<@s.if test="%{#count == ${parameters.pageCount}}">
							<@s.set var="breakLoop" value="%{true}" />
						</@s.if>
					</@s.if>						  			
		  		</@s.iterator>
	  		<#elseif (parameters.currentPage &gt; 5)>
		  		<@s.iterator var="count" begin="1" end="${parameters.pageCount}">
		  			<@s.if test="!#breakLoop">
		  				<@s.if test="%{#count == 1}">
		  					<li><a href="#" onclick="$('#currentPage').val('1');$('#${parameters.id}').submit();">1</a></li>
							<li><a href="#" onclick="$('#currentPage').val('2');$('#${parameters.id}').submit();">2</a></li>
							<li><a class="paging-hidden">...</a></li>
		  				</@s.if>
		  				<@s.if test="%{${parameters.currentPage} == #count}">
		  					<li><span><@s.property value="${parameters.currentPage}"/></span></li>
		  				</@s.if>
		  				<@s.elseif test="%{#count == ${parameters.currentPage+1} || #count == ${parameters.currentPage+2} || #count == ${parameters.currentPage-1} || #count == ${parameters.currentPage-2}}">
		  					<li><a href="#" onclick="$('#currentPage').val('<@s.property value="%{#count}"/>');$('#${parameters.id}').submit();"><@s.property value="%{#count}"/></a></li>
						</@s.elseif>
						<@s.if test="%{#count == ${parameters.pageCount}}">
							<li><a class="paging-hidden">...</a></li>
							<li><a href="#" onclick="$('#currentPage').val('<@s.property value="${parameters.pageCount-1}"/>');$('#${parameters.id}').submit();"><@s.property value="${parameters.pageCount-1}"/></a></li>
							<li><a href="#" onclick="$('#currentPage').val('<@s.property value="${parameters.pageCount}"/>');$('#${parameters.id}').submit();"><@s.property value="${parameters.pageCount}"/></a></li>
							<@s.set var="breakLoop" value="%{true}" />
						</@s.if>
					</@s.if>						  			
				</@s.iterator>
		  	</#if>
			<#if parameters.currentPage == parameters.pageCount>
				<li><a class="disabled">&raquo;</a></li>
				<#else>
					<li><a href="#" onclick="$('#currentPage').val('${parameters.nextPage?html}');$('#${parameters.id}').submit();">&raquo;</a></li>
			</#if>
			<@s.hidden id="currentPage" name="pagingBean.currentPage"></@s.hidden>
			<@s.hidden id="rowsPerPage_${parameters.id}" name="pagingBean.rowsPerPage" value="${parameters.rowsPerPage?default(parameters.defaultShowPerPage)}"></@s.hidden>
			</ul>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="inline">
				<label class="control-label">
				<@s.text name="COMMON_PAGING_SHOWING"></@s.text> ${(parameters.currentPage * parameters.rowsPerPage - parameters.rowsPerPage + 1)} <@s.text name="COMMON_PAGING_TO"></@s.text> 
				<@s.if test="%{${parameters.currentPage} == ${parameters.pageCount}}">
					${parameters.totalRows}
				</@s.if>
				<@s.else>
					${parameters.currentPage * parameters.rowsPerPage}
				</@s.else>
					 <@s.text name="COMMON_PAGING_FROM_TOTAL"></@s.text> ${parameters.totalRows} <@s.text name="COMMON_PAGING_ROWS"></@s.text>
				</label>
			</div>
		</div>
	</div>
</form>
<#if (parameters.customOnsubmitEnabled?if_exists)>
<script type="text/javascript">
<#-- 
  Enable auto-select of optiontransferselect tag's entries upon containing form's 
  submission.
-->
<#if (parameters.optiontransferselectIds?if_exists?size > 0)>
	var containingForm = document.getElementById("${parameters.id}");
	<#assign selectObjIds = parameters.optiontransferselectIds.keySet() />
	<#list selectObjIds as selectObjectId>
		StrutsUtils.addEventListener(containingForm, "submit", 
			function(evt) {
				var selectObj = document.getElementById("${selectObjectId}");
				<#if parameters.optiontransferselectIds.get(selectObjectId)??>
					<#assign selectTagHeaderKey = parameters.optiontransferselectIds.get(selectObjectId)/>
					selectAllOptionsExceptSome(selectObj, "key", "${selectTagHeaderKey}");
				<#else>
					selectAllOptionsExceptSome(selectObj, "key", "");
				</#if>
			}, true);
	</#list>
</#if>
<#if (parameters.inputtransferselectIds?if_exists?size > 0)>
	var containingForm = document.getElementById("${parameters.id}");
	<#assign selectObjIds = parameters.inputtransferselectIds.keySet() />
	<#list selectObjIds as selectObjectId>
		StrutsUtils.addEventListener(containingForm, "submit",
			function(evt) {
				var selectObj = document.getElementById("${selectObjectId}");
				<#if parameters.inputtransferselectIds.get(selectObjectId)??>
					<#assign selectTagHeaderKey = parameters.inputtransferselectIds.get(selectObjectId)/>
					selectAllOptionsExceptSome(selectObj, "key", "${selectTagHeaderKey}");
				<#else>
					selectAllOptionsExceptSome(selectObj, "key", "");
				</#if>
			}, true);
	</#list>
</#if>
<#if (parameters.optiontransferselectDoubleIds?if_exists?size > 0)>
	var containingForm = document.getElementById("${parameters.id}");
	<#assign selectDoubleObjIds = parameters.optiontransferselectDoubleIds.keySet() />
	<#list selectDoubleObjIds as selectObjId>
		StrutsUtils.addEventListener(containingForm, "submit", 
			function(evt) {
				var selectObj = document.getElementById("${selectObjId}");
				<#if parameters.optiontransferselectDoubleIds.get(selectObjId)??>
					<#assign selectTagHeaderKey = parameters.optiontransferselectDoubleIds.get(selectObjId)/>
					selectAllOptionsExceptSome(selectObj, "key", "${selectTagHeaderKey}");
				<#else>
					selectAllOptionsExceptSome(selectObj, "key", "");
				</#if>
			}, true);
	</#list>
</#if>


<#--
	Enable auto-select of all elements of updownselect tag upon its containing form
	submission
-->
<#if (parameters.updownselectIds?if_exists?size > 0)>
	var containingForm = document.getElementById("${parameters.id}");
	<#assign tmpIds = parameters.updownselectIds.keySet() />
	<#list tmpIds as tmpId>
		StrutsUtils.addEventListener(containingForm, "submit", 
			function(evt) {
				var updownselectObj = document.getElementById("${tmpId}");
				<#if parameters.updownselectIds.get(tmpId)??>
					<#assign tmpHeaderKey = parameters.updownselectIds.get(tmpId) />
					selectAllOptionsExceptSome(updownselectObj, "key", "${tmpHeaderKey}");
				<#else>
					selectAllOptionsExceptSome(updownselectObj, "key", "");
				</#if>
			}, true);
	</#list>
</#if>
</script>
</#if>


<#-- 
 Code that will add javascript needed for tooltips
--><#t/>
<#if (parameters.hasTooltip?default(false))><#t/>
	<#lt/><!-- javascript that is needed for tooltips -->
	<#lt/><script type="text/javascript" src='<@s.url value="/struts/domTT.js" includeParams="none" encode="false" />'></script>
	<#lt/><link rel="stylesheet" type="text/css" href="<@s.url value="/struts/domTT.css" includeParams="none" encode="false" />"/>
	
</#if><#t/>