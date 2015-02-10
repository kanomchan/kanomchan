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
<#--
	Only show message if errors are available.
	This will be done if ActionSupport is used.
-->
<#assign hasFieldErrors = parameters.name?? && fieldErrors?? && fieldErrors[parameters.name]??/>
<div class="form-group" errorPosition="${parameters.errorposition?default("top")}">
	<#if parameters.errorposition?default("top") == 'top'>
	<#if hasFieldErrors>
	<#list fieldErrors[parameters.name] as error>
		<#if parameters.label??>
			<div class="rowError" <#if parameters.name??> hasLabel="true" name="${parameters.name?html}Error"<#rt/></#if>
			<#if parameters.id??>
		   		for="${parameters.id}"
			</#if> >
				<div class="col-md-offset-4 col-md-4">
					<div class="message warning" role="alert">${error?html}</div>
				</div>
			</div>
		<#else>
				<div class="message warning" name="${parameters.name?html}Error" role="alert" <#if parameters.id??>
		   		for="${parameters.id}"
			</#if> >${error?html}</div>
		</#if>
	</#list>
	<#else>
		<#if parameters.label??>
			<div class="rowError" <#if parameters.name??> hasLabel="true" name="${parameters.name?html}Error"<#rt/></#if>
			<#if parameters.id??>
		   		for="${parameters.id}"
			</#if> >
				<div class="col-md-offset-4 col-md-4">
					<div style="display:none" class="message warning" role="alert"></div>
				</div>
			</div>
		<#else>
			<div style="display:none" class="message warning"  <#if parameters.name??>name="${parameters.name?html}Error"</#if> role="alert" <#if parameters.id??> for="${parameters.id}"</#if> ></div>
		</#if>
	</#if>
	</#if>
	<div <#-- class="row"  20140910 Edit by Bee  -->>
		<#if parameters.label??>
			<label
			<#if parameters.labelposition?default("left") == 'top'>
				<#if hasFieldErrors>
			    class="col-md-12 errorLabel"<#t/>
				<#else>
			    class="col-md-12"<#t/>
				</#if>
			</#if>
			<#if parameters.labelposition?default("left") == 'left'>
				<#if hasFieldErrors>
			    class="control-label col-md-<#if (parameters.title?default("6")?number < 9)>4<#elseif (parameters.title?default("6")?number == 12)>12 text-left<#else> ${12-parameters.title?default("8")?number}</#if> errorLabel"<#t/>
				<#else>
			    class="control-label col-md-<#if (parameters.title?default("6")?number < 9)>4<#elseif (parameters.title?default("6")?number == 12)>12 text-left<#else>${12-parameters.title?default("8")?number}</#if>"<#t/>
				</#if>
			</#if>
		    <#if parameters.id??>
		   		for="${parameters.id}"
			</#if>
			><#if parameters.required?default(false) && parameters.requiredPosition?default("right") != 'right'>
        		<span class="required">*</span>
			</#if> 
			<@s.property value="parameters.label" /> 
			<#if parameters.required?default(false) && parameters.requiredPosition?default("right") == 'right'>
		        <span class="required">*</span>
			</#if>
			${parameters.labelseparator?default("")?html}</label>
			<#if parameters.labelposition?default("left") == 'top'>
				</div>
				<div <#-- class="row"  20140910 Edit by Bee  -->>
			</#if>
		</#if>