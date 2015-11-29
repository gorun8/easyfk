/**
 **/
$(document).ready(function(){
	$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
	// === jQeury Gritter, a growl-like notifications === //
	$.gritter.add({
		title:	'收到新消息',
		text:	'您有4条新消息.',
		image: 	'/unicorn/style/images/icons/32/mail.png',
		sticky: false
	});
});
