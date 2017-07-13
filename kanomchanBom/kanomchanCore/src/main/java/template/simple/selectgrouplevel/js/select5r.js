var option = {
	val : "",
	multiple : ""
};

$(".select5-select-container").on("click",function(e) {
	e.stopPropagation();
});
var openSelect5 = function(event,element) {
	$('.select5-select-container').not(element.parent().parent().parent().children('.select5-select-container')).hide();
	element.parent().parent().parent().children('.select5-select-container').toggle();
	event.stopPropagation();
};
$(document).on('click',function() {
	$('.select5-select-container').hide();
});

