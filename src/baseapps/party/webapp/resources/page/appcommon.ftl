<#--
Project:Easy Web Framework

Description: This project is based on much more open source projects than ever before,
             and can be applied to mostly web development environment.
Author:hezhiping   Email:110476592@qq.com


===================================================================================================

-->

<@easyfkDecoratorScreen name="common-body" location="component://common/webcommon/ftl/common.ftl">
	<@easyfkTopPanel></@easyfkTopPanel>
	<@easyfkLeftPanel></@easyfkLeftPanel>
	<@easyfkRightPanel></@easyfkRightPanel>
<div id="content">
    <@easyfkNavBar></@easyfkNavBar>
    <div class="container-fluid">
		<@easyfkDecoratorBody name="body"/>
    </div>
</div>
</@easyfkDecoratorScreen>
