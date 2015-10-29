package cn.gorun8.easyfk.workflow.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gorun8.easyfk.workflow.dao.TaskHolderDao;
import cn.gorun8.easyfk.workflow.service.TaskHolderService;

@Service
public class TaskHolderServiceImpl implements TaskHolderService {

	@Autowired
	TaskHolderDao taskHolderDao;
	
	@Override
	public HashMap getTaskHolder(int id) {
		return taskHolderDao.findTaskHolderById(id);
	}
	

}
