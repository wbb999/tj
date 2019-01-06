package cn.com.tj.byhy.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.com.tj.byhy.dao.ProductionDao;
import cn.com.tj.byhy.model.Production;

/**
 * 2018/12/25
 * @see 作品dao层实现
 * @author wubeibei
 *
 */
public class ProductionDaoImpl extends HibernateDaoSupport implements ProductionDao {
	/*-----------------------方法----------------------------*/
	/**
	 * @see ProductionDaoImpl 作品添加
	 */
	@Override
	public boolean add(Production production) {
		try {
			super.getHibernateTemplate().save(production);//save方法插入一条activity
			return true;
		} catch (Exception e) {
			return false;//如果跑出异常，返回false
		}
	}
	/**
	 * @see ProductionDaoImpl 作品删除
	 */
	@Override
	public boolean del(Production production) {
		try {
			super.getHibernateTemplate().delete(production);;//delete方法删除一条activity
			return true;
		} catch (Exception e) {
			return false;//如果跑出异常，返回false
		}
	}
	/**
	 * @see ProductionDaoImpl 更新作品
	 */
	@Override
	public boolean update(Production production) {
		try {
			super.getHibernateTemplate().update(production);//update方法
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * @see ProductionDaoImpl 查找单个作品
	 */
	@Override
	public Production getone(Production production) {
		Production act=null;
		String hql = "FROM Production a where a.id="+production.getId();
		if(super.getHibernateTemplate().find(hql).size()>0){
			act = (Production) super.getHibernateTemplate().find(hql).get(0);
		}
		return act;
	}

	/**
	 * @see ProductionDaoImpl 选取作品
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Production> selectProduction(String hql,int pageNow, int pageSize ) {
		List<Production> list=null;//初始化作品list
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
	 * @see ProductionDaoImpl 查询条数
	 */
	@Override
	public int countProduction(String hql) {
		return getHibernateTemplate().find(hql).size();//返回查询到的总量 
	}
}