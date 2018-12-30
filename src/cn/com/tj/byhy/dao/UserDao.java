package cn.com.tj.byhy.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.tj.byhy.model.User;

/**
 * 2016/1/11
 * @author wubeibei
 * @see UserDao接口
 */
@Service
public interface UserDao {
	
	User selectOne(String hql);//查询单个用户

	boolean adduser(User user);//添加用户
	
	boolean updateUser(User user);//更新用户
	
	boolean deluser(User user);//删除用户
	
	int selectuserNum(String hql);//查询用户总数

	List<User> selectUser(String hql, int pageNow, int pageSize);//分页查询用户  pageSize=0代表不分页

}
