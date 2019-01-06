package cn.com.tj.byhy.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.tj.byhy.model.Service_Product;

/**
 * 2018/12/25
 * @author wubeibei
 * @see Service_ProductDao接口
 */
@Service
public interface Service_ProductDao {
	
public boolean add(Service_Product service_product);//添加服务/产品
	
	public boolean del(Service_Product service_product);//删除服务/产品
	
	public boolean update(Service_Product service_product);//更新服务/产品
	
	public Service_Product getone(Service_Product service_product);//查找单个服务/产品

	public List<Service_Product> selectService_Product(String hql,int pageNow, int pageSize);//分页查询服务/产品
	
	public int countService_Product(String hql);//计算查询的服务/产品条数

}
