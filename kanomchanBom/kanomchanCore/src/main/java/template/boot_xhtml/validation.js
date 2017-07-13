function clearErrorLabelsXHTML(form) {
	var formFormGroup = $(form).find('.form-group');
	$(formFormGroup).each(function() {
		$(this).removeClass('has-error').addClass('has-success');
//		var rowError = $(this).find(".rowError");
//		var errorChild = rowError.children();
//		errorChild.hide();
//		if (errorChild.prop("role") == "alert")
//			errorChild.text("");
//		else
//			errorChild.children().text("");
		$(this).find('.message').hide();
//		if(fieldError.attr("hasLabel") == 'true'){
//			fieldError.find(".message").slideUp();
//		}else{
//			fieldError.text(errorText).slideUp();
//		}
	});
}

function clearErrorLabels(form) {
	clearErrorLabelsXHTML(form);
}

function addErrorXHTML(e, errorText) {
	try {
		var fieldError = $("[name='" + e + "Error']");
		var formGroup = fieldError.closest('.form-group');
		formGroup.removeClass('has-success').addClass('has-error');
		if(fieldError.attr("hasLabel") == 'true'){
			fieldError.find(".message").text(errorText).slideDown();
		}else{
			fieldError.text(errorText).slideDown();
		}

	} catch (err) {
		alert(err);
	}
}

function addError(e, errorText) {
	addErrorXHTML(e, errorText);
}
