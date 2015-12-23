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
	<#include "css/jquery.scrollbar.css">
	<#include "js/jquery.scrollbar.js">
</script>
<style>

	.select5r-wrapper{
	    width:  100%;
	    position: relative;
	}
	.select5r-input-box{
		border: 1px solid #CCC;
	}
	.select5r-input-wrapper{
	
	}
	.select5r-input{
		background-color: #FFF;
	    cursor: text;
	    min-height: 32px;
	    width: 100%;
	    display: inline-block;
	    margin-bottom: -5px;
	    padding-right: 5px;
	}
	.select5r-input-item{
	    position: relative;
	    float: left;
	    padding: 0px 5px;
	    background-color: #469ED4;
	    color: #FFF;
	    margin: 5px 0px 5px 5px;
	    border: 1px solid #415D8E;
	    border-radius: 4px;
	    cursor: default;
	}
	.select5r-input-box-cursor{
	    padding: 7px;
	    margin: 0;
	    border: 0px;
	    outline: none;
	    width: 16px;
	}
	.select5r-input-item-label{
	    float: left;
	    font-size: 14px;
	    padding-right: 20px;
	}
	.select5r-input-remove{
	    padding: 0px 3px;
	    background-color: #FFF;
	    color: #888;
	    margin-left: 7px;
	    margin-top: 3px;
	    border-radius: 4px;
	    cursor: pointer;
	    font-size: 9px;
	    position: absolute;
	    right: 5px;
	}
	.select5r-select-container{
	    width: 100%;
	    background-color: #FFF;
	    border: 1px solid #CCC;
	    padding: 0px;
	    max-height: 500px;
	    position: absolute;
	    z-index: 100;
	    margin-top: -1px;
	    display: none;
	
	}
	.select5r-select-item{
	    width: 100%;
	    padding: 10px 25px;
	    position: relative;
	    color: #888;
	    cursor: pointer;
	    transition: background-color 0.2s ease;
	}
	.select5r-select-item label{
	    cursor: pointer;
	}
	.select5r-select-item.active, .select5r-select-item.active.selected{
		background-color: #C9E9FF;
	    border-left: 3px solid #FF8D57;
	}
	.select5r-select-item.selected:hover{
		background-color: #DDF1FF;
	}
	.select5r-select-item.active:hover{
		background-color: #C9E9FF;
	}
	.select5r-select-item.selected{
		background-color: #DDF1FF;
	}
	.sub .select5r-select-item:hover{
		background-color: #93D3FF;
	}
	.select5r-select-item:hover{
		background-color: #E5E5E5;
	}
	.select5r-select-col{
		width: 50%;
		float: left;
		height: 250px;
	}
	.select5r-select-col.sub{
		background-color: #C9E9FF;
	}
	.select5r-select-col.sub.disabled{
		background-color: #F9F9F9 !important;
	}
	.select5r-select-col.sub.disabled .select5r-select-item label{
		color: #DDD !important;
	}
	.select5r-select-col.sub.disabled .check-box{
		background-color: #DDD;
	}
	.icon-right{
		position: absolute;
		right: 13px;
		top: 13px;
	}
	.select5r-scroll{
	    max-height: 250px;
	    height: 100%;
	}
	.select5r-scroll::-webkit-scrollbar {
		display: none;
	}
	.check-box{
	    position: absolute;
	    left: 5px;
	    background-color: #FFF;
	    width: 15px;
	    height: 15px;
	    border-radius: 4px;
	    border: 1px solid #BBB;
	    cursor: pointer;
	}
	.check-box.isChecked{
		font-family: FontAwesome;
	}
	.check-box.isChecked:before{
		content: "\f00c";
	    top: -1px;
	    left: 1px;
	    position: absolute;
	}
	.select5r-input-sub-item-wrapper{
		position: absolute;
	    min-width: calc(100% + 2px);
	    width: 250px;
	    background-color: #469ED4;
	    left: -1px;
	    bottom: -1px;
	    border-radius: 4px;
	    border: 1px solid #415D8E;
	    padding: 5px;
	    z-index: 10000;
	    display: none;
	}
	.select5r-input-sub-item-label{
	    background-color: #3577A0;
	    margin-bottom: 5px;
	    padding: 2px 5px;
	    border-radius: 4px;
	}
	.select5r-input-sub-item-label:last-of-type{
	    margin-bottom: 0px;
	}
	.select5r_${parameters.id} .radio-label{
		color: #888;
		font-size: 12px;
	}
	.item_profi_radio_${parameters.id}{
 		display:none; 
	}
	.select5r_${parameters.id} label em{
		font-weight:bold;
  		font-style:normal;
  		 background:#ff6;
	}
	.select5r_${parameters.id} .spinner-div{
		text-align: center;
		margin: 5px;
	}
