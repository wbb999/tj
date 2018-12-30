package cn.com.tj.byhy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.tj.byhy.dao.ProductionDao;
import cn.com.tj.byhy.model.Production;
import cn.com.tj.byhy.service.ProductionService;

/**
 * 2016/4/4
 * @see 作品service实现
 * @author wubeibei
 *
 */
@Scope("singleton")
@Controller("productionServiceImpl")
public class ProductionServiceImpl implements ProductionService {
	/*-----------------------对象-----------------------------*/
	@Autowired
	private ProductionDao productionDao;//
	/*-----------------------方法-----------------------------*/
	/**
	 * @see 添加作品 
	 * @param activity
	 * @return
	 */
	@Override
	public boolean add(Production production) {
		if(productionDao.add(production)){//如果添加成功返回true，否则返回false
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @see 删除作品
	 * @param activity
	 * @return
	 */
	@Override
	public boolean delete(Production production) {
		Production act = singleProduction(production);//取出要查询的作品
		if(productionDao.del(act)){//删除该作品
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @see 更新作品
	 */
	@Override
	public boolean update(Production production) {
		if(productionDao.update(production)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @see 查询作品总条数
	 */
	@Override
	public int allnum() {
		String hql = "FROM Production";
		return productionDao.countProduction(hql);
	}
	/**
	 * @see 按关键字条件查询作品总条数
	 */
	@Override
	public int selectnum(String key) {
		String hql="FROM Production a where a.name like '%"+key+"%' or a.address like '%"+key+"%'  or a.detail like '%"+key+"%')";
		return productionDao.countProduction(hql);
	}
	
	/**
	 * @see 查询所有作品 
	 * @return
	 */
	@Override
	public List<Production> allProduction(){
		String hql = "FROM Production a ORDER BY a.id DESC";
		return productionDao.selectProduction(hql,0,0);//0,0不分页
	}
	/**
	 * @see 单个查询  
	 * @param activity
	 * @return
	 */
	@Override
	public Production singleProduction(Production production) {
		return productionDao.getone(production);
	}
	/**
	 * @see 分页获取所有作品
	 * @param activity
	 * @return
	 */
	@Override
	public List<Production> allProductionByPage(int pageNow, int pageSize){
		String hql="FROM Production a ORDER BY a.id DESC";
		return productionDao.selectProduction(hql, pageNow, pageSize);
	};

	/**
	 * @see 分页获取按关键字查询的作品
	 * @param activity
	 * @return
	 */
	@Override
	public List<Production> selectProductionByPage(String key,int pageNow, int pageSize) {
		String hql="FROM Production a where a.name like '%"+key+"%' or a.address like '%"+key+"%'  or a.detail like '%"+key+"%')";
		return productionDao.selectProduction(hql, pageNow, pageSize);
	}

	
}
