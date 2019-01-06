package cn.com.tj.byhy.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.com.tj.byhy.dao.TrolleyDao;
import cn.com.tj.byhy.model.Trolley;

/**
 * 2018/12/25
 * @see 作品dao层实现
 * @author wubeibei
 *
 */
public class TrolleyDaoImpl extends HibernateDaoSupport implements TrolleyDao {
	/*-----------------------方法----------------------------*/
	/**
	 * @see TrolleyDaoImpl 作品添加
	 */
	@Override
	public boolean add(Trolley trolley) {
		try {
			super.getHibernateTemplate().save(trolley);//save方法插入一条activity
			return true;
		} catch (Exception e) {
			return false;//如果跑出异常，返回false
		}
	}
	/**
	 * @see TrolleyDaoImpl 作品删除
	 */
	@Override
	public boolean del(Trolley trolley) {
		try {
			super.getHibernateTemplate().delete(trolley);;//delete方法删除一条activity
			return true;
		} catch (Exception e) {
			return false;//如果跑出异常，返回false
		}
	}
	/**
	 * @see TrolleyDaoImpl 更新作品
	 */
	@Override
	public boolean update(Trolley trolley) {
		try {
			super.getHibernateTemplate().update(trolley);//update方法
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * @see TrolleyDaoImpl 查找单个作品
	 */
	@Override
	public Trolley getone(Trolley trolley) {
		Trolley act=null;
		String hql = "FROM Trolley a where a.id="+trolley.getId();
		if(super.getHibernateTemplate().find(hql).size()>0){
			act = (Trolley) super.getHibernateTemplate().find(hql).get(0);
		}
		return act;
	}

	/**
	 * @see TrolleyDaoImpl 选取作品
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Trolley> selectTrolley(String hql,int pageNow, int pageSize ) {
		List<Trolley> list=null;//初始化作品list
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
	 * @see TrolleyDaoImpl 查询条数
	 */
	@Override
	public int countTrolley(String hql) {
		return getHibernateTemplate().find(hql).size();//返回查询到的总量 
	}
	/**
	 * @see TrolleyDaoImpl 查询一条记录
	 */
	@Override
	public Trolley selectone(String hql) {
		Trolley act=null;
		System.out.println("Trolleydao selectone(String hql)");
		if(super.getHibernateTemplate().find(hql).size()>0){
			act = (Trolley) super.getHibernateTemplate().find(hql).get(0);
		}
		return act;
	}
}