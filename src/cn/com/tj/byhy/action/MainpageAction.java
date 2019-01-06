package cn.com.tj.byhy.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.tj.byhy.model.Production;
import cn.com.tj.byhy.model.Service_Product;
import cn.com.tj.byhy.model.User;
import cn.com.tj.byhy.service.ProductionService;
import cn.com.tj.byhy.service.Service_ProductService;
import cn.com.tj.byhy.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 2018/12/25
 * @see 用户前台主页面相关的action
 * @author wubeibei
 *
 */
@Scope("request")
@Controller("mainpageAction")
public class MainpageAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	/*-----------------------对象----------------------------*/
	private Production production;//作品对象
	private List<Production> productions = null;//作品对象链表
	private List<Production> productionslink = null;//作品对象链表
	private String errowMesg;//错误信息提示
	private int pageNow=1;//当前页
	private int pageSize=9;//每页显示数据条数
	private String key="";//关键字查询

	private User oneuser=null;//用户
	
	private Service_Product service_product;//服务对象
	private List<Service_Product> service_products = null;//服务/产品对象链表
	
	private List<Service_Product> services = null;//服务产品对象链表
	private List<Service_Product> products = null;//实体产品对象链表
	private List<Service_Product> rentpro = null;//租赁产品对象链表
	

	@Autowired
	@Qualifier("productionServiceImpl")
	private ProductionService productionService;//注入productionService
	
	
	@Autowired
	@Qualifier("service_productServiceImpl")
	private Service_ProductService service_productService;//注入service_productService

	/*-----------------------方法----------------------------*/
	/**
	 * 单个作品查找
	 * @return singleSX
	 * @throws Exception
	 */
	public String singleproduction()throws Exception{
		System.out.println("single id:"+production.getId());
		production = productionService.singleProduction(production);//根据id选取单个作品
		if(production!=null){//如果查询成功返回singleSX
			rentpro = service_productService.allService_ProductByPage(1, 6);//获取列表前6个前台显示的产品
			productions = productionService.allProductionByPage(pageNow, 3);//查询list
			productionslink = productionService.allProductionByPage(pageNow, 6);//查询list
			return "singleproductionSX";
		}else{
			errowMesg = "查询失败";//查询失败将错误信息防入error
			return "error";
		}
	}
//	/**
//	 * 分页选取所有的作品
//	 * @return selectSX
//	 * @throws Exception
//	 */
//	@SuppressWarnings("unchecked")
//	public String allProductionByPage() throws Exception{
//		System.out.println("allProductionByPage");
//		@SuppressWarnings("rawtypes")
//		Map request = (Map)ActionContext.getContext().get("request");
//		Pager page = new Pager(getPageNow(), productionService.allnum());//查询所有数目
//		request.put("page",page);//把page放入request
//		productions = productionService.allProductionByPage(pageNow, pageSize);//查询list
//		System.out.println("allProductionByPagelist：size+"+productions.size());
//		System.out.println("productions[0]====="+productions.get(0).getName());
//		return "allproductionSX";//返回
//	}
	/**
	 * 选取主页面展示的9个作品
	 * @return selectSX
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String homemain() throws Exception{
		System.out.println("homemain");
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		@SuppressWarnings("rawtypes")
		Map request = (Map)ActionContext.getContext().get("request");
		Pager page = new Pager(getPageNow(), productionService.allnum());//查询所有数目
		request.put("page",page);//把page放入request
		productions = productionService.allProductionByPage(pageNow, pageSize);//查询list
		productions.get(0).getDetail().length();
		System.out.println("allProductionByPagelist：size+"+productions.size());
		System.out.println("productions[0]====="+productions.get(0).getName());
		return "getproductionSX";//返回
	}
	/**
	 * 选取主页面展示的9个作品
	 * @return selectSX
	 * @throws Exception
	 */
	public String homeindex() throws Exception{
		System.out.println("homeindex");
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		services = service_productService.getServices(pageNow, 4);//获取列表前4个前台显示的服务
		products = service_productService.getProducts(pageNow, 4);//获取列表前4个前台显示的实体产品
		rentpro = service_productService.getRentpro(pageNow, 4);//获取列表前4个前台显示的租赁产品
		productions = productionService.allProductionByPage(pageNow, 3);//查询list
		productionslink = productionService.allProductionByPage(pageNow, 6);//查询list
		
		service_products = service_productService.allService_ProductByPage(pageNow, 6);
		return "indexSX";//返回
	}
