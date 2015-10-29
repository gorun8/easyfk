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
package cn.gorun8.easyfk.@component-name@.service;

import cn.gorun8.easyfk.entity.GenericValue;
import java.util.List;


/**
 * demo 服务接口
 *
 */
public interface DemoService {
	
	public void getDemo();

	public List<GenericValue> listByPage();
}
