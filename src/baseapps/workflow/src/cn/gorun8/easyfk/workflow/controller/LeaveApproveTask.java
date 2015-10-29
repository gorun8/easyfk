package cn.gorun8.easyfk.workflow.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javolution.util.FastMap;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.web.servlet.ModelAndView;

import cn.gorun8.easyfk.base.util.UtilIOC;
import cn.gorun8.easyfk.workflow.service.DemoService;

/**
 * 请假审批
 * @author:hezhiping(110476592@qq.com)
 *
 */
public class LeaveApproveTask implements ITaskHolder{
	@Override
	public ModelAndView run(HashMap  param) {
		TaskService taskService = UtilIOC.getBean("taskService");
		String taskId  = (String)param.get("taskId");
	
		try {
            Map<String, Object> variables = FastMap.newInstance();
            taskService.complete(taskId, variables);
        } catch (Exception e) {
        	e.printStackTrace();
        }

		return null;
	}

	@Override
	public ModelAndView init(HashMap params) {
		TaskService taskService = UtilIOC.getBean("taskService");
		String userId = (String)params.get("userId");
		List<HashMap> results = new ArrayList<HashMap>();
		RuntimeService runtimeService = UtilIOC.getBean("runtimeService");
		DemoService demoService = UtilIOC.getBean("demoService");
		ModelAndView mv =(ModelAndView)params.get("mv");
		
		// 根据当前人的ID查询
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
        List<Task> tasks = taskQuery.list();
        // 根据流程的业务ID查询实体并关联
        for (Task task : tasks) {
	            String processInstanceId = task.getProcessInstanceId();
	            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
	            String businessKey = processInstance.getBusinessKey();
	            if (businessKey == null) {
	                continue;
	            }
	            
	            HashMap leave = demoService.getLeave(Integer.parseInt(businessKey));
//	            leave.setTask(task);
//	            leave.setProcessInstance(processInstance);
//	            leave.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
	            results.add(leave);
	        }
        
        	mv.addObject("results", results);
	        return mv;
	}

}
