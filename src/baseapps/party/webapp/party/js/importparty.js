/**
 **/

easyfk.getstatus= function(){
	var url ="/party/dyn/party/importStatus?categoryKey=${categoryKey}";
	var stop = false;
	$.getJSON(url,function(data){
		var jsonobj = jQuery.parseJSON(data);
		if(jsonobj.hasOwnProperty("messageList"))
		{
			var targetPanel =$("#statusPanel");
			$.each(jsonobj.messageList, function(i,item){
				var msg = item.message;

				if(msg =="EASYFK_NO_MORE_DATA"){
					msg="处理结束";
					stop = true;
					$("#import_progress_bar").attr("style","width:100%");
				}else{
					$("#import_progress_bar").attr("style","width:"+item.percent+"%");
				}

				var obj="";
				if(item.type == "success"){
					obj =$("<p><span class=\"label label-success\">"+msg+"</span></p>");
				}else{
					obj =$("<p><span class=\"label label-warning\">"+msg+"</span></p>");
				}
				//$("#statusPanel").append(obj);
				obj.insertAfter(targetPanel);


			});

			if(!stop){
				setTimeout(easyfk.getstatus,1000);
			}else{
				$("#form_actions_panel").show();
			}
		}
	});
}

easyfk.gotoClsParty=function(){
	document.location.href ="/party/dyn/partyclsgroup/list";
}

easyfk.exportTemplate=function(){
	document.location.href ="/party/template/party_user.xls";
}

$(document).ready(function(){

});
