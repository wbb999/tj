package cn.com.tj.byhy.model;
import java.util.Set;
public class Order_state{
	/**
	 * 2018/12/25
	 * @author wubeibei
	 * @see 用户信息表
	 */
	private int id;//类型ID
	private String name;//类型名
	private Set<Ordertable> orders;//订单类型对应的订单
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
	public Set<Ordertable> getOrders() {
		return orders;
	}
	public void setOrders(Set<Ordertable> orders) {
		this.orders = orders;
	}
}
