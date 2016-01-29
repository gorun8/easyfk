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
<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuHomeId']}" location="component://party/webapp/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="首面">

{NAV_BAR_DATA:[{id:'navid1',title:'系统状态',tip:'系统状态',style:'icon-signal',href:'${ctx}'},
]}

</@easyfkSetNavBar>

<@easyfkHeaderAttach>
<script src="/images/js/jquery/excanvas.min.js"></script>
<script src="/images/js/jquery/jquery.flot.min.js"></script>
<script src="/images/js/jquery/jquery.flot.pie.min.js"></script>
<script src="/images/js/jquery/jquery.flot.resize.min.js"></script>
</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/index.js"></script>
<script src="/party/js/indexcharts.js"></script>
</@easyfkFooterAttach>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/resources/page/appcommon.ftl">
<div class="row-fluid">
    <div class="span12">
        <div class="widget-box widget-plain">
            <div class="widget-content center">
                <ul class="stats-plain">
                    <li>
                        <h4>36094</h4>
                        <span>总访问数</span>
                    </li>
                    <li>
                        <h4>1433</h4>
                        <span>注册用户数</span>
                    </li>
                    <li>
                        <h4>8650</h4>
                        <span>处理文件数</span>
                    </li>
                    <li>
                        <h4>29</h4>
                        <span><button class="btn btn-warning" onclick="easyfk.alertDetail()">告警次数</button></span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-12">
        <div class="alert alert-info">
            Welcome in the <strong>Unicorn Admin Theme</strong>. Don't forget to check all the pages!
            <a href="#" data-dismiss="alert" class="close">×</a>
        </div>
        <div class="widget-box">
            <div class="widget-title">
                <span class="icon"><i class="fa fa-signal"></i></span>
                <h5>Site Statistics</h5>
                <div class="buttons">
                    <a href="#" class="btn"><i class="fa fa-refresh"></i> <span class="text">Update stats</span></a>
                </div>
            </div>
            <div class="widget-content">
                <div class="row">
                    <div class="col-xs-12 col-sm-4">
                        <ul class="site-stats">
                            <li><div class="cc"><i class="fa fa-user"></i> <strong>1433</strong> <small>Total Users</small></div></li>
                            <li><div class="cc"><i class="fa fa-arrow-right"></i> <strong>16</strong> <small>New Users (last week)</small></div></li>
                            <li class="divider"></li>
                            <li><div class="cc"><i class="fa fa-shopping-cart"></i> <strong>259</strong> <small>Total Shop Items</small></div></li>
                            <li><div class="cc"><i class="fa fa-tag"></i> <strong>8650</strong> <small>Total Orders</small></div></li>
                            <li><div class="cc"><i class="fa fa-repeat"></i> <strong>29</strong> <small>Pending Orders</small></div></li>
                        </ul>
                    </div>
                    <div class="col-xs-12 col-sm-8">
                        <div class="chart"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-4">
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="fa fa-th-list"></i>
								</span>
                <h5>服务器环境</h5>
            </div>
            <div class="widget-content nopadding">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="width: 60px;">项目</th>
                        <th>内容</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Server</td>
                        <td>Tomcat7.0 & Tengine1.7</td>
                    </tr>
                    <tr>
                        <td>OS</td>
                        <td>CentOS6.5</td>
                    </tr>
                    <tr>
                        <td>CPU</td>
                        <td>Intel(R) Core(TM)2 Duo CPU E7300 @2.66GHz X 4</td>
                    </tr>
                    <tr>
                        <td>WEB服务</td>
                        <td>192.168.8.7:80</td>
                    </tr>

                    </tbody>
                </table>
            </div>
        </div>
    </div>


    <div class="col-xs-12 col-sm-4">
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="fa fa-arrow-right"></i>
								</span>
                <h5>快捷链接</h5>
            </div>
            <div class="widget-content nopadding">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>站点</th>
                        <th>地址</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Google</td>
                        <td><a href="#">http://google.com</a></td>
                    </tr>
                    <tr>
                        <td>Bing</td>
                        <td><a href="#">http://bing.com</a></td>
                    </tr>
                    <tr>
                        <td>YaHoo</td>
                        <td><a href="#">http://yahoo.com</a></td>
                    </tr>
                    <tr>
                        <td>SomeThing</td>
                        <td><a href="#">http://www.something.com</a></td>
                    </tr>
                    <tr>
                        <td>Else</td>
                        <td><a href="#">http://else.com</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-4">
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="fa fa-envelope"></i>
								</span>
                <h5>公告通知</h5>

                <button class="btn   btn-mini" onclick="easyfk.gotoInbox();" style="float: right;margin: 9px 15px 0 0;"><i class="icon-eye-open"></i>详细</button>

            </div>
            <div class="widget-content">

                    <div class="alert">
                        <strong>张三</strong><p></p> 老板，该加工资了吧！.老板，该加工资了吧！.
                     </div>
                    <div class="alert alert-success">
                         <strong>王二</strong><p></p> 老板，该加工资了吧！.老板，该加工资了吧！.
                    </div>

                    <div class="alert alert-block alert-warning">
                        <a class="close" data-dismiss="alert" href="#">×</a>
                        <h4 class="alert-heading">Warning!</h4>
                        Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
                    </div>
            </div>


        </div>
    </div>


</div>

<script type="text/javascript">

easyfk.alertDetail = function() {
    $("#alertDetailPanel").modal("show");
}
easyfk.gotoInbox  = function() {
    document.location.href = "/party/page/inbox.ftl";
}


</script>

<div id="alertDetailPanel" class="modal  fade" role="dialog" aria-labelledby="alertDetailLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="alertDetailLabel">
            消息
        </h3>
    </div>
    <div class="modal-body">
        <div class="widget-content">
            <div class="alert alert-block">
                <a class="close" data-dismiss="alert" href="#">×</a>
                <h4 class="alert-heading">Warning!</h4>
                Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
            </div>
            <div class="alert alert-success alert-block">
                <a class="close" data-dismiss="alert" href="#">×</a>
                <h4 class="alert-heading">Success!</h4>
                Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
            </div>
            <div class="alert alert-info alert-block">
                <a class="close" data-dismiss="alert" href="#">×</a>
                <h4 class="alert-heading">Info!</h4>
                Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
            </div>
            <div class="alert alert-error alert-block">
                <a class="close" data-dismiss="alert" href="#">×</a>
                <h4 class="alert-heading">Error!</h4>
                Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
            </div>
        </div>

    </div>
    <div class="modal-footer">
         <button class="btn btn-primary" id="alertDetailClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>
</div>

</@easyfkDecoratorScreen>



