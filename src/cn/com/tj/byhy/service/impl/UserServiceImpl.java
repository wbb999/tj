package cn.com.tj.byhy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.com.tj.byhy.dao.UserDao;
import cn.com.tj.byhy.model.User;
import cn.com.tj.byhy.service.UserService;

/**
 * 2018/12/24
 * @author wubeibei
 * @see  所有对用户user处理的服务处理层 ，对获取的信息处理分配后调用相应的dao层实现类
 */
@Scope("singleton")
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;//自动注入useDao

	@Override
	/**
	 * @see 用户登录 
	 */
	public User isLogin(String username, String password) {
		//用户登陆
		String hql="FROM User a where a.username = '"+username+"' and a.password = '"+password+"'";
		User user = userDao.selectOne(hql);
		return user;
	}

	@Override
	/**
	 * @see 用户添加
	 */
	public boolean adduser(User user) {
		if (userDao.adduser(user)) {
			return true;
		}
		return false;
	}
	@Override
	/**
	 * @see 禁用用户,或者恢复用户权限
	 */
	public boolean disableuser(User user) {
		System.out.println("service:disableuser");
		user=selectOne(user.getId());
		if(user.getState()==0){
			user.setState(1);
		}else{
			user.setState(0);
		}
		if(userDao.updateUser(user)){
			return true;
		}
		return false;
	}

	/**
	 * @see 删除用户
	 */
	public boolean deluser(int id) {
		User user=selectOne(id);
		if(userDao.deluser(user)){
			return true;
		}
		return false;
	}

	@Override
	/**
	 * @see 查询用户数量
	 */
	public int userNum() {
		String hql="FROM User";
		return userDao.selectuserNum(hql);
	}

	@Override
	/**
	 * @see 查询所有用户
	 */
	public List<User> selectAlluser() {
		String hql="FROM User a ORDER BY a.type.id ASC ORDER BY a.id ASC";
		return userDao.selectUser(hql, 0, 0);
	}

	@Override
	/**
	 * @see 根据id查询用户信息
	 */
	public User selectOne(int id) {
		String hql="FROM User a where a.id = "+ id ;
		return	userDao.selectOne(hql);
	}

	@Override
	/**
	 * @see 修改用户信息
	 */
	public boolean updateUser(User user) {
		if (userDao.updateUser(user)) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * @see 根据用户名获取用户信息
	 */
	@Override
	public User getUserByName(String username) {
		String hql="FROM User a where a.username = '"+username+"'";
		return userDao.selectOne(hql);
	}

	/**
	 * @see 分页查询用户
	 */
	@Override
	public List<User> selectUserByPage(String key, int pageNow, int pageSize) {
		String hql="FROM User a where a.username like '%"+key+"%' or a.realName like '%"+key+"%'or a.type.name like '%"+key+"%'or a.phone like '%"+key+"%'or a.mail like '%"+key+"%' ORDER BY a.type.id ASC ORDER BY a.id ASC";
		return userDao.selectUser(hql, pageNow, pageSize);
	}

	/**
	 * @see 按条件查询用户总条数
	 */
	@Override
	public int selectnum(String key) {
		String hql="FROM User a where a.username like '%"+key+"%' or a.realName like '%"+key+"%'or a.type.name like '%"+key+"%'or a.phone like '%"+key+"%'or a.mail like '%"+key+"%' ";
		return userDao.selectuserNum(hql);
	}

	/**
	 * @see 查询用户总条数
	 */
	@Override
	public int allnum() {
		String hql = "FROM User a where a.type!=5";
		return userDao.selectuserNum(hql);
	}

	/**
	 * @see 分页获取所有用户
	 */
	@Override
	public List<User> allUserByPage(int pageNow, int pageSize) {
		String hql="FROM User a where a.type<5 ORDER BY a.type.id ASC ORDER BY a.id ASC";
		return userDao.selectUser(hql, pageNow, pageSize);
	}
}
