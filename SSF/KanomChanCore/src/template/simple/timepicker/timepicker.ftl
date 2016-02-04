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
<script>
	<#include "css/jquery-clockpicker.min.css">
	<#include "js/jquery-clockpicker.min.js">
</script>

<div class="input-group clockpicker_${parameters.id}" data-donetext=""> 
    <input type="text" name="${parameters.name}" class="form-control" <#if parameters.name?? && stack.findValue(parameters.name)??>value="<@s.date name="stack.findValue(parameters.name)" format="HH:mm"/>"</#if>>
    <span class="input-group-addon">
        <span class="glyphicon glyphicon-time"></span>
    </span>
</div>
<script type="text/javascript">
		$('.clockpicker_${parameters.id}').clockpicker({
			donetext: "<#if parameters.buttonText?? && stack.findValue(parameters.name)??> <@s.text name="${parameters.buttonText}"/> <#else> <@s.text name="COMMON_OK"/> </#if>"
			<#if parameters.autoClose??>,autoclose : ${parameters.autoClose}  </#if>
			<#if parameters.name?? && stack.findValue(parameters.name)??>,default : '<@s.date name="stack.findValue(parameters.name)" format="HH:mm"/>' <#else> ,default : 'now'</#if>
		});
</script>
