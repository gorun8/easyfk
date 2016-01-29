<#--
Project:Easy Web Framework

Description: This project is based on much more open source projects than ever before,
             and can be applied to mostly web development environment.
Author:hezhiping   Email:110476592@qq.com


===================================================================================================


-->
<#macro renderButtonsContexMenu>
    <div style="margin-top: 0px;">
        <#list EASYFK_CONTEXT_MENU.MENU_DATA as item>
            <a href="#" onclick="${item.href?default('')}" data-toggle="${item.toggle?default('')}">
                <button class="${item.style?default('')}" title="${item.desc?default("")}"><i
                        class="${item.style2?default('')}"></i>  ${item.title?default('')}</button>
            </a>
        </#list>
    </div>
</#macro>

<#macro renderDropdownContexMenu>
<div class="btn-group open">
    <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle">操作 <span class="caret"></span></button>
    <ul class="dropdown-menu pull-right" >
    <#list EASYFK_CONTEXT_MENU.MENU_DATA as item>
        <#if item.id =="divider">
            <li class="divider"></li>
        <#else>
        <li >
            <a href="${item.href?default('')}" class="tip-left" title="${item.desc?default("")}" data-toggle="${item.toggle?default('')}">
                <i class="${item.style2?default('')}"></i>  ${item.title?default('')}
            </a>
        </li>
        </#if>
    </#list>
    </ul>
</div>

</#macro>

<#if EASYFK_CONTEXT_MENU?has_content>
    <#if EASYFK_CONTEXT_MENU.MENU_DATA?has_content>

     <#if  EASYFK_MENU_STYLE =="Dropdown">
         <@renderDropdownContexMenu></@renderDropdownContexMenu>
     <#else>
         <@renderButtonsContexMenu></@renderButtonsContexMenu>

    </#if>
    </#if>
</#if>

