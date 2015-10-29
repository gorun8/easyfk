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

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import cn.gorun8.easyfk.base.util.Debug;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RedisSessionDAO
 *
 *
 */
public class EasyFKSessionDAO extends AbstractSessionDAO {
	public static final String module = EasyFKSessionDAO.class.getName();

	private EasyFKManager easyFKManager;
	
	/**
	 * The Redis key prefix for the sessions 
	 */
	private String keyPrefix = "shiro_redis_session:";
	
	@Override
	public void update(Session session) throws UnknownSessionException {
		this.saveSession(session);
	}
	
	/**
	 * save session
	 * @param session
	 * @throws UnknownSessionException
	 */
	private void saveSession(Session session) throws UnknownSessionException{
		if(session == null || session.getId() == null){
			Debug.logError("session or session id is null", module);
			return;
		}
		
		byte[] key = getByteKey(session.getId());
		byte[] value = SerializeUtils.serialize(session);
		session.setTimeout(easyFKManager.getExpire()*1000);
		this.easyFKManager.set(key, value, easyFKManager.getExpire());
	}

	@Override
	public void delete(Session session) {
		if(session == null || session.getId() == null){
			Debug.logError("session or session id is null", module);
			return;
		}
		easyFKManager.del(this.getByteKey(session.getId()));

	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();
		
		Set<byte[]> keys = easyFKManager.keys(this.keyPrefix + "*");
		if(keys != null && keys.size()>0){
			for(byte[] key:keys){
				Session s = (Session)SerializeUtils.deserialize(easyFKManager.get(key));
				sessions.add(s);
			}
		}
		
		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);  
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if(sessionId == null){
			Debug.logError("session id is null", module);
			return null;
		}
		
		Session s = (Session)SerializeUtils.deserialize(easyFKManager.get(this.getByteKey(sessionId)));
		return s;
	}
	
	/**
	 * 获得byte[]型的key
	 * @param sessionId
	 * @return
	 */
	private byte[] getByteKey(Serializable sessionId){
		String preKey = this.keyPrefix + sessionId;
		return preKey.getBytes();
	}

	public EasyFKManager getEasyFKManager() {
		return easyFKManager;
	}

	public void setEasyFKManager(EasyFKManager easyFKManager) {
		this.easyFKManager = easyFKManager;
		
		/**
		 * 初始化redisManager
		 */
		this.easyFKManager.init();
	}

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
	
	
}
