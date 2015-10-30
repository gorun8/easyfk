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
