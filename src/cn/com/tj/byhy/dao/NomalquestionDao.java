package cn.com.tj.byhy.dao;

import java.util.List;
import org.springframework.stereotype.Service;
import cn.com.tj.byhy.model.Nomalquestion;
/**
 * 2018/12/25
 * @author wubeibei
 * @see NomalquestionDao接口
 */
@Service
public interface NomalquestionDao {

	public boolean add(Nomalquestion question);//添加常见问题

	public boolean del(Nomalquestion question);//删除常见问题

	public boolean update(Nomalquestion question);//更新常见问题

	public Nomalquestion getone(Nomalquestion question);//查找单个常见问题

	public List<Nomalquestion> selectNomalquestion(String hql,int pageNow, int pageSize);//分页查询常见问题

	public int countNomalquestion(String hql);//计算查询的常见问题条数

}
