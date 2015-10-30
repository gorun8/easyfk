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

package cn.gorun8.easyfk.base.util;

import org.apache.log4j.Level;
import org.apache.log4j.net.SyslogAppender;

/**
 * NotifyLevel
 */
@SuppressWarnings("serial")
public class NotifyLevel extends Level {

    public static final int NOTIFY_INT = FATAL_INT + 10000;
    public static final Level NOTIFY = new NotifyLevel(NOTIFY_INT, "NOTIFY", SyslogAppender.LOG_LOCAL0);
    public static final Level notify = new NotifyLevel(NOTIFY_INT, "notify", SyslogAppender.LOG_LOCAL0);

    public NotifyLevel(int level, String levelStr, int syslogEquivalent) {
        super(level, levelStr, syslogEquivalent);
    }
}