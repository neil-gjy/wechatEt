package com.tjport.wechatEt.wechat.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tjport.common.wechat.WeChatUtil;
import com.tjport.common.wechat.po.AccessTokenPo;
import com.tjport.wechatEt.wechat.controller.TokenController;

public class AccessTokenJob implements Job {

	public static AccessTokenPo token = null;
	
    public void execute(JobExecutionContext context) 
            throws JobExecutionException {
    	
    	TokenController.setToken(WeChatUtil.getAccessToken());//获取AccessToken
    }
}