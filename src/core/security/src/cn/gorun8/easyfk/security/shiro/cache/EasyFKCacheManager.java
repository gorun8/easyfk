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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * redis 缓存管理
 * @author kuang hj
 *
 */
public class EasyFKCacheManager implements CacheManager {


	// fast lookup by name map
	@SuppressWarnings("rawtypes")
	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

	private EasyFKManager easyFKManager;

	/**
	 * The Redis key prefix for caches 
	 */
	private String keyPrefix = "shiro_redis_cache:";
	
	/**
	 * Returns the Redis session keys
	 * prefix.
	 * @return The prefix
	 */
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/**
	 * Sets the Redis sessions key 
	 * prefix.
	 * @param keyPrefix The prefix
	 */
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
	
	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {

		@SuppressWarnings("unchecked")
		Cache<K, V> c = caches.get(name);
		
		if (c == null) {

			// initialize the Redis manager instance
			easyFKManager.init();
			
			// create a new cache instance
			c = new EasyFKCache<K, V>(easyFKManager, keyPrefix);
			
			// add it to the cache collection
			caches.put(name, c);
		}
		return c;
	}

	public EasyFKManager getRedisManager() {
		return easyFKManager;
	}

	public void setEasyFKManager(EasyFKManager easyFKManager) {
		this.easyFKManager = easyFKManager;
	}

}
