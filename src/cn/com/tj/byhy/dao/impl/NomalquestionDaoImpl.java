package cn.com.tj.byhy.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cn.com.tj.byhy.dao.NomalquestionDao;
import cn.com.tj.byhy.model.Nomalquestion;
/**
 * 2016/4/4
 * @author wubeibei
 * @see Nomalquestion 增 删 改 查  以及各类查询的实现
 *
 */
public class NomalquestionDaoImpl extends HibernateDaoSupport implements NomalquestionDao {

	/**
	 * @see NomalquestionDaoImpl 活动添加
	 */
	@Override
	public boolean add(Nomalquestion question) {
		try {
			super.getHibernateTemplate().save(question);//save方法插入一条Nomalquestion
			return true;
		} catch (Exception e) {
			return false;//如果跑出异常，返回false
		}
	}
	/**
	 * @see NomalquestionDaoImpl 活动删除
	 */
	@Override
	public boolean del(Nomalquestion question) {
		try {
			super.getHibernateTemplate().delete(question);;//delete方法删除一条Nomalquestion
			return true;
		} catch (Exception e) {
			return false;//如果跑出异常，返回false
		}
	}
	/**
	 * @see NomalquestionDaoImpl 更新活动
	 */
	@Override
	public boolean update(Nomalquestion question) {
		try {
			super.getHibernateTemplate().update(question);//update方法
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * @see NomalquestionDaoImpl 查找单个活动
	 */
	@Override
	public Nomalquestion getone(Nomalquestion question) {
		Nomalquestion act=null;
		String hql = "FROM Nomalquestion a where a.id="+question.getId();
		if(super.getHibernateTemplate().find(hql).size()>0){
			act = (Nomalquestion) super.getHibernateTemplate().find(hql).get(0);
		}
		return act;
	}

	/**
	 * @see NomalquestionDaoImpl 选取活动
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Nomalquestion> selectNomalquestion(String hql,int pageNow, int pageSize ) {
		List<Nomalquestion> list=null;//初始化活动list
		try {
			Session session = getHibernateTemplate().getSessionFactory().openSession();//新建session
			Transaction ts = session.beginTransaction();//创建事务
			Query query = session.createQuery(hql);//持久化操作
			if(pageSize!=0){//0代表不分页
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
	 * @see NomalquestionDaoImpl 查询条数
	 */
	@Override
	public int countNomalquestion(String hql) {
		return getHibernateTemplate().find(hql).size();//返回查询到的总量 
	}
}
