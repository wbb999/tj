package cn.com.tj.byhy.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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

import cn.com.tj.byhy.model.Order_state;
import cn.com.tj.byhy.model.Orderlist;
import cn.com.tj.byhy.model.Ordertable;
import cn.com.tj.byhy.model.Service_Product;
import cn.com.tj.byhy.model.Trolley;
import cn.com.tj.byhy.model.User;
import cn.com.tj.byhy.model.User_type;
import cn.com.tj.byhy.service.OrderlistService;
import cn.com.tj.byhy.service.OrdertableService;
import cn.com.tj.byhy.service.Service_ProductService;
import cn.com.tj.byhy.service.TrolleyService;
import cn.com.tj.byhy.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 2018/12/30
 * @author wubeibei
 * @see 前台用户的 操作 
 */
@Scope("request")
@Controller("usercustomAction")
public class UsercustomAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	/*---------------------------对象-------------------------------*/

	private User oneuser=null;//用户
	private List<User> userList = null;//所有用户
	private String[] proids;// 用于添加到订单表的产品id数列
	private String errowMesg;//错误信息提示
	private int pageNow=1;//当前页
	private int pageSize=8;//每页显示数据条数
	private String key;//查询关键字
	private int searchtype = 0;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	Trolley trolley = null;
	List <Trolley> trolleylist = null;
	private Ordertable oneorder=null;//订单
	List <Ordertable> ordertablelist = null;
	private Orderlist orderlist=null;//订单单品列表
	private Service_Product pro = null;
	
	
	@Autowired
	@Qualifier("trolleyServiceImpl")
	private TrolleyService trolleyService;
	@Autowired
	@Qualifier("service_productServiceImpl")
	private Service_ProductService service_productService;
	@Autowired
	@Qualifier("orderlistServiceImpl")
	private OrderlistService orderlistService;
	@Autowired
	@Qualifier("ordertableServiceImpl")
	private OrdertableService ordertableService;
	
	
	private double allprice = 0.0;

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
			errowMesg = "注册成功，请登录！";
		} else {
			errowMesg = "添加失败";//将错误信息传入errowMesg
		}
		return "cus_login";//添加成功，返回用户管理界面
	}

	/**
	 * @see 根据用户名获取用户信息,返回用户信息展示页面
	 * 
	 */
	public String cusmassage() throws Exception {
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		return "cusmassage";
	}

	/**
	 * @see 跳转到修改用户信息界面
	 * 
	 */
	public String usermassagemodify() throws Exception {
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		return "cusmassagemodify";
	}
	/**
	 * @see 跳转到修改密码界面
	 * 
	 */
	public String userpwdmodify() throws Exception {
		System.out.println("跳转到修改密码界面");
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		return "cuspwdmodify";
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
				return "cuspswmodifyS";//回到用户信息界面
			}else{
				usertemp.setPassword(oneuser.getPassword());
				oneuser = usertemp;
				if(userService.updateUser(usertemp)){
					System.out.println("userpswmodifyS!");
					errowMesg="用户密码修改成功！";
					return "cuspswmodifyS";//回到用户信息界面
				}else{
					oneuser.setPassword(oldpwd);
					errowMesg="用户密码修改失败！请检查重试";
					return "cuspswmodifyF";//返回密码修改界面
				}
			}
		}else{
			oneuser = usertemp;
			System.out.println("userpswmodify!");
			errowMesg="原始密码输入错误，用户密码修改失败！";
			return "cuspswmodifyF";//返回密码修改界面
		}
	}
	/**
	 * @see 购物车页面
	 * @return trolley
	 * @throws Exception
	 */
	public String trolley() throws Exception{
		System.out.println("trolley");
		//获取session中登录用户的信息
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		if(oneuser == null){
			System.out.println("user==null");
			return "tologin";
		}else{
			//根据登录用户ID查询用户最新信息，因为在User model类中有属性Set<Ordertable> trolleySet，所以hibernate在查询user的时候会把对应的购物车列表查询出来
			oneuser = userService.selectOne(oneuser.getId());
			
			trolleylist = trolleyService.selectTrolleyByUserId(oneuser.getId());
			
//			Iterator<Trolley> i = oneuser.getTrolleySet().iterator();//先迭代出来  
//			System.out.println("userIterator");
//			while(i.hasNext()){
//				System.out.println("price="+((Trolley) i).getService_product().getPrice());
//				allprice = allprice +((Trolley) i).getService_product().getPrice()*((Trolley) i).getAmount();
//				System.out.println("allprice="+allprice);
//			}
//			for(int i=0 ;i<trolleylist.size();i++){
//				allprice = allprice + (Trolley)trolleylist[i].get
//			}
			return "trolley";//返回
		}
	}
	
	/**
	 * @see 下单
	 * @return showorder
	 * @throws Exception
	 */
	public String addtoorder() throws Exception{
		System.out.println("addtoorder");
		//获取session中登录用户的信息
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		if(oneuser == null){
			System.out.println("user==null");
			return "tologin";
		}else{
			//根据登录用户ID查询用户最新信息，因为在User model类中有属性Set<Ordertable> trolleySet，所以hibernate在查询user的时候会把对应的购物车列表查询出来
			oneuser = userService.selectOne(oneuser.getId());
			oneorder = new Ordertable();
			oneorder.setUser(oneuser);
			Order_state state = new Order_state();
			state.setId(1);
			state.setName("未接单");
			oneorder.setState(state);
			oneorder.setTime(new Date());
			
			if(ordertableService.addOrder(oneorder)){
				//添加订单
				System.out.println("add订单成功");
				errowMesg = "add success!";
				oneorder = ordertableService.getlastone();
				System.out.println("add订单成功id="+oneorder.getId());
				if(proids==null){//向订单中添加产品
					System.out.println("proidsweikong");
				}else{
					int[] proidss=new int[proids.length];
					System.out.println("proids长度"+proids.length);
					trolley = new Trolley();
					for(int i=0;i<proids.length;i++){
						proidss[i]=Integer.parseInt(proids[i]);
						System.out.println("proidss[i]:"+proidss[i]);
						trolley.setId(proidss[i]);
						trolley = trolleyService.singleTrolley(trolley);
						orderlist = new Orderlist();
						orderlist.setAmount(trolley.getAmount());
						orderlist.setOrder(oneorder);
						orderlist.setService_product(trolley.getService_product());
						if(orderlistService.addOrderlist(orderlist)){
							System.out.println("addorderlist成功");
							trolley.setAmount(0);
							 if (trolleyService.update(trolley)){//从购物车删除
								 System.out.println("update sx");
							 }else{
								 System.out.println("update fail");
							 }
						}else{
							System.out.println("addorderlist失败");
						}
					}
				}
			}else{
				errowMesg = "add fail!";
				System.out.println("add订单失败");
			}
			return "showorder";//返回
		}
	}
	/**
	 * @see 直接下单按钮的实现，包括添加购物车和跳转到购物车
	 */
	public String addandgotoorder(){
		System.out.println("usercustom_add");
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("trolley_user");
		oneuser = (User) request.getSession().getAttribute("userLogin");
		if(oneuser == null){
			System.out.println("trolley_oneuser == null");
			return "tologin";
		}else{
			System.out.println("userid=" +oneuser.getId() +" proid="+pro.getId());
			trolley = trolleyService.check(oneuser.getId(), pro.getId());	
			if(trolley!=null){
				System.out.println("trolley_!=null");
				trolley.setAmount(trolley.getAmount()+1);
				if(trolleyService.update(trolley)){
					return "showtrolley";
				}else{
					return "showtrolley";
				}
			}else{
				System.out.println("trolley_==null");
				pro = service_productService.singleService_Product(pro);
				trolley = new Trolley();
				System.out.println("service_productService.singleService_Product(pro):"+pro.getId());
				trolley.setUser(oneuser);
				trolley.setService_product(pro);
				trolley.setAmount(1);
				if(trolleyService.add(trolley)){
					return "showtrolley";
				}else{
					return "showtrolley";
				}
			}
		}
	}
	
	/**
	 * @see 删除订单，其实是把订单的state更新为7 删除状态，在数据库中可恢复
	 * @return showorder
	 * @throws Exception
	 */
	public String delorder() throws Exception{
		System.out.println("delorder");
		//获取session中登录用户的信息
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		if(oneuser == null){
			System.out.println("user==null");
			return "tologin";
		}else{
			//根据登录用户ID查询用户最新信息，因为在User model类中有属性Set<Ordertable> trolleySet，所以hibernate在查询user的时候会把对应的购物车列表查询出来
			oneorder = ordertableService.selectOne(oneorder.getId());
			Order_state state = new Order_state();
			state.setId(7);
			state.setName("删除");
			oneorder.setState(state);
			if(ordertableService.updateOrder(oneorder)){
				//添加订单
				System.out.println("delorder订单成功");
				errowMesg = "delete success!";
				oneuser = userService.selectOne(oneuser.getId());
			}else{
				errowMesg = "delete fail!";
				System.out.println("delorder订单失败");
			}
			return "showorder";//返回
		}
	}
	/**
	 * @see 取消订单，其实是把订单的state更新为3 删除状态，在数据库中可恢复
	 * @return showorder
	 * @throws Exception
	 */
	public String unorder() throws Exception{
		System.out.println("delorder");
		//获取session中登录用户的信息
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		if(oneuser == null){
			System.out.println("user==null");
			return "tologin";
		}else{
			//根据登录用户ID查询用户最新信息，因为在User model类中有属性Set<Ordertable> trolleySet，所以hibernate在查询user的时候会把对应的购物车列表查询出来
			oneorder = ordertableService.selectOne(oneorder.getId());
			Order_state state = new Order_state();
			state.setId(3);
			state.setName("删除");
			oneorder.setState(state);
			if(ordertableService.updateOrder(oneorder)){
				//添加订单
				System.out.println("unorder订单成功");
				errowMesg = "delete success!";
				oneuser = userService.selectOne(oneuser.getId());
			}else{
				errowMesg = "delete fail!";
				System.out.println("unorder订单失败");
			}
			return "showorder";//返回
		}
	}
	/**
	 * @see 删除购物车中某一商品，其实是把数量设置为0，在数据库中可恢复
	 * @return showorder
	 * @throws Exception
	 */
	public String deltrolley() throws Exception{
		System.out.println("deltrolley");
		//获取session中登录用户的信息
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		if(oneuser == null){
			System.out.println("user==null");
			return "tologin";
		}else{
			//根据登录用户ID查询用户最新信息，因为在User model类中有属性Set<Ordertable> trolleySet，所以hibernate在查询user的时候会把对应的购物车列表查询出来
			trolley = trolleyService.singleTrolley(trolley);
			trolley.setAmount(0);
			if(trolleyService.update(trolley)){
				//添加订单
				System.out.println("del订单成功");
				errowMesg = "delete success!";
				oneuser = userService.selectOne(oneuser.getId());
			}else{
				errowMesg = "delete fail!";
				System.out.println("delorder订单失败");
			}
			return "showtrolley";//返回
		}
	}
	/**
	 * @see 清空购物车中的商品，其实是把该用户购物车中所有的商品数量设置为0，在数据库中可恢复
	 * @return showorder
	 * @throws Exception
	 */
	public String removetrolley() throws Exception{
		System.out.println("removetrolley");
		//获取session中登录用户的信息
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		if(oneuser == null){
			System.out.println("user==null");
			return "tologin";
		}else{
			//根据登录用户ID查询用户最新信息，因为在User model类中有属性Set<Ordertable> trolleySet，所以hibernate在查询user的时候会把对应的购物车列表查询出来
			trolleylist = trolleyService.selectTrolleyByUserId(oneuser.getId());
			for(int i = 0;i<trolleylist.size();i++){
				trolley = trolleylist.get(i);
				trolley.setAmount(0);
				if(trolleyService.update(trolley)){
					//添加订单
					System.out.println("removetrolley成功");
					
				}else{
					System.out.println("removetrolley失败");
				}
			}
			oneuser = userService.selectOne(oneuser.getId());
			return "showtrolley";//返回
		}
	}
	
	
	/**
	 * @see 查看历史订单
	 * @return trolley
	 * @throws Exception
	 */
	public String orderlist() throws Exception{
		System.out.println("orderlist");
		//获取session中登录用户的信息
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		if(oneuser == null){
			System.out.println("user==null");
			return "tologin";
		}else{
			//根据登录用户ID查询用户最新信息，因为在User model类中有属性Set<Ordertable> trolleySet，所以hibernate在查询user的时候会把对应的购物车列表查询出来
			ordertablelist = ordertableService.selectordertableByUserId(oneuser.getId());
			return "orderlist";//返回
		}
	}
	/**
	 * @see 查询历史订单
	 * @return orderlist
	 * @throws Exception
	 */
	public String searchorder() throws Exception{
		System.out.println("searchorder");
		System.out.println("searchtype:"+searchtype);
		//获取session中登录用户的信息
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		if(oneuser == null){
			System.out.println("user==null");
			return "tologin";
		}else{
			if(searchtype==1){
				int id = Integer.parseInt(key);
				ordertablelist = ordertableService.selectordertableByOrderId(id);
			}else if(searchtype ==2){
				ordertablelist = ordertableService.selectordertableByPage(key, 1, 0);
			}else{
				System.out.println("errow");
			}
			return "orderlist";//返回
		}
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


	public double getAllprice() {
		return allprice;
	}


	public void setAllprice(double allprice) {
		this.allprice = allprice;
	}


	public String[] getProids() {
		return proids;
	}


	public void setProids(String[] proids) {
		this.proids = proids;
	}


	public Trolley getTrolley() {
		return trolley;
	}


	public void setTrolley(Trolley trolley) {
		this.trolley = trolley;
	}


	public List<Trolley> getTrolleylist() {
		return trolleylist;
	}


	public void setTrolleylist(List<Trolley> trolleylist) {
		this.trolleylist = trolleylist;
	}


	public Ordertable getOneorder() {
		return oneorder;
	}


	public void setOneorder(Ordertable oneorder) {
		this.oneorder = oneorder;
	}


	public Orderlist getOrderlist() {
		return orderlist;
	}


	public void setOrderlist(Orderlist orderlist) {
		this.orderlist = orderlist;
	}


	public List<Ordertable> getOrdertablelist() {
		return ordertablelist;
	}


	public void setOrdertablelist(List<Ordertable> ordertablelist) {
		this.ordertablelist = ordertablelist;
	}


	public Service_Product getPro() {
		return pro;
	}


	public void setPro(Service_Product pro) {
		this.pro = pro;
	}


	public int getSearchtype() {
		return searchtype;
	}


	public void setSearchtype(int searchtype) {
		this.searchtype = searchtype;
	}
	
}
