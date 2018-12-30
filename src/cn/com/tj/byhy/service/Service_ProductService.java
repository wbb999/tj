package cn.com.tj.byhy.service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.com.tj.byhy.model.Service_Product;

/**
 * 2016/4/4
 * @author wubeibei
 * @see Suportservice服务/产品层接口
 */
@Scope("singleton")
@Controller("service_productService")
public interface Service_ProductService {

	/*-----------------------方法----------------------------*/
	public boolean add(Service_Product service_product);//添加服务/产品
	
	public boolean delete(Service_Product service_product);//删除服务/产品
	
	public boolean update(Service_Product service_product);//更新服务/产品
	
	public int allnum();//查询服务/产品总条数
	
	public int selectnum(String key);//按条件查询服务/产品总条数
	
	public List<Service_Product> allService_Product();//获取所有服务/产品
	
	public Service_Product singleService_Product(Service_Product service_product);//获取单个服务/产品信息
	
	public List<Service_Product> allService_ProductByPage(int pageNow, int pageSize);//分页获取所有服务/产品
	
	public List<Service_Product> selectService_ProductByPage(String key,int pageNow, int pageSize);//分页查询服务/产品

	public List<Service_Product> getServices(int pageNow, int pageSize);//获取列表前9个前台显示的服务

	public List<Service_Product> getProducts(int pageNow, int pageSize);//获取列表前9个前台显示的实体产品

	public List<Service_Product> getRentpro(int pageNow, int pageSize);//获取列表前9个前台显示的租赁产品

}
