package com.tjport.common.wechat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tjport.common.wechat.menu.Button;
import com.tjport.common.wechat.menu.ClickButton;
import com.tjport.common.wechat.menu.Menu;
import com.tjport.common.wechat.menu.ViewButton;
import com.tjport.common.wechat.po.AccessTokenPo;
import com.tjport.common.wechat.po.TagsPo;
import com.tjport.common.wechat.po.UserInfoPo;
import com.tjport.common.wechat.po.UserListPo;
import com.tjport.common.wechat.menu.Matchrule;
import com.tjport.common.wechat.menu.PersonalMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 菜单
 * @author Neil
 * Date: 2016-03-18
 */

public class WeChatUtil {
	
	// 测试公众号
	private static final String APPID = "wxf526f4da64acb9eb";
	private static final String APPSECRET = "94491ad6160a8f1a7c19d8014ea50765";
	
	/*private static final String APPID = "wxb18faeee700b0ea4";
	private static final String APPSECRET = "78301ec28d29a6954f5d6e1564d0530c";*/
	
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	private static final String CREATE_PERSONAL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN";
	
	private static final String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	private static final String USER_INFO_LIST = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
	
	private static final String GET_USER_LIST = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	
	private static final String SET_USER_REMARK_URL = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";
	
	private static final String SEND_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
	
	private static final String CUSTOM_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	private static final String TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	// 创建标签
	private static final String CREATE_TAGS = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";
	
	// 获取标签
	private static final String GET_TAGS = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";
	
	// 编辑标签
	private static final String EDIT_TAGS = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";
	
	// 为用户打标签
	private static final String LABEL_TAGS = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN";
		
