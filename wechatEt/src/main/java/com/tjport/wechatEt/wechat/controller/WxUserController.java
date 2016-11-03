package com.tjport.wechatEt.wechat.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tjport.common.model.ComboboxResult;
import com.tjport.common.model.DatatablesParameters;
import com.tjport.common.model.DatatablesResult;
import com.tjport.common.model.Result;
import com.tjport.common.query.Page;
import com.tjport.common.query.QueryFilter;
import com.tjport.common.query.Rule;
import com.tjport.common.wechat.WeChatUtil;
import com.tjport.common.wechat.po.TagsPo;
import com.tjport.common.wechat.po.UserListPo;
import com.tjport.wechatEt.security.ShiroPrincipal;
import com.tjport.wechatEt.sys.dao.IUserDao;
import com.tjport.wechatEt.sys.model.User;
import com.tjport.wechatEt.sys.service.IUserService;
import com.tjport.wechatEt.sys.vo.UserVo;
import com.tjport.wechatEt.wechat.controller.TokenController;




@Controller  
@RequestMapping(WxUserController.BASE + "/" + WxUserController.PATH)  
public class WxUserController {  
    final static String BASE = "wechat"; 
    final static String PATH = "user";
    
    
    @RequestMapping("userList")  
    public String userList() {  
         //return "success"; //跳转到success页面   
         return BASE + "/" + PATH + "/userList";
    } 
	

    // 获取用户信息列表
    @ResponseBody
	@RequestMapping("getUsersList")
	public Result getUsersList() {
    	UserListPo usersListPo = WeChatUtil.getUserList(TokenController.token.getToken(), "");
    	
    	//List<String> openids = new ArrayList<String>();
    	/*for(int i=0; i<usersListPo.getTotal(); i++){
    		openids.add(e)
    	}*/
    	
    	JSONObject obj = WeChatUtil.getUserInfoList(TokenController.token.getToken(), usersListPo.getOpenid());
    	
    	Result mReturn = Result.successResult().setObj(obj);
    	
    	return mReturn;
    }
    
    
    // 用户标签
    @ResponseBody
	@RequestMapping("getTagsList")
	public Result getTagsList() {
    	List<TagsPo> tagsList = WeChatUtil.getTags(TokenController.token.getToken());
    	
    	Result mReturn = Result.successResult().setObj(tagsList);
    	
    	return mReturn;
    }
  
}  