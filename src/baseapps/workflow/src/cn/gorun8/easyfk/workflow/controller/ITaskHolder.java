package cn.gorun8.easyfk.workflow.controller;

import java.util.HashMap;

import org.springframework.web.servlet.ModelAndView;

public interface ITaskHolder {
	
	public ModelAndView init(HashMap  params);
	public ModelAndView run(HashMap param);
		
}
