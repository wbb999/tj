package cn.com.tj.byhy.model;

import java.util.Set;
public class User{
	/**
	 * 2016/1/11
	 * @author wubeibei
	 * @see 用户信息表
	 */
	private int id;//用户ID
	private String username;//登陆名
	private String password;//密码
	private String realName;//真实姓名
	private int state;//状态，1正常
	private String phone;//电话
	private String mail;//邮箱
	private User_type type;//用户类型id
	private Set<Ordertable> orderSet;//user对应的所有订单
	private Set<Trolley> trolleySet;//user对应的所有购物车物品
	/****************************************************/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public User_type getType() {
		return type;
	}
	public void setType(User_type type) {
		this.type = type;
	}
	public Set<Ordertable> getOrderSet() {
		return orderSet;
	}
	public void setOrderSet(Set<Ordertable> orderSet) {
		this.orderSet = orderSet;
	}
	public Set<Trolley> getTrolleySet() {
		return trolleySet;
	}
	public void setTrolleySet(Set<Trolley> trolleySet) {
		this.trolleySet = trolleySet;
	}
	
}
