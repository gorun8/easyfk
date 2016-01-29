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

<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuPartygSetting','menuPartygSetting_2']}" location="component://party/webapp/party/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="认证参数配置">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'参数配置',tip:'参数配置',href:'${ctx}/partyclsgroup/list'},
    {id:'navid3',title:'认证参数配置'},
 ]}
</@easyfkSetNavBar>
<@easyfkHeaderAttach>
<#--
<link rel="stylesheet" href="/static/css/datepicker.css" />
-->
<link rel="stylesheet" href="/static/css/uniform.css" />
<link rel="stylesheet" href="/static/css/select2.css" />
<#--
<script src="/static/js/jquery/bootstrap-datepicker.js"></script>
-->
<script src="/static/js/jquery/validate/jquery.validate.min.js"></script>
<script src="/static/js/jquery/validate/localization/messages_zh.min.js"></script>
<script src="/static/js/jquery/jquery.uniform.js"></script>
<script src="/static/js/jquery/select2.min.js"></script>
<script src="/static/js/validate.js"></script>
</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/authparam.js"></script>
</@easyfkFooterAttach>


<@easyfkDecoratorScreen name="body" location="component://party/webapp/party/resources/page/appcommon.ftl">
<div class="row-fluid">
    <div class="span12">
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
                <h5>基本信息</h5>
           
            </div>
            <div class="widget-content nopadding">
                <form action="#" method="get" class="form-horizontal" />
                <div class="control-group">
                    <label class="control-label">登录方式</label>
                    <div class="controls">
                        <select>
                            <option />用户名和密码
                            <option />证书
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">允许登录偿试次数</label>
                    <div class="controls">
                        <select>
                            <option />2
                            <option />3
                            <option />4
                            <option />5
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">验证码</label>
                    <div class="controls">
                        <label><input type="radio" name="radios" checked />启用</label>
                        <label><input type="radio" name="radios" /> 不用</label>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">HTTP会话生存时间</label>
                    <div class="controls">
                        <input type="text"  value="30" class="input-small" style="width:40px;"/>(分钟)
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

<div class="row-fluid">
    <div class="span12">
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
                <h5>认证服务器</h5>
            </div>
            <div class="widget-content nopadding">
                <form action="#" method="post" class="form-horizontal" id="authServerForm" name="authServerForm" />
                <div class="control-group">
                    <label class="control-label">协议</label>
                    <div class="controls">
                        <select>
                            <option />LDAP
                            <option />Radis
                            <option />其它
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">IP</label>
                    <div class="controls">
                        <input type="text"  value="" class="input-small" id="authServerIp" name="authServerIp"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">端口</label>
                    <div class="controls">
                        <input type="text"  value="" class="input-small" style="width:60px;" id="authServerPort" name="authServerPort"/>
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
<script type="text/javascript">
    $(document).ready(function(){
        var authRules = {authServerIp:{required:true,url:true},
            authServerPort:{required:true,min:100}
        };
        easyfk.validate("authServerForm",authRules);
    });
</script>
</@easyfkDecoratorScreen>












