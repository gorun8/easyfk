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
				<h2><img src="style/images/guide_4.gif" width="83" height="17" /></h2>

				<div class="gray_box">
					<div class="box clearfix">
						<p class="red"><img src="style/images/right.gif" width="16" height="15" />说明：</p>
						<p class="green intent">系统已经安装成功，需要重新启动服务才能正常访问所有功能。</p>
						<p class="green intent">如果需要重新安装，请手动删除/core/base/conf/install.lock。然后重新启动服务即可开始安装.</p>
						
						<#--
						<a class="go_index f_l" href="rebootserver.shtm"></a>
						<a class="go_admin f_r" href="/sitecms"></a>
						-->
					</div>
				</div>
			</div>
 <#include "component://install/webapp/install/resources/page/footer.ftl">