package cn.com.tj.byhy.service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.tj.byhy.model.Production;

/**
 * 2018/12/25
 * @see 作品service接口
 * @author wubeibei
 *
 */
@Scope("singleton")
@Controller("productionService")
public interface ProductionService {
	/*-----------------------方法----------------------------*/
	public boolean add(Production production);//添加作品
	
	public boolean delete(Production production);//删除作品
	
	public boolean update(Production production);//更新作品
	
	public int allnum();//查询作品总条数
	
	public int selectnum(String key);//按条件查询作品总条数
	
	public List<Production> allProduction();//获取所有作品
	
	public Production singleProduction(Production production);//获取单个作品信息
	
	public List<Production> allProductionByPage(int pageNow, int pageSize);//分页获取所有作品
	
	public List<Production> selectProductionByPage(String key,int pageNow, int pageSize);//分页查询作品
	
}