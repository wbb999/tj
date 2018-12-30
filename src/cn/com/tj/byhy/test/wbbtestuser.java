package cn.com.tj.byhy.test;

import cn.com.tj.byhy.model.User;

public class wbbtestuser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user=new User();
		System.out.println("msg1!");
		user.setUsername("wbb");
		System.out.println("msg2!");
		System.out.println("1+"+user.getUsername());
		System.out.println("msg3!");
	}
}
