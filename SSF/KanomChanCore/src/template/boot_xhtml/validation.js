function clearErrorLabelsXHTML(form) {
	var formFormGroup = $(form).find('.form-group');
	$(formFormGroup).each(function() {
		$(this).removeClass('has-error').addClass('has-success');
		var rowError = $(this).find(".rowError");
		var errorChild = rowError.children();
		errorChild.hide();
		if (errorChild.prop("role") == "alert")
			errorChild.text("");
		else
			errorChild.children().text("");
	});

	// var i, elements = form.elements;
	//    
	// for (i = 0; i < elements.length; i++) {
	// var parentEl = elements[i];
	// var field = parentEl;
	// var divRow = $(field).parent().parent();
	// var formGroup = divRow.parent();
	// var rowError = formGroup.attr("errorPosition") == "bottom" ?
	// divRow.next() : divRow.prev();
	// var errorChild = rowError.children();
	// errorChild.hide();
	// $(field).closest('.form-group').removeClass('has-error').addClass('has-success');
	//        
	// $(field).closest('.form-group').removeClass('has-success').addClass('has-error');
	// var rowError = formGroup.find(".rowError");
	// var errorChild = rowError.children();
	// errorChild.hide();
	// if(errorChild.prop("role") == "alert")
	// errorChild.text("");
	// else
	// errorChild.children().text("");
	//        
	// }

}

function clearErrorLabels(form) {
	clearErrorLabelsXHTML(form);
}

function addErrorXHTML(e, errorText) {
	try {
		var fieldError = $("[name='" + e + "Error']");
//		var field = (e.type ? e : e[0]);
		var formGroup = fieldError.closest('.form-group');
//		var rowError = formGroup.find(".rowError");
		var errorChild = fieldError.children();
		errorChild.slideDown();
		formGroup.removeClass('has-success').addClass('has-error');
		if (errorChild.prop("role") == "alert")
			errorChild.text(errorText);
		else
			errorChild.children().text(errorText);

	} catch (err) {
		alert(err);
	}
}

function addError(e, errorText) {
	addErrorXHTML(e, errorText);
}
