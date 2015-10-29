/*
 * Project:Easy Web Framework
 *
 * Description: This project is based on much more open source projects than ever before,
 *              and can be applied to mostly web development environment.
 * Author:hezhiping   Email:110476592@qq.com
 * 
 * 
 *==========================================================================================
 * 
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



 