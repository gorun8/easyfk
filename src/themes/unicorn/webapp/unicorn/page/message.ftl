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
<#escape x as x?html>
    <#if requestAttributes.errorMessageList?has_content><#assign errorMessageList=requestAttributes.errorMessageList></#if>
    <#if requestAttributes.eventMessageList?has_content><#assign eventMessageList=requestAttributes.eventMessageList></#if>
    <#if requestAttributes.serviceValidationException?exists><#assign serviceValidationException = requestAttributes.serviceValidationException></#if>
    <#if requestAttributes.uiLabelMap?has_content><#assign uiLabelMap = requestAttributes.uiLabelMap></#if>

    <#if !errorMessage?has_content>
        <#assign errorMessage = requestAttributes._ERROR_MESSAGE_?if_exists>
    </#if>
    <#if !errorMessageList?has_content>
        <#assign errorMessageList = requestAttributes._ERROR_MESSAGE_LIST_?if_exists>
    </#if>
    <#if !eventMessage?has_content>
        <#assign eventMessage = requestAttributes._EVENT_MESSAGE_?if_exists>
    </#if>
    <#if !eventMessageList?has_content>
        <#assign eventMessageList = requestAttributes._EVENT_MESSAGE_LIST_?if_exists>
    </#if>
<div style="z-index:5000;position: relative;margin-left: 150px;margin-right: 150px;">
<#-- display the error messages -->
    <#if (errorMessage?has_content || errorMessageList?has_content)>
        <div class="alert alert-danger alert-block">
            <a class="close" data-dismiss="alert" href="#">×</a>
            <h4 class="alert-heading"><#noescape> ${uiLabelMap.CommonFollowingErrorsOccurred}</#noescape></h4>
            <div style="padding:20px;background-color: #f2dede;border: 1px solid #a94442;">
                <#if errorMessage?has_content>${StringUtil.wrapString(errorMessage)}</#if>
                <#if errorMessageList?has_content>
                    <#list errorMessageList as errorMsg>
                    ${StringUtil.wrapString(errorMsg)}
                    </#list>
                </#if>
            </div>
        </div>
    </#if>
<#-- display the event messages -->
    <#if (eventMessage?has_content || eventMessageList?has_content)>
        <div class="alert alert-info alert-block">
            <a class="close" data-dismiss="alert" href="#">×</a>
            <h4 class="alert-heading"> <#noescape> ${uiLabelMap.CommonFollowingOccurred} </#noescape></h4>
            <div style="padding:20px;background-color: #f2dede;border: 1px solid #31708f;">
                <#if eventMessage?has_content>
                     ${StringUtil.wrapString(eventMessage)}
                </#if>
                <#if eventMessageList?has_content>
                    <#list eventMessageList as eventMsg>
                         ${StringUtil.wrapString(eventMsg)}
                    </#list>
                </#if>
            </div>
        </div>
    </#if>
</div>
</#escape>





