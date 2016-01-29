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

<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuPartygpgs']}" location="component://party/webapp/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="会员列表">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'fa  fa-home',href:'${ctx}'},
    {id:'navid2',title:'组织机构',tip:'管理组织机构',href:'${ctx}/partyclsgroup/list'},
    <#list parentClsGroupList as item>
    {id:'navid${item.partyClassificationGroupId}',tip:'${item.description}',title:'${item.description}'},
    </#list>
    {id:'navid3',title:'会员详情'},
 ]}
</@easyfkSetNavBar>

<@easyfkHeaderAttach>
<link rel="stylesheet" href="/images/css/select2.css" />
<link rel="stylesheet" href="/images/css/datepicker.css" />
<script src="/images/js/jquery/bootstrap-datepicker.js"></script>
<script src="/images/js/jquery/validate/jquery.validate.min.js"></script>
<script src="/images/js/jquery/validate/localization/messages_zh.min.js"></script>
<script src="/images/js/jquery/select2.min.js"></script>
</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/partydetial.js"></script>
</@easyfkFooterAttach>


<@easyfkDecoratorScreen name="body" location="component://party/webapp/resources/page/appcommon.ftl">
<div class="row">
    <div class="col-xs-12 col-sm-12">
        <#--@easyfkContextMenu  >
            {MENU_DATA:[
            {id:'',title:'详情',desc:'显示会员详情汇总',toggle:'modal',href:'javascript:easyfk.noimplement()',style:'btn btn-primary tip-bottom',style2:' fa  fa-map-marker fa  fa-white'},
            {id:'',title:'角色',desc:'显示会员具有的角色',toggle:'modal',href:'javascript:easyfk.noimplement()',style:'btn btn-primary tip-bottom',style2:' fa  fa-map-marker fa  fa-white'},
            {id:'',title:'权限',desc:'显示会员权限',toggle:'modal',href:'javascript:easyfk.noimplement()',style:'btn btn-primary tip-bottom',style2:' fa  fa-map-marker fa  fa-white'},
            {id:'',title:'组织',desc:'显示会员所属的组织机构',toggle:'modal',href:'javascript:easyfk.noimplement()',style:'btn btn-primary tip-bottom',style2:' fa  fa-map-marker fa  fa-white'},
            {id:'',title:'日志',desc:'显示会员相关的日志',toggle:'modal',href:'javascript:easyfk.noimplement()',style:'btn btn-primary tip-bottom',style2:' fa  fa-map-marker fa  fa-white'},

            ]}
        </@easyfkContextMenu-->

            <div class="row">
                <div class="col-xs-12 col-sm-6">
                    <div class="widget-box">
                        <div class="widget-title">
								<span class="icon">
									<i class="fa  fa-th-list"></i>
								</span>
                            <h5>简介</h5>
                            <button class="btn btn-info btn-mini" onclick="easyfk.editUserInfo()" style="float: right;margin: 1px 10px 0 0;">编辑</button>
                        </div>
                        <div class="widget-content">
                            这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="widget-box">
                        <div class="widget-title">
								<span class="icon">
									<i class="fa  fa-th-list"></i>
								</span>
                            <h5>联系信息</h5>
                            <button class="btn btn-info btn-mini" onclick="easyfk.selectConactType();" style="float: right;margin: 1px 15px 0 0;">添加</button>
                        </div>
                        <div class="widget-content">
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>类型</th>
                                    <th>内容</th>
                                    <th style="width:80px;">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>
                                        <span class="badge">手机</span>
                                    </td>
                                    <td>
                                        <i   title="已过期" class="tip-bottom" style="color:#999;"> 18030662011 </i>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <a href="#" data-toggle="dropdown" class="btn btn-primary dropdown-toggle"> 操作 <span class="caret"></span></a>
                                            <ul class="dropdown-menu dropdown-primary pull-right">
                                                <li><a href="#" onclick="easyfk.removeUserLogin()">重新启用</a></li>
                                                <li><a href="#" onclick="easyfk.newMobile()">添加新的</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <span class="badge badge-success">手机</span>
                                    </td>
                                    <td>
                                        <a   title="正常" class="tip-bottom" style="color:#468847;">13530662011 </a>

                                    </td>
                                    <td>

                                        <div class="btn-group">
                                            <a href="#" data-toggle="dropdown" class="btn btn-primary dropdown-toggle"> 操作 <span class="caret"></span></a>
                                            <ul class="dropdown-menu dropdown-primary pull-right">
                                                <li><a href="#" onclick="easyfk.removeUserLogin()">重新启用</a></li>
                                                <li><a href="#" onclick="easyfk.newMobile()">添加新的</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                <tr>

                                    <td>
                                        <span class="badge badge-success">办公电话</span>
                                    </td>
                                    <td>
                                        <a   title="正常" class="tip-bottom" style="color:#468847;">028-82545845 </a>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <a href="#" data-toggle="dropdown" class="btn btn-primary dropdown-toggle"> 操作 <span class="caret"></span></a>
                                            <ul class="dropdown-menu dropdown-primary pull-right">
                                                <li><a href="#" onclick="easyfk.removeUserLogin()">重新启用</a></li>
                                                <li><a href="#" onclick="easyfk.newTel()">添加新的</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <span class="badge badge-warning">电子邮件</span>
                                    </td>
                                    <td>
                                        <a   title="已停用" class="tip-bottom" style="color:#f89406;"> wwww@gorun8.cn </a>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <a href="#" data-toggle="dropdown" class="btn btn-primary dropdown-toggle"> 操作 <span class="caret"></span></a>
                                            <ul class="dropdown-menu dropdown-primary pull-right">
                                                <li><a href="#" onclick="easyfk.removeUserLogin()">重新启用</a></li>
                                                <li><a href="#" onclick="easyfk.newEmail()">添加新的</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <span class="badge badge-warning">公司地址</span>
                                    </td>
                                    <td>
                                        <a   title="该账号已停用" class="tip-bottom" style="color:#f89406;">成都高新区云华路333号 </a>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <a href="#" data-toggle="dropdown" class="btn btn-primary dropdown-toggle"> 操作 <span class="caret"></span></a>
                                            <ul class="dropdown-menu dropdown-primary pull-right">
                                                <li><a href="#" onclick="easyfk.removeUserLogin()">重新启用</a></li>
                                                <li><a href="#" onclick="easyfk.newAddress()">添加新的</a></li>
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
    </div>
