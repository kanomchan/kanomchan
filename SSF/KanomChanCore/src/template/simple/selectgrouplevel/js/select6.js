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
		load_item_${parameters.id}($('#select6-select-item-${parameters.id}-'+idGroup),autoFocus_${parameters.id},id${parameters.id});
	}
	
	autoFocus_${parameters.id} = function(id){
		$("#select6-select-item-sub-${parameters.id}-"+id).children(".item_profi_radio_${parameters.id}").show();
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
