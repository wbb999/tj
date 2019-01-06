package cn.com.tj.byhy.service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import cn.com.tj.byhy.model.Nomalquestion;
@Scope("singleton")
@Service("nomalquestionService")
/**
 * 2018/12/25
 * @author wubeibei
 * @see Nomalquestionservice服务层接口
 */
public interface NomalquestionService {
	
public boolean add(Nomalquestion question);//添加常见问题
	
	public boolean delete(Nomalquestion question);//删除常见问题
	
	public boolean update(Nomalquestion question);//更新常见问题
	
	public int allnum();//查询常见问题总条数
	
	public int selectnum(String key);//按条件查询常见问题总条数
	
	public List<Nomalquestion> allNomalquestion();//获取所有常见问题
	
	public Nomalquestion singleNomalquestion(Nomalquestion question);//获取单个常见问题信息
	
	public List<Nomalquestion> allNomalquestionByPage(int pageNow, int pageSize);//分页获取所有常见问题
	
	public List<Nomalquestion> selectNomalquestionByPage(String key,int pageNow, int pageSize);//分页查询常见问题
}
