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
{id:'navid2',title:'组织机构',tip:'组织机构',style:'fa fa-sitemap',href:'${ctx}/partyclsgroup/list'},
{id:'navid3',title:'导入用户'}]}
</@easyfkSetNavBar>

<@easyfkHeaderAttach>

<link rel="stylesheet" href="/static/css/font-awesome.css" />
<link rel="stylesheet" href="/static/css/icheck/flat/blue.css" />

<script src="/static/js/jquery/validate/jquery.validate.min.js"></script>
<script src="/static/js/jquery/validate/localization/messages_zh.min.js"></script>
<script src="/static/js/jquery/jquery.icheck.min.js"></script>
<script src="/static/js/validate.js"></script>

</@easyfkHeaderAttach>

<@easyfkFooterAttach>
<script src="/party/js/importparty.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
        $('input[type=checkbox],input[type=radio]').not("[data-switch-no-init]").iCheck({
            checkboxClass: 'icheckbox_flat-blue',
            radioClass: 'iradio_flat-blue'
        });
    });
</script>

</@easyfkFooterAttach>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/party/resources/page/appcommon.ftl">
<div class="row">
    <div class="col-xs-12">
        <@easyfkContextMenu  >
            {MENU_DATA:[
            {id:'',title:'返回',desc:'返回组织机构',toggle:'modal',href:'javascript:easyfk.gotoClsParty()',style:'btn btn-primary tip-bottom',style2:' fa fa-search icon-white'},
            {id:'',title:'下载模板',desc:'下载会员电子表格模板文件',toggle:'modal',href:'javascript:easyfk.exportTemplate()',style:'btn btn-primary tip-bottom',style2:' fa fa-plus-circle icon-white'},
            ]}
        </@easyfkContextMenu>
        <div class="widget-box">
            <div class="widget-title">
				<span class="icon">
					<i class="fa fa-align-justify"></i>
				</span>
                <h5>导入用户</h5>
                <#--
                <span class="label label-danger">48 notices</span>
                -->
            </div>
            <div class="widget-content nopadding">
                <form class="form-horizontal" method="post"   enctype="multipart/form-data"  action="${ctx}/party/importfromfile" name="userImportForm" id="userImportForm" novalidate="novalidate">
                    <div class="form-group">
                        <label class="col-sm-3 col-md-3 col-lg-2 control-label">文件</label>
                        <div class="col-sm-9 col-md-9 col-lg-10">
                            <input type="file" class="form-control input-sm" name="theFile" id="theFile">
                        </div>
                    </div>
                    <div class="form-actions" id="form_actions_panel" style="<#if categoryKey?has_content>display:none;</#if>">
                        <input type="submit" value="开始导入" class="btn btn-primary" >
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        var userImportRules = {usersFile:{required:true}};
        easyfk.validateAndSubmit("userImportForm",userImportRules,function(form){
            var fileVal = $("#theFile").filebox('getValue');
            if(null == fileVal || "" == $.trim(fileVal)) {
                alert('错误', "上传文件不能为空", 'error');
                return false;
            }
            form.submit();
            return false;
        });
    });
</script>

<#if categoryKey?has_content>

<div class="row">
    <div class="col-xs-12">
        <div class="widget-box">
            <div class="widget-title">
				<span class="icon">
					<i class="fa fa-align-justify"></i>
				</span>
                <h5>处理进度</h5>
            <#--
            <span class="label label-danger">48 notices</span>
            -->
            </div>
            <div class="widget-content ">
                <div class="progress progress-sm" >
                    <div style="width: 1%;" class="progress-bar" id="import_progress_bar"></div>
                </div>
                <div  style="padding:10px;overflow-y: auto;height: 450px;">

                    <div id="statusPanel"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function(){
        easyfk.getstatus();
    });

</script>
</#if>
</@easyfkDecoratorScreen>

