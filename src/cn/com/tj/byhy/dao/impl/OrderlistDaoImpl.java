package cn.com.tj.byhy.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.com.tj.byhy.dao.OrderlistDao;
import cn.com.tj.byhy.model.Orderlist;
/**
 * 2018/12/30
 * @author wubeibei
 * @see Orderlist 增 删 改 查  以及各类查询的实现
 *
 */
public class OrderlistDaoImpl extends HibernateDaoSupport implements OrderlistDao {

	/*
	 * (non-Javadoc)
	 * @see cn.com.tj.byhy.dao.OrderlistDao#findOrderlists(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Orderlist> findOrderlists(String hql) {
		List<Orderlist> list=null;//初始化活动list
		if(super.getHibernateTemplate().find(hql).size()>0){
			list = (List<Orderlist>) super.getHibernateTemplate().find(hql);
		}
		return list;
	}
	
	
	@Override
	/**
	  * @see 查询单个订单单品列表
	  * @return 订单单品列表
	  */
	public Orderlist selectOne(String hql){
		Orderlist orderlist = null;
		if(super.getHibernateTemplate().find(hql).size()>0){
			orderlist = (Orderlist) super.getHibernateTemplate().find(hql).get(0);
		}
		return orderlist;
	}

	@Override
	/**
	  * @see 添加订单单品列表
	  * @return 是否成功添加
	  */
	public boolean addOrderlist(Orderlist orderlist) {
		try {
			super.getHibernateTemplate().save(orderlist);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	/**
	  * @see 删除订单单品列表
	  * @return Orderlist
	  */
	public boolean delOrderlist(Orderlist orderlist) {
		//删除语句
		try {
			super.getHibernateTemplate().delete(orderlist);
			return true;
		} catch (DataAccessException e) {
		}
		return false;
	}

	/**
	  * @see 修改订单单品列表信息
	  * @return 
	  */
	@Override
	public boolean updateOrderlist(Orderlist orderlist ) {
		try {
			//更新代码
			super.getHibernateTemplate().update(orderlist);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	/**
	 * @see 查询订单单品列表总数
	 */
	@Override
	public int selectOrderlistNum(String hql) {
		return super.getHibernateTemplate().find(hql).size();
	}
}
