package com.tjport.wechatEt.wechat.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信用户信息
 * @author By Neil
 * Date: 2016-10-20
 */

public class WxUserVo {

	private Boolean subscribe;
	private String openid;
	private String nickname;
	private Integer sex;
	private String language;
	private String city;
	private String province;
	private String country;
	private String headimgurl;
	private long subscribe_time;
	private String unionid;
	private String remark;
	private String groupid;
	private String tagid_list;
	
	public Boolean getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public long getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(long subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getTagid_list() {
		return tagid_list;
	}
	public void setTagid_list(String tagid_list) {
		this.tagid_list = tagid_list;
	}
	
	
	
}
