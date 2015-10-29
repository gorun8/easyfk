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
package cn.gorun8.easyfk.workflow.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gorun8.easyfk.workflow.dao.LeaveDao;
import cn.gorun8.easyfk.workflow.dao.TaskHolderDao;
import cn.gorun8.easyfk.workflow.service.DemoService;

/**
 * 
 *   服务接口实现类
 *
 */

@Service
public class DemoServiceImpl implements DemoService{

	@Autowired
	private LeaveDao leaveDao;
	
	@Autowired
	private TaskHolderDao taskHolderDao;
	
	@Override
	public HashMap getLeave(int id) {
		System.out.println("sevice autowired success");
		HashMap leave= leaveDao.findLeaveById(id);
		System.out.println("dddd");
		return leave;
	}

	@Override
	public void addLeave(HashMap leave) {
		leaveDao.addLeave(leave);
	}

	
	

}
