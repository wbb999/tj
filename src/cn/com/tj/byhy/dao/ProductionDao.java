package cn.com.tj.byhy.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.tj.byhy.model.Production;

/**
 * 2018/12/25
 * @see 作品dao层接口
 * @author wubeibei
 *
 */
@Service
public interface ProductionDao {
	/*-----------------------方法----------------------------*/
	public boolean add(Production production);//添加作品
	
	public boolean del(Production production);//删除作品
	
	public boolean update(Production production);//更新作品
	
	public Production getone(Production production);//查找单个作品

	public List<Production> selectProduction(String hql,int pageNow, int pageSize);//分页查询作品
	
	public int countProduction(String hql);//计算查询的作品条数

	
}
