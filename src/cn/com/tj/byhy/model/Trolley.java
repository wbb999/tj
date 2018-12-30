package cn.com.tj.byhy.model;

public class Trolley{

	/**
	 * 2016/1/11
	 * @author wubeibei
	 * @see 用户信息表
	 */
	private int id;//订单列表 ID
	private User user;//订单
	private Service_Product service_product;//服务/产品
	private int amount;//数量
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
	public Service_Product getService_product() {
		return service_product;
	}
	public void setService_product(Service_Product service_product) {
		this.service_product = service_product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
