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

<@easyfkSetNavBar subTitle="资源列表">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'权限管理',tip:'认证授权',href:'${ctx}/partyclsgroup/list'},
    {id:'navid3',title:'权限资源'},
 ]}
</@easyfkSetNavBar>

<@easyfkHeaderAttach>
<#--
<link rel="stylesheet" href="/static/css/datepicker.css" />
<link rel="stylesheet" href="/static/css/uniform.css" />
<link rel="stylesheet" href="/static/css/select2.css" />
<script src="/static/js/jquery/bootstrap-datepicker.js"></script>
-->
<script src="/static/js/jquery/validate/jquery.validate.min.js"></script>
<script src="/static/js/jquery/validate/localization/messages_zh.min.js"></script>


</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/resourcelist.js"></script>
</@easyfkFooterAttach>


<@easyfkDecoratorScreen name="body" location="component://party/webapp/party/resources/page/appcommon.ftl">
<div class="row">
    <div class="col-xs-12 col-sm-12">
        <@easyfkContextMenu >
            {MENU_DATA:[
            {id:'',title:'扫描资源',desc:'重新扫描系统的权限资源',toggle:'modal',href:'javascript:easyfk.scanResource()',style:'btn btn-primary tip-bottom',style2:' icon-map-marker icon-white'},
            ]}
        </@easyfkContextMenu>
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="fa  fa-th-list"></i>
								</span>
                <h5>角色列表</h5>
            </div>
            <div class="widget-content" style="min-height: 450px;">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th style="width:80px;">资源标识</th>
                        <th style="width:120px;">上级资源</th>
                        <th style="width:120px;">资源说明</th>
                        <th style="width:40px;">类型</th>
                        <th style="width:60px;">权限标识</th>
                        <th style="width:150px;">关联程序</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list permissionList as item>
                        <tr title="关联程序：${item.relativeClass?default('')}">
                            <td >
                               <span class="badge badge-success"  >${item.permissionId?default('')}</span>
                            </td>
                            <td>
                            ${item.parentDescription?default('')}
                            </td>
                            <td>
                            ${item.description?default('')}
                            </td>
                            <td>
                            <#if "Y" == item.isSystem?default("")>
                            <span class="label label-success">系统</span>
                            <#else>
                                <span class="label label-info">业务</span>
                            </#if>
                            </td>
                           <td>
                                ${item.permissionTag?default('')}
                            </td>
                            <td style="word-wrap:break-word;word-break:break-all;">

                            ${item.relativeClass?default('')}

                            </td>

                        </tr>
                        </#list>

                    </tbody>
                </table>
            </div>
        </div>
        <@easyfkPageBar pageUrl="${ctx}/dyn/security/resourcelist?pageSize=10&pageIndex="></@easyfkPageBar>
    </div>
</div>
<script type="text/javascript">

</script>


</@easyfkDecoratorScreen>










