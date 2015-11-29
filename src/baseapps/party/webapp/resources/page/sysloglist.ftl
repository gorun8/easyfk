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

<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuPartygLogs','menuPartygLogs_1']}" location="component://party/webapp/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="系统日志">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'日志管理',tip:'日志管理',href:'${ctx}/partyclsgroup/list'},
    {id:'navid3',title:'系统日志'},
 ]}
</@easyfkSetNavBar>
<@easyfkHeaderAttach>

<link rel="stylesheet" href="/images/css/datepicker.css" />
<link rel="stylesheet" href="/images/css/uniform.css" />

<script src="/images/js/jquery/bootstrap-datepicker.js"></script>
<script src="/images/js/jquery/validate/jquery.validate.min.js"></script>
<script src="/images/js/jquery/validate/localization/messages_zh.min.js"></script>
<script src="/images/js/jquery/jquery.uniform.js"></script>

</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/sysloglist.js"></script>
</@easyfkFooterAttach>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/resources/page/appcommon.ftl">
<div class="row-fluid">
    <div class="span12">
        <@easyfkContextMenu  >
            {MENU_DATA:[
            {id:'',title:'查询',desc:'查询日志',toggle:'modal',href:'javascript:easyfk.searchLog()',style:'btn btn-primary tip-bottom',style2:' icon-map-marker icon-white'},
            {id:'',title:'导出PDF',desc:'导出PDF',toggle:'modal',href:'javascript:easyfk.exportLog()',style:'btn btn-primary tip-bottom',style2:' icon-map-marker icon-white'},
            ]}
        </@easyfkContextMenu>
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
                <h5>系统运行过程中产生的日志</h5>

            </div>
            <div class="widget-content">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th style="width:40px;">序号</th>
                        <th>内容</th>
                        <th>级别</th>
                        <th>时间</th>
                        <th>来源</th>

                    </tr>
                    </thead>
                    <tbody>
           <#list 1..3 as ii>
                    <tr>
                        <td>
                            <span class="badge badge-info"  >1</span>
                        </td>
                        <td>
                            系统运行过程中产生的日志系统运行过程中产生的日志系统运行过程中产生的日志
                        </td>
                        <td>
                             <button onclick="easyfk.showLogDetail()" class="btn btn-info btn-mini tip-bottom"   data-original-title="点击查看详情">信息</button>
                        </td>
                        <td>
                            2015-11-12 15:20:30
                        </td>
                        <td>
                            JAVA
                        </td>


                    </tr>
                    <tr>
                        <td>
                            <span class="badge badge-warning"  >2</span>
                        </td>
                        <td>
                            系统运行过程中产生的日志系统运行过程中产生的日志系统运行过程中产生的日志
                        </td>
                        <td>
                             <button onclick="easyfk.showLogDetail()" class="btn btn-warning btn-mini tip-bottom"   data-original-title="点击查看详情">警告</button>
                        </td>
                        <td>
                            2015-11-12 15:20:30
                        </td>
                        <td>
                            JAVA
                        </td>


                    </tr>
                    <tr >
                        <td>
                            <span class="badge  badge-important tip-bottom" >3</span>
                        </td>
                        <td>
                            系统运行过程中产生的日志系统运行过程中产生的日志系统运行过程中产生的日志
                        </td>
                        <td>
                             <button onclick="easyfk.showLogDetail()" class="btn btn-danger btn-mini tip-bottom"   data-original-title="点击查看详情">错误</button>
                       </td>
                        <td>
                            2015-11-12 15:20:30
                        </td>
                        <td>
                            JAVA
                        </td>
                    </tr>



           </#list>
                    </tbody>
                </table>
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

</div>

<script type="text/javascript">

    easyfk.showLogDetail = function(showcert) {
        $("#logDetailPanel").modal("show");
    }

    easyfk.removeUserLogin = function(){
        $("#unicornAlertDailog_title").html("询问");
        $("#unicornAlertDailog_body").html("确定要XX吗？");
        $("#unicornAlertDailog").modal("show");
    }

    easyfk.searchLog= function(){
        $("#sysLogSearchFormDailog").modal("show");
    }
    easyfk.exportLog= function(){
        $("#sysLogSearchExportFormDailog").modal("show");
    }

    easyfk.doexport= function(){
        $("#sysLogSearchExportFormDailog").modal("hide");
         $("#processBarDailog_title").html("正在导出日志，请稍候...");
        $("#processBarDailog").modal("show");
    }

