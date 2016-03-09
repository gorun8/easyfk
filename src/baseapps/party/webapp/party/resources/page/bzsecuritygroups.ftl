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

<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuPartyAuth','menuPartyAuth_3']}" location="component://party/webapp/party/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="业务安全组">
{NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
{id:'navid2',title:'认证授权',tip:'认证授权',href:'${ctx}/partyclsgroup/list'},
{id:'navid3',title:'业务安全组'},
]}
</@easyfkSetNavBar>

<@easyfkHeaderAttach>
<link rel="stylesheet" href="/static/css/font-awesome.css" />
<link rel="stylesheet" href="/static/css/icheck/flat/blue.css" />
<link rel="stylesheet" href="/static/css/select2.css" />
<link rel="stylesheet" href="/static/css/datepicker.css" />
<link rel="stylesheet" href="/static/css/bootstrap-switch.min.css" />
<script src="/static/js/jquery/validate/jquery.validate.min.js"></script>
<script src="/static/js/jquery/validate/localization/messages_zh.min.js"></script>

<script src="/static/js/jquery/bootstrap-datepicker.js"></script>
<script src="/static/js/jquery/bootstrap-switch.min.js"></script>
<script src="/static/js/jquery/jquery.icheck.min.js"></script>
<script src="/static/js/jquery/select2.min.js"></script>
<script src="/static/js/validate.js"></script>
<link rel="stylesheet" href="/static/js/jquery/zTree/css/metroStyle/metroStyle.css" type="text/css">

<script type="text/javascript" src="/static/js/jquery/zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="/static/js/jquery/zTree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="/static/js/jquery/zTree/js/jquery.ztree.exedit-3.5.js"></script>

</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/bzsecuritygroups.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('input[type=checkbox],input[type=radio]').not("[data-switch-no-init]").iCheck({
            checkboxClass: 'icheckbox_flat-blue',
            radioClass: 'iradio_flat-blue'
        });

        $('select').select2();
    });

</script>
</@easyfkFooterAttach>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/party/resources/page/appcommon.ftl">
<div class="row">
    <div class="col-xs-12 col-sm-12">
        <@easyfkContextMenu >
            {MENU_DATA:[
            {id:'',title:'新建安全组',desc:'新建自定义安全组',toggle:'modal',href:'javascript:easyfk.newSecurityGroup()',style:'btn btn-primary tip-bottom',style2:' icon-map-marker icon-white'},
            ]}
        </@easyfkContextMenu>
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="fa  fa-th-list"></i>
								</span>
                <h5>安全组列表</h5>
            </div>
            <div class="widget-content" style="min-height: 450px;">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th style="width:60px;">序号</th>
                        <th>安全组</th>
                        <th>类型</th>
                        <th>说明</th>
                        <th style="width:80px;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list securityGroupList as group>
                        <input type="hidden" name="securityGroup_${group.groupId}_name" id="securityGroup_${group.groupId}_name"
                               value="${group.name?default('')}" />
                        <input type="hidden" name="securityGroup_${group.groupId}_description" id="securityGroup_${group.groupId}_description"
                               value="${group.description?default('')}" />

                        <tr>
                            <td>
                                <#if "Y" == group.isSystem?default("")>
                                    <span class="badge badge-success"  >${group_index+1}</span>
                                <#else>
                                    <span class="badge badge-info"  >${group_index+1}</span>
                                </#if>
                            </td>
                            <td>
                                ${group.name?default('')}
                            </td>
                            <td>
                                <#if "Y" == group.isSystem?default("")>
                                    <span class="label label-success">系统安全组</span>
                                <#else>
                                    <span class="label label-info">业务安全组</span>
                                </#if>
                            </td>
                            <td>
                                ${group.description?default('')}
                            </td>
                            <td>
                                <div class="btn-group">
                                    <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle">操作 <span class="caret"></span></button>
                                    <ul class="dropdown-menu  pull-right">
                                        <li><a href="#" onclick="easyfk.editSecurityGroup('${group.groupId}')"><i class="fa fa-eye"></i>修改</a></li>
                            <#if "Y" != group.isSystem?default("")>
                                        <li><a href="#" onclick="easyfk.removeSecurityGroup('${group.groupId}')"><i class="fa fa-eye"></i>删除</a></li>
                            </#if>
                                        <li class="divider"></li>
                                        <li><a href="#" onclick="easyfk.setGroupPermission('${group.groupId}')"><i class="fa fa-eye"></i>资源</a></li>
                                        <li><a href="#" onclick="easyfk.showUserLogin('${group.groupId}')"><i class="fa fa-eye"></i>关联账号</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#" onclick="easyfk.showLog('${group.groupId}')"><i class="fa fa-eye"></i>关联日志</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </#list>


                    </tbody>
                </table>
            </div>
        </div>
        <@easyfkPageBar pageUrl="${ctx}/dyn/security/bzsecuritygroups?pageSize=10&pageIndex="></@easyfkPageBar>

    </div>
