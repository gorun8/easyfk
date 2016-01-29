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
<div style="display: none;" id="WORK_PAGE_FLAG">
<#--登录页加载完成时会检测是否存在WORK_PAGE_FLAG对象，如果有，会刷新整个页面
    以解决通过ajax加载数据，超时后将登录界面显示的数据区的问题
-->
</div>

<#macro renderMenu menudata>
<ul>

    <#list menudata as item>
        <#if item.sub?has_content>
            <#assign  liclass = ""/>
            <#if EASYFK_MENU_FOCUS_IDS?has_content && EASYFK_MENU_FOCUS_IDS.indexOf(item.id) gt 0>
                <#assign  liclass = "active open"/>
            </#if>

        <li id="${item.id?default('')}" class="${liclass} submenu "  >
            <a href="#" >
                <i class="fa ${item.style?default('')}"></i>
                <span>${item.title?default('')}</span>
                <span class="label">${item.sub.size()}</span>
            </a>
            <@renderMenu menudata = item.sub />
        </li>
        <#else>
            <#assign  liclass = ""/>
            <#if EASYFK_MENU_FOCUS_IDS?has_content && EASYFK_MENU_FOCUS_IDS.indexOf(item.id) gt 0>
                <#assign  liclass = "active"/>
            </#if>

        <li id="${item.id?default('')}" class="${liclass} tip-top"   data-original-title="${item.desc?default('')}">
            <a href="${item.href?default('')}" target="${item.target?default('')}">
                <i class="fa ${item.style?default('')}"></i>
                <span>${item.title?default('')} </span>
            </a>
        </li>
        </#if>
    </#list>
</ul>
</#macro>


<div id="sidebar">
    <div id="search">
        <input type="text" placeholder="请输入关键字..."/><button type="submit" class="tip-right" title="Search"><i class="fa fa-search"></i></button>
    </div>


    <#if EASYFK_MENU?has_content>
        <#if EASYFK_MENU.MENU_DATA?has_content>
            <@renderMenu menudata = EASYFK_MENU.MENU_DATA />
        </#if>
    </#if>
</div>





