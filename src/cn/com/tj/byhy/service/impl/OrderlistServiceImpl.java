package cn.com.tj.byhy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.com.tj.byhy.dao.OrderlistDao;
import cn.com.tj.byhy.model.Orderlist;
import cn.com.tj.byhy.service.OrderlistService;
/**
 * 2018/12/30
 * @author wubeibei
 * @see  所有对用户Orderlist处理的服务处理层 ，对获取的信息处理分配后调用相应的dao层实现类
 */
@Scope("singleton")
@Service("orderlistServiceImpl")
public class OrderlistServiceImpl implements OrderlistService {

	@Autowired
	private OrderlistDao orderlistDao;//自动注入useDao


	@Override
	/**
	 * @see 用户添加
	 */
	public boolean addOrderlist(Orderlist orderlist) {
		if (orderlistDao.addOrderlist(orderlist)) {
			return true;
		}
		return false;
	}

	/**
	 * @see 删除用户
	 */
	public boolean delOrderlist(int id) {
		Orderlist orderlist=selectOne(id);
		if(orderlistDao.delOrderlist(orderlist)){
			return true;
		}
		return false;
	}

	@Override
	/**
	 * @see 查询用户数量
	 */
	public int OrderlistNum() {
		String hql="FROM Orderlist";
		return orderlistDao.selectOrderlistNum(hql);
	}

	@Override
	/**
	 * @see 查询所有用户
	 */
	public List<Orderlist> selectAllOrderlist() {
		String hql="FROM Orderlist a ORDER BY a.id ASC";
		return orderlistDao.findOrderlists(hql);
	}

	@Override
	/**
	 * @see 根据id查询用户信息
	 */
	public Orderlist selectOne(int id) {
		String hql="FROM Orderlist a where a.id = "+ id ;
		return	orderlistDao.selectOne(hql);
	}

	@Override
	/**
	 * @see 修改用户信息
	 */
	public boolean updateOrderlist(Orderlist orderlist) {
		if (orderlistDao.updateOrderlist(orderlist)) {
			return true;
		}else {
			return false;
		}
	}

}
