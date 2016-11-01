package com.tjport.wechatEt.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjport.common.spring.BaseController;
import com.tjport.common.wechat.CheckUtils;
import com.tjport.common.wechat.MessageUtil;
import com.tjport.common.wechat.po.TextMessagePo;
import com.tjport.common.wechat.po.UserInfoPo;
import com.tjport.common.wechat.WeChatUtil;

@Controller
@RequestMapping(value = ConnectController.BASE)
public class ConnectController extends BaseController {
	final static String BASE = "connect";
	//final static String PATH = "check";
	
	//public static final String BIND_INFO = "请您输入个人身份证号绑定个人信息！";

	@RequestMapping(value = "check", method = RequestMethod.GET)
	public void checkGet (HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		// 加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		
		
		if(CheckUtils.checkSingnature(signature, timestamp, nonce)){
			PrintWriter out = response.getWriter();
			
			out.print(echostr);
			out.flush();
			out.close();
		}
		
		//return "home";
	}
	
	
	@RequestMapping(value = "check", method = RequestMethod.POST)
	public void checkPost (HttpServletResponse response, HttpServletRequest request) throws Exception{
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		try{
			Map<String,String> map = MessageUtil.parseXml(request);
			
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			
			String message = null;// 待发送消息
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
				TextMessagePo textPo = new TextMessagePo();
				
				textPo.setFromUserName(toUserName);
				textPo.setToUserName(fromUserName);
				textPo.setMsgType(msgType);
				
				Date date = new Date();
				textPo.setCreateTime(date.getTime());
				
				message = MessageUtil.initText(toUserName, fromUserName, "查询条件输入有误，请重新输入");
				
				//message = MessageUtil.MessageToXml(textPo);
			}
			else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){
				String eventType = map.get("Event");
				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.welText());
				}
				else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
					String key = map.get("EventKey");
					
					// 判断菜单键值
//					if(key.equals("leftOne")){
//						message = MessageUtil.initText(toUserName, fromUserName, content);
//						message= MessageUtil.bindInfoLink("www.baidu.com", openId)
//					}
				}
				
			}
		    
			out.print(message);
		}catch(DocumentException e){
			e.printStackTrace();
		}
		
	}

}

