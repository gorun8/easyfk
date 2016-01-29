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
<div id="partyuserlogin_po"></div>
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

                    <#list userLoginList as item>
                        <#assign colorcss ="badge-success"/>
                        <#if item.enabled ?has_content  && "N" == item.enabled>
                            <#assign colorcss ="badge-warning"/>
                        </#if>
                        <tr>
                            <td>
                                <span class="badge ${colorcss}"  >${item_index}</span>
                            </td>
                            <td>
                                  ${item.userLoginId?default("")}
                            </td>
                            <td>
                                <span class="badge ${colorcss}"  >
                                <#if item.enabled ?has_content  && "N" == item.enabled>
                                    停用
                                    <#else>
                                    正常
                                </#if>
                                </span>
                            </td>
                            <td>

                            </td>
                            <td>
                                密码
                            </td>

                            <td>
                                <div class="btn-group">
                                    <a href="#" data-toggle="dropdown" class="btn btn-primary dropdown-toggle"> 操作 <span class="caret"></span></a>
                                    <ul class="dropdown-menu dropdown-primary pull-right">
                                        <li><a href="#" onclick="easyfk.editUserLogin('${item.userLoginId?default("")}')">修改</a></li>
                                       <#if item.enabled ?has_content  && "N" == item.enabled>
                                           <li><a href="#" onclick="easyfk.changeStatus('确定要启用${item.userLoginId?default("")}吗？','${item.userLoginId?default("")}','Y')">启用</a></li>
                                        <#else>
                                           <li><a href="#" onclick="easyfk.changeStatus('确定要停用${item.userLoginId?default("")}吗？','${item.userLoginId?default("")}','N')">停用</a></li>
                                       </#if>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">


easyfk.doChangeStatus = function(userLoginId,status){
    var ajaxUrl = "${ctx}/dyn/party/disableUserLogin";
    if(status =="Y"){
        ajaxUrl = "${ctx}/dyn/party/enableUserLogin";
    }
    var postparams ={userLoginId:userLoginId,partyId:"${currnetPartyId}"};
    easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
        if(type){
            easyfk.refreshSelPortlet("partyuserlogin_po","${ctx}/dyn/portal/partyuserlogin?currnetPartyId=${currnetPartyId}");
        }else{
            alert(msg);
        }
    });
}

easyfk.changeStatus = function(msg,userLoginId,status){
    easyfk.confirm(msg,function(rel){
        if(rel){
            easyfk.doChangeStatus(userLoginId,status)
        }
    });
}


</script>

<div id="userLoginFormPanel" class="modal fade" role="dialog" aria-labelledby="userLoginFormLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="userLoginFormLabel">
                    新建登录账号
                </h3>
            </div>
            <form action="" method="post" class="form-horizontal" name="userLoginForm" id="userLoginForm">
                <input type="hidden" id="userLoginFormAction">
                <div class="modal-body">
                    <input type="hidden" name="partyId" id="partyId" value="${currnetPartyId}">
                    <input type="hidden" name="requirePasswordChange" id="requirePasswordChange" value="Y">

                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">帐号</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <input   class="form-control input-sm" type="text"  id="userLoginId" name="userLoginId" class="form-control input-sm"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">角色类型</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <select   multiple="" >
                                <option value="">一般用户</option>
                                <option value="">系统管理员</option>
                                <option value="">系统安全员</option>
                                <option value="">安全保密管理员</option>
                                <option value="">自定义角色XX</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">${uiLabelMap.CommonPassword}</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <input   class="form-control input-sm" type="password" id="currentPassword" name="currentPassword" class="form-control input-sm"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">${uiLabelMap.CommonPasswordConfirm}</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <input   class="form-control input-sm" type="password" id="password2" name="password2" class="form-control input-sm"/>
                        </div>
                    </div>
            </div>
            <div class="modal-footer">
                <input type="submit" value="${uiLabelMap.CommonSave}" class="btn btn-primary">
                <button class="btn" id="userLoginFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    easyfk.newUserLogin = function () {
        $("#userLoginFormLabel").html("新建登录账号");
        $("#userLoginForm").find("#userLoginId").removeAttr("readonly");

        var param = {userLoginId:'',userLoginFormAction:'${ctx}/dyn/party/createUserLogin'};
        easyfk.showDailog("userLoginFormPanel",param);
    }

    easyfk.editUserLogin = function (userLoginId) {
        $("#userLoginFormLabel").html("编辑登录账号");
        $("#userLoginForm").find("#userLoginId").attr("readonly","true");

        var param = {userLoginId :userLoginId,userLoginFormAction:'${ctx}/dyn/party/saveUserLogin'};
        easyfk.showDailog("userLoginFormPanel",param);
    }

    easyfk.submitUserLogin = function(form){
        if(easyfk.isFormSubmited("userLoginForm")){
            return ;
        }
        var ajaxUrl = $("#userLoginForm").find("#userLoginFormAction").val();

        var postparams =$("#userLoginForm").serialize();
        easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
            easyfk.resetFormSubmitStatus("userLoginForm");
            if(type){
               $("#userLoginFormPanel").modal("hide");
                easyfk.refreshSelPortlet("partyuserlogin_po","${ctx}/dyn/portal/partyuserlogin?currnetPartyId=${currnetPartyId}");
            }else{
                alert(msg);
            }
        });
        return false;
    }


    $(document).ready(function(){
        //$('select').select2();
        var userLoginRules = {userLoginId:{required:true,minlength:5,maxlength:50},
            currentPassword:{required:true,minlength:6,maxlength:20},
            password2:{required:true,minlength:6,maxlength:20,equalTo:"#currentPassword"}
        };

        easyfk.validateAndSubmit("userLoginForm",userLoginRules,function(form){
            easyfk.submitUserLogin(form);
            return false;
        });
    });
</script>

