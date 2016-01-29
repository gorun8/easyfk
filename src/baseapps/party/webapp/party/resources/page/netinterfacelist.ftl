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

<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuNetConfig','menuNetConfig_1']}" location="component://party/webapp/party/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="接口配置">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'网络配置',tip:'认证授权',href:'${ctx}/partyclsgroup/list'},
    {id:'navid3',title:'接口配置'},
 ]}
</@easyfkSetNavBar>
<@easyfkHeaderAttach>
<script src="/static/js/jquery/validate/jquery.validate.min.js"></script>
<script src="/static/js/jquery/validate/localization/messages_zh.min.js"></script>
</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/netinterfacelist.js"></script>
</@easyfkFooterAttach>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/party/resources/page/appcommon.ftl">
<div class="row-fluid">
    <div class="span12">

        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
                <h5>网络接口列表</h5>

            </div>
            <div class="widget-content">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th style="width:40px;">序号</th>
                        <th>接口</th>
                        <th>地址</th>
                        <th>状态</th>
                        <th>说明</th>
                        <th style="width:80px;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <span class="badge badge-success"  >1</span>
                        </td>
                        <td>
                            eth0
                        </td>
                        <td>
                            192.168.1.1/<br>
                            255.255.225.0/<br>
                            192.168.1.254
                        </td>
                        <td><span class="label label-success">正常</span></td>
                        <td>
                            外网接口
                        </td>
                        <td>
                            <div class="btn-group">
                                <button class="btn btn-mini">操作</button>
                                <button data-toggle="dropdown" class="btn   btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu pull-right">
                                    <li><a href="#" onclick="easyfk.editInterface()">修改</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.disableInterface()">禁用</a></li>
                                     
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span class="badge badge-warning"  >2</span>
                        </td>
                        <td>
                            eth1
                        </td>
                        <td>
                            192.168.1.1/<br>
                            255.255.225.0/<br>
                            192.168.1.254
                        </td>
                        <td><span class="label label-warning">故障</span></td>
                        <td>
                           连接数据库服务器
                        </td>
                        <td>
                            <div class="btn-group">
                                <button class="btn btn-mini">操作</button>
                                <button data-toggle="dropdown" class="btn   btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu pull-right">
                                    <li><a href="#" onclick="easyfk.editInterface()">修改</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.disableInterface()">修复</a></li>
                                    <li><a href="#" onclick="easyfk.disableInterface()">禁用</a></li>

                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span class="badge  "  >3</span>
                        </td>
                        <td>
                            eth0
                        </td>
                        <td>
                            192.168.1.3/<br>
                            255.255.225.0/<br>
                            192.168.1.254
                        </td>
                        <td><span class="label  ">未连接</span></td>
                        <td>
                            备用接口
                        </td>
                        <td>
                            <div class="btn-group">
                                <button class="btn btn-mini">操作</button>
                                <button data-toggle="dropdown" class="btn   btn-mini dropdown-toggle"><span class="caret"></span></button>
                                <ul class="dropdown-menu pull-right">
                                    <li><a href="#" onclick="easyfk.editInterface()">修改</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" onclick="easyfk.disableInterface()">禁用</a></li>

                                </ul>
                            </div>
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



    easyfk.editInterface = function () {
        $("#interfaceFormLabel").html("修改接口");
        $("#interfaceFormPanel").modal("show");
    }




    easyfk.disableInterface = function(){
        $("#unicornAlertDailog_title").html("询问");
        $("#unicornAlertDailog_body").html("确定要XX吗？");
        $("#unicornAlertDailog").modal("show");

    }


</script>

<input type="hidden" id="interfaceFormAction">
<div id="interfaceFormPanel" class="modal  fade" role="dialog" aria-labelledby="interfaceFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="interfaceFormLabel">
            修改接口设置
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="interfaceForm" id="interfaceForm">
            <input type="hidden" name="partyId" id="partyId">
            <div class="control-group certcontent">
                <label class="control-label">IP地址</label>
                <div class="controls">
                   <input type="text" id="ip" value="192.168.1.1">
                </div>
            </div>
            <div class="control-group certcontent">
                <label class="control-label">子网掩码</label>
                <div class="controls">
                    <input type="text" id="ip" value="255.255.255.0">
                </div>
            </div>
            <div class="control-group certcontent">
                <label class="control-label">默认网关</label>
                <div class="controls">
                    <input type="text" id="ip" value="192.168.1.254">
                </div>
            </div>

        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="interfaceFormSubmit" >保存</button>
        <button class="btn" id="interfaceFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>











