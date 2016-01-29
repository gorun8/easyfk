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

<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuPartygpgs']}" location="component://party/webapp/party/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="会员列表">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'fa  fa-home',href:'${ctx}'},
    {id:'navid2',title:'组织机构',tip:'管理组织机构',href:'${ctx}/dyn/partyclsgroup/list'},
    <#list parentClsGroupList as item>
    {id:'navid${item.partyClassificationGroupId}',tip:'${item.description}',title:'${item.description}'},
    </#list>
    {id:'navid3',title:'会员详情'},
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

</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/partydetial.js"></script>
</@easyfkFooterAttach>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/party/resources/page/appcommon.ftl">
<#--@easyfkContextMenu  >
           {MENU_DATA:[
           {id:'',title:'详情',desc:'显示会员详情汇总',toggle:'modal',href:'javascript:easyfk.noimplement()',style:'btn btn-primary tip-bottom',style2:' fa  fa-map-marker fa  fa-white'},
           {id:'',title:'角色',desc:'显示会员具有的角色',toggle:'modal',href:'javascript:easyfk.noimplement()',style:'btn btn-primary tip-bottom',style2:' fa  fa-map-marker fa  fa-white'},
           {id:'',title:'权限',desc:'显示会员权限',toggle:'modal',href:'javascript:easyfk.noimplement()',style:'btn btn-primary tip-bottom',style2:' fa  fa-map-marker fa  fa-white'},
           {id:'',title:'组织',desc:'显示会员所属的组织机构',toggle:'modal',href:'javascript:easyfk.noimplement()',style:'btn btn-primary tip-bottom',style2:' fa  fa-map-marker fa  fa-white'},
           {id:'',title:'日志',desc:'显示会员相关的日志',toggle:'modal',href:'javascript:easyfk.noimplement()',style:'btn btn-primary tip-bottom',style2:' fa  fa-map-marker fa  fa-white'},

           ]}
       </@easyfkContextMenu-->
<div class="row" id="partyPortalPagePanel">
<#--
    <@easyFK.renderPortalPage columnList=pageColumnList portalList=pagePortalList params=""/>
    -->
</div>

<script>
    $(function(){
        easyfk.renderPortalPage("${ctx}","partyPortalPagePanel","PARTYPROFILE_PAGE","currnetPartyId=${currnetPartyId?default('')}");
    });
</script>

</@easyfkDecoratorScreen>