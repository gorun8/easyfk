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
  <#include "component://install/webapp/install/resources/page/header.ftl">
	<form action='dbinit' method='post'  id="dbinitform"  >
	 
		<h2><img src="style/images/guide_3.gif" width="82" height="15" /></h2>

		<div class="red_box" <#if errList?has_content><#else>style='display:none'</#if> id='error_div'>
			<img src="style/images/error.gif" width="16" height="15" />
			安装发生错误：<label>
			<#if errList?has_content>
				<#list errList as item>
				${item?default("")}
				</#list>
			</#if>
			</label>
		</div>

		<div class="gray_box">
			<div class="box">
				<strong>类型</strong>
				<label for="">
				<select  name='dbtype' id="dbtype">
    				<option <#if dbtype?has_content && dbtype =="MySQL"> selected</#if> value='mysql'>MySQL</option>
    				<option <#if dbtype?has_content && dbtype =="Oracle"> selected</#if> value='oracle'>Oracle</option>
    				</select>
				 </label>
			<hr />
                <strong>数据</strong>
                <label for="">
                    <select  name='dataType' id="dataType">
                        <option <#if dataType?has_content && dataType =="Seed"> selected</#if> value='Seed'>安装初始数据(适用于正式环境)</option>
                        <option <#if dataType?has_content && dataType =="Demo"> selected</#if> value='Demo'>安装测试数据(适用于测试环境)</option>
					</select>
                </label>
                <hr />
			
				<table class="form_table">
					<col width="100px" />
					<col />
					<tr>
						<th>数据库地址</th><td><input class="gray" type="text" name='dbip' value='${dbip?default('localhost')}' <#if readOnlyField?has_content> readonly='readonly' </#if> /><br />
						<label class="error" id='dbip_label' style='display:none'><img src="style/images/failed.gif" width="16" height="15" />请填写数据库地址</label>
						</td>
					</tr>
					<tr>
						<th>数据库端口</th><td><input class="gray" type="text" name='dbport' value='${dbport?default('3306')}' <#if readOnlyField?has_content> readonly='readonly' </#if> /><br />
						<label class="error" id='dbport_label' style='display:none'><img src="style/images/failed.gif" width="16" height="15" />请填写数据库端口</label>
						</td>
					</tr>
					<tr>
						<th>数据库名称</th>
						<td><input class="gray" type="text" value='${dbname?default('easyfk')}' name='dbname' <#if readOnlyField?has_content> readonly='readonly' </#if>/><br /><label class="error" id='dbname_label' style='display:none'><img src="style/images/failed.gif" width="16" height="15" />请填写正确的数据库前缀字符</label></td>
					</tr>
					
					 
					<tr>
						<th>账户</th><td><input class="gray" type="text" name='dbusername' value='${dbusername?default('')}' <#if readOnlyField?has_content> readonly='readonly' </#if>/>
						<br>
						<label class="error" id='dbusername_label' style='display:none'><img src="style/images/failed.gif" width="16" height="15" />请填写数据库账户</label>
						
						</td>
					</tr>
					<tr>
						<th>密码</th><td><input class="gray" type="password" name='dbpassword' value='${dbpassword?default('')}' <#if readOnlyField?has_content> readonly='readonly' </#if>/>
						<br>
						<label class="error" id='dbpassword_label' style='display:none'><img src="style/images/failed.gif" width="16" height="15" />请填写数据库密码</label>
						
						</td>
					</tr>
					   
					<tr>
						<th></th><td><input class="check" id="checkbutton" type="button" onclick="check_mysql();" <#if readOnlyField?has_content> disabled="disabled" </#if>/></td>
					</tr>
				</table>

				<p id='right_p' style='display:none'><img src="style/images/right.gif" width="19" height="18" />数据库连接正确</p>
				<p id='error_p' style='display:none'><img src="style/images/failed.gif" width="16" height="16" />数据库连接不正确</p>
				<p id='right_p_exist' style='display:none'><img src="style/images/failed.gif" width="16" height="16" />数据库不存在，请联系数据库管理员创建数据库</p>
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
							<input class="gray" type="password" name='admin_pwd' value='${admin_pwd?default('')}' <#if readOnlyField?has_content> readonly='readonly' </#if>/><br />
							<label class="error" id='admin_pwd_label' style='display:none'><img src="style/images/failed.gif" width="16" height="15" />密码格式不正确，字符在6-16个之间</label>
						</td>
					</tr>
					<tr>
						<th>再次确认</th>
						<td>
							<input class="gray" type="password" name='admin_repwd' value='${admin_repwd?default('')}'  <#if readOnlyField?has_content> readonly='readonly' </#if>/><br />
							<label class="error" id='admin_repwd_label' style='display:none'><img src="style/images/failed.gif" width="16" height="15" />二次密码输入的不一致</label>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<p class="operate"><input class="return" type="button" onclick="window.location.href = '/';" <#if readOnlyField?has_content>  disabled="disabled" </#if> /><input class="next" id="nextbutton" onclick="check_form()"  type="button" value='' <#if readOnlyField?has_content>  disabled="disabled" </#if>/></p>