</script>


<div id="logDetailPanel" class="modal hide fade" role="dialog" aria-labelledby="logDetailLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="logDetailLabel">
            日志详情
        </h3>
    </div>
    <div class="modal-body">
      <div class="control-group">
        <div class="controls">
             <div style="height: 300px;">
                 系统运行过程中产生的日志系统运行过程中产生的日志系统运行过程中产生的日志 系统运行过程中产生的日志系统运行过程中产生的日志系统运行过程中产生的日志 系统运行过程中产生的日志系统运行过程中产生的日志系统运行过程中产生的日志 系统运行过程中产生的日志系统运行过程中产生的日志系统运行过程中产生的日志 系统运行过程中产生的日志系统运行过程中产生的日志系统运行过程中产生的日志 系统运行过程中产生的日志系统运行过程中产生的日志系统运行过程中产生的日志 系统运行过程中产生的日志系统运行过程中产生的日志系统运行过程中产生的日志 系统运行过程中产生的日志系统运行过程中产生的日志系统运行过程中产生的日志 系统运行过程中产生的日志系统运行过程中产生的日志系统运行过程中产生的日志 系统运行过程中产生的日志系统运行过程中产生的日志系统运行过程中产生的日志
             </div>
        </div>
      </div>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="sysLogSearchFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>
</div>




<input type="hidden"   id="sysLogSearchFormAction" value="">
<div id="sysLogSearchFormDailog" class="modal hide fade" role="dialog" aria-labelledby="sysLogSearchFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="sysLogSearchFormLabel">
            查询日志
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="sysLogSearchForm" id="sysLogSearchForm">
            <div class="control-group">
                <label class="control-label">关键字</label>
                <div class="controls">
                    <input type="text" id="description" name="description"  />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">日志级别</label>
                <div class="controls">
                    <select>
                        <option >信息</option>
                        <option >警告</option>
                        <option >错误</option>
                        <option >严重错误</option>
                        <option >致命错误</option>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">来源</label>
                <div class="controls">
                    <input type="text" id="description" name="description"  style="width: 100px;"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">时间段</label>
                <div class="controls">
                    <input type="text" data-date="12-02-2012" data-date-format="dd-mm-yyyy" value="12-02-2012" class="datepicker" style="width: 100px;"/>-<input type="text" data-date="12-02-2012" data-date-format="dd-mm-yyyy" value="12-02-2012" class="datepicker"  style="width: 100px;"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="sysLogSearchFormSubmit" onclick="easyfk.dosearch()">搜索</button>
        <button class="btn" id="sysLogSearchFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>

<input type="hidden"   id="sysLogSearchExportFormAction" value="">
<div id="sysLogSearchExportFormDailog" class="modal hide fade" role="dialog" aria-labelledby="sysLogSearchExportFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="sysLogSearchExportFormLabel">
            导出日志
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="sysLogSearchExportForm" id="sysLogSearchExportForm">
            <div class="control-group">
                <label class="control-label">关键字</label>
                <div class="controls">
                    <input type="text" id="description" name="description"  />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">日志级别</label>
                <div class="controls">
                    <select>
                        <option >信息</option>
                        <option >警告</option>
                        <option >错误</option>
                        <option >严重错误</option>
                        <option >致命错误</option>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">来源</label>
                <div class="controls">
                    <input type="text" id="description" name="description"  style="width: 100px;"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">时间段</label>
                <div class="controls">
                    <input type="text" data-date="12-02-2012" data-date-format="dd-mm-yyyy" value="12-02-2012" class="datepicker" style="width: 100px;"/>-<input type="text" data-date="12-02-2012" data-date-format="dd-mm-yyyy" value="12-02-2012" class="datepicker"  style="width: 100px;"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="sysLogSearchExportFormSubmit" onclick="easyfk.doexport()">导出</button>
        <button class="btn" id="sysLogSearchExportFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>
</@easyfkDecoratorScreen>










