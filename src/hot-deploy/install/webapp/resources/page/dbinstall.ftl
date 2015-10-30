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
 <#include "component://install/webapp/resources/page/header.ftl">
  	<h2><img src="style/images/guide_3.gif" width="82" height="15" /></h2>

		<div class="red_box" style='display:none' id='error_div'>
			<img src="style/images/error.gif" width="16" height="15" />
			安装失败：<div id="errormsgpanel"></div>
		</div>

		<div class="gray_box">
			<div class="box">
				
 				<div id='install_state' >
					<strong>安装进度</strong>
					<label>正在安装，需要5到10分钟,请稍后...</label>
					<div class="loading"><span style="width:0px;"></span><img src="style/images/loading.gif" style='width:500px;height:20px' /></div>
				</div>
				<hr />
			 
				<strong>数据库类型</strong>
				 <label for=""> ${dbtype?default('')}</label> 
				  
				 
			<hr />
			<strong>配置信息</strong>
				<table class="form_table">
					<col width="100px" />
					<col />
					<tr>
						<th>数据库地址</th><td><input class="gray" type="text" name='dbip' value='${dbip?default('localhost')}' <#if readOnlyField?has_content> readonly='readonly' </#if> /> </td>
					</tr>
					<tr>
						<th>数据库端口</th><td><input class="gray" type="text" name='dbport' value='${dbport?default('localhost')}' <#if readOnlyField?has_content> readonly='readonly' </#if> /> </td>
					</tr>
								
					<tr>
						<th>数据库名称</th>
						<td><input class="gray" type="text" value='${dbname?default('easyfk')}' name='dbname' <#if readOnlyField?has_content> readonly='readonly' </#if>/><br /><label class="error" id='db_pre_label' style='display:none'><img src="style/images/failed.gif" width="16" height="15" />请填写正确的数据库前缀字符</label></td>
					</tr>
					
					 
					<tr>
						<th>账户</th><td><input class="gray" type="text" name='dbusername' value='${dbusername?default('')}' <#if readOnlyField?has_content> readonly='readonly' </#if>/></td>
					</tr>
					<tr>
						<th>密码</th><td><input class="gray" type="text" name='dbpassword' value='${dbpassword?default('')}' <#if readOnlyField?has_content> readonly='readonly' </#if>/></td>
					</tr>
					    
				</table>

				 <hr />

				<table class="form_table hide">
					<col width="100px" />
					<col />
					<tr>
						<th>管理员账户</th>
						<td>
							<input class="gray" type="text" name='admin_user' value='${admin_user?default('admin')}'   <#if readOnlyField?has_content> readonly='readonly' </#if>/><br />
							<label class="error" id='admin_user_label' style='display:none'><img src="style/images/failed.gif" width="16" height="15" />密码格式不正确，字符在4-12个之间</label>
						</td>
					</tr>
					<tr>
						<th>密码</th>
						<td>
							<input class="gray" type="text" name='admin_pwd' value='${admin_pwd?default('')}' <#if readOnlyField?has_content> readonly='readonly' </#if>/><br />
							<label class="error" id='admin_pwd_label' style='display:none'><img src="style/images/failed.gif" width="16" height="15" />密码格式不正确，字符在6-16个之间</label>
						</td>
					</tr>
					 
				</table>
 				
			</div>
		</div>
	</div>
	<p class="operate"><input class="return" id="returnbutton" type="button" style='display:none' onclick="window.location.href = '/dbconfig';"   /> </p>
  
 

<script type='text/javascript'>
    var easyfk = {};
    easyfk.install = function(ajaxUrl,postparams,callback)
    {
        jQuery.ajax({
            url: ajaxUrl,
            type: 'POST',
            data : JSON.stringify(postparams),
            contentType : 'application/json;charset=utf-8',
            error: function(msg) {
                var msg = "通信出错，可能网络不通";
                $("#error_div").show();
                $("#errormsgpanel")[0].innerHTML = msg;
                $('#returnbutton').show();
            },
            success: function(jsonstr) {
				var obj = jQuery.parseJSON(jsonstr);
				var msg = "";
                var result = false;

				if(obj.hasOwnProperty("errorMessageList"))
                {
                    msg = obj.errorMessageList;
                    result = false;
                }else{
                    if(obj.hasOwnProperty("eventMessageList"))
                    {
                        msg = obj.eventMessageList;
                    }
                    result = true;
                }

                if(obj.hasOwnProperty("responseType"))
				{
					if("success" == obj.hasOwnProperty("responseType"))
					{
                        result = true;
					}else{
                        result = false;
					}
				}


                if(callback != null && callback != undefined)
                {
                    callback(msg,result,obj);
                }
            }
        });
    }


	$(function(){
		var postparams={};
		var ajaxUrl = "installdb";
        easyfk.install(ajaxUrl,postparams,function(msgtxt,rel,obj){
			if(rel){
			   window.location.href = 'finished';
			}else{
                $("#returnbutton").show();
                $("#error_div").show();
                $("#errormsgpanel")[0].innerHTML = msgtxt;
			    $('#install_state').hide();
			}
		});
	});

	//更新进度条
	var g_gorgress_value =0;
	function update_progress()
	{
		var step = 0.01;
		g_gorgress_value += step;
		if(g_gorgress_value>=1)
		{
			g_gorgress_value = 0;
		}
		
		var whole       = $('#install_state img').css('width');
		var nowProgress = parseInt(whole) * parseFloat(g_gorgress_value);
 
		$('#install_state .loading span').css('width',nowProgress);
 
	}
	self.setInterval("update_progress()", 3000);	 
</script>
 
 <#include "component://install/webapp/resources/page/footer.ftl"> 