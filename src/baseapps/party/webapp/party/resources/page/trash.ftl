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

<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuPartygCommunite','menuPartygCommunite_5']}" location="component://party/webapp/party/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="草稿箱">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'公告消息',tip:'公告消息',href:'${ctx}/partyclsgroup/list'},
    {id:'navid3',title:'草稿箱'},
 ]}
</@easyfkSetNavBar>

<@easyfkHeaderAttach>
<link rel="stylesheet" href="/static/css/uniform.css" />
<link rel="stylesheet" href="/static/css/select2.css" />

<script src="/static/js/jquery/validate/jquery.validate.min.js"></script>
<script src="/static/js/jquery/validate/localization/messages_zh.min.js"></script>
<script src="/static/js/jquery/jquery.uniform.js"></script>
<script src="/static/js/jquery/select2.min.js"></script>
</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/inbox.js"></script>
</@easyfkFooterAttach>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/party/resources/page/appcommon.ftl">

<div class="row-fluid">
    <div class="span12">
        <@easyfkContextMenu>
            {MENU_DATA:[
            {id:'',title:'编写新消息',desc:'编写新的消息',toggle:'modal',href:'javascript:easyfk.showPublicDetail()',style:'btn btn-primary tip-bottom',style2:' icon-pencil icon-white'},
            {id:'',title:'选择收件人',desc:'选择新消息的收件人',toggle:'modal',href:'javascript:easyfk.selectParty()',style:'btn btn-primary tip-bottom',style2:'  icon-filter icon-white'},

            ]}
        </@easyfkContextMenu>

        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
                <h5>草稿箱</h5>
            </div>
            <div class="widget-content ">
                <div class="row-fluid">
                    <div class="span12">
                        <div class="alert">
                            <div class="btn-group " style="float: right">
                                <button class="btn btn-mini btn-info" >操作</button>
                                <button data-toggle="dropdown" class="btn btn-info  btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu  pull-right">
                                    <li><a href="#" onclick="easyfk.showMessageDetail()">编辑</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.removemsg(1)">删除</a></li>
                                </ul>
                            </div>
                            <strong>张三</strong><p></p>       这是草稿,这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿..........
                        </div>
                        <div class="alert alert-success">
                            <div class="btn-group " style="float: right">
                                <button class="btn btn-mini btn-info" >操作</button>
                                <button data-toggle="dropdown" class="btn btn-info  btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu  pull-right">
                                    <li><a href="#" onclick="easyfk.showMessageDetail()">编辑</a></li>

                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.removemsg(1)">删除</a></li>
                                </ul>
                            </div>
                            <strong>王二</strong><p></p>        这是草稿,这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿..........

                        </div>
                        <div class="alert alert-info">
                            <div class="btn-group " style="float: right">
                                <button class="btn btn-mini btn-info" >操作</button>
                                <button data-toggle="dropdown" class="btn btn-info  btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu  pull-right">
                                    <li><a href="#" onclick="easyfk.showMessageDetail()">编辑</a></li>

                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.removemsg(1)">删除</a></li>
                                </ul>
                            </div>
                            <strong>李四</strong><p></p>       这是草稿,这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿..........

                        </div>
                        <div class="alert alert-error">
                            <div class="btn-group " style="float: right">
                                <button class="btn btn-mini btn-info" >操作</button>
                                <button data-toggle="dropdown" class="btn btn-info  btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu  pull-right">
                                    <li><a href="#" onclick="easyfk.showMessageDetail()">编辑</a></li>

                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.removemsg(1)">删除</a></li>
                                </ul>
                            </div>
                            <strong>王五</strong><p></p>
                            这是草稿,这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿..........

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="pagination">
        <ul>
            <li><a href="#">首页</a></li>
            <li class="active">
                <a href="#">1</a>
            </li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">尾页</a></li>
        </ul>
    </div>
</div>


<script type="text/javascript">

    easyfk.showMessageDetail = function() {
        $("#messageDetailPanel").modal("show");
    }
    easyfk.showPublicDetail = function() {
        $("#messagePublicPanel").modal("show");
    }

    easyfk.selectParty= function(){
        $("#partyClsPanel").modal("show");

    }

    easyfk.removemsg = function(){
        $("#unicornAlertDailog_title").html("询问");
        $("#unicornAlertDailog_body").html("确定要XX吗？");
        $("#unicornAlertDailog").modal("show");

    }
</script>


