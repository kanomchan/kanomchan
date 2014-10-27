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
<#if parameters.editview?default("false") == "true" && parameters.action?? >
	<div class="box-align-center" id="editable-save${parameters.id?html}" style="display:none;">
		<p><input type="button" class="btn btn-primary" id="btnSave${parameters.id?html}" value="Save"/>
		<input type="button" class="btn btn-default"  id="btnCancel${parameters.id?html}" value="Cancel"/></p>
	</div>
</div>
<#elseif parameters.action??>
	<div class="box-align-center" id="editable-save${parameters.id?html}">
		<p><input type="submit" class="btn btn-primary" id="btnSave${parameters.id?html}" value="Save"/></p>
	</div>
</#if>

<#if parameters.editview?default("false") == "true" && parameters.action?? >
<div id="ajaxView${parameters.id?html}">
	<div class="box-align-center" id="editable-edit${parameters.id?html}">
		<p><input type="button" class="btn btn-save pull-right" id="btnEdit${parameters.id?html}" value="Edit"/></p>
	</div>
	<div class='form-horizontal'>
		<#list parameters.tagNames as tagName>
		<div class='form-group'>
			<div>
				<label class='col-md-4 control-label' id="lblView${parameters.id?html}${tagName}">
					
				</label>
				<div class='col-md-4'>
					<p class='value' style='margin-top:6px;' id="pView${parameters.id?html}${tagName}">
					
					</p>
				</div>
			</div>
		</div>
		</#list>
	</div>
</div>
<#elseif parameters.action??>

</#if>
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

<#if parameters.editview?default("false") == "true" && parameters.action?? >
	<script type="text/javascript">
		jQuery(document).ready(function() {
			formId = "${parameters.id?html}";
			formName = "${parameters.name?html}";
			${parameters.id?html}Editable();
			
	    	function ${parameters.id?html}Editable(){
				form = $("#${parameters.id?html}");
				$("#ajaxEdit${parameters.id?html}").hide();
				$("#ajaxView${parameters.id?html}").show();
			<#list parameters.tagNames as tagName>
				var fieldName = "${tagName?replace(".", "\\\\.")}";
				var field = $("[name='" + fieldName + "']");
				var valueName = "";
				//${tagName?html}
				if(field.is("input[type=radio]")){
					field.next("label").hide();
					if(field.is(":checked"))
						valueName = field.next("label").text();
					else
						valueName = "";
				}else if(field.is("input[type=checkbox]")){
	    			if(field.is(":checked"))
	    				valueName = field.text();
	    			else{
	    				valueName = "";
	    				field.next().next().hide();
	    			}
	    		}else if(field.is("select")){
	    			if(field.val() == 0){
	    				if(valueName != "-")
	    					valueName = "-";
	    			}else
	    				valueName = field.find("option:selected").text();
	    		}else if(field.is("input")){
	    			valueName = field.val();
	    		}
				if(field.parent().find('p.value').length){
					field.parent().find('p.value').text(valueName);
					field.parent().find('p.value').show();
				}else{
					
					var label = field.parent().prev();
					console.log("lblView${parameters.id?html}" + fieldName);
					$("#lblView${parameters.id?html}" + fieldName).text(label.text());
					$("#pView${parameters.id?html}" + fieldName).text(valueName);
				}
			</#list>
	    		$("#editable-edit${parameters.id?html}").show();
				$("#editable-save${parameters.id?html}").hide();
			}
			$("#btnEdit${parameters.id?html}").click( function(){
				form = $("#${parameters.id?html}");
				$("#ajaxView${parameters.id?html}").hide();
				$("#ajaxEdit${parameters.id?html}").show();
	    	<#list parameters.tagNames as tagName>
				//var fieldName = "${tagName?replace(".", "\\\\.")}";
				//var field = $("[name='" + fieldName + "']");
				//field.parent().find("p.value").hide();
	    		//field.show();
	    		//if(field.is("input[type=radio]"))
	    			//field.next("label").show();
				//else{
				//	if(field.is("input[type=checkbox]"))
	    				//field.next().next().show();
	    		//}
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
						var field = $("[name='" + fieldName + "']");
						if(field.val() == ""){
							if(field.parent().find('p.error').length)
								field.parent().find("p.error").show();
							else
								field.parent().append('<p class="error" style="margin-top:6px; color:red;"> warning not null');
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
						var field = $("[name='" + fieldName + "']");
						field.next("p.error").hide();
						</#list>
			    		<#list parameters.tagNames as tagName>
						var fieldName = "${tagName?replace(".", "\\\\.")}";
						var field = $("#" + fieldName);
						field.removeClass('has-error').addClass('has-success');
						</#list>
			    		$("#editable-save${parameters.id?html}").hide();
			    	    resultUpdate = ${parameters.id?html}ajaxUpdate("${parameters.action?html}",jsondata,${parameters.id?html}UpdateSuccess,${parameters.id?html}UpdateFail);
			    	    function ${parameters.id?html}UpdateSuccess(){
			    	    	${parameters.id?html}Editable(form);		    	    
						}
			    	    function ${parameters.id?html}UpdateFail(){
					    	<#list parameters.tagNames as tagName>
							var fieldName = "${tagName?replace(".", "\\\\.")}";
							var field = $("[name='" + fieldName + "']");
							field.parent().find("p.value").hide();
				    		field.css("display","");
							</#list>
					    	$("#editable-edit${parameters.id?html}").hide();
					    	$("#editable-save${parameters.id?html}").show();
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
	              		if(returnData.status == "S"){
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
