<#--
  Project:Easy Web Framework
  Description:
  EasyFK stands for Easy Web Framework.It's an open source product for E-Business / E-Commerce.It
  was launched by a chinese Hezhiping(QQ:110476592) in 2015.The goal of EasyFK is to  provide a
  foundation and starting point for reliable, secure , simple-to-use ,cost-effective ,scalable
  and suitable-for-Chinese E-Business / E-Commerce solutions. With EasyFK, you can get started
  right away without the huge deployment and maintenance costs of E-Business / E-Commerce systems.
  Of course, you can customize it or use it as a framework to implement your most challenging business needs.
  EasyFk is licensed under the Apache License Version 2.0.  You may obtain a copy of the License at
  http://www.apache.org/licenses/LICENSE-2.0
  Author:hezhiping   Email:110476592@qq.com
-->
<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menupartyClsGroupId']}" location="component://party/webapp/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>
<#--
<@easyfkSetContextMenu>
{MENU_DATA:[
{id:'contextNewMenuId',title:'新建',desc:'新建组织机构',toggle:'modal',href:'#newPartyClsGroupFormPanel',style:'btn btn-primary tip-bottom',style2:' icon-plus-sign icon-white'},
]}
</@easyfkSetContextMenu>
-->
<@easyfkSetNavBar subTitle="组织机构">
{NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
{id:'navid2',title:'组织机构'}]}
</@easyfkSetNavBar>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/resources/page/appcommon.ftl">
<div class="row-fluid">
    <div class="span5">
            <div  id="partyClsGroupMenuBar" style="display: none;">
                <@easyfkContextMenu menuStyle ="Dropdown">
                    {MENU_DATA:[
                    {id:'',title:'新建机构',desc:'新建当前选中的下级组织机构',toggle:'modal',href:'javascript:easyfk.neworgnode()',style:'btn btn-primary tip-bottom',style2:' icon-plus-sign icon-white'},
                    {id:'',title:'会员',desc:'属于当前选中机构的会员',toggle:'modal',href:'javascript:easyfk.partylist()',style:'btn btn-primary tip-bottom',style2:' icon-user icon-white'},
                    {id:'',title:'编辑',desc:'编辑当前选中的组织机构',toggle:'modal',href:'javascript:easyfk.editnode()',style:'btn btn-inverse tip-bottom',style2:' icon-pencil icon-white'},
                    {id:'',title:'删除',desc:'删除当前选中的组织机构',toggle:'modal',href:'javascript:easyfk.removenode()',style:'btn btn-danger tip-bottom',style2:' icon-remove icon-white'},
                    ]}
                </@easyfkContextMenu>
            </div>
        <div >
            <div id="partyClsGroupTree" style="overflow-y: auto;height: 550px;">

            </div>
        </div>
    </div>
    <div class="span7">
        <@easyfkContextMenu>
            {MENU_DATA:[
            {id:'contextNewMenuId',title:'个人会员',desc:'新建个人会员',toggle:'modal',href:'javascript:easyfk.newPerson()',style:'btn btn-primary tip-bottom',style2:' icon-plus-sign icon-white'},
            {id:'contextNewMenuId',title:'组织会员',desc:'新建组织会员',toggle:'modal',href:'javascript:easyfk.newGroup()',style:'btn btn-primary tip-bottom',style2:' icon-plus-sign icon-white'},

            ]}
        </@easyfkContextMenu>
        <div id="listdatacontainer"></div>

    </div>
</div>
</@easyfkDecoratorScreen>

<style type="text/css">
 #partyClsGroupTree   .nodeselected{
        background-color: #e5e5e5;
        box-shadow: 0 0 1px #ffffff;
        border: 1px solid #d6d6d6;
    }
 #partyClsGroupTree  .subtree{
        padding: 5px;padding-bottom:13px;
    }
 #partyClsGroupTree  .itemmenubar{
    float:right;
 }
 #partyClsGroupTree  .subtree a{padding-top:8px;}
