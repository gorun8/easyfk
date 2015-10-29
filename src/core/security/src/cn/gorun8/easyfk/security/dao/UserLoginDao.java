package cn.gorun8.easyfk.security.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.gorun8.easyfk.entity.GenericValue;

/**
 * 访问用户数据
 * @author:hezhiping(110476592@qq.com)
 *
 */
@Repository
public interface UserLoginDao {
	/**
	 * 根据logonId返回用户信息
	 * @param userLoginId
	 * @return
	 */
	public GenericValue findUserLogin(@Param("userLoginId") String userLoginId);
	
	/**
	 * 根据ticket返回用户信息
	 * @param ticket
	 * @return
	 */
	public GenericValue findUserByTicket(@Param("ticket") String ticket);
	
	
	/**
	 * 保存user信息
	 * @param userLogin
	 */
	public void saveUserLogin(@Param("userLogin") GenericValue userLogin);
	
	/**
	 * 保存票据
	 * @param userLoginId
	 * @param ticket
	 */
	public void saveTicket(@Param("userLoginId")String userLoginId,@Param("ticket")String ticket);
	
	}
