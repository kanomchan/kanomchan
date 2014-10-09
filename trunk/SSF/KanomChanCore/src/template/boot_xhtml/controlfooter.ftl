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
	<#if parameters.label??>
		</div>
	</#if>
	</div>
	<#if parameters.errorposition?default("top") == 'bottom'>
	<#if hasFieldErrors>
	<#list fieldErrors[parameters.name] as error>
		<#if parameters.label??>
			<div class="row" <#if parameters.id??>
		   		for="${parameters.id}"
			</#if> >
				<div class="col-md-offset-4 col-md-4">
					<div class="message error" role="alert">${error?html}</div>
				</div>
			</div>
			<#else>
				<div class="message error" role="alert" <#if parameters.id??>
		   		for="${parameters.id}"
			</#if> >${error?html}</div>
		</#if>
	</#list>
	<#else>
		<#if parameters.label??>
			<div class="row" <#if parameters.id??>
		   		for="${parameters.id}"
			</#if> >
				<div style="display:none" class="col-md-offset-4 col-md-4">
					<div class="message error" role="alert"></div>
				</div>
			</div>
			<#else>
				<div style="display:none" class="message error" role="alert" <#if parameters.id??>
		   		for="${parameters.id}"
			</#if> ></div>
		</#if>
	</#if>
	</#if>
</div>

