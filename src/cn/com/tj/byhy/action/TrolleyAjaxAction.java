package cn.com.tj.byhy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.tj.byhy.model.Service_Product;
import cn.com.tj.byhy.model.Trolley;
import cn.com.tj.byhy.model.User;
import cn.com.tj.byhy.service.Service_ProductService;
import cn.com.tj.byhy.service.TrolleyService;
import cn.com.tj.byhy.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 2016.4.4
 * @author wubeibei
 * @see 购物车Ajax动态交互
 */
@Scope("request")
@Controller("trolleyAjaxAction")
public class TrolleyAjaxAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*-----------------------对象----------------------------*/
	private Trolley trolley = null;
	private User oneuser = null;
	private Service_Product pro = null;
	
	@Autowired
	@Qualifier("userServiceImpl")
	public UserService userService;
	@Autowired
	@Qualifier("trolleyServiceImpl")
	private TrolleyService trolleyService;
	@Autowired
	@Qualifier("service_productServiceImpl")
	private Service_ProductService service_productService;
	
	/*-----------------------方法----------------------------*/
	/**
	 * @see 添加购物车操作
	 */
	public void add(){
		System.out.println("trolley_add");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		System.out.println("trolley_user");
		oneuser = (User) request.getSession().getAttribute("userLogin");
		if(oneuser == null){
			System.out.println("trolley_oneuser == null");
			try {
				response.getWriter().write("3");//请先登录提示
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("userid=" +oneuser.getId() +" proid="+pro.getId());
			trolley = trolleyService.check(oneuser.getId(), pro.getId());	
		//	System.out.println("userid=" +trolley.getUser().getId() +" proid="+trolley.getService_product().getId());
			if(trolley!=null){
				System.out.println("trolley_!=null");
				trolley.setAmount(trolley.getAmount()+1);
				if(trolleyService.update(trolley)){
					try {
						response.getWriter().write("1");//加入购物车成功
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					try {
						response.getWriter().write("2");//加入购物车失败
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}else{
				System.out.println("trolley_==null");
				pro = service_productService.singleService_Product(pro);
				trolley = new Trolley();
				System.out.println("service_productService.singleService_Product(pro):"+pro.getId());
				trolley.setUser(oneuser);
				System.out.println("1");
				trolley.setService_product(pro);
				System.out.println("2");
				trolley.setAmount(1);
				System.out.println("3");
				if(trolleyService.add(trolley)){
					try {
						response.getWriter().write("1");//加入购物车成功
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					try {
						response.getWriter().write("2");//加入购物车失败
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		}
	}
	/**
	 * @see 购物车加一操作
	 */
	public void plus(){
		System.out.println("trolley_plus");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		oneuser = (User) request.getSession().getAttribute("userLogin");
		if(oneuser == null){
			try {
				response.getWriter().write("3");//请先登录提示
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			trolley = trolleyService.singleTrolley(trolley);		
			if(trolley!=null){
				System.out.println("trolley_!=null");
				trolley.setAmount(trolley.getAmount()+1);
				if(trolleyService.update(trolley)){
					try {
						response.getWriter().write("1");//购物车加一成功
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					try {
						response.getWriter().write("2");//购物车加一失败
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}else{
				System.out.println("trolley_==null");
				try {
					response.getWriter().write("4");//加入购物车失败
				} catch (Exception e) {
						e.printStackTrace();
				}
			};
		}
	}
	/**
	 * @see 购物车减一操作
	 */
	public void down(){
		System.out.println("trolley_down");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		oneuser = (User) request.getSession().getAttribute("userLogin");
		if(oneuser == null){
			try {
				response.getWriter().write("3");//先登录提示
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			trolley = trolleyService.singleTrolley(trolley);		
			if(trolley!=null){
				System.out.println("trolley_!=null");
				trolley.setAmount(trolley.getAmount()-1);
				if(trolleyService.update(trolley)){
					try {
						response.getWriter().write("1");//购物车减一成功
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					try {
						response.getWriter().write("2");//购物车减一失败
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}else{
				System.out.println("trolley_==null");
				try {
					response.getWriter().write("4");//加入购物车失败
				} catch (Exception e) {
						e.printStackTrace();
				}
			};
		}
	}
	
	/*-----------------------get set----------------------------*/
	public Trolley getTrolley() {
		return trolley;
	}
	public void setTrolley(Trolley trolley) {
		this.trolley = trolley;
	}
	public User getOneuser() {
		return oneuser;
	}
	public void setOneuser(User oneuser) {
		this.oneuser = oneuser;
	}
	public Service_Product getPro() {
		return pro;
	}
	public void setPro(Service_Product pro) {
		this.pro = pro;
	}
}
