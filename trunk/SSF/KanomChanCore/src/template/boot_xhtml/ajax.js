



function getData(from , fieldName){
	
	
	
//	var fieldName = "${tagName?replace(".", "\\\\.")}";
	var field = $("[name='" + fieldName + "']");
	var valueName = "";
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
	
	return valueName;
}
function getDataFrom(from){
	var values = {};
	$.each($('#'+from).serializeArray(), function(i, field) {
	    values[field.name] = field.value;
	});
	return values;
	
}


