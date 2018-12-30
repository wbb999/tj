package cn.com.tj.byhy.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.com.tj.byhy.dao.Service_ProductDao;
import cn.com.tj.byhy.model.Service_Product;
/**
 * 2016/4/4
 * @author wubeibei
 * @see Service_Product 增 删 改 查  以及各类查询的实现
 *
 */
public class Service_ProductDaoImpl extends HibernateDaoSupport implements Service_ProductDao {

	/**
	 * @see Service_ProductDaoImpl 服务添加
	 */
	@Override
	public boolean add(Service_Product service_product) {
		try {
			super.getHibernateTemplate().save(service_product);//save方法插入一条Service_Product
			return true;
		} catch (Exception e) {
			return false;//如果跑出异常，返回false
		}
	}
	/**
	 * @see Service_ProductDaoImpl 服务删除
	 */
	@Override
	public boolean del(Service_Product service_product) {
		try {
			super.getHibernateTemplate().delete(service_product);;//delete方法删除一条Service_Product
			return true;
		} catch (Exception e) {
			return false;//如果跑出异常，返回false
		}
	}
	/**
	 * @see Service_ProductDaoImpl 更新服务
	 */
	@Override
	public boolean update(Service_Product service_product) {
		try {
			super.getHibernateTemplate().update(service_product);//update方法
			System.out.println("service_productDao:true!");
			return true;
		} catch (Exception e) {
			System.out.println("service_productDao:false!");
			return false;
		}
	}
	/**
	 * @see Service_ProductDaoImpl 查找单个服务
	 */
	@Override
	public Service_Product getone(Service_Product service_product) {
		Service_Product act=null;
		String hql = "FROM Service_Product a where a.id="+service_product.getId();
		if(super.getHibernateTemplate().find(hql).size()>0){
			act = (Service_Product) super.getHibernateTemplate().find(hql).get(0);
		}
		return act;
	}

	/**
	 * @see Service_ProductDaoImpl 选取服务
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Service_Product> selectService_Product(String hql,int pageNow, int pageSize ) {
		List<Service_Product> list=null;//初始化服务list
		try {
			Session session = getHibernateTemplate().getSessionFactory().openSession();//新建session
			Transaction ts = session.beginTransaction();//创建事务
			Query query = session.createQuery(hql);//持久化操作
			if(pageSize!=0){
				int firstResult=(pageNow-1)*pageSize;//选择查询数据的最小值
				query.setFirstResult(firstResult);//result开始的条数
				query.setMaxResults(pageSize);//result结束的条数
			}
			list = query.list();//获取list
			ts.commit();//提交事务
			session.close();//关闭session
			session=null;
			return list;//返回list
		} catch (Exception e) {
			e.printStackTrace();
			return list;//如果抛出异常，依旧返回list
		}
	}	
	/**
	 * @see Service_ProductDaoImpl 查询条数
	 */
	@Override
	public int countService_Product(String hql) {
		return getHibernateTemplate().find(hql).size();//返回查询到的总量 
	}

}
