package cn.com.tj.byhy.model;
import java.util.Date;

public class Production {
	/**
	 * 2016/4/4
	 * @author wubeibei
	 * @see 历史活动信息表
	 */
	private int id;//活动ID
	private String name;//活动名
	private Date time;//活动时间
	private String address;//活动地点
	private String detail;//活动详情
	private String picture;//活动图片地址
	/****************************************************/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}
