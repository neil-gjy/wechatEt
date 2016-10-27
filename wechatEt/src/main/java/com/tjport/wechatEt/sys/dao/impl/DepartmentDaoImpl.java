package com.tjport.wechatEt.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.tjport.common.hibernate.BaseDaoImpl;
import com.tjport.wechatEt.sys.dao.IDepartmentDao;
import com.tjport.wechatEt.sys.model.Department;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl <Department, String> implements IDepartmentDao {
	
}
