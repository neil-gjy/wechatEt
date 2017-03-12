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
@RequestMapping(MapController.BASE + "/" + MapController.PATH)
public class MapController extends BaseController {
	final static String BASE = "wechat";
	final static String PATH = "map";

	@Autowired
	private IUserService userService;
	
	
	
	@RequestMapping("index")  
    public String index() {  
         //return "success"; //跳转到success页面   
         return BASE + "/" + PATH + "/index";
    } 

	
}
