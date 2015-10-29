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
package cn.gorun8.easyfk.base.conversion;

import java.io.StringWriter;

import cn.gorun8.easyfk.base.json.JSONWriter;

public interface JSONResult {
    JSONWriter getWriter();
    String getResult();

    public static class Indenting implements JSONResult {
        private final StringWriter sw = new StringWriter();
        private final JSONWriter writer = new JSONWriter(sw);

        public JSONWriter getWriter() {
            return writer;
        }

        public String getResult() {
            return sw.toString();
        }
    }
}

