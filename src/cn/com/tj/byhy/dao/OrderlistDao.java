package cn.com.tj.byhy.dao;

import java.util.List;

import org.springframework.stereotype.Service;
import cn.com.tj.byhy.model.Orderlist;

/**
 * 2016/1/11
 * @author wubeibei
 * @see OrderDao接口
 */
@Service
public interface OrderlistDao {
	
	List<Orderlist> findOrderlists(String hql);//查寻订单单品列表
	
	Orderlist selectOne(String hql);//查询单个订单单品列表

	boolean addOrderlist(Orderlist orderlist);//添加订单单品列表
	
	boolean updateOrderlist(Orderlist orderlist);//更新订单单品列表
	
	boolean delOrderlist(Orderlist orderlist);//删除订单单品列表
	
	int selectOrderlistNum(String hql);//查询订单单品列表总数
}
