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
			form = $("form[name='"+formName+"']");
			console.log("form name :"+formId);
			console.log("action url : ${parameters.action?html}");
			editable(formName);
				
			function editable(formName){
		    	elementInForm = $("form[name='"+formName+"'] :input[name^='"+formName+".']");
		    	elementInForm.each(function(i){
		    		$(this).css("display","none");
					if($(this).is("input[type=radio]")){
						$(this).next("label").css("display","none");
						if($(this).is(":checked")){
							valueName = $(this).next("label").text();
						}
						else{
							valueName = "";
						}
					}	
					else if($(this).is("input[type=checkbox]"))
		    			if($(this).is(":checked"))
		    				valueName = $(this).text();
		    			else{
		    				valueName = "";
		    				$(this).next().next().css("display","none");
		    			}
		    		else if($(this).is("select"))
		    			if($(this).val() == 0)
		    				valueName = "-";
		    			else
		    				valueName = $(this).find("option:selected").text();
		    		else if($(this).is("input"))
		    			valueName = $(this).val();
		    		$(this).parent().append('<p class="value" style="margin-top:6px;">'+valueName+'</p>');
		    	});
	    		$("form[name='"+formName+"']").prepend(
    				'<div class="box-align-center editable-edit"> '
						+'<p><input type="button" Class="btn btn-save pull-right btnEdit"  value="Edit"/></p>'
					+'</div>'
	    		);
			}
			$(document).on('click', "input:button.btnEdit", function() {
				form = $(this).parents("form");
		    	formName = $(this).parents("form")[0].name;
		    	elementInForm = $(form).find(":input[name^='"+formName+".']");
		    	elementInForm.each(function(){
		    		$(this).parent().find("p.value").remove();
		    		$(this).css("display","");
		    		if($(this).is("input[type=radio]"))
		    			$(this).next("label").css("display","");
					else 
						if($(this).is("input[type=checkbox]"))
		    				$(this).next().next().css("display","");
		    	});
		    	$(form).find(".editable-edit").remove();
		    	$(form).append(
    				'<div class="box-align-center editable-save"> '
						+'<p><input type="button" Class="btn btn-primary btnSave" value="Save"/><input type="button" Class="btn btn-default btnCancel" value="Cancel"/></p>'
					+'</div>'
	    		);
		    });
			$(document).on('click', "input:button.btnCancel", function(){
				form = $(this).parents("form");
		    	formName = $(this).parents("form")[0].name;
				$(form).find(".editable-save").remove();
				editable(formName);
			});
		    $(document).on('click', "input:button.btnSave", function() {
		    	form = $(this).parents("form");
		    	formName = $(this).parents("form")[0].name;
		    	jsondata={};
		    	//validate check null and put data to json
		    	validPass = true;
		    	$(form).find(":input[name^='"+formName+".']").each(function(){
		    		if($(this).val() == ""){
		    			$(this).parent().find("p.error").remove();
		    			$(this).parent().append('<p class="error" style="margin-top:6px; color:red;"> warning not null');
		    			$(this).closest('.form-group').removeClass('has-success').addClass('has-error');
		    			validPass = false;
		    			return;
		    		}else{
		    			if($(this).is("input[type=radio]")){
							if($(this).is(":checked"))
								jsondata[$(this)[0].name] = $(this).val();
						}else if($(this).is("input[type=checkbox]")){
							if($(this).is(":checked"))
								jsondata[$(this)[0].name] = $(this).val();
							else
								jsondata[$(this)[0].name] = "N";
						}else
		    				jsondata[$(this)[0].name] = $(this).val();
		    			$(this).closest('.form-group').removeClass('has-error').addClass('has-success');
		    		}
		    	});
		    	if(validPass == false)
		    		return false;
		    	else{
		    		$(form).find(":input[name^='"+formName+".']").each(function(){
		    			$(this).next("p.error").remove();
		    		});
		    		$(form).find('.form-group').each(function(){
		    			$(this).removeClass('has-error').addClass('has-success');
		    		});
		    		$(form).find(".editable-save").remove();
// 		    	    alert(JSON.stringify(jsondata));
					urlUpdate = $(form).attr('action');
		    	    resultUpdate = ajaxUpdate(urlUpdate,jsondata,UpdateSuccess,UpdateFail);
		    	    function UpdateSuccess(){
		    	    	editable(formName);
		    	    }
		    	    function UpdateFail(){
				    	elementInForm = $(form).find(":input[name^='"+formName+".']");
				    	elementInForm.each(function(){
				    		$(this).parent().find("p.value").remove();
				    		$(this).css("display","");
				    	});
				    	$(form).find(".editable-edit").remove();
				    	$(form).append(
		    				'<div class="box-align-center editable-save"> '
								+'<p><input type="button" Class="btn btn-primary btnSave" value="Save"/><input type="button" Class="btn btn-default btnCancel" value="Cancel"/></p>'
							+'</div>'
			    		);
		    	    	alert("Update Fail!");
		    	    }
		    	}
		    });
		    
		    function ajaxUpdate(url,jsondata,updateSuccess,updateFail){
			    $.ajax({
	            	type: "POST",
	            	url: url,
	              	dataType: "json",
	              	data: jsondata, 
	              	success: function(returnData){
 						console.log(returnData);
	              		if(returnData == "done"){
	              			updateSuccess();
	              		}else{
	              			updateFail();
	              		}
	              	},error: function(xhr, textStatus, errorThrown){
	              		alert('ajax loading error... ... '+url + query);
	              		return false;
	              	}
	            });
			}
		});
		</script>
</#if>
