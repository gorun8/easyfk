package cn.gorun8.easyfk.workflow.controller;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.web.servlet.ModelAndView;

import cn.gorun8.easyfk.base.util.UtilIOC;
import cn.gorun8.easyfk.workflow.service.DemoService;

/**
 * 请假签收
 * @author:hezhiping(110476592@qq.com)
 *
 */
public class LeaveClaimTask implements ITaskHolder{
	
	@Override
	public ModelAndView run(HashMap  param) {
		RuntimeService runtimeService = UtilIOC.getBean("runtimeService");
		DemoService demoService = UtilIOC.getBean("demoService");
		TaskService taskService = UtilIOC.getBean("taskService");
		String taskId = (String)param.get("taskId");
		//String userId = (String)param.get("userId");
		String userId = "ldx"; 
	    taskService.claim(taskId, userId);
	    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
	    Map firstDate  = runtimeService.getVariables(task.getExecutionId());
	    System.out.println("firstDate = "+firstDate);
	
	    return null;
	}

 

	@Override
	public ModelAndView init(HashMap params) {
		
		return null;
        //return mav;
	}

}
