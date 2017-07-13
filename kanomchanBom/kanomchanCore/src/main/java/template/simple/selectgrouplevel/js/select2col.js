(function($){
	$.widget( "jm.select2col", {
		options: {
			isSingle : false,
			input_limit : false,
			label_col_parent : "parent",
			label_col_child : "child",
			label_all_group : "all group",
			label_not_found : "not found",
			input_setting : {
				name_prefix: "name"
				,nameKey: "nameKey"
				,nameParentKey: "nameParentKey"
				,nameStatus: "nameStatus"
				,nameToSaveKey: "nameToSaveKey"
				,status: "A"
			},
			isMinusOneOnRemove : true,
			isNameToSave : true,
			inputInit : {
				data:[{name:"input1", id:1, id_parent: 1, id_to_save: 50}]
			},
			parentInit :{
				data:[{name:"parent 1", id:1},{name:"parent 2", id:2},{name:"parent 3", id:3},{name:"parent 4", id:4}]
			},
			getChild : function(id_parent, currentPage, keyword, widget){
				var tempChildList = [
					[],
					[{name:"Child 1",id:1, id_parent: 1},{name:"Child 2",id:2, id_parent: 1},{name:"Child 3",id:3, id_parent: 1},
					{name:"Child 4",id:4, id_parent: 1},{name:"Child 5",id:5, id_parent: 1},{name:"Child 6",id:6, id_parent: 1},
					{name:"Child 7",id:7, id_parent: 1},{name:"Child 8",id:8, id_parent: 1},{name:"Child 9",id:9, id_parent: 1}],
					[{name:"C 55",id:55, id_parent: 2},{name:"Child 110",id:110, id_parent: 2}],
					[{name:"Child 48",id:48, id_parent: 3},{name:"Child 64",id:64, id_parent: 3},{name:"Child 21",id:21, id_parent: 3}]
				];

				widget.getChildCallback(tempChildList[id_parent]);
			},
			getParentHasSearchChild: function(keyWord,widget){
				widget.getParentHasSearchChildCallBack();
			}
		},
		state:{
			isLoading : false,
			isLastPage : false,
			currentIdParent : -1,
			currentPage : 1,
			indexInput : 0,
			keyword : ''
		},
		_create: function(){
			var widget = this;
			if (!String.prototype.format) {
			  	this._addFormat();
			}
			console.log(this.options.inputInit);
			//create element
			
			var selectBody = this.body_model.format( this.options.label_col_parent,this.options.label_col_child, this.options.label_not_found);

			this.element.addClass("select2col select2col-wrapper").append(selectBody);

			var dataInput = this.options.inputInit.data;
			this._createInput(dataInput);

			var dataParent = this.options.parentInit.data;

			this._createParent(dataParent);
			this._change_parent(0);
			
			//open and close select container
			this._handler_openCloseSelectContainer();

			//make arrow functional
			this._handler_arrowFunction();

			//search
			this._handler_search();

			this.element.find(".select2col-select-container .select2col-select-col.group .mCustomScrollbar").mCustomScrollbar();
			this.element.find(".select2col-select-container .select2col-select-col.sub .mCustomScrollbar").mCustomScrollbar({
				callbacks : {onScroll:function(){
					if(this.mcs.topPct > 90	&& !widget.state.isLoading){
						widget._scrollBottom();
					}
				}}
			});
		},

		_createInput : function(data, index){
			var widget = this;
			var new_input = '';
			if(typeof index !== 'undefined' ){
				new_input += this._makeInput(data[index]);
				this.state.indexInput += 1 ;
			}
			else if(Object.prototype.toString.call( data ) === '[object Array]'){
				$.each(data, function(index,value){
					new_input += widget._makeInput(value);
					widget.state.indexInput += 1 ;
				});
			}
			else{
				new_input += this._makeInput(data);
				this.state.indexInput += 1 ;
			}

			$(new_input).on("click",function(event){
				event.stopPropagation();
				widget._change_parent($(this).find('input.idParent').val());
				widget.element.find(".select2col-select-container").show();
			}).appendTo(this.element.find(".select2col-input-wrapper .select2col-input"));

			this.element.find(".select2col-input-wrapper .select2col-input-remove").unbind("click").on("click",function(event){
				event.stopPropagation();
				widget._removeInput($(this).parent());
			});
		},
		_removeInput : function(e){
			var id = e.find("input.id").val();
			this.element.find(".select2col-select-container > div.select2col-select-col.sub .select2col-select-item").has("input.id[value="+id+"]").trigger("childUncheck");
			e.remove();
		},
		_createParent : function(parentList){
			var widget = this;
			var parent = this._makeParent({name:this.options.label_all_group, id: 0});
			$.each(parentList,function(index,value){
				parent += widget._makeParent(value);
			});

			$(parent).on("click",function(event){
				if(!$(this).hasClass('active')){
					if(widget.state.currentIdParent != $(this).find("input.id").val()){
						widget._change_parent($(this).find("input.id").val());
					}
				}
			}).on("select",function(event){
				$(this).addClass('active');
			}).on("unSelect",function(event){
				$(this).removeClass('active');		
			}).appendTo(this.element.find(".select2col-select-container .select2col-select-col.group .select2col-scroll .group-wrapper"));
		},

		_change_parent : function(id_parent){
			this.state.isLastPage = false;
			this.state.currentIdParent = id_parent;
			this.state.currentPage = 1;
			this.element.find('.select2col-select-container .select2col-select-col.sub .select2col-scroll div.sub-wrapper').empty();
			var selectedParent = this.element.find(".select2col-select-container .select2col-select-col.group .select2col-scroll .select2col-select-item.parent").has("input.id[value="+id_parent+"]");
			this._loadChildList(id_parent);
			selectedParent.trigger("select");
			selectedParent.siblings().trigger("unSelect");
		},

		_scrollBottom : function(){		
			this.state.isLoading = true;
			this._loadChildList();
		},

		_loadChildList : function(id_parent){
			if(!this.state.isLastPage){
				this.element.find(".select2col-select-col.sub .not-found-result").hide();
				this.element.find(".select2col-select-col.sub .spinner-div").show();
				var idParent = (typeof id_parent === 'undefined')? this.state.currentIdParent : id_parent;
				this.options.getChild(idParent,this.state.currentPage, this.state.keyword,this);
			}
		},

		getChildCallback(childList,pagingBean){
			console.log("callback")
			var spinner = this.element.find(".select2col-select-col.sub .spinner-div");
			var notFound = this.element.find(".select2col-select-col.sub .not-found-result");
			if(typeof pagingBean !== 'undefined'){
				var rowsPerPage = pagingBean.rowsPerPage;
				var totalRows = pagingBean.totalRows;
				var totalPage = totalRows/rowsPerPage;
				if(totalRows <= rowsPerPage || this.state.currentPage + 1 > totalPage || totalRows == 0){
					this.state.isLastPage = true;
					spinner.hide();
				}
			}
			if(childList.length == 0){
				notFound.show();
			}
			this.state.currentPage += 1;
			this.state.isLoading = false;
			this._createChild(childList);
		},

		getChild : function(id_parent, currentPage, keyword, widget){
			var tempChildList = [
				[],
				[{name:"Child 1",id:1, id_parent: 1},{name:"Child 2",id:2, id_parent: 1},{name:"Child 3",id:3, id_parent: 1},
				{name:"Child 4",id:4, id_parent: 1},{name:"Child 5",id:5, id_parent: 1},{name:"Child 6",id:6, id_parent: 1},
				{name:"Child 7",id:7, id_parent: 1},{name:"Child 8",id:8, id_parent: 1},{name:"Child 9",id:9, id_parent: 1}],
				[{name:"C 55",id:55, id_parent: 2},{name:"Child 110",id:110, id_parent: 2}],
				[{name:"Child 48",id:48, id_parent: 3},{name:"Child 64",id:64, id_parent: 3},{name:"Child 21",id:21, id_parent: 3}]
			];

			widget.getChildCallback(tempChildList[id_parent]);
		},

		_check_selectedChild(){
			var listChild = this.element.find(".select2col-select-container .select2col-select-col.sub .select2col-scroll .select2col-select-item");
			var widget = this;
			this.element.find("div.select2col-input-wrapper .select2col-input-item input.id").each(function(index){
				listChild.has("input.id[value="+$(this).val()+"]").trigger('childCheck');
			});
		},

		_createChild : function(childList){
			var widget = this;
			var child = "";
			$.each(childList,function(index,value){
				child += widget._makeChild(index,value);
			}); 

			$(child).on("click",function(event){
				var index = $(this).find("input.index").val();
				var childData = childList[index];
				var inputItem = widget.element.find("div.select2col-input-wrapper .select2col-input-item").has("input.id[value='"+childData.id+"']");
				
				if(inputItem.length > 0 ){
					widget._removeInput(inputItem);
				}
				else{
					$(this).trigger("childCheck");
					widget._addInput($(this), childData );
				}
				
			}).on("childToggle",function(event){
				var checkBox = $(this).find(".check-box");
				if(checkBox.hasClass("isChecked")){
					checkBox.removeClass("isChecked");
				}
				else{
					checkBox.addClass("isChecked");
				}
			}).on("childUncheck",function(){
				$(this).find(".check-box").removeClass("isChecked");
			}).on("childCheck",function(){
				$(this).find(".check-box").addClass("isChecked");
			}).appendTo(this.element.find(".select2col-select-container .select2col-select-col.sub .select2col-scroll .sub-wrapper"));
			this._check_selectedChild();
		},
		_addInput : function(eleChild, childData){
			var widget = this;
			if(this.options.isSingle){
				this.element.find("div.select2col-input-wrapper .select2col-input-item").each(function(index){
					widget._removeInput($(this));
				});
			}
			this._createInput(childData);
		},

		//event for open select containner
		_handler_openCloseSelectContainer : function(){
			var widget = this;
			this.element.on("click",function(event){
				event.stopPropagation();
			});
			this.element.find(".select2col-input-wrapper").on("click",function(event){
				$(this).parent().find(".select2col-select-container").toggle();
			});
			$(document).on("click", function(event){
				widget.element.find(".select2col-select-container").hide();
		    });
		    this.element.find(".select2col-input-wrapper .select2col-input .select2col-input-item").on("click",function(event){
				event.stopPropagation();
				widget.element.find(".select2col-select-container").show();
			});
		},
		//event for arrow
		_handler_arrowFunction : function(){
			var widget = this;
			this._customHide();
			this._customShow();
			this.element.find(".select2col-select-container").on("show",function(event){
				widget.element.find("div.input-down-arrow span.glyphicon").removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
			}).on("hide",function(event){
				widget.element.find("div.input-down-arrow span.glyphicon").removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
			});
		},
		//start search
		_handler_search : function(){
			var widget = this;
			this.element.find(".select2col-select-container div div input[name='keyword']").on("keyup",function(event){
				var val = $(this).val();
				var search = function(){
					widget._search_by_keyword(val);
				};
				widget._delay(search , 1000 );
	    		widget.element.find(".select2col-select-container div div .spinner-search-div").show();
			});
		},

		_search_by_keyword : function(keyword){
			this.state.keyword = keyword;
			this.element.find(".select2col-select-container div div .spinner-search-div").hide();
			if(keyword === ""){
				this._keywordParent();
			}
			else{
				this.options.getParentHasSearchChild(keyword,this);
			}
			this._change_parent(this.state.currentIdParent);
		},

		_keywordParent : function(){
			var widget = this;
			var keyword = this.state.keyword;
			$.each(this.options.parentInit.data,function(key,val){
				var p = widget.element.find(".select2col-select-item.parent").has("input.id[value='"+val.id+"']");
				var matchStart = val.name.toLowerCase().indexOf(keyword.toLowerCase());
				if (matchStart >= 0){
					p.show();
					var newName = widget._highlightText(val.name,matchStart,keyword);

					p.find("label").empty().append(newName);
				}else{
					p.hide();

				}
			});
		},

		getParentHasSearchChildCallBack : function(arr){
			var widget = this;
			this._keywordParent();
			$.each(arr,function(key,val){
				widget.element.find(".select2col-select-item.parent").has("input.id[value='"+val+"']").show();
			});
		},
		clearData : function(e){
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
		},
		//search end
		_makeInput : function(data){
			if("" != data.id && data.id != null){
				var hiddenInput = "<input type='hidden' class='id' value='"+data.id+"'/>"
	 			+ "<input type='hidden' class='idParent' value='"+data.id_parent+"'/>";
				var leftOver = "";
				var inputName = this.options.input_setting;
				var arraySlot = (!this.options.isSingle)? '['+this.state.indexInput+']' : '';
				
				var	nameKey = inputName.name_prefix+arraySlot+"."+inputName.nameKey;
				var	nameParentKey = inputName.name_prefix+arraySlot+"."+inputName.nameParentKey;
				var	nameStatus = inputName.name_prefix+arraySlot+"."+inputName.nameStatus;
				var	nameToSaveKey = inputName.name_prefix+arraySlot+"."+inputName.nameToSaveKey;
				
				hiddenInput += "<input type='hidden' name='"+nameKey+"' value='"+data.id+"'/>" 
							+ "<input type='hidden' name='"+nameParentKey+"' value='"+data.id_parent+"'/>"
							+ "<input type='hidden' name='"+nameStatus+"' value='"+inputName.status+"'/>";
				leftOver ="<input type='hidden' name='__pushdataonremove_"+nameStatus+"' value='I'/>";
				if(this.options.isMinusOneOnRemove){
					leftOver += "<input type='hidden' name='__pushdataonremove_"+nameKey+"' value='-1' />"
				}
				if(typeof data.id_to_save  !== "undefined" && data.id_to_save != null && data.id_to_save != 0){
					leftOver += "<input type='hidden' name='"+nameToSaveKey+"' value='"+data.id_to_save+"' />"
				}
				
				return this.input_model.format(data.name, hiddenInput,leftOver);
			}
		},

		_makeParent : function(data){
			var hiddenInput = '<input type="hidden" class="id" value="'+data.id+'"/>';
			var keyWord = this.state.keyword;
			var name = '';
			if(keyWord != null && keyWord != "" ){
				var text = data.name;
				var matchStart = text.toLowerCase().indexOf(keyWord.toLowerCase());
				if (matchStart >= 0){
					name = this._highlightText(text,matchStart,keyWord);
				}
				else{
					name = text;
				}
			}
			else{
				name =  data.name;
			}
			return this.parent_model.format(name,hiddenInput);
		},

		_makeChild : function(index,data){
			var keyWord = this.state.keyword;
			var name = '';
			if(keyWord != null && keyWord != "" ){
				var text = data.name;
				var matchStart = text.toLowerCase().indexOf(keyWord.toLowerCase());
				if (matchStart >= 0){
					name = this._highlightText(text,matchStart,keyWord);
				}
				else{
					name = text;
				}
			}
			else{
				name =  data.name;
			}
			return this.child_model.format(name,index,data.id);
		},
		
		body_model :  '<div class="select2col-input-box">'
				      + '<div class="select2col-input-wrapper">'
				      +   '<div class="select2col-input">'
				      +   '<div class="input-down-arrow"><div><span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span></div></div>'
				      +	   '</div>'
				      +  '</div>'
				      + '<div class="select2col-select-container" style="display: none;">'
				      +   '<div class="row"><div class="col-md-12">'
					  +      '<input type="text" name="keyword" class="form-control" placeholder="Search" onclick="">'
					  +      '<div class="spinner-search-div"><i class="fa fa-spinner fa-2x fa-spin"></i></div>'
					  +   '</div></div>'
					  +	  '<div class="row title-container">'
					  +    	'<div class="col-xs-6"><div><label> {0} </label></div></div>'
					  +    	'<div class="col-xs-6"><div><label> {1} </label></div></div>'
					  +   '</div>'
				      +   '<div class="select2col-select-col group">'
				      +     '<div class="select2col-scroll mCustomScrollbar" data-mcs-theme="minimal-dark">'
				      +			'<div class="group-wrapper">'
				      +         '</div>'
                      +     '</div>'
				      +    '</div>' 
				      +   '<div class="select2col-select-col sub">'
				      +     '<div class="select2col-scroll mCustomScrollbar" data-mcs-theme="minimal-dark">'
				      +			'<div class="sub-wrapper">'
				      +         '</div>'  
				      +         '<div class="spinner-div"><i class="fa fa-spinner fa-2x fa-spin"></i></div>'
					  +   		'<div class="not-found-result"><label>{2}</label></div>'
                      +     '</div>'
				      +   '</div>'
				      +  '</div>'
				      +'</div>',
 
		input_model : '<div class="select2col-input-item">'
					  +  '<div class="select2col-input-item-label">{0}</div>'  
					  +    '{1}'
					  +   '<div class="select2col-input-remove"><i class="fa fa-times"></i></div>'
					  +'</div>'
					  +'{2}',

		parent_model : '<div class="select2col-select-item parent" >'
					   +   '<label style="font-size: 12px;"> {0} </label>'
					   +   '{1}'
					   +   '<i class="fa fa-chevron-right icon-right" style="color: #BBB"></i>'
					   +'</div>',

		child_model : 	'<div class="select2col-select-item" >' 
					    +  '<div class="check-box"></div>'
					    +  '<input type="hidden" class="index" value="{1}"/>'
					    +  '<input type="hidden" class="id" value="{2}" />'
					    +  '<label style="font-size: 12px;"> {0} </label>'
					   	+'</div>',

        //custom show hide event trigger
		_customHide : function(){
			var _oldhide = $.fn.hide;
			$.fn.hide = function(speed, callback) {
			    $(this).trigger('hide');
			    return _oldhide.apply(this,arguments);
			}
		},
		_customShow : function(){
			var _oldshow = $.fn.show;
			$.fn.show = function(speed, callback) {
			    $(this).trigger('show');
			    return _oldshow.apply(this,arguments);
			}
		},

		//add method format to String prototype
		_addFormat : function(){
			String.prototype.format = function() {
				var args =[];
				for( var i in arguments ) {
					if (arguments.hasOwnProperty(i)){
						args.push(arguments[i]);
					}
				}
				return this.replace(/{(\d+)}/g, function(match, number) {
				    return typeof args[number] != 'undefined'? args[number]: match;
				});
			};
		},

		_highlightText : function(text,matchStart, keyWord){
			var matchEnd = matchStart + keyWord.length - 1;
			var beforeMatch = text.slice(0, matchStart);
	        var matchText = text.slice(matchStart, matchEnd + 1);
	        var afterMatch = text.slice(matchEnd + 1);
	        return beforeMatch + "<em>" + matchText + "</em>" + afterMatch;
		},
		_delay : (function(){
			var timer = 0;
			return function(callback, ms){
				clearTimeout(timer);
				timer = setTimeout(callback, ms);
			};
		})()	      

	});
})(jQuery);
