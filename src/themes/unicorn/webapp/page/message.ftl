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

<#if requestAttributes.errorMessageList?has_content><#assign errorMessageList=requestAttributes.errorMessageList></#if>
<#if requestAttributes.eventMessageList?has_content><#assign eventMessageList=requestAttributes.eventMessageList></#if>
<#if requestAttributes.serviceValidationException?exists><#assign serviceValidationException = requestAttributes.serviceValidationException></#if>
<#if requestAttributes.uiLabelMap?has_content><#assign uiLabelMap = requestAttributes.uiLabelMap></#if>

<#-- display the error messages -->
<#--
<#if (errorMessage?has_content || errorMessageList?has_content)>
  <div id="content-messages" class="content-messages errorMessage" onclick="document.getElementById('content-messages').parentNode.removeChild(this)">
    <#if errorMessage?has_content>
      <p>${errorMessage}</p>
     
    </#if>
    <#if errorMessageList?has_content>
      <#list errorMessageList as errorMsg>
        <p>${errorMsg}</p>
      </#list>
    </#if>
  </div>
</#if>

<#if (eventMessage?has_content || eventMessageList?has_content)>
  <div id="content-messages" class="content-messages eventMessage" onclick="document.getElementById('content-messages').parentNode.removeChild(this)">
    <#if eventMessage?has_content>
      <p>${eventMessage}</p>
    </#if>
    <#if eventMessageList?has_content>
      <#list eventMessageList as eventMsg>
        <p>${eventMsg}</p>
      </#list>
    </#if>
  </div>
</#if>
-->
 <div id="messagebox"></div>
<#if (errorMessage?has_content || errorMessageList?has_content)>
    <#if errorMessage?has_content>
     <script type="text/javascript" >
         easyfk.showErrorBox("${errorMessage}");
 	 </script>
    </#if>
    
    <#if errorMessageList?has_content>
      <#assign msgall =""/>
      <#list errorMessageList as errorMsg>
        <#assign msgall = msgall + errorMsg />
      </#list>
      
      
     <script type="text/javascript" >
         easyfk.showErrorBox("${msgall}");
     </script>
    </#if>
 
</#if>
 
<#if (eventMessage?has_content || eventMessageList?has_content)>
    <#if eventMessage?has_content>
      <script type="text/javascript" >
          easyfk.showInfoBox("${eventMessage}");
 	 </script>
    </#if>
    
    <#if eventMessageList?has_content>
      <#assign msgall ="<ul>"/>
      <#list eventMessageList as eventMsg>
        <#assign msgall = msgall +"<li>" + eventMsg + "</li>"/>
      </#list>
      <#assign msgall =msgall + "</ul>"/>
      
     <script type="text/javascript" >
         easyfk.showInfoBox("${msgall}");
 	 </script>
    </#if>
 
</#if> 




