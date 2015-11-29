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

<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuPartygLogs','menuPartygLogs_0']}" location="component://party/webapp/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="系统日志">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'日志管理',tip:'日志管理',href:'${ctx}/partyclsgroup/list'},
    {id:'navid3',title:'日志概况'},
 ]}
</@easyfkSetNavBar>
<@easyfkHeaderAttach>
<script src="/images/js/jquery/excanvas.min.js"></script>
<script src="/images/js/jquery/jquery.flot.min.js"></script>
<script src="/images/js/jquery/jquery.flot.pie.min.js"></script>
<script src="/images/js/jquery/jquery.flot.resize.min.js"></script>
</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/logchartreport.js"></script>
</@easyfkFooterAttach>


<@easyfkDecoratorScreen name="body" location="component://party/webapp/resources/page/appcommon.ftl">
<div class="span6">
    <div class="widget-box">
        <div class="widget-title">
								<span class="icon">
									<i class="icon-adjust"></i>
								</span>
            <h5>日志概况图</h5>
        </div>
        <div class="widget-content">
<#--
            <img src="${ctx}/style/images/1.png" >
    -->
    <div class="pie"></div>


        </div>
    </div>
</div>
<div class="span4">
    <div class="widget-box">
        <div class="widget-title">
								<span class="icon">
									<i class="icon-hand-up"></i>
								</span>
            <h5>分级统计</h5>
        </div>
        <div class="widget-content nopadding">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>级别</th>
                    <th>数量</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><span class="label label-info">信息</span></td>
                    <td>875</td>
                </tr>
                <tr>
                    <td><span class="label label-warning">警告</span></td>
                    <td>562</td>
                </tr>
                <tr>
                    <td><span class="label label-important">错误</span></td>
                    <td>400</td>
                </tr>
                <tr>
                    <td><span class="label label-important">严重错误</span></td>
                    <td>100</td>
                </tr>
                <tr>
                    <td><span class="label label-inverse">致命错误</span></td>
                    <td>2</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</@easyfkDecoratorScreen>












