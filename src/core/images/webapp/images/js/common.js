
var easyfk = {};
//ajax 
easyfk.ajaxpost = function(ajaxUrl,postparams,callback)
{
    jQuery.ajax({
        url: ajaxUrl,
        type: 'POST',
        data: postparams,
        error: function(msg) {
            alert("通信出错，可能网络不通");
            easyfk.hideProgress();
        },
        success: function(jsonstr) {
        	easyfk.hideProgress();
			var obj = jQuery.parseJSON(jsonstr);

        	var msg = "";
        	var type = false;
        	if(obj.hasOwnProperty("errorMessageList"))
        	{
        	     msg = obj.errorMessageList;
        	     type = false;
       		}else{
				if(obj.hasOwnProperty("eventMessageList"))
            	{
        			msg = obj.eventMessageList;
            	}
        		type = true;
        	}

        	if(callback != null && callback != undefined)
    		{
    			callback(msg,type,obj);
    		}
        }
    });
}


easyfk.ajaxgetstring = function(ajaxUrl,postparams,callback)
{
    jQuery.ajax({
        url: ajaxUrl,
        type: 'POST',
        data: postparams,
        error: function(msg) {
            alert("通信出错，可能网络不通");
            easyfk.hideProgress();
        },
        success: function(xml) {
        	callback(xml);
        }
    });
}

/**
 * 选择checkbox 可以全选，反选。jqType可以根据css,id等灵活设置*/
easyfk.selectAllCheckbox = function(jqType)
{
	var checkboxs = $(jqType);
	if(checkboxs== null || checkboxs == undefined)
	{
		return ;
	}
	
	$.each(checkboxs,function(index,item){
		var ck =$(this).is(':checked');
		$(this).attr('checked',!ck);
	});
}
/**/
easyfk.getSelectedCheckbox = function(jqType)
{
	 
	var selectedList =[];
	var checkboxs = $(jqType);
	if(checkboxs== null || checkboxs == undefined)
	{
		return selectedList;
	}
	$.each(checkboxs,function(index,item){
		var ck =$(this).is(':checked');
		if(ck)
		{
			selectedList.push($(this));
		}
	});
	
	return selectedList;
}

//message
easyfk.confirm= function(msg,callback){
	 
	$.messager.confirm('请确认操作', msg, function(r){
		if (r){
			callback();
		}
	});
	return false;
}


//
easyfk._show_message_func=function(title,message,icon,css){
	 
	var html = "<div id=\""+css+"\" class=\"message "+css+"\">";
	html += "<div class=\"image\">";
	html += "<img src=\""+icon+"\" alt=\"Error\" height=\"32\">";
	html += "</div>";
	html += "<div class=\"dismiss\" >";
	html += "<a href=\"#"+css+"\" onclick='easyfk.closeMessageBox(\""+css+"\")'></a>";
	html += "</div>";
	html += "<div class=\"text\">";
	html += "<h6>"+title+"</h6>";
	html += "<span>";
	html += message;
	html += "</span></div></div>";
	$("#messagebox").hide();
	$("#messagebox").append(html);
	
	var left_w=($(window).width()-$('#messagebox').width())/2;
	var scrollLeft = $(document).scrollLeft();
	left_w += scrollLeft; 
	var top_w=($(window).height()-$('#messagebox').height())/2;
	var scrollTop = $(document).scrollTop();
	top_w +=scrollTop;
	
	$("#messagebox").css("left",left_w);
    $("#testDiv").css("top",top_w);
	
    $("#messagebox").fadeIn(500);
	setTimeout(function(){
		easyfk.closeMessageBox("messagebox");
		},10000);
}

easyfk.closeMessageBox = function(objId)
{
	$("#"+objId).fadeOut(1000);
	$("#messagebox").html("");
}

easyfk.showInfoBox = function(message){
	 
	var title ="信息";
	var icon ="/images/icons/messages/success.png";
	var css ="message-success";
	easyfk._show_message_func(title,message,icon,css);
}

easyfk.showErrorBox = function(message){
	  
	var title ="错误";
	var icon ="/images/icons/messages/error.png";
	var css ="message-error";
	easyfk._show_message_func(title,message,icon,css);
}

easyfk.showNoticeBox = function(message){
	  
	var title ="提示";
	var icon ="/images/icons/messages/notice.png";
	var css ="message-notice";
	easyfk._show_message_func(title,message,icon,css);
}

easyfk.showWarningBox = function(message){
	  
	var title ="注意";
	var icon ="/images/icons/messages/warning.png";
	var css ="message-warning";
	easyfk._show_message_func(title,message,icon,css);
}

function confirmLink(msg,linkObj){
	var url = linkObj.attr("href");
	if(!url||url =="#")
	{
		return true;
	}
	
	$.messager.confirm('请确认操作', msg, function(r){
		if (r){
			window.location.href= url;
		}
	});
	
	return false;
}

function confirmForm(msg,formObj){
	$.messager.confirm('请确认操作', msg, function(r){
		if (r){
			formObj.submit();
		}
	});
	
	return false;
}

//进度条
easyfk.showProgress = function(message,title,closetime)
{
	var win = $.messager.progress({
		title:title,
		msg:message
	});
	
	if(closetime >0)
	{
		//setTimeout(function(){
		//$.messager.progress('close');
		//},5000);
	}
}

easyfk.hideProgress= function()
{
	//$.messager.progress('close');
}

easyfk.showSubmitProgress = function()
{
	easyfk.showProgress("正在提交数据...","处理进度",0);
}


//弹出框
window.realAlert = window.alert;
window.alert = function(mess)
{
	//$.messager.alert('easyfk',mess);
	easyfk.showWarningBox(mess);
}

 

