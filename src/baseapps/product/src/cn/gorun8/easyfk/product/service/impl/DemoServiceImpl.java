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
package cn.gorun8.easyfk.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.product.dao.DemoReadDao;
import cn.gorun8.easyfk.product.dao.DemoWriteDao;
import java.util.List;
import cn.gorun8.easyfk.product.service.DemoService;

/**
 * 
 * demo 服务接口实现类
 *
 */

@Service("productDemoService")
public class DemoServiceImpl implements DemoService{

	@Autowired
	private DemoReadDao demoReadDao;
	@Autowired
	private DemoWriteDao demoWriteDao;

	@Override
	public void getDemo() {

		System.out.println("sevice autowired success");
	}

	public List<GenericValue> listByPage()
	{
		return null;
	}

}
