/*
 * Project:Easy Web Framework
 * Description:
 * EasyFK stands for Easy Web Framework.It's an open source product for E-Business / E-Commerce.It
 * was launched by a chinese Hezhiping(QQ:110476592) in 2015.The goal of EasyFK is to  provide a
 * foundation and starting point for reliable, secure , simple-to-use ,cost-effective ,scalable
 * and suitable-for-Chinese E-Business / E-Commerce solutions. With EasyFK, you can get started
 * right away without the huge deployment and maintenance costs of E-Business / E-Commerce systems.
 * Of course, you can customize it or use it as a framework to implement your most challenging business needs.
 * EasyFk is licensed under the Apache License Version 2.0.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Author:hezhiping   Email:110476592@qq.com
 */

/**
 * show search dailog
 */
easyfk.searchParty= function(){
	easyfk.showDailog("partySearchFormDailog");
}

easyfk.searchPartyCls= function(){
	easyfk.showDailog("partyClsSearchFormDailog");
}

/**
 * create a new node
 */
easyfk.newOrgNode = function () {
	var currnetId = $("#currentSelectedGroupId").val();
	$("#partyClsGroupFormLabel").html("新建组织机构");
	var param = {parentGroupId:currnetId,description:'',partyClsGroupFormAction:'/party/partyclsgroup/create'};
	easyfk.showDailog("partyClsGroupFormDailog",param);
}

/**
 * submit data to server
 */
easyfk.submitOrgNode = function(form){
	if(easyfk.isFormSubmited("partyClsGroupForm")){
		return ;
	}
	var ajaxUrl = $("#partyClsGroupFormAction").val();
	var postparams =$("#partyClsGroupForm").serialize();
	var currentParentId = $("#parentGroupId").val();
	easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
		easyfk.resetFormSubmitStatus("partyClsGroupForm");
		if(type){
			easyfk.storeMenuBar();
			easyfk.getChildNode(currentParentId,'node_'+currentParentId,1);
			$("#partyClsGroupFormDailog").modal("hide");
		}else{
			alert(msg);
		}
	});
}

/**
 * search node
 * @param form
 */
easyfk.submitSearchNode = function(form){
	if(easyfk.isFormSubmited("partyClsSearchForm")){
		return ;
	}
	var ajaxUrl ="/party/partyclsgroup/searchnode";
	var postparams =$("#partyClsSearchForm").serialize();
	var partyClsName = $("#partyClsName").val();

	easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
		easyfk.resetFormSubmitStatus("partyClsSearchForm");
		if(type){
			easyfk.storeMenuBar();
			var  found = false;
			if(obj.hasOwnProperty("partyClsGroupList")){
				if(obj.partyClsGroupList.length>0){
					var curRootId = obj.partyClsGroupList[0].partyClassificationGroupId;
					$("#parentGroupId").val(curRootId);
					easyfk.renderTree(obj.partyClsGroupList,"node_N_A",0);
					easyfk.getChildNode(curRootId,'node_'+curRootId,1);
					found = true;
				}
			}

			if(!found){
				$("#node_N_A").html("");
				$("#searchKeyWork").html("<span>没有找到<b>"+partyClsName+"</b><a class=\"btn btn-mini\" href=\"/party/partyclsgroup/list\"><i class=\"icon-remove\"></i></a></span>");
			}else{
				$("#searchKeyWork").html("<span>关键字：<b>"+partyClsName+"</b><a class=\"btn btn-mini\" href=\"/party/partyclsgroup/list\"><i class=\"icon-remove\"></i></a></span>");
			}
			$("#partyClsSearchFormDailog").modal("hide");
		}else{
			alert(msg);
		}
	});
}

/**
 * edit a node
 */
