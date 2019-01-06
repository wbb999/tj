package cn.com.tj.byhy.service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.com.tj.byhy.model.Ordertable;

@Scope("singleton")
@Service("ordertableService")
/**
 * 2018/12/30
 * @author wubeibei
 * @see service服务层接口
 */
public interface OrdertableService {
	
	public boolean addOrder(Ordertable oneorder);//添加订单
	
	public boolean delOrder(int id);//根据id删除订单
	
	public boolean updateOrder(Ordertable oneorder);//更新订单
	
	public List<Ordertable> selectAllOrder();//查询所有订单
	
	public int OrderNum();//查询订单数目
	
	public Ordertable selectOne(int id);//根据id查询单个订单

	public List<Ordertable> allordertableByPage(int pageNow, int pageSize);//分页查询所有订单

	public int selectnum(String key);//关键字查询订单数目

	public List<Ordertable> selectordertableByPage(String key, int pageNow,
			int pageSize);//分页，关键字查询所有订单

	public Ordertable getlastone();//获取最后一个添加的订单

	public List<Ordertable> selectordertableByUserId(int id);//根据用户id查询出所有的订单

	public List<Ordertable> selectordertableByOrderId(int id);//根据订单id查找订单

	public int selectnumByState(String key);//根据订单状态查找订单数

	public List<Ordertable> selectordertableByStateByPage(String key,
			int pageNow, int pageSize);//根据订单状态查找订单

	public int selectnumByUserId(int id);//根据订单用户查找订单数

	public List<Ordertable> selectordertableByUserIdByPage(int id,
			int pageNow, int pageSize);//根据订单用户查找订单
}
