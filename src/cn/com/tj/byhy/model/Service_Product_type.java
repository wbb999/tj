package cn.com.tj.byhy.model;

import java.util.Set;

public class Service_Product_type {
	/**
	 * 2018/12/25
	 * @author wubeibei
	 * @see 提供服务信息表
	 */
	private int id;//类型ID
	private String name;//类型名
	private Set<Service_Product> service_products;//用户类型对应的用户
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
	public Set<Service_Product> getService_products() {
		return service_products;
	}
	public void setService_products(Set<Service_Product> service_products) {
		this.service_products = service_products;
	}
	
}
