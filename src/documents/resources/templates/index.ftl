<#--
Project:Easy Web Framework

Description: This project is based on much more open source projects than ever before,
             and can be applied to mostly web development environment.
Author:hezhiping   Email:110476592@qq.com


===================================================================================================


-->
<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuHomeId']}"  location="component://@component-name@/webapp/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>
<@easyfkSetContextMenu>
{MENU_DATA:[
{id:'contextNewMenuId',title:'新建',desc:'新建',toggle:'modal',href:'test',style:'btn btn-primary tip-bottom',style2:' icon-plus-sign icon-white'},
]}
</@easyfkSetContextMenu>

<@easyfkSetNavBar subTitle="首面">
{NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
]}
</@easyfkSetNavBar>


<@easyfkDecoratorScreen name="body" location="component://@component-name@/webapp/resources/page/appcommon.ftl">
欢迎使用EasyFK！这是@component-title@组件
    <P>
        分页栏
    </P>
    <@easyfkPageBar pageUrl="/@component-name@/@component-name@demo/listpage?pageSize=10&pageIndex="></@easyfkPageBar>
<P>
    上下文菜单
</P>
    <@easyfkContextMenu></@easyfkContextMenu>

</@easyfkDecoratorScreen>
 


