package cn.com.tj.byhy.service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.com.tj.byhy.model.Orderlist;

@Scope("singleton")
@Service("orderlistService")
/**
 * 2016/2/24
 * @author wubeibei
 * @see service服务层接口
 */
public interface OrderlistService {
	
	public boolean addOrderlist(Orderlist orderlist);//添加订单单品列表
	
	public boolean updateOrderlist(Orderlist orderlist);//更新订单单品列表
	
	public boolean delOrderlist(int id);//根据id删除订单单品列表
	
	public List<Orderlist> selectAllOrderlist();//查询所有订单单品列表
	
	public int OrderlistNum();//查询订单单品列表数目
	
	public Orderlist selectOne(int id);//根据id查询单个订单单品列表

}
