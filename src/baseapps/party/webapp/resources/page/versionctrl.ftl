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

<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuPartygVersion','menuPartygVersion_1']}" location="component://party/webapp/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="版本控制">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'版本管理',tip:'版本管理',href:'${ctx}/partyclsgroup/list'},
    {id:'navid3',title:'客户端'},
 ]}
</@easyfkSetNavBar>
<@easyfkHeaderAttach>
<script src="/images/js/jquery/validate/jquery.validate.min.js"></script>
<script src="/images/js/jquery/validate/localization/messages_zh.min.js"></script>
</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/versionctrl.js"></script>
</@easyfkFooterAttach>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/resources/page/appcommon.ftl">

<div class="row-fluid">
    <div class="span12">
        <@easyfkContextMenu>
            {MENU_DATA:[
            {id:'',title:'发布程序',desc:'发布新的程序模块',toggle:'modal',href:'javascript:easyfk.publishModule()',style:'btn btn-primary tip-bottom',style2:'  icon-gift icon-white'},

            ]}
        </@easyfkContextMenu>

        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
                <h5>已发布程序</h5>
            </div>
            <div class="widget-content ">
                <div class="row-fluid">
                    <div class="span12">
                        <div class="alert">
                            <div class="btn-group " style="float: right">
                                <button class="btn btn-mini btn-info" >操作</button>
                                <button data-toggle="dropdown" class="btn btn-info  btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu  pull-right">
                                    <li><a href="#" onclick="easyfk.publishModule()">修改</a></li>
                                    <li><a href="#" onclick="easyfk.removemsg()">安装</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.removemsg()">停用</a></li>
                                    <li><a href="#" onclick="easyfk.removemsg(1)">删除</a></li>
                                </ul>
                            </div>
                            <strong>客户端</strong><p></p>
                            <strong>电子文件客户端模块</strong><p></p>可能性枯；霜降枯叶 枯黄另呆若木鸡国家医药管理局..........
                        </div>
                        <div class="alert alert-success">
                            <div class="btn-group " style="float: right">
                                <button class="btn btn-mini btn-info" >操作</button>
                                <button data-toggle="dropdown" class="btn btn-info  btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu  pull-right">
                                    <li><a href="#" onclick="easyfk.publishModule()">修改</a></li>
                                    <li><a href="#" onclick="easyfk.removemsg()">安装</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.removemsg()">停用</a></li>
                                    <li><a href="#" onclick="easyfk.removemsg(1)">删除</a></li>
                                </ul>
                            </div>
                            <strong>客户端</strong><p></p>
                            <strong>电子文件客户端模块</strong><p></p>可能性枯；霜降枯叶 枯黄另呆若木鸡国家医药管理局..........

                        </div>
                        <div class="alert alert-info">
                            <div class="btn-group " style="float: right">
                                <button class="btn btn-mini btn-info" >操作</button>
                                <button data-toggle="dropdown" class="btn btn-info  btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu  pull-right">
                                    <li><a href="#" onclick="easyfk.publishModule()">修改</a></li>
                                    <li><a href="#" onclick="easyfk.removemsg()">安装</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.removemsg()">停用</a></li>
                                    <li><a href="#" onclick="easyfk.removemsg(1)">删除</a></li>
                                </ul>
                            </div>
                            <strong>客户端</strong><p></p>
                            <strong>电子文件客户端模块</strong><p></p>可能性枯；霜降枯叶 枯黄另呆若木鸡国家医药管理局..........

                        </div>
                        <div class="alert alert-error">
                            <div class="btn-group " style="float: right">
                                <button class="btn btn-mini btn-info" >操作</button>
                                <button data-toggle="dropdown" class="btn btn-info  btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu  pull-right">
                                    <li><a href="#" onclick="easyfk.publishModule()">修改</a></li>
                                    <li><a href="#" onclick="easyfk.removemsg()">安装</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.removemsg()">停用</a></li>
                                    <li><a href="#" onclick="easyfk.removemsg(1)">删除</a></li>
                                </ul>
                            </div>
                            <strong>客户端</strong><p></p>
                            <strong>电子文件客户端模块</strong><p></p>可能性枯；霜降枯叶 枯黄另呆若木鸡国家医药管理局..........

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="pagination">
        <ul>
            <li><a href="#">首页</a></li>
            <li class="active">
                <a href="#">1</a>
            </li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">尾页</a></li>
        </ul>
    </div>
</div>


<script type="text/javascript">

    easyfk.publishModule = function() {
        $("#messagePublicPanel").modal("show");
    }


    easyfk.removemsg = function(){
        $("#unicornAlertDailog_title").html("询问");
        $("#unicornAlertDailog_body").html("确定要XX吗？");
        $("#unicornAlertDailog").modal("show");
    }

</script>



<div id="messagePublicPanel" class="modal fade" role="dialog" aria-labelledby="messagePubliclLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="messagePublicLabel">
            发布新程序
        </h3>
    </div>
    <div class="modal-body">

        <form action="" method="post" class="form-horizontal" name="messagePublicForm" id="partyClsSearchForm">
            <div class="control-group">
                <label class="control-label"  style="width: 60px;margin-left:-5px;">标题</label>
                <div class="controls" style="margin-left:70px;">
                     <input type="text" >
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"  style="width: 60px;margin-left:-5px;">类别</label>
                <div class="controls" style="margin-left:70px;">
                    <select>
                        <option />客户端
                        <option />服务端
                    </select>
                </div>
            </div>

            <div class="control-group">
                <div class="controls" style="width: 60px;margin-left:-5px;">
                    <div style="height: 200px;" >
                        <textarea style="height: 200px;width: 525px;" >

                        </textarea>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button class="btn " id="sysLogSearchFormSubmit" onclick="easyfk.dosearch()">回复</button>
        <button class="btn btn-primary" id="sysLogSearchFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>

    </div>
</div>
</div>

</@easyfkDecoratorScreen>








