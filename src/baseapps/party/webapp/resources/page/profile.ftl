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

<@easyfkSetNavBar subTitle="我的信息">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'组织机构',tip:'管理组织机构',href:'${ctx}/partyclsgroup/list'},
    {id:'navid3',title:'我的信息'},
 ]}
</@easyfkSetNavBar>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/resources/page/appcommon.ftl">


<div class="row-fluid">
    <div class="span12">
            <div class="row-fluid">
                <div class="span6">
                    <div class="widget-box">
                        <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
                            <h5>简介</h5>
                            <button class="btn btn-info btn-mini" onclick="easyfk.editUserInfo()" style="float: right;margin: 9px 15px 0 0;">编辑</button>
                        </div>
                        <div class="widget-content">
                            这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....这是关于这个会员的简要介绍.....
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="widget-box">
                        <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
                            <h5>联系信息</h5>
                            <button class="btn btn-info btn-mini" onclick="easyfk.selectConactType();" style="float: right;margin: 9px 15px 0 0;">添加</button>

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
                                            <button class="btn btn-mini">操作</button>
                                            <button data-toggle="dropdown" class="btn   btn-mini dropdown-toggle"><span class="caret"></span></button>
                                            <ul class="dropdown-menu pull-right">
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
                                            <button class="btn btn-mini">操作</button>
                                            <button data-toggle="dropdown" class="btn   btn-mini dropdown-toggle"><span class="caret"></span></button>
                                            <ul class="dropdown-menu pull-right">
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
                                            <button class="btn  btn-mini">操作</button>
                                            <button data-toggle="dropdown" class="btn  btn-mini dropdown-toggle"><span class="caret"></span></button>
                                            <ul class="dropdown-menu pull-right">
                                                <li><a href="#" onclick="easyfk.removeUserLogin()">停用</a></li>
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
                                            <button class="btn  btn-mini">操作</button>
                                            <button data-toggle="dropdown" class="btn  btn-mini dropdown-toggle"><span class="caret"></span></button>
                                            <ul class="dropdown-menu pull-right">
                                                <li><a href="#" onclick="easyfk.removeUserLogin()">启用</a></li>
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
                                            <button class="btn  btn-mini">操作</button>
                                            <button data-toggle="dropdown" class="btn  btn-mini dropdown-toggle"><span class="caret"></span></button>
                                            <ul class="dropdown-menu pull-right">
                                                <li><a href="#" onclick="easyfk.removeUserLogin()">启用</a></li>
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



<div class="row-fluid">
    <div class="span12">
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
                <h5>登录账号</h5>

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

                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>

                        <td>
                            <span class="badge badge-success">1</span>
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
                                <button class="btn  btn-mini">操作</button>
                                <button data-toggle="dropdown" class="btn  btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu pull-right">
                                    <li><a href="#" onclick="easyfk.changePassword()">修改</a></li>
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

</@easyfkDecoratorScreen>
<script type="text/javascript">
    easyfk.noimplement= function(){
        alert("该功能还未实现");
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
        $("#userLoginFormLabel").html("修改密码");
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

    });

</script>

<input type="hidden" id="userLoginFormAction">
<div id="userLoginFormPanel" class="modal fade" role="dialog" aria-labelledby="userLoginFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="userLoginFormLabel">
            新建登录账号
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="userLoginForm" id="userLoginForm">
            <input type="hidden" name="partyId" id="partyId">
            <div class="control-group">
                <label class="control-label">帐号</label>
                <div class="controls">
                    <input type="text" id="userLoginId" name="userLoginId"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">角色类型</label>
                <div class="controls">系统管理员

                </div>
            </div>

            <div class="control-group">
                <label class="control-label">${uiLabelMap.CommonPassword}</label>
                <div class="controls">
                    <input type="password" id="password" name="password"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">${uiLabelMap.CommonPasswordConfirm}</label>
                <div class="controls">
                    <input type="password" id="password2" name="password2"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">密码提示</label>
                <div class="controls">
                    <input type="text" id="password2" name="password2"/>
                </div>
            </div>

        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="userLoginFormSubmit" >保存</button>
        <button class="btn" id="userLoginFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>


