package com.tjport.wechatEt.sys.dao;

import java.util.List;

import com.tjport.common.hibernate.IBaseDao;
import com.tjport.wechatEt.sys.model.Resource;
import com.tjport.wechatEt.sys.model.Role;



public interface IRoleDao extends IBaseDao<Role, String> {

	List<Resource> getResourcesByRole(String roleId);
}
