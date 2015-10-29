 
 <#include "component://install/webapp/resources/page/header.ftl">
				<h1 style="color:#666666">欢迎安装EasyFK,请仔细阅读以下协议</h1>
 
				<div class="gray_box">
					<div class="box" style="height:314px; overflow-y:auto">
						<h1>用户协议</h1> 

<br />感谢您选择easyfk。希望EasyFK能为您提供一个强大高效的安全应用开发解决方案。
<br />
		EasyFK 提供了创建基于最新J2EE/XML规范和技术标准，构建大中型企业级、跨平台、
		跨数据库、跨应用服务器的多层、分布式WEB应用系统的框架。 EasyFK最主要的特点是吸收了大量开源项目的优点，并进行了大量的优化设计与开发
		。为您提供了基于Java的高效、美观大方。
		<a href="author:hezhiping   ,Email:hzpldx@163.com" target="_blank">www.gorun8.cn</a> 拥有对本授权协议的最终解释权。
<br />
<br />协议条款
<br />    1. 您可以在遵守本授权协议的基础上，将本软件应用于非商业用途。
<br />    2. 您将本软件应用于商业用途前，必须购买获得特许授权。
<br />    3. 特许授权用户享有反映和提出意见的权力，相关意见将被作为首要考虑，但没有一定被采纳的承诺或保证。
<br />    4. 未获特许授权之前，不得将本软件用于商业用途（包括但不限于企业网站、经营性网站、以营利为目或实现盈利的网站）。 
<br />    5. 不得对本软件或与之关联的特许授权进行出租、出售、抵押或发放子许可证。
<br />    6. 如果您未能遵守本协议的条款，您的授权将被终止，所被许可的权利将被收回，并承担相应法律责任。
<br />    7. 本软件及所附带的文件是作为不提供任何明确的或隐含的赔偿或担保的形式提供的。
<br />    8. 用户出于自愿而使用本软件，使用之前必须了解使用本软存在的风险，我们不承诺任何形式的使用担保，也不承担任何因使用本软件而产生问题的相关责任。
<br />    9. 我们不对使用本软件构建的站点中的文章或信息承担责任。
<br />
<br />
<br />您一旦开始安装EasyFK，即被视为完全理解并接受本协议的各项条款，协议许可范围以外的行为，将直接违反本授权协议并构成侵权，我们有权随时终止授权，责令停止损害，并保留追究相关责任的权力。<br />					
<br /> 

<#assign nowTimestamp = Static["cn.gorun8.easyfk.base.util.UtilDateTime"].nowTimestamp()>
		版权所有 (c) 2008-${nowTimestamp?string("yyyy")} <a href="author:hezhiping   ,Email:hzpldx@163.com" target="_blank">www.gorun8.cn</a>

 保留所有权利。
<br />
<p class="agree green_box"><label><input type="checkbox" id='agree' /> 我同意上述条款和条件</label></p>
				
</div>
				</div>
				<P>
				<div class="red_box" style='display:none' id='error_div'>
					<img src="style/images/error.gif" width="16" height="15" />
					请认真阅读并同意以上条款
				</div>
			</div>
			<p class="operate"><input class="next" type="button" onclick="check_license();" /></p>
		 <script type='text/javascript'>
			//检查协议阅读状态
			function check_license()
			{
				var is_agree = document.getElementById('agree').checked;
				if(is_agree == true)
				{
					$("#error_div").hide();
					window.location.href='dbconfig';
				}
				else
				{
					$("#error_div").show();
				}
			}
		</script>
  
 <#include "component://install/webapp/resources/page/footer.ftl">