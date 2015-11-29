/**
 **/
$(document).ready(function(){
	$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
	// Form Validation
	$("#partyClsGroupForm").validate({
		rules:{
			description:{
				required:true
			}
		},
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}
	});

});
