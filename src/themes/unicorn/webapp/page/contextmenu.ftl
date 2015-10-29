<#--
Project:Easy Web Framework

Description: This project is based on much more open source projects than ever before,
             and can be applied to mostly web development environment.
Author:hezhiping   Email:110476592@qq.com


===================================================================================================


-->

<#if EASYFK_CONTEXT_MENU?has_content>
    <#if EASYFK_CONTEXT_MENU.MENU_DATA?has_content>
    <div style="margin-top: 0px;">
        <#list EASYFK_CONTEXT_MENU.MENU_DATA as item>
            <a href="${item.href?default('')}" data-toggle="${item.toggle?default('')}">
                <button class="${item.style?default('')}" title="${item.desc?default("")}"><i
                        class="${item.style2?default('')}"></i>  ${item.title?default('')}</button>
            </a>
        </#list>
    </div>
    </#if>
</#if>