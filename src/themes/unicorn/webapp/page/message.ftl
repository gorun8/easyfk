<#--
Project:Easy Web Framework

Description: This project is based on much more open source projects than ever before,
             and can be applied to mostly web development environment.
Author:hezhiping   Email:110476592@qq.com


===================================================================================================


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




