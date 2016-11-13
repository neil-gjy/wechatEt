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
import com.alibaba.fastjson.JSONArray;
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
import com.tjport.wechatEt.wechat.vo.WxUserVo;




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
    	
    	JSONObject obj = WeChatUtil.getUserInfoList(TokenController.token.getToken(), usersListPo.getOpenid());
    	
    	Result mReturn = Result.successResult().setObj(obj);
    	
    	return mReturn;
    }
    
    @ResponseBody
	@RequestMapping("loadUserList")
	public DatatablesResult<WxUserVo> loadUserList(@RequestBody DatatablesParameters params)
			throws UnsupportedEncodingException {
		int page = (params.getStart() / params.getLength()) + 1;
    	int rows = params.getLength();
    	
    	UserListPo usersListPo = WeChatUtil.getUserList(TokenController.token.getToken(), "");
    	
    	JSONObject obj = WeChatUtil.getUserInfoList(TokenController.token.getToken(), usersListPo.getOpenid());
    	
    	JSONArray arr = obj.getJSONArray("user_info_list");
    	
    	ArrayList<WxUserVo> list = new ArrayList<WxUserVo>();
    	int total = arr.size();
    	for(int i=0; i<total; i++){
    		WxUserVo wxUser = new WxUserVo();
    		
    		wxUser.setOpenid(arr.getJSONObject(i).getString("openid"));
    		wxUser.setRemark(arr.getJSONObject(i).getString("remark"));
    		wxUser.setNickname(arr.getJSONObject(i).getString("nickname"));
    		//wxUser.setLanguage(arr.getJSONObject(i).getString("language"));
    		wxUser.setTagid_list(arr.getJSONObject(i).get("tagid_list").toString());
    		
    		list.add(wxUser);
    	}
             	
    	DatatablesResult<WxUserVo> result = new DatatablesResult<WxUserVo>();
    	result.setDraw(params.getDraw());
    	result.setData(list);
    	result.setRecordsFiltered(total);
    	result.setRecordsTotal(total);
    	
    	return result;
	}
    
    
    @ResponseBody
	@RequestMapping("loadTagList")
	public DatatablesResult<TagsPo> loadTagList(@RequestBody DatatablesParameters params)
			throws UnsupportedEncodingException {
		int page = (params.getStart() / params.getLength()) + 1;
    	int rows = params.getLength();
    	
    	List<TagsPo> tagsList = WeChatUtil.getTags(TokenController.token.getToken());
    	ArrayList<TagsPo> list = new ArrayList<TagsPo>();
    	
    	for(int i=0; i<tagsList.size(); i++){
    		list.add(tagsList.get(i));
    	}
    	
    	int total = tagsList.size();
             	
    	DatatablesResult<TagsPo> result = new DatatablesResult<TagsPo>();
    	result.setDraw(params.getDraw());
    	result.setData(list);
    	result.setRecordsFiltered(total);
    	result.setRecordsTotal(total);
    	
    	return result;
	}
    
    
    // 用户标签
    @ResponseBody
	@RequestMapping("getTagsList")
	public Result getTagsList() {
    	List<TagsPo> tagsList = WeChatUtil.getTags(TokenController.token.getToken());
    	
    	Result mReturn = Result.successResult().setObj(tagsList);
    	
    	return mReturn;
    }
    
    //保存标签
    @ResponseBody
	@RequestMapping("saveTag")
	public Result saveTag(String tags) {
    	Result mReturn = null;
    	
    	int res = WeChatUtil.createTags(TokenController.token.getToken(), tags);
    	
    	if(res == 1){
    		mReturn = Result.successResult().setMsg("保存成功！");
    	}
    	else{
    		mReturn = Result.errorResult().setMsg("保存失败！");
    	}
    	
    	
    	return mReturn;
    }
    
    //为用户打标签
    @ResponseBody
	@RequestMapping("labelTag")
	public Result labelTag(String openids, String tag) {
    	Result mReturn = null;
    	String[] lOpenids = openids.split(",");
    	
        List<String> list = Arrays.asList(lOpenids);  
    	
    	int res = WeChatUtil.labelTags(TokenController.token.getToken(), list, tag);
    	
    	if(res == 0){
    		mReturn = Result.successResult().setMsg("标签赋值成功！");
    	}
    	else{
    		mReturn = Result.errorResult().setMsg("标签赋值失败！");
    	}
    	
    	
    	return mReturn;
    }
  
}  