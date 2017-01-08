package com.tjport.wechatEt.wechat.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
import com.tjport.common.wechat.FileUpLoadUtil;
import com.tjport.common.wechat.WeChatUtil;
import com.tjport.common.wechat.po.QRCodeTicketPo;
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
@RequestMapping(ManageController.BASE + "/" + ManageController.PATH)  
public class ManageController {  
    final static String BASE = "wechat"; 
    final static String PATH = "manage";
    
    
    @RequestMapping("index")  
    public String userList() {  
         //return "success"; //跳转到success页面   
         return BASE + "/" + PATH + "/index";
    } 
	

    // 获取用户信息列表
    @ResponseBody
	@RequestMapping("showQRCode")
	public Result showQRCode(int expire_seconds, boolean isLimit, Integer scence_id) {
    	QRCodeTicketPo qRCodeTicketPo = WeChatUtil.getQRCodeTicket(TokenController.token.getToken(), expire_seconds, isLimit, scence_id);
    	
    	String ticket = qRCodeTicketPo.getTicket();
    	
    	Result mReturn = Result.successResult().setObj(ticket);
    	
    	return mReturn;
    }
    
    @ResponseBody
	@RequestMapping("upLoadFile")
	public Result add(@RequestParam(value = "file", required = false) MultipartFile file) {

		Result mReturn = null;
		try {
//			CommonsMultipartFile cf = (CommonsMultipartFile)file;   
//		        //这个myfile是MultipartFile的  
//	        DiskFileItem fi = (DiskFileItem) cf.getFileItem();  
//	        File f = fi.getStoreLocation();   
	        
			//FileUpLoadUtil.uploadPicMatiral(TokenController.token.getToken(), file);
			
			//File f = (File)file;  
			  
			//FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), f); 
			
			File f = new File("./tmp.jpg"); 
			file.transferTo(f);
			FileUpLoadUtil.uploadPicMatiral(TokenController.token.getToken(), f);
			mReturn = Result.successResult().setMsg("添加成功");

		} catch (Exception e) {
			// logger.error("添加失败", e);
			mReturn = Result.errorResult().setMsg(e.getMessage());
		}
		return mReturn;
	}
    
   
  
}  