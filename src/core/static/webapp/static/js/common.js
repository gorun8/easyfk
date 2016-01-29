
var easyfk = {};
/*
*  POST 方式的AJAX请求，
*  @param ajaxUrl
*        请求URL
*  @param params
*        JSON格式的请求参数
*  @param callback
* 		 响应的回调接口，接口定义为：callback(msg,responseType,obj)
* 		 msg :返回的消息提示
* 		 responseType:成功 true,失败 false
* 		 obj : json格式的响应数据
* */
easyfk.ajaxpost = function(ajaxUrl,params,callback){
	easyfk.ajax('POST',ajaxUrl,params,callback);
}
/*
 *  GET 方式的AJAX请求，
 *  @param ajaxUrl
 *        请求URL
 *  @param params
 *        JSON格式的请求参数
 *  @param callback
 * 		 响应的回调接口，接口定义为：callback(msg,responseType,obj)
 * 		 msg :返回的消息提示
 * 		 responseType:成功 true,失败 false
 * 		 obj : json格式的响应数据
 * */
easyfk.ajaxget = function(ajaxUrl,params,callback){
	easyfk.ajax('GET',ajaxUrl,params,callback);
}

/*
 *  AJAX请求，
 *  @param method
 *        请求方式 ，post ,get ,head等
 *  @param ajaxUrl
 *        请求URL
 *  @param params
 *        JSON格式的请求参数
 *  @param callback
 * 		 响应的回调接口，接口定义为：callback(msg,responseType,obj)
 * 		 msg :返回的消息提示
 * 		 responseType:成功 true,失败 false
 * 		 obj : json格式的响应数据
 * */
easyfk.ajax= function(method,ajaxUrl,params,callback)
{
	jQuery.ajax({
		url: ajaxUrl,
		type: method,
		data: params,
		error: function(msg) {
			callback("通信出错，可能网络不稳定",false,null);
		},
		success: function(jsonstr) {

			alert(jsonstr);
			var obj = jQuery.parseJSON(jsonstr);
			var msg = "";
			var responseType = false;

			if(obj.responseType == "success"){
				responseType = true;
				if(obj.hasOwnProperty("_EVENT_MESSAGE_")){
					msg = obj._EVENT_MESSAGE_;
				}else if(obj.hasOwnProperty("eventMessage")){
					msg = obj.eventMessage;
				}

				if(obj.hasOwnProperty("eventMessageList")){
					$.each( obj.eventMessageList, function(i, n){
						msg +=","+n;
					});
				}else if(obj.hasOwnProperty("_EVENT_MESSAGE_LIST_")){
					$.each( obj._EVENT_MESSAGE_LIST_, function(i, n){
						msg +=","+n;
					});
				}
			}else{
				responseType = false;
				if(obj.hasOwnProperty("_ERROR_MESSAGE_")){
					msg = obj._ERROR_MESSAGE_;
				}else if(obj.hasOwnProperty("errorMessage")){
					msg = obj.errorMessage;
				}

				if(obj.hasOwnProperty("errorMessageList")){
					$.each( obj.errorMessageList, function(i, n){
						msg +=","+n;
					});
				}else if(obj.hasOwnProperty("_ERROR_MESSAGE_LIST_")){
					$.each( obj._ERROR_MESSAGE_LIST_, function(i, n){
						msg +=","+n;
					});
				}
			}
			if(callback != null && callback != undefined)
			{
				callback(msg,responseType,obj);
			}
		}
	});
}

/*
 *  GET 方式的AJAX请求，以获取数据
 *  @param ajaxUrl
 *        请求URL
 *  @param params
 *        JSON格式的请求参数
 *  @param callback
 * 		 响应的回调接口，接口定义为：callback(responseType,obj)
 * 		 responseType:成功 true,失败 false
 * 		 obj :  当responseType==true时，json格式的响应数据
 * 		        当responseType == false时，提示消息
 * */
