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
import jdbm.recman.BaseRecordManager;

/**
 * Customer JDBM Record Manager
 *
 */
public class JdbmRecordManager implements RecordManager {

    protected BaseRecordManager manager = null;
    protected JdbmSerializer serial = null;

    public JdbmRecordManager(String name) throws IOException {
        manager = new BaseRecordManager(name);
        serial = new JdbmSerializer();
    }

    public ISerializationHandler getSerializationHandler() {
        return serial;
    }

    public RecordManager getBaseRecordManager() {
        return manager;
    }

    public RecordManager getRecordManager() {
        return this;
    }

    public long insert(Object o) throws IOException {
        return insert(o, serial);
    }

    public void update(long l, Object o) throws IOException {
        update(l, o, serial);
    }

    public Object fetch(long l) throws IOException {
        return fetch(l, serial);
    }

    public void close() throws IOException {
        manager.close();
    }

    public void commit() throws IOException {
        manager.commit();
    }

    public void delete(long l) throws IOException {
        manager.delete(l);
    }

    @SuppressWarnings("unchecked")
    public Object fetch(long l, Serializer s) throws IOException {
        return manager.fetch(l, s);
    }

    public long getNamedObject(String name) throws IOException {
        return manager.getNamedObject(name);
    }

    public long getRoot(int i) throws IOException {
        return manager.getRoot(i);
    }

    public int getRootCount() {
        return manager.getRootCount();
    }

    @SuppressWarnings("unchecked")
    public long insert(Object o, Serializer s) throws IOException {
        return manager.insert(o, s);
    }

    public void rollback() throws IOException {
        manager.rollback();
    }

    public void setNamedObject(String s, long l) throws IOException {
        manager.setNamedObject(s, l);
    }

    public void setRoot(int i, long l) throws IOException {
        manager.setRoot(i, l);
    }

    @SuppressWarnings("unchecked")
    public void update(long l, Object o, Serializer s) throws IOException {
        manager.update(l, o, s);
    }
}
