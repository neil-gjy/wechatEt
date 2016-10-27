package com.tjport.wechatEt.sys.service;

import java.util.List;

import com.tjport.wechatEt.sys.model.Department;

public interface IOrganizationService {
	
	List<Department> getDeptsByOrgId(String orgId);

	void addDepts(String orgId, String[] deptIds);
}