easyfk.editNode = function(){
	var currnetId = $("#currentSelectedGroupId").val();
	var jsonstr = $("#node_"+currnetId).attr("data");
	var obj = jQuery.parseJSON(jsonstr);

	$("#partyClsGroupFormLabel").html("编辑组织机构");
	var param = {partyClassificationGroupId:obj.partyClassificationGroupId,
				 parentGroupId:obj.parentGroupId,
		         description:obj.description,
		         partyClsGroupFormAction:'/party/partyclsgroup/update'};
	easyfk.showDailog("partyClsGroupFormDailog",param);
}

/**
 * remove a node
 */
easyfk.removeNode = function(){
	var nodeId = $("#currentSelectedGroupId").val();
	var ajaxUrl ="/party/partyclsgroup/remove";
	var postparams ={"partyClsGroupId":nodeId};
	easyfk.confirm("确定要删除这个组织机构吗？",function(rel){
		if(rel){
			easyfk.ajaxpost(ajaxUrl,postparams,function(msg,relsult,obj){
				if(relsult)
				{
					easyfk.storeMenuBar();
					$("#node_"+nodeId).remove();
				}else{
					alert(msg);
				}
			});
		}//endif
	});
}

/**
 * show sub tree
 * @param id
 */
easyfk.showSubTree =function(id){
	var theObj = $("#node_"+id);
	$("#currentSelectedGroupId").val(id);

	if ( theObj.hasClass("open")){
		theObj.children(".subpanel").hide();
		theObj.removeClass("open");
		$("#icon"+id).removeClass("icon-folder-open");
		$("#icon"+id).addClass("icon-folder-close");
	}else{
		theObj.children(".subpanel").show();
		theObj.addClass("open");
		$("#icon"+id).removeClass("icon-folder-close");
		$("#icon"+id).addClass("icon-folder-open");
	}//endif

	$(".nodeselected").removeClass("nodeselected");
	theObj.addClass("nodeselected");
	$("#partyClsGroupMenuBar").insertBefore($("#itemmenupanel"+id));
	$("#partyClsGroupMenuBar").show();
}//endif

/**
 * retrive children data from remote server
 * @param parentId
 * @param thisItemId
 * @param childCount
 */
easyfk.getChildNode = function(parentId ,thisItemId,childCount){
	$("#currentSelectedGroupId").val(parentId);
	var ajaxUrl ="/party/partyclsgroup/listchild";
	var postparams ={"parentId":parentId};
	easyfk.ajaxgetstring(ajaxUrl,postparams,function(jsonstr){
		var jsonobj = jQuery.parseJSON(jsonstr);
		if(!jsonobj.hasOwnProperty("partyClsGroupList"))
		{
			return ;
		}
		var data = jsonobj.partyClsGroupList;
		easyfk.renderTree(data,thisItemId,childCount);

	});
	easyfk.listParty();
}

/**
 * render the tree
 * @param data
 * @param thisItemId
 * @param childCount
 */
easyfk.renderTree = function(data,thisItemId,childCount){
	if(data.length <=0)
	{
		return ;
	}

	$("#"+thisItemId).children(".subpanel").remove();
	var navids = $("#"+thisItemId).attr("navids");

	var html ="<ul class=\"subpanel\" style=\"list-style: none;\">";
	$.each( data, function(i, item){
		html +="<li  id=\"node_"+ item.partyClassificationGroupId +"\" ";
		if(i == 0){html +="  topmost='true'";}

		html +="  data='"+JSON.stringify(item)+"'";
		var ids = "";
		if(navids != undefined)
		{
			ids = navids;
		}

		ids = ids + "," + item.partyClassificationGroupId;

		html +=" navids='" + ids + "'";
		html+=" class=\"subtree\" >";
		html+="<div  class=\"itemmenubar\" >";
		html+="<i  id=\"itemmenupanel"+ item.partyClassificationGroupId +"\" ></i>";
		html+="</div>";
		html+="<a    href=\"javascript:void(0)\" onclick=\"easyfk.showSubTree('"+item.partyClassificationGroupId +"')\">";
		html+="<i class=\"icon icon-folder-close\" id=\"icon"+ item.partyClassificationGroupId +"\" ></i>";
		html+="<span  ondblclick=easyfk.getChildNode('"+item.partyClassificationGroupId+"','node_"+item.partyClassificationGroupId+"',"+item.childCount+")";
		html+=">"+item.description +"</span>";

		if(item.childCount > 0) {
			html += "<span class=\"label\" style='margin-left:50px;'>" + item.childCount + "</span>";
		}

		html+="</a></li>";
	});
	html+="</ul>"
	$("#"+thisItemId).append(html);
}


