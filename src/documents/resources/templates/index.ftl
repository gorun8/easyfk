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
<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuHomeId']}"  location="component://@component-name@/webapp/@component-name@/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>


<@easyfkSetNavBar subTitle="首面">
{NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
]}
</@easyfkSetNavBar>


<@easyfkDecoratorScreen name="body" location="component://@component-name@/webapp/@component-name@/resources/page/appcommon.ftl">
${uiLabelMap.@component-name@desc}
    <P>
        分页栏
    </P>
    <@easyfkPageBar pageUrl="/@component-name@/@component-name@demo/listpage?pageSize=10&pageIndex="></@easyfkPageBar>
<P>
    上下文菜单
</P>
    <@easyfkContextMenu>
    {MENU_DATA:[
    {id:'contextNewMenuId',title:'${uiLabelMap.CommonAddNew}',desc:'${uiLabelMap.CommonAddNew}',toggle:'modal',href:'test',style:'btn btn-primary tip-bottom',style2:' icon-plus-sign icon-white'},
    ]}
    </@easyfkContextMenu>

</@easyfkDecoratorScreen>
 


