package cn.com.tj.byhy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.com.tj.byhy.dao.NomalquestionDao;
import cn.com.tj.byhy.model.Nomalquestion;
import cn.com.tj.byhy.service.NomalquestionService;

/**
 * 2018/12/25
 * @author wubeibei
 * @see  Nomalquestion服务处理层 ，对获取的信息处理分配后调用相应的dao层实现类
 */
@Scope("singleton")
@Service("nomalquestionServiceImpl")
public class NomalquestionServiceImpl implements NomalquestionService {
	@Autowired
	private NomalquestionDao nomalquestionDao;//自动注入
	/*-----------------------方法-----------------------------*/
	/**
	 * @see 添加活动 
	 * @param Nomalquestion
	 * @return
	 */
	@Override
	public boolean add(Nomalquestion question) {
		if(nomalquestionDao.add(question)){//如果添加成功返回true，否则返回false
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @see 删除活动
	 * @param Nomalquestion
	 * @return
	 */
	@Override
	public boolean delete(Nomalquestion question) {
		Nomalquestion que = singleNomalquestion(question);//取出要查询的活动
		if(nomalquestionDao.del(que)){//删除该活动
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @see 更新活动
	 */
	@Override
	public boolean update(Nomalquestion question) {
		if(nomalquestionDao.update(question)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @see 查询活动总条数
	 */
	@Override
	public int allnum() {
		String hql = "FROM Nomalquestion";
		return nomalquestionDao.countNomalquestion(hql);
	}
	/**
	 * @see 按关键字条件查询活动总条数
	 */
	@Override
	public int selectnum(String key) {
		String hql="FROM Nomalquestion a where a.question like '%"+key+"%' or a.answer like '%"+key+"%'";
		return nomalquestionDao.countNomalquestion(hql);
	}
	
	/**
	 * @see 查询所有活动 
	 * @return
	 */
	@Override
	public List<Nomalquestion> allNomalquestion(){
		String hql = "FROM Nomalquestion a ORDER BY a.id DESC";
		return nomalquestionDao.selectNomalquestion(hql,0,0);//0,0不分页
	}
	/**
	 * @see 单个查询  
	 * @param Nomalquestion
	 * @return
	 */
	@Override
	public Nomalquestion singleNomalquestion(Nomalquestion question) {
		return nomalquestionDao.getone(question);
	}
	/**
	 * @see 分页获取所有活动
	 * @param Nomalquestion
	 * @return
	 */
	@Override
	public List<Nomalquestion> allNomalquestionByPage(int pageNow, int pageSize){
		String hql="FROM Nomalquestion a ORDER BY a.account DESC";
		return nomalquestionDao.selectNomalquestion(hql, pageNow, pageSize);
	};

	/**
	 * @see 分页获取按关键字查询的活动
	 * @param Nomalquestion
	 * @return
	 */
	@Override
	public List<Nomalquestion> selectNomalquestionByPage(String key,int pageNow, int pageSize) {
		String hql="FROM Nomalquestion a where a.question like '%"+key+"%' or a.answer like '%"+key+"%'";
		return nomalquestionDao.selectNomalquestion(hql, pageNow, pageSize);
	}

	
}
