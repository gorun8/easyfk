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

<div id="header">
    <h1><a href="./dashboard.html">EasyFK</a></h1>
</div>

<div id="search">
    <input type="text" placeholder="请输入关键字..." /><button type="submit" class="tip-right" title="搜索"><i class="icon-search icon-white"></i></button>
</div>
<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav btn-group">
    <#--
            <li class="btn btn-inverse"><a title="" href="/party/page/profile.ftl"><i class="icon icon-user"></i> <span class="text">我的</span></a></li>
            <li class="btn btn-inverse dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-envelope"></i> <span class="text">消息</span> <span class="label label-important">5</span> <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a class="sAdd" title="" href="#">新消息</a></li>
                    <li><a class="sInbox" title="" href="#">收件箱</a></li>
                    <li><a class="sOutbox" title="" href="#">发件箱</a></li>
                    <li><a class="sTrash" title="" href="#">草稿箱</a></li>
                </ul>
            </li>
    -->
    <li class="btn btn-inverse"><a title="" href="/party/page/secparam.ftl"><i class="icon icon-cog"></i> <span class="text">设置</span></a></li>
    <li class="btn btn-inverse"><a title="" href="${ctx}/common/logout"><i class="icon icon-share-alt"></i> <span class="text">注销</span></a></li>
    </ul>
</div>
