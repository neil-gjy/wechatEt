package com.tjport.common.wechat;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.tjport.common.wechat.po.TextMessagePo;

/**
 * 微信消息解析
 * @author Neil
 * Date: 2016-03-17
 */

public class MessageUtil {
	
	public static final String MESSAGE_TEXT = "text"; 
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "view";
	
	
	/**
	 * 解析微信Xml信息
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		
		InputStream inputStream = request.getInputStream();
		
		SAXReader reader = new SAXReader();
		
		Document document = reader.read(inputStream);
		
		Element root = document.getRootElement();
		
		List<Element> elementList = root.elements();
		
		for (Element e : elementList)
			map.put(e.getName(), e.getText());
		
		inputStream.close();
		inputStream = null;
		
		return map;	
	}
	
	/**
	 * 将文本转为XML
	 * @param textMessagePo
	 * @return xml
	 */
	public static String MessageToXml(TextMessagePo textMessagePo){
		XStream xstream = new XStream();
		
		xstream.alias("xml", textMessagePo.getClass());
		return xstream.toXML(textMessagePo);
	}
	
	/**
	 * 文本消息转XML
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	public static String initText(String toUserName, String fromUserName, String content){
		TextMessagePo textPo = new TextMessagePo();
		
		textPo.setFromUserName(toUserName);
		textPo.setToUserName(fromUserName);
		textPo.setMsgType(MESSAGE_TEXT);
		
		Date date = new Date();
		textPo.setCreateTime(date.getTime());
		textPo.setContent(content);
		
		return MessageToXml(textPo);
	}
	
	/**
	 * 公众号关注消息
	 * @return
	 */
	public static String welText(){
		StringBuilder content = new StringBuilder();
		
		content.append("欢迎关注天津港信息技术发展有限公司！\n");
		content.append("目前开放功能：散杂货费收报表查询\n\n");
		/*content.append("本月工资及奖金可直接通过点击菜单进行查询\n");
		content.append("历史工资以2016年4月为起始点\n");
		content.append("历史奖金以2016年1月未起始点\n");*/
		
		return content.toString();
	}
	
	/**
	 * 历史奖金消息
	 * @return
	 */
	public static String hisBonus(){
		StringBuilder content = new StringBuilder();
		
		content.append("历史奖金查询：\n\n");
		content.append("例如输入【jj201602】或者【jj2016】");
		content.append("分别查询2016年2月奖金或者2016年奖金\n");
		
		return content.toString();
	}
	
	/**
	 * 历史工资消息
	 * @return
	 */
	public static String hisSalary(){
		StringBuilder content = new StringBuilder();
		
		content.append("历史工资查询：\n\n");
		content.append("例如输入【gz201602】或者【gz2016】\n");
		content.append("分别查询2016年2月工资或者2016年工资\n");
		
		return content.toString();
	}
	
	/**
	 * 绑定信息及链接
	 * @param link
	 * @param openId
	 * @return 绑定消息及链接
	 */
	public static String bindInfoLink(String link, String openId){
		StringBuilder content = new StringBuilder();
		
		content.append("点击下方链接进行绑定账户：\n\n");
		content.append("绑定信息后可获取相应系统权限\n");
		content.append( "<a href='" + link +"/" + openId + "'>点击这里，进行绑定</a>\n");
		
		return content.toString();
	}
	
	
	public static String initPicText(String toUserName, String fromUserName, String content){
		TextMessagePo textPo = new TextMessagePo();
		
		textPo.setFromUserName(toUserName);
		textPo.setToUserName(fromUserName);
		textPo.setMsgType(MESSAGE_TEXT);
		
		Date date = new Date();
		textPo.setCreateTime(date.getTime());
		textPo.setContent(content);
		
		return MessageToXml(textPo);
	}
	
	
}
