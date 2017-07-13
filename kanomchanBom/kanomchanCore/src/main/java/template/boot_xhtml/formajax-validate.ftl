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
<script>
		//555555
	</script>
<#if parameters.editview?default(false) == true && parameters.validate?default(false) == true >
	<script type="text/javascript" src="${base}/struts/boot_xhtml/validation.js"></script>
	<script type="text/javascript" src="${base}/struts/utils.js"></script>
	<script type="text/javascript" src="${base}/struts/boot_xhtml/ajax.js"></script>
	<#if parameters.onsubmit??>
		${tag.addParameter('onsubmit', "${parameters.onsubmit}; return submitForm_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}(validateForm_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}());")}
	<#else>
		${tag.addParameter('onsubmit', "return submitForm_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}(validateForm_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}());")}
	</#if>
<#elseif parameters.validate?default(false) == true>
	<script type="text/javascript" src="${base}/struts/boot_xhtml/validation.js"></script>
	<script type="text/javascript" src="${base}/struts/utils.js"></script>
	<#if parameters.onsubmit??><script>
		//555555 ${parameters.onsubmit}
	</script>

		${tag.addParameter('onsubmit', "return validateForm_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}() && ${parameters.onsubmit};")}
	<#else>
		${tag.addParameter('onsubmit', "return validateForm_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}();")}
	</#if>
<#elseif parameters.editview?default(false) == true>
	<script type="text/javascript" src="${base}/struts/boot_xhtml/ajax.js"></script>
	<#if parameters.onsubmit??>
		${tag.addParameter('onsubmit', "${parameters.onsubmit}; return submitForm_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}(true);")}
	<#else>
		${tag.addParameter('onsubmit', "return submitForm_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}(true);")}
</#if>
</#if>
<#if parameters.editview?default(false) == true && parameters.validate?default(false) == true >
<#if parameters.onreset??>
	${tag.addParameter('onreset', "${parameters.onreset}; clearErrorMessages(this); clearErrorLabels(this); close_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}();")}
<#else>
	${tag.addParameter('onreset', "clearErrorMessages(this); clearErrorLabels(this); close_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}();")}
</#if>
<#elseif parameters.editview?default(false)>

<#if parameters.onreset??>
	${tag.addParameter('onreset', "${parameters.onreset}; close_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}();")}
<#else>
	${tag.addParameter('onreset', "close_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}();")}
</#if>
<#elseif parameters.validate?default(false)>
<#if parameters.onreset??>
	${tag.addParameter('onreset', "${parameters.onreset}; clearErrorMessages(this);clearErrorLabels(this);")}
<#else>
	${tag.addParameter('onreset', "clearErrorMessages(this);clearErrorLabels(this);")}
</#if>
</#if>

<#--
<#if parameters.validate?default(false) == true>
	<script type="text/javascript" src="${base}/struts/boot_xhtml/validation.js"></script>
	<script type="text/javascript" src="${base}/struts/utils.js"></script>
	<#if parameters.onsubmit??>
		${tag.addParameter('onsubmit', "${parameters.onsubmit}; return validateForm_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}();")}
	<#else>
		${tag.addParameter('onsubmit', "return validateForm_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}();")}
	</#if>
</#if>
-->