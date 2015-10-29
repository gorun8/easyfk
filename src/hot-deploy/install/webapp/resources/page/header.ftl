<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>EasyFK初始安装向导(${currentstep})</title>
<link rel="stylesheet" href="style/install.css" />
<script src="/images/js/jquery/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<#assign currentstep =currentstep?default("1")/>
<div class="container">
	<div class="head">
	<img src="style/images/logo.gif" width="354" height="53" alt="安装向导" /></div>
	<div class="ins_box clearfix">
		<div class="cont clearfix">
			<ul class="step">
				<li id="step_1" <#if currentstep == "1"> class="current" </#if> ></li>
				<li id="step_2" <#if currentstep == "2"> class="current" </#if> ></li>
				<li id="step_3" <#if currentstep == "3"> class="current" </#if> ></li>
				<li id="step_4" <#if currentstep == "4"> class="current" </#if> ></li>
			</ul>
 <div class="log_box"> 