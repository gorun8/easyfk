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
package cn.gorun8.easyfk.security.shiro.cache;


import java.util.Set;

public interface EasyFKManager {

    public void init();
    public byte[] get(byte[] key);
    public Set<byte[]> keys(String pattern);
    public void del(byte[] key);
    public int getExpire();
    public byte[] set(byte[] key,byte[] value,int expire);
    public byte[] set(byte[] key,byte[] value);
    public void flushDB();
    public Long dbSize();
}
