package cn.com.tj.byhy.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.tj.byhy.model.Service_Product;
import cn.com.tj.byhy.model.Service_Product_type;
import cn.com.tj.byhy.service.Service_ProductService;
import cn.com.tj.byhy.util.Pager;
import cn.com.tj.byhy.util.PictureUpload;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 2016/4/30
 * @author wubeibei
 * @see service_product增删改查
 */
@Scope("request")
@Controller("service_productAction")
public class Service_ProductAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	/*-----------------------对象----------------------------*/
	private Service_Product service_product;//服务对象
	private List<Service_Product> service_products = null;//服务对象链表
	private String errowMesg;//错误信息提示
	private int pageNow=1;//当前页
	private int pageSize=8;//每页显示数据条数
	private String key="";//关键字查询

	//图片上传属性
	private File myFile;
	private String contentType;
	private String fileName;
	private String caption;
	private String imageFileName;
	@Autowired
	@Qualifier("service_productServiceImpl")
	private Service_ProductService service_productService;//注入service_productService

	/*
	 * 封装Service_Product对象
	 */
	public Service_Product service_product() {
		Service_Product_type ty = new Service_Product_type();
		if (service_product.getType().getId() == 1) {
			ty.setId(1);
			ty.setName("实体产品");
		} else if (service_product.getType().getId() == 2) {
			ty.setId(2);
			ty.setName("服务产品");
		} else if (service_product.getType().getId() == 3) {
			ty.setId(3);
			ty.setName("租赁产品");
		} 
		service_product.setType(ty);
		System.out.println(service_product.getName());
		return service_product;
	}

	/*-----------------------方法----------------------------*/
	/**
	 * 服务添加
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		System.out.println("service_product_add");
		service_product = service_product();
		service_product.setPicture("//8086");
		System.out.println("service_product_name"+service_product.getName());
		System.out.println("service_product_Intro"+service_product.getIntro());
		System.out.println("service_product_Price"+service_product.getPrice());
		System.out.println("service_product_Type"+service_product.getType());
		if(service_productService.add(service_product)){//添加操作
			errowMesg = "add success!";
			return "addSX";
		}else{//添加失败
			errowMesg = "添加失败";//将错误信息传入error
			return "error";
		}
	}
	
	/**
	 * 服务图片添加
	 * @return
	 * @throws Exception
	 */
	public String toaddpicture() throws Exception{
		System.out.println("service_product_toaddpicture");
		service_product = service_productService.singleService_Product(service_product);
		return "toaddpicture";
	}
	/**
	 * 服务tupian添加
	 * @return
	 * @throws Exception
	 */
	public String addpicture() throws Exception{
		System.out.println("service_product_addpicture");
		System.out.println("service_product[0].id"+service_product.getId());
		service_product = service_productService.singleService_Product(service_product);
		System.out.println("service_product[0].name"+service_product.getName());
		//	String path = ServletActionContext.getServletContext().getRealPath( "/picture" ) + "/" ;
		//上面获取的路径不在项目中，而是在临时文件里，所以下面直接用本机绝对路径，更换workspace路径时要手动修改路径
		String path="C:/java/workspace/TJ/WebContent/picture/";//picture在本机的绝对路径
		System.out.println("path"+path);
		PictureUpload p = new PictureUpload();
		String picture = p.uploadFile(myFile, fileName, path,imageFileName);
		System.out.println("picture"+picture);
		service_product.setPicture("picture/"+picture);//添加图片地址到数据库
		if(service_productService.update(service_product)){//添加操作
			errowMesg = "addpicture success!";//查询信息
			return "addpictureSX";
		}else{//添加失败
			errowMesg = "添加失败";//将错误信息传入error
			return "error";
		}
	}
	
	/**
	 * 删除服务
	 * @return deleteSX
	 * @throws Exception
	 */
	public String delete()throws Exception{
		System.out.println("service_product_delete");
		service_product = service_productService.singleService_Product(service_product);
		if(service_productService.delete(service_product)){//执行删除操作
			System.out.println("service_productService.delete(service_product)");
			errowMesg = "delete success!";
			return "deleteSX";//返回deleteSX
		}
		errowMesg = "删除失败";//删除失败将错误信息防入error
		return "error";
	}
	/**
	 * 服务修改前查询
	 * @return toupdateSX
	 * @throws Exception
	 */
	public String toupdate()throws Exception{
		service_product = service_productService.singleService_Product(service_product);//查询单个服务
		if(service_product != null){
			return "toupdateSX";//非空的话返回toupdateSX
		}else{
			errowMesg = "查询失败";//查询失败将错误信息防入error
			return "error";
		}
	}
	/**
	 * 服务更新
	 * @ return 
	 */
	public String update()throws Exception{
		//执行update操作
		System.out.println("update!");
		System.out.println("update id:"+service_product.getId());
		System.out.println("service_product_name"+service_product.getName());
		System.out.println("service_product_Intro"+service_product.getIntro());
		System.out.println("service_product_Price"+service_product.getPrice());
		
		Service_Product temProduct=service_productService.singleService_Product(service_product);
		service_product = service_product();//设置type
		temProduct.setName(service_product.getName());
		temProduct.setIntro(service_product.getIntro());
		temProduct.setPrice(service_product.getPrice());
		temProduct.setType(service_product.getType());
		System.out.println("service_product_Type"+service_product.getType().getName());
		if(service_productService.update(temProduct)){
			errowMesg = "update success!";
			return "updateSX";
		}else{
			errowMesg = "更新失败";
			return "error";
		}
	}
	/**
	 * 单个服务查找
	 * @return singleSX
	 * @throws Exception
	 */
	public String single()throws Exception{
		System.out.println("single id:"+service_product.getId());
		service_product = service_productService.singleService_Product(service_product);//根据id选取单个服务
		if(service_product!=null){//如果查询成功返回singleSX
			return "singleSX";
		}else{
			errowMesg = "查询失败";//查询失败将错误信息防入error
			return "error";
		}
	}
	/**
	 * 分页选取所有的服务
	 * @return allservice_productSX
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String allservice_productByPage() throws Exception{
		System.out.println("allservice_productByPage");
		@SuppressWarnings("rawtypes")
		Map request = (Map)ActionContext.getContext().get("request");
		Pager page = new Pager(getPageNow(), service_productService.allnum());//查询所有数目
		System.out.println("service_productService.allnum():"+service_productService.allnum());
		request.put("page",page);//把page放入request
		service_products = service_productService.allService_ProductByPage(pageNow, pageSize);//查询list
		System.out.println("allservice_productByPage:"+service_products.size());
		return "allservice_productSX";//返回
	}
	/**
	 * 分页选取关键字查询的服务
	 * @return keysearchSX
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String keysearch() throws Exception{
		System.out.println("keysearchByPage");
		System.out.println("key:"+key);
		@SuppressWarnings("rawtypes")
		Map request = (Map)ActionContext.getContext().get("request");
		Pager page = new Pager(getPageNow(), service_productService.selectnum(key));//查询所有数目
		System.out.println("service_productService.allnum():"+service_productService.allnum());
		request.put("page",page);//把page放入request
		//service_products = service_productService.selectservice_productByPage(key, pageNow, pageSize);//查询list
		System.out.println("keysearchByPage:"+service_products.size());
		return "keysearchSX";//返回
	}
	/*-----------------------get set----------------------------*/
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
	public String getErrowMesg() {
		return errowMesg;
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
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public void setMyFileContentType(String contentType) {
        this .contentType = contentType;
   } 
    public void setMyFileFileName(String fileName) {
        this .fileName = fileName;
   }
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
}
