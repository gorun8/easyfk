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

<div class="row-fluid">
    <div class="span12">
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-signal"></i>
								</span>
                <h5>CPU和内存</h5>
            </div>
            <div class="widget-content">
                <!--
                <div class="chart" style="padding: 0px; position: relative;"><canvas class="base" width="972" height="300"></canvas><canvas class="overlay" width="972" height="300" style="position: absolute; left: 0px; top: 0px;"></canvas><div class="tickLabels" style="font-size:smaller"><div class="xAxis x1Axis" style="color:#545454"><div class="tickLabel" style="position:absolute;text-align:center;left:-4px;top:280px;width:64px">0</div><div class="tickLabel" style="position:absolute;text-align:center;left:66px;top:280px;width:64px">1</div><div class="tickLabel" style="position:absolute;text-align:center;left:135px;top:280px;width:64px">2</div><div class="tickLabel" style="position:absolute;text-align:center;left:205px;top:280px;width:64px">3</div><div class="tickLabel" style="position:absolute;text-align:center;left:275px;top:280px;width:64px">4</div><div class="tickLabel" style="position:absolute;text-align:center;left:344px;top:280px;width:64px">5</div><div class="tickLabel" style="position:absolute;text-align:center;left:414px;top:280px;width:64px">6</div><div class="tickLabel" style="position:absolute;text-align:center;left:483px;top:280px;width:64px">7</div><div class="tickLabel" style="position:absolute;text-align:center;left:553px;top:280px;width:64px">8</div><div class="tickLabel" style="position:absolute;text-align:center;left:623px;top:280px;width:64px">9</div><div class="tickLabel" style="position:absolute;text-align:center;left:692px;top:280px;width:64px">10</div><div class="tickLabel" style="position:absolute;text-align:center;left:762px;top:280px;width:64px">11</div><div class="tickLabel" style="position:absolute;text-align:center;left:832px;top:280px;width:64px">12</div><div class="tickLabel" style="position:absolute;text-align:center;left:901px;top:280px;width:64px">13</div></div><div class="yAxis y1Axis" style="color:#545454"><div class="tickLabel" style="position:absolute;text-align:right;top:255px;right:951px;width:21px">-1.5</div><div class="tickLabel" style="position:absolute;text-align:right;top:213px;right:951px;width:21px">-1.0</div><div class="tickLabel" style="position:absolute;text-align:right;top:171px;right:951px;width:21px">-0.5</div><div class="tickLabel" style="position:absolute;text-align:right;top:129px;right:951px;width:21px">0.0</div><div class="tickLabel" style="position:absolute;text-align:right;top:86px;right:951px;width:21px">0.5</div><div class="tickLabel" style="position:absolute;text-align:right;top:44px;right:951px;width:21px">1.0</div><div class="tickLabel" style="position:absolute;text-align:right;top:2px;right:951px;width:21px">1.5</div></div></div><div class="legend"><div style="position: absolute; width: 55px; height: 44px; top: 9px; right: 9px; opacity: 0.85; background-color: rgb(255, 255, 255);"> </div><table style="position:absolute;top:9px;right:9px;;font-size:smaller;color:#545454"><tbody><tr><td class="legendColorBox"><div style="border:1px solid #ccc;padding:1px"><div style="width:4px;height:0;border:5px solid #BA1E20;overflow:hidden"></div></div></td><td class="legendLabel">sin(x)</td></tr><tr><td class="legendColorBox"><div style="border:1px solid #ccc;padding:1px"><div style="width:4px;height:0;border:5px solid #459D1C;overflow:hidden"></div></div></td><td class="legendLabel">cos(x)</td></tr></tbody></table></div></div>
                -->
                <div class="chart"></div>
                <!--
                <img src="${ctx}/style/images/2.png" >
                -->
            </div>
        </div>
    </div>
</div>
<div class="row-fluid">
    <div class="span4">
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
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
    <div class="span4">
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-arrow-right"></i>
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
    <div class="span4">
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-envelope"></i>
								</span>
                <h5>公告通知</h5>

                <button class="btn   btn-mini" onclick="easyfk.gotoInbox();" style="float: right;margin: 9px 15px 0 0;"><i class="icon-eye-open"></i>详细</button>

            </div>
            <div class="widget-content nopadding">

                    <div class="alert">
                        <strong>张三</strong><p></p> 老板，该加工资了吧！.老板，该加工资了吧！.
                     </div>
                    <div class="alert alert-success">
                         <strong>王二</strong><p></p> 老板，该加工资了吧！.老板，该加工资了吧！.
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

<div id="alertDetailPanel" class="modal hide fade" role="dialog" aria-labelledby="alertDetailLabel" aria-hidden="true">
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



