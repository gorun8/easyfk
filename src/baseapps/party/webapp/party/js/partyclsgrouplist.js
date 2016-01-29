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



easyfk.beforeRemove = function(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("partyClsGroupTree");
	zTree.selectNode(treeNode);
	var currnetId = treeNode.id;
	var ajaxUrl ="/party/dyn/partyclsgroup/remove";
	var postparams ={"partyClsGroupId":currnetId};

	easyfk.confirm("确定要删除"+ treeNode.name +"吗？",function(rel){
		if(rel){
			easyfk.ajaxpost(ajaxUrl,postparams,function(msg,relsult,obj){
				if(relsult)
				{
					easyfk.loadPartyClsGroupTree();
				}else{
					alert(msg);
				}
			});
		}//endif
	});

	return false;
}


easyfk.showRemoveBtn = function(treeId, treeNode) {
	return  treeNode.pId != null;
}

easyfk.showRenameBtn =function(treeId, treeNode) {
	return treeNode.pId != null;
}


easyfk.addHoverDom= function(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId+ "' title='添加下级' onfocus='this.blur();'></span>";
	//var addStr = "<button class='btn ' title='添加下级'  id='addBtn_" + treeNode.tId	+ "' ><i class='fa fa-plus icon-white'></i>添加</button>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){
		var currnetId = treeNode.id;
		$("#partyClsGroupFormLabel").html("新建组织机构");
		var param = {parentGroupId:currnetId,description:'',partyClsGroupFormAction:'/party/dyn/partyclsgroup/create'};
		easyfk.showDailog("partyClsGroupFormDailog",param);
		return false;
	});
};

easyfk.removeHoverDom=function(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
};


easyfk.showEditDialog = function(treeId, treeNode, newName, isCancel) {
	var zTree = $.fn.zTree.getZTreeObj("partyClsGroupTree");
	zTree.selectNode(treeNode);

	$("#partyClsGroupFormLabel").html("编辑组织机构");
	var param = {partyClassificationGroupId:treeNode.id,
		parentGroupId:treeNode.pId,
		description:treeNode.name,
		partyClsGroupFormAction:'/party/dyn/partyclsgroup/update'};
	easyfk.showDailog("partyClsGroupFormDailog",param);

	return false;
}

easyfk.loadPartyClsGroupTree= function(){
	$("#partylistpanel").hide();
	$("#partyclstreecontainer").load("/party/dyn/partyclsgroup/listtree");

}

easyfk.renderTree = function(conainerId,nodes){
	var setting = {
		view: {
			addHoverDom: easyfk.addHoverDom,
			removeHoverDom: easyfk.removeHoverDom,
			selectedMulti: false
		},
		edit: {
			enable: true,
			editNameSelectAll: true,
			showRemoveBtn: easyfk.showRemoveBtn,
			showRenameBtn: easyfk.showRenameBtn
		},
		data: {
			keep: {
				parent:true,
				leaf:true
			},
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeEditName: easyfk.showEditDialog,
			beforeRemove: easyfk.beforeRemove,
			onClick: easyfk.nodeClick,
		 	beforeDrop: easyfk.beforeDrop
		}
	};

	$.fn.zTree.init($("#"+conainerId), setting, nodes);
}



easyfk.beforeDrop = function(treeId, treeNodes, targetNode, moveType, isCopy){
	if((targetNode== null) ||(treeNodes.length <=0)){
		return;
	}
	var srcNode = treeNodes[0];
	var thename = treeNodes[0].name;
	var targetname =  targetNode.name;

	easyfk.confirm("确定要将["+thename+"]及其下级移动到["+ targetname +"]下吗？",function(rel){
		if(rel){
			var ajaxUrl ="/party/dyn/partyclsgroup/movenode";
			var postparams ={partyClassificationGroupId:srcNode.id,parentGroupId:targetNode.id};
			 easyfk.ajaxpost(ajaxUrl,postparams,function(msg,relsult,obj){
				if(relsult)
				{
					easyfk.loadPartyClsGroupTree();
				}else{
					alert(msg);
				}
			});

		}//endif
	});

	return false;
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

	easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
		easyfk.resetFormSubmitStatus("partyClsGroupForm");
		if(type){
			$("#partyClsGroupFormDailog").modal("hide");
			easyfk.loadPartyClsGroupTree();
		}else{
			alert(msg);
		}
	});
}

/**
 * search node
 * @param form
 */
easyfk.submitSearchNode = function(){
	var partyClsName = $("#partyClsSearchForm").find("#partyClsName").val();
	$("#partyclstreecontainer").load("/party/dyn/partyclsgroup/searchnode?partyClsName="+partyClsName);
}




easyfk.nodeClick= function(event, treeId, treeNode, clickFlag) {
	var id = treeNode.id;
	$("#currentSelectedGroupId").val(id);
	$("#partylistpanel").show();
	easyfk.listParty(id);
}
/**
 * show partylist
 */
easyfk.partylist = function(){
	var nodeId = $("#currentSelectedGroupId").val();
	var navids = $("#node_"+nodeId).attr("navids");
	document.location.href= "/party/dyn/party/list?navids="+ navids+"&clsId="+nodeId;
}

/**
 * list party below current node
 */
easyfk.listParty = function(){
	var nodeId = $("#currentSelectedGroupId").val();
	var url ="/party/dyn/party/listpartydata?clsId="+nodeId;
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

	var param = {personClassificationGroupId : nodeId,partyId:'',firstName:'',description:'',externalId:'',partyPersonFormAction:'/party/dyn/party/createperson'};
	easyfk.showDailog("partyPersonFormPanel",param);
}

easyfk.importPerson = function () {
	document.location.href="/party/dyn/party/beforeimport";
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
	alert(ajaxUrl);
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
	var param = {groupName:'',comments:'',partyGroupFormAction:'/party/dyn/party/creategroup'};
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
	document.location.href = "/party/dyn/party/partydetial?partyId="+partyId;
}

/**
 * remove is just set it to disable.
 * @param partyId
 */
easyfk.disableParty = function(partyId){

	easyfk.confirm("确定要删除这个会员吗？",function(rel){
	if(rel){
			var ajaxUrl = "/party/dyn/party/disableParty";
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

});
