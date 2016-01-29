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

<@easyfkSetNavBar subTitle="资源管理">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'权限管理',tip:'认证授权',href:'${ctx}/partyclsgroup/list'},
    {id:'navid3',title:'资源管理'},
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
<#--
<script src="/static/js/jquery/jquery.uniform.js"></script>
<script src="/static/js/jquery/select2.min.js"></script>
-->
<style type="text/css">
    #partyClsGroupTree   .nodeselected{
        background-color: #e5e5e5;
        box-shadow: 0 0 1px #ffffff;
        border: 1px solid #d6d6d6;
    }
    #partyClsGroupTree  .subtree{
        padding: 5px;padding-bottom:13px;
    }
    #partyClsGroupTree  .itemmenubar{
        float:right;
    }
    #partyClsGroupTree  .subtree a{padding-top:8px;}
</style>
</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/partyrolelist.js"></script>
</@easyfkFooterAttach>


<@easyfkDecoratorScreen name="body" location="component://party/webapp/party/resources/page/appcommon.ftl">
<div class="row-fluid">
    <div class="span12">
        <div id="partyClsGroupTree" style="overflow-y: auto;height: 550px;">
            <ul class="subpanel" style="list-style: none;"><li id="node_10193" data="{&quot;description&quot;:&quot;资源目录&quot;,&quot;partyClassificationGroupId&quot;:&quot;10193&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:19,&quot;month&quot;:10,&quot;nanos&quot;:515000000,&quot;seconds&quot;:52,&quot;time&quot;:1448414392515,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;N/A&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:19,&quot;month&quot;:10,&quot;nanos&quot;:515000000,&quot;seconds&quot;:52,&quot;time&quot;:1448414392515,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:3}" navids=",10193" class="subtree open nodeselected"><div class="itemmenubar"><div id="partyClsGroupMenuBar" style="display: block;">
                <div class="btn-group">
                    <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle">操作 <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right">
                        <li>
                            <a href="javascript:easyfk.neworgnode()" class="tip-left" data-toggle="modal" data-original-title="新建当前选中的下级组织机构">
                                <i class=" icon-plus-sign icon-white"></i>  添加资源
                            </a>
                        </li>

                        <li>
                            <a href="javascript:easyfk.editnode()" class="tip-left" data-toggle="modal" data-original-title="编辑当前选中的组织机构">
                                <i class=" icon-pencil icon-white"></i>  编辑
                            </a>
                        </li>
                        <li>
                            <a href="javascript:easyfk.removenode()" class="tip-left" data-toggle="modal" data-original-title="删除当前选中的组织机构">
                                <i class=" icon-remove icon-white"></i>  删除
                            </a>
                        </li>
                    </ul>
                </div>


                <!-- component://unicorn/webapp/page/contextmenu.ftl end-->
            </div><i id="itemmenupanel10193"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10193')"><i class="icon icon-folder-open" id="icon10193"></i><span ondblclick="easyfk.getChildNode('10193','node_10193',3)">资源目录</span><span class="label" style="margin-left:50px;">3</span></a><ul class="subpanel" style="list-style: none;"><li id="node_10270" data="{&quot;description&quot;:&quot;用户管理&quot;,&quot;partyClassificationGroupId&quot;:&quot;10270&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:19,&quot;month&quot;:10,&quot;nanos&quot;:203000000,&quot;seconds&quot;:53,&quot;time&quot;:1448414393203,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10193&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:19,&quot;month&quot;:10,&quot;nanos&quot;:203000000,&quot;seconds&quot;:53,&quot;time&quot;:1448414393203,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:6}" navids=",10193,10270" class="subtree"><div class="itemmenubar"><i id="itemmenupanel10270"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10270')"><i class="icon icon-folder-close" id="icon10270"></i><span ondblclick="easyfk.getChildNode('10270','node_10270',6)">用户管理</span><span class="label" style="margin-left:50px;">6</span></a><ul class="subpanel" style="list-style: none;"><li id="node_10311" data="{&quot;description&quot;:&quot;禁用用户登录账号&quot;,&quot;partyClassificationGroupId&quot;:&quot;10311&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:453000000,&quot;seconds&quot;:39,&quot;time&quot;:1448414439453,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10270&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:453000000,&quot;seconds&quot;:39,&quot;time&quot;:1448414439453,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}" navids=",10193,10270,10311" class="subtree"><div class="itemmenubar"><i id="itemmenupanel10311"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10311')"><i class="icon icon-folder-close" id="icon10311"></i><span ondblclick="easyfk.getChildNode('10311','node_10311',0)">禁用用户登录账号</span></a></li><li id="node_10312" data="{&quot;description&quot;:&quot;新建用户登录账号&quot;,&quot;partyClassificationGroupId&quot;:&quot;10312&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:453000000,&quot;seconds&quot;:39,&quot;time&quot;:1448414439453,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10270&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:453000000,&quot;seconds&quot;:39,&quot;time&quot;:1448414439453,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}" navids=",10193,10270,10312" class="subtree"><div class="itemmenubar"><i id="itemmenupanel10312"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10312')"><i class="icon icon-folder-close" id="icon10312"></i><span ondblclick="easyfk.getChildNode('10312','node_10312',0)">新建用户登录账号</span></a></li><li id="node_10313" data="{&quot;description&quot;:&quot;删除用户登录账号&quot;,&quot;partyClassificationGroupId&quot;:&quot;10313&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:453000000,&quot;seconds&quot;:39,&quot;time&quot;:1448414439453,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10270&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:453000000,&quot;seconds&quot;:39,&quot;time&quot;:1448414439453,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}" navids=",10193,10270,10313" class="subtree"><div class="itemmenubar"><i id="itemmenupanel10313"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10313')"><i class="icon icon-folder-close" id="icon10313"></i><span ondblclick="easyfk.getChildNode('10313','node_10313',0)">删除用户登录账号</span></a></li><li id="node_10314" data="{&quot;description&quot;:&quot;用户账号锁定&quot;,&quot;partyClassificationGroupId&quot;:&quot;10314&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:453000000,&quot;seconds&quot;:39,&quot;time&quot;:1448414439453,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10270&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:453000000,&quot;seconds&quot;:39,&quot;time&quot;:1448414439453,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}" navids=",10193,10270,10314" class="subtree"><div class="itemmenubar"><i id="itemmenupanel10314"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10314')"><i class="icon icon-folder-close" id="icon10314"></i><span ondblclick="easyfk.getChildNode('10314','node_10314',0)">用户账号锁定</span></a></li><li id="node_10315" data="{&quot;description&quot;:&quot;用户账号解除锁定&quot;,&quot;partyClassificationGroupId&quot;:&quot;10315&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:453000000,&quot;seconds&quot;:39,&quot;time&quot;:1448414439453,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10270&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:453000000,&quot;seconds&quot;:39,&quot;time&quot;:1448414439453,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}" navids=",10193,10270,10315" class="subtree"><div class="itemmenubar"><i id="itemmenupanel10315"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10315')"><i class="icon icon-folder-close" id="icon10315"></i><span ondblclick="easyfk.getChildNode('10315','node_10315',0)">用户账号解除锁定</span></a></li><li id="node_10316" data="{&quot;description&quot;:&quot;关联登录证书&quot;,&quot;partyClassificationGroupId&quot;:&quot;10316&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:453000000,&quot;seconds&quot;:39,&quot;time&quot;:1448414439453,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10270&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:453000000,&quot;seconds&quot;:39,&quot;time&quot;:1448414439453,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}" navids=",10193,10270,10316" class="subtree"><div class="itemmenubar"><i id="itemmenupanel10316"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10316')"><i class="icon icon-folder-close" id="icon10316"></i><span ondblclick="easyfk.getChildNode('10316','node_10316',0)">关联登录证书</span></a></li></ul></li><li id="node_10271" data="{&quot;description&quot;:&quot;三员管理&quot;,&quot;partyClassificationGroupId&quot;:&quot;10271&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:19,&quot;month&quot;:10,&quot;nanos&quot;:203000000,&quot;seconds&quot;:53,&quot;time&quot;:1448414393203,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10193&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:19,&quot;month&quot;:10,&quot;nanos&quot;:203000000,&quot;seconds&quot;:53,&quot;time&quot;:1448414393203,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:3}" navids=",10193,10271" class="subtree open"><div class="itemmenubar"><i id="itemmenupanel10271"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10271')"><i class="icon icon-folder-open" id="icon10271"></i><span ondblclick="easyfk.getChildNode('10271','node_10271',3)">三员管理</span><span class="label" style="margin-left:50px;">3</span></a><ul class="subpanel" style="list-style: none; display: block;"><li id="node_10318" data="{&quot;description&quot;:&quot;显示三员列表&quot;,&quot;partyClassificationGroupId&quot;:&quot;10318&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:15000000,&quot;seconds&quot;:29,&quot;time&quot;:1448414429015,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10271&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:15000000,&quot;seconds&quot;:29,&quot;time&quot;:1448414429015,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}" navids=",10193,10271,10318" class="subtree"><div class="itemmenubar"><i id="itemmenupanel10318"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10318')"><i class="icon icon-folder-close" id="icon10318"></i><span ondblclick="easyfk.getChildNode('10318','node_10318',0)">显示三员列表</span></a></li><li id="node_10319" data="{&quot;description&quot;:&quot;编辑三员信息&quot;,&quot;partyClassificationGroupId&quot;:&quot;10319&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:15000000,&quot;seconds&quot;:29,&quot;time&quot;:1448414429015,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10271&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:15000000,&quot;seconds&quot;:29,&quot;time&quot;:1448414429015,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}" navids=",10193,10271,10319" class="subtree"><div class="itemmenubar"><i id="itemmenupanel10319"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10319')"><i class="icon icon-folder-close" id="icon10319"></i><span ondblclick="easyfk.getChildNode('10319','node_10319',0)">编辑三员信息</span></a></li><li id="node_10320" data="{&quot;description&quot;:&quot;关联登录证书&quot;,&quot;partyClassificationGroupId&quot;:&quot;10320&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:15000000,&quot;seconds&quot;:29,&quot;time&quot;:1448414429015,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10271&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:15000000,&quot;seconds&quot;:29,&quot;time&quot;:1448414429015,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}" navids=",10193,10271,10320" class="subtree"><div class="itemmenubar"><i id="itemmenupanel10320"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10320')"><i class="icon icon-folder-close" id="icon10320"></i><span ondblclick="easyfk.getChildNode('10320','node_10320',0)">关联登录证书</span></a></li></ul></li><li id="node_10317" data="{&quot;description&quot;:&quot;角色管理&quot;,&quot;partyClassificationGroupId&quot;:&quot;10317&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:19,&quot;month&quot;:10,&quot;nanos&quot;:203000000,&quot;seconds&quot;:53,&quot;time&quot;:1448414393203,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10193&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:19,&quot;month&quot;:10,&quot;nanos&quot;:203000000,&quot;seconds&quot;:53,&quot;time&quot;:1448414393203,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}" navids=",10193,10317" class="subtree open"><div class="itemmenubar"><i id="itemmenupanel10317"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10317')"><i class="icon icon-folder-open" id="icon10317"></i><span ondblclick="easyfk.getChildNode('10317','node_10317',0)">角色管理</span></a><ul class="subpanel" style="list-style: none;"><li id="node_10321" data="{&quot;description&quot;:&quot;显示角色列表&quot;,&quot;partyClassificationGroupId&quot;:&quot;10321&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:500000000,&quot;seconds&quot;:17,&quot;time&quot;:1448414417500,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10317&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:500000000,&quot;seconds&quot;:17,&quot;time&quot;:1448414417500,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}" navids=",10193,10317,10321" class="subtree"><div class="itemmenubar"><i id="itemmenupanel10321"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10321')"><i class="icon icon-folder-close" id="icon10321"></i><span ondblclick="easyfk.getChildNode('10321','node_10321',0)">显示角色列表</span></a></li><li id="node_10322" data="{&quot;description&quot;:&quot;新增自定义角色&quot;,&quot;partyClassificationGroupId&quot;:&quot;10322&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:500000000,&quot;seconds&quot;:17,&quot;time&quot;:1448414417500,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10317&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:500000000,&quot;seconds&quot;:17,&quot;time&quot;:1448414417500,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}" navids=",10193,10317,10322" class="subtree"><div class="itemmenubar"><i id="itemmenupanel10322"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10322')"><i class="icon icon-folder-close" id="icon10322"></i><span ondblclick="easyfk.getChildNode('10322','node_10322',0)">新增自定义角色</span></a></li><li id="node_10323" data="{&quot;description&quot;:&quot;删除自定义角色&quot;,&quot;partyClassificationGroupId&quot;:&quot;10323&quot;,&quot;lastUpdatedStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:500000000,&quot;seconds&quot;:17,&quot;time&quot;:1448414417500,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;parentGroupId&quot;:&quot;10317&quot;,&quot;partyClassificationTypeId&quot;:&quot;ORGANIZATION_CLASSIF&quot;,&quot;lastUpdatedTxStamp&quot;:{&quot;date&quot;:25,&quot;day&quot;:3,&quot;hours&quot;:9,&quot;minutes&quot;:20,&quot;month&quot;:10,&quot;nanos&quot;:500000000,&quot;seconds&quot;:17,&quot;time&quot;:1448414417500,&quot;timezoneOffset&quot;:-480,&quot;year&quot;:115},&quot;childCount&quot;:0}" navids=",10193,10317,10323" class="subtree"><div class="itemmenubar"><i id="itemmenupanel10323"></i></div><a href="javascript:void(0)" onclick="easyfk.showSubTree('10323')"><i class="icon icon-folder-close" id="icon10323"></i><span ondblclick="easyfk.getChildNode('10323','node_10323',0)">删除自定义角色</span></a></li></ul></li></ul></li></ul></div>
    </div>

