package com.tjport.wechatEt.sys.service;

import com.tjport.wechatEt.sys.vo.UserVo;

public interface IUserService {

	public void save(UserVo vo) throws Exception;
	public void update(UserVo vo) throws Exception;
	
	public void bindOpenid(String username, String openid) throws Exception;
}
