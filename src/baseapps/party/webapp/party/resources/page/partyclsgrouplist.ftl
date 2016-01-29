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
<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuPartygpgs','menuPartygpgs1']}" location="component://party/webapp/party/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="组织机构">
{NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'fa fa-home',href:'${ctx}'},
{id:'navid2',title:'组织机构'}]}
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


<style>
    #partyClsGroupTree .line{height: auto; }
</style>
</@easyfkHeaderAttach>

<@easyfkFooterAttach>
<script src="/party/js/partyclsgrouplist.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('input[type=checkbox],input[type=radio]').not("[data-switch-no-init]").iCheck({
            checkboxClass: 'icheckbox_flat-blue',
            radioClass: 'iradio_flat-blue'
        });
        $('select').select2();
        easyfk.loadPartyClsGroupTree();
    });
</script>

</@easyfkFooterAttach>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/party/resources/page/appcommon.ftl">

<div class="row">
    <div class="col-xs-12 col-sm-6">
        <form   method="post" class="form-horizontal" name="partyClsSearchForm" id="partyClsSearchForm" >
            <div class="input-group nopadding col-xs-12 col-sm-12">
                 <input type="text" class="form-control" id="partyClsName" name="partyClsName">
                 <span class="input-group-btn">
                     <button class="btn btn-primary" type="button" onclick="easyfk.submitSearchNode();"><i class="fa fa-search"></i>查询</button>
                 </span>
                <button class="btn btn-primary tip-bottom" onclick="easyfk.importPerson();return false;" style="margin-left:5px;" data-original-title="从电子表格导入个人会员"><i class=" fa fa-plus-circle icon-white"></i>导入会员</button>
            </div>

        </form>
        <div>
            <div style="overflow-y: auto;height: 550px;" id="partyclstreecontainer">
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6">
        <div id="partylistpanel">
            <@easyfkContextMenu  >
                {MENU_DATA:[
                {id:'',title:'查询',desc:'查询会员',toggle:'modal',href:'javascript:easyfk.searchParty()',style:'btn btn-primary tip-bottom',style2:' fa fa-search icon-white'},
                {id:'',title:'新建个人',desc:'新建会员',toggle:'modal',href:'javascript:easyfk.newPerson()',style:'btn btn-primary tip-bottom',style2:' fa fa-plus-circle icon-white'},
            <#--
                {id:'',title:'导入会员',desc:'从电子表格导入个人会员',toggle:'modal',href:'javascript:easyfk.importPerson()',style:'btn btn-primary tip-bottom',style2:' fa fa-plus-circle icon-white'},
                {id:'',title:'新建团体',desc:'新建团体会员',toggle:'modal',href:'javascript:easyfk.newGroup()',style:'btn btn-primary tip-bottom',style2:' fa fa-plus-circle icon-white'},
             -->
                ]}
            </@easyfkContextMenu>

            <div id="listdatacontainer"></div>
        </div>
    </div>
</div>


<input type="hidden"   id="currentSelectedGroupId" value="_NA_">

<div id="partyClsGroupFormDailog" class="modal fade" role="dialog" aria-labelledby="partyClsGroupFormLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="partyClsGroupFormLabel">
                     新建组织机构
                </h3>
            </div>
            <form action="/party/partyclsgroup/search" method="post" class="form-horizontal" name="partyClsGroupForm" id="partyClsGroupForm">
                <input type="hidden"   id="partyClsGroupFormAction" value="">
                <div class="modal-body">
                    <input type="hidden" name="partyClassificationGroupId" id="partyClassificationGroupId">
                    <input type="hidden" name="partyClassificationTypeId" id="partyClassificationTypeId" value="ORGANIZATION_CLASSIF">
                    <input type="hidden" name="parentGroupId" id="parentGroupId" value="_NA_">
                        <div class="form-group">
                            <label class="col-sm-4 col-md-4 col-lg-3 col-sm-4 col-md-4 col-lg-3 control-label">${uiLabelMap.GroupName}</label>
                            <div class="col-sm-8 col-md-8 col-lg-9">
                                <input   class="form-control input-sm" type="text"  id="description" name="description" class="form-control input-sm"/>
                            </div>
                        </div>
                </div>
                <div class="modal-footer">
                    <input type="submit" value="${uiLabelMap.CommonSave}" class="btn btn-primary">
                    <button class="btn" id="partyClsGroupFormClose" data-dismiss="modal" aria-hidden="true">${uiLabelMap.CommonClose}</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        var partyClsRules = {description:{required:true,minlength:2,maxlength:20}
        };
        easyfk.validateAndSubmit("partyClsGroupForm",partyClsRules,function(form){
            easyfk.submitOrgNode(form);
            return false;
        });
    });
</script>


<#--search party begin-->
<div id="partySearchFormDailog" class="modal  fade" role="dialog" aria-labelledby="partySearchFormLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="partySearchFormLabel">
                    查询会员
                </h3>
            </div>
            <form action="/party/partyclsgroup/list" method="post" class="form-horizontal" name="partySearchForm" id="partySearchForm" >
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 control-label">会员名称</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <input   class="form-control input-sm" type="text"  id="description" name="description"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 col-md-4 col-lg-3 control-label">类型</label>
                        <div class="col-sm-8 col-md-8 col-lg-9">
                            <select>
                                <option value="PERSON">个人会员</option>
                                <option value="PARTY_GROUP">团体会员</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <input type="submit" value="搜索" class="btn btn-primary">
                    <button class="btn" id="partySearchFormClose" data-dismiss="modal" aria-hidden="true">${uiLabelMap.CommonClose}</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        var searchPartyRules = {description:{required:true,minlength:2,maxlength:20}
        };
        easyfk.validate("partySearchForm",searchPartyRules);
    });