</style>
<script type="text/javascript">

    easyfk.neworgnode = function () {
        var currnetId = $("#currentSelectedGroupId").val();
        $("#parentGroupId").val(currnetId);
        $("#description").val("");
        $("#formDailogLabel").html("新建组织机构");
        $("#partyClsGroupFormAction").val("${ctx}/partyclsgroup/create");
        $("#partyClsGroupFormDailog").modal("show");
    }

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
        }
        $(".nodeselected").removeClass("nodeselected");
        theObj.addClass("nodeselected");
         $("#partyClsGroupMenuBar").insertBefore($("#itemmenupanel"+id));
        $("#partyClsGroupMenuBar").show();

    }

     easyfk.getChildNode = function(parentId ,thisItemId,childCount){
        $("#currentSelectedGroupId").val(parentId);
        var ajaxUrl ="${ctx}/partyclsgroup/listchild";
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
    }

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


    easyfk.editnode = function(){
        var nodeId = $("#currentSelectedGroupId").val();
        var jsonstr = $("#node_"+nodeId).attr("data");
        $("#partyClsGroupFormAction").val("${ctx}/partyclsgroup/update");
        var obj = jQuery.parseJSON(jsonstr);
        $("#partyClassificationGroupId").val(obj.partyClassificationGroupId);
        $("#parentGroupId").val(obj.parentGroupId);
        $("#description").val(obj.description);
        $("#formDailogLabel").html("编辑组织机构");
        $("#partyClsGroupFormDailog").modal("show");
    }

    easyfk.storeMenuBar = function(){
        $("#partyClsGroupMenuBar").hide();
        $("#partyClsGroupMenuBar").insertBefore($("#partyClsGroupTree"));
    }

    easyfk.removenode = function(){
        var nodeId = $("#currentSelectedGroupId").val();
        var ajaxUrl ="${ctx}/partyclsgroup/remove";
        var postparams ={"partyClsGroupId":nodeId};
        easyfk.ajaxpost(ajaxUrl,postparams,function(msg,relsult,obj){
            if(relsult)
            {
                easyfk.storeMenuBar();
                $("#node_"+nodeId).remove();
            }else{
                alert(msg);
            }
        });
    }

    easyfk.partylist = function(){
        var nodeId = $("#currentSelectedGroupId").val();
        var navids = $("#node_"+nodeId).attr("navids");
        document.location.href= "${ctx}/party/list?navids="+ navids;
    }



    $(document).ready(function(){
        <#if partyClsGroupList?has_content>
            easyfk.renderTree(${partyClsGroupList.partyClsGroupRoot},"partyClsGroupTree",0);
            easyfk.getChildNode('${partyClsGroupRootId}','node_${partyClsGroupRootId}',1);
        </#if>



        $("#partyClsGroupForm").validate({
            rules:{
                description:{
                    required:true,
                    minlength:2
                },
                url:{
                    required:true,
                    url: true
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


        $("#partyClsGroupFormSubmit").click(function(){
            $("#partyClsGroupForm").submit();
        });

        $("#partyClsGroupForm").submit(function(){
            var ajaxUrl = $("#partyClsGroupFormAction").val();
            var postparams =$("#partyClsGroupForm").serialize();
            var currentParentId = $("#parentGroupId").val();
            easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
                if(type){
                    easyfk.storeMenuBar();
                    $("#description").val("");
                    easyfk.getChildNode(currentParentId,'node_'+currentParentId,1);
                    $("#partyClsGroupFormDailog").modal("hide");
                }else{
                    alert(msg);
                }
            });
            return false;
        });

    });

</script>

<input type="hidden"   id="currentSelectedGroupId" value="">
<input type="hidden"   id="partyClsGroupFormAction" value="">
<div id="partyClsGroupFormDailog" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="formDailogLabel">
             新建组织机构
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="partyClsGroupForm" id="partyClsGroupForm">
            <input type="hidden" name="partyClassificationGroupId" id="partyClassificationGroupId">
            <input type="hidden" name="partyClassificationTypeId" id="partyClassificationTypeId" value="ORGANIZATION_CLASSIF">
            <input type="hidden" name="parentGroupId" id="parentGroupId" value="N/A">

                <div class="control-group">
                    <label class="control-label">组织机构</label>
                    <div class="controls">
                        <input type="text" id="description" name="description"/>
                    </div>
                </div>
        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="partyClsGroupFormSubmit" >保存</button>
        <button class="btn" id="partyClsGroupFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>

</div>

<script type="text/javascript">
    easyfk.newPerson = function () {
        $("#partyPersonFormAction").val("${ctx}/party/createperson");
        $("#partyPersonFormLabel").html("新建个人会员");
        $("#partyPersonFormPanel").modal("show");
    }

    easyfk.newGroup = function () {
        $("#partyGroupFormAction").val("${ctx}/party/creategroup");
        $("#partyPersonFormLabel").html("新建组织会员");
        $("#partyPersonFormPanel").modal("show");
    }

    easyfk.loaddata = function(){
        var url ="${ctx}/party/listpartydata?navids=${navids}";
        $("#listdatacontainer").load(url);
    }
    easyfk.partydetial = function(partyId){
        document.location.href = "${ctx}/party/partydetial?id="+partyId;
    }

    easyfk.removeparty = function(){
        $("#unicornAlertDailog_title").html("询问");
        $("#unicornAlertDailog_body").html("确定要删除吗？");
        $("#unicornAlertDailog").modal("show");

//        easyfk.confirm("",function(){
//            alert("ok");
//        });
    }


    $(document).ready(function(){
        //load data
        easyfk.loaddata();

        // Form Validation
        $("#partyPersonFormSubmit").click(function(){
            $("#partyPersonForm").submit();
        });

        $("#partyPersonForm").submit(function(){
            var ajaxUrl = $("#partyPersonFormAction").val();

            var postparams =$("#partyPersonForm").serialize();
            easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
                if(type){
                    //easyfk.storeMenuBar();
                    $("#partyPersonForm").reset();
                }else{
                    alert(msg);
                }
            });
            return false;
        });


        $("#partyGroupFormSubmit").click(function(){
            $("#partyGroupForm").submit();
        });

        $("#partyGroupForm").submit(function(){
            var ajaxUrl = $("#partyGroupFormAction").val();
            var postparams =$("#partyGroupForm").serialize();
            easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
                if(type){
                    $("#description").val("");
                }else{
                    alert(msg);
                }
            });
            return false;
        });

    });

