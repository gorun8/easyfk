
//切换验证码
easyfk.changeCaptcha = function(urlVal,imgObj)
{
	var radom = Math.random();
	if( urlVal.indexOf("?") == -1 )
	{
		urlVal = urlVal+'?unique='+radom;
	}
	else
	{
		urlVal = urlVal + '&amp;unique='+radom;
	}
	$('#'+imgObj).attr('src',urlVal);
}

easyfk.checkCaptcha = function(ajaxUrl,captchaField,msgField) 
{
	  
	var captchaValue = $("#"+captchaField).val();
	if(captchaValue == "" || captchaValue == undefined)
	{
		//$("#"+msgField).html
		alert("请填图片中的字符");
		return ;
	}
	 
	if(captchaValue.length != 4)
	{
    	//$("#"+msgField).html
		alert("验证码长度不对");
    	return ;
    }
	var postparams = {"code":captchaValue};
	easyfk.ajaxgetstring(ajaxUrl,postparams,function(rel){
		if("true" !=rel)
		{
			//$("#"+msgField).html
			alert("验证码不正确");
			return ;
		}
		//$("#"+msgField).html("");
	});
}
$(function(){
	
	$("#loginform").submit(function(){
		var ajx =$("#AJAX").val();
		if("Y" != ajx)
		{
			return true;
		}
		loginAjax();
		return false;
	});
	
	//提交用户名和密码登录
	function loginAjax(){
		var postparams = $("#loginform").serialize();
		var ajaxUrl = "/security/login";
		easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,jsonObj){
			alert(jsonObj);
		});
	}
});


