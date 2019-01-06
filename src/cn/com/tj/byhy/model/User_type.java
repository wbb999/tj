package cn.com.tj.byhy.model;

import java.util.Set;

public class User_type{
	/**
	 * 2019/1/2
	 * @author wubeibei
	 * @see 用户类型信息表
	 */
	private int id;//类型ID
	private String name;//类型名
	private Set<User> users;//用户类型对应的用户
	/****************************************************/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
