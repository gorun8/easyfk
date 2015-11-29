/*
 * Project:Easy Web Framework
 * Description:
 * EasyFK stands for Easy Web Framework.It's an open source product for E-Business / E-Commerce.It
 * was launched by a chinese Hezhiping(QQ:110476592) in 2015.The goal of EasyFK is to  provide a
 * foundation and starting point for reliable, secure , simple-to-use ,cost-effective ,scalable
 * and suitable-for-Chinese E-Business / E-Commerce solutions. With EasyFK, you can get started
 * right away without the huge deployment and maintenance costs of E-Business / E-Commerce systems.
 * Of course, you can customize it or use it as a framework to implement your most challenging business needs.
 * EasyFk is licensed under the Apache License Version 2.0.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Author:hezhiping   Email:110476592@qq.com Date:
 */

easyfk.confirm= function(msg,callback){
    $("#unicornAlertDailog_title").html("询问");
    $("#unicornAlertDailog_body").html(msg);
    $("#unicornAlertDailog_ok").unbind( "click" );
    $("#unicornAlertDailog_ok").click(function(){
        $("#unicornAlertDailog").modal("hide");
        callback(true);
    });
    $("#unicornAlertDailog_cancel").unbind( "click" );
    $("#unicornAlertDailog_cancel").click(function(){
        $("#unicornAlertDailog").modal("hide");
        callback(false);
    });

    $("#unicornAlertDailog").modal({backdrop:'static',keyboard:false,show:true});
    return false;
}

easyfk.showProgress = function(message,title,closetime){
    $("#processBarDailog").modal({backdrop:'static',keyboard:false,show:true});
}

easyfk.hideProgress= function(){
    $("#processBarDailog").modal("hide");
}