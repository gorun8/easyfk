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

<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuPartygVersion','menuPartygVersion_2']}" location="component://party/webapp/party/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="CA证书">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'版本版权',tip:'管理程序和允可证等',href:'${ctx}/partyclsgroup/list'},
    {id:'navid3',title:'许可证'},
 ]}
</@easyfkSetNavBar>
<@easyfkHeaderAttach>
<link rel="stylesheet" href="/static/css/uniform.css" />
<script src="/static/js/jquery/validate/jquery.validate.min.js"></script>
<script src="/static/js/jquery/validate/localization/messages_zh.min.js"></script>
<script src="/static/js/jquery/jquery.uniform.js"></script>
</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/licence.js"></script>
</@easyfkFooterAttach>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/party/resources/page/appcommon.ftl">
<div class="row-fluid">
    <div class="span12">
        <@easyfkContextMenu  >
            {MENU_DATA:[
            {id:'',title:'添加许可证',desc:'导入新的许可证',toggle:'modal',href:'javascript:easyfk.setCACert()',style:'btn btn-primary tip-bottom',style2:' icon-tags icon-white'},
            ]}
        </@easyfkContextMenu>
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-tag"></i>
								</span>
                <h5>XX服务端许可证</h5>
                <div class="btn-group" style="float: right;margin: 9px 15px 0 0;">
                    <button data-toggle="dropdown" class="btn btn-info btn-mini dropdown-toggle">操作 <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right">
                        <li><a href="#" onclick="easyfk.removeCACert()">删除</a></li>
                        <li><a href="#" onclick="easyfk.setCACert()">更新</a></li>

                    </ul>
                </div>
            </div>
            <div class="widget-content">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>项目</th>
                        <th>内容</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <span>授权主题</span>
                        </td>
                        <td>
                            CN = XX,E = admin@gorun8.cn
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span>生效时间</span>
                        </td>
                        <td>
                            2012年2月15日 20:50:53
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span>失效时间</span>
                        </td>
                        <td>
                            2012年2月15日 20:50:53
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span>项目</span>
                        </td>
                        <td>
                            最大用户数：2000<br>
                            最大用并发数：200<br>
                            ....
                        </td>
                    </tr>

                    </tbody>
                </table>
             </div>
        </div>
    </div>

</div>

<div class="row-fluid">
    <div class="span12">
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class=" icon-tag"></i>
								</span>
                <h5>XX客户端证可证</h5>
                <div class="btn-group" style="float: right;margin: 9px 15px 0 0;">
                    <button data-toggle="dropdown" class="btn btn-info btn-mini dropdown-toggle">操作 <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right">
                        <li><a href="#" onclick="easyfk.removeCACert()">删除</a></li>
                        <li><a href="#" onclick=" easyfk.setCACert()">更新</a></li>

                    </ul>
                </div>
            </div>
            <div class="widget-content">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>项目</th>
                        <th>内容</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <span>授权主题</span>
                        </td>
                        <td>
                            CN = XX,E = admin@gorun8.cn
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span>生效时间</span>
                        </td>
                        <td>
                            2012年2月15日 20:50:53
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span>失效时间</span>
                        </td>
                        <td>
                            2012年2月15日 20:50:53
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span>项目</span>
                        </td>
                        <td>
                            最大用户数：2000<br>
                            最大用并发数：200<br>
                            ....
                        </td>
                    </tr>

                    </tbody>
                </table>

            </div>
        </div>
    </div>

</div>

</@easyfkDecoratorScreen>
<script type="text/javascript">


    easyfk.setCACert = function(showcert) {
        if(!showcert)
        {
            $(".certcontent").hide();
        }else{
            $(".certcontent").show();
        }
        $("#userCertFormPanel").modal("show");
    }

    easyfk.removeCACert = function(){
        $("#unicornAlertDailog_title").html("询问");
        $("#unicornAlertDailog_body").html("确定要XX吗？");
        $("#unicornAlertDailog").modal("show");

    }

</script>


<input type="hidden" id="userLoginFormAction">
<div id="userCertFormPanel" class="modal  fade" role="dialog" aria-labelledby="userCertFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="userCertFormLabel">
            许可证书
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="userCertForm" id="userCertForm">

            <div class="control-group">
                <label class="control-label">许可证书文件</label>
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









