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

<div id="content-header">
    <h1>${EASYFK_SUB_TITLE?default("")}</h1>
    <div class="btn-group">
        <a class="btn btn-large tip-bottom" href="/party/" title="系统状态"><i class="fa fa-signal"></i><span class="label label-danger" style="z-index:99">告警</span></a>
        <a class="btn btn-large tip-bottom" href="/party/page/profile.ftl" title="我的信息"><i class="fa fa-user"></i></a>
        <a class="btn btn-large tip-bottom" href="/party/page/inbox.ftl" title="我收到的消息"><i class="fa fa-comment"></i><span class="label label-danger">4</span></a>
        <a class="btn btn-large tip-bottom" title="管理订单"><i class="fa fa-shopping-cart"></i></a>

    </div>
</div>

<#if EASYFK_NAV_BAR?has_content>
    <div id="breadcrumb">
        <#if EASYFK_NAV_BAR.NAV_BAR_DATA?has_content>
            <#list EASYFK_NAV_BAR.NAV_BAR_DATA as item>
                <a href="${item.href?default("#")}" id="${item.id?default("")}"
                   <#if item.tip?has_content>
                        title="${item.tip?default("")}"
                        class="tip-bottom "
                   <#else>
                       class="  current"
                   </#if>
                 >
                    <#if item.style?has_content><i class="${item.style?default("")}"></i></#if>
                ${item.title?default("")}  </a>
            </#list>
        </#if>
    </div>
</#if>


