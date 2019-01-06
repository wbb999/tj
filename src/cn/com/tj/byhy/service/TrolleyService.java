package cn.com.tj.byhy.service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.tj.byhy.model.Trolley;

/**
 * 2018/12/25
 * @see 购物车service接口
 * @author wubeibei
 *
 */
@Scope("singleton")
@Controller("trolleyService")
public interface TrolleyService {
	/*-----------------------方法----------------------------*/
	public boolean add(Trolley trolley);//添加购物车
	
	public boolean delete(Trolley trolley);//删除购物车
	
	public boolean update(Trolley trolley);//更新购物车
	
	public int allnum();//查询购物车总条数
	
	public int selectnum(String key);//按条件查询购物车总条数
	
	public List<Trolley> allTrolley();//获取所有购物车
	
	public Trolley singleTrolley(Trolley trolley);//获取单个购物车信息
	
	public List<Trolley> allTrolleyByPage(int pageNow, int pageSize);//分页获取所有购物车
	
	public List<Trolley> selectTrolleyByPage(String key,int pageNow, int pageSize);//分页查询购物车

	
	
	public Trolley check(int userid, int proid);//查看购物车中有没有用户已经添加了产品的记录，如果有就返回一条购物车记录

	public List<Trolley> selectTrolleyByUserId(int id);//查询出一个用户购物车中所有的产品
	
}