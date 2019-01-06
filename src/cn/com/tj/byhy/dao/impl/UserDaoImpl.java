package cn.com.tj.byhy.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.com.tj.byhy.dao.UserDao;
import cn.com.tj.byhy.model.User;
/**
 * 2018/12/30
 * @author wubeibei
 * @see User 增 删 改 查  以及各类查询的实现
 *
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	/**
	  * @see 查询单个用户
	  * @return 用户
	  */
	public User selectOne(String hql){
		User user = null;
		if(super.getHibernateTemplate().find(hql).size()>0){
			user = (User) super.getHibernateTemplate().find(hql).get(0);
		}
		return user;
	}

	@Override
	/**
	  * @see 添加用户
	  * @return 是否成功添加
	  */
	public boolean adduser(User user) {
		try {
			super.getHibernateTemplate().save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	/**
	  * @see 删除用户
	  * @return User
	  */
	public boolean deluser(User user) {
		//删除语句
		try {
			super.getHibernateTemplate().delete(user);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	  * @see 修改用户信息
	  * @return 
	  */
	@Override
	public boolean updateUser(User user ) {
		try {
			//更新代码
			super.getHibernateTemplate().update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	/**
	 * @see 查询用户总数
	 */
	@Override
	public int selectuserNum(String hql) {
		return super.getHibernateTemplate().find(hql).size();
	}

	/**
	 * @see 分页查询用户
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> selectUser(String hql, int pageNow, int pageSize) {
		List<User> list=null;//初始化活动list
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
}