<div id="messageDetailPanel" class="modal  fade" role="dialog" aria-labelledby="messageDetailLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="messageDetailLabel">
            消息
        </h3>
    </div>
    <div class="modal-body">

        <form action="" method="post" class="form-horizontal" name="partyClsSearchForm" id="partyClsSearchForm">
            <div class="control-group">
                <label class="control-label" style="width: 60px;margin-left:-5px;">收件人</label>
                <div class="controls"  style="margin-left:70px;">
                    <select multiple="">
                        <option />张三
                        <option selected="" />李四
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"  style="width: 60px;margin-left:-5px;">级别</label>
                <div class="controls" style="margin-left:70px;">
                    <select>
                        <option />一般消息
                        <option />重要消息
                        <option />比较重要
                        <option />特别重要紧急
                    </select>
                    (显示为不同色彩)
                </div>
            </div>
            <div class="control-group">
                <div class="controls" style="width: 60px;margin-left:-5px;">
                    <div style="height: 300px;" >
                        <textarea style="height: 300px;width: 525px;" >这是草稿,这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿这是草稿..........
                        </textarea>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button class="btn " id="sysLogSearchFormSubmit" onclick="easyfk.dosearch()">回复</button>
        <button class="btn btn-primary" id="sysLogSearchFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>

    </div>
</div>
</div>


<div id="messagePublicPanel" class="modal  fade" role="dialog" aria-labelledby="messagePubliclLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="messagePublicLabel">
            新消息
        </h3>
    </div>
    <div class="modal-body">

        <form action="" method="post" class="form-horizontal" name="messagePublicForm" id="partyClsSearchForm">
            <div class="control-group">
                <label class="control-label" style="width: 60px;margin-left:-5px;">收件人</label>
                <div class="controls"  style="margin-left:70px;">
                    <select multiple="">
                        <option />张三
                        <option selected="" />李四
                    </select>
                    <button class="btn btn-info btn-mini" style="margin-left: 10px;margin-top: 2px;" onclick="easyfk.selectParty()">...</button>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label"  style="width: 60px;margin-left:-5px;">级别</label>
                <div class="controls" style="margin-left:70px;">
                    <select>
                        <option />一般消息
                        <option />重要消息
                        <option />比较重要
                        <option />特别重要紧急
                    </select>
                    (显示为不同色彩)
                </div>
            </div>

            <div class="control-group">
                <div class="controls" style="width: 60px;margin-left:-5px;">
                    <div style="height: 260px;" >
                        <textarea style="height: 260px;width: 525px;" >

                        </textarea>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button class="btn " id="sysLogSearchFormSubmit" onclick="easyfk.dosearch()">回复</button>
        <button class="btn btn-primary" id="sysLogSearchFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>

    </div>
</div>
</div>

<div id="partyClsPanel" class="modal  fade" role="dialog" aria-labelledby="partyClslLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="partyClsLabel">
            收件人
        </h3>
    </div>
    <div class="modal-body">
            <div id="partyClsGroupTree" style="overflow-y: auto;height: 550px;">

            </div>



    </div>
    <div class="modal-footer">
        <button class="btn " id="partyClsFormSubmit" onclick="easyfk.dosearch()">确定</button>
        <button class="btn btn-primary" id="partyClsFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>

    </div>
</div>
</div>

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
        easyfk.ajaxgetstring(ajaxUrl,postparams,function(rel,msg){
            if(!rel){
                alert(msg);
                return ;
            }
            var jsonobj = jQuery.parseJSON(msg);
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
            html+="><input type=\"checkbox\" style=\"margin: 5px;\">"+item.description +"</span>";

            if(item.childCount > 0) {
                html += "<span class=\"label\" style='margin-left:50px;'>" + item.childCount + "</span>";
            }

            html+="</a></li>";
        });
        html+="</ul>"
        $("#"+thisItemId).append(html);
    }



    $(document).ready(function() {
        easyfk.renderTree([{
            "description": "我的公司",
            "partyClassificationGroupId": "10193",
            "lastUpdatedStamp": {
                "date": 20,
                "day": 5,
                "hours": 16,
                "minutes": 30,
                "month": 10,
                "nanos": 46000000,
                "seconds": 15,
                "time": 1448008215046,
                "timezoneOffset": -480,
                "year": 115
            },
            "parentGroupId": "N/A",
            "partyClassificationTypeId": "ORGANIZATION_CLASSIF",
            "lastUpdatedTxStamp": {
                "date": 20,
                "day": 5,
                "hours": 16,
                "minutes": 30,
                "month": 10,
                "nanos": 46000000,
                "seconds": 15,
                "time": 1448008215046,
                "timezoneOffset": -480,
                "year": 115
            },
            "childCount": 3
        }], "partyClsGroupTree", 0);
        easyfk.getChildNode('10193', 'node_10193', 1);
    });


</script>

</@easyfkDecoratorScreen>






