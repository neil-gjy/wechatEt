package com.tjport.wechatEt.sys.service;

import com.tjport.wechatEt.sys.vo.UserVo;

public interface IUserService {

	public void save(UserVo vo) throws Exception;
	public void update(UserVo vo) throws Exception;
}
