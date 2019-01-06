package cn.com.tj.byhy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.tj.byhy.dao.TrolleyDao;
import cn.com.tj.byhy.model.Trolley;
import cn.com.tj.byhy.service.TrolleyService;

/**
 * 2018/12/25
 * @see 作品service实现
 * @author wubeibei
 *
 */
@Scope("singleton")
@Controller("trolleyServiceImpl")
public class TrolleyServiceImpl implements TrolleyService {
	/*-----------------------对象-----------------------------*/
	@Autowired
	private TrolleyDao trolleyDao;//
	/*-----------------------方法-----------------------------*/
	/**
	 * @see 添加作品 
	 * @param trolley
	 * @return
	 */
	@Override
	public boolean add(Trolley trolley) {
		if(trolleyDao.add(trolley)){//如果添加成功返回true，否则返回false
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @see 删除作品
	 * @param trolley
	 * @return
	 */
	@Override
	public boolean delete(Trolley trolley) {
		Trolley act = singleTrolley(trolley);//取出要查询的作品
		if(trolleyDao.del(act)){//删除该作品
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @see 更新作品
	 */
	@Override
	public boolean update(Trolley trolley) {
		if(trolleyDao.update(trolley)){
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
		String hql = "FROM Trolley";
		return trolleyDao.countTrolley(hql);
	}
	/**
	 * @see 按关键字条件查询作品总条数
	 */
	@Override
	public int selectnum(String key) {
		String hql="FROM Trolley a where a.name like '%"+key+"%' or a.address like '%"+key+"%'  or a.detail like '%"+key+"%')";
		return trolleyDao.countTrolley(hql);
	}
	
	/**
	 * @see 查询所有作品 
	 * @return
	 */
	@Override
	public List<Trolley> allTrolley(){
		String hql = "FROM Trolley a ORDER BY a.id DESC";
		return trolleyDao.selectTrolley(hql,0,0);//0,0不分页
	}
	/**
	 * @see 单个查询  
	 * @param trolley
	 * @return
	 */
	@Override
	public Trolley singleTrolley(Trolley trolley) {
		return trolleyDao.getone(trolley);
	}
	/**
	 * @see 分页获取所有作品
	 * @param trolley
	 * @return
	 */
	@Override
	public List<Trolley> allTrolleyByPage(int pageNow, int pageSize){
		String hql="FROM Trolley a ORDER BY a.id DESC";
		return trolleyDao.selectTrolley(hql, pageNow, pageSize);
	};

	/**
	 * @see 分页获取按关键字查询的作品
	 * @param trolley
	 * @return
	 */
	@Override
	public List<Trolley> selectTrolleyByPage(String key,int pageNow, int pageSize) {
		String hql="FROM Trolley a where a.name like '%"+key+"%' or a.address like '%"+key+"%'  or a.detail like '%"+key+"%')";
		return trolleyDao.selectTrolley(hql, pageNow, pageSize);
	}
	
	/**
	 * @see 查看购物车中有没有用户已经添加了产品的记录，如果有就返回一条购物车记录
	 * @param trolley
	 * @return
	 */
	@Override
	public Trolley check(int userid, int proid) {
		String hql="FROM Trolley a where a.user = "+userid +" and a.service_product = "+proid ;
		return trolleyDao.selectone(hql);
	}
	@Override
	public List<Trolley> selectTrolleyByUserId(int id) {
		String hql = "FROM Trolley a where a.amount>0 and a.user = "+id + " ORDER BY a.id DESC";
		return trolleyDao.selectTrolley(hql,0,0);//0,0不分页
	}
}
