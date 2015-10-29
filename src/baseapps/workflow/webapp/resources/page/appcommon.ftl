<#--
Project:Easy Web Framework

Description: This project is based on much more open source projects than ever before,
             and can be applied to mostly web development environment.
Author:hezhiping   Email:110476592@qq.com


===================================================================================================


-->
  
<decorator-screen name="body" location="component://webapp/webcommon/ftl/common.ftl">	
	<#include "component://workflow/webapp/resources/page/appmenus.ftl"/>
 	<div id="main" data-options="region:'center'">
 	<decorator-section-body/>
 	</div>
</decorator-screen> 
 
