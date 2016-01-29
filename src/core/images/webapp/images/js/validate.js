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

easyfk.isFormSubmited = function(formId){
    var rel = $("#"+formId).attr("submited");
    if(rel){
        return true;
    }
    $("#"+formId).attr("submited",true);
    return false;
}

easyfk.resetFormSubmitStatus = function(formId){
    $("#"+formId).removeAttr("submited");
}

/**
 * validate the form only
 * @param formId
 * @param rulesJson
 */
easyfk.validate=function(formId,rulesJson){

    $("#"+formId).validate({
        submitHandler:function(form){
            if(easyfk.isFormSubmited(formId)){
                return ;
            }
            form.submit();
        },
        rules:rulesJson,
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
         },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
         },
        success:function(element){
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        }
        ,
        errorPlacement : function(error, element) {
            var txt = error.text();
            $(element).attr("title",txt);
            $(element).tooltip('destroy');
            $(element).tooltip('show');
        }
    });
}

/**
 * validate the form and handler submit the data
 * @param formId
 * @param rulesJson
 * @param submitHandler
 */
easyfk.validateAndSubmit=function(formId,rulesJson ,submitHandler){

    $("#"+formId).validate({
        submitHandler:submitHandler,
        rules:rulesJson,
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');

        },
        success:function(element){
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');

        }
        ,
        errorPlacement : function(error, element) {
             var txt = error.text();
            $(element).attr("title",txt);
            $(element).tooltip('destroy');
            $(element).tooltip('show');
        }
    });
}