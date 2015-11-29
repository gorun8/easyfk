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

<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuPartyAuth','menuPartyAuth_2']}" location="component://party/webapp/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="角色授权">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'认证授权',tip:'认证授权',href:'${ctx}/partyclsgroup/list'},
    {id:'navid3',title:'角色授权'},
 ]}
</@easyfkSetNavBar>

<@easyfkHeaderAttach>
<#--
<link rel="stylesheet" href="/images/css/datepicker.css" />
<link rel="stylesheet" href="/images/css/uniform.css" />
<link rel="stylesheet" href="/images/css/select2.css" />
<script src="/images/js/jquery/bootstrap-datepicker.js"></script>
-->
<script src="/images/js/jquery/validate/jquery.validate.min.js"></script>
<script src="/images/js/jquery/validate/localization/messages_zh.min.js"></script>
<#--
<script src="/images/js/jquery/jquery.uniform.js"></script>
<script src="/images/js/jquery/select2.min.js"></script>
-->
</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/partyrolelist.js"></script>
</@easyfkFooterAttach>


<@easyfkDecoratorScreen name="body" location="component://party/webapp/resources/page/appcommon.ftl">
<div class="row-fluid">
    <div class="span12">
        <@easyfkContextMenu  >
            {MENU_DATA:[
            {id:'',title:'新建角色',desc:'新建自定义角色',toggle:'modal',href:'javascript:easyfk.newpartyRight()',style:'btn btn-primary tip-bottom',style2:' icon-map-marker icon-white'},

            ]}
        </@easyfkContextMenu>
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
                <h5>角色列表</h5>

            </div>
            <div class="widget-content">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th style="width:40px;">序号</th>
                        <th>角色</th>
                        <th>类型</th>
                        <th>说明</th>
                        <th style="width:80px;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <span class="badge badge-success"  >1</span>
                        </td>
                        <td>
                            系统管理员
                        </td>
                        <td><span class="label label-success">系统角色</span></td>
                        <td>
                            具有系统最高权限的管理员，负责其它管理员权限的管理等
                        </td>
                        <td>
                            <div class="btn-group">
                                <button class="btn btn-mini">操作</button>
                                <button data-toggle="dropdown" class="btn   btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu pull-right">
                                    <li><a href="#" onclick="easyfk.editpartyRole()">修改</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.partyRight(1)">资源</a></li>
                                    <li><a href="#" onclick="easyfk.showUserLogin(1)">关联账号</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.showLog()">关联日志</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span class="badge badge-success">2</span>
                        </td>
                        <td>
                            安全保密管理员
                        </td>
                        <td><span class="label label-success">系统角色</span></td>
                        <td>
                            具有系统全面管理权限的管理员，负责系统的日常维护
                        </td>
                        <td>
                            <div class="btn-group">
                                <button class="btn btn-mini">操作</button>
                                <button data-toggle="dropdown" class="btn   btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu pull-right">
                                    <li><a href="#" onclick="easyfk.editpartyRole()">修改</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.partyRight(1)">资源</a></li>
                                    <li><a href="#" onclick="easyfk.showUserLogin(1)">关联账号</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.showLog()">关联日志</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>

                        <td>
                            <span class="badge badge-success">4</span>
                        </td>

                        <td>
                            系统安全员
                        </td>
                        <td><span class="label label-success">系统角色</span></td>
                        <td>
                            负责系统安全审计
                        </td>
                        <td>
                            <div class="btn-group">
                                <button class="btn btn-mini">操作</button>
                                <button data-toggle="dropdown" class="btn   btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu pull-right">
                                    <li><a href="#" onclick="easyfk.editpartyRole()">修改</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.partyRight(1)">资源</a></li>
                                    <li><a href="#" onclick="easyfk.showUserLogin(1)">关联账号</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.showLog()">关联日志</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <span class="badge badge-info">4</span>
                        </td>

                        <td>
                            业务操作员
                        </td>
                        <td><span class="label label-info">自定义角色</span></td>
                        <td>
                            由系统管理员定义，负责业务操作
                        </td>
                        <td>


                            <div class="btn-group">
                                <button class="btn btn-mini">操作</button>
                                <button data-toggle="dropdown" class="btn   btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu pull-right">
                                    <li><a href="#" onclick="easyfk.editpartyRole()">修改</a></li>
                                    <li><a href="#" onclick="easyfk.removeUserLogin()">删除</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.partyRight(1)">资源</a></li>
                                    <li><a href="#" onclick="easyfk.showUserLogin(1)">关联账号</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.showLog()">关联日志</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    easyfk.showUserLogin = function(partyId){
        document.location.href = "${ctx}/page/roleuserloginlist.ftl"+partyId;
    }


    easyfk.partyRight = function () {
        $("#partyRightFormAction").val("${ctx}/party/createuserlogin");
        $("#partyRightFormLabel").html("资源");
        $("#partyRightFormPanel").modal("show");
    }

    easyfk.newpartyRight = function () {
        $("#partyRoleFormAction").val("${ctx}/party/createuserlogin");
        $("#partyRoleFormLabel").html("新建角色");
        $("#partyRoleFormPanel").modal("show");
    }

    easyfk.editpartyRole = function () {
        $("#partyRoleFormAction").val("${ctx}/party/createuserlogin");
        $("#partyRoleFormLabel").html("修改角色");
        $("#roleName").val("系统管理员");
        $("#partyRoleFormPanel").modal("show");
    }

    easyfk.editUserInfo = function () {

        $("#userInfoFormPanel").modal("show");
    }


    easyfk.removeUserLogin = function(){
        $("#unicornAlertDailog_title").html("询问");
        $("#unicornAlertDailog_body").html("确定要XX吗？");
        $("#unicornAlertDailog").modal("show");

    }

    easyfk.showLog = function(){
        document.location.href = "${ctx}/page/oploglist.ftl";
    }

    $(document).ready(function(){

        // Form Validation
        $("#partyRightFormSubmit").click(function(){
            $("#partyRightForm").submit();
        });

        $("#partyRightForm").submit(function(){
            var ajaxUrl = $("#partyRightFormAction").val();
            alert(ajaxUrl);
            var postparams =$("#partyRightForm").serialize();
            easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
                if(type){
                    //easyfk.storeMenuBar();
                    $("#partyRightForm").reset();
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

    easyfk.showSubTree =function(id){
        var theObj = $("#node_"+id);
        $("#currentSelectedGroupId").val(id);

        if ( theObj.hasClass("open")){
            theObj.children(".subpanel").hide();
            theObj.removeClass("open");
            $("#icon"+id).removeClass("icon-folder-open");
            $("#icon"+id).addClass("icon-eye-close");
        }else{
            theObj.children(".subpanel").show();
            theObj.addClass("open");
            $("#icon"+id).removeClass("icon-eye-close");
            $("#icon"+id).addClass("icon-eye-open");
        }
        $(".nodeselected").removeClass("nodeselected");
        theObj.addClass("nodeselected");
        $("#partyClsGroupMenuBar").insertBefore($("#itemmenupanel"+id));
        $("#partyClsGroupMenuBar").show();

    }
</script>

<input type="hidden" id="partyRightFormAction">
<div id="partyRightFormPanel" class="modal hide fade" role="dialog" aria-labelledby="partyRightFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="partyRightFormLabel">
            资源
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="partyRightForm" id="partyRightForm">
            <div id="partyClsGroupTree" style="overflow-y: auto; ">

                <ul class="subpanel" style="list-style: none;">
                    <li id="node_10193"
                        data="{&quot;description&quot;:&quot;资源目录&quot;,&quot;partyClassificationGroupId&quot;:&quot;10193&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:546000000,&quot;seconds&quot;:16,&quot;time&quot;:1447918516546,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;N/A&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:546000000,&quot;seconds&quot;:16,&quot;time&quot;:1447918516546,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:3}"
                        navids=",10193" class="subtree">
                        <div class="itemmenubar"><i id="itemmenupanel10193"></i></div>
                        <a href="javascript:void(0)" onclick="easyfk.showSubTree('10193')"><i
                                class="icon icon-folder-open" id="icon10193"></i><span
                                 ><input type="checkbox" name="radios" style="margin:5px;">资源目录</span><span class="label"
                                                                                                          style="margin-left:50px;">3</span></a>
                        <ul class="subpanel" style="list-style: none;">
                            <li id="node_10215"
                                data="{&quot;description&quot;:&quot;用户管理&quot;,&quot;partyClassificationGroupId&quot;:&quot;10215&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:156000000,&quot;seconds&quot;:17,&quot;time&quot;:1447918517156,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10193&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:156000000,&quot;seconds&quot;:17,&quot;time&quot;:1447918517156,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:6}"
                                navids=",10193,10215" class="subtree open">
                                <div class="itemmenubar"><i id="itemmenupanel10215"></i></div>
                                <a href="javascript:void(0)" onclick="easyfk.showSubTree('10215')"><i
                                        class="icon icon-folder-open" id="icon10215"></i><span><input type="checkbox" name="radios" style="margin:5px;">用户管理</span><span
                                        class="label" style="margin-left:50px;">6</span></a>
                                <ul class="subpanel" style="list-style: none;">
                                    <li id="node_10280"
                                        data="{&quot;description&quot;:&quot;禁用用户登录账号&quot;,&quot;partyClassificationGroupId&quot;:&quot;10280&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:328000000,&quot;seconds&quot;:19,&quot;time&quot;:1447918519328,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10215&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:328000000,&quot;seconds&quot;:19,&quot;time&quot;:1447918519328,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}"
                                        navids=",10193,10215,10280" class="subtree">
                                        <div class="itemmenubar"><i id="itemmenupanel10280"></i></div>
                                        <a href="javascript:void(0)" onclick="easyfk.showSubTree('10280')"><i
                                                class="icon icon-eye-close" id="icon10280"></i><span
                                                 ><input type="checkbox" name="radios" style="margin:5px;">禁用用户登录账号</span></a>
                                    </li>
                                    <li id="node_10281"
                                        data="{&quot;description&quot;:&quot;新建用户登录账号&quot;,&quot;partyClassificationGroupId&quot;:&quot;10281&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:328000000,&quot;seconds&quot;:19,&quot;time&quot;:1447918519328,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10215&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:328000000,&quot;seconds&quot;:19,&quot;time&quot;:1447918519328,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}"
                                        navids=",10193,10215,10281" class="subtree">
                                        <div class="itemmenubar"><i id="itemmenupanel10281"></i></div>
                                        <a href="javascript:void(0)" onclick="easyfk.showSubTree('10281')"><i
                                                class="icon icon-eye-close" id="icon10281"></i><span
                                                 ><input type="checkbox" name="radios" style="margin:5px;">新建用户登录账号</span></a>
                                    </li>
                                    <li id="node_10282"
                                        data="{&quot;description&quot;:&quot;删除用户登录账号&quot;,&quot;partyClassificationGroupId&quot;:&quot;10282&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:328000000,&quot;seconds&quot;:19,&quot;time&quot;:1447918519328,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10215&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:328000000,&quot;seconds&quot;:19,&quot;time&quot;:1447918519328,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}"
                                        navids=",10193,10215,10282" class="subtree">
                                        <div class="itemmenubar"><i id="itemmenupanel10282"></i></div>
                                        <a href="javascript:void(0)" onclick="easyfk.showSubTree('10282')"><i
                                                class="icon icon-eye-close" id="icon10282"></i><span
                                                 ><input type="checkbox" name="radios" style="margin:5px;">删除用户登录账号</span></a>
                                    </li>
                                    <li id="node_10283"
                                        data="{&quot;description&quot;:&quot;用户账号锁定&quot;,&quot;partyClassificationGroupId&quot;:&quot;10283&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:328000000,&quot;seconds&quot;:19,&quot;time&quot;:1447918519328,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10215&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:328000000,&quot;seconds&quot;:19,&quot;time&quot;:1447918519328,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}"
                                        navids=",10193,10215,10283" class="subtree">
                                        <div class="itemmenubar"><i id="itemmenupanel10283"></i></div>
                                        <a href="javascript:void(0)" onclick="easyfk.showSubTree('10283')"><i
                                                class="icon icon-eye-close" id="icon10283"></i><span
                                                 ><input type="checkbox" name="radios" style="margin:5px;">用户账号锁定</span></a>
                                    </li>
                                    <li id="node_10284"
                                        data="{&quot;description&quot;:&quot;用户账号解除锁定&quot;,&quot;partyClassificationGroupId&quot;:&quot;10284&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:328000000,&quot;seconds&quot;:19,&quot;time&quot;:1447918519328,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10215&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:328000000,&quot;seconds&quot;:19,&quot;time&quot;:1447918519328,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}"
                                        navids=",10193,10215,10284" class="subtree">
                                        <div class="itemmenubar"><i id="itemmenupanel10284"></i></div>
                                        <a href="javascript:void(0)" onclick="easyfk.showSubTree('10284')"><i
                                                class="icon icon-eye-close" id="icon10284"></i><span
                                                 ><input type="checkbox" name="radios" style="margin:5px;">用户账号解除锁定</span></a>
                                    </li>
                                    <li id="node_10292"
                                        data="{&quot;description&quot;:&quot;关联登录证书&quot;,&quot;partyClassificationGroupId&quot;:&quot;10292&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:328000000,&quot;seconds&quot;:19,&quot;time&quot;:1447918519328,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10215&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:328000000,&quot;seconds&quot;:19,&quot;time&quot;:1447918519328,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}"
                                        navids=",10193,10215,10292" class="subtree">
                                        <div class="itemmenubar"><i id="itemmenupanel10292"></i></div>
                                        <a href="javascript:void(0)" onclick="easyfk.showSubTree('10292')"><i
                                                class="icon icon-eye-close" id="icon10292"></i><span
                                                 ><input type="checkbox" name="radios" style="margin:5px;">关联登录证书</span></a>
                                    </li>
                                </ul>
                            </li>
                            <li id="node_10270"
                                data="{&quot;description&quot;:&quot;角色管理&quot;,&quot;partyClassificationGroupId&quot;:&quot;10270&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:156000000,&quot;seconds&quot;:17,&quot;time&quot;:1447918517156,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10193&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:156000000,&quot;seconds&quot;:17,&quot;time&quot;:1447918517156,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:4}"
                                navids=",10193,10270" class="subtree">
                                <div class="itemmenubar"><i id="itemmenupanel10270"></i></div>
                                <a href="javascript:void(0)" onclick="easyfk.showSubTree('10270')"><i
                                        class="icon icon-folder-open" id="icon10270"></i><span
                                        ><input type="checkbox" name="radios" style="margin:5px;">角色管理</span><span
                                        class="label" style="margin-left:50px;">4</span></a>
                                <ul class="subpanel" style="list-style: none;">
                                    <li id="node_10285"
                                        data="{&quot;description&quot;:&quot;显示角色列表&quot;,&quot;partyClassificationGroupId&quot;:&quot;10285&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:812000000,&quot;seconds&quot;:20,&quot;time&quot;:1447918520812,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10270&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:812000000,&quot;seconds&quot;:20,&quot;time&quot;:1447918520812,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}"
                                        navids=",10193,10270,10285" class="subtree">
                                        <div class="itemmenubar"><i id="itemmenupanel10285"></i></div>
                                        <a href="javascript:void(0)" onclick="easyfk.showSubTree('10285')"><i
                                                class="icon icon-eye-close" id="icon10285"></i><span
                                                 ><input type="checkbox" name="radios" style="margin:5px;">显示角色列表</span></a>
                                    </li>
                                    <li id="node_10286"
                                        data="{&quot;description&quot;:&quot;新增自定义角色&quot;,&quot;partyClassificationGroupId&quot;:&quot;10286&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:812000000,&quot;seconds&quot;:20,&quot;time&quot;:1447918520812,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10270&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:812000000,&quot;seconds&quot;:20,&quot;time&quot;:1447918520812,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}"
                                        navids=",10193,10270,10286" class="subtree">
                                        <div class="itemmenubar"><i id="itemmenupanel10286"></i></div>
                                        <a href="javascript:void(0)" onclick="easyfk.showSubTree('10286')"><i
                                                class="icon icon-eye-close" id="icon10286"></i><span
                                                 ><input type="checkbox" name="radios" style="margin:5px;">新增自定义角色</span></a>
                                    </li>
                                    <li id="node_10287"
                                        data="{&quot;description&quot;:&quot;删除自定义角色&quot;,&quot;partyClassificationGroupId&quot;:&quot;10287&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:812000000,&quot;seconds&quot;:20,&quot;time&quot;:1447918520812,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10270&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:812000000,&quot;seconds&quot;:20,&quot;time&quot;:1447918520812,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}"
                                        navids=",10193,10270,10287" class="subtree">
                                        <div class="itemmenubar"><i id="itemmenupanel10287"></i></div>
                                        <a href="javascript:void(0)" onclick="easyfk.showSubTree('10287')"><i
                                                class="icon icon-eye-close" id="icon10287"></i><span
                                                 ><input type="checkbox" name="radios" style="margin:5px;">删除自定义角色</span></a>
                                    </li>
                                    <li id="node_10288"
                                        data="{&quot;description&quot;:&quot;角色授权&quot;,&quot;partyClassificationGroupId&quot;:&quot;10288&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:812000000,&quot;seconds&quot;:20,&quot;time&quot;:1447918520812,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10270&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:812000000,&quot;seconds&quot;:20,&quot;time&quot;:1447918520812,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}"
                                        navids=",10193,10270,10288" class="subtree">
                                        <div class="itemmenubar"><i id="itemmenupanel10288"></i></div>
                                        <a href="javascript:void(0)" onclick="easyfk.showSubTree('10288')"><i
                                                class="icon icon-eye-close" id="icon10288"></i><span
                                                 ><input type="checkbox" name="radios" style="margin:5px;">角色授权</span></a>
                                    </li>
                                </ul>
                            </li>
                            <li id="node_10271"
                                data="{&quot;description&quot;:&quot;三员管理&quot;,&quot;partyClassificationGroupId&quot;:&quot;10271&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:156000000,&quot;seconds&quot;:17,&quot;time&quot;:1447918517156,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10193&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:156000000,&quot;seconds&quot;:17,&quot;time&quot;:1447918517156,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:3}"
                                navids=",10193,10271" class="subtree nodeselected">
                                <div class="itemmenubar">
                                    <div id="partyClsGroupMenuBar" style="">

                                    </div>
                                    <i id="itemmenupanel10271"></i></div>
                                <a href="javascript:void(0)" onclick="easyfk.showSubTree('10271')"><i
                                        class="icon icon-folder-open" id="icon10271"></i><span
                                         ><input type="checkbox" name="radios" style="margin:5px;">三员管理</span><span
                                        class="label" style="margin-left:50px;">3</span></a>
                                <ul class="subpanel" style="list-style: none;">
                                    <li id="node_10289"
                                        data="{&quot;description&quot;:&quot;显示三员列表&quot;,&quot;partyClassificationGroupId&quot;:&quot;10289&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:875000000,&quot;seconds&quot;:22,&quot;time&quot;:1447918522875,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10271&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:875000000,&quot;seconds&quot;:22,&quot;time&quot;:1447918522875,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}"
                                        navids=",10193,10271,10289" class="subtree">
                                        <div class="itemmenubar"><i id="itemmenupanel10289"></i></div>
                                        <a href="javascript:void(0)" onclick="easyfk.showSubTree('10289')"><i
                                                class="icon icon-eye-close" id="icon10289"></i><span
                                                 ><input type="checkbox" name="radios" style="margin:5px;">显示三员列表</span></a>
                                    </li>
                                    <li id="node_10290"
                                        data="{&quot;description&quot;:&quot;编辑三员信息&quot;,&quot;partyClassificationGroupId&quot;:&quot;10290&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:875000000,&quot;seconds&quot;:22,&quot;time&quot;:1447918522875,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10271&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:875000000,&quot;seconds&quot;:22,&quot;time&quot;:1447918522875,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}"
                                        navids=",10193,10271,10290" class="subtree">
                                        <div class="itemmenubar"><i id="itemmenupanel10290"></i></div>
                                        <a href="javascript:void(0)" onclick="easyfk.showSubTree('10290')"><i
                                                class="icon icon-eye-close" id="icon10290"></i><span
                                                 ><input type="checkbox" name="radios" style="margin:5px;">编辑三员信息</span></a>
                                    </li>
                                    <li id="node_10291"
                                        data="{&quot;description&quot;:&quot;关联登录证书&quot;,&quot;partyClassificationGroupId&quot;:&quot;10291&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:875000000,&quot;seconds&quot;:22,&quot;time&quot;:1447918522875,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10271&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:19,&quot;day&quot;:4,&quot;hours&quot;:15,&quot;minutes&quot;:35,&quot;month&quot;:10,&quot;nanos&quot;:875000000,&quot;seconds&quot;:22,&quot;time&quot;:1447918522875,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}"
                                        navids=",10193,10271,10291" class="subtree">
                                        <div class="itemmenubar"><i id="itemmenupanel10291"></i></div>
                                        <a href="javascript:void(0)" onclick="easyfk.showSubTree('10291')"><i
                                                class="icon icon-eye-close" id="icon10291"></i><span
                                                 ><input type="checkbox" name="radios" style="margin:5px;">关联登录证书</span></a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="partyRightFormSubmit" >保存</button>
        <button class="btn" id="partyRightFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>


<!---->



<input type="hidden" id="partyRoleFormAction">
<div id="partyRoleFormPanel" class="modal hide fade" role="dialog" aria-labelledby="partyRoleFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="partyRoleFormLabel">
            修改角色
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="partyRoleForm" id="partyRoleForm">
            <input type="hidden" name="partyId" id="partyId">
            <div class="control-group certcontent">
                <label class="control-label">角色</label>
                <div class="controls">
                   <input type="text" id="roleName">
                </div>
            </div>
            <div class="control-group certcontent">
                <label class="control-label">说明</label>
                <div class="controls">
                    <textarea type="text" id="roleName"></textarea>
                </div>
            </div>

        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="partyRoleFormSubmit" >保存</button>
        <button class="btn" id="partyRoleFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>
</@easyfkDecoratorScreen>










