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
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>EasyFK</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="shortcut icon" href="/static/ofbiz.ico"/>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/static/css/font-awesome.css" />
    <link rel="stylesheet" href="/static/css/jquery.jscrollpane.css" />
    <script src="/static/js/jquery/jquery.min.js"></script>
    <script src="/static/js/jquery/jquery-ui.custom.js"></script>
    <script src="/static/js/jquery/bootstrap.min.js"></script>
    <script src="/static/js/jquery/bootbox.min.js"></script>
    <script src="/static/js/jquery/jquery.nicescroll.min.js"></script>
    <script charset="utf-8" type="text/javascript" src="/static/js/common.js"></script>
     ${EASYFK_HEADER_ATTACH?default("")}
    <script src="/unicorn/js/commonex.js"></script>
    <style>
        .datepicker, .select2-drop{z-index:99999;}
    </style>
    <link rel="stylesheet" href="/unicorn/style/css/unicorn.css" />
    <!--[if lt IE 9]>
        <script type="text/javascript" src="/static/js/jquery/respond.min.js"></script>
    <![endif]-->
</head>
<body  data-color="grey" class="flat">
<input type="hidden" id="CONTEXT_OBJECT" name="CONTEXT_OBJECT" value="${ctx?default('')}">
<div id="wrapper">
