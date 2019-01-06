package cn.com.tj.byhy.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.tj.byhy.model.Order_state;
import cn.com.tj.byhy.model.Ordertable;
import cn.com.tj.byhy.model.User;
import cn.com.tj.byhy.service.OrdertableService;
import cn.com.tj.byhy.service.UserService;
import cn.com.tj.byhy.util.Pager;
import cn.com.tj.byhy.util.SendEmail;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 2018/12/30
 * @author wubeibei
 * @see order增删改查、登陆
 */
@Scope("request")
@Controller("ordertableAction")
public class OrdertableAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	/*---------------------------对象-------------------------------*/

	private Ordertable oneorder=null;//订单
	List<Ordertable> ordertables = null;//所有订单
	private String errowMesg;//错误信息提示
	private int flag = 3;
	private int pageNow=1;//当前页
	private int pageSize=8;//每页显示数据条数
	private String key="";//关键字查询
	private User oneuser = null;//登录用户信息
	private int searchtype = 0;

	@Autowired
	@Qualifier("ordertableServiceImpl")
	private OrdertableService ordertableService;
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	/*---------------------------方法-------------------------------*/
	/**
	 * 添加订单
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		System.out.println("addorder添加订单");
		oneuser = userService.getUserByName(oneorder.getUser().getUsername());
		oneorder.setUser(oneuser);
		Order_state state = new Order_state();
		state.setId(1);
		state.setName("未接单");
		oneorder.setState(state);
		oneorder.setTime(new Date());
		if(ordertableService.addOrder(oneorder)){
			errowMesg = "添加成功";
		} else {
			errowMesg = "添加失败";//将错误信息传入errowMesg
		}
		return "addSX";//添加成功，返回订单管理界面
	}

	/**
	 * 删除订单
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		if (ordertableService.delOrder(oneorder.getId())) {//根据id删除订单
			errowMesg = "delete success!";
		} else {
			errowMesg = "delete fail!";
		}
		return "allorder";
	}
	/**
	 * 查看单个订单
	 * @return singleSX
	 * @throws Exception
	 */
	public String single() throws Exception{
		oneorder = ordertableService.selectOne(oneorder.getId());
		return "singleSX";
	}

	/**
	 * @see 跳转到修改订单信息界面
	 * 
	 */
	public String massagemodify() throws Exception {
		return "ordermassagemodify";
	}

	/**
	 * @see 修改订单信息，然后回到订单信息界面
	 * 
	 */
	public String domodify() throws Exception {
		System.out.println("domodify!");
		Ordertable ordertemp = null;
		System.out.println("domodify!order===orderid="+oneorder.getId()+"orderusername="+oneorder.getUser().getUsername());
		ordertemp = ordertableService.selectOne(oneorder.getId());
		System.out.println("domodify!orderid="+ordertemp.getId());
		//		order.setState(ordertemp.getState());
		//		order.setId(ordertemp.getId());
		if(ordertableService.updateOrder(oneorder)){
			System.out.println("domodifyS!");
			errowMesg="订单信息修改成功！";
			return "domodifyS";
		}else{
			errowMesg="订单信息修改失败！";
			return "domodifyF";
		}

	}

	/**
	 * @see 查找所有订单信息，并返回显示页面
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String allordertableByPage() throws Exception {
		System.out.println("allorderByPage!");
		@SuppressWarnings("rawtypes")
		Map request = (Map)ActionContext.getContext().get("request");
		Pager page = new Pager(getPageNow(), ordertableService.OrderNum());//查询所有数目
		System.out.println("ordertableService.allnum():"+ordertableService.OrderNum());
		request.put("page",page);//把page放入request
		ordertables = ordertableService.allordertableByPage(pageNow, pageSize);//查询list
		System.out.println("allservice_productByPage:"+ordertables.size());
		return "allordertableSX";//返回
	}
	/**
	 * 分页选取关键字查询的服务
	 * @return keysearchSX
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String keysearch() throws Exception{
		System.out.println("keysearchByPage");
		System.out.println("key:"+key);
		System.out.println("type:"+searchtype);
		@SuppressWarnings("rawtypes")
		Map request = (Map)ActionContext.getContext().get("request");
		int selectnum = 0;
		if(searchtype==1){
			int id = Integer.parseInt(key);
			ordertables = ordertableService.selectordertableByOrderId(id);
			selectnum = ordertables.size();
		}else if(searchtype ==2){
			selectnum = ordertableService.selectnum(key);
			ordertables = ordertableService.selectordertableByPage(key, pageNow, pageSize);
		}else if(searchtype ==3){
			selectnum = ordertableService.selectnumByState(key);
			ordertables = ordertableService.selectordertableByStateByPage(key, pageNow, pageSize);
		}else if(searchtype ==4){
			if(userService.getUserByName(key)==null){
				ordertables = null;
			}else{
				int id=userService.getUserByName(key).getId();
				selectnum = ordertableService.selectnumByUserId(id);
				ordertables = ordertableService.selectordertableByUserIdByPage(id, pageNow, pageSize);
			}
		}else{
			System.out.println("errow");
		}
		Pager page = new Pager(getPageNow(), selectnum);//查询所有数目
		System.out.println("ordertableService.allnum():"+ordertableService.selectnum(key));
		request.put("page",page);//把page放入request
		return "keysearchSX";//返回
	}
	/**
	 * @see 接单，其实是把订单的state更新为2接单状态
	 * @return showorder
	 * @throws Exception
	 */
	public String receorder() throws Exception{
		System.out.println("receorder");
		//获取session中登录用户的信息
		oneuser = (User) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		if(oneuser == null){
			System.out.println("user==null");
			return "tologin";
		}else{
			//根据登录用户ID查询用户最新信息，因为在User model类中有属性Set<Ordertable> trolleySet，所以hibernate在查询user的时候会把对应的购物车列表查询出来
			oneorder = ordertableService.selectOne(oneorder.getId());
			Order_state state = new Order_state();
			state.setId(2);
			state.setName("已接单");
			oneorder.setState(state);
			if(ordertableService.updateOrder(oneorder)){
				//添加订单
				System.out.println("receorder订单成功");
				errowMesg = "receorder success!";
				String toMail=oneorder.getUser().getMail();
				String content = "尊敬的用户您好，您的订单已经被接收，稍后会与您联系并处理订单";
				// 发送邮件
				SendEmail.sendMailmessageToUser(toMail, content);
				System.out.println("SendEmail.sendMail");
				// 邮件发送成功将sentEmail设为1
				flag=1;
				oneuser = userService.selectOne(oneuser.getId());
			}else{
				flag=2;
				errowMesg = "receorder fail!";
				System.out.println("receorder订单失败");
			}
			return "receorderSX";//返回
		}
	}
	/**
	 * @see 取消订单，其实是把订单的state更新为4管理员取消订单状态
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
			state.setId(4);
			state.setName("管理员取消订单");
			oneorder.setState(state);
			if(ordertableService.updateOrder(oneorder)){
				//添加订单
				System.out.println("unorder订单成功");
				errowMesg = "unorder success!";
				String toMail=oneorder.getUser().getMail();
				String content = "尊敬的用户您好，您的订单已经被取消，如有疑问请与管理员联系，联系人：木鱼，联系邮箱为：test_nju_edu2@163.com，联系电话：***********。";
				// 发送邮件
				SendEmail.sendMailmessageToUser(toMail, content);
				System.out.println("SendEmail.sendMail");
				// 邮件发送成功将sentEmail设为1
				flag=1;
				return "unorderSX";//返回
			}else{
				flag=2;
				errowMesg = "unorder fail!";
				System.out.println("unorder订单失败");
			}
			return "unorderSX";//返回
		}		
	}
	/**
	 * @see 结束订单，其实是把订单的state更新为5订单完成状态
	 * @return showorder
	 * @throws Exception
	 */
	public String downorder() throws Exception{
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
			state.setId(5);
			state.setName("订单完成");
			oneorder.setState(state);
			if(ordertableService.updateOrder(oneorder)){
				//添加订单
				System.out.println("unorder订单成功");
				errowMesg = "unorder success!";
				return "unorderSX";//返回
			}else{
				errowMesg = "unorder fail!";
				System.out.println("unorder订单失败");
			}
			return "downorderSX";//返回
		}		
	}
	/**
	 * @see 删除订单，其实是把订单的state更新为7订单删除状态
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
				errowMesg = "delorder success!";
				return "unorderSX";//返回
			}else{
				errowMesg = "delorder fail!";
				System.out.println("delorder订单失败");
			}
			return "downorderSX";//返回
		}		
	}
	/*---------------------------get、set方法-------------------------------*/
	
	public List<Ordertable> getOrdertables() {
		return ordertables;
	}

	public Ordertable getOneorder() {
		return oneorder;
	}

	public void setOneorder(Ordertable oneorder) {
		this.oneorder = oneorder;
	}

	public void setOrdertables(List<Ordertable> ordertables) {
		this.ordertables = ordertables;
	}

	public String getErrowMesg() {
		return errowMesg;
	}

	public void setErrowMesg(String errowMesg) {
		this.errowMesg = errowMesg;
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

	public User getOneuser() {
		return oneuser;
	}

	public void setOneuser(User oneuser) {
		this.oneuser = oneuser;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(int searchtype) {
		this.searchtype = searchtype;
	}
	
}
