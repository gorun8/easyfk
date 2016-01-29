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
<link rel="stylesheet" href="/unicorn/style/css/unicorn-login.css"/>

<div id="container">
    <div id="logo">
        <img src="/unicorn/style/images/logo.png" alt="" />
    </div>

    <div id="loginbox">
        <form id="loginform" class="form-vertical" method="post" action="/security/dyn/login"/>
        <input type="hidden" name="authType" value="PASSWD"/>
        <input type="hidden" name="rememberme" value="1"/>
        <input type="hidden" name="captchaid" value="userlogin"/>
        <input type="hidden" name="ajax" id="ajax" value="N"/>
        <p></p>
            <div class="input-group input-sm">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input class="form-control" type="text" name="username" id="username"
                       placeholder="用户名" value="easyfkadmin"/>
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input class="form-control" name="password" id="password"
                       type="password" placeholder="用户口令"
                       value="gorun8.cn"/>
            </div>

            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <div  style="float:left;">
                    <input class="form-control" name="captcha" id="captcha"
                        type="text" placeholder="验证码" style="width:120px;"/>
                    <img src='/security/dyn/captcha.jpg/userlogin' id='captchaImg'
                        onclick="javascript:easyfk.changeCaptcha('/security/dyn/captcha.jpg/userlogin','captchaImg');"
                        style="width:85px;height:33px;"/>
                </div>
            </div>

            <div class="form-actions clearfix">
                <div class="pull-left">
                    <a href="#recoverform" class="flip-link to-recover grey">忘记密码</a>
                </div>
                <input type="submit" class="btn btn-block btn-primary btn-default" value="登录" />
            </div>
            <div class="footer-login">
            <#--
                  <div class="pull-left text">
                      用其它方式登录
                  </div>
                  <div class="pull-right btn-social">
                      <a class="btn btn-facebook" href="#"><i class="fa fa-facebook"></i></a>
                      <a class="btn btn-twitter" href="#"><i class="fa fa-twitter"></i></a>
                      <a class="btn btn-google-plus" href="#"><i class="fa fa-google-plus"></i></a>
                  </div>
              -->
            </div>
        </form>
        <form id="recoverform" action="/security/recover">
            <p>请填写您注册时写的电子邮件，以便找回密码</p>
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                <input class="form-control" type="text" name="email" id="email"
                       placeholder="电子邮件"  />
            </div>
            <div class="form-actions clearfix">
                <div class="pull-left">
                    <a href="#loginform" class="grey flip-link to-login">已经有账号</a>
                </div>
                <div class="pull-right">

                </div>
                <input type="submit" class="btn btn-block btn-inverse" value="找回密码" />
            </div>
        </form>

    </div>
</div>

<script src="/unicorn/js/unicorn.login.js"></script>

<script>

    $(document).ready(function(){
        var loginRules = {username: {required: true,minlength: 6,maxlength: 20},
            password: {required: true,minlength: 6,maxlength: 20},
            captcha: {required: true,minlength: 4,maxlength: 4}
        };
        easyfk.validateAndSubmit("loginform",loginRules,function(form){
            form.submit();
            return false;
        });

        var recoverRules = {email: {required: true,email: true}};
        easyfk.validateAndSubmit("recoverform",recoverRules,function(form){
            form.submit();
            return false;
        });

    <#--
    登录页加载完成时会检测是否存在WORK_PAGE_FLAG对象，如果有，会退出登录
    以解决通过ajax加载数据，超时后将登录界面显示的数据区的问题
    -->
        var falgObj = $("#WORK_PAGE_FLAG")[0];
        if(falgObj){
          document.location.href ="${ctx}/dyn/common/logout";
        }
    });
</script>
