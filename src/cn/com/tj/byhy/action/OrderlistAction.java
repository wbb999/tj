package cn.com.tj.byhy.action;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import cn.com.tj.byhy.model.Orderlist;
import cn.com.tj.byhy.service.OrderlistService;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 2018/12/30
 * @author wubeibei
 * @see Order增删改查、登陆
 */
@Scope("request")
@Controller("orderlistAction")
public class OrderlistAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	/*---------------------------对象-------------------------------*/

	private Orderlist orderlist=null;//订单单品列表
	List<Orderlist> orderlists = null;//所有订单单品列表
	private String errowMesg;//错误信息提示

	@Autowired
	@Qualifier("orderlistServiceImpl")
	private OrderlistService orderlistService;

	/*---------------------------方法-------------------------------*/
	/**
	 * 添加订单单品列表
	 * @return
	 * @throws Exception
	 */
	public String addOrderlist() throws Exception{
		System.out.println("addOrder添加订单单品列表");
		if(orderlistService.addOrderlist(orderlist)){
			errowMesg = "添加成功";
		} else {
			errowMesg = "添加失败";//将错误信息传入errowMesg
		}
		return "allOrderlist";//添加成功，返回订单单品列表管理界面
	}

	/**
	 * 删除订单单品列表
	 * @return
	 * @throws Exception
	 */
	public String deleteOrder() throws Exception{
		if (orderlistService.delOrderlist(orderlist.getId())) {//根据id软删除订单单品列表
			errowMesg = "delete success!";
		} else {
			errowMesg = "delete fail!";
		}
		return "allOrder";
	}
	/**
	 * @see 跳转到修改订单单品列表信息界面
	 * 
	 */
	public String Ordermassagemodify() throws Exception {
		return "Ordermassagemodify";
	}
	/**
	 * @see 修改订单单品列表信息，然后回到订单单品列表信息界面
	 * 
	 */
	public String domodify() throws Exception {
//		System.out.println("domodify!");
//		Order Ordertemp = null;
//		System.out.println("domodify!Order===Ordername="+Order.getOrdername()+"Orderphone="+Order.getPhone());
//		Ordertemp = orderlistService.getOrderByName(Order.getOrdername());
//		System.out.println("domodify!Ordername="+Ordertemp.getOrdername()+"Orderid="+Ordertemp.getId());
//		Order.setPassword(Ordertemp.getPassword());
//		Order.setState(Ordertemp.getState());
//		Order.setId(Ordertemp.getId());
//		if(orderlistService.updateOrder(Order)){
//			System.out.println("domodifyS!");
//			errowMesg="订单单品列表信息修改成功！";
//			return "domodifyS";
//		}else{
//			errowMesg="订单单品列表信息修改失败！";
//			return "domodifyF";
//		}
		return "domodifyF";
	}

	/**
	 * @see 查找所有订单单品列表信息，并返回显示页面
	 * @return
	 * @throws Exception
	 */
	public String allOrderlist() throws Exception {
		System.out.println("allOrder!");
		try {
			orderlists=orderlistService.selectAllOrderlist();
			if(orderlists==null){
				errowMesg="没有订单单品列表！";
			}else{
			}
			return "allOrderS";
		} catch (Exception e) {
			System.out.println("orderlists!errow");
			e.printStackTrace();
			errowMesg="查询所有订单单品列表失败！";
			return "allOrderlistF";
		}
	}
	/*---------------------------get、set方法-------------------------------*/
	public Orderlist getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(Orderlist orderlist) {
		this.orderlist = orderlist;
	}

	public List<Orderlist> getOrderlists() {
		return orderlists;
	}

	public void setOrderlists(List<Orderlist> orderlists) {
		this.orderlists = orderlists;
	}

	public String getErrowMesg() {
		return errowMesg;
	}

	public void setErrowMesg(String errowMesg) {
		this.errowMesg = errowMesg;
	}

	public OrderlistService getOrderlistService() {
		return orderlistService;
	}

	public void setOrderlistService(OrderlistService orderlistService) {
		this.orderlistService = orderlistService;
	}
		

}
