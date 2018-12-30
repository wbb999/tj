package cn.com.tj.byhy.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.tj.byhy.model.Trolley;

/**
 * 2016/4/4
 * @see 购物车dao层接口
 * @author wubeibei
 *
 */
@Service
public interface TrolleyDao {
	/*-----------------------方法----------------------------*/
	public boolean add(Trolley trolley);//添加购物车
	
	public boolean del(Trolley trolley);//删除购物车
	
	public boolean update(Trolley trolley);//更新购物车
	
	public Trolley getone(Trolley trolley);//查找单个购物车

	public List<Trolley> selectTrolley(String hql,int pageNow, int pageSize);//分页查询购物车
	
	public int countTrolley(String hql);//计算查询的购物车条数

	public Trolley selectone(String hql);//查找购物车

	
}
