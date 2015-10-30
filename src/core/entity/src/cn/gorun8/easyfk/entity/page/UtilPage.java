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
package cn.gorun8.easyfk.entity.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.SqlUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 实现分页，在调用数据查询之前，调用该类的方法即可实现分页。
 * 分页对象Page会存入上下文的PAGE_DATA中。
 */
public class UtilPage {
    public final static  String PAGE_DATA ="PAGE_DATA";

    public static void startPage(HttpServletRequest request,int pageNum, int pageSize) {
        Page page =  PageHelper.startPage(pageNum,pageSize);
        request.setAttribute(PAGE_DATA,page);
    }

    public static void startPage(HttpServletRequest request,int pageNum, int pageSize, boolean count) {
        Page page = PageHelper.startPage(pageNum, pageSize,count);
        request.setAttribute(PAGE_DATA,page);
    }

    public static void startPage(HttpServletRequest request,int pageNum, int pageSize, String orderBy) {
        Page page = PageHelper.startPage(pageNum, pageSize,orderBy);
        request.setAttribute(PAGE_DATA,page);
    }

    public static void startPage(HttpServletRequest request,int pageNum, int pageSize, boolean count, Boolean reasonable) {
         Page page = PageHelper.startPage(pageNum, pageSize,count,reasonable);
        request.setAttribute(PAGE_DATA,page);
    }

    public static void startPage(HttpServletRequest request,int pageNum, int pageSize, boolean count, Boolean reasonable, Boolean pageSizeZero) {
        Page page = PageHelper.startPage(pageNum, pageSize,count,reasonable,pageSizeZero);
        request.setAttribute(PAGE_DATA,page);
    }

    public static void startPage(HttpServletRequest request,Object params) {
        Page page = PageHelper.startPage(params);
        request.setAttribute(PAGE_DATA,page);
    }


}
