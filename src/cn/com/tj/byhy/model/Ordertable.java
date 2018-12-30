package cn.com.tj.byhy.model;

import java.util.Date;
import java.util.Set;
public class Ordertable{

	/**
	 * 2016/1/11
	 * @author wubeibei
	 * @see 用户信息表
	 */
	private int id;//用户ID
	private User user;//登陆名
	private Date time;//时间
	private Order_state state;//用户类型id
	private Set<Orderlist> orderlistSet;//订单对应的订单列表
	/****************************************************/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Order_state getState() {
		return state;
	}
	public void setState(Order_state state) {
		this.state = state;
	}
	public Set<Orderlist> getOrderlistSet() {
		return orderlistSet;
	}
	public void setOrderlistSet(Set<Orderlist> orderlistSet) {
		this.orderlistSet = orderlistSet;
	}
	
}
