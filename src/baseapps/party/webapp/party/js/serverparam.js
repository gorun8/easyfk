/**
 **/
$(document).ready(function(){
	
	$("#serverparamForm").validate({
		rules:{
			httpPort:{
				required: true,
				min:10,
				max:65537
			},
			httpsPort:{
				required:true,
				min:10,
				max:65537
			},
			storePort:{
				required:true,
				min:10,
				max:65537
			},

			number:{
				required:true,
				number:true
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
