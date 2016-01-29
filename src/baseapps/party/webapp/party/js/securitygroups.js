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

easyfk.newSecurityGroup = function () {
	$("#securityGroupFormLabel").html("新建角色");
	var param = {groupId:'',name:'',description:'',securityGroupFormAction:'/party/dyn/security/createGroup'};
	easyfk.showDailog("securityGroupFormPanel",param);
}

easyfk.editSecurityGroup = function (groupId) {
	$("#securityGroupFormLabel").html("修改角色");
	var name= $("#securityGroup_"+groupId+"_name").val();
	var description= $("#securityGroup_"+groupId+"_description").val();
	var param = {groupId:groupId,name:name,description:description,securityGroupFormAction:'/party/dyn/security/updateGroup'};
	easyfk.showDailog("securityGroupFormPanel",param);
}

easyfk.submitSecurityGorup = function(form){
	if(easyfk.isFormSubmited("securityGroupForm")){
		return ;
	}
	var ajaxUrl = $("#securityGroupFormAction").val();
	var postparams =$("#securityGroupForm").serialize();
	easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
		easyfk.resetFormSubmitStatus("securityGroupForm");
		if(type){
			$("#securityGroupFormPanel").modal("hide");
			document.location.href ="/party/dyn/security/securitygroups";
		}else{
			alert(msg);
		}
	});
}

easyfk.removeSecurityGroup = function(groupId){
	var name= $("#securityGroup_"+groupId+"_name").val();
	easyfk.confirm("确定要删除"+name +"吗?",function(r){
		if(r){
			var ajaxUrl="/party/dyn/security/removeGroup";
			var params ={groupId:groupId};
			easyfk.ajaxget(ajaxUrl,params,function(msg,type,obj){
				if(type){
					document.location.href ="/party/dyn/security/securitygroups";
				}else{
					alert(msg);
				}
			});
		}
	});
}

easyfk.submitGroupPermission = function(treeObj){
	var zTree = $.fn.zTree.getZTreeObj(treeObj);
	var checkItems = zTree.getCheckedNodes(true);
	if(checkItems.length  <= 0){
		alert("至少必须选择一项权限");
		return ;
	}

    var selectedIds = [];
	$.each( checkItems, function(i, item){
		selectedIds[i] = item.id;
	});

	if(easyfk.isFormSubmited("groupPermissionForm")){
		return ;
	}
	var groupId = $("#groupPermissionForm").find("#groupId").val();
	var ajaxUrl = $("#groupPermissionAction").val();
	var postparams = {"selectedPermissions":selectedIds,"groupId":groupId};

	easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
		easyfk.resetFormSubmitStatus("groupPermissionForm");
		if(type){
			$("#groupPermissionPanel").modal("hide");
		}else{
			alert(msg);
		}
	});
}

easyfk.renderGroupPermissionTree = function(containerId,groupId,callback){
	var ajaxUrl = "/party/dyn/security/getPermissionTree";
	var postparams ={groupId:groupId};
	easyfk.ajaxget(ajaxUrl,postparams,function(msg,type,obj){
		if(!type){
			alert(msg);
			return ;
		}
		if(obj.hasOwnProperty("permissionJsonList")){
			var zNodes2 = obj.permissionJsonList;
			var setting = {check: {enable: true},data: {simpleData: {enable: true}}};
			$.fn.zTree.init($("#"+containerId), setting, zNodes2);
			callback();
		}else{
			alert("获取权限资源出错");
		};
	});

}