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

import cn.gorun8.easyfk.security.dao.DBCacheDao;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 *  DB 管理
 * @author kuang hj
 *
 */
public class JDBCManager implements  EasyFKManager{
	// 0 - never expire
	private int expire = 0;

	@Autowired
	DBCacheDao dBCacheDao;
	/**
	 * 初始化方法
	 */
	public void init(){

	}
	
	/**
	 * get value from redis
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key){
		byte[] value = null;
		String keyStr = new String(key);
		value = dBCacheDao.get(keyStr);
		return value;
	}
	
	/**
	 * set 
	 * @param key
	 * @param value
	 * @return
	 */
	public byte[] set(byte[] key,byte[] value){
		String keyStr = new String(key);
		dBCacheDao.set(keyStr,value);
        return value;
	}
	
	/**
	 * set 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public byte[] set(byte[] key,byte[] value,int expire){
		set(key,value);
		return value;
	}
	
	/**
	 * del
	 * @param key
	 */
	public void del(byte[] key){
		String keyStr = new String(key);
		dBCacheDao.del(keyStr);
	}
	
	/**
	 * flush
	 */
	public void flushDB(){
		dBCacheDao.clear();
	}
	
	/**
	 * size
	 */
	public Long dbSize(){
		Long dbSize = 0L;
		dbSize = dBCacheDao.dbSize();
		return dbSize;
	}

	/**
	 * keys
	 * @param pattern
	 * @return
	 */
	public Set<byte[]> keys(String pattern){
		Set<byte[]> keys = null;
//		Jedis jedis = jedisPool.getResource();
//		try{
//			keys = jedis.keys(pattern.getBytes());
//		}finally{
//			jedisPool.returnResource(jedis);
//		}
		return keys;
	}
	


	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

}
