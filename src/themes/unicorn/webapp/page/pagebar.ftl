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



<#if PAGE_DATA?has_content>

<div class="pagination">
    <ul>
        <#if hasPrev?has_content>
            <li><a href="javascript:easyfk.changePage('${pageUrl?default('')}1')" class="active">首页</a></li>
        <#else>
            <li><a  title="已经是第一页了" class="tip-top">首页</a></li>
        </#if>

        <#list startShowIndex..endShowIndex as ii>
            <li  <#if viewIndex == ii >class="active" </#if>>
                    <a href="javascript:easyfk.changePage('${pageUrl?default('')}${ii}')">${ii?string}</a>
            </li>
        </#list>
        <#if hasNext?has_content>
            <li><a href="javascript:easyfk.changePage('${pageUrl?default('')}${endShowIndex}')"  >末页</a></li>
        <#else>
            <li><a   title="已经是最后一页了" class="tip-top" class="inactive">末页</a></li>
        </#if>
    </ul>
</div>
</#if>
