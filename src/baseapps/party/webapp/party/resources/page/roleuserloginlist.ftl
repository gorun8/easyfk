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

<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuPartyAuth','menuPartyAuth_2']}" location="component://party/webapp/party/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="角色关联账号">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'认证授权',tip:'认证授权',href:'${ctx}/partyclsgroup/list'},
    {id:'navid3',title:'角色权限',tip:'角色权限',href:'${ctx}/partyclsgroup/list'},
    {id:'navid4',title:'角色关联账号'},
 ]}
</@easyfkSetNavBar>

<@easyfkHeaderAttach>
<#--
<link rel="stylesheet" href="/static/css/datepicker.css" />
-->
<link rel="stylesheet" href="/static/css/uniform.css" />
<#--
<link rel="stylesheet" href="/static/css/select2.css" />
<script src="/static/js/jquery/bootstrap-datepicker.js"></script>
-->
<script src="/static/js/jquery/validate/jquery.validate.min.js"></script>
<script src="/static/js/jquery/validate/localization/messages_zh.min.js"></script>

<script src="/static/js/jquery/jquery.uniform.js"></script>
<#--
<script src="/static/js/jquery/select2.min.js"></script>
-->
</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/roleuserloginlist.js"></script>
</@easyfkFooterAttach>


<@easyfkDecoratorScreen name="body" location="component://party/webapp/party/resources/page/appcommon.ftl">
<div class="row-fluid">
    <div class="span12">
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
                <h5>与XX角色关联的账号</h5>

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
                        <th>会员姓名</th>
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
                            张三
                        </td>
                        <td>
                            <div class="btn-group">
                                <button class="btn btn-mini">操作</button>
                                <button data-toggle="dropdown" class="btn   btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu pull-right">
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
                            王五
                        </td>
                        <td>
                            <div class="btn-group">
                                <button class="btn  btn-mini">操作</button>
                                <button data-toggle="dropdown" class="btn  btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu pull-right">
                                    <li><a href="#" onclick="easyfk.partydetial(1)">会员详情</a></li>

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
                            李四
                        </td>
                        <td>
                            <div class="btn-group">
                                <button class="btn  btn-mini">操作</button>
                                <button data-toggle="dropdown" class="btn  btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu pull-right">
                                    <li><a href="#" onclick="easyfk.partydetial(1)">会员详情</a></li>

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

    easyfk.partydetial = function(partyId){
        document.location.href = "${ctx}/party/partydetial?id="+partyId;
    }

    easyfk.noimplement= function(){
        alert("该功能还未实现");
    }

    easyfk.newUserLogin = function () {
        $("#userLoginFormAction").val("${ctx}/party/createuserlogin");
        $("#userLoginFormLabel").html("新建登录账号");
        $("#userLoginId").val("");
        $("#userLoginId").removeAttr("readonly");
        $("#userLoginFormPanel").modal("show");
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

    });

</script>

<input type="hidden" id="userLoginFormAction">
<div id="userLoginFormPanel" class="modal   fade" role="dialog" aria-labelledby="userLoginFormLabel" aria-hidden="true">
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
                <div class="controls">
                    <select   multiple="">
                        <option value=""/>一般用户
                        <option value=""/>系统管理员
                        <option value=""/>系统安全员
                        <option value=""/>安全保密管理员
                        <option value=""/>自定义角色XX
                    </select>
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

        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="userLoginFormSubmit" >保存</button>
        <button class="btn" id="userLoginFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>




<input type="hidden" id="userLoginFormAction">
<div id="userCertFormPanel" class="modal   fade" role="dialog" aria-labelledby="userCertFormLabel" aria-hidden="true">
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
</@easyfkDecoratorScreen>








