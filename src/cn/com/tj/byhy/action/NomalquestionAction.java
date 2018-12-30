package cn.com.tj.byhy.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.tj.byhy.model.Nomalquestion;
import cn.com.tj.byhy.service.NomalquestionService;
import cn.com.tj.byhy.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 2016/4/4
 * @author wubeibei
 * @see Nomalquestion增删改查
 */
@Scope("request")
@Controller("nomalquestionAction")
public class NomalquestionAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	/*---------------------------对象-------------------------------*/

	private Nomalquestion question=null;//常见问题
	List<Nomalquestion> questions = null;//所有常见问题
	private String errowMesg;//错误信息提示
	private int pageNow=1;//当前页
	private int pageSize=8;//每页显示数据条数
	private String key="";//关键字查询

	@Autowired
	@Qualifier("nomalquestionServiceImpl")
	private NomalquestionService nomalquestionService;

	/*---------------------------方法-------------------------------*/

	/**
	 * 常见问题添加
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		System.out.println("question_add");
		System.out.println("question_Question"+question.getQuestion());
		System.out.println("question_Answer"+question.getAnswer());
		question.setAccount(0);;//
		if(nomalquestionService.add(question)){//添加操作
			return "addSX";
		}else{//添加失败
			errowMesg = "添加失败";//将错误信息传入error
			return "error";
		}
	}
	/**
	 * 删除常见问题
	 * @return deleteSX
	 * @throws Exception
	 */
	public String delete()throws Exception{
		System.out.println("question_delete");
		question = nomalquestionService.singleNomalquestion(question);
		if(nomalquestionService.delete(question)){//执行删除操作
			System.out.println("nomalquestionService.delete(question)");
			return "deleteSX";//返回deleteSX
		}
		errowMesg = "删除失败";//删除失败将错误信息防入error
		return "error";
	}
	/**
	 * 常见问题修改前查询
	 * @return toupdateSX
	 * @throws Exception
	 */
	public String toupdate()throws Exception{
		System.out.println("question_toupdate");
		question = nomalquestionService.singleNomalquestion(question);//查询单个常见问题
		if(question != null){
			return "toupdateSX";//非空的话返回toupdateSX
		}else{
			errowMesg = "查询失败";//查询失败将错误信息防入error
			return "error";
		}
	}
	/**
	 * 常见问题更新
	 * @ return 
	 */
	public String update()throws Exception{
		//执行update操作
		System.out.println("update id:"+question.getId());
		System.out.println("question_Question"+question.getQuestion());
		System.out.println("question_Answer"+question.getAnswer());
	//	question.setImgaddress("1234");
		if(nomalquestionService.update(question)){
			return "updateSX";
		}else{
			errowMesg = "更新失败";
			return "error";
		}
	}
	/**
	 * 单个常见问题查找
	 * @return singleSX
	 * @throws Exception
	 */
	public String single()throws Exception{
		System.out.println("single id:"+question.getId());
		
		question = nomalquestionService.singleNomalquestion(question);//根据id选取单个常见问题
		if(question!=null){//如果查询成功返回singleSX
			return "singleSX";
		}else{
			errowMesg = "查询失败";//查询失败将错误信息防入error
			return "error";
		}
	}
	/**
	 * 分页选取所有的常见问题
	 * @return selectSX
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String allnomalquestionByPage() throws Exception{
			System.out.println("allactByPage");
			@SuppressWarnings("rawtypes")
			Map request = (Map)ActionContext.getContext().get("request");
			Pager page = new Pager(getPageNow(), nomalquestionService.allnum());//查询所有数目
			request.put("page",page);//把page放入request
			questions = nomalquestionService.allNomalquestionByPage(pageNow, pageSize);//查询list
			System.out.println("allactByPage+"+questions.size());
		return "allquestionSX";//返回
	}
	
	/**
	 * 分页选取关键字查询的常见问题
	 * @return keysearchSX
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String keysearch() throws Exception{
		System.out.println("keysearchByPage");
		System.out.println("key:"+key);
		System.out.println("activityService.selectnum(key):"+nomalquestionService.selectnum(key));
			@SuppressWarnings("rawtypes")
			Map request = (Map)ActionContext.getContext().get("request");
			Pager page = new Pager(getPageNow(), nomalquestionService.selectnum(key));//查询所有数目
			request.put("page",page);//把page放入request
			questions = nomalquestionService.selectNomalquestionByPage(key, pageNow, pageSize);//查询list
			System.out.println("keysearchByPage："+questions.size());
		return "keysearchSX";//返回
	}
	/*---------------------------get、set方法-------------------------------*/

	public String getErrowMesg() {
		return errowMesg;
	}

	public void setErrowMesg(String errowMesg) {
		this.errowMesg = errowMesg;
	}
	public Nomalquestion getQuestion() {
		return question;
	}
	public void setQuestion(Nomalquestion question) {
		this.question = question;
	}
	public List<Nomalquestion> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Nomalquestion> questions) {
		this.questions = questions;
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
}
