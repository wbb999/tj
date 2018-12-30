package cn.com.tj.byhy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.tj.byhy.model.Nomalquestion;
import cn.com.tj.byhy.model.User;
import cn.com.tj.byhy.service.NomalquestionService;
import cn.com.tj.byhy.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 2016.4.4
 * @author wubeibei
 * @see Ajax动态交互
 */
@SuppressWarnings("serial")
@Scope("request")
@Controller("loginAjaxAction")
public class LoginAjaxAction extends ActionSupport{
	
	/*-----------------------对象----------------------------*/
	private String number;
	private String username;
	private int questionid = 0;
	
	@Autowired
	@Qualifier("userServiceImpl")
	public UserService userService;
	@Autowired
	@Qualifier("nomalquestionServiceImpl")
	private NomalquestionService nomalquestionService;
	
	/*-----------------------方法----------------------------*/
	/**
	 * @see 验证验证码正确性
	 */
	public void checknumber(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String vali_sys = request.getSession().getAttribute("Valicode")
				.toString();
		// 需要注意的是Parameter-key是input的name
		String vali_user = number;
		if (vali_user.equalsIgnoreCase(vali_sys)) {
			try {
				response.getWriter().write("1");//验证码正确
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().write("2");//验证码错误
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @see 验证用户名是否存在
	 */
	public void checkusername(){
		HttpServletResponse response = ServletActionContext.getResponse();
		User user = userService.getUserByName(username);
		boolean flagn = true;
		if(user!=null){
			flagn = false;
		}
		if (flagn) {
			try {
				response.getWriter().write("1");//用户名不存在
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().write("2");//用户名已存在
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @see 常见问题点赞
	 */
	public void zan(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Nomalquestion temp = new Nomalquestion();
		temp.setId(questionid);
		Nomalquestion question = nomalquestionService.singleNomalquestion(temp);
		boolean flagn = false;
		if(question!=null){
			question.setAccount(question.getAccount()+1);
			nomalquestionService.update(question);
			flagn = true;
		}else{
			System.out.println("查询问题失败，无法点赞！");
			flagn = false;
		}
		if (flagn) {
			try {
				response.getWriter().write("1");//操作成功
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().write("2");//操作失败
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*-----------------------get set----------------------------*/
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getQuestionid() {
		return questionid;
	}
	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}
	
}
