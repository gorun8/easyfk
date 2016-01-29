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
        title:'系统状态',
        desc:'显示系统状态监控的信息',
        style:'fa fa-home',
        href:'${ctx}'
       },
      {
      id:'menuPartygpgs',
      title:'机构和用户',
      desc:'管理组织机构和组织机构下的用户',
      style:'fa-home',
          sub:[
          {
          id:'menuPartygpgs1',
          title:'组织机构',
          desc:'管理组织机构',
          style:'fa-tint',
          href:'${ctx}/partyclsgroup/list'
          },
          {
          id:'menuPartygpgs2',
          title:'三员管理',
          desc:'显示具有三员权限的登录帐号',
          style:'fa-tint',
          href:'${ctx}/page/adminpartylist.ftl'
          }]
      },
      {
      id:'menuPartyAuth',
      title:'权限管理',
      desc:'对用户进行授权',
      style:'fa-qrcode',
      sub:[
      {
      id:'menuPartyAuth_2',
      title:'角色授限',
      desc:'管理角色和为角色授权',
      style:'fa-tint',
      href:'${ctx}/page/partyrolelist.ftl',
      target:'_self'
      },
      {
      id:'menuPartyAuth_3',
      title:'资源管理',
      desc:'管理可授权的资源',
      style:'fa-tint',
      href:'${ctx}/page/resourcelist.ftl',
      target:'_self'
      },
      ]
      }
        ,
      {
      id:'menuPartygCert',
      title:'证书管理',
      desc:'证书管理',
      style:'fa-th-list',
          sub:[
              {
              id:'menuPartygCert_1',
              title:'服务器证书',
              desc:'配置代表服务器身份的证书',
              style:'fa-tint',
              href:'${ctx}/page/servercert.ftl',
              target:'_self'
              },
              {
              id:'menuPartygCert_2',
              title:'CA证书',
              desc:'CA证书',
              style:'fa-tint',
              href:'${ctx}/page/calist.ftl',
              target:'_self'
              },
              {
              id:'menuPartygCert_3',
              title:'CRL黑表',
              desc:'CRL黑表',
              style:'fa-tint',
              href:'${ctx}/page/crllist.ftl',
              target:'_self'
              }
          ]
      },
      {
      id:'menuNetConfig',
      title:'网络配置',
      desc:'网络配置',
      style:'fa-random',
      sub:[
          {
          id:'menuNetConfig_1',
          title:'网络接口',
          desc:'配置网络接口的IP、网关、DNS等',
          style:'fa-tint',
          href:'${ctx}/page/netinterfacelist.ftl',
          target:'_self'
          },
          {
          id:'menuNetConfig_2',
          title:'服务端口',
          desc:'配置WEB服务端口、存贮服务端口等',
          style:'fa-tint',
          href:'${ctx}/page/serverparam.ftl',
          target:'_self'
          }
      ]
      },
      {
      id:'menuPartygSetting',
      title:'参数设置',
      desc:'参数设置',
      style:'fa-cog',
      sub:[
          {
          id:'menuPartygSetting_1',
          title:'密码安全',
          desc:'配置密码安全相关的参数',
          style:'fa-tint',
          href:'${ctx}/page/secparam.ftl',
          target:'_self'
          },
          {
          id:'menuPartygSetting_2',
          title:'认证参数配置',
          desc:'配置认证的凭证类型、锁定策略、验证码策略等',
          style:'fa-tint',
          href:'${ctx}/page/authparam.ftl',
          target:'_self'
          },

          {
          id:'menuPartygSetting_3',
          title:'日志配置',
          desc:'配置日志级别、告警邮件、远程日志服务地址等',
          style:'fa-tint',
          href:'${ctx}/page/logparam.ftl',
          target:'_self'
          }
          ]
      },
      {
      id:'menuPartygLogs',
      title:'日志管理',
      desc:'审计和管理日志数据',
      style:'fa-list-alt',
      sub:[

      {
      id:'menuPartygLogs_0',
      title:'日志概况',
      desc:'显示日志的整体状态',
      style:'fa-tint',
      href:'${ctx}/page/logchartreport.ftl',
      target:'_self'
      },
      {
      id:'menuPartygLogs_1',
      title:'系统日志',
      desc:'系统运行过程中产生的日志',
      style:'fa-tint',
      href:'${ctx}/page/sysloglist.ftl',
      target:'_self'
      },
      {
      id:'menuPartygLogs_2',
      title:'操作日志',
      desc:'系统管理员和一般用户进行操作时产生的日志',
      style:'fa-tint',
      href:'${ctx}/page/oploglist.ftl',
      target:'_self'
      },
      {
      id:'menuPartygSetting_3',
      title:'日志配置',
      desc:'配置日志级别、告警邮件、远程日志服务地址等',
      style:'fa-tint',
      href:'${ctx}/page/logparam.ftl',
      target:'_self'
      }
      ]
      },
      {
      id:'menuPartygCommunite',
      title:'公告和消息',
      desc:'发布公告或消息',
      style:'fa-comment',
          sub:[
              {
              id:'menuPartygCommunite_0',
              title:'收件箱',
              desc:'我收到的所有消息',
              style:'fa-tint',
              href:'${ctx}/page/inbox.ftl',
              target:'_self'
              }
              ,
              {
              id:'menuPartygCommunite_4',
              title:'发件箱',
              desc:'我发出的所有消息',
              style:'fa-tint',
              href:'${ctx}/page/outbox.ftl',
              target:'_self'
              }
              ,
              {
              id:'menuPartygCommunite_5',
              title:'草稿箱',
              desc:'我写的消息草稿',
              style:'fa-tint',
              href:'${ctx}/page/trash.ftl',
              target:'_self'
              },
              {
              id:'menuPartygCommunite_6',
              title:'即时消息',
              desc:'即时消息',
              style:'fa-tint',
              href:'${ctx}/page/chat.ftl',
              target:'_bank'
              }

          ]
      },
      {
      id:'menuPartygVersion',
      title:'版本版权',
      desc:'程序模块的版本、版权管理和升级',
      style:'fa-inbox',
      sub:[
      {
          id:'menuPartygVersion_1',
          title:'程序管理',
          desc:'新新程序版本发布、版本检测、升级策略等',
          href:'/party/page/versionctrl.ftl'

      },
      {
          id:'menuPartygVersion_2',
          title:'许可证',
          desc:'许可证的管理',
          href:'${ctx}/page/licence.ftl'

      },

      ]
      }
  ]}