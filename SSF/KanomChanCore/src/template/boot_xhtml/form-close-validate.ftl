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
START SNIPPET: supported-validators
Only the following validators are supported:
* required validator
* requiredstring validator
* stringlength validator
* regex validator
* email validator
* url validator
* int validator
* double validator
END SNIPPET: supported-validators
-->
<#if ((parameters.validate!false == true) && (parameters.performValidation!false == true))>
<script type="text/javascript">
var validate;
if(validate == undefined){
validate = {}
}
validate.form_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')} = {
id : "form_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}",
getFieldValue : function(field) {
			var type = field.type ? field.type : field[0].type;
            
            if (type == 'select-one' || type == 'select-multiple') {
                return (field.selectedIndex == -1 ? "" : field.options[field.selectedIndex].value);
            } else if (type == 'checkbox' || type == 'radio') {
                if (!field.length) {
                    field = [field];
                }
                for (var i = 0; i < field.length; i++) {
                    if (field[i].checked) {
                        return field[i].value;
                    }
                }
                return "";
            }
            return field.value;
        },
validateList :[
    <#list parameters.tagNames as tagName>
    	<#list tag.getValidators("${tagName}") as aValidator>
{

		<#if aValidator.validatorType = "field-visitor">
			<#assign validator = aValidator.fieldValidator >
        	//visitor validator switched to: ${validator.validatorType}
        <#else>
			<#assign validator = aValidator >
        </#if>
		name:"${tagName?js_string}",
		validatorType:"${validator.validatorType?js_string}",
		message : "${validator.getMessage(action)?js_string}",
		fieldName : "${aValidator.fieldName?js_string}",
		continueValidation : <#if validator.shortCircuit>false<#else>true</#if>,
		validate : function(fieldValue,continueValidation){
			errors = false;
 			<#if validator.validatorType = "required">
				return {errors : (fieldValue == "" || fieldValue == "0" || fieldValue == "-1" || fieldValue == 0 || fieldValue == -1)};
			<#elseif validator.validatorType = "customjs">
				return {errors : ((continueValidation && fieldValue != null && fieldValue != "") && ${validator.call?js_string}(fieldValue) == false)};
            <#elseif validator.validatorType = "requiredstring">
            	return {errors : (continueValidation && fieldValue != null && (fieldValue == "" || fieldValue.replace(/^\s+|\s+$/g,"").length == 0))};
            <#elseif validator.validatorType = "stringlength">

            if (continueValidation && fieldValue != null) {
                var value = fieldValue;
                <#if validator.trim>
                    //trim field value
                    while (value.substring(0,1) == ' ')
                        value = value.substring(1, value.length);
                    while (value.substring(value.length-1, value.length) == ' ')
                        value = value.substring(0, value.length-1);
                </#if>
                if ((${validator.minLength?c} > -1 && value.length < ${validator.minLength?c}) ||
                    (${validator.maxLength?c} > -1 && value.length > ${validator.maxLength?c})) {
                    errors = true;
                }
            }
			return {errors : errors};
            <#elseif validator.validatorType = "regex">
            if (continueValidation && fieldValue != null && !fieldValue.match("${validator.regex?js_string}")) {
                errors = true;
            }
			return {errors : errors};
            <#elseif validator.validatorType = "email">
            if (continueValidation && fieldValue != null && fieldValue.length > 0 && fieldValue.match("${validator.regex?js_string}")==null) {
                errors = true;
            }
			return {errors : errors};
            <#elseif validator.validatorType = "url">
            if (continueValidation && fieldValue != null && fieldValue.length > 0 && fieldValue.match("${validator.regex?js_string}")==null) {
                errors = true;
            }
			return {errors : errors};
            <#elseif validator.validatorType = "int" || validator.validatorType = "short">
            if (continueValidation && fieldValue != null) {
                if (<#if validator.min??>parseInt(fieldValue) <
                     ${validator.min?c}<#else>false</#if> ||
                        <#if validator.max??>parseInt(fieldValue) >
                           ${validator.max?c}<#else>false</#if>) {
                    errors = true;
                }
            }
			return {errors : errors};
            <#elseif validator.validatorType = "double">
            if (continueValidation && fieldValue != null) {
                var value = parseFloat(fieldValue);
                if (<#if validator.minInclusive??>value < ${validator.minInclusive?c}<#else>false</#if> ||
                        <#if validator.maxInclusive??>value > ${validator.maxInclusive?c}<#else>false</#if> ||
                        <#if validator.minExclusive??>value <= ${validator.minExclusive?c}<#else>false</#if> ||
                        <#if validator.maxExclusive??>value >= ${validator.maxExclusive?c}<#else>false</#if> || 
						isNaN(value)) {
                    errors = true;
                }
            }
			return {errors : errors};
            </#if>
            }
},

        </#list>
    </#list>
{}
]
};

var error = new Array();
    function validateForm_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}() {
        <#--
            In case of multiselect fields return only the first value.
        -->
		try{

        //form = document.getElementById("${parameters.id}");
        form = $("#${parameters.id}")[0];
        clearErrorLabels(form);
		var error = new Array();
		var log ={};
		var errors = false;
        var continueValidation = true;
		for (i = 0; i < validate.form_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}.validateList.length; i++) { 
		    validator = validate.form_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}.validateList[i];
				if(validator.validate!=undefined){
			    log.fieldName = validator.fieldName;
				var field = form.elements[validator.fieldName];
			    var fieldValue = validate.form_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}.getFieldValue(field);
			    if(validator.validate!=undefined && validator.validate(fieldValue,continueValidation).errors){
				    if(!validator.continueValidation){
				    	continueValidation = false;
				    }
	            	addError(validator.fieldName, validator.message);
	                errors = true;
					error.push(validator);
			    }
		    }
		    
		}
        

		}catch(err){
		log.err = err;
		//console.log(log);
		return false;
		}
        return !errors;
    }
</script>
</#if>