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
<link href="${base}/struts/simple/selectgrouplevel/css/select2col.css" rel="stylesheet"></link>
<script src="${base}/struts/simple/selectgrouplevel/js/select2col.js"></script>
<div id="select2col_${parameters.id}"></div>

<script type="text/javascript">
	var options_${parameters.id} = {
			id: "${parameters.id}"
			,label_not_found : "<@s.text name="COMMON_NO_RESULTS_FOUND"/>"
			,label_col_parent : "<#if parameters.groupListLabel??> <@s.text name="${parameters.groupListLabel}"/> <#else><@s.text name="COMMON_GROUP"/> </#if>"
			,label_col_child : "<#if parameters.subNameLabel??> <@s.text name="${parameters.subNameLabel}"/> <#else><@s.text name="COMMON_SUP_GROUP"/> </#if>"
			,label_all_group : "<@s.text name="COMMON_ALL_GROUP"/>"
			,input_setting : {
				name_prefix: "${parameters.name}"
				,nameKey: "${parameters.nameKey}"
				,nameParentKey: "${parameters.nameParentKey}"
				,nameStatus: "<#if parameters.nameStatus??>${parameters.nameStatus}<#else>status</#if>"
				,nameToSaveKey: "${parameters.nameToSaveKey}"
				,status : "<#if parameters.nameStatusInitKey??>${parameters.nameStatusInitKey}<#else>A</#if>"
			}
			<#if parameters.singleSelect?? && stack.findValue(parameters.singleSelect)>
				,isSingle: true
				<#else>
				,isSingle: false
			</#if>
			<#if parameters.minusOneOnRemove?? && stack.findValue(parameters.minusOneOnRemove)>
				,isMinusOneOnRemove: true
				<#else>
				,isMinusOneOnRemove: false
			</#if>
			<#if parameters.nameToSaveKey??>
				,isNameToSave: true
				<#else>
				,isNameToSave: false
			</#if>
			,parentInit :{
				data:[
					<@s.iterator value="stack.findValue(parameters.groupList)" status="status">
						{name: '<@s.property value="${parameters.groupListValue}"/>',id :  <@s.property value="${parameters.groupListKey}"/>}<@s.if test="!#status.last">,</@s.if>
					</@s.iterator>
				]
			}
			,inputInit : {
			
				data: [<#if parameters.nameList?? && !(parameters.singleSelect?? && stack.findValue(parameters.singleSelect))>
					<@s.iterator value="stack.findValue(parameters.nameList)" status="status">
						 {name : "${stack.findValue(parameters.nameValue)}",
						  id: ${stack.findValue(parameters.nameKey)},
						  id_parent: <#if stack.findValue(parameters.nameParentKey)??>${stack.findValue(parameters.nameParentKey)}<#else>-2</#if>,
						  id_to_save: <@s.property value=(parameters.nameToSaveKey)/>
						}<@s.if test="!#status.last">,</@s.if>
					</@s.iterator>
				<#elseif (parameters.singleSelect?? && stack.findValue(parameters.singleSelect))>
					<#if stack.findValue(parameters.nameValue_Single)?? && stack.findValue(parameters.nameKey_Single)?? && stack.findValue(parameters.nameParentKey_Single)??>
						{
							name : "${stack.findValue(parameters.nameValue_Single)}",
							id: ${stack.findValue(parameters.nameKey_Single)},
							id_parent: <#if stack.findValue(parameters.nameParentKey_Single)??>${stack.findValue(parameters.nameParentKey_Single)}<#else>-2</#if>,
							id_to_save: <@s.property value=(parameters.nameToSaveKey)/>
						}
					</#if>
				</#if>
			]}
			
			,getChild : function(id_parent, currentPage, keyword,widget){
				var urlSub;
				if( id_parent == 0){
					urlSub = '${parameters.subAllGroupUrl}';
				}
				else{
					urlSub = '${parameters.subUrl}';
				}
				
				$.ajax({
					type : "POST",
					url : urlSub,
					data : {"idParent": id_parent,
							"keyWord": keyword,
							"currentPage": currentPage
							},
					cache : false,
					success : function(jsonResponse) {
						if (typeof jsonResponse != 'undefined' && jsonResponse.success && jsonResponse != null && jsonResponse.result != null && jsonResponse.result<#if parameters.subName??>.${parameters.subName}</#if>.length > 0){
							var childArr = new Array();
							$.each( jsonResponse.result<#if parameters.subName??>.${parameters.subName}</#if>, function( key, val ) {
								childArr.push({
									name : ""+val.${parameters.subNameValue},
									id : val.${parameters.subNameKey}, 
									id_parent : val.${parameters.subNameParentKey}
								});
								
							});
							
							widget.getChildCallback(childArr,jsonResponse.pagingBean);
						}
						else{
							widget.getChildCallback([],jsonResponse.pagingBean);
						}
					}
					
				});
			
			}
			,getParentHasSearchChild: function(keyWord,widget){
				console.log("fire");
				$.ajax({
					type : "POST",
					url : '${parameters.searchGroupUrl}',
					data : { "keyWord": keyWord},
					cache : false,
					success : function(jsonResponse) {
						var hasChildArr = new Array();
						if (typeof jsonResponse != 'undefined' && jsonResponse.success && jsonResponse != null && jsonResponse.result != null){
							$.each( jsonResponse.result, function( key, val ) {
								hasChildArr.push(val.<#if parameters.searchGroupUrlKey??>${parameters.searchGroupUrlKey}<#else>${parameters.groupListKey}</#if>);
							});
						}
						widget.getParentHasSearchChildCallBack(hasChildArr);
					}
				});
			},
			clearData : function(){
				this.element.find(".select2col-select-container > div.select2col-select-col.sub .select2col-select-item").trigger("childUncheck");
			},
			reCreate : function(e, data){
				e.select2col('destroy');
				e.find(".select2col-input-box").remove();
				if(typeof data !== 'undefined' && Object.prototype.toString.call( data ) === '[object Array]'){
					this.options.inputInit = {
						data: data
					}
				}else{
					this.options.inputInit = {
						data: []
					}
				}
				e.select2col(this.options);
			}
		}

	$('document').ready(function(){
		$("#select2col_${parameters.id}").select2col(options_${parameters.id});

	});
	
	
</script>
