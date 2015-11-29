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
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="shortcut icon" href="/images/ofbiz.ico"/>

    <link rel="stylesheet" href="/images/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/images/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="/images/css/fullcalendar.css"/>
    <link rel="stylesheet" href="/images/css/jquery.gritter.css" />
    <link rel="stylesheet" href="/images/css/bootstrap-switch.min.css" />


    <script src="/images/js/jquery/jquery.min.js" ></script>
    <script src="/images/js/jquery/jquery.ui.custom.js"></script>
    <script src="/images/js/jquery/bootstrap.min.js"></script>
    <script src="/images/js/jquery/jquery.gritter.min.js"></script>
    <script src="/images/js/jquery/jquery.peity.min.js"></script>
    <script src="/images/js/jquery/bootstrap-switch.min.js"></script>


    <script charset="utf-8" type="text/javascript" src="/images/js/common.js"></script>
     ${EASYFK_HEADER_ATTACH?default("")}
    <script src="/unicorn/js/commonex.js"></script>
    <style>
        .datepicker, .select2-drop{z-index:99999;}
    </style>
    <link rel="stylesheet" href="/unicorn/style/css/unicorn.main.css"/>
    <link rel="stylesheet" href="/unicorn/style/css/unicorn.grey.css" class="skin-color"/>
</head>

<body>
<div id="unicornAlertDailog" class="modal hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3 id="unicornAlertDailog_title">提示</h3>
    </div>
    <div class="modal-body">
        <p id="unicornAlertDailog_body">...</p>
    </div>
    <div class="modal-footer">
        <button class="btn" id="unicornAlertDailog_ok">确定</button>
        <button class="btn btn-primary" id="unicornAlertDailog_cancel" data-dismiss="modal" aria-hidden="true">取消</button>
    </div>
</div>

<div id="processBarDailog" class="modal hide">
    <div class="modal-header">
        <h3 id="processBarDailog_title">正在处理，请稍候...</h3>
    </div>
    <div class="modal-body">
        <div class="progress progress-striped active">
            <div style="width: 100%;" class="bar"></div>
        </div>
    </div>
</div>
