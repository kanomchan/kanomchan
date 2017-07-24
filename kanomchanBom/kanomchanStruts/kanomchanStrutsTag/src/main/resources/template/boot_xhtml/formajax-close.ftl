<#include "/${parameters.templateDir}/${parameters.expandTheme}/form-close.ftl" />

	<script type="text/javascript" src="${base}/struts/boot_xhtml/ajax.js"></script>
<#if parameters.editview?default(false) && parameters.action?? >
	<script type="text/javascript">
var updateSuccess_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}  = function (returnData){
var display = document.getElementById("${parameters.displayId?html}");
var displaytexts = display.getElementsByTagName("displaytext");

for (i = 0; i < displaytexts.length; i++) { 
    displaytext = displaytexts[i];
    var key = displaytext.getAttribute("value");
    var data = returnData.result[key];
    displaytext.innerHTML = data;
}

close_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}();
};
var updateFail_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}  = function (returnData){

};
var submitForm_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}  = function (f){
if(!f)
return f;
var data;
<#list parameters.tagNames as tagName>
//data["${tagName?replace(".", "\\\\.")}"] = getdata("${parameters.id?html}","${tagName?replace(".", "\\\\.")}");
</#list>
data = getDataFrom("${parameters.id?html}");
$.ajax({
	            	type: "POST",
	            	url: "${parameters.action?html}",
	              	dataType: "json",
	              	data: data, 
	              	success: function(returnData){
	              		if(returnData.status == "S"){
	              			updateSuccess_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}(returnData);
	              		}else{
	              			updateFail_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}(returnData);
	              		}
	              	},error: function(xhr, textStatus, errorThrown){
	              		alert('ajax loading error... ... '+url);
	              		return false;
	              	}
	            });
return false;

};
var close_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}  = function (){
document.getElementById('${parameters.id?html}').style.display = 'none';
document.getElementById('btn_close_${parameters.id?html}').style.display = 'none';
document.getElementById('btn_edit_${parameters.id?html}').style.display = 'block'; 
document.getElementById('${parameters.displayId?html}').style.display = 'block';
};
var open_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}  = function (){
document.getElementById('${parameters.id?html}').style.display = 'block';
document.getElementById('btn_close_${parameters.id?html}').style.display = 'block';
document.getElementById('btn_edit_${parameters.id?html}').style.display = 'none'; 
document.getElementById('${parameters.displayId?html}').style.display = 'none';
};

jQuery(document).ready(function() {
close_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}();
});


<#-- 

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
		}); -->
		</script>
</#if>
