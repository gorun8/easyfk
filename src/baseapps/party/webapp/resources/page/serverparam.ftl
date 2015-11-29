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

<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuNetConfig','menuNetConfig_2']}" location="component://party/webapp/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="服务端口配置">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'网络配置',tip:'管理用户角色、权限等',href:'${ctx}/partyclsgroup/list'},
    {id:'navid3',title:'服务端口配置'},
 ]}
</@easyfkSetNavBar>

<@easyfkHeaderAttach>
<script src="/images/js/jquery/validate/jquery.validate.min.js"></script>
<script src="/images/js/jquery/validate/localization/messages_zh.min.js"></script>
</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/serverparam.js"></script>
</@easyfkFooterAttach>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/resources/page/appcommon.ftl">
<div class="row-fluid">
    <div class="span12">
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
                <h5>基本配置</h5>
           
            </div>
            <div class="widget-content nopadding">

                <form class="form-horizontal" method="post" action="#" name="serverparamForm" id="serverparamForm" novalidate="novalidate" />
                <div class="control-group">
                    <label class="control-label">HTTP</label>
                    <div class="controls">
                        <input type="text"  value="80" name="httpPort" id="httpPort" class="input-small" style="width:40px;"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label">HTTPS</label>
                    <div class="controls">
                        <input type="text"  value="" name="httpsPort" id="httpsPort" class="input-small" style="width:40px;"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">存贮服务</label>
                    <div class="controls">
                        <input type="text"  value="" name="storePort" id="storePort"  class="input-small" style="width:40px;"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label"> </label>
                    <div class="controls">
                        <input type="submit" value="保存" class="btn btn-primary">
                    </div>
                </div>

                </form>
            </div>

        </div>
    </div>

</div>


</@easyfkDecoratorScreen>












