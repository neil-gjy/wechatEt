package com.tjport.common.wechat;

import com.alibaba.fastjson.JSONObject;
import com.tjport.common.wechat.menu.Button;
import com.tjport.common.wechat.menu.ClickButton;
import com.tjport.common.wechat.menu.Menu;
import com.tjport.common.wechat.menu.ViewButton;
import com.tjport.common.wechat.po.AccessTokenPo;
import com.tjport.common.wechat.po.UserInfoPo;

import java.io.IOException;

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

public class MaterialUtils {
	
	// 获取素材列表URL
	private static final String MATERIAL_LIST_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
	
	
	private static final String UPLOAD_PIC_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
	
	public static int getMaterialList(String token, String type, String offset, String count){
		int result = 0;
		String url = MATERIAL_LIST_URL.replace("ACCESS_TOKEN", token);
		
		JSONObject jsonPost = new JSONObject();
		jsonPost.put("type", type);
		jsonPost.put("offset", offset);
		jsonPost.put("count", count);
		
		String postStr = jsonPost.toJSONString();
		
		JSONObject jsonObject = WeChatUtil.postStr(url, postStr);
		
		if(jsonObject != null){
			result = jsonObject.getIntValue("errorcode");
		}
		
		return result;
    }
	
	public static int uploadPicMaterial(String token, String title, String url, String content){
		int result = 0;
		/*String url = UPLOAD_PIC_MATERIAL_URL.replace("ACCESS_TOKEN", token);
		
		//curl --form "fileupload=@filename.txt" http://hostname/resource
		
		if(jsonObject != null){
			result = jsonObject.getIntValue("errorcode");
		}*/
		
		return result;
	}
	
}
