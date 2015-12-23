<#setting number_format="#.#####">
<style>
	<#include "css/select5.css">
	<#include "css/jquery.scrollbar.css">
</style>
<div class="select5-wrapper multiple select5_${parameters.id}">
	<div class="select5-input-box">
		<div class="select5-input-wrapper">
			<div class="select5-input" id="select5-input-${parameters.id}" onclick="openSelect5(event,$(this))">
				<div class="clone-input-${parameters.id}" style="display: none;">
					<div class="select5-input-item">
						<div class="select5-input-item-label" onmouseenter="showSubItem_${parameters.id}(event,$(this));" onmouseleave="hideSubItem_${parameters.id}(event,$(this));">
							<div class="select5-input-sub-item-wrapper" onclick="event.stopPropagation()">
								<span class="sub-item-{0}"/>
							</div>
							{1} {2}<input type="hidden" name="{3}" value="{0}" />{4}{5}
						</div>
						<div class="select5-input-remove" onclick="deleteInputItem_${parameters.id}(event,{0});"><i class="fa fa-times"></i></div>
					</div>
				</div>
				<div class="clone-sub-input-${parameters.id}" style="display: none;">
					<div class="select5-input-sub-item-label">{1}<input type="hidden" name="{2}" value="{0}" />{3}{4}</div>
				</div>
				<@s.iterator value="stack.findValue(parameters.name)" status="stat">
					<#if stack.findValue(parameters.itemKey)?? && stack.findValue(parameters.itemName)??>
						<input type='hidden' name='__pushdataonremove_${parameters.name}[${stat.index}].status' value='I'/>
					</#if>
				</@s.iterator>
			</div>
		</div>
	</div>
	<div class="select5-select-container">
		<div class="select5-select-col">
			<div class="select5-scroll mCustomScrollbar" data-mcs-theme="minimal-dark">
				<@s.iterator value="parameters.list">
					<#if parameters.listKey??>
			            <#if stack.findValue(parameters.listKey)??>
			              <#assign itemKey = stack.findValue(parameters.listKey)/>
			              <#assign itemKeyStr = stack.findString(parameters.listKey)/>
			            <#else>
			              <#assign itemKey = ''/>
			              <#assign itemKeyStr = ''/>
			            </#if>
			        <#else>
			            <#assign itemKey = stack.findValue('top')/>
			            <#assign itemKeyStr = stack.findString('top')>
			        </#if>
			        <#if parameters.listValue??>
			            <#if stack.findString(parameters.listValue)??>
			              <#assign itemValue = stack.findString(parameters.listValue)/>
			            <#else>
			              <#assign itemValue = ''/>
			            </#if>
			        <#else>
			            <#assign itemValue = stack.findString('top')/>
			        </#if>
			        <#if parameters.listCssClass??>
			            <#if stack.findString(parameters.listCssClass)??>
			              <#assign itemCssClass= stack.findString(parameters.listCssClass)/>
			            <#else>
			              <#assign itemCssClass = ''/>
			            </#if>
			        </#if>
			        <#if parameters.listCssStyle??>
			            <#if stack.findString(parameters.listCssStyle)??>
			              <#assign itemCssStyle= stack.findString(parameters.listCssStyle)/>
			            <#else>
			              <#assign itemCssStyle = ''/>
			            </#if>
			        </#if>
			        <#if parameters.listTitle??>
			            <#if stack.findString(parameters.listTitle)??>
			              <#assign itemTitle= stack.findString(parameters.listTitle)/>
			            <#else>
			              <#assign itemTitle = ''/>
			            </#if>
			        </#if>
					<div class="select5-select-item" id="select5-select-item-${itemKeyStr?html}">
						<div class="check-box <#if tag.contains(parameters.nameValue, itemKey) == true>isChecked</#if>" onclick="checkboxClick_${parameters.id}(event,$(this));">
							<input type="hidden" class="item_id_${parameters.id}" value="${itemKeyStr?html}"/>
						</div>
						<label style="font-size: 12px;">${itemValue?html}</label> 
						<i class="fa fa-chevron-right icon-right" style="color: #BBB"></i>
					</div>
				</@s.iterator>
			</div>
		</div>
		<div class="select5-select-col sub">
			<div class="select5-scroll mCustomScrollbar" data-mcs-theme="minimal-dark">
				<div class="clone${parameters.id}" style="display: none">
					<div class="select5-select-item" id="select5-select-item-sub-{0}" onclick="checkboxClick_${parameters.id}(event,$(this).children('.check-box'));">
						<div class="check-box" onclick="checkboxClick_${parameters.id}(event,$(this));">
							<input type="hidden" class="item_id_${parameters.id}" value="{0}"/>
							<input type="hidden" name="item_parent_id_${parameters.id}" value="{2}"/>
						</div>
						<label style="font-size: 12px;">{1}</label>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	<#include "js/select5.js">
	<#include "js/jquery.scrollbar.js">
