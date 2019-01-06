package cn.com.tj.byhy.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.tj.byhy.model.User;
import cn.com.tj.byhy.model.User_type;
import cn.com.tj.byhy.service.UserService;
import cn.com.tj.byhy.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 2018/12/30
 * @author wubeibei
 * @see User增删改查、登陆
 */
@Scope("request")
@Controller("userAction")
public class UserAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	/*---------------------------对象-------------------------------*/

	private User oneuser=null;//用户
	private List<User> userList = null;//所有用户
	private String errowMesg;//错误信息提示
	private int pageNow=1;//当前页
	private int pageSize=8;//每页显示数据条数
	private String key;//查询关键字
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	private User adminuser=null;//用户
	

	/*
	 * 封装User对象
	 */
	public User user() {
		User_type ty = new User_type();
		if (oneuser.getType().getId() == 1) {
			ty.setId(1);
			ty.setName("超级管理员");
		} else if (oneuser.getType().getId() == 2) {
			ty.setId(2);
			ty.setName("管理员");
		} else if (oneuser.getType().getId() == 3) {
			ty.setId(3);
			ty.setName("会员");
		} else if (oneuser.getType().getId() == 4) {
			ty.setId(4);
			ty.setName("普通用户");
		}
		oneuser.setType(ty);
		System.out.println(oneuser.getUsername());
		return oneuser;
	}


	/*---------------------------方法-------------------------------*/
	/**
	 * 用户登陆,管理员和超级管理员登陆后跳转到后台，普通用户和会员跳转到前台
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		System.out.println("login!");
		System.out.println("Username!"+oneuser.getUsername());
		System.out.println("msg1!");
		User usertemp1 = userService.getUserByName(oneuser.getUsername());
		System.out.println("login!user.getUsername():"+oneuser.getUsername());
		if(usertemp1!=null){
			if(usertemp1.getState()==1){
				errowMesg="此用户已无效！";
				return "loginF";
			}
			User usertemp = userService.isLogin(oneuser.getUsername(), oneuser.getPassword());
			// 验证用户名和密码正确的时候返回用户实例对象
			if(usertemp==null){
				errowMesg="密码错误！";
				return "loginF";
			}else{
				HttpServletRequest request=ServletActionContext.getRequest();
				HttpSession session=request.getSession();
				session.setAttribute("userLogin", usertemp);
				// 登录成功时将用户信息存储在session中
				oneuser = usertemp;
				if(oneuser.getType().getId()>2&&oneuser.getType().getId()<5){
					return "namaluserloginS";

				}else{
					return "loginS";//登陆成功
				}
			}
		}else{
			errowMesg="用户不存在！";
			return "loginF";
		}
	}

	/**
	 * @see 获取登录用户信息，返回添加用户页面
	 * 
	 */
	public String toadduser () throws Exception {
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		return "toadduser";
	}

	/**
	 * 管理员添加用户
	 * @return
	 * @throws Exception
	 */
	public String adduser() throws Exception{
		System.out.println("adduser添加用户");
		oneuser=user();
		System.out.println("usertype:"+oneuser.getType().getName());
		System.out.println("username:"+oneuser.getUsername());
		System.out.println(",userRname:"+oneuser.getRealName());
		System.out.println(",userpwd:"+oneuser.getPassword());
		System.out.println(",userphone:"+oneuser.getPhone());
		System.out.println(",usermail:"+oneuser.getMail());
		System.out.println(",usertypeid:"+oneuser.getType().getId());
		if(userService.adduser(oneuser)){
			errowMesg = "添加成功";
		} else {
			errowMesg = "添加失败";//将错误信息传入errowMesg
		}
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		return "alluserbypage";//添加成功，返回用户管理界面
	}
	/**
	 * 注册用户,只能从用户前台注册普通用户
	 * @return
	 * @throws Exception
	 */
	public String register() throws Exception{
		System.out.println("register添加用户");
		User_type type = new User_type();
		type.setId(4);
		type.setName("普通用户");
		oneuser.setType(type);
		System.out.println("register用户ming:"+oneuser.getUsername()+oneuser.getPassword()+"typeid:"+oneuser.getType().getId());
		if(userService.adduser(oneuser)){
			errowMesg = "添加成功";
		} else {
			errowMesg = "添加失败";//将错误信息传入errowMesg
		}
		return "user_index";//添加成功，返回用户管理界面
	}

	//	/**
	//	 * @see 验证用户名是否存在
	//	 * @return
	//	 * @throws Exception
	//	 */
	//	public String checkuser() throws Exception{
	//		System.out.println("checkuser添加用户");
	//		if(userService.getUserByName(user.getUsername())==null){
	//			errowMesg = "用户名可用";
	//		} else {
	//			errowMesg = "用户名已存在";//将错误信息传入errowMesg
	//		}
	//		return "usernamecheck";//返回注册界面
	//	}


	/**
	 * 删除用户
	 * @return
	 * @throws Exception
	 */
	public String deleteUser() throws Exception{
		if (userService.deluser(oneuser.getId())) {//根据id删除用户
			errowMesg = "delete success!";
		} else {
			errowMesg = "delete fail!";
		}
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		return "alluserbypage";
	}

	/**
	 * 禁用或者恢复用户
	 * @return
	 * @throws Exception
	 */
	public String disableUser() throws Exception{
		if (userService.disableuser(oneuser)) {//禁用或者恢复用户
			errowMesg = "disable/restore success!";
		} else {
			errowMesg = "disable/restore fail!";
		}
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		return "alluserbypage";
	}

	/**
	 * @see 根据用户名获取用户信息，返回登录后台主页面
	 * 
	 */
	public String getuser () throws Exception {
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		return "adminshow";
	}
	/**
	 * @see 根据用户名获取用户信息,返回用户信息展示页面
	 * 
	 */
	public String usermassage() throws Exception {
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		return "usermassage";

	}

	/**
	 * @see 跳转到修改用户信息界面
	 * 
	 */
	public String usermassagemodify() throws Exception {
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		return "usermassagemodify";
	}
	/**
	 * @see admin跳转到修改用户信息界面 更新用户信息
	 * 
	 */
	public String toadmin_usermsgmfy() throws Exception {
		oneuser = userService.selectOne(oneuser.getId());
		adminuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		return "adminusermassagemodify";
	}
	/**
	 * @see admin跳转到修改用户信息界面 更新用户信息
	 * 
	 */
	public String toadminuserroot() throws Exception {
		oneuser = userService.selectOne(oneuser.getId());
		adminuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		System.out.println("adminuser:"+adminuser.getUsername());
		System.out.println("adminusertype:"+adminuser.getType().getName());
		return "adminroot";
	}
	/**
	 * @see 跳转到修改密码界面
	 * 
	 */
	public String userpwdmodify() throws Exception {
		System.out.println("跳转到修改密码界面");
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		return "userpwdmodify";
	}

	/**
	 * @see 修改用户信息，然后回到用户信息界面
	 * 
	 */
	public String domodify() throws Exception {
		System.out.println("domodify!");
		User usertemp = null;
		System.out.println("domodify!user===username="+oneuser.getUsername()+"userphone="+oneuser.getPhone());
		usertemp =  (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		System.out.println("domodify!username="+usertemp.getUsername()+"userid="+usertemp.getId());
		usertemp.setRealName(oneuser.getRealName());
		usertemp.setPhone(oneuser.getPhone());
		usertemp.setMail(oneuser.getMail());
		if(userService.updateUser(usertemp)){
			System.out.println("domodifyS!");
			oneuser = usertemp;
			errowMesg="用户信息修改成功！";
			return "domodifyS";
		}else{
			errowMesg="用户信息修改失败！";
			return "domodifyF";
		}

	}
	/**
	 * @see 管理员修改用户信息，然后回到用户信息界面
	 * 
	 */
	public String admindomodify() throws Exception {
		System.out.println("admindomodify!");
		User usertemp = null;
		System.out.println("admindomodify!user===username="+oneuser.getUsername()+"userphone="+oneuser.getPhone());
		usertemp =  userService.getUserByName(oneuser.getUsername());
		System.out.println("domodify!username="+usertemp.getUsername()+"userid="+usertemp.getId());
		usertemp.setRealName(oneuser.getRealName());
		usertemp.setPhone(oneuser.getPhone());
		usertemp.setMail(oneuser.getMail());
		if(userService.updateUser(usertemp)){
			System.out.println("domodifyS!");
			oneuser = usertemp;
			errowMesg="管理员更新用户信息成功！";
			return "adminusermassageS";
		}else{
			errowMesg="管理员更新用户信息失败！";
			return "domodifyF";
		}

	}
	/**
	 * @see 管理员修改用户权限，然后回到用户信息界面
	 * 
	 */
	public String adminroot() throws Exception {
		System.out.println("adminrootmodify!");
		User usertemp = null;
		System.out.println("adminrootmodify!user===username="+oneuser.getUsername()+"userphone="+oneuser.getPhone());
		usertemp =  userService.getUserByName(oneuser.getUsername());
		System.out.println("domodify!username="+usertemp.getUsername()+"userid="+usertemp.getId());
		usertemp.setType(user().getType());
		if(userService.updateUser(usertemp)){
			System.out.println("adminrootS!");
			oneuser = usertemp;
			adminuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
			errowMesg="管理员更新用户权限成功！";
			return "adminrootS";
		}else{
			errowMesg="管理员更新用户权限失败！";
			return "domodifyF";
		}
	}
	/**
	 * @see 修改用户密码，然后回到用户信息界面
	 * 
	 */
	public String userpswmodify() throws Exception {
		System.out.println("userpswmodify!");
		User usertemp = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		String oldpwd = errowMesg;
		errowMesg = "";
		System.out.println("oldpwd="+oldpwd);
		if(usertemp.getPassword().equals(oldpwd)){
			if(oneuser.getPassword().equals(oldpwd)){
				oneuser = usertemp;
				errowMesg="新密码与原密码相同！";
				return "userpswmodifyS";//回到用户信息界面
			}else{
				usertemp.setPassword(oneuser.getPassword());
				oneuser = usertemp;
				if(userService.updateUser(usertemp)){
					System.out.println("userpswmodifyS!");
					errowMesg="用户密码修改成功！";
					return "userpswmodifyS";//回到用户信息界面
				}else{
					oneuser.setPassword(oldpwd);
					errowMesg="用户密码修改失败！请检查重试";
					return "userpswmodifyF";//返回密码修改界面
				}
			}
		}else{
			oneuser = usertemp;
			System.out.println("userpswmodify!");
			errowMesg="原始密码输入错误，用户密码修改失败！";
			return "userpswmodifyF";//返回密码修改界面
		}
	}

	/**
	 * @see 用户密码重置为“111111”
	 * 
	 */
	public String resetpassword() throws Exception {
		System.out.println("resetpassword!");
		oneuser = userService.selectOne(oneuser.getId());
		oneuser.setPassword("111111");
		if(userService.updateUser(oneuser)){
			errowMesg="reset password success！";
		}else{
			errowMesg="reset password fail！";
		}
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		return "alluserbypage";
	}


	/**
	 * @see 查找所有用户信息，并返回显示页面
	 * @return
	 * @throws Exception
	 */
//	public String allUser() throws Exception {
//		System.out.println("allUser!");
//		try {
//			userList=userService.selectAlluser();
//			if(userList==null){
//				errowMesg="没有用户！";
//			}else{
//			}
//			return "allUserS";
//		} catch (Exception e) {
//			System.out.println("userList!errow");
//			e.printStackTrace();
//			errowMesg="查询所有用户失败！";
//			return "allUserF";
//		}
//	}
	/**
	 * 分页选取所有的用户
	 * @return selectSX
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String allUserByPage() throws Exception{
		System.out.println("allactByPage");
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		@SuppressWarnings("rawtypes")
		Map request = (Map)ActionContext.getContext().get("request");
		Pager page = new Pager(getPageNow(), userService.allnum());//查询所有数目
		request.put("page",page);//把page放入request
		userList = userService.allUserByPage(pageNow, pageSize);//查询list
		System.out.println("alluserByPage+"+userList.size());
		return "allUserByPageSX";//返回
	}

	/**
	 * 分页选取关键字查询的用户
	 * @return keysearchSX
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String keysearch() throws Exception{
		System.out.println("keysearchByPage");
		System.out.println("key:"+key);
		System.out.println("activityService.selectnum(key):"+userService.selectnum(key));
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		@SuppressWarnings("rawtypes")
		Map request = (Map)ActionContext.getContext().get("request");
		Pager page = new Pager(getPageNow(), userService.selectnum(key));//查询所有数目
		request.put("page",page);//把page放入request
		userList = userService.selectUserByPage(key, pageNow, pageSize);//查询list
		System.out.println("keysearchByPage："+userList.size());
		return "keysearchSX";//返回
	}

	/**
	 * 用户注销
	 * @return
	 * @throws Exception
	 */
	public String logout(){
		System.out.println("user_logout");
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		session.invalidate();
		System.out.println("user_logoutSX");
		return "logoutSX";	
	}
	/**
	 * 
	 * 生成验证码图片
	 */
	public void validatenumber() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		// 首先设置浏览器不缓存
		response.setHeader("cache-control", "no-cache");
		response.setHeader("Expire", "-1"); // >0为缓存
		response.setHeader("pragma", "no-cache");
		// 存储正确的验证码
		String vali_code = "";
		String Vali_str = "abcdefghijklmnopqrstuvwsyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Color[] colors = { Color.BLACK, Color.RED, Color.BLUE };
		// 1.创建图像
		int width = 120, height = 30;
		int x_offset = 5, y_offset = 20;
		BufferedImage img = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);
		// 2.创建画布
		Graphics2D g = img.createGraphics();
		// 3.为画布添加背景颜色为亮灰色
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);// fillRect是包括矩形框内部
		// 4.为画布添加边框
		g.setColor(Color.RED);
		g.drawRect(0, 0, width - 2, height - 2);// drawRect是绘制矩形边框
		// 5.绘制干扰线
		for (int i = 0; i < 10; i++) {
			g.setColor(new Color(randNum(0, 255), randNum(0, 255), randNum(0,
					255)));
			g.drawLine(randNum(0, width), randNum(0, height),
					randNum(0, width), randNum(0, height));
		}
		g.setFont(new Font("黑体", Font.BOLD, 20));//设置字体格式及大小
		for (int i = 0; i < 4; i++) {
			int r = randNum(-180, 180);
			String code = Vali_str.charAt(randNum(0, Vali_str.length() - 1))
					+ "";
			g.rotate(r / 180 * Math.PI);
			g.setColor(colors[randNum(0, colors.length)]);
			g.drawString(code, x_offset + i * 30, y_offset);
			g.rotate(-r / 180 * Math.PI);
			vali_code += code;
		}
		// 将验证码的内容存储在HttpSession中
		HttpSession session = request.getSession();
		session.setAttribute("Valicode", vali_code);
		// 通过response的输出流打给浏览器
		try {
			ImageIO.write(img, "jpg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return "text";//在登录页面显示验证码图片
	}

	// 单列模式
	private Random random = new Random();

	// 随机生成begin和end之间的一个int值
	private int randNum(int begin, int end) {
		return random.nextInt(end - begin) + begin;
	}

	//		/**
	//		 * 检测验证码的正确性
	//		 * @return
	//		 * @throws UnsupportedEncodingException
	//		 * @throws IOException
	//		 */
	//		public boolean checkvalidate() throws UnsupportedEncodingException,
	//				IOException {
	//			HttpServletResponse response = ServletActionContext.getResponse();
	//			HttpServletRequest request = ServletActionContext.getRequest();
	//			String vali_sys = request.getSession().getAttribute("Valicode")
	//					.toString();
	//			// 需要注意的是Parameter-key是input的name
	//			String vali_user = ValiImage;
	//			System.out.println(vali_user);
	//			System.out.println(vali_sys);
	//			if (vali_user.equalsIgnoreCase(vali_sys)) {
	//				return true;
	//				// 验证成功，登录到loginsuccess.html页面，可以使用下面三种方式
	//				// 1.验证码正确，请求重定向
	//				// response.setStatus(302);
	//				// response.setHeader("Location", "/TestPro/loginsuccess.html");
	//				// 2.请求重定向的第二种方式
	//				// response.sendRedirect("/TestPro/loginsuccess.html");
	//				// 3.使用Refresh跳转
	//				// response.setHeader("Refresh","3;url=/TestPro/loginsuccess.html");
	//			} else {
	//				// 验证码错误，提示验证失败
	//				System.out.println("错误");
	//			}
	//			return false;
	//		}



	/*---------------------------get、set方法-------------------------------*/

	public String getErrowMesg() {
		return errowMesg;
	}

	public void setErrowMesg(String errowMesg) {
		this.errowMesg = errowMesg;
	}
	public User getOneuser() {
		return oneuser;
	}
	public void setOneuser(User oneuser) {
		this.oneuser = oneuser;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}



	public int getPageNow() {
		return pageNow;
	}


	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public User getAdminuser() {
		return adminuser;
	}


	public void setAdminuser(User adminuser) {
		this.adminuser = adminuser;
	}

}
