package com.tjport.wechatEt.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjport.common.model.Result;
import com.tjport.common.spring.BaseController;
import com.tjport.wechatEt.security.ShiroPrincipal;
import com.tjport.wechatEt.sys.model.User;


@Controller
@RequestMapping
public class LoginController extends BaseController {
	final static String BASE = "/";


	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		ShiroPrincipal subject = (ShiroPrincipal) SecurityUtils.getSubject().getPrincipal();
		if (subject != null && subject.isAuthorized()) {
			return "redirect:home";
		}
		
		return "login";
	}

	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Result login(User user) {

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		
		Result result = new Result();
		
		try {
			
			subject.login(token);
			        
			result = Result.successResult().setMsg("登录成功");
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
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login";
	}
}
