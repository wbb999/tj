package cn.com.tj.byhy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.com.tj.byhy.dao.OrdertableDao;
import cn.com.tj.byhy.model.Ordertable;
import cn.com.tj.byhy.service.OrdertableService;

/**
 * 2016/4/30
 * @author wubeibei
 * @see  所有对订单Order处理的服务处理层 ，对获取的信息处理分配后调用相应的dao层实现类
 */
@Scope("singleton")
@Service("ordertableServiceImpl")
public class OrdertableServiceImpl implements OrdertableService {
	@Autowired
	private OrdertableDao ordertableDao;//自动注入ordertableDao

	@Override
	/**
	 * @see 订单添加
	 */
	public boolean addOrder(Ordertable oneorder) {
		if (ordertableDao.addOrder(oneorder)) {
			return true;
		}
		return false;
	}
	@Override
	/**
	 * @see 删除订单
	 */
	public boolean delOrder(int id) {
		Ordertable order=selectOne(id);
		if(ordertableDao.delOrder(order)){
			return true;
		}
		return false;
	}

	@Override
	/**
	 * @see 查询订单数量
	 */
	public int OrderNum() {
		String hql="FROM Ordertable a where a.state < 7";
		return ordertableDao.selectOrderNum(hql);
	}

	@Override
	/**
	 * @see 查询所有订单
	 */
	public List<Ordertable> selectAllOrder() {
		String hql="FROM Ordertable a where a.state < 7 ORDER BY a.id DESC";
		return ordertableDao.findOrders(hql);
	}

	@Override
	/**
	 * @see 根据id查询订单信息
	 */
	public Ordertable selectOne(int id) {
		String hql="FROM Ordertable a where a.id = "+ id +" and a.state < 7" ;
		return	ordertableDao.selectOne(hql);
	}

	@Override
	/**
	 * @see 修改订单信息
	 */
	public boolean updateOrder(Ordertable oneorder) {
		if (ordertableDao.updateOrder(oneorder)) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * @see 分页查询所有订单
	 */
	@Override
	public List<Ordertable> allordertableByPage(int pageNow, int pageSize) {
		String hql="FROM Ordertable a where a.state < 7 ORDER BY a.id DESC";
		return ordertableDao.selectOrdertable(hql, pageNow, pageSize);
	}
	/**
	 * @see 关键字查询订单数目
	 */
	@Override
	public int selectnum(String key) {
		String hql="FROM Ordertable a where a.state < 7 and a.id in(SELECT id FROM Service_Product s where s.intro like '%"+key+"%' or s.name like '%"+key+"%' )";
		return ordertableDao.selectOrderNum(hql);
	}
	/**
	 * @see 分页，关键字查询所有订单
	 */
	@Override
	public List<Ordertable> selectordertableByPage(String key, int pageNow,
			int pageSize) {
		String hql="FROM Ordertable a where a.state < 7 and a.id in(SELECT order from Orderlist o where o.service_product in (SELECT id FROM Service_Product s where s.intro like '%"+key+"%' or s.name like '%"+key+"%') )ORDER BY a.id DESC";
		return ordertableDao.selectOrdertable(hql, pageNow, pageSize);
	}
	@Override
	public Ordertable getlastone() {
		String hql="FROM Ordertable a where a.state < 7 ORDER BY a.id DESC";
		return	ordertableDao.selectOne(hql);
	}
	
	
	
	@Override
	public List<Ordertable> selectordertableByUserId(int id) {
		String hql="FROM Ordertable a where a.state < 7 and a.user = "+ id +" ORDER BY a.id DESC";
		return ordertableDao.selectOrdertable(hql, 0, 0);
	}
	/*
	 * @see 前台查询出用户要找的对应id的订单
	 * (non-Javadoc)
	 * @see cn.com.tj.byhy.service.OrdertableService#selectordertableByOrderId(int)
	 */
	@Override
	public List<Ordertable> selectordertableByOrderId(int id) {
		String hql="FROM Ordertable a where a.state < 7 and a.id="+id+"ORDER BY a.id DESC";
		return ordertableDao.selectOrdertable(hql, 0, 0);
	}
	/*
	 * (non-Javadoc)
	 * @see cn.com.tj.byhy.service.OrdertableService#selectnumByState(java.lang.String)
	 */
	@Override
	public int selectnumByState(String key) {
		String hql="FROM Ordertable a where a.state < 7 and a.state in(SELECT id from Order_state s where s.name like '%"+key+"%')";
		return ordertableDao.selectOrderNum(hql);
	}
	@Override
	public List<Ordertable> selectordertableByStateByPage(String key,
			int pageNow, int pageSize) {
		String hql="FROM Ordertable a where a.state < 7 and a.state in(SELECT id from Order_state s where s.name like '%"+key+"%') ORDER BY a.id DESC";
		return ordertableDao.selectOrdertable(hql, pageNow, pageSize);
	}
	@Override
	public int selectnumByUserId(int id) {
		String hql="FROM Ordertable a where a.state < 7 and a.user = "+ id +" ORDER BY a.id DESC";
		return ordertableDao.selectOrderNum(hql);
	}
	@Override
	public List<Ordertable> selectordertableByUserIdByPage(int id,
			int pageNow, int pageSize) {
		String hql="FROM Ordertable a where a.state < 7 and a.user = "+ id +" ORDER BY a.id DESC";
		return ordertableDao.selectOrdertable(hql, pageNow, pageSize);
	}
}
