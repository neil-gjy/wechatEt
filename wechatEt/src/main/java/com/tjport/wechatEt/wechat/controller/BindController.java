package com.tjport.wechatEt.wechat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjport.common.model.Result;
import com.tjport.common.spring.BaseController;
import com.tjport.common.wechat.WeChatUtil;
import com.tjport.wechatEt.security.ShiroPrincipal;
import com.tjport.wechatEt.sys.dao.IUserDao;
import com.tjport.wechatEt.sys.model.User;
import com.tjport.wechatEt.sys.service.IUserService;


@Controller
@RequestMapping(BindController.BASE + "/" + BindController.PATH)
public class BindController extends BaseController {
	final static String BASE = "wechat";
	final static String PATH = "bind";

	@Autowired
	private IUserService userService;
	
	
	
	@RequestMapping(value = "/bindAccount/{openid}")
	public String login(@PathVariable String openid, Map<String,String> map){
		map.put("openid", openid);
		
		return BASE + "/" + PATH + "/bindAccount";
	}

	// 将用户绑定到
	@ResponseBody
	@RequestMapping(value = "/bindUser/{openid}", method = RequestMethod.POST)
	public Result login(User user, @PathVariable String openid, Map<String,String> map) {
		map.put("openid", openid);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		
		Result result = null;
		
		try {
			
			subject.login(token);
			
			userService.bindOpenid(user.getName(), user.getOpenid());
			List<String> list = new ArrayList<String>();
			list.add(user.getOpenid());
			
			// 赋标签
			WeChatUtil.labelTags(TokenController.token.getToken(), list, "100");
			        
			result = Result.successResult().setMsg("绑定成功");
		} catch (AccountException e) {
			token.clear();
			result = Result.errorResult().setMsg(e.getMessage());
		} catch (Exception e) {
			token.clear();
			result = Result.errorResult().setMsg("用户名密码错误");
			e.printStackTrace();
		}
		return result;
	}
	
}
