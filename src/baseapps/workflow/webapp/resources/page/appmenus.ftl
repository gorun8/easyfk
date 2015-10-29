<#--
Project:Easy Web Framework

Description: This project is based on much more open source projects than ever before,
             and can be applied to mostly web development environment.
Author:hezhiping   Email:110476592@qq.com


===================================================================================================


-->
 <div data-options="region:'north'">
  <div class="head_panel" style="height:104px;">
    <div class="headbg">
      <div class="logo"></div>
      <!--logo-->
      <div style="float:right; margin:4px 8px 0px 0px;"> <a href="#" class="easyui-menubutton" menu="#mm1" iconCls="icon-boss" style="color:#eaf2ff;">Administrator</a> <a href="#" class="easyui-menubutton" menu="#mm2" iconCls="icon-help"  style="color:#eaf2ff;">帮助</a> </div>
      <div id="mm1" style="width:150px;">
        <div >个性化</div>
        <div iconCls="icon-key">密码修改</div>
        <div class="menu-sep"></div>
        <div>注销</div>
        <div class="menu-sep"></div>
        <div iconCls="icon-door">退出</div>
      </div>
      <div id="mm2" style="width:100px;">
        <div>帮助</div>
        <div iconCls="icon-exclamation">关于</div>
      </div>
      <div id="navfloat">
        <div id="nav">
          <div class="navtabs">
            <ul>
              <li id="navtab1" class="navtab_selected" onClick="clicknavtab(1);Choice('/blue/page/desktop1.htm')">首 页</li>
              <li id="navtab5" class="navtab_normal" onClick="clicknavtab(5);subnavtab(51);Choice('/blue/page/service/service.htm')">一级菜单(无纵向导航模式)</li>
              <li id="navtab2" class="navtab_normal" onClick="clicknavtab(2);subnavtab(21);Choice('/blue/page/policy/policy.htm')">一级菜单(有右导航模式)</li>
              <li id="navtab3" class="navtab_normal" onClick="clicknavtab(3);subnavtab(31);Choice('/blue/page/monitor/logManage.htm')">一级菜单</li>
              <li id="navtab4" class="navtab_normal" onClick="clicknavtab(4);subnavtab(41);Choice('/blue/page/System/System.htm')">一级菜单</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="navbar">
      <div id="navcontent1" class="subnav" style="display:block"> </div>
      <div id="navcontent2" class="subnav" style="display:none">
        <ul >
          <li id="subnav21" class="subnav_selected" onClick="subnavtab(21);Choice('/blue/page/policy/policy.htm')">组织机构管理</li>
        </ul>
      </div>
      <div id="navcontent3" class="subnav" style="display:none">
        <ul >
		  <li id="subnav31" class="subnav_normal" onClick="subnavtab(31);Choice('/blue/page/monitor/logManage.htm')">管理日志审计</li>
          <li id="subnav32" class="subnav_normal" onClick="subnavtab(32);Choice('/blue/page/HostInspect/HostInspect.htm')">主机监控与审计</li>
          <li id="subnav33" class="subnav_normal" onClick="subnavtab(33);">日志管理</li>
          <li id="subnav34" class="subnav_normal" onClick="subnavtab(34);">终端安全防护系统</li>
          <li id="subnav35" class="subnav_normal" onClick="subnavtab(35);">XXXXXX</li>
        </ul>
      </div>
      <div id="navcontent4" class="subnav" style="display:none">
        <ul >
          <li id="subnav41" class="subnav_normal" onClick="subnavtab(41);Choice('/blue/page/System/System.htm')">服务器管理</li>
          <li id="subnav42" class="subnav_normal" onClick="subnavtab(42);">系统应用</li>
          <li id="subnav43" class="subnav_normal" onClick="subnavtab(43);">管理员管理</li>
          <li id="subnav44" class="subnav_normal" onClick="subnavtab(44);">系统数据备份与恢复</li>
          <li id="subnav45" class="subnav_normal" onClick="subnavtab(45);">服务器参数配置</li>
          <li id="subnav46" class="subnav_normal" onClick="subnavtab(46);">级联单位管理</li>
        </ul>
      </div>
      <div id="navcontent5" class="subnav" style="display:none">
        <ul >
          <li id="subnav51" class="subnav_normal" onClick="subnavtab(51);Choice('/blue/page/service/service.htm')">二级菜单名</li>
          <li id="subnav52" class="subnav_normal" onClick="subnavtab(52);Choice('/blue/page/service/service2.htm')">二级菜单名</li>
		  <li id="subnav53" class="subnav_normal" onClick="subnavtab(53);Choice('/blue/page/service/service3.htm')">二级菜单名</li>
          <li id="subnav54" class="subnav_normal" onClick="subnavtab(54);Choice('/blue/page/service/service4.htm')">二级菜单名</li>
          <li id="subnav55" class="subnav_normal" onClick="subnavtab(55);Choice('/blue/page/service/service5.htm')">二级菜单名</li>
        </ul>
      </div>
      <div id="navcontent6" class="subnav" style="display:none">
        <ul >
          <li id="subnav61" class="subnav_normal" onClick="subnavtab(61);Choice('/blue/page/report/report.htm')">XXXXX</li>
          <li id="subnav62" class="subnav_normal" onClick="subnavtab(62);Choice('/blue/page/report/report2.htm')">XXXXXXXXX</li>
          <li id="subnav63" class="subnav_normal" onClick="subnavtab(63);Choice('/blue/page/report/report3.htm')">XXXXXXXXX</li>
          <li id="subnav64" class="subnav_normal" onClick="subnavtab(64);Choice('/blue/page/report/report4.htm')">XXXXXXX</li>
        </ul>
      </div>
      <div id="navcontent7" class="subnav" style="display:none">
        <ul >
          <li id="subnav71" class="subnav_normal" onClick="subnavtab(71);Choice('/blue/page/audit/audit.htm')">XXXXXX</li>
          <li id="subnav72" class="subnav_normal" onClick="subnavtab(72);Choice('/blue/page/audit/audit2.htm')">XXXXX</li>
          <li id="subnav73" class="subnav_normal" onClick="subnavtab(73);Choice('/blue/page/audit/audit3.htm')">XXXXXX</li>
        </ul>
      </div>
      <div id="navcontent8" class="subnav" style="display:none">
        <ul >
          <li id="subnav81" class="subnav_normal" onClick="subnavtab(81);Choice('/blue/page/config/organize.htm')">XXXXXX</li>
          <li id="subnav82" class="subnav_normal" onClick="subnavtab(82);Choice('/blue/page/config/node.htm')">XXXXX</li>
          <li id="subnav83" class="subnav_normal" onClick="subnavtab(83);Choice('/blue/page/config/role.htm')">XXXXX</li>
          <li id="subnav84" class="subnav_normal" onClick="subnavtab(84);Choice('/blue/page/config/user.htm')">XXXXX</li>
          <li id="subnav85" class="subnav_normal" onClick="subnavtab(85);Choice('/blue/page/config/cert.htm')">XXXXX</li>
        </ul>
      </div>
    </div>
  </div>
</div>
<!--
 <script type="text/javascript"> 
	$(document).ready(function(){
		Choice("/blue/page/desktop1.htm")
	});
</script>
-->