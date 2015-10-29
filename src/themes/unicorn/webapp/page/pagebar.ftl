<#--
Project:Easy Web Framework

Description: This project is based on much more open source projects than ever before,
             and can be applied to mostly web development environment.
Author:hezhiping   Email:110476592@qq.com


===================================================================================================


-->



<#if PAGE_DATA?has_content>

<div class="pagination">
    <ul>
        <#if hasPrev?has_content>
            <li><a href="${pageUrl?default('')}1" class="active">首页</a></li>
        <#else>
            <li><a  title="已经是第一页了" class="tip-top">首页</a></li>
        </#if>

        <#list startShowIndex..endShowIndex as ii>
            <li  <#if viewIndex == ii >class="active" </#if>>
                    <a href="${pageUrl?default('')}${ii}">${ii?string}</a>
            </li>
        </#list>
        <#if hasNext?has_content>
            <li><a href="${pageUrl?default('')}${endShowIndex}"  >末页</a></li>
        <#else>
            <li><a   title="已经是最后一页了" class="tip-top" class="inactive">末页</a></li>
        </#if>
    </ul>
</div>
</#if>