<input type="hidden" id="userInfoFormAction">
<div id="userInfoFormPanel" class="modal fade" role="dialog" aria-labelledby="userInfoFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="userInfoFormLabel">
            编辑个人简介
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="userInfoForm" id="userInfoForm">
            <input type="hidden" name="partyId" id="partyId">
            <div class="control-group">
               <textarea style="width: 515px;">这是关于这个会员的简要介绍.....</textarea>
            </div>

        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="userLoginFormSubmit" >保存</button>
        <button class="btn" id="userLoginFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
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
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="addressFormLabel">
            地址信息
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="userLoginForm" id="userLoginForm">
            <input type="hidden" name="partyId" id="partyId">
            <div class="control-group">
                <label class="control-label">国家</label>
                <div class="controls">
                    <input type="text" id="userLoginId" name="userLoginId"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">省/市</label>
                <div class="controls">
                    <input type="text" id="userLoginId" name="userLoginId"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">县/区</label>
                <div class="controls">
                    <input type="text" id="userLoginId" name="userLoginId"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">街道</label>
                <div class="controls">
                    <input type="text" id="userLoginId" name="userLoginId"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">邮编</label>
                <div class="controls">
                    <input type="text" id="userLoginId" name="userLoginId"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="userLoginFormSubmit" >保存</button>
        <button class="btn" id="userLoginFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>

<input type="hidden" id="emailFormAction">
<div id="emailFormPanel" class="modal fade" role="dialog" aria-labelledby="emailFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="emailFormLabel">
            邮件地址
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="emailForm" id="emailForm">
            <input type="hidden" name="partyId" id="partyId">
            <div class="control-group">
                <label class="control-label">邮件地址</label>
                <div class="controls">
                    <input type="text" id="userLoginId" name="userLoginId"/>
                </div>
            </div>
         </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="userLoginFormSubmit" >保存</button>
        <button class="btn" id="userLoginFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>

<input type="hidden" id="telFormAction">
<div id="telFormPanel" class="modal fade" role="dialog" aria-labelledby="telFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="telFormLabel">
            新建电话
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="telForm" id="telForm">
            <input type="hidden" name="partyId" id="partyId">
            <div class="control-group">
                <label class="control-label">区号</label>
                <div class="controls">
                    <input type="text"  style="width:30px;"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">号码</label>
                <div class="controls">
                    <input type="password" id="password" name="password"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">分机</label>
                <div class="controls">
                    <input type="password" id="password2" name="password2"/>
                </div>
            </div>

        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="userLoginFormSubmit" >保存</button>
        <button class="btn" id="userLoginFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>

<input type="hidden" id="mobileFormAction">
<div id="mobileFormPanel" class="modal fade" role="dialog" aria-labelledby="mobileFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="mobileFormLabel">添加新手机号
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="mobileForm" id="mobileForm">
            <input type="hidden" name="partyId" id="partyId">
            <div class="control-group">
                <label class="control-label">手机</label>
                <div class="controls">
                    <input type="text" id="userLoginId" name="userLoginId"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="userLoginFormSubmit" >保存</button>
        <button class="btn" id="userLoginFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>


<input type="hidden" id="selectConactTypeFormAction">
<div id="selectConactTypeFormPanel" class="modal fade" role="dialog" aria-labelledby="selectConactTypeFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="selectConactTypeFormLabel">新建联系信息
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="selectConactTypeForm" id="selectConactTypeForm">
            <div class="control-group">
                <label class="control-label">类型</label>
                <div class="controls">
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


<input type="hidden" id="userLoginFormAction">
<div id="userCertFormPanel" class="modal fade" role="dialog" aria-labelledby="userCertFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="userCertFormLabel">
            关联证书
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="userCertForm" id="userCertForm">
            <input type="hidden" name="partyId" id="partyId">
            <div class="control-group certcontent">
                <label class="control-label">主题</label>
                <div class="controls">
                    CN = XX,E = admin@gorun8.cn.cn
                </div>
            </div>
            <div class="control-group certcontent">
                <label class="control-label">生效时间</label>
                <div class="controls">
                    2012年2月15日 20:50:53
                </div>
            </div>
            <div class="control-group certcontent">
                <label class="control-label">失效时间</label>
                <div class="controls">
                    2017年2月13日 20:50:53
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">证书文件</label>
                <div class="controls">
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
