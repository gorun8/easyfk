<link rel="stylesheet" href="/unicorn/style/css/unicorn.login.css"/>
<#--
<div id="logo">
    <img src="/unicorn/style/images/logo.png" alt=""/>
</div>
-->
<div id="loginbox">
    <form id="loginform" class="form-vertical" method="post" action="/security/login"/>
    <input type="hidden" name="AUTHTYPE" value="PASSWD"/>
    <input type="hidden" name="REMEMBERME" value="1"/>
    <input type="hidden" name="CAPTCHAID" value="userlogin"/>
    <input type="hidden" name="AJAX" id="AJAX" value="N"/>

    <p id="msgTip"></p>

    <div class="control-group">
        <div class="controls">
            <div class="input-prepend">
                <span class="add-on"><i class="icon-user"></i></span><input name="USERNAME" id="USERNAME" type="text"
                                                                            placeholder="用户名" value="gorun8admin"/>
            </div>
        </div>
    </div>

    <div class="control-group">
        <div class="controls">
            <div class="input-prepend">
                <span class="add-on"><i class="icon-lock"></i></span><input name="PASSWORD" id="PASSWORD"
                                                                            type="password" placeholder="用户口令"
                                                                            value="gorun8.cn"/>
            </div>
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <div class="input-prepend">
                <span class="add-on"><i class="icon-user"></i></span><input name='CAPTCHA' style="width:120px;"
                                                                            id='CAPTCHA' type="text" placeholder="验证码"
                                                                            onblur="easyfk.checkCaptcha('/security/checkcaptcha/userlogin','CAPTCHA','msgTip')"/>
                <img src='/security/captcha.jpg/userlogin' id='captchaImg'
                     onclick="javascript:easyfk.changeCaptcha('/security/captcha.jpg/userlogin','captchaImg');"
                     style="width:85px;height:30px;"/>
            </div>
        </div>
    </div>

    <div class="form-actions">
        <span class="pull-left"><a href="#" class="flip-link" id="to-recover">忘记密码?</a></span>
        <span class="pull-right"><input type="submit" class="btn btn-inverse" value="登录"/></span>
    </div>

    </form>

    <form id="recoverform" action="#" class="form-vertical"/>
    <p>Enter your e-mail address below and we will send you instructions how to recover a password.</p>

    <div class="control-group">
        <div class="controls">
            <div class="input-prepend">
                <span class="add-on"><i class="icon-envelope"></i></span><input type="text"
                                                                                placeholder="E-mail address"/>
            </div>
        </div>
    </div>
    <div class="form-actions">
        <span class="pull-left"><a href="#" class="flip-link" id="to-login">&lt; Back to login</a></span>
        <span class="pull-right"><input type="submit" class="btn btn-inverse" value="Recover"/></span>
    </div>
    </form>
</div>
<script src="/unicorn/js/unicorn.login.js"></script>
<script>
    $(document).ready(function () {
        $("#loginform").validate({
            rules: {
                USERNAME: {
                    required: true,
                    minlength: 6,
                    maxlength: 20
                },
                PASSWORD: {
                    required: true,
                    minlength: 6,
                    maxlength: 20
                },
                CAPTCHA: {
                    required: true,
                    minlength: 4,
                    maxlength: 4
                }


            },
            errorClass: "help-inline",
            errorElement: "span",
            highlight: function (element, errorClass, validClass) {
                $(element).parents('.control-group').addClass('error');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).parents('.control-group').removeClass('error');
                $(element).parents('.control-group').addClass('success');
            }
        });
    });
</script>
