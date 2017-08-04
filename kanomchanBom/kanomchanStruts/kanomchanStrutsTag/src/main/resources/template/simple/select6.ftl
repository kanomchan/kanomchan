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
<link href="${base}/struts/simple/selectgrouplevel/css/select6.css" rel="stylesheet">

<div class="select6 select6-wrapper multiple select6_${parameters.id}" onclick="clickSearch_${parameters.id}(event)">
	<div class="select6-input-box">
		<div class="select6-input-box">
			<div class="select6-input-wrapper">
				<div class="select6-input" id="select6-input-${parameters.id}" onclick="openselect6_${parameters.id}(event,$(this))">
					<#if parameters.nameList?? && !(parameters.singleSelect?? && stack.findValue(parameters.singleSelect))>
						<@s.iterator value="stack.findValue(parameters.nameList)" status="status">
							<div class="select6-input-item" onclick="inputItemClick_${parameters.id}(event,$(this))">
								<div class="select6-input-item-label">
								 	${stack.findValue(parameters.nameValue)}
								</div>
								<input type="hidden" class="id_${parameters.id}" name="${parameters.name}[<@s.property value="#status.index"/>].${parameters.nameKey}" value="${stack.findValue(parameters.nameKey)}"> 
								<input type="hidden" class="id_${parameters.id}_parent" name="${parameters.name}[<@s.property value="#status.index"/>].${parameters.nameParentKey}" value="${stack.findValue(parameters.nameParentKey)}">
								<input type="hidden" name="${parameters.name}[<@s.property value="#status.index"/>].<#if parameters.nameStatus??>${parameters.nameStatus}<#else>status</#if>" value="<#if parameters.nameStatusInitKey??>${parameters.nameStatusInitKey}<#else>A</#if>">
								<div class="select6-input-remove" onclick="deleteInputItem_${parameters.id}(event,$(this).parent());"><i class="fa fa-times"></i></div>
							</div>
							<input type="hidden" name="item_count_${parameters.id}"/>
							<#if parameters.nameToSaveKey??>
								<input type="hidden" class="id_${parameters.id}_toSave" name="${parameters.name}[<@s.property value="#status.index"/>].${parameters.nameToSaveKey}" value="<@s.property value=(parameters.nameToSaveKey)/>"> 
							</#if>
							<input type="hidden" name="__pushdataonremove_${parameters.name}[<@s.property value="#status.index"/>].<#if parameters.nameStatus??>${parameters.nameStatus}<#else>status</#if>" value="I">
						</@s.iterator>
						<#else>
							<#if stack.findValue(parameters.nameValue_Single)?? && stack.findValue(parameters.nameKey_Single)?? && stack.findValue(parameters.nameParentKey_Single)??>
								<div class="select6-input-item" onclick="inputItemClick_${parameters.id}(event,$(this))">
									<div class="select6-input-item-label">
										${stack.findValue(parameters.nameValue_Single)}
									</div>
									<input type="hidden" class="id_${parameters.id}" name="${parameters.name}.${parameters.nameKey}" value="${stack.findValue(parameters.nameKey_Single)}"> 
									<input type="hidden" class="id_${parameters.id}_parent" name="${parameters.name}.${parameters.nameParentKey}" value="${stack.findValue(parameters.nameParentKey_Single)}">
									<input type="hidden" name="${parameters.name}.<#if parameters.nameStatus??>${parameters.nameStatus}<#else>status</#if>" value="<#if parameters.nameStatusInitKey??>${parameters.nameStatusInitKey}<#else>A</#if>">
									<div class="select6-input-remove" onclick="deleteInputItem_${parameters.id}(event,$(this).parent());"><i class="fa fa-times"></i></div>
								</div>
								<input type="hidden" name="item_count_${parameters.id}"/>
								<#if parameters.nameToSaveKey??>
									<input type="hidden" class="id_${parameters.id}_toSave" name="${parameters.name}.${parameters.nameToSaveKey}" value="<@s.property value=(parameters.nameToSaveKey)/>"> 
								</#if>
								<input type="hidden" class="pushRemove_single_${parameters.id}" name="__pushdataonremove_${parameters.name}.<#if parameters.nameStatus??>${parameters.nameStatus}<#else>status</#if>" value="I">	
								<#if parameters.minusOneOnRemove?? && stack.findValue(parameters.minusOneOnRemove)>
									<input type="hidden" class="pushRemove_single_${parameters.id}" name="__pushdataonremove_${parameters.name}.${parameters.nameKey}" value="-1">
								</#if>
							</#if>					
					</#if>
					<#if !(parameters.singleSelect?? && stack.findValue(parameters.singleSelect))>
						<div class="clone-input-${parameters.id} clone" style="display: none;">
							<div class="select6-input-item" onclick="inputItemClick_${parameters.id}(event,$(this))">
								<div class="select6-input-item-label" >
									{0}
								</div>
								<input type="hidden" class="id_${parameters.id}" name="${parameters.name}[{3}].${parameters.nameKey}" value="{1}"> 
								<input type="hidden" class="id_${parameters.id}_parent" name="${parameters.name}[{3}].${parameters.nameParentKey}" value="{2}">
								<input type="hidden" name="${parameters.name}[{3}].<#if parameters.nameStatus??>${parameters.nameStatus}<#else>status</#if>" value="<#if parameters.nameStatusInitKey??>${parameters.nameStatusInitKey}<#else>A</#if>">
								<div class="select6-input-remove" onclick="deleteInputItem_${parameters.id}(event,$(this).parent());"><i class="fa fa-times"></i></div>
							</div>
							<input type="hidden" name="item_count_${parameters.id}"/>
							<input type="hidden" class="pushRemove_single_${parameters.id}" name="__pushdataonremove_${parameters.name}[{3}].<#if parameters.nameStatus??>${parameters.nameStatus}<#else>status</#if>" value="I">
						</div>
						<#else>
							<div class="clone-input-${parameters.id} clone" style="display: none;">
								<div class="select6-input-item" onclick="inputItemClick_${parameters.id}(event,$(this))">
									<div class="select6-input-item-label" >
										{0}
									</div>
									<input type="hidden" class="id_${parameters.id}" name="{5}" value="{1}"> 
									<input type="hidden" class="id_${parameters.id}_parent" name="{6}" value="{2}">
									<input type="hidden" name="{3}" value="<#if parameters.nameStatusInitKey??>${parameters.nameStatusInitKey}<#else>A</#if>">
									<div class="select6-input-remove" onclick="deleteInputItem_${parameters.id}(event,$(this).parent());"><i class="fa fa-times"></i></div>
								</div>
								<input type="hidden" name="item_count_${parameters.id}"/>
								<input type="hidden" class="pushRemove_single_${parameters.id}" name="{4}" value="I">
								<#if parameters.minusOneOnRemove?? && stack.findValue(parameters.minusOneOnRemove)>
									<input type="hidden" class="pushRemove_single_${parameters.id}" name="{7}" value="-1">
								</#if>
							</div>
					</#if>
					
					<div class="input-down-arrow">
						<div>
							<span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="select6-select-container" style="display: none;">
			<div class="row">
				<div class="col-md-12">
					<input type="text" name="keyWord" id="keyWordSearch_${parameters.id}" class="form-control" placeholder="<@s.text name="COMMON_SEARCH"/>" onclick="clickSearch_${parameters.id}(event)">
					<div class="spinner-search-div">
						<i class="fa fa-spinner fa-2x fa-spin"></i>
					</div>
				</div> 
			</div>
			<div class="row title-container">
				<div class="col-xs-6">
					<div><label> <#if parameters.groupListLabel??> <@s.text name="${parameters.groupListLabel}"/> <#else><@s.text name="COMMON_GROUP"/> </#if></label></div>
				</div>
				<div class="col-xs-6">
					<div><label><#if parameters.subNameLabel??> <@s.text name="${parameters.subNameLabel}"/> <#else><@s.text name="COMMON_SUP_GROUP"/> </#if> </label></div>
				</div>
			</div>
			<div class="select6-select-col group">
				<div class="select6-scroll mCustomScrollbar" data-mcs-theme="minimal-dark">
					<div class="select6-select-item all-group active" id="select6-select-item-${parameters.id}-0" onclick="groupClick_${parameters.id}(event,$(this))">
						<input type="hidden" class="item_id_${parameters.id}" value="0">
						<label style="font-size: 12px;"><@s.text name="COMMON_ALL_GROUP"/></label> 
						<i class="fa fa-chevron-right icon-right" style="color: #BBB"></i>
					</div>
					<@s.iterator value="stack.findValue(parameters.groupList)">
						<div class="select6-select-item" id="select6-select-item-${parameters.id}-<#if parameters.groupListKey??><@s.property value="${parameters.groupListKey}"/></#if>" onclick="groupClick_${parameters.id}(event,$(this))">
							<input type="hidden" class="item_id_${parameters.id}" value="<#if parameters.groupListKey??><@s.property value="${parameters.groupListKey}"/></#if>">
							<input type="hidden" class="item_name_${parameters.id}" value="<#if parameters.groupListValue??><@s.property value="${parameters.groupListValue}"/></#if>">
							<label style="font-size: 12px;"><#if parameters.groupListValue??><@s.property value="${parameters.groupListValue}"/></#if> </label> 
							<i class="fa fa-chevron-right icon-right" style="color: #BBB"></i>
						</div>
					</@s.iterator>
				</div>
			</div>
			<div class="select6-select-col sub">
				<div class="select6-scroll mCustomScrollbar" data-mcs-theme="minimal-dark" >
					<div class="clone${parameters.id} clone" style="display: none">
						<div class="select6-select-item" id="select6-select-item-sub-${parameters.id}-{0}" onclick="click_${parameters.id}(event,$(this));">
							<div class="check-box" onclick="
								<#if parameters.singleSelect?? && stack.findValue(parameters.singleSelect)>
									checkboxClickSigle_${parameters.id}(event,$(this).parent()); 
								<#else>
									checkboxClick_${parameters.id}(event,$(this).parent());
								</#if>
							">
								<input type="hidden" class="item_id_${parameters.id}" value="{0}">
								<input type="hidden" name="item_parent_id_${parameters.id}" value="{2}">
								<input type="hidden" class="item_name_${parameters.id}" value="{3}">
							</div>
							<label style="font-size: 12px;">{1}</label>
						</div>
					</div>
					<div class="spinner-div">
						<i class="fa fa-spinner fa-2x fa-spin"></i>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var addItemList_${parameters.id} = new Object();
	var currentPage_${parameters.id};
	var  isLastPage_${parameters.id} = false;
	
	scrollEnd_${parameters.id} = function(){
		currentPage_${parameters.id}++;
		var idParent = $('.select6_${parameters.id} .select6-select-item.active').children('.item_id_${parameters.id}').val();
		var keyWord = $("#keyWordSearch_${parameters.id}").val();
		
		var urlSub;
		if( idParent == 0){
			urlSub = '${parameters.subAllGroupUrl}';
		}
		else{
			urlSub = '${parameters.subUrl}';
		}
		
		if(!isLastPage_${parameters.id}){
			$.ajax({
				type : "POST",
				url : urlSub,
				data : {"idParent": idParent,
						"keyWord": keyWord,
						"currentPage": currentPage_${parameters.id}},
				cache : false,
				success : function(jsonResponse) {
					if (typeof jsonResponse != 'undefined' && jsonResponse.success && jsonResponse != null && jsonResponse.result != null){
						var rowsPerPage = jsonResponse.pagingBean.rowsPerPage;
						var totalRows = jsonResponse.pagingBean.totalRows;
						var totalPage = totalRows/rowsPerPage;
						if(totalRows%rowsPerPage > 0){
							totalPage++;
						}
						if(currentPage_${parameters.id}+1 > totalPage){
							isLastPage_${parameters.id} = true;
							$(".select6_${parameters.id} .spinner-div").hide();
						}
						if(totalRows == 0 ){
							$(".select6_${parameters.id} .spinner-div").hide();
						}
						
						$.each( jsonResponse.result<#if parameters.subName??>.${parameters.subName}</#if>, function( key, val ) {
							cloneSub_${parameters.id}(val,keyWord)
						});
						
						
						setInputToItemList_${parameters.id}();
						if (typeof func != 'undefined' && func != null && typeof idItem != 'undefined' && idItem != null){
							func(idItem);
						}
					}
				}
			});
		}
	}
	
	cloneSub_${parameters.id} = function(val,keyWord){
		var data = $(".clone${parameters.id}").html();
		var name ;
		if(keyWord != null && keyWord != "" ){
			var text = val.${parameters.subNameValue};
			var matchStart = text.toLowerCase().indexOf(keyWord.toLowerCase());
			if (matchStart >= 0){
				name = highlightText(text,matchStart,keyWord);
			}
			else{
				name = text;
			}
		}
		else{
			name = val.${parameters.subNameValue};
		}
		
		$('.clone${parameters.id}').before(data.format(
			val.${parameters.subNameKey}, // {0}
			name, // {1}
			val.${parameters.subNameParentKey},
			val.${parameters.subNameValue}
		));
	}

	$(document).on('click',function() {
		$('.select6-select-container').hide();
		$(".select6_${parameters.id} .input-down-arrow").empty();
		$(".select6_${parameters.id} .input-down-arrow").append("<div><span class='glyphicon glyphicon-chevron-down' aria-hidden='true'></span></div>");
	});
	toggleselect6_${parameters.id} = function(element){
		var arrow;
		if($(element).is(":visible")){
			arrow = "glyphicon glyphicon-chevron-down";
		}
		else{
			arrow = "glyphicon glyphicon-chevron-up";
		}
		$(".select6_${parameters.id} .input-down-arrow").empty();
		$(".select6_${parameters.id} .input-down-arrow").append("<div><span class='"+arrow+"' aria-hidden='true'></span></div>");
		element.toggle();
	};
	var openselect6_${parameters.id} = function(event,element) {
		$('.select6_${parameters.id} .select6-select-container').not(element.parent().parent().parent().children('.select6-select-container')).hide();
		toggleselect6_${parameters.id}(element.parent().parent().parent().children('.select6-select-container'));
		
		event.stopPropagation();
	};
	$(document).ready(function(){
		$(".select6_${parameters.id} .select6-select-col.group .select6-scroll").mCustomScrollbar();
		$(".select6_${parameters.id} .select6-select-col.sub .select6-scroll").mCustomScrollbar({
				callbacks : {onScroll:function(){
					if(this.mcs.topPct > 90	){
						scrollEnd_${parameters.id}();
					}
				}}
		});
		$('.select6_${parameters.id} .scroll-element.scroll-y').hide(0);
		load_item_${parameters.id}($('#select6-select-item-${parameters.id}-0'),null);
		refreshState_${parameters.id}();
	});
	
	clickSearch_${parameters.id} = function(event){
		event.stopPropagation();
	}
	
	checkboxClick_${parameters.id} = function(event,e){
		event.stopPropagation();
		var checkBox = e.children('.check-box');
		if(checkBox.hasClass('isChecked')){
			checkBox.removeClass('isChecked'); 
			$('#select6-input-${parameters.id} .select6-input-item').has("input.id_${parameters.id}[value="+ $(checkBox).children(".item_id_${parameters.id}").val() +"]").remove();
		}
		else{
			checkBox.addClass('isChecked');
			setItemToInput_${parameters.id}(e);
		}
	}
	
	<#if parameters.singleSelect?? && stack.findValue(parameters.singleSelect)>
	checkboxClickSigle_${parameters.id} = function(event,e){
		event.stopPropagation();
		var checkBox = e.children('.check-box');
		if(checkBox.hasClass('isChecked')){
			checkBox.removeClass('isChecked'); 
			$('#select6-input-${parameters.id} .select6-input-item').has("input.id_${parameters.id}[value="+ $(checkBox).children(".item_id_${parameters.id}").val() +"]").remove();
		}
		else{
			$(".select6_${parameters.id} .select6-select-item .check-box").removeClass("isChecked");
			checkBox.addClass('isChecked');
			$("#select6-input-${parameters.id} > div:not(.clone-input-${parameters.id}):not(.input-down-arrow").remove();
			$("div:not(.clone) > .pushRemove_single_${parameters.id}").remove();
			setItemToInput_${parameters.id}(e);
			setInputToItemList_${parameters.id}();
		}
		refreshState_${parameters.id}();
	}
	</#if>
	
	groupClick_${parameters.id} = function(event,e){
		event.stopPropagation();
		
		load_item_${parameters.id}(e, null);
	}
	
	load_item_${parameters.id} = function(e,func,idItem){
		if(e.attr("id") != $('.select6_${parameters.id} .select6-select-item.active').attr("id")){
			$('.select6_${parameters.id} .select6-select-item').removeClass('active');
		}
		e.addClass("active");
		
		$('.select6_${parameters.id} .select6-select-col.sub .select6-scroll div:not(.clone${parameters.id})>.select6-select-item').remove();
		
		e.addClass("active");
		var idParent = e.children('.item_id_${parameters.id}').val();
		var keyWord = $("#keyWordSearch_${parameters.id}").val();
		currentPage_${parameters.id} = 1;
		isLastPage_${parameters.id} = false;
		$(".select6_${parameters.id} .spinner-div").show();
		
		var urlSub;
		if( idParent == 0){
			urlSub = '${parameters.subAllGroupUrl}';
		}
		else{
			urlSub = '${parameters.subUrl}';
		}
		
		$.ajax({
			type : "POST",
			url : urlSub,
			data : {"idParent": idParent,
					"keyWord": keyWord,
					"currentPage": currentPage_${parameters.id}},
			cache : false,
			success : function(jsonResponse) {
				if (typeof jsonResponse != 'undefined' && jsonResponse.success && jsonResponse != null && jsonResponse.result != null && jsonResponse.result<#if parameters.subName??>.${parameters.subName}</#if>.length > 0){
					$.each( jsonResponse.result<#if parameters.subName??>.${parameters.subName}</#if>, function( key, val ) {
						var rowsPerPage = jsonResponse.pagingBean.rowsPerPage;
						var totalRows = jsonResponse.pagingBean.totalRows;
						var totalPage = totalRows/rowsPerPage;
						if(totalRows % rowsPerPage > 0){
							totalPage++;
						}
						
						if(totalRows <= rowsPerPage || currentPage_${parameters.id}+1 > totalPage || totalRows == 0){
							isLastPage_${parameters.id} = true;
							$(".select6_${parameters.id} .spinner-div").hide();
						}
						
						cloneSub_${parameters.id}(val,keyWord)
						
					});
					
					
					setInputToItemList_${parameters.id}();
					if (typeof func != 'undefined' && func != null && typeof idItem != 'undefined' && idItem != null){
						func(idItem);
					}
				}
				else{
					$(".select6_${parameters.id} .spinner-div").hide();
				}
				refreshState_${parameters.id}();
			}
			
		});
	}
	
	setInputToItemList_${parameters.id} = function(){
		
		$('#select6-input-${parameters.id}').children('.select6-input-item').each(function(key,value){
		   	var idInputSkill = $(value).children('.id_${parameters.id}').val();
		   
		 	var checkBox = $("#select6-select-item-sub-${parameters.id}-"+idInputSkill).children('.check-box');
			if(!checkBox.hasClass('isChecked')){
				checkBox.addClass('isChecked');
			}
			  
			var idRadio = $(value).children('.id_radio').val();
			$("#select6-select-item-sub-${parameters.id}-"+idInputSkill+" input[type='radio']").filter('[value="'+idRadio+'"]').attr('checked', true);
		});
	}
	
	click_${parameters.id} = function(event, e){
		event.stopPropagation();
		<#if parameters.singleSelect?? && stack.findValue(parameters.singleSelect)>
			checkboxClickSigle_${parameters.id}(event, e);
		<#else>
			checkboxClick_${parameters.id}(event, e);
		</#if>
		
	}
	
	setItemToInput_${parameters.id} = function(e){
		var id = e.children('.check-box').children('.item_id_${parameters.id}').val();
		var idParent = e.children('.check-box').children('[name="item_parent_id_${parameters.id}"]').val();
		
		var name = e.children('label').text();
		
		var isDup = false;
		$('#select6-input-${parameters.id}').children('.select6-input-item').each(function(key,value){
		   var idInputSkill = $(value).children('.id_${parameters.id}').val();
		   if(idInputSkill == id){
			   isDup = true;
			   $(value).children('.select6-input-item-label').text(name);
		   }
		});
		
		if(!isDup){
			cloneInputItem_${parameters.id}(name, id, idParent);
		}
		refreshState_${parameters.id}();
	}
		
	deleteInputItem_${parameters.id} = function(event,e){
		event.stopPropagation();
		var id_input_item = e.children('.id_${parameters.id}').val();
		$("#select6-select-item-sub-${parameters.id}-"+ id_input_item+ " .check-box").removeClass("isChecked");
		$("#select6-select-item-sub-${parameters.id}-"+ id_input_item+ " input[type='radio']").attr("checked" , false);
		e.remove();
		refreshState_${parameters.id}();
	}
	
	inputItemClick_${parameters.id} = function(event,e){
		event.stopPropagation();
		var idActiveParent = $('.select6-select-item.active').children(".item_id_${parameters.id}").val();
		var id${parameters.id} = $(e).find(".id_${parameters.id}").val();
		var idGroup = $(e).find(".id_${parameters.id}_parent").val();
		$('#keyWordSearch_${parameters.id}').val("");
		load_item_${parameters.id}($('#select6-select-item-${parameters.id}-'+idGroup),null,id${parameters.id});
	}
	
	function keyWordChange_${parameters.id}(){
		var keyWord = $('#keyWordSearch_${parameters.id}').val();
		 $(".select6_${parameters.id} .spinner-search-div").hide();
		if(keyWord === ""){
			$(".select6_${parameters.id} .select6-select-col.group .select6-select-item").each(function(key,value){
				var text = $(value).children(".item_name_${parameters.id}").val();
				$(value).children("label").html(text);
			}).show();
			load_item_${parameters.id}($(".select6_${parameters.id} .select6-select-col.group .select6-select-item.active"), null);
		}
		else{
			$.ajax({
				type : "POST",
				url : '${parameters.searchGroupUrl}',
				data : { "keyWord": keyWord},
				cache : false,
				success : function(jsonResponse) {
					$(".select6_${parameters.id} .select6-select-col.group .select6-select-item:not(.all-group)").each(function(key,value){
						$(value).children("label").empty();
						var text = $(value).children(".item_name_${parameters.id}").val();
						var matchStart = text.toLowerCase().indexOf(keyWord.toLowerCase());
						if (matchStart >= 0){
							$(value).children("label").html(highlightText(text,matchStart,keyWord));
							$(value).show();
						}
						else{
							$(value).children("label").html(text);
							if($(value).hasClass('active')){
								$(value).show();
							}
							else{
								$(value).hide();
							}
						}
					});
					
					if (typeof jsonResponse != 'undefined' && jsonResponse.success && jsonResponse != null && jsonResponse.result != null){
						$.each( jsonResponse.result, function( key, val ) {
							$('#select6-select-item-${parameters.id}-'+ val.<#if parameters.searchGroupUrlKey??>${parameters.searchGroupUrlKey}<#else>${parameters.groupListKey}</#if>).show();
						});
					}
					

					load_item_${parameters.id}($(".select6_${parameters.id} .select6-select-item.active"), null);
					
				}
			});
		}
	}
	
	cloneInputItem_${parameters.id} = function(name, id, idParent){
		var data = $(".clone-input-${parameters.id}").html();
		<#if !(parameters.singleSelect?? && stack.findValue(parameters.singleSelect))>
			$('.clone-input-${parameters.id}').before(data.format(
				name, // {0}
				id,
				idParent,
				$(".select6_${parameters.id} div:not(.clone-input-${parameters.id})>input[name='item_count_${parameters.id}'] ").length
			));
			<#else>
				$('.clone-input-${parameters.id}').before(data.format(
					name, // {0}
					id,
					idParent,
					"${parameters.name}.<#if parameters.nameStatus??>${parameters.nameStatus}<#else>status</#if>",
					"__pushdataonremove_${parameters.name}.<#if parameters.nameStatus??>${parameters.nameStatus}<#else>status</#if>",
					"${parameters.name}.${parameters.nameKey}",
					"${parameters.name}.${parameters.nameParentKey}",
					"__pushdataonremove_${parameters.name}.${parameters.nameKey}"
				));
		</#if>
	}
	
	highlightText = function(text,matchStart, keyWord){
		var matchEnd = matchStart + keyWord.length - 1;
		var beforeMatch = text.slice(0, matchStart);
        var matchText = text.slice(matchStart, matchEnd + 1);
        var afterMatch = text.slice(matchEnd + 1);
        return beforeMatch + "<em>" + matchText + "</em>" + afterMatch;
	}
	
	var delay = (function(){
		  var timer = 0;
		  return function(callback, ms){
		    clearTimeout(timer);
		    timer = setTimeout(callback, ms);
		  };
	})();
	
	$('#keyWordSearch_${parameters.id}').keyup(function() {
	    delay(keyWordChange_${parameters.id} , 1000 );
	    $(".select6_${parameters.id} .spinner-search-div").show();
	});
	
	function ${parameters.id}_clearData(){
		$(".select6_${parameters.id} .select6-input > div:not(.clone):not(.input-down-arrow), .select6_${parameters.id} .select6-input > input").remove();
		refreshState_${parameters.id};
	}
	
	function ${parameters.id}_setInitInput(e){
		var id = null;
		if(typeof e.${parameters.nameKey} !== "undefined" && e.${parameters.nameKey} != null){
			id = e.${parameters.nameKey};
		}
		var idParent = null;
		if(typeof e.${parameters.nameParentKey} !== "undefined"  && e.${parameters.nameParentKey} != null){
			idParent = e.${parameters.nameParentKey};
		}
		
		if( id == null || idParent == null){
			console.log("id or idParent are not existed");
		}
		else{
			var name = e.${parameters.nameValue};
			
			var isDup = false;
			$('#select6-input-${parameters.id}').children('.select6-input-item').each(function(key,value){
			   var idInputSkill = $(value).children('.id_${parameters.id}').val();
			   if(idInputSkill == id){
				   isDup = true;
				   $(value).children('.select6-input-item-label').text(name);
			   }
			});
			
			if(!isDup){
				cloneInputItem_${parameters.id}(name, id, idParent);
			}
		}
		
		checkBox = $("#select6-select-item-sub-${parameters.id}-"+id);
		$(".select6_${parameters.id} .select6-select-item .check-box").removeClass("isChecked");
		checkBox.children(".check-box").addClass('isChecked');
		refreshState_${parameters.id}();
	}
	
	function ${parameters.id}_getExceptClearList(){
		var exceptList = ""
		return ;
	}
	
	refreshState_${parameters.id} = function(){
		//hilight group that has children selected
		$(".select6_${parameters.id} .group .select6-select-item").removeClass("group_has_input");
		
		$(".select6_${parameters.id} div:not(.clone)>.select6-input-item").each(function(key,value){
			var idInputParent = $(value).children('.id_${parameters.id}_parent').val();
			$("#select6-select-item-${parameters.id}-"+idInputParent).addClass("group_has_input");
		});
	}
	
</script>