</script>



<input type="hidden" id="partyPersonFormAction">
<div id="partyPersonFormPanel" class="modal hide fade" role="dialog" aria-labelledby="partyPersonFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="partyPersonFormLabel">
            新建个人会员
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="partyPersonForm" id="partyPersonForm">
            <input type="hidden" name="partyId" id="partyId">
            <input type="hidden" name="partyTypeId" id="partyTypeId" value="PERSON">
            <input type="hidden" name="preferredCurrencyUomId" id="preferredCurrencyUomId" value="CNY">
            <div class="control-group">
                <label class="control-label">姓名</label>
                <div class="controls">
                    <input type="text" id="firstName" name="firstName"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">${uiLabelMap.CommonDescription}</label>
                <div class="controls">
                    <input type="text" id="description" name="description"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">${uiLabelMap.FormFieldTitle_externalId}</label>
                <div class="controls">
                    <input type="text" id="externalId" name="externalId"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="partyPersonFormSubmit" >保存</button>
        <button class="btn" id="partyPersonFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>

<input type="hidden" id="partyGroupFormAction">
<div id="partyGroupFormPanel" class="modal hide fade" role="dialog" aria-labelledby="partyGroupFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="partyGroupFormLabel">
            新建组织会员
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="partyGroupForm" id="partyGroupForm">
            <input type="hidden" name="partyId" id="partyId">
            <input type="hidden" name="partyClassificationTypeId" id="partyClassificationTypeId" value="PARTY_GROUP">
            <input type="hidden" name="groupNameLocal" id="groupNameLocal" value="zh_CN">

            <div class="control-group">
                <label class="control-label">${uiLabelMap.PartyGroupName}</label>
                <div class="controls">
                    <input type="text" id="groupName" name="groupName"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">${uiLabelMap.CommonDescription}</label>
                <div class="controls">
                    <input type="text" id="comments" name="comments"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="partyGroupFormSubmit" >保存</button>
        <button class="btn" id="partyGroupFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>



