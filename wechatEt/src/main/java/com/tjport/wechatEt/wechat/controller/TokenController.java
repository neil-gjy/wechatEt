package com.tjport.wechatEt.wechat.controller;

import java.util.Date;

import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.apache.shiro.SecurityUtils;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

import com.tjport.common.wechat.WeChatUtil;
import com.tjport.wechatEt.wechat.job.AccessTokenJob;
import com.tjport.common.spring.BaseController;
import com.tjport.common.wechat.po.AccessTokenPo;


@Controller
@RequestMapping
public class TokenController extends BaseController {
	
	public static AccessTokenPo token = null;
	
	public static void setToken(AccessTokenPo accessTokenPo){
		token = accessTokenPo;
	}
	
	static{
        token = WeChatUtil.getAccessToken();
		
		System.out.println("token:" + token.getToken());
		System.out.println("TimeL:" + token.getExpiresIn());
		
		String menu = JSONObject.toJSON(WeChatUtil.initMenu()).toString();
		int result = WeChatUtil.createMenu(token.getToken(), menu);
		
		String personalMenu = JSONObject.toJSON(WeChatUtil.initPersonalMenu()).toString();
		int resultPer = WeChatUtil.createPersonalMenu(token.getToken(), personalMenu);
		
		if(result == 0){
			System.out.println("Success！");
		}
		else{
			System.out.println("Error Code:" + result);
		}
		
		
		// 循环获取Token 任务
		try {
			schedulerRun();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@RequestMapping(value = "login", method = RequestMethod.GET)
//	public String login() {
//		
//		return "login";
//	}

	
	
	/**
	 * 启动获取Access_Token Job
	 * @throws SchedulerException
	 */
	@SuppressWarnings("deprecation")
	public static void schedulerRun() throws SchedulerException{
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		
		Scheduler scheduler = schedulerFactory.getScheduler();
		
		JobDetail jobDetail = new JobDetailImpl("access_token_job",
				Scheduler.DEFAULT_GROUP,AccessTokenJob.class);
		
		SimpleTrigger simpleTrigger = new SimpleTriggerImpl("access_token_trigger", 
				Scheduler.DEFAULT_GROUP);
		
		((SimpleTriggerImpl) simpleTrigger).setStartTime(new Date(System.currentTimeMillis()));
        ((SimpleTriggerImpl) simpleTrigger).setRepeatInterval(7000000);//7000s获取一次
        ((SimpleTriggerImpl) simpleTrigger).setRepeatCount(-1);// -1位不限制次数

        scheduler.scheduleJob(jobDetail, simpleTrigger);

        scheduler.start();	
	}

}
