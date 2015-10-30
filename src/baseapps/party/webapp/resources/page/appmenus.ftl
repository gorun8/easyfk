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
  <#--菜单-->

   {MENU_DATA:[
      {
        id:'menuHomeId',
        title:'首页',
        desc:'显示首面',
        style:'icon-home',
        href:'${ctx}'
       },
      {
      id:'menupartyClsGroupId',
      title:'组织机构',
      desc:'管理组织机构',
      style:'icon-home',
      href:'${ctx}/partyclsgroup/list'
      },
      {
      id:'menuFormId',
      title:'表单元素',
      desc:'表单元素',
      style:'icon-th-list',
          sub:[
              {
              id:'menuFormId_1',
              title:'通用元素',
              desc:'通用元素',
              style:'icon-tint',
              href:'/unicorn/page/form-common.html',
              target:'_bank'
              },
              {
              id:'menuFormId_2',
              title:'表单验证',
              desc:'表单验证',
              style:'icon-tint',
              href:'/unicorn/page/form-validation.html',
              target:'_self'
              },
              {
              id:'menuFormId_3',
              title:'向导',
              desc:'表单向导',
              style:'icon-tint',
              href:'/unicorn/page/form-wizard.html',
              target:'_self'
              }
          ]
      },
      {
      id:'menuButtonId',
      title:'按钮',
      desc:'按钮',
      style:'icon-tint',
      href:'/unicorn/page/buttons.html',
      target:'_bank'
      },
      {
      id:'menuInterfaceId',
      title:'接口',
      desc:'接口',
      style:'icon-pencil',
      href:'/unicorn/page/interface.html',
      target:'_bank'
      },
      {
      id:'menuTableId',
      title:'表格',
      desc:'表格',
      style:'icon-th',
      href:'/unicorn/page/tables.html',
      target:'_bank'
      },
      {
      id:'menuGradleId',
      title:'列表',
      desc:'列表',
      style:'icon-th-list',
      href:'/unicorn/page/grid.html',
      target:'_bank'
      },
      {
      id:'menuPageId',
      title:'页面',
      desc:'页面',
      style:'icon-file',
      sub:[
          {
          id:'menuPageId_1',
          title:'Invoice',
          desc:'Invoice',
          href:'/unicorn/page/invoice.html',
          target:'_bank'
          },
          {
          id:'menuPageId_2',
          title:'聊天',
          desc:'聊天',
          href:'/unicorn/page/chat.html',
          target:'_bank'
          },
          {
          id:'menuPageId_3',
          title:'日历',
          desc:'日历',
          href:'/unicorn/page/calendar.html',
          target:'_bank'
          },
          {
          id:'menuPageId_4',
          title:'日历',
          desc:'日历',
          href:'/unicorn/page/gallery.html',
          target:'_bank'
          }
        ]
      },
      {
      id:'menuChartsId',
      title:'图表',
      desc:'图表',
      style:'icon-signal',
      href:'/unicorn/page/charts.html',
      target:'_bank'
      },
      {
      id:'menuWidgetsId',
      title:'控件',
      desc:'控件',
      style:'icon-inbox',
      href:'/unicorn/page/widgets.html',
      target:'_bank'
      }

  ]}