easyfk.ajaxgetstring = function(ajaxUrl,params,callback)
{
	jQuery.ajax({
		url: ajaxUrl,
		type: 'GET',
		data: params,
		error: function(msg) {
			callback(false,"通信出错，可能网络不通");
		},
		success: function(data) {
			callback(true,data);
		}
	});
}

/**
 * 加载portlet页面
 * @param containerId
 * @param portletUrl
 */
easyfk.loadPortlet = function(containerId,portletUrl ){
	$("#"+containerId).load(portletUrl);
}
/**
 * 加载portlet页面
 * @param containerId
 * @param portletUrl
 */
easyfk.refreshSelPortlet = function(containerId,portletUrl ){
	$("#"+containerId).parent(".portalpartcontainer").load(portletUrl);
}

/**
 * renderPortal 页面
 * @param containerId
 * @param portletUrl
 */
easyfk.renderPortalPage = function(ctx,containerId,portalPageId,portaletParams){
	var ajaxUrl=ctx+"/dyn/portlet/getPagePortal";
	var postparams ={portalPageId:portalPageId};

	easyfk.ajaxgetstring(ajaxUrl,postparams,function(rel,msg){
		if(!rel){
			return ;
		}

		var jsonobj = jQuery.parseJSON(msg);
		if(!jsonobj.hasOwnProperty("pagePortalList")){
			return ;
		}
		if(!jsonobj.hasOwnProperty("pageColumnList")){
			return ;
		}

		var portalList = jsonobj.pagePortalList;
		var columnList = jsonobj.pageColumnList;

		var html="";
		$.each( columnList, function(i, colum){
			html+="<div class=\"col-xs-12 col-sm-"+ colum.columnWidthPercentage+"\">";
			$.each( portalList, function(j, portal){
				if(portal.columnSeqId == colum.columnSeqId){
					html+="<div class='portalpartcontainer' id=" + colum.portalPageId+"_" + colum.columnSeqId +j+ "></div>";
				}
			});
			html+="</div>";
		});
		html+="";
		$("#"+containerId).append($(html));
		//call load
		$.each( columnList, function(i, colum){
			$.each( portalList, function(j, portal){
				if(portal.columnSeqId == colum.columnSeqId){
					easyfk.loadPortlet(colum.portalPageId+"_"+colum.columnSeqId+j, portal.screenLocation+"?"+portaletParams);
				}
			});
		});
	});
}

easyfk.changePage = function(url){
	document.location.href = url;
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



easyfk.showInfoBox = function(message){

	//var title ="信息";
	//var icon ="/static/icons/messages/success.png";
	//var css ="message-success";
	//easyfk._show_message_func(title,message,icon,css);
}

easyfk.showErrorBox = function(message){

	//var title ="错误";
	//var icon ="/static/icons/messages/error.png";
	//var css ="message-error";
	//easyfk._show_message_func(title,message,icon,css);
}

easyfk.showNoticeBox = function(message){

	//var title ="提示";
	//var icon ="/static/icons/messages/notice.png";
	//var css ="message-notice";
	//easyfk._show_message_func(title,message,icon,css);
}

easyfk.showWarningBox = function(message){

	//var title ="注意";
	//var icon ="/static/icons/messages/warning.png";
	//var css ="message-warning";
	//easyfk._show_message_func(title,message,icon,css);
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
/*window.realAlert = window.alert;
window.alert = function(mess)
{
	bootbox.alert(mess);
}
*/
easyfk.confirm=function(msg ,callback){
	bootbox.confirm(msg, function(result) {
		callback(result);
	});
}


/**
 * 显示对模态对话框
 * @param containerId
 */
easyfk.showDailog = function(containerId,jsondata){
	var container = $("#"+containerId);
	if(jsondata != null && jsondata != undefined){
		$.each( jsondata, function(key, val){
			//$("#"+key).val(val);
			container.find("#"+key).val(val);
		});
	}//endif
	container.modal({backdrop:'static',keyboard:false,show:true});
}