</style>
<div class="select5r-wrapper multiple select5r_${parameters.id}" onclick="clickSearch_${parameters.id}(event)">
	<div class="select5r-input-box">
		<div class="select5r-input-box">
			<div class="select5r-input-wrapper">
				<div class="select5r-input" id="select5r-input-${parameters.id}" onclick="openselect5r_${parameters.id}(event,$(this))">
					<@s.iterator value="stack.findValue(parameters.nameList)" status="status">
						<div class="select5r-input-item" onclick="inputItemClick_${parameters.id}(event,$(this))">
							<div class="select5r-input-item-label">
							 <@s.property value=(parameters.nameValue)/> / <@s.property value=(parameters.nameProfiValue)/>
							</div>
							<input type="hidden" class="id_${parameters.id}" name="${parameters.name}[<@s.property value="#status.index"/>].${parameters.nameKey}" value="<@s.property value=(parameters.nameKey)/>"> 
							<input type="hidden" class="id_${parameters.id}_parent" name="${parameters.name}[<@s.property value="#status.index"/>].${parameters.nameParentKey}" value="<@s.property value=(parameters.nameParentKey)/>">
							<input type="hidden" class="id_radio" name="${parameters.name}[<@s.property value="#status.index"/>].${parameters.nameProfiKey}" value="<@s.property value=(parameters.nameProfiKey)/>">
							<input type="hidden" name="${parameters.name}[<@s.property value="#status.index"/>].<#if parameters.nameStatus??>${parameters.nameStatus}<#else>status</#if>" value="<#if parameters.nameStatusInitKey??>${parameters.nameStatusInitKey}<#else>A</#if>">
							<div class="select5r-input-remove" onclick="deleteInputItem_${parameters.id}(event,$(this).parent());"><i class="fa fa-times"></i></div>
						</div>
						<input type="hidden" name="item_count_${parameters.id}"/>
						<input type="hidden" name="__pushdataonremove_${parameters.name}[<@s.property value="#status.index"/>].<#if parameters.nameStatus??>${parameters.nameStatus}<#else>status</#if>" value="I">
					</@s.iterator>
					<div class="clone-input-${parameters.id}" style="display: none;">
						<div class="select5r-input-item" onclick="inputItemClick_${parameters.id}(event,$(this))">
							<div class="select5r-input-item-label" >
								{0} / {1} 
							</div>
							<input type="hidden" class="id_${parameters.id}" name="${parameters.name}[{5}].${parameters.nameKey}" value="{2}"> 
							<input type="hidden" class="id_${parameters.id}_parent" name="${parameters.name}[{5}].${parameters.nameParentKey}" value="{3}">
							<input type="hidden" class="id_radio" name="${parameters.name}[{5}].${parameters.nameProfiKey}" value="{4}">
							<input type="hidden" name="${parameters.name}[{5}].<#if parameters.nameStatus??>${parameters.nameStatus}<#else>status</#if>" value="<#if parameters.nameStatusInitKey??>${parameters.nameStatusInitKey}<#else>A</#if>">
							<div class="select5r-input-remove" onclick="deleteInputItem_${parameters.id}(event,$(this).parent());"><i class="fa fa-times"></i></div>
						</div>
						<input type="hidden" name="item_count_${parameters.id}"/>
						<input type="hidden" name="__pushdataonremove_${parameters.name}[{5}].<#if parameters.nameStatus??>${parameters.nameStatus}<#else>status</#if>" value="I">
					</div>
				</div>
			</div>
		</div>
		<div class="select5r-select-container" style="display: none;">
			<div class="row">
				<div class="col-md-12">
					<input type="text" name="keyWord" id="keyWordSearch_${parameters.id}" class="form-control" placeholder="Search" onclick="clickSearch_${parameters.id}(event)">
				</div> 
			</div>
			<div class="select5r-select-col group">
				<div class="select5r-scroll mCustomScrollbar" data-mcs-theme="minimal-dark">
					<div class="select5r-select-item all-group" id="select5r-select-item-${parameters.id}-0" onclick="groupClick_${parameters.id}(event,$(this))">
						<input type="hidden" class="item_id_${parameters.id}" value="0">
						<label style="font-size: 12px;"><@s.text name="COMMON_ALL_GROUP"/></label> 
						<i class="fa fa-chevron-right icon-right" style="color: #BBB"></i>
					</div>
					<@s.iterator value="stack.findValue(parameters.groupList)">
						<div class="select5r-select-item" id="select5r-select-item-${parameters.id}-<#if parameters.groupListKey??><@s.property value="${parameters.groupListKey}"/></#if>" onclick="groupClick_${parameters.id}(event,$(this))">
							<input type="hidden" class="item_id_${parameters.id}" value="<#if parameters.groupListKey??><@s.property value="${parameters.groupListKey}"/></#if>">
							<input type="hidden" class="item_name_${parameters.id}" value="<#if parameters.groupListValue??><@s.property value="${parameters.groupListValue}"/></#if>">
							<label style="font-size: 12px;"><#if parameters.groupListValue??><@s.property value="${parameters.groupListValue}"/></#if> </label> 
							<i class="fa fa-chevron-right icon-right" style="color: #BBB"></i>
						</div>
					</@s.iterator>
				</div>
			</div>
			<div class="select5r-select-col sub">
				<div class="select5r-scroll mCustomScrollbar" data-mcs-theme="minimal-dark" >
					<div class="clone${parameters.id}" style="display: none">
						<div class="select5r-select-item" id="select5r-select-item-sub-${parameters.id}-{0}" onclick="click_${parameters.id}(event,$(this));">
							<div class="check-box" onclick="checkboxClick_${parameters.id}(event,$(this).parent());">
								<input type="hidden" class="item_id_${parameters.id}" value="{0}">
								<input type="hidden" name="item_parent_id_${parameters.id}" value="{2}">
								<input type="hidden" class="item_name_${parameters.id}" value="{3}">
							</div>
							<label style="font-size: 12px;">{1}</label>
							<div class="item_profi_radio_${parameters.id}" >
								<@s.radio list="stack.findValue(parameters.profiList)"  
								name="radio_${parameters.id}_{0}" listKey="${parameters.profiListKey}" listValue="${parameters.profiListValue}" 
								onchange="radioChange_${parameters.id}(event,$('#select5r-select-item-sub-${parameters.id}-{0}'))"/>
							</div>
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
	var currentPage_${parameters.id};
	var  isLastPage_${parameters.id} = false;
	scrollEnd_${parameters.id} = function(){
		currentPage_${parameters.id}++;
		var idParent = $('.select5r_${parameters.id} .select5r-select-item.active').children('.item_id_${parameters.id}').val();
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
							$(".select5r_${parameters.id} .spinner-div").hide();
						}
						if(totalRows == 0 ){
							$(".select5r_${parameters.id} .spinner-div").hide();
						}
						
						$.each( jsonResponse.result.${parameters.subName}, function( key, val ) {
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

	$(document).on('click',function() {
		$('.select5r-select-container').hide();
	});
	var openselect5r_${parameters.id} = function(event,element) {
		$('.select5r_${parameters.id} .select5r-select-container').not(element.parent().parent().parent().children('.select5r-select-container')).hide();
		element.parent().parent().parent().children('.select5r-select-container').toggle();
		event.stopPropagation();
	};
	$(document).ready(function(){
		$(".select5r_${parameters.id} .select5r-select-col.group .select5r-scroll").mCustomScrollbar();
		$(".select5r_${parameters.id} .select5r-select-col.sub .select5r-scroll").mCustomScrollbar({
				callbacks : {onScroll:function(){
					if(this.mcs.topPct > 90	){
						scrollEnd_${parameters.id}();
					}
				}}
		});
		$('.select5r_${parameters.id} .scroll-element.scroll-y').hide(0);
		load_item_${parameters.id}($('#select5r-select-item-${parameters.id}-0'),null);

	});
	
	clickSearch_${parameters.id} = function(event){
		event.stopPropagation();
	}
	
	checkboxClick_${parameters.id} = function(event,e){
		event.stopPropagation();
		var checkBox = e.children('.check-box');
		if(checkBox.hasClass('isChecked')){
			checkBox.removeClass('isChecked'); 
			e.children('.item_profi_radio_${parameters.id}');
			$(e).find("[type='radio']").attr("checked" , false);
			$('#select5r-input-${parameters.id} .select5r-input-item').has("input.id_${parameters.id}[value="+ $(checkBox).children(".item_id_${parameters.id}").val() +"]").remove();
		}
		else{
			checkBox.addClass('isChecked');
		}
		e.children('.item_profi_radio_${parameters.id}').show();
		
	}
	
	groupClick_${parameters.id} = function(event,e){
		event.stopPropagation();
		
		load_item_${parameters.id}(e, null);
	}
	
	load_item_${parameters.id} = function(e,func,idItem){
		if(e.attr("id") != $('.select5r_${parameters.id} .select5r-select-item.active').attr("id")){
			$('.select5r_${parameters.id} .select5r-select-item').removeClass('active');
		}
		e.addClass("active");
		
		$('.select5r_${parameters.id} .select5r-select-col.sub .select5r-scroll div:not(.clone${parameters.id})>.select5r-select-item').remove();
		
		var idParent = e.children('.item_id_${parameters.id}').val();
		var keyWord = $("#keyWordSearch_${parameters.id}").val();
		currentPage_${parameters.id} = 1;
		isLastPage_${parameters.id} = false;
		$(".select5r_${parameters.id} .spinner-div").show();
		
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
				if (typeof jsonResponse != 'undefined' && jsonResponse.success && jsonResponse != null && jsonResponse.result != null && jsonResponse.result.${parameters.subName}.length > 0){
					$.each( jsonResponse.result.${parameters.subName}, function( key, val ) {
						var rowsPerPage = jsonResponse.pagingBean.rowsPerPage;
						var totalRows = jsonResponse.pagingBean.totalRows;
						var totalPage = totalRows/rowsPerPage;
						if(totalRows % rowsPerPage > 0){
							totalPage++;
						}
						
						if(totalRows <= rowsPerPage || currentPage_${parameters.id}+1 > totalPage || totalRows == 0){
							isLastPage_${parameters.id} = true;
							$(".select5r_${parameters.id} .spinner-div").hide();
						}
						
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
						
					});
					
					
					setInputToItemList_${parameters.id}();
					if (typeof func != 'undefined' && func != null && typeof idItem != 'undefined' && idItem != null){
						func(idItem);
					}
				}
				else{
					$(".select5r_${parameters.id} .spinner-div").hide();
				}
			}
			
		});
	}
	
	setInputToItemList_${parameters.id} = function(){
		
		$('#select5r-input-${parameters.id}').children('.select5r-input-item').each(function(key,value){
		   	var idInputSkill = $(value).children('.id_${parameters.id}').val();
		   
		 	var checkBox = $("#select5r-select-item-sub-${parameters.id}-"+idInputSkill).children('.check-box');
			if(!checkBox.hasClass('isChecked')){
				checkBox.addClass('isChecked');
			}
			  
			var idRadio = $(value).children('.id_radio').val();
			$("#select5r-select-item-sub-${parameters.id}-"+idInputSkill+" input[type='radio']").filter('[value="'+idRadio+'"]').attr('checked', true);
		});
	}
	
	click_${parameters.id} = function(event, e){
		event.stopPropagation();
		e.children('.item_profi_radio_${parameters.id}').toggle();
	}
	
	radioChange_${parameters.id} = function(event, e){
		event.stopPropagation();
		var checkBox = e.children('.check-box');
		if(!checkBox.hasClass('isChecked')){
			checkBox.addClass('isChecked');
		}
		setItemToInput_${parameters.id}(e);
	}
	
	setItemToInput_${parameters.id} = function(e){
		var id = e.children('.check-box').children('.item_id_${parameters.id}').val();
		var idParent = e.children('.check-box').children('[name="item_parent_id_${parameters.id}"]').val();
		
		var name = e.children('label').text();
		var radioChecked = $('#select5r-select-item-sub-${parameters.id}-'+ id + ' .item_profi_radio_${parameters.id} input[name="radio_${parameters.id}_'+id+'"]:checked');
		var idRadio = radioChecked.val();
		var textRadio = radioChecked.next().text();
		
		var isDup = false;
		$('#select5r-input-${parameters.id}').children('.select5r-input-item').each(function(key,value){
		   var idInputSkill = $(value).children('.id_${parameters.id}').val();
		   if(idInputSkill == id){
			   isDup = true;
			   $(value).children('.select5r-input-item-label').text(name+" / "+textRadio);
			   $(value).children('.id_radio').val(idRadio);
		   }
		});
		
		if(!isDup){
			var data = $(".clone-input-${parameters.id}").html();
			$('.clone-input-${parameters.id}').before(data.format(
				name, // {0}
				textRadio, // {1
				id,
				idParent,
				idRadio,
				$(".select5r_${parameters.id} div:not(.clone-input-${parameters.id})>input[name='item_count_${parameters.id}'] ").length
			));
		}
		
	}
		
	deleteInputItem_${parameters.id} = function(event,e){
		event.stopPropagation();
		var id_input_item = e.children('.id_${parameters.id}').val();
		$("#select5r-select-item-sub-${parameters.id}-"+ id_input_item+ " .check-box").removeClass("isChecked");
		$("#select5r-select-item-sub-${parameters.id}-"+ id_input_item+ " input[type='radio']").attr("checked" , false);
		e.remove();
	}
	
	inputItemClick_${parameters.id} = function(event,e){
		event.stopPropagation();
		var idActiveParent = $('.select5r-select-item.active').children(".item_id_${parameters.id}").val();
		var id${parameters.id} = $(e).find(".id_${parameters.id}").val();
		var idGroup = $(e).find(".id_${parameters.id}_parent").val();
		$('#keyWordSearch_${parameters.id}').val("");
		load_item_${parameters.id}($('#select5r-select-item-${parameters.id}-'+idGroup),autoFocus_${parameters.id},id${parameters.id});
	}
	
	autoFocus_${parameters.id} = function(id){
		$("#select5r-select-item-sub-${parameters.id}-"+id).children(".item_profi_radio_${parameters.id}").show();
	}
	
	function keyWordChange_${parameters.id}(){
		var keyWord = $('#keyWordSearch_${parameters.id}').val();
		if(keyWord === ""){
			$(".select5r_${parameters.id} .select5r-select-col.group .select5r-select-item").each(function(key,value){
				var text = $(value).children(".item_name_${parameters.id}").val();
				$(value).children("label").html(text);
			}).show();
			load_item_${parameters.id}($(".select5r_${parameters.id} .select5r-select-col.group .select5r-select-item.active"), null);
		}
		else{
			$.ajax({
				type : "POST",
				url : '${parameters.searchGroupUrl}',
				data : { "keyWord": keyWord},
				cache : false,
				success : function(jsonResponse) {
					$(".select5r_${parameters.id} .select5r-select-col.group .select5r-select-item:not(.all-group)").each(function(key,value){
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
							$('#select5r-select-item-${parameters.id}-'+ val.<#if parameters.searchGroupUrlKey??>${parameters.searchGroupUrlKey}<#else>${parameters.groupListKey}</#if>).show();
						});
					}
					
					var isMatched = false;
					
					load_item_${parameters.id}($(".select5r_jobSpecialSkillListItems .select5r-select-item.active"), null);
					
				}
			});
		}
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
	    delay(keyWordChange_${parameters.id} , 2000 );
	});
	
</script>
