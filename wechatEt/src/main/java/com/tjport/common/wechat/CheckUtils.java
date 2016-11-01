package com.tjport.common.wechat;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 微信校验
 * @author by neil 
 * @date 2016-03-17
 */
public class CheckUtils {
   //private static final String token = "xinxi";
   private static final String token = "neilguo";//测试公众号
	
   public static boolean checkSingnature(String signature, String timestamp, String nonce){
	   String arr[] = new String[]{token,timestamp,nonce};
	   
	   Arrays.sort(arr);
	   
	   StringBuffer content = new StringBuffer();
	   for(int i=0;i<arr.length;i++){
		   content.append(arr[i]);
	   }
	   
	   String srcSha1 = getSha1(content.toString());
	   
	   return srcSha1.equals(signature);
   }
   
   public static String getSha1(String src){
	   if(src == null || src.length() == 0){
		   return null;
	   }
	   
	   char hexdigits[] = {'0','1','2','3','4','5','6','7','8','9',
			   'a','b','c','d','e','f'};
	   
	   try{
		   MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
		   mdTemp.update(src.getBytes("UTF-8"));
		   
		   byte[] md = mdTemp.digest();
		   int len = md.length;
		   char buff[] = new char[len*2];
		   int n = 0;
		   
		   for(int i=0;i<len; i++){
			   byte bt = md[i];
			   buff[n++] = hexdigits[bt>>>4 & 0xf];
			   buff[n++] = hexdigits[bt & 0xf];
		   }
		   return new String(buff);
		   
	   }catch(Exception e){
		   return null;
	   }
   }
}