easyfk.storeMenuBar = function(){
	$("#partyClsGroupMenuBar").hide();
	$("#partyClsGroupMenuBar").insertBefore($("#node_N_A"));
}

/**
 * show partylist
 */
easyfk.partylist = function(){
	var nodeId = $("#currentSelectedGroupId").val();
	var navids = $("#node_"+nodeId).attr("navids");
	document.location.href= "/party/party/list?navids="+ navids+"&clsId="+nodeId;
}

/**
 * list party below current node
 */
easyfk.listParty = function(){
	var nodeId = $("#currentSelectedGroupId").val();
	var navids = $("#node_"+nodeId).attr("navids");
	var url ="/party/party/listpartydata?navids="+ navids+"&clsId="+nodeId;
	$("#listdatacontainer").load(url);
}

/**
 * create person
 */
easyfk.newPerson = function () {
	var nodeId = $("#currentSelectedGroupId").val();
	 if(nodeId == ""){
		 alert("请选择组织机构");
		 return ;
	 }
	//$("#personClassificationGroupId").val(nodeId);
	var param = {personClassificationGroupId : nodeId,partyId:'',firstName:'',description:'',externalId:'',partyPersonFormAction:'/party/party/createperson'};
	easyfk.showDailog("partyPersonFormPanel",param);
}

/**
 * submit person
 */
easyfk.submitPerson = function(form){
	if(easyfk.isFormSubmited("partyPersonForm")){
		return ;
	}
	var ajaxUrl = $("#partyPersonFormAction").val();
	var postparams =$("#partyPersonForm").serialize();
	easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
		easyfk.resetFormSubmitStatus("partyPersonForm");
		if(type){
			$("#partyPersonFormPanel").modal("hide");
			alert("新建个人成功");
			easyfk.listParty();
		}else{
			alert(msg);
		}
	});
}

/**
 * create new group
 */
easyfk.newGroup = function () {
	var param = {groupName:'',comments:'',partyGroupFormAction:'/party/party/creategroup'};
	easyfk.showDailog("partyGroupFormPanel",param);
}

/**
 * submit party group
 * @param form
 * @returns {boolean}
 */
easyfk.submitPartyGroup = function(form){
	if(easyfk.isFormSubmited("partyGroupForm")){
		return ;
	}
	var ajaxUrl = $("#partyGroupFormAction").val();
	var postparams =$("#partyGroupForm").serialize();
	easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
		easyfk.resetFormSubmitStatus("partyGroupForm");
		if(type){
			alert("新建组织会员成功");
			$("#partyGroupFormPanel").modal("hide");
		}else{
			alert(msg);
		}
	});
	return false;
}

easyfk.partyDetial = function(partyId){
	document.location.href = "/party/party/partydetial?id="+partyId;
}

/**
 * remove is just set it to disable.
 * @param partyId
 */
easyfk.disableParty = function(partyId){

	easyfk.confirm("确定要删除这个会员吗？",function(rel){
	if(rel){
			var ajaxUrl = "/party/party/disableParty";
			var postparams ={partyId:partyId};
			easyfk.ajaxpost(ajaxUrl,postparams,function(msg,relsult,obj){
				if(relsult)
				{
					alert("删除会员成功");
					easyfk.listParty();
				}else{
					alert(msg);
				}
			});
		}//endif
	});
}

$(document).ready(function(){
	$('input[type=checkbox],input[type=radio],input[type=file]').uniform();

});