</div>

<script type="text/javascript">
    easyfk.showUserLogin = function(partyId){
        document.location.href = "${ctx}/page/roleuserloginlist.ftl"+partyId;
    }

    easyfk.showLog = function(){
        document.location.href = "${ctx}/page/oploglist.ftl";
    }

</script>



<div id="securityGroupFormPanel" class="modal  fade" role="dialog" aria-labelledby="securityGroupFormLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="securityGroupFormLabel">
                    修改安全组
                </h3>
            </div>
            <form action="" method="post" class="form-horizontal" name="securityGroupForm" id="securityGroupForm">
                <input type="hidden" id="securityGroupFormAction">
            <div class="modal-body">
                    <input type="hidden" name="groupId" id="groupId">
                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 control-label">安全组</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <input   class="form-control input-sm" type="text"  id="name" name="name"/><p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 control-label">说明</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <input   class="form-control input-sm" type="text"  id="description" name="description"/><p></p>
                        </div>
                    </div>
            </div>
            <div class="modal-footer">
                <input type="submit" value="${uiLabelMap.CommonSave}" class="btn btn-primary">
                <button class="btn" id="securityGroupFormClose" data-dismiss="modal" aria-hidden="true">${uiLabelMap.CommonClose}</button>
            </div>
            </form>
        </div>

    </div>
</div>

<script type="text/javascript">

    $(document).ready(function(){
        var securityGorupRules = {name:{required:true,minlength:2,maxlength:20},
            description:{required:true,minlength:2,maxlength:20}
        };
        easyfk.validateAndSubmit("securityGroupForm",securityGorupRules,function(form){
            easyfk.submitSecurityGorup(form);
            return false;
        });


    });

</script>
<style>
    #securityResourceTree .line{height: auto; }
</style>

<input type="hidden" id="groupPermissionAction">
<div id="groupPermissionPanel" class="modal  fade" role="dialog" aria-labelledby="groupPermissionLabel" aria-hidden="true">

<div class="modal-dialog">
    <div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="groupPermissionLabel">
            权限资源
        </h3>
    </div>
    <form action="" method="post" class="form-horizontal" name="groupPermissionForm" id="groupPermissionForm">
        <input type="hidden" name="groupId" id="groupId">
        <input type="hidden" name="selectedPermissions" id="selectedPermissions">

        <div class="modal-body">
        <div  style222="overflow-y: auto;height: 250px; ">
            <ul id="securityResourceTree" class="ztree"></ul>

        </div>

    </div>
    <div class="modal-footer">
        <input type="submit" value="${uiLabelMap.CommonSave}" id="securityGroupFormSubmit" class="btn btn-primary">
        <button class="btn" id="securityGroupFormClose" data-dismiss="modal" aria-hidden="true">${uiLabelMap.CommonClose}</button>
    </div>
    </form>
</div>
</div>
</div>

<script type="text/javascript">

    easyfk.setGroupPermission = function (groupId) {
        easyfk.renderGroupPermissionTree("securityResourceTree",groupId,function(){
            if("FULLADMIN" == groupId){
                $("#securityGroupFormSubmit").hide();
                $("#groupPermissionAction").val("");
                $("#groupPermissionForm").find("#groupId").val("");
            }else{
                $("#securityGroupFormSubmit").show();
                $("#groupPermissionAction").val("${ctx}/dyn/security/setGroupPermission");
                $("#groupPermissionForm").find("#groupId").val(groupId);
            }
            $("#groupPermissionPanel").modal("show");
        });
    }

    $(document).ready(function(){
        var groupPermissionRules = {};
        easyfk.validateAndSubmit("groupPermissionForm",groupPermissionRules,function(form){
            easyfk.submitGroupPermission("securityResourceTree");

            return false;
        });

    });

</script>

</@easyfkDecoratorScreen>










