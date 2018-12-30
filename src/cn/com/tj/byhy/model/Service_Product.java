package cn.com.tj.byhy.model;

public class Service_Product {
	/**
	 * 2016/4/4
	 * @author wubeibei
	 * @see 提供服务信息表
	 */
	/***********************  属性    *****************************/
	private int id;//服务ID
	private String name;//服务名称
	private double price;//产品价格
	private String picture;//服务图片
	private String intro;//服务介绍
	private Service_Product_type type;//服务类别  
	/************************  get set  ****************************/
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Service_Product_type getType() {
		return type;
	}
	public void setType(Service_Product_type type) {
		this.type = type;
	}
}
