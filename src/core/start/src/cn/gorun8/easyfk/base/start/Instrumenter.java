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
package cn.gorun8.easyfk.base.start;

import java.io.File;
import java.io.IOException;

public interface Instrumenter {
    File getDefaultFile() throws IOException;
    void open(File dataFile, boolean forInstrumenting) throws IOException;
    byte[] instrumentClass(byte[] bytes) throws IOException;
    void close() throws IOException;
}
