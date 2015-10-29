package cn.gorun8.easyfk.workflow.controller;

import java.util.HashMap;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.web.servlet.ModelAndView;

import cn.gorun8.easyfk.base.util.UtilIOC;
import cn.gorun8.easyfk.workflow.service.DemoService;

/**
 * 申请请假期
 * @author:hezhiping(110476592@qq.com)
 *
 */
public class LeaveStartTask implements ITaskHolder{
	@Override
	public ModelAndView run(HashMap param) {
		RuntimeService runtimeService = UtilIOC.getBean("runtimeService");
		DemoService demoService = UtilIOC.getBean("demoService");
		IdentityService identityService = UtilIOC.getBean("identityService");
	
		ModelAndView mv =(ModelAndView)param.get("mv");
		String businessKey ="1";
		ProcessInstance processInstance = null;
	    try {
	    	  HashMap myparams = new HashMap();
	           // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
	          identityService.setAuthenticatedUserId("hzp");
	          processInstance = runtimeService.startProcessInstanceByKey("process", businessKey, myparams);
	          String processInstanceId = processInstance.getId();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally {
	            identityService.setAuthenticatedUserId(null);
	        }
		
	    String pid = processInstance.getId();
		HashMap<String ,String > leave = new HashMap<String,String>();
	//	leave.put("id", businessKey);
		leave.put("aptime","2015-07-31 09:00:00");
		leave.put("endtime","2015-08-9 09:00:00");
		leave.put("type","1");
		leave.put("pid",pid);
		leave.put("reason","sss");
		leave.put("uid","hzp");
		demoService.addLeave(leave);
     
		return mv;
	}

	@Override
	public ModelAndView init(HashMap params) {
		ModelAndView mv =(ModelAndView)params.get("mv");
		mv.addObject("view", "applyleave");
		return mv;
	}

}
