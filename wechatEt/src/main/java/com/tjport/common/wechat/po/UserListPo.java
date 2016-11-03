package com.tjport.common.wechat.po;

import java.util.ArrayList;
import java.util.List;

/**
 * Access_Token
 * @author test
 * Date: 2016-11-03
 */

public class UserListPo {

	private int total;
	private int count;
	private List<String> openid = new ArrayList<String>();
	private String next_openid;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<String> getOpenid() {
		return openid;
	}
	public void setOpenid(List<String> openid) {
		this.openid = openid;
	}
	public String getNext_openid() {
		return next_openid;
	}
	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	
	
}
