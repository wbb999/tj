package cn.com.tj.byhy.model;

public class Orderlist{

	/**
	 * 2018/12/25
	 * @author wubeibei
	 * @see 用户信息表
	 */
	private int id;//订单列表 ID
	private Ordertable order;//订单
	private Service_Product service_product;//服务/产品
	private int amount;//数量
	/****************************************************/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Ordertable getOrder() {
		return order;
	}
	public void setOrder(Ordertable order) {
		this.order = order;
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
