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
package cn.gorun8.easyfk.base.util.cache;

import java.io.IOException;

import jdbm.RecordManager;
import jdbm.helper.ISerializationHandler;
import jdbm.helper.Serializer;

import cn.gorun8.easyfk.base.util.UtilObject;

/**
 * JDBC Serializer which uses EasyFK internal serialization
 * (needed do to the fact that we do dynamic class loading)
 *
 */
@SuppressWarnings({"serial", "unchecked"})
public class JdbmSerializer implements Serializer, ISerializationHandler {

    public byte[] serialize(Object o) throws IOException {
        return UtilObject.getBytes(o);
    }

    public byte[] serialize(RecordManager recman, long recid, Object o) throws IOException {
        return UtilObject.getBytes(o);
    }

    public Object deserialize(byte[] bytes) throws IOException {
        return UtilObject.getObject(bytes);
    }

    public Object deserialize(RecordManager recman, long recid, byte[] bytes) throws IOException {
        return UtilObject.getObject(bytes);
    }
}
