package cn.gorun8.easyfk.workflow.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.gorun8.easyfk.base.util.UtilHttp;
import cn.gorun8.easyfk.workflow.service.TaskHolderService;

@Controller
@RequestMapping(value = "wf")
public class WorkflowController {
	
	@Autowired
	private  TaskHolderService taskHolderService;
	
	@RequestMapping(value = "run",method = RequestMethod.GET)
	public ModelAndView runGet(@RequestParam(value = "holderid")int holderid
			,HttpServletRequest request
			,HttpServletResponse response){
		Map<String, Object> tmpmap = UtilHttp.getParameterMap(request);
		HashMap params = taskHolderService.getTaskHolder(holderid);
		params.putAll(tmpmap);
		String page = (String)params.get("GET_PAGE");
		ModelAndView mv = new ModelAndView(page);
		params.put("mv", mv);
		ITaskHolder taskHolder = getHolderObject(params);
		return taskHolder.init(params);
	}
	
	
	@RequestMapping(value = "run",method = RequestMethod.POST)
	public ModelAndView runPost(@RequestParam(value = "id")int id){
		HashMap params = taskHolderService.getTaskHolder(id);
		String page = (String)params.get("POST_PAGE");
		ModelAndView mv = new ModelAndView(page);
		params.put("mv", mv);
		ITaskHolder taskHolder = getHolderObject(params);
		return	taskHolder.run(params);
	}
	
	private ITaskHolder getHolderObject(HashMap holdermap)
	{
		try {
			String cls = (String)holdermap.get("CLASS_NAME");
			Class tmp= Class.forName(cls);
			return (ITaskHolder)tmp.newInstance();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	

}
