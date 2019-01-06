package cn.com.tj.byhy.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.tj.byhy.model.Ordertable;

/**
 * 2018/12/30
 * @author wubeibei
 * @see OrdertableDao接口
 */
@Service
public interface OrdertableDao {
	
	List<Ordertable> findOrders(String hql);//查寻订单
	
	Ordertable selectOne(String hql);//查询单个订单

	boolean addOrder(Ordertable oneorder);//添加订单
	
	boolean updateOrder(Ordertable oneorder);//更新订单
	
	boolean delOrder(Ordertable oneorder);//删除订单
	
	int selectOrderNum(String hql);//查询订单总数

	List<Ordertable> selectOrdertable(String hql, int pageNow, int pageSize);//分页查询

}
