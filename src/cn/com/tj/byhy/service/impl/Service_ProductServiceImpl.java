package cn.com.tj.byhy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.com.tj.byhy.dao.Service_ProductDao;
import cn.com.tj.byhy.model.Service_Product;
import cn.com.tj.byhy.service.Service_ProductService;

/**
 * 2018/12/25
 * @author wubeibei
 * @see  所有对用户Service_Product处理的服务/产品处理层 ，对获取的信息处理分配后调用相应的dao层实现类
 */
@Scope("singleton")
@Controller("service_productServiceImpl")
public class Service_ProductServiceImpl implements Service_ProductService {
	/*-----------------------对象-----------------------------*/
	@Autowired
	private Service_ProductDao service_productDao;//自动注入
	/*-----------------------方法-----------------------------*/
	/**
	 * @see 添加服务/产品
	 * @param service_product
	 * @return
	 */
	@Override
	public boolean add(Service_Product service_product) {
		if(service_productDao.add(service_product)){//如果添加成功返回true，否则返回false
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @see 删除服务/产品
	 * @param service_product
	 * @return
	 */
	@Override
	public boolean delete(Service_Product service_product) {
		Service_Product act = singleService_Product(service_product);//取出要查询的服务/产品
		if(service_productDao.del(act)){//删除该服务/产品
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @see 更新服务/产品
	 */
	@Override
	public boolean update(Service_Product service_product) {
		if(service_productDao.update(service_product)){
			System.out.println("service_productserv:true!");
			return true;
		}else{
			System.out.println("service_productserv:false!");
			return false;
		}
	}
	/**
	 * @see 查询服务/产品总条数
	 */
	@Override
	public int allnum() {
		String hql = "FROM Service_Product";
		return service_productDao.countService_Product(hql);
	}
	/**
	 * @see 按关键字条件查询服务/产品总条数
	 */
	@Override
	public int selectnum(String key) {
		String hql="FROM Service_Product a where a.name like '%"+key+"%' or a.intro like '%"+key+"%' ";
		return service_productDao.countService_Product(hql);
	}
	
	/**
	 * @see 查询所有服务/产品
	 * @return
	 */
	@Override
	public List<Service_Product> allService_Product(){
		String hql = "FROM Service_Product a ORDER BY a.id DESC";
		return service_productDao.selectService_Product(hql,0,0);//0,0不分页
	}
	/**
	 * @see 单个查询  
	 * @param service_product
	 * @return
	 */
	@Override
	public Service_Product singleService_Product(Service_Product service_product) {
		return service_productDao.getone(service_product);
	}
	/**
	 * @see 分页获取所有服务/产品
	 * @param service_product
	 * @return
	 */
	@Override
	public List<Service_Product> allService_ProductByPage(int pageNow, int pageSize){
		String hql="FROM Service_Product a ORDER BY a.id DESC";
		return service_productDao.selectService_Product(hql, pageNow, pageSize);
	};

	/**
	 * @see 分页获取按关键字查询的服务/产品
	 * @param service_product
	 * @return
	 */
	@Override
	public List<Service_Product> selectService_ProductByPage(String key,int pageNow, int pageSize) {
		String hql="FROM Service_Product a where a.name like '%"+key+"%' or a.intro like '%"+key+"%' ";
		return service_productDao.selectService_Product(hql, pageNow, pageSize);
	}
	/**
	 * @see 获取列表前9个前台显示的服务
	 * @param service_product
	 * @return
	 */
	@Override
	public List<Service_Product> getServices(int pageNow, int pageSize) {
		String hql="FROM Service_Product a where a.type.id=2 ORDER BY a.id DESC";
		return service_productDao.selectService_Product(hql, pageNow, pageSize);
	}
	/**
	 * @see 获取列表前9个前台显示的实体产品
	 * @param service_product
	 * @return
	 */
	@Override
	public List<Service_Product> getProducts(int pageNow, int pageSize) {
		String hql="FROM Service_Product a where a.type.id=1 ORDER BY a.id DESC";
		return service_productDao.selectService_Product(hql, pageNow, pageSize);
	}
	/**
	 * @see 获取列表前9个前台显示的租赁产品
	 * @param service_product
	 * @return
	 */
	@Override
	public List<Service_Product> getRentpro(int pageNow, int pageSize) {
		String hql="FROM Service_Product a where a.type.id=3 ORDER BY a.id DESC";
		return service_productDao.selectService_Product(hql, pageNow, pageSize);
	}

	
}
