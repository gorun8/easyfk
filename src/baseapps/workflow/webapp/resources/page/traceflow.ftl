<#--
Project:Easy Web Framework

Description: This project is based on much more open source projects than ever before,
             and can be applied to mostly web development environment.
Author:hezhiping   Email:110476592@qq.com


===================================================================================================


-->
<decorator-screen name="body" location="component://workflow/webapp/resources/page/appcommon.ftl">	
流程状态跟踪<Br>
<img usemap="imgmap" src="/images/${imgurl}" style="border: 5px solid #E6EEF8;;margin-left:300px;"/>
maps = ${maps}
<map name="imgmap">
<#list maps as are>
<area shape="rect" style="border: 5px solid #E6EEF8;" mce_shape="rect" coords="${are}" mce_coords="${are}" href="#" onclick="alert('${are_index}')"/>
</#list>
</map>
</decorator-screen>
 


