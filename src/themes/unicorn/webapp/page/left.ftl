<#--
Project:Easy Web Framework

Description: This project is based on much more open source projects than ever before,
             and can be applied to mostly web development environment.
Author:hezhiping   Email:110476592@qq.com


===================================================================================================


-->
<#macro renderMenu menudata>
<ul>
    <#list menudata as item>
        <#if item.sub?has_content>
        <li id="${item.id?default('')}" class="submenu">
            <a href="#">
                <i class="icon ${item.style?default('')}"></i>
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

        <li id="${item.id?default('')}" class="${liclass}">
            <a href="${item.href?default('')}" target="${item.target?default('')}">
                <i class="icon ${item.style?default('')}"></i>
                <span>${item.title?default('')}</span>
            </a>
        </li>
        </#if>
    </#list>
</ul>
</#macro>


<div id="sidebar">
    <a href="#" class="visible-phone"><i class="icon icon-home"></i>Dashboard</a>

    <#if EASYFK_MENU?has_content>
        <#if EASYFK_MENU.MENU_DATA?has_content>
            <@renderMenu menudata = EASYFK_MENU.MENU_DATA />
        </#if>
    </#if>
</div>





