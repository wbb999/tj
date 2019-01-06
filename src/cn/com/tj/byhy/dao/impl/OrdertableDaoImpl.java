package cn.com.tj.byhy.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.com.tj.byhy.dao.OrdertableDao;
import cn.com.tj.byhy.model.Ordertable;
/**
 * 2018/12/30
 * @author wubeibei
 * @see Ordertable 增 删 改 查  以及各类查询的实现
 *
 */
public class OrdertableDaoImpl extends HibernateDaoSupport implements OrdertableDao {

	/*
	 * (non-Javadoc)
	 * @see cn.com.tj.byhy.dao.OrderDao#findOrders(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Ordertable> findOrders(String hql) {
		List<Ordertable> list=null;//初始化活动list
		if(super.getHibernateTemplate().find(hql).size()>0){
			list = (List<Ordertable>) super.getHibernateTemplate().find(hql);
		}
		return list;
	}
	
	
	@Override
	/**
	  * @see 查询单个订单
	  * @return 订单
	  */
	public Ordertable selectOne(String hql){
		Ordertable order = null;
		if(super.getHibernateTemplate().find(hql).size()>0){
			order = (Ordertable) super.getHibernateTemplate().find(hql).get(0);
		}
		return order;
	}

	@Override
	/**
	  * @see 添加订单
	  * @return 是否成功添加
	  */
	public boolean addOrder(Ordertable oneorder) {
		try {
			super.getHibernateTemplate().save(oneorder);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	/**
	  * @see 删除订单
	  * @return Order
	  */
	public boolean delOrder(Ordertable oneorder) {
		//删除语句
		try {
			super.getHibernateTemplate().delete(oneorder);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	  * @see 修改订单信息
	  * @return 
	  */
	@Override
	public boolean updateOrder(Ordertable oneorder ) {
		try {
			//更新代码
			super.getHibernateTemplate().update(oneorder);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	/**
	 * @see 查询订单总数
	 */
	@Override
	public int selectOrderNum(String hql) {
		return super.getHibernateTemplate().find(hql).size();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Ordertable> selectOrdertable(String hql, int pageNow,
			int pageSize) {
		List<Ordertable> list=null;//初始化服务list
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
}
