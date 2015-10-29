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
package cn.gorun8.easyfk.@component-name@.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.@component-name@.dao.DemoDao;
import java.util.List;
import cn.gorun8.easyfk.@component-name@.service.DemoService;

/**
 * 
 * demo 服务接口实现类
 *
 */

@Service("@component-name@DemoService")
public class DemoServiceImpl implements DemoService{

	@Autowired
	private DemoDao demoDao;

	@Override
	public void getDemo() {
		demoDao.saveMaster();
		demoDao.saveSlave();
		System.out.println("sevice autowired success");
	}

	public List<GenericValue> listByPage()
	{
		return  demoDao.listByPage();
	}

}
