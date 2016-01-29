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

<div id="partycontact_po"></div>
<div class="row">
<div class="col-xs-12 col-sm-12">
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
                    <th>状态</th>
                    <th style="width:80px;">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list partyContactMechList as partyContact>
                    <#if partyContact.inValidate?has_content>
                    <tr>
                        <td>
                            <span class="badge badge-warning">${uiLabelMap[partyContact.contactMechTypeId]}</span>
                        </td>
                        <td>
                             <i   title="${uiLabelMap.CommonInvalidate}" class="tip-bottom" style="color:#f89406;"> ${partyContact.infoString} </i>
                        </td>
                        <td>
                             <i   title="${uiLabelMap.CommonInvalidate}" class="tip-bottom" style="color:#f89406;"> ${uiLabelMap.CommonInvalidate} </i>
                        </td>
                        <td>
                            <div class="btn-group">
                                <a href="#" data-toggle="dropdown" class="btn btn-primary dropdown-toggle"> 操作 <span class="caret"></span></a>
                                <ul class="dropdown-menu dropdown-primary pull-right">
                                    <li><a href="#" onclick="easyfk.removeUserLogin()">启用</a></li>
                                    <li><a href="#" onclick="easyfk.newContactDialog('${partyContact.contactMechTypeId}')">新建</a></li>
                                    <li><a href="#" onclick="easyfk.editContactDialog('${partyContact.contactMechTypeId}','${partyContact.contactMechId}')">编辑</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <#else>
                    <tr>
                        <td>
                                <span class="badge badge-success">${uiLabelMap[partyContact.contactMechTypeId]}</span>
                        </td>
                        <td>
                                <i   title="${uiLabelMap.CommonValidate}" class="tip-bottom" style="color:#468847;"> ${partyContact.infoString} </i>
                        </td>
                        <td>
                                <i   title="${uiLabelMap.CommonValidate}" class="tip-bottom" style="color:#468847;">${uiLabelMap.CommonValidate} </i>
                        </td>
                        <td>
                            <div class="btn-group">
                                <a href="#" data-toggle="dropdown" class="btn btn-primary dropdown-toggle"> 操作 <span class="caret"></span></a>
                                <ul class="dropdown-menu dropdown-primary pull-right">
                                    <li><a href="#" onclick="easyfk.removeUserLogin()">停用</a></li>
                                    <li><a href="#" onclick="easyfk.newContactDialog('${partyContact.contactMechTypeId}')">新建</a></li>
                                    <li><a href="#" onclick="easyfk.editContactDialog('${partyContact.contactMechTypeId}','${partyContact.contactMechId}')">编辑</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </#if>
                </#list>
               </tbody>
            </table>
        </div>
    </div>
</div>
</div>

<script type="text/javascript">
    easyfk.newAddress = function(){
        var param = {};
        easyfk.showDailog("addressFormPanel",param);
    }

    easyfk.newEmail = function(){
        var param = {"emailAddress":""};
        easyfk.showDailog("emailFormPanel",param);
    }

    easyfk.newTel = function(){
        var param = {};
        easyfk.showDailog("telFormPanel",param);
    }
    easyfk.newMobile = function(){
        var param = {};
        easyfk.showDailog("mobileFormPanel",param);
    }

    easyfk.selectConactType = function(){
        var param = {};
        easyfk.showDailog("selectConactTypeFormPanel",param);
    }

    easyfk.newContactDialog = function(v){
        if(v == "mobile"){
            easyfk.newMobile();
        }else if(v =="TELECOM_NUMBER"){
            easyfk.newTel();
        }else if(v =="EMAIL_ADDRESS"){
            easyfk.newEmail();
        }else if(v =="POSTAL_ADDRESS"){
            easyfk.newAddress();
        }
    }

    easyfk.editContactDialog = function(v){
        if(v == "mobile"){
            easyfk.newMobile();
        }else if(v =="TELECOM_NUMBER"){
            easyfk.newTel();
        }else if(v =="EMAIL_ADDRESS"){
            easyfk.newEmail();
        }else if(v =="POSTAL_ADDRESS"){
            easyfk.newAddress();
        }
    }


    $(function(){
        $("#selectConactTypeFormSubmit").click(function(){
            $("#selectConactTypeFormPanel").modal("hide");
            var v = $("#conactType").val();
            easyfk.newContactDialog(v);
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
                <form action="" method="post" class="form-horizontal" name="addressForm" id="addressForm">
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
                <button class="btn btn-primary" id="addressFormSubmit" >保存</button>
                <button class="btn" id="addressFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>


<div id="emailFormPanel" class="modal fade" role="dialog" aria-labelledby="emailFormLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="emailFormLabel">
                    邮件地址
                </h3>
            </div>
            <form action="" method="post" class="form-horizontal" name="emailForm" id="emailForm">
            <div class="modal-body">
                   <input type="hidden" name="partyId"  value="${currnetPartyId?default('')}">
                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">邮件地址</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <input    name="emailAddress" id="emailAddress" type="text"  class="form-control input-sm"/>
                        </div>
                    </div>

            </div>
            <div class="modal-footer">
                <input type="submit" value="${uiLabelMap.CommonSave}" class="btn btn-primary">
                <button class="btn" id="emailFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    easyfk.submitEmailForm = function(form){
        if(easyfk.isFormSubmited("emailForm")){
            return ;
        }

        var ajaxUrl = "${ctx}/dyn/contact/createEmail";
        var postparams =$("#emailForm").serialize();
        easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
            easyfk.resetFormSubmitStatus("emailForm");
            if(type){
                $("#emailFormPanel").modal("hide");
                easyfk.refreshSelPortlet("partycontact_po","${ctx}/dyn/portal/partycontact?currnetPartyId=${currnetPartyId}");
            }else{
                alert(msg);
            }
        });
        return false;
    }

    $(document).ready(function(){
        var eamilRules = {emailAddress:{required:true,email: true}};
        easyfk.validateAndSubmit("emailForm",eamilRules,function(form){
            easyfk.submitEmailForm(form);
            return false;
        });
    });
</script>



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
                <button class="btn btn-primary" id="telFormSubmit" >保存</button>
                <button class="btn" id="telFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
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
                <button class="btn btn-primary" id="mobileFormSubmit" >保存</button>
                <button class="btn" id="mobileFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
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
                    <input type="hidden" id="partyId" name="partyId" value="${currnetPartyId}">
                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">类型</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <select id="conactType">
                                <option value="TELECOM_NUMBER">手机</option>
                                <option value="TELECOM_NUMBER">办公电话</option>
                                <option value="EMAIL_ADDRESS">电子邮件</option>
                                <option value="POSTAL_ADDRESS">公司地址</option>
                            </select>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <input type="submit" value="${uiLabelMap.CommonSave}" class="btn btn-primary">
                <button class="btn" id="selectConactTypeFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>