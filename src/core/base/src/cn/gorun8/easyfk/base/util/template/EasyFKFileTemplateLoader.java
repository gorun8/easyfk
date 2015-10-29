package cn.gorun8.easyfk.base.util.template;

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