</form>
		 
<script type='text/javascript'>
	 
	 
	function check_form()
	{
		$('label.error').hide();
		var checkObj   =
		{
/*			dbip       :/.^\\S+$,
			dbname     :/.^\\S+$,
			dbusername :/.^\\S+$,
			dbport     :/.^[1-9]\\d*$, 
			dbpassword :/.^\\S+$,
			admin_pwd :/.{6,16}/i
			*/
		};

		for(val in checkObj)
		{
			var matchResult = $.trim($('[name="'+val+'"]').val()).match(checkObj[val]);
			if(matchResult == null)
			{
				$('[name="'+val+'"]').focus();
				$('#'+val+'_label').show();
				return false;
			}
		}

		if($('[name="admin_repwd"]').val() != $('[name="admin_pwd"]').val())
		{
			$('#admin_repwd_label').show();
			return false;
		}
		
		dochecksetting(function(data){
			if("connectFail" == data){
				$('#right_p').hide();
				$('#error_p').show();
				$('#right_p_exist').hide();
			}else if("dbExist" == data){
				$('#right_p_exist').show();
				$('#error_p').hide();
				$('#right_p').hide();
			}else {
				$('#right_p').show();
				$('#error_p').hide();
				$('#right_p_exist').hide();
				$('#install_state').show();
				$("#dbinitform").submit();
				$('.next').attr('disabled','disabled');
			}
		},function(){
			$('#right_p').hide();
			$('#error_p').show();
			$('#right_p_exist').hide();
		});

		
		 
	}

	//检查mysql链接
	function check_mysql()
	{
		$('#error_p').hide();
		dochecksetting(function(data){
			if("connectFail" == data){
				$('#right_p').hide();
				$('#error_p').show();
				$('#right_p_exist').hide();
			}else if("dbNotExist" == data){
				$('#right_p_exist').show();
				$('#error_p').hide();
				$('#right_p').hide();
			}else {
				$('#right_p').show();
				$('#error_p').hide();
				$('#right_p_exist').hide();
			}
		},function(){
			$('#right_p').hide();
			$('#error_p').show();
			$('#right_p_exist').hide();
		});
	}
	
	function dochecksetting(sucfun,errfun)
	{
		var checkObj   =
		{
	/*		dbip       :/.^\\S+$,
			dbname     :/.^\\S+$,
			dbusername :/.^\\S+$,
			dbport     :/.^[1-9]\\d*$, 
			dbpassword :/.^\\S+$
		*/	
		};

		for(val in checkObj)
		{
			var matchResult = $.trim($('[name="'+val+'"]').val()).match(checkObj[val]);
			if(matchResult == null)
			{
				$('[name="'+val+'"]').focus();
				$('#'+val+'_label').show();
				 
				return;
			}else{
				$('#'+val+'_label').hide();
			
			}
		}
	
		var params = {'dbip':'','dbport':'','dbusername':'','dbpassword':'','dbname':''};
		for(val in params)
		{
			 params[val] = $.trim($('[name="'+val+'"]').val());
		}
		 
		params['dbtype'] =$("#dbtype").val();
        params['dataType'] =$("#dataType").val();

		$.ajax({
			type : 'POST',
			url : 'checkSetting',
			data : JSON.stringify(params),
			contentType : 'application/json;charset=utf-8',
			success : function(data) {
				sucfun(data);
			},
			error : function() {
				errfun();
			}
		});
		 
	}
</script>
 
 <#include "component://install/webapp/install/resources/page/footer.ftl">