</script>
<script>
	var addItemList_${parameters.id} = new Object();
	$(document).ready(function(){
		$(".select5_${parameters.id} .select5-scroll").mCustomScrollbar();
		$('.select5_${parameters.id} .scroll-element.scroll-y').hide(0);
		$('.select5_${parameters.id} .scroll-wrapper').on('mouseenter',function(){
			$(this).children('.select5_${parameters.id} .scroll-element.scroll-y').fadeIn(400);
		});
	    $('.select5_${parameters.id} .scroll-wrapper').on('mouseleave',function(){
			$(this).children('.select5_${parameters.id} .scroll-element.scroll-y').fadeOut(400);
		});
		findSubItem_${parameters.id}($('.select5_${parameters.id} .select5-select-item:first-of-type').children('.check-box').children('input[type="hidden"]').val());
		initItem_${parameters.id}();
	});
	var checkboxClick_${parameters.id} = function(event,e){
		event.stopPropagation();
		var value = e.parent().children('label').text();
		var key = e.children("input[type='hidden'][class='item_id_${parameters.id}']").val();
		if(e.children("input[type='hidden'][name='item_parent_id_${parameters.id}']").val() != null || e.children("input[type='hidden'][name='item_parent_id_${parameters.id}']").val() != 'undefined'){
			var parentId = e.children("input[type='hidden'][name='item_parent_id_${parameters.id}']").val();
		}else var parentId = null;
		if(e.hasClass('isChecked')){
			deleteInputItem_${parameters.id}(event,key,parentId);
		}else{
			addInputItem_${parameters.id}(event,key,value,parentId);
		}
	};
	function item_${parameters.id}(key,value){
		this.key = key;
		this.value = value;
	};
	var addInputItem_${parameters.id} = function(event,id,value,parentId,beanId){
		event.stopPropagation();
		<#if parameters.chooseLimit??>
			var checkItemLimit = 0;
			$.each( addItemList_${parameters.id}, function( key, val ) {
				checkItemLimit += val.listSubItem!=null?val.listSubItem.length:1;
			});
			if(checkItemLimit >= ${parameters.chooseLimit}){
				alert('<@s.text name="COMMON_SELECT_GROUP_LIMIT_TEXT"></@s.text> ${parameters.chooseLimit}');
			}else{
		</#if>		
		if(parentId != null && parentId != 'undefined'){
			var parentLabel = $('.select5_${parameters.id} input[type="hidden"][value="'+parentId+'"]:not([name="item_parent_id_${parameters.id}"])').parent().parent().children('label').text();
			var addItemListSub_${parameters.id} = new Object();
			var listSubItem = [];
			if(addItemList_${parameters.id}[parentId] != 'undefined' && addItemList_${parameters.id}[parentId] != null){
				listSubItem = addItemList_${parameters.id}[parentId].listSubItem;
			}
			if(addItemList_${parameters.id}[parentId] != 'undefined' || addItemList_${parameters.id}[parentId] != null){
				addItemListSub_${parameters.id}[id] = {value,beanId};
				listSubItem.push(addItemListSub_${parameters.id})
				addItemList_${parameters.id}[parentId] = {parentLabel,listSubItem};
			}
		}else{
			var parentLabel = value;
			addItemList_${parameters.id}[id] = {parentLabel,beanId};
		}
		refresh_check_item_${parameters.id}();
		<#if parameters.chooseLimit??>
			}
		</#if>
	}
	var deleteInputItem_${parameters.id} = function(event,id,parentId){
		event.stopPropagation();
		if(parentId != null && parentId != 'undefined'){
			$.each(addItemList_${parameters.id}[parentId].listSubItem, function(i,e){
				for(var el in e){
				    if(el === id) {
				        addItemList_${parameters.id}[parentId].listSubItem.splice(i,1);
				        return false;
				    }
			    }
			});
			if(addItemList_${parameters.id}[parentId].listSubItem.length == 0){
				delete addItemList_${parameters.id}[parentId];
			}
		}else{
			delete addItemList_${parameters.id}[id];
		}
		refresh_check_item_${parameters.id}();
	}
	var showSubItem_${parameters.id} = function(event,e){
		event.stopPropagation();
		if(e.children('.select5-input-sub-item-wrapper').children('.select5-input-sub-item-label').length>0)
		e.children('.select5-input-sub-item-wrapper').show();
	}
	var hideSubItem_${parameters.id} = function(event,e){
		event.stopPropagation();
		e.children('.select5-input-sub-item-wrapper').hide();
	}
	var num = 0;
	var refresh_check_item_${parameters.id} = function(){
		$(".select5_${parameters.id} #select5-input-${parameters.id}>.select5-input-item").remove();
		$(".select5_${parameters.id} .select5-select-col .check-box").removeClass('isChecked');
		$(".select5-select-col.sub").removeClass('disabled');
		$('.select5_${parameters.id} .select5-select-item').removeClass('selected');
		$.each( addItemList_${parameters.id}, function( key, val ) {
			var data = $(".select5_${parameters.id} .clone-input-${parameters.id}").html();
			if($('.select5_${parameters.id} .select5-input-item-label>input[type="hidden"][name^="${parameters.name}"][value="'+key+'"]') != null){
				$('.select5_${parameters.id} .clone-input-${parameters.id}').before(data.format(
					key, // {0} Parent Value
					val.parentLabel, // {1} Parent Label
					val.listSubItem!=null?"("+val.listSubItem.length+")":"", // {2} itemSize
					val.listSubItem==null?"${parameters.name}[" + num + "].${parameters.beanName}":"", // {3}
					val.listSubItem==null?"<input type='hidden' name='${parameters.name}[" + num + "].status' value='D'/>":"", // {4}
					val.listSubItem==null&&val.beanId!=null&&val.beanId!='undefined'?"<input type='hidden' name='${parameters.name}[" + num + "].${parameters.beanId}' value='"+val.beanId+"'/>":"" // {5}
				));
				num++;
			}
			if(val.listSubItem!=null){
				$('.select5_${parameters.id} #select5-select-item-'+key).addClass('selected');
				$.each(val.listSubItem,function( id, value ){
					var data = $(".select5_${parameters.id} .clone-sub-input-${parameters.id}").html();
					for(var el in value){
						var itemId = el;
					}
					if($('.select5_${parameters.id} .select5-input-sub-item-wrapper>input[type="hidden"][name^="${parameters.name}"][value="'+id+'"]') != null){
						$('.select5_${parameters.id} .sub-item-'+key).before(data.format(
							itemId, // {0} Value
							value[itemId].value, // {1} Label
							"<#if parameters.subName??>${parameters.subName}<#else>${parameters.name}</#if>[" + num + "].<#if parameters.subBeanName??>${parameters.subBeanName}<#else>${parameters.beanName}</#if>", // {2}
							"<input type='hidden' name='<#if parameters.subName??>${parameters.subName}<#else>${parameters.name}</#if>[" + num + "].status' value='D'/>", // {3}
							value[itemId].beanId!=null&&value[itemId].beanId!='undefined'?"<input type='hidden' name='${parameters.name}[" + num + "].${parameters.beanId}' value='"+value[itemId].beanId+"'/>":"" // {4}
						));
						
						$(".select5_${parameters.id} .select5-select-col.sub .check-box>input[type='hidden'][value='"+itemId+"']:not([name='item_parent_id_${parameters.id}'])").parent().addClass('isChecked');
						num++;
					}
				});
			}else{
				if($(".select5_${parameters.id} .select5-select-item.active>.check-box>input[type='hidden']:not([name='item_parent_id_${parameters.id}'])").val() == key){
					$(".select5_${parameters.id} .select5-select-col.sub .check-box>input[type='hidden'][value='"+key+"'][name='item_parent_id_${parameters.id}']").parent().parent().parent().parent().parent().parent('.select5-select-col.sub').addClass('disabled');
				}
				$(".select5_${parameters.id} .select5-select-col:not(.sub) .check-box>input[type='hidden'][value='"+key+"']:not([name='item_parent_id_${parameters.id}'])").parent().addClass('isChecked');
			}
		});
	}
	$('.select5_${parameters.id} .select5-select-item').on('click',function(){
		findSubItem_${parameters.id}(null,$(this));
	});
	function findSubItem_${parameters.id}(id,e){
		$('.select5_${parameters.id} .select5-select-item').removeClass('active');
		$('.select5_${parameters.id} .select5-select-col.sub .select5-scroll div:not(.clone${parameters.id})>.select5-select-item').remove();
		if(id != null && id != "undefined"){
			var idToFind = id;
		}else{
			$(this).addClass('active');
			var idToFind = e.children('.check-box').children('input[type="hidden"]').val();
		}
		$('.select5_${parameters.id} #select5-select-item-'+idToFind).addClass('active');
		var request = $.ajax({
			type: "POST",
			url: '${parameters.ajaxUrl}',
			data : { 
				idToFind: idToFind
				}
		})
		request.done(function( returnData ) {
			if (typeof returnData != 'undefined' && returnData != null && returnData.result != null){
				$.each( returnData.result, function( key, val ) {
					var data = $(".clone${parameters.id}").html();
					$('.clone${parameters.id}').before(data.format(
						val.value, // {0}
						val.label, // {1}
						idToFind
					));
				});
				refresh_check_item_${parameters.id}();
			}
		});
	}
	var initItem_${parameters.id} = function(){
		<@s.iterator value="stack.findValue(parameters.name)">
			<#if stack.findValue(parameters.itemKey)?? && stack.findValue(parameters.itemName)??>
				<#if stack.findValue(parameters.itemParentId)?? && parameters.typeOfIgnore == "P" >
					<#if stack.findValue(parameters.itemParentId)?? && stack.findValue(parameters.itemParentId) != stack.findValue(parameters.idParentIgnore) >
					//1
						addInputItem_${parameters.id}(event,${stack.findValue(parameters.itemKey)},"${stack.findValue(parameters.itemName)}",${stack.findValue(parameters.itemParentId)},${stack.findValue(parameters.beanId)});
						<#else>
						addInputItem_${parameters.id}(event,${stack.findValue(parameters.itemKey)},"${stack.findValue(parameters.itemName)}",null,${stack.findValue(parameters.beanId)});
					</#if>
				</#if>
				<#if stack.findValue(parameters.itemParentId)?? && parameters.typeOfIgnore == "L" >
					<#if stack.findValue(parameters.itemParentId)?? && stack.findValue(parameters.parentLevel)?? && stack.findValue(parameters.levelIgnore)?string != stack.findValue(parameters.parentLevel)?string>
					//2
						addInputItem_${parameters.id}(event,${stack.findValue(parameters.itemKey)},"${stack.findValue(parameters.itemName)}",${stack.findValue(parameters.itemParentId)},${stack.findValue(parameters.beanId)});
						<#else>
						addInputItem_${parameters.id}(event,${stack.findValue(parameters.itemKey)},"${stack.findValue(parameters.itemName)}",null,${stack.findValue(parameters.beanId)});
					</#if>
				</#if>
			</#if>
		</@s.iterator>
	}
</script>
