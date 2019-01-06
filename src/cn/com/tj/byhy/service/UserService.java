package cn.com.tj.byhy.service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.com.tj.byhy.model.User;

@Scope("singleton")
@Service("userService")
/**
 * 2018/12/24
 * @author wubeibei
 * @see service服务层接口
 */
public interface UserService {
	
	public User isLogin(String username, String password);//用户登录

	public boolean adduser(User user);//添加用户
	
	public boolean updateUser(User user);//更新用户
	
	public boolean deluser(int id);//根据id删除用户
	
	public boolean disableuser(User user);//禁用用户 或者恢复权限
	
	public List<User> selectAlluser();//查询所有用户
	
	public int userNum();//查询用户数目
	
	public User getUserByName(String username);//根据用户名查询用户
	
	public User selectOne(int id);//根据id查询单个用户

	public List<User> selectUserByPage(String key, int pageNow, int pageSize);//分页查询用户

	public int selectnum(String key);//按条件查询用户总条数

	public int allnum();//查询用户总条数

	public List<User> allUserByPage(int pageNow, int pageSize);//分页获取所有用户

}
