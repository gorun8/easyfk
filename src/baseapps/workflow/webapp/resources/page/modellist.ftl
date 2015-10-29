<#--
Project:Easy Web Framework

Description: This project is based on much more open source projects than ever before,
             and can be applied to mostly web development environment.
Author:hezhiping   Email:110476592@qq.com


===================================================================================================


-->
<decorator-screen name="body" location="component://workflow/webapp/resources/page/appcommon.ftl">

<div class="easyui-layout" data-options="fit:true">
   <div data-options="region:'north'" class="main_bar">
      <div class="main_tit">标准数据表格</div> 
   </div>
  <div data-options="region:'center',border:false">
    <table class="easyui-datagrid" data-options="fitColumns: true,
            												rownumbers:true,
            												striped:true,
                                                            fit: true,
                                                            collapsible:true,
                                                            method:'get',
															pageList:[10,20,30,40,50],
                                                    		pagination:true">
      <thead>
                    <tr>
                    	<th data-options="width:40,checkbox:'true'"></th>
                        <th data-options="field:'date',width:150">ID</th>
                        <th data-options="field:'user',width:80">名称</th>
						<th data-options="field:'stat',width:80,align:'center'">当前状态</th>
                    </tr>
      </thead>
     
	  <tbody>
	   <#list modelList as item>
	  
                    <tr onDblClick="window.location='view.htm'">
						<td></td>
                    	<td>${item.id?default('')}</td>
						<td>${item.name?default('')}</td>
						<td>
						<a href="/workflow/service/editor?id=${item.id}" target="_bank">编辑</a></td>
                    </tr>
      </#list>
					   
	  </tbody>
    </table>
  </div>
</div>

 
</decorator-screen>
 


