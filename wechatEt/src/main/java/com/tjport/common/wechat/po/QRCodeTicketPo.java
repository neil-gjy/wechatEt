package com.tjport.common.wechat.po;

/**
 * Access_Token
 * @author test
 * Date: 2017-01-06
 */

public class QRCodeTicketPo {

	private String ticket;
	private int expire_seconds;
	private String url;
	
	
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public int getExpire_seconds() {
		return expire_seconds;
	}
	public void setExpire_seconds(int expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
