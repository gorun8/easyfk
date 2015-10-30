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
 * Author:hezhiping   Email:110476592@qq.com
 */
package cn.gorun8.easyfk.webapp.taglib;

public enum ThemePart {
	//登录页面模板
	VT_LOGIN_TMPLT_LOC,
	//错误页面
	VT_ERR_PAGE_LOC,
	//消息样式模板文件，用于显示各类消息：如提示、警告，错误信息等
	VT_MSG_TMPLT_LOC,
	//导航模板文件
	VT_NAV_BAR_LOC,
	//上下文件菜单模板文件
	VT_CONTEXT_MENU_LOC,
	//分页栏模板
	VT_PAGE_BAR_LOC,
	//页头文件
	VT_HDR_TMPLT_LOC,
	//TOP模板文件
	VT_TOP_PANEL_LOC,
	//LEFT模板文件
	VT_LEFT_PANEL_LOC,
	//RIGHT模板文件
	VT_RIGHT_PANEL_LOC,
	//页脚文件模板文件
	VT_FTR_TMPLT_LOC;

    public String toString() {
        return name();
    }
}



 