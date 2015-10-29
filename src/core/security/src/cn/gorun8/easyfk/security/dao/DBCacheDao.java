package cn.gorun8.easyfk.security.dao;

import cn.gorun8.easyfk.entity.GenericValue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 数据库缓存管理
 * @author:hezhiping(110476592@qq.com)
 *
 */
@Repository
public interface DBCacheDao {

	/**
	 * 保存二进制数据
	 * @param key
	 * @param value
	 */
	public void set(@Param("key") String key, @Param("value") byte [] value);
	public byte [] get(@Param("key") String key);
	public void del(@Param("key") String key);
	public void clear();
	public  Long dbSize();

	}
