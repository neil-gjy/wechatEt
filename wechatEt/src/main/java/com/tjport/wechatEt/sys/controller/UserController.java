package com.tjport.wechatEt.sys.controller;

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
import com.tjport.common.model.ComboboxResult;
import com.tjport.common.model.DatatablesParameters;
import com.tjport.common.model.DatatablesResult;
import com.tjport.common.model.Result;
import com.tjport.common.query.Page;
import com.tjport.common.query.QueryFilter;
import com.tjport.common.query.Rule;
import com.tjport.common.wechat.WeChatUtil;
import com.tjport.common.wechat.po.TagsPo;
import com.tjport.wechatEt.security.ShiroPrincipal;
import com.tjport.wechatEt.sys.dao.IUserDao;
import com.tjport.wechatEt.sys.model.User;
import com.tjport.wechatEt.sys.service.IUserService;
import com.tjport.wechatEt.sys.vo.UserVo;
import com.tjport.wechatEt.wechat.controller.TokenController;




@Controller  
@RequestMapping(UserController.BASE + "/" + UserController.PATH)  
public class UserController {  
    final static String BASE = "sys"; 
    final static String PATH = "user";
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserService userService;
	
	
    @RequestMapping("userList")  
    public String userList() {  
         //return "success"; //跳转到success页面   
         return BASE + "/" + PATH + "/userList";
    } 
    

    @RequestMapping(value="userDialog/{type}")  
    public String userDialog(@PathVariable String type, Map<String,Object> map) {  
    	 map.put("type", type);  
         //return "success"; //跳转到success页面   
         return BASE + "/" + PATH + "/userDialog";
    } 
    
  
    @RequestMapping(value="userSelectionDialog")  
    public String orgSelectionDialog() {  

         return BASE + "/" + PATH + "/userSelectionDialog";
    } 
    
    @ResponseBody
	@RequestMapping("loadUserList")
	public DatatablesResult<UserVo> loadUserList(@RequestBody DatatablesParameters params)
			throws UnsupportedEncodingException {
		int page = (params.getStart() / params.getLength()) + 1;
    	int rows = params.getLength();
    	
    	Page<User> dataPage = new Page<User>(page, rows);
    	dataPage.setOrderBy(params.getOrderBy());
    	dataPage.setOrder(params.getOrderDir());
    	dataPage.setAutoCount(true);
    	
    	String search = params.getSearch();
    	
        
    	
        if (StringUtils.isNoneBlank(search))
        {
        	
        	dataPage = this.userDao.findPage(dataPage, search);
        }
        else
        {
        	dataPage = this.userDao.getAll(dataPage);
        }

        ArrayList<UserVo> list = new ArrayList<UserVo>();
        for(User entity : dataPage.getResult()){
        	try {
				list.add(new UserVo(entity));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
        }
             	
    	DatatablesResult<UserVo> result = new DatatablesResult<UserVo>();
    	result.setDraw(params.getDraw());
    	result.setData(list);
    	result.setRecordsFiltered(dataPage.getTotalCount());
    	result.setRecordsTotal(dataPage.getTotalCount());
    	
    	return result;
	}

    
    @ResponseBody
	@RequestMapping("userAdd")
	public Result add(UserVo userVo) {
    	
    	
    	userVo.setCreateTime(new Date(System.currentTimeMillis()));
    	
    	Result mReturn = null;
		try {

			userService.save(userVo);
			
			mReturn = Result.successResult().setMsg("添加成功");
		} catch (Exception e) {
			//logger.error("添加失败", e);
			mReturn = Result.errorResult().setMsg(e.getMessage());
		}
		return mReturn;
	}
    
    @ResponseBody
	@RequestMapping("userEdit")
	public Result edit(UserVo userVo) {

    	// 添加修改人和修改时间
		ShiroPrincipal principal = (ShiroPrincipal) SecurityUtils.getSubject().getPrincipal();
		

		Result mReturn = null;
		try {
			userService.update(userVo);
			mReturn = Result.successResult().setMsg("编辑成功");
		} catch (Exception e) {

			mReturn = Result.errorResult().setMsg(e.getMessage());
		}
		return mReturn;
	}
    
    @ResponseBody
	@RequestMapping("userDel")
	public Result del(String id) {
    	Result mReturn = new Result();
		try {

			userDao.delete(id);
			mReturn = Result.successResult().setMsg("删除成功");
		} catch (Exception e) {
			//logger.error("添加失败", e);
			mReturn = Result.errorResult().setMsg(e.getMessage());
		}
		return mReturn;
	}
    
    @ResponseBody
    @RequestMapping("loadUserCombo")
    public List<ComboboxResult> loadUserCombo() {
      List<User> list = userDao.findAll();
      List<ComboboxResult> result = new ArrayList<ComboboxResult>();

      for (User l : list) {
        ComboboxResult comboboxResult = new ComboboxResult();
        comboboxResult.setId(l.getId());
        comboboxResult.setText(l.getName());
        result.add(comboboxResult);
      }

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
  
}  