	/**
	 * Get
	 * @param url
	 * @return
	 */
	public static JSONObject getStr(String url){
		@SuppressWarnings({ "deprecation", "resource" })
		DefaultHttpClient httpClient = new DefaultHttpClient();
		
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		
		try {
			HttpResponse response = httpClient.execute(httpGet);
			
			HttpEntity entity = response.getEntity();
			if(entity != null){
				String result = EntityUtils.toString(entity,"UTF-8");
				jsonObject = JSONObject.parseObject(result);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonObject;
		
	}
	
	/**
	 * Post
	 * @param url
	 * @param out
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static JSONObject postStr(String url, String out){
		@SuppressWarnings("resource")
		DefaultHttpClient httpClient = new DefaultHttpClient();
		
		HttpPost httpPost  = new HttpPost(url);
		JSONObject jsonObject = null;
		
		
		try {
			httpPost.setEntity(new StringEntity(out,"UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(),"UTF-8");
			
			jsonObject = JSONObject.parseObject(result);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	/**
	 * 获取Access_Token
	 * @return
	 */
	public static AccessTokenPo getAccessToken(){
		AccessTokenPo tokenPo = new AccessTokenPo();
		
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		
		JSONObject jsonObject = getStr(url);
		if(jsonObject != null){
			tokenPo.setToken(jsonObject.getString("access_token"));
			tokenPo.setExpiresIn(jsonObject.getIntValue("expires_in"));
		}
		
		return tokenPo;
	}
	
	/**
	 * 主菜单
	 * @return Menu
	 */
	public static Menu initMenu(){
		Menu menu = new Menu();
		
		ViewButton leftOne = new ViewButton();
		leftOne.setName("信息查询1");
		leftOne.setType("view");
		leftOne.setUrl("http://10.128.51.172:8081/etoll/report/index");
		
		
		ViewButton leftTwo = new ViewButton();
		leftTwo.setName("信息查询2");
		leftTwo.setType("view");
		leftTwo.setUrl("http://10.128.137.245:18080/smartbi/vision/openresource.jsp?resid=I8a000d370156c0a7c0a7a22d0156d4e1dae569d9&user=haolj&password=123456");
		
		
		ClickButton scanButton = new ClickButton();
		scanButton.setName("扫码");
		scanButton.setType("scancode_push");
		scanButton.setKey("scanCode");
		
		
		ClickButton bindButton = new ClickButton();
		bindButton.setName("绑定个人信息");
		bindButton.setType("click");
		bindButton.setKey("bindInfo");
		
		ClickButton locationButton = new ClickButton();
		locationButton.setName("地理位置");
		locationButton.setType("location_select");
		locationButton.setKey("location");
		
		Button salaryButton = new Button();
		salaryButton.setName("信息查询");
		salaryButton.setSub_button(new Button[]{leftOne, leftTwo});
		
		Button otherButton = new Button();
		otherButton.setName("联系我们");
		otherButton.setSub_button(new Button[]{bindButton,locationButton});
		
		menu.setButton(new Button[]{salaryButton,scanButton,otherButton});
		
		return menu;
	}
	
	
	public static int createMenu(String token,String menu){
		int result = 0;
		
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		
		JSONObject jsonOject = postStr(url, menu);
		
		if(jsonOject != null){
			result = jsonOject.getIntValue("errorcode");
		}
		
		return result;
	}
	
	public static int deleteMenu(String token){
		int result = 0;
		
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
		
		JSONObject jsonObject = getStr(url);
		
		if(jsonObject != null){
			result = jsonObject.getIntValue("errorcode");
		}
		
		return result;
	}
	
	/**
	 * 个性化主菜单
	 * @return Menu
	 */
	public static Menu initPersonalMenu(){
		PersonalMenu menu = new PersonalMenu();
		
		ViewButton leftOne = new ViewButton();
		leftOne.setName("报表1");
		leftOne.setType("view");
		leftOne.setUrl("http://10.128.51.172:8081/etoll/report/index");
		
		
		ViewButton leftTwo = new ViewButton();
		leftTwo.setName("报表2");
		leftTwo.setType("view");
		leftTwo.setUrl("http://10.128.137.245:18080/smartbi/vision/openresource.jsp?resid=I8a000d370156c0a7c0a7a22d0156d4e1dae569d9&user=haolj&password=123456");
		
		
		ClickButton scanButton = new ClickButton();
		scanButton.setName("扫码");
		scanButton.setType("scancode_push");
		scanButton.setKey("scanCode");
		
		
		ClickButton bindButton = new ClickButton();
		bindButton.setName("绑定个人信息");
		bindButton.setType("click");
		bindButton.setKey("bindInfo");
		
		ClickButton locationButton = new ClickButton();
		locationButton.setName("地理位置");
		locationButton.setType("location_select");
		locationButton.setKey("location");
		
		Button salaryButton = new Button();
		salaryButton.setName("报表查询");
		salaryButton.setSub_button(new Button[]{leftOne, leftTwo});
		
		Button otherButton = new Button();
		otherButton.setName("联系我们");
		otherButton.setSub_button(new Button[]{bindButton,locationButton});
		
		menu.setButton(new Button[]{salaryButton,scanButton,otherButton});
		
		Matchrule matchrule = new Matchrule();
		matchrule.setTag_id(100);
		
		menu.setMatchrule(matchrule);
		
		return menu;
	}
	
	
	public static int createPersonalMenu(String token,String menu){
		int result = 0;
		
		String url = CREATE_PERSONAL_MENU_URL.replace("ACCESS_TOKEN", token);
		
		JSONObject jsonOject = postStr(url, menu);
		
		if(jsonOject != null){
			result = jsonOject.getIntValue("errorcode");
		}
		
		return result;
	}
	
	
	/**
	 * 获取用户信息
	 * @param token
	 * @param openid
	 * @return
	 */
	public static UserInfoPo getUserInfo(String token, String openid){
		UserInfoPo userInfoPo = new UserInfoPo();
		
		String url = USER_INFO_URL.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
		
		JSONObject jsonObject = getStr(url);
		
		if(jsonObject != null){
			userInfoPo.setRemark(jsonObject.getString("remark"));
			userInfoPo.setNickname(jsonObject.getString("nickname"));
			userInfoPo.setSubscribe(jsonObject.getIntValue("subscribe"));
			userInfoPo.setOpenid(jsonObject.getString("openid"));
			userInfoPo.setUnionid(jsonObject.getString("unionid"));;
		}
		
		return userInfoPo;
		
	}
	
	/**
	 * 获取用户openid列表
	 * @param token
	 * @param openid
	 * @return
	 */
	public static UserListPo getUserList(String token, String openid){
		UserListPo userListPo = new UserListPo();
		
		String url = "";
		
		if(openid.isEmpty()){
			url = GET_USER_LIST.replace("ACCESS_TOKEN", token).replace("&next_openid=NEXT_OPENID", openid);
		}
		else{
			url = GET_USER_LIST.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
		}
		
		JSONObject jsonObject = getStr(url);
		
		if(jsonObject != null){
			userListPo.setCount(jsonObject.getIntValue("total"));
			userListPo.setCount(jsonObject.getIntValue("count"));
			
			userListPo.setNext_openid(jsonObject.getString("next_openid"));
			JSONObject data = jsonObject.getJSONObject("data");
			
			JSONArray arr = data.getJSONArray("openid");
			
			//List<String>
			for(int i=0; i<arr.size(); i++){
				userListPo.getOpenid().add(arr.getString(i));
			}
		}
		
		return userListPo;
		
	}
	
	public static JSONObject getUserInfoList(String token, List<String> openids){
		int result = 0;
		String url = USER_INFO_LIST.replace("ACCESS_TOKEN", token);
		
		JSONArray arr = new JSONArray();
		for(int i=0; i< openids.size(); i++){
			JSONObject obj = new JSONObject();
			
			obj.put("openid", openids.get(i));
			obj.put("lang", "zh-CN");
			
			arr.add(obj);
		}
		
		JSONObject jsonPost = new JSONObject();
		jsonPost.put("user_list", arr);
		
		String postStr = jsonPost.toJSONString();
		
		JSONObject jsonObject = postStr(url, postStr);
		
		if(jsonObject != null){
			result = jsonObject.getIntValue("errorcode");
		}
		
		return jsonObject;
    }
	
	/**
	 * 设置用户备注
	 * @param token
	 * @param openid
	 * @param remark
	 * @return
	 */
	public static int setUserRemark(String token, String openid, String remark){
		int result = 0;
		String url = SET_USER_REMARK_URL.replace("ACCESS_TOKEN", token);
		
		JSONObject jsonPost = new JSONObject();
		jsonPost.put("openid", openid);
		jsonPost.put("remark", remark);
		
		String postStr = jsonPost.toJSONString();
		
		JSONObject jsonObject = postStr(url, postStr);
		
		if(jsonObject != null){
			result = jsonObject.getIntValue("errorcode");
		}
		
		return result;
    }
	
	/**
	 * 群发文本消息（主动）
	 * @param token
	 * @param openid
	 * @param content
	 * @return
	 */
	public static int sendMessage(String token, String openid, String content){
		int result = 0;
		String url = SEND_MESSAGE.replace("ACCESS_TOKEN", token);
		
		String[] openidList = new String[]{openid};
		
		JSONObject jsonText = new JSONObject();
		jsonText.put("content", "test");
		
		JSONObject jsonPost = new JSONObject();
		jsonPost.put("touser", openidList);
		jsonPost.put("msgtype", "text");
		jsonPost.put("text", jsonText);
		
		String postStr = jsonPost.toJSONString();
		
		JSONObject jsonObject = postStr(url, postStr);
		
		if(jsonObject != null){
			result = jsonObject.getIntValue("errorcode");
		}
		
		return result;
	}
	
	
	/**
	 * 客服消息（主动）
	 * @param token
	 * @param openid
	 * @param content
	 * @return
	 */
	public static int customMessage(String token, String openid, String content){
		int result = 0;
		String url = CUSTOM_MESSAGE.replace("ACCESS_TOKEN", token);
		
		JSONObject jsonText = new JSONObject();
		jsonText.put("content", content);
		
		JSONObject jsonPost = new JSONObject();
		jsonPost.put("touser", openid);
		jsonPost.put("msgtype", "text");
		jsonPost.put("text", jsonText);
		
		String postStr = jsonPost.toJSONString();
		
		JSONObject jsonObject = postStr(url, postStr);
		
		if(jsonObject != null){
			result = jsonObject.getIntValue("errorcode");
		}
		
		return result;
	}
	
	/**
	 * 模板消息（待办任务）
	 * @param token 微信token
	 * @param openid 用户openId
	 * @param title 标题
	 * @param content 内容
	 * @param endTime 任务截止时间
	 * @return 返回值
	 */
	public static int templateMessage(String token, String openid, String title, String content,String endTime){
		int result = 0;
		String url = TEMPLATE_MESSAGE.replace("ACCESS_TOKEN", token);
		
		JSONObject json1 = new JSONObject();
		json1.put("value", title);
		json1.put("color", "#173177");
		
		JSONObject json2 = new JSONObject();
		json2.put("value", content);
		json2.put("color", "#173177");
		
		JSONObject json3 = new JSONObject();
		json3.put("value", endTime);
		json3.put("color", "#173177");
		
		JSONObject jsonText = new JSONObject();
		jsonText.put("first", json1);
		jsonText.put("keyword1", json2);
		jsonText.put("keyword2", json3);
		
		JSONObject jsonPost = new JSONObject();
		jsonPost.put("touser", openid);
		jsonPost.put("template_id", "3zrYkvhUB6f0WktFmxbp1dXvas6WFpdUdXIRvxRQWiw");
		jsonPost.put("data", jsonText);
		jsonPost.put("url","mail.126.com");
		
		
		String postStr = jsonPost.toJSONString();
		
		JSONObject jsonObject = postStr(url, postStr);
		
		if(jsonObject != null){
			result = jsonObject.getIntValue("errorcode");
		}
		
		return result;
	}
	
	
	/**
	 * 创建标签
	 * @param token
	 * @param openid
	 * @param tags
	 * @return
	 */
	public static int createTags(String token, String tags){
		int result = 0;
		String url = CREATE_TAGS.replace("ACCESS_TOKEN", token);
		
		JSONObject jsonText = new JSONObject();
		jsonText.put("name", tags);
		
		JSONObject jsonPost = new JSONObject();
		jsonPost.put("tag", jsonText);
		
		String postStr = jsonPost.toJSONString();
		
		JSONObject jsonObject = postStr(url, postStr);
		
		if(jsonObject.getJSONObject("tag") != null){
			result = 1;
		}
		else{
			result = jsonObject.getIntValue("errorcode");
		}
		
		
		return result;
	}
	
	
	/**
	 * 获取标签列表
	 * @param token
	 * @return
	 */
	public static List<TagsPo> getTags(String token){
		String url = GET_TAGS.replace("ACCESS_TOKEN", token);
		
		JSONObject jsonObject = getStr(url);
		
		List<TagsPo> list = new ArrayList<TagsPo>();
		if(jsonObject != null){
			JSONArray arr = jsonObject.getJSONArray("tags");
			int len = arr.size();
			for(int i=0; i< len; i++){
				TagsPo tag = new TagsPo();
				JSONObject obj = arr.getJSONObject(i);
				tag.setId(obj.getIntValue("id"));
				tag.setName(obj.getString("name"));
				tag.setCount(obj.getIntValue("count"));
				
				list.add(tag);
			}
		}
		
		return list;
	}
	
	
	/**
	 * 为用户打标签
	 * @param token
	 * @param openid
	 * @param tags
	 * @return
	 */
	public static int labelTags(String token, List<String> openids, String tagid){
		int result = 0;
		String url = LABEL_TAGS.replace("ACCESS_TOKEN", token);
		
		JSONObject jsonPost = new JSONObject();
		
		jsonPost.put("openid_list", openids);
		jsonPost.put("tagid", tagid);
			
		
		String postStr = jsonPost.toJSONString();
		
		JSONObject jsonObject = postStr(url, postStr);
		
		if(jsonObject != null){
			result = jsonObject.getIntValue("errorcode");
		}
		
		return result;
    }
	
	
	
}