</div>



<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="fa  fa-th-list"></i>
								</span>
                <h5>登录账号</h5>
                <button class="btn btn-info btn-mini" onclick="easyfk.newUserLogin();" style="float: right;margin: 1px 15px 0 0;">添加</button>

            </div>
            <div class="widget-content">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th style="width:40px;">序号</th>
                        <th>账号</th>
                        <th>状态</th>
                        <th>角色</th>
                        <th>认证方式</th>

                        <th style="width:80px;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <span class="badge"  >1</span>
                        </td>
                        <td>
                            <i   title="该账号已过期" class="tip-bottom" style="color:#999;"> zxxx@gorun8.cn </i>
                        </td>
                        <td>
                            过期
                        </td>
                        <td>
                            系统管理员
                        </td>
                        <td>
                            密码
                        </td>

                        <td>
                            <div class="btn-group">
                                <a href="#" data-toggle="dropdown" class="btn btn-primary dropdown-toggle"> 操作 <span class="caret"></span></a>
                                <ul class="dropdown-menu dropdown-primary pull-right">
                                    <li><a href="#" onclick="easyfk.partydetial(1)">会员详情</a></li>
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
                            <a   title="该账号正常" class="tip-bottom" style="color:#468847;"> zxxx@gorun8.cn </a>
                        </td>
                        <td>
                            正常
                        </td>
                        <td>
                            安全保密管理员
                        </td>
                        <td>
                            密码
                        </td>

                        <td>
                            <div class="btn-group">
                                <a href="#" data-toggle="dropdown" class="btn btn-primary dropdown-toggle"> 操作 <span class="caret"></span></a>
                                <ul class="dropdown-menu dropdown-primary pull-right">
                                    <li><a href="#" onclick="easyfk.removeUserLogin()">停用</a></li>
                                    <li><a href="#" onclick="easyfk.changePassword()">修改</a></li>
                                    <li><a href="#" onclick="easyfk.setUserCert(false)">关联证书</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>

                        <td>
                            <span class="badge badge-warning">4</span>
                        </td>
                        <td>
                            <a   title="该账号已停用" class="tip-bottom" style="color:#f89406;"> wwww@gorun8.cn </a>
                        </td>
                        <td>
                            停用
                        </td>
                        <td>
                            系统安全员
                        </td>

                        <td>
                            <button class="btn btn-success btn-mini">证书</button>
                        </td>

                        <td>
                            <div class="btn-group">
                                <a href="#" data-toggle="dropdown" class="btn btn-primary dropdown-toggle"> 操作 <span class="caret"></span></a>
                                <ul class="dropdown-menu dropdown-primary pull-right">
                                    <li><a href="#" onclick="easyfk.removeUserLogin()">启用</a></li>
                                    <li><a href="#" onclick="easyfk.changePassword()">修改</a></li>
                                    <li><a href="#" onclick="easyfk.setUserCert(true)">关联证书</a></li>
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

    easyfk.showLog = function(){
        document.location.href = "${ctx}/page/oploglist.ftl";
    }

    easyfk.setUserCert = function(showcert) {
        if(!showcert)
        {
            $(".certcontent").hide();
        }else{
            $(".certcontent").show();
        }
        $("#userCertFormPanel").modal("show");
    }

    easyfk.newUserLogin = function () {
        $("#userLoginFormAction").val("${ctx}/party/createuserlogin");
        $("#userLoginFormLabel").html("新建登录账号");
        $("#userLoginId").val("");
        $("#userLoginId").removeAttr("readonly");
        $("#userLoginFormPanel").modal("show");
    }

    easyfk.changePassword = function () {
        $("#userLoginFormAction").val("${ctx}/party/createuserlogin");
        $("#userLoginFormLabel").html("修改");
        $("#userLoginId").val(" wwww@gorun8.cn");
        $("#userLoginId").attr("readonly","true");
        $("#userLoginFormPanel").modal("show");
    }

    easyfk.editUserInfo = function () {
        $("#userInfoFormPanel").modal("show");
    }


    easyfk.removeUserLogin = function(){
        $("#unicornAlertDailog_title").html("询问");
        $("#unicornAlertDailog_body").html("确定要XX吗？");
        $("#unicornAlertDailog").modal("show");

    }


    $(document).ready(function(){

        // Form Validation
        $("#userLoginFormSubmit").click(function(){
            $("#userLoginForm").submit();
        });

        $("#userLoginForm").submit(function(){
            var ajaxUrl = $("#userLoginFormAction").val();
            alert(ajaxUrl);
            var postparams =$("#userLoginForm").serialize();
            easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
                if(type){
                    //easyfk.storeMenuBar();
                    $("#userLoginForm").reset();
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


        $('select').select2();

    });

</script>

<input type="hidden" id="userLoginFormAction">
<div id="userLoginFormPanel" class="modal fade" role="dialog" aria-labelledby="userLoginFormLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="userLoginFormLabel">
                    新建登录账号
                </h3>
            </div>
            <div class="modal-body">
                <form action="" method="post" class="form-horizontal" name="userLoginForm" id="userLoginForm">
                    <input type="hidden" name="partyId" id="partyId">
                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">帐号</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <input   class="form-control input-sm" type="text"  id="userLoginId" name="userLoginId" class="form-control input-sm"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">角色类型</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <select   multiple="">
                                <option value=""/>一般用户
                                <option value=""/>系统管理员
                                <option value=""/>系统安全员
                                <option value=""/>安全保密管理员
                                <option value=""/>自定义角色XX
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">${uiLabelMap.CommonPassword}</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <input   class="form-control input-sm" type="password" id="password" name="password" class="form-control input-sm"/>

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">${uiLabelMap.CommonPasswordConfirm}</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <input   class="form-control input-sm" type="password" id="password2" name="password2" class="form-control input-sm"/>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="userLoginFormSubmit" >保存</button>
                <button class="btn" id="userLoginFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>


<input type="hidden" id="userInfoFormAction">
<div id="userInfoFormPanel" class="modal fade" role="dialog" aria-labelledby="userInfoFormLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="userInfoFormLabel">
                    编辑个人简介
                </h3>
            </div>
            <div class="modal-body">
                <form action="" method="post" class="form-horizontal" name="userInfoForm" id="userInfoForm">
                    <input type="hidden" name="partyId" id="partyId">

                    <div class="form-group">
                        <div class="col-sm-12 col-md-12 col-lg-12">
                            <textarea rows="5" class="form-control focus"></textarea>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="userLoginFormSubmit" >保存</button>
                <button class="btn" id="userLoginFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    easyfk.newAddress = function(){
        $("#addressFormPanel").modal("show");
    }
    easyfk.newEmail = function(){
        $("#emailFormPanel").modal("show");
    }

    easyfk.newTel = function(){
        $("#telFormPanel").modal("show");
    }
    easyfk.newMobile = function(){
        $("#mobileFormPanel").modal("show");
    }
    easyfk.selectConactType = function(){
        $("#selectConactTypeFormPanel").modal("show");
    }

    $(function(){
        $("#selectConactTypeFormSubmit").click(function(){
            $("#selectConactTypeFormPanel").modal("hide");
            var v = $("#conactType").val();
            if(v == "mobile"){
                easyfk.newMobile();
            }else if(v =="tel"){
                easyfk.newTel();
            }else if(v =="email"){
                easyfk.newEmail();
            }else if(v =="address"){
                easyfk.newAddress();
            }
        });

    });





</script>

<input type="hidden" id="addressFormAction">
<div id="addressFormPanel" class="modal fade" role="dialog" aria-labelledby="addressFormLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="addressFormLabel">
                地址信息
            </h3>
        </div>
        <div class="modal-body">
            <form action="" method="post" class="form-horizontal" name="userLoginForm" id="userLoginForm">
                <input type="hidden" name="partyId" id="partyId">

                <div class="form-group">
                    <label class="col-sm-3 col-md-3 col-lg-2 control-label">国家</label>
                    <div class="col-sm-9 col-md-9 col-lg-10">
                        <input   class="form-control input-sm" type="text"   class="form-control input-sm"/>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-3 col-md-3 col-lg-2 control-label">地址</label>
                    <div class="col-sm-9 col-md-9 col-lg-10">
                        <div class="row">
                            <div class="col-md-4">
                                <input type="text" class="form-control input-sm">
                                <span class="help-block text-left">省</span>
                            </div>
                            <div class="col-md-4">
                                <input type="text" class="form-control input-sm">
                                <span class="help-block text-center">市/县</span>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 col-md-3 col-lg-2 control-label">地址</label>
                    <div class="col-sm-9 col-md-9 col-lg-10">
                        <input type="text" class="form-control input-sm">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 col-md-3 col-lg-2 control-label">邮编</label>
                    <div class="col-sm-9 col-md-9 col-lg-10">
                        <input type="text" class="form-control input-sm">
                    </div>
                </div>

            </form>
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" id="userLoginFormSubmit" >保存</button>
            <button class="btn" id="userLoginFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
        </div>
    </div>
    </div>
</div>

<input type="hidden" id="emailFormAction">
<div id="emailFormPanel" class="modal fade" role="dialog" aria-labelledby="emailFormLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="emailFormLabel">
                    邮件地址
                </h3>
            </div>
            <div class="modal-body">
                <form action="" method="post" class="form-horizontal" name="emailForm" id="emailForm">
                    <input type="hidden" name="partyId" id="partyId">

                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">邮件地址</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <input   class="form-control input-sm" type="text"  class="form-control input-sm"/>
                        </div>
                    </div>
                 </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="userLoginFormSubmit" >保存</button>
                <button class="btn" id="userLoginFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

<input type="hidden" id="telFormAction">
<div id="telFormPanel" class="modal fade" role="dialog" aria-labelledby="telFormLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="telFormLabel">
                    新建电话
                </h3>
            </div>
            <div class="modal-body">
                <form action="" method="post" class="form-horizontal" name="telForm" id="telForm">
                    <input type="hidden" name="partyId" id="partyId">

                    <div class="form-group">
                        <label class="col-sm-3 col-md-3 col-lg-2 control-label">电话</label>
                        <div class="col-sm-9 col-md-9 col-lg-10">
                            <div class="row">
                                <div class="col-md-4">
                                    <input type="text" class="form-control input-sm">
                                    <span class="help-block text-left">区号</span>
                                </div>
                                <div class="col-md-4">
                                    <input type="text" class="form-control input-sm">
                                    <span class="help-block text-center">号码</span>
                                </div>
                                <div class="col-md-4">
                                    <input type="text" class="form-control input-sm">
                                    <span class="help-block text-right">分机</span>
                                </div>
                            </div>
                        </div>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="userLoginFormSubmit" >保存</button>
                <button class="btn" id="userLoginFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

<input type="hidden" id="mobileFormAction">
<div id="mobileFormPanel" class="modal fade" role="dialog" aria-labelledby="mobileFormLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="mobileFormLabel">添加新手机号
                </h3>
            </div>
            <div class="modal-body">
                <form action="" method="post" class="form-horizontal" name="mobileForm" id="mobileForm">
                    <input type="hidden" name="partyId" id="partyId">
                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">手机</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <input   class="form-control input-sm" type="text"  class="form-control input-sm"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="userLoginFormSubmit" >保存</button>
                <button class="btn" id="userLoginFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>


<input type="hidden" id="selectConactTypeFormAction">
<div id="selectConactTypeFormPanel" class="modal fade" role="dialog" aria-labelledby="selectConactTypeFormLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="selectConactTypeFormLabel">新建联系信息
                </h3>
            </div>
            <div class="modal-body">
                <form action="" method="post" class="form-horizontal" name="selectConactTypeForm" id="selectConactTypeForm">
                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">类型</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <select id="conactType">
                                <option value="mobile"/>手机
                                <option value="tel"/>办公电话
                                <option value="email"/>电子邮件
                                <option value="address"/>公司地址
                            </select>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="selectConactTypeFormSubmit" >保存</button>
                <button class="btn" id="selectConactTypeFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>


<input type="hidden" id="userLoginFormAction">
<div id="userCertFormPanel" class="modal fade" role="dialog" aria-labelledby="userCertFormLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="userCertFormLabel">
                    关联证书
                </h3>
            </div>
            <div class="modal-body">
                <form action="" method="post" class="form-horizontal" name="userCertForm" id="userCertForm">
                    <input type="hidden" name="partyId" id="partyId">
                    <div class="form-group certcontent">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">主题</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            CN = XX,E = admin@gorun8.cn.cn
                        </div>
                    </div>

                    <div class="form-group certcontent">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">生效时间</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            2012年2月15日 20:50:53
                        </div>
                    </div>
                    <div class="form-group certcontent">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">失效时间</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            2012年2月15日 20:50:53
                        </div>
                    </div>
                    <div class="form-group certcontent">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">证书文件</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <input type="file" id="cert" name="cert"/>
                        </div>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="userCertFormSubmit" >保存</button>
                <button class="btn" id="userCertFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
</@easyfkDecoratorScreen>