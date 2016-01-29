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

<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuPartygCommunite','menuPartygCommunite_6']}" location="component://party/webapp/party/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="即时消息">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'公告和消息',tip:'公告和消息',href:'${ctx}/partyclsgroup/list'},
    {id:'navid3',title:'即时消息'},
 ]}
</@easyfkSetNavBar>
<@easyfkHeaderAttach>
<script src="/static/js/jquery/validate/jquery.validate.min.js"></script>
<script src="/static/js/jquery/validate/localization/messages_zh.min.js"></script>
</@easyfkHeaderAttach>
<@easyfkFooterAttach>
<script src="/party/js/chat.js"></script>
</@easyfkFooterAttach>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/party/resources/page/appcommon.ftl">

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="widget-box widget-chat">
                <div class="widget-title">
								<span class="icon">
									<i class="icon-comment"></i>
								</span>
                    <h5>即时消息</h5>
                </div>
                <div class="widget-content nopadding">
                    <div class="chat-content panel-left">
                        <div class="chat-messages" id="chat-messages">
                            <div id="chat-messages-inner"></div>
                        </div>
                        <div class="chat-message well">
                            <button class="btn btn-success">发送</button>
										<span class="input-box">
											<input type="text" name="msg-box" id="msg-box" />
										</span>
                        </div>
                    </div>
                    <div class="chat-users panel-right">
                        <div class="panel-title"><h5>在线用户</h5></div>
                        <div class="panel-content nopadding">
                            <ul class="contact-list">
                                <li id="user-michelle" class="online new"><a href="#"><img alt="" src="/unicorn/style/images/demo/av1.jpg" /> <span>Michelle</span></a><span class="msg-count badge badge-info">3</span></li>
                                <li id="user-neytiri"><a href="#"><img alt="" src="/unicorn/style/images/demo/av2.jpg" /> <span>Neytiri</span></a></li>
                                <li id="user-cartoon-man" class="online"><a href="#"><img alt="" src="/unicorn/style/images/demo/av3.jpg" /> <span>张三</span></a></li>
                                <li id="user-rick-newton" class="online"><a href="#"><img alt="" src="/unicorn/style/images/demo/av1.jpg" /> <span>李四</span></a></li>
                                <li id="user-zack-renson" class="online new"><a href="#"><img alt="" src="/unicorn/style/images/demo/av2.jpg" /> <span>Zack Renson</span></a><span class="msg-count badge badge-info">1</span></li>
                                <li id="user-vladimir-ivanov"><a href="#"><img alt="" src="/unicorn/style/images/demo/av3.jpg" /> <span>Wladimir Ivanov</span></a></li>
                                <li id="user-master-moda"><a href="#"><img alt="" src="/unicorn/style/images/demo/av1.jpg" /> <span>Master Moda</span></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</@easyfkDecoratorScreen>






