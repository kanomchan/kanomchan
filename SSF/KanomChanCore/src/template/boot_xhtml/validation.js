
function clearErrorLabelsXHTML(form) {
    var i, elements = form.elements;
    for (i = 0; i < elements.length; i++) {
        var parentEl = elements[i];
        var field = parentEl;
        var divRow = $(field).parent().parent();
        var formGroup = divRow.parent();
        var rowError = formGroup.attr("errorPosition") == "bottom" ? divRow.next() : divRow.prev();
        var errorChild = rowError.children();
        errorChild.hide();
        $(field).closest('.form-group').removeClass('has-error').addClass('has-success');
        if(errorChild.prop("role") == "alert")
        	errorChild.text("");
        else
        	errorChild.children().text("");
        
    }

}

function clearErrorLabels(form) {
    clearErrorLabelsXHTML(form);
}

function addErrorXHTML(e, errorText) {
    try {
        var field = (e.type ? e : e[0]);
        var divRow = $(field).parent().parent();
        var formGroup = divRow.parent();
        var rowError = formGroup.attr("errorPosition") == "bottom" ? divRow.next() : divRow.prev();
        var errorChild = rowError.children();
        errorChild.slideDown();
        $(field).closest('.form-group').removeClass('has-success').addClass('has-error');
        if(errorChild.prop("role") == "alert")
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

