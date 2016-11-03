package com.tjport.wechatEt.wechat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tjport.common.utils.DesUtils;
import com.tjport.common.utils.StringUtils;
import com.tjport.common.wechat.WeChatUtil;
import com.tjport.wechatEt.wechat.controller.TokenController;
import com.tjport.wechatEt.wechat.service.IWxUserService;
import com.tjport.wechatEt.wechat.vo.WxUserVo;



/**
 * 用户
 * @author by Neil
 *
 */

@Service("wxUserService")
public class WxUserServiceImpl implements IWxUserService{

	@Override
	public List<WxUserVo> getWxUserList() {
		// TODO Auto-generated method stub
		
		WeChatUtil.getUserList(TokenController.token.getToken(), "");
		
		return null;
	}

	
	
	
}