</script>
<#--search party end-->


<div id="partyPersonFormPanel" class="modal  fade" role="dialog" aria-labelledby="partyPersonFormLabel" aria-hidden="true">
<div class="modal-dialog">
    <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="partyPersonFormLabel">
                    ${uiLabelMap.newPerson}
                </h3>
            </div>
            <form action="" method="post" class="form-horizontal" name="partyPersonForm" id="partyPersonForm">
                <input type="hidden" id="partyPersonFormAction">
                <div class="modal-body">
                        <input type="hidden" name="partyTypeId" id="partyTypeId" value="PERSON">
                        <input type="hidden" name="preferredCurrencyUomId" id="preferredCurrencyUomId" value="CNY">
                        <input    type="hidden"  name="partyClassificationGroupId" id="personClassificationGroupId" >

                        <div class="form-group">
                            <label class="col-sm-4 col-md-4 col-lg-3 control-label">${uiLabelMap.partyId}</label>
                            <div class="col-sm-8 col-md-8 col-lg-9">
                                 <input   class="form-control input-sm" type="text"  id="partyId" name="partyId"/><p></p>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 col-md-4 col-lg-3 control-label">${uiLabelMap.personalName}</label>
                            <div class="col-sm-8 col-md-8 col-lg-9">
                                <input   class="form-control input-sm" type="text"  id="firstName" name="firstName"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 col-md-4 col-lg-3 control-label">${uiLabelMap.gender}</label>
                            <div class="col-sm-8 col-md-8 col-lg-9">
                                <label><input type="radio" name="gender" id="gender" value="M"/> ${uiLabelMap.CommonMale}</label>
                                <label><input type="radio" name="gender" id="gender" value="F"/> ${uiLabelMap.CommonFemale}</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 col-md-4 col-lg-3 control-label">${uiLabelMap.CommonRoleType}</label>
                            <div class="col-sm-8 col-md-8 col-lg-9">
                                <select   multiple="" id="roleTypeId" name="roleTypeId">
                                    <#list partyRoleTypeList as roleType>
                                        <option value="${roleType.roleTypeId}">${roleType.description}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 col-md-4 col-lg-3 control-label">${uiLabelMap.externalId}</label>
                            <div class="col-sm-8 col-md-8 col-lg-9">
                                <input   class="form-control input-sm" type="text"  id="externalId" name="externalId"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 col-md-4 col-lg-3 control-label">${uiLabelMap.CommonDescription}</label>
                            <div class="col-sm-8 col-md-8 col-lg-9">
                                <input   class="form-control input-sm" type="text"  id="description" name="description"/>
                            </div>
                        </div>
                </div>
                <div class="modal-footer">
                    <input type="submit" value="${uiLabelMap.CommonSave}" class="btn btn-primary">
                    <button class="btn" id="partyPersonFormClose" data-dismiss="modal" aria-hidden="true">${uiLabelMap.CommonClose}</button>
                </div>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function(){
        var personRules = {partyId:{minlength:5,maxlength:50},
            firstName:{required:true,minlength:2,maxlength:20},
            gender:{required:true},
            roleTypeId:{required:true},
            description:{minlength:2,maxlength:20},
            externalId:{minlength:2,maxlength:50}
        };
        easyfk.validateAndSubmit("partyPersonForm",personRules,function(form){
            easyfk.submitPerson(form);
            return false;
        });
    });
</script>


<div id="partyGroupFormPanel" class="modal  fade" role="dialog" aria-labelledby="partyGroupFormLabel" aria-hidden="true">
<div class="modal-dialog">
    <div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="partyGroupFormLabel">
            新建团体会员
        </h3>
    </div>
    <form action="" method="post" class="form-horizontal" name="partyGroupForm" id="partyGroupForm">
        <input type="hidden" id="partyGroupFormAction">
        <div class="modal-body">
            <input type="hidden" name="partyId" id="partyId">
            <input type="hidden" name="partyClassificationTypeId" id="partyClassificationTypeId" value="PARTY_GROUP">
            <input type="hidden" name="groupNameLocal" id="groupNameLocal" value="zh_CN">

            <div class="form-group">
                <label class="col-sm-4 col-md-4 col-lg-3 control-label">${uiLabelMap.PartyGroupName}</label>
                <div class="col-sm-8 col-md-8 col-lg-9">
                    <input   class="form-control input-sm" type="text"  id="groupName" name="groupName"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 col-md-4 col-lg-3 control-label">${uiLabelMap.CommonDescription}</label>
                <div class="col-sm-8 col-md-8 col-lg-9">
                    <input   class="form-control input-sm" type="text"  id="comments" name="comments"/>
                </div>
            </div>

        </div>
        <div class="modal-footer">
            <input type="submit" value="${uiLabelMap.CommonSave}" class="btn btn-primary">
            <button class="btn" id="partyGroupFormClose" data-dismiss="modal" aria-hidden="true">${uiLabelMap.CommonClose}</button>
        </div>
    </form>
</div>
    </div>
    </div>

<script type="text/javascript">
    $(document).ready(function(){
        var partyGroupRules = {groupName:{required:true,minlength:2,maxlength:20},
            comments:{required:true,minlength:2,maxlength:20}
        };
        easyfk.validateAndSubmit("partyGroupForm",partyGroupRules,function(form){
            easyfk.submitPartyGroup(form);
            return false;
        });
    });
</script>

</@easyfkDecoratorScreen>

