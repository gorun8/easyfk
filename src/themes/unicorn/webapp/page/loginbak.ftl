<!DOCTYPE html>
<html lang="zh">
    <head>
        <title>用户登录</title>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="shortcut icon" href="/images/ofbiz.ico" />
		<link rel="stylesheet" type="text/css" href="/images/css/icons.css">
		<link rel="stylesheet" type="text/css" href="/images/css/messages.css"> 
		<link rel="stylesheet" href="/unicorn/style/css/bootstrap.min.css" />
		<link rel="stylesheet" href="/unicorn/style/css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="/unicorn/style/css/unicorn.login.css" />
 
        <script src="/unicorn/js/jquery.min.js"></script>  
        <script charset="utf-8" type="text/javascript" src="/images/js/common.js"></script>
 
    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>
    <body>
        <div id="logo">
            <img src="/unicorn/style/images/logo.png" alt="" />
        </div>
        <div id="loginbox">            
            <form id="loginform" class="form-vertical" method="post" action="/security/login"/>
					<input type="hidden" name="AUTHTYPE" value="PASSWD"/>
					<input type="hidden" name="REMEMBERME" value="0"/>
					<input type="hidden" name="AJAX" value="Y"/>

					<p id="msgTip"></p>
					<div class="control-group">
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-user"></i></span><input name="USERNAME" id="USERNAME" type="text" placeholder="用户名" />
                        </div>
                    </div>
                </div>
				
                <div class="control-group">
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-lock"></i></span><input name="PASSWORD" id="PASSWORD" type="password" placeholder="用户口令" />
                        </div>
                    </div>
                </div>
				<div class="control-group" >
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-user"></i></span><input name='CAPTCHA' style="width:120px;" id='CAPTCHA'  type="text" placeholder="验证码" onblur="easyfk.checkCaptcha('/security/checkcaptcha/userlogin','CAPTCHA','msgTip')"/>
							<img  src='/security/captcha.jpg/userlogin' id='captchaImg' onclick="javascript:easyfk.changeCaptcha('/security/captcha.jpg/userlogin','captchaImg');" style="width:85px;height:30px;"/>
				        </div>
                    </div>
                </div>

                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link" id="to-recover">Lost password?</a></span>
                    <span class="pull-right"><input type="submit" class="btn btn-inverse" value="登录" /></span>
                </div>
				 
            </form>

            <form id="recoverform" action="#" class="form-vertical" />
				<p>Enter your e-mail address below and we will send you instructions how to recover a password.</p>
				<div class="control-group">
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-envelope"></i></span><input type="text" placeholder="E-mail address" />
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link" id="to-login">&lt; Back to login</a></span>
                    <span class="pull-right"><input type="submit" class="btn btn-inverse" value="Recover" /></span>
                </div>
            </form>
        </div>
        
        <script src="/unicorn/js/unicorn.login.js"></script> 
  </body>
</html>        

  