</div>

<script type="text/javascript">
    easyfk.showUserLogin = function(partyId){
        document.location.href = "${ctx}/page/roleuserloginlist.ftl"+partyId;
    }

    easyfk.neworgnode = function () {
        var currnetId = $("#currentSelectedGroupId").val();
        $("#parentGroupId").val(currnetId);
        $("#description").val("");
      //  $("#formDailogLabel").html("新建组织机构");
        $("#partyClsGroupFormAction").val("${ctx}/partyclsgroup/create");
        $("#partyClsGroupFormDailog").modal("show");
    }

    easyfk.editnode = function () {
        var currnetId = $("#currentSelectedGroupId").val();
        $("#parentGroupId").val(currnetId);
        $("#description").val("");
        $("#formDailogLabel").html("编辑资源");
        $("#partyClsGroupFormDailog").modal("show");
    }

    easyfk.removenode = function(){
        $("#unicornAlertDailog_title").html("询问");
        $("#unicornAlertDailog_body").html("确定要XX吗？");
        $("#unicornAlertDailog").modal("show");

    }

    easyfk.showLog = function(){
        document.location.href = "${ctx}/page/oploglist.ftl";
    }



    easyfk.showSubTree =function(id){
        var theObj = $("#node_"+id);
        $("#currentSelectedGroupId").val(id);

        if ( theObj.hasClass("open")){
            theObj.children(".subpanel").hide();
            theObj.removeClass("open");
            $("#icon"+id).removeClass("icon-folder-open");
            $("#icon"+id).addClass("icon-folder-close");
        }else{
            theObj.children(".subpanel").show();
            theObj.addClass("open");
            $("#icon"+id).removeClass("icon-folder-close");
            $("#icon"+id).addClass("icon-folder-open");
        }
        $(".nodeselected").removeClass("nodeselected");
        theObj.addClass("nodeselected");
        $("#partyClsGroupMenuBar").insertBefore($("#itemmenupanel"+id));
        $("#partyClsGroupMenuBar").show();

    }
</script>

<input type="hidden"   id="currentSelectedGroupId" value="">
<input type="hidden"   id="partyClsGroupFormAction" value="">
<div id="partyClsGroupFormDailog" class="modal   fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="formDailogLabel">
            新建资源
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="partyClsGroupForm" id="partyClsGroupForm" novalidate="novalidate">
            <input type="hidden" name="partyClassificationGroupId" id="partyClassificationGroupId">
            <input type="hidden" name="partyClassificationTypeId" id="partyClassificationTypeId" value="ORGANIZATION_CLASSIF">
            <input type="hidden" name="parentGroupId" id="parentGroupId" value="N/A">
            <div class="control-group">
                <label class="control-label">资源名称</label>
                <div class="controls">
                    <input type="text" id="description" name="description"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">权限标识</label>
                <div class="controls">
                    <input type="text" id="rightId" name="rightId"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">

        <button class="btn btn-primary" id="partyClsGroupFormSubmit" >保存</button>
        <button class="btn" id="partyClsGroupFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>
</@easyfkDecoratorScreen>










