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
package cn.gorun8.easyfk.base.util.template;



import java.io.*;
import java.nio.charset.Charset;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.FileUtil;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.base.util.UtilXml;

import freemarker.cache.FileTemplateLoader;

/**
 * 支持通过component://协议访问不同组件的ftl文件
 *
 * @author:hezhiping(110476592@qq.com)
 */
public class EasyFKFileTemplateLoader extends FileTemplateLoader {
    static final String module = EasyFKFileTemplateLoader.class.toString();


    public EasyFKFileTemplateLoader() throws IOException {
        super();
    }

    public EasyFKFileTemplateLoader(final File baseDir) throws IOException {
        super(baseDir, false);
    }

    public Object findTemplateSource(final String name) throws IOException {
        if (name.contains("component://")) {
            File filePath = FileUtil.getFile(name);
            if (!filePath.isFile() || !filePath.exists()) {
                filePath = null;
            }

            return filePath;
        }

        return super.findTemplateSource(name);

    }


}
