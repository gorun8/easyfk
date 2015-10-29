<#--
Project:Easy Web Framework

Description: This project is based on much more open source projects than ever before,
             and can be applied to mostly web development environment.
Author:hezhiping   Email:110476592@qq.com


===================================================================================================


-->

<div id="content-header">
    <h1>${EASYFK_SUB_TITLE?default("")}</h1>
    <div class="btn-group">
        <a class="btn btn-large tip-bottom" title="管理文件"><i class="icon-file"></i></a>
        <a class="btn btn-large tip-bottom" title="管理用户"><i class="icon-user"></i></a>
        <a class="btn btn-large tip-bottom" title="管理消息"><i class="icon-comment"></i><span class="label label-important">5</span></a>
        <a class="btn btn-large tip-bottom" title="管理订单"><i class="icon-shopping-cart"></i></a>
    </div>
</div>

<#if EASYFK_NAV_BAR?has_content>
    <div id="breadcrumb">
        <#if EASYFK_NAV_BAR.NAV_BAR_DATA?has_content>
            <#list EASYFK_NAV_BAR.NAV_BAR_DATA as item>
                <a href="${item.href?default("#")}" id="${item.id?default("")}"
                   <#if item.tip?has_content>
                   title="${item.tip?default("")}" class="tip-bottom"
                   </#if>
                 ><#if item.style?has_content><i class="${item.style?default("")}"></i></#if>
                ${item.title?default("")}</a>
            </#list>
        </#if>
    </div>
</#if>