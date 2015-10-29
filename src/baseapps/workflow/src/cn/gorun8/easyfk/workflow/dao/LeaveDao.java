package cn.gorun8.easyfk.workflow.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

public interface LeaveDao {

	public HashMap findLeaveById(@Param("id")int id);
	
	public void addLeave(HashMap leave);
}
