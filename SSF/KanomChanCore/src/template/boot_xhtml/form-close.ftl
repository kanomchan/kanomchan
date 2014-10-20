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
<#include "/${parameters.templateDir}/${parameters.expandTheme}/control-close.ftl" />
<#include "/${parameters.templateDir}/simple/form-close.ftl" />
<#include "/${parameters.templateDir}/${parameters.expandTheme}/form-close-validate.ftl" />
<#if parameters.focusElement?if_exists != "">
<script type="text/javascript">
    StrutsUtils.addOnLoad(function() {
        var element = document.getElementById("${parameters.focusElement?html}");
        if(element) {
            element.focus();
        }
    });
</script>
</#if>

<#if parameters.editview?? && parameters.action?? >
	<script type="text/javascript">
		jQuery(document).ready(function() {
			formId = "${parameters.id?html}";
			formName = "${parameters.name?html}";
			//form = $("form[name='"+formName+"']");
			console.log("form name :"+formId);
			console.log("action url : ${parameters.action?html}");
			${parameters.id?html}Editable();
			form.prepend(
    			'<div class="box-align-center" id="editable-edit${parameters.id?html}"> '
					+'<p><input type="button" Class="btn btn-save pull-right" id="btnEdit${parameters.id?html}" value="Edit"/></p>'
				+'</div>'
    		);
    		
    		form.append(
    			'<div class="box-align-center" id="editable-save${parameters.id?html}" style="display:none;"> '
					+'<p><input type="button" class="btn btn-primary" id="btnSave${parameters.id?html}" value="Save"/>'
					+'<input type="button" class="btn btn-default"  id="btnCancel${parameters.id?html}" value="Cancel"/></p>'
				+'</div>'
	    	);
				
			function ${parameters.id?html}Editable(){
				form = $("#${parameters.id?html}");
			<#list parameters.tagNames as tagName>
				var fieldName = "${tagName?replace(".", "\\\\.")}";
				var field = $("#" + fieldName);
				var valueName = "";
				field.hide();
				if(field.is("input[type=radio]")){
					field.next("label").hide();
					if(field.is(":checked")){
						valueName = field.next("label").text();
					}
					else{
						valueName = "";
					}
				}else if(field.is("input[type=checkbox]")){
	    			if(field.is(":checked"))
	    				valueName = field.text();
	    			else{
	    				valueName = "";
	    				field.next().next().hide();
	    			}
	    		}else if(field.is("select")){
	    			if(field.val() == 0)
	    				if(valueName != "-")
	    					valueName = "-";
	    			else
	    				valueName = field.find("option:selected").text();
	    		}else if(field.is("input")){
	    			valueName = field.val();
	    			console.log(field.val());
	    		}
	    			
	    		
	    		
				if(field.parent().find('p.value').length){
					field.parent().find('p.value').text(valueName);
					field.parent().find('p.value').show();
				}else{
					field.parent().append('<p class="value" style="margin-top:6px;">'+valueName+'</p>');
				}
			</#list>
	    		$("#editable-edit${parameters.id?html}").show();
				$("#editable-save${parameters.id?html}").hide();
			}
			
			$("#btnEdit${parameters.id?html}").click( function(){
				form = $("#${parameters.id?html}");
	    	<#list parameters.tagNames as tagName>
				var fieldName = "${tagName?replace(".", "\\\\.")}";
				var field = $("#" + fieldName);
				field.parent().find("p.value").hide();
	    		field.show();
	    		if(field.is("input[type=radio]"))
	    			field.next("label").show();
				else 
					if(field.is("input[type=checkbox]"))
	    				field.next().next().show();
			</#list>
		    	
		    	$("#editable-edit${parameters.id?html}").hide();
		    	$("#editable-save${parameters.id?html}").show();
		    	
	    		$("#btnCancel${parameters.id?html}").click( function(){
					$("#editable-save${parameters.id?html}").hide();
					${parameters.id?html}Editable();
				});
				$("#btnSave${parameters.id?html}").click( function(){
			    	jsondata={};
			    	//validate check null and put data to json
			    	validPass = true;
					<#list parameters.tagNames as tagName>
						var fieldName = "${tagName?replace(".", "\\\\.")}";
						var field = $("#" + fieldName);
						if(field.val() == ""){
							if(field.parent().find('p.error').length){
								field.parent().find("p.error").show();
							}else{
								field.parent().append('<p class="error" style="margin-top:6px; color:red;"> warning not null');
							}
			    			
			    			field.closest('.form-group').removeClass('has-success').addClass('has-error');
			    			validPass = false;
			    			return;
			    		}else{
			    			if(field.is("input[type=radio]")){
								if(field.is(":checked"))
									jsondata[field.attr("name")] = field.val();
							}else if(field.is("input[type=checkbox]")){
								if(field.is(":checked"))
									jsondata[field.attr("name")] = field.val();
								else
									jsondata[field.attr("name")] = "N";
							}else
			    				jsondata[field.attr("name")] = field.val();
			    			field.closest('.form-group').removeClass('has-error').addClass('has-success');
			    		}
					</#list>
			    	
			    	if(validPass == false)
			    		return false;
			    	else{
			    		<#list parameters.tagNames as tagName>
						var fieldName = "${tagName?replace(".", "\\\\.")}";
						var field = $("#" + fieldName);
						field.next("p.error").hide();
						</#list>
			    		<#list parameters.tagNames as tagName>
						var fieldName = "${tagName?replace(".", "\\\\.")}";
						var field = $("#" + fieldName);
						field.removeClass('has-error').addClass('has-success');
						</#list>
			    		//form.find(".editable-save").hide();
			    		$("#editable-save${parameters.id?html}").hide();
						//urlUpdate = form.attr('action');
						console.log("${parameters.action?html}");
						console.log(jsondata);
			    	    resultUpdate = ${parameters.id?html}ajaxUpdate("${parameters.action?html}",jsondata,${parameters.id?html}UpdateSuccess,${parameters.id?html}UpdateFail);
			    	    function ${parameters.id?html}UpdateSuccess(){
			    	    	${parameters.id?html}Editable(form);		    	    
						}
			    	    function ${parameters.id?html}UpdateFail(){
					    	<#list parameters.tagNames as tagName>
							var fieldName = "${tagName?replace(".", "\\\\.")}";
							var field = $("#" + fieldName);
							field.parent().find("p.value").hide();
				    		field.css("display","");
							</#list>
					    	//form.find(".editable-edit").hide();
					    	$("#editable-edit${parameters.id?html}").hide();
					    	$("#editable-save${parameters.id?html}").show();
					    	/*form.append(
			    				'<div class="box-align-center editable-save"> '
									+'<p><input type="button" class="btn btn-primary btnSave" id="btnSave${parameters.id?html}" value="Save"/><input type="button" class="btn btn-default btnCancel" id="btnCancel${parameters.id?html}" value="Cancel"/></p>'
								+'</div>'
				    		);*/
			    	    	alert("Update Fail!");
			    	    }
			    	}
				});
			});

		    
		    function ${parameters.id?html}ajaxUpdate(url,jsondata,${parameters.id?html}updateSuccess,${parameters.id?html}updateFail){
			    $.ajax({
	            	type: "POST",
	            	url: url,
	              	dataType: "json",
	              	data: jsondata, 
	              	success: function(returnData){
 						console.log(returnData);
	              		if(returnData == "done"){
	              			${parameters.id?html}updateSuccess();
	              		}else{
	              			${parameters.id?html}updateFail();
	              		}
	              	},error: function(xhr, textStatus, errorThrown){
	              		alert('ajax loading error... ... '+url);
	              		return false;
	              	}
	            });
			}
		});
		</script>
</#if>
