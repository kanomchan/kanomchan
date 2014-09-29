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
<div class="form-group">
	<#assign hasFieldErrors = parameters.name?? && fieldErrors?? && fieldErrors[parameters.name]??/>
	<#if parameters.errorposition?default("top") == 'top'>
	<#if hasFieldErrors>
	<#list fieldErrors[parameters.name] as error>
		<#if parameters.label??>
			<div class="row">
				<div class="col-md-offset-4 col-md-4">
					<div class="alert alert-danger" role="alert">MESSAGE_9999 : ERROR</div>
				</div>
			</div>
			<#else>
				<div class="alert alert-danger" role="alert">MESSAGE_9999 : ERROR</div>
		</#if>
	</#list>
	</#if>
	</#if>
	<div class="row">
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
			    class="control-label col-md-4 errorLabel"<#t/>
				<#else>
			    class="control-label col-md-4"<#t/>
				</#if>
			</#if>
			
			
		    <#if parameters.id??>
		   		for="${parameters.id}"
			</#if>
			
			><#if parameters.required?default(false) && parameters.requiredPosition?default("right") != 'right'>
        		<span class="required">*</span><#t/>
			</#if> 
			<@s.property value="parameters.label" /> 
			<#if parameters.required?default(false) && parameters.requiredPosition?default("right") == 'right'>
		        <span class="required">*</span><#t/>
			</#if>
			${parameters.labelseparator?default("")?html}</label>
			
			
			<#if parameters.labelposition?default("left") == 'top'>
				</div>
				<div class="row">
			</#if>
		</#if>