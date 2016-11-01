package com.tjport.common.wechat.po;

/**
 * Access_Token
 * @author test
 * Date: 2016-03-18
 */

public class AccessTokenPo {

	private String token;
	private int expiresIn;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