//	/**
//	 * 分页关键字查询的作品
//	 * @return keysearchSX
//	 * @throws Exception
//	 */
//	@SuppressWarnings("unchecked")
//	public String keysearchproduction() throws Exception{
//		System.out.println("keysearchByPage");
//		System.out.println("key:"+key);
//		System.out.println("productionService.selectnum(key):"+productionService.selectnum(key));
//		@SuppressWarnings("rawtypes")
//		Map request = (Map)ActionContext.getContext().get("request");
//		Pager page = new Pager(getPageNow(), productionService.selectnum(key));//查询所有数目
//		request.put("page",page);//把page放入request
//		productions = productionService.selectProductionByPage(key, pageNow, pageSize);//查询list
//		System.out.println("keysearchByPage==productions.size():"+productions.size());
//		return "keysearchproductionSX";//返回
//	}
//	
	/**
	 * 单个服务查找
	 * @return singleSX
	 * @throws Exception
	 */
	public String singleservice_product()throws Exception{
		System.out.println("single id:"+service_product.getId());
		service_product = service_productService.singleService_Product(service_product);//根据id选取单个服务
		if(service_product!=null){//如果查询成功返回singleSX
			rentpro = service_productService.allService_ProductByPage(1, 6);//获取列表前6个前台显示的产品
			productions = productionService.allProductionByPage(pageNow, 3);//查询list
			productionslink = productionService.allProductionByPage(pageNow, 6);//查询list
			return "singleservice_productSX";
		}else{
			errowMesg = "查询失败";//查询失败将错误信息防入error
			return "error";
		}
	}
	/**
	 * 关键字服务查找
	 * @return singleSX
	 * @throws Exception
	 */
	public String keysearchservice_product()throws Exception{
		System.out.println("single id:"+service_product.getId());
		service_product = service_productService.singleService_Product(service_product);//根据id选取单个服务
		if(service_product!=null){//如果查询成功返回singleSX
			rentpro = service_productService.allService_ProductByPage(1, 6);//获取列表前6个前台显示的产品
			productions = productionService.allProductionByPage(pageNow, 3);//查询list
			productionslink = productionService.allProductionByPage(pageNow, 6);//查询list
			return "singleservice_productSX";
		}else{
			errowMesg = "查询失败";//查询失败将错误信息防入error
			return "error";
		}
	}
//	/**
//	 * 分页选取所有的服务
//	 * @return allservice_productSX
//	 * @throws Exception
//	 */
//	@SuppressWarnings("unchecked")
//	public String allservice_productByPage() throws Exception{
//		System.out.println("allservice_productByPage");
//		@SuppressWarnings("rawtypes")
//		Map request = (Map)ActionContext.getContext().get("request");
//		Pager page = new Pager(getPageNow(), service_productService.allnum());//查询所有数目
//		System.out.println("service_productService.allnum():"+service_productService.allnum());
//		request.put("page",page);//把page放入request
//		service_products = service_productService.allService_ProductByPage(pageNow, pageSize);//查询list
//		System.out.println("allservice_productByPage:"+service_products.size());
//		return "allservice_productSX";//返回
//	}
	/**
	 * 分类 选取服务和产品 以及 业务页面所需要的 数据
	 * @return businessSX
	 * @throws Exception
	 */
	public String business() throws Exception{
		System.out.println("business");
		//Map request = (Map)ActionContext.getContext().get("request");
		//Pager page = new Pager(getPageNow(), service_productService.allnum());//查询所有数目
		System.out.println("service_productService.allnum():"+service_productService.allnum());
		//request.put("page",page);//把page放入request
		//service_products = service_productService.allService_ProductByPage(pageNow, pageSize);//查询list
		//System.out.println("allservice_productByPage:"+service_products.size());
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		services = service_productService.getServices(pageNow, 4);//获取列表前4个前台显示的服务
		products = service_productService.getProducts(pageNow, 4);//获取列表前4个前台显示的实体产品
		rentpro = service_productService.getRentpro(pageNow, 4);//获取列表前4个前台显示的租赁产品
		
		productions = productionService.allProductionByPage(pageNow, 3);//查询list
		productionslink = productionService.allProductionByPage(pageNow, 6);//查询list
		return "businessSX";//返回
	}	
//	/**
//	 * 分页选取关键字查询的服务
//	 * @return keysearchSX
//	 * @throws Exception
//	 */
//	@SuppressWarnings("unchecked")
//	public String keysearchservice_product() throws Exception{
//		System.out.println("keysearchByPage");
//		System.out.println("key:"+key);
//		@SuppressWarnings("rawtypes")
//		Map request = (Map)ActionContext.getContext().get("request");
//		Pager page = new Pager(getPageNow(), service_productService.selectnum(key));//查询所有数目
//		System.out.println("service_productService.allnum():"+service_productService.allnum());
//		request.put("page",page);//把page放入request
//		//service_products = service_productService.selectservice_productByPage(key, pageNow, pageSize);//查询list
//		System.out.println("keysearchByPage:"+service_products.size());
//		return "keysearchservice_productSX";//返回
//	}
	
	/*-----------------------get set----------------------------*/
	
	public String getErrowMesg() {
		return errowMesg;
	}
	public Production getProduction() {
		return production;
	}
	public void setProduction(Production production) {
		this.production = production;
	}
	public List<Production> getProductions() {
		return productions;
	}
	public void setProductions(List<Production> productions) {
		this.productions = productions;
	}
	public void setErrowMesg(String errowMesg) {
		this.errowMesg = errowMesg;
	}
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Service_Product getService_product() {
		return service_product;
	}
	public void setService_product(Service_Product service_product) {
		this.service_product = service_product;
	}
	public List<Service_Product> getService_products() {
		return service_products;
	}
	public void setService_products(List<Service_Product> service_products) {
		this.service_products = service_products;
	}
	public List<Service_Product> getServices() {
		return services;
	}
	public void setServices(List<Service_Product> services) {
		this.services = services;
	}
	public List<Service_Product> getProducts() {
		return products;
	}
	public void setProducts(List<Service_Product> products) {
		this.products = products;
	}
	public List<Service_Product> getRentpro() {
		return rentpro;
	}
	public void setRentpro(List<Service_Product> rentpro) {
		this.rentpro = rentpro;
	}

	public List<Production> getProductionslink() {
		return productionslink;
	}

	public void setProductionslink(List<Production> productionslink) {
		this.productionslink = productionslink;
	}

	public User getOneuser() {
		return oneuser;
	}

	public void setOneuser(User oneuser) {
		this.oneuser = oneuser;
	}
	
}
