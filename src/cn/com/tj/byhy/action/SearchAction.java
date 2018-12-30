package cn.com.tj.byhy.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.tj.byhy.model.Production;
import cn.com.tj.byhy.model.Nomalquestion;
import cn.com.tj.byhy.model.Service_Product;
import cn.com.tj.byhy.service.ProductionService;
import cn.com.tj.byhy.service.NomalquestionService;
import cn.com.tj.byhy.service.Service_ProductService;
import cn.com.tj.byhy.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 2016.4.6
 * @author wubeibei
 * @see 主页搜索栏
 */
@Scope("request")
@Controller("searchAction")
public class SearchAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	/*-----------------------对象----------------------------*/
	private int searchtype;
	private String key;

	private Production production;//活动对象
	private List<Production> productions = null;//活动对象链表
	private Nomalquestion question=null;//常见问题
	List<Nomalquestion> questions = null;//所有常见问题
	private Service_Product service_product;//服务对象
	private List<Service_Product> service_products = null;//服务对象链表
	private String errowMesg;//错误信息提示
	private int pageNow=1;//当前页
	private int pageSize=8;//每页显示数据条数

	@Autowired
	@Qualifier("productionServiceImpl")
	private ProductionService productionService;//注入productionService

	@Autowired
	@Qualifier("service_productServiceImpl")
	private Service_ProductService service_productService;//注入service_productService

	@Autowired
	@Qualifier("nomalquestionServiceImpl")
	private NomalquestionService nomalquestionService;

	/*-----------------------方法----------------------------*/
	/**
	 * @see 实现主页关键字查找功能
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String search() throws Exception{
		if(searchtype==1){//搜索服务/产品
			@SuppressWarnings("rawtypes")
			Map request = (Map)ActionContext.getContext().get("request");
			Pager page = new Pager(getPageNow(), service_productService.selectnum(key));//查询所有数目
			request.put("page",page);//把page放入request
			service_products = service_productService.selectService_ProductByPage(key, pageNow, pageSize);//查询list
			System.out.println("service_productssize:"+service_products.size());
			System.out.println("searchtype1:"+searchtype);
			return "searchservice_productSX";
		}else if(searchtype==2){//搜索常见问题
			@SuppressWarnings("rawtypes")
			Map request = (Map)ActionContext.getContext().get("request");
			Pager page = new Pager(getPageNow(), nomalquestionService.selectnum(key));//查询所有数目
			request.put("page",page);//把page放入request
			questions = nomalquestionService.selectNomalquestionByPage(key, pageNow, pageSize);//查询list
			System.out.println("searchtype2:"+searchtype);
			return "searchquestionSX";
		}else if(searchtype==3){//搜索历史服务
			@SuppressWarnings("rawtypes")
			Map request = (Map)ActionContext.getContext().get("request");
			Pager page = new Pager(getPageNow(), productionService.selectnum(key));//查询所有数目
			request.put("page",page);//把page放入request
			productions = productionService.selectProductionByPage(key, pageNow, pageSize);//查询list
			System.out.println("searchtype3:"+searchtype);
			return "searchproductionSX";
		}else{
			errowMesg="searchtype错误！";
			System.out.println("searchtype:"+searchtype);
			return "errow";
		}
	}

	/**
	 * @see 所有服务查询
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String service() throws Exception{
		@SuppressWarnings("rawtypes")
		Map request = (Map)ActionContext.getContext().get("request");
		Pager page = new Pager(getPageNow(), service_productService.allnum());//查询所有数目
		System.out.println("service_productService.allnum():"+service_productService.allnum());
		request.put("page",page);//把page放入request
		service_products = service_productService.allService_ProductByPage(pageNow, pageSize);//查询list
		return "service_productSX";

	}
	
	/**
	 * @see 所有常见问题查询
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String question() throws Exception{
		@SuppressWarnings("rawtypes")
		Map request = (Map)ActionContext.getContext().get("request");
		Pager page = new Pager(getPageNow(), nomalquestionService.allnum());//查询所有数目
		request.put("page",page);//把page放入request
		questions = nomalquestionService.allNomalquestionByPage(pageNow, pageSize);//查询list
		return "questionSX";

	}
	
	/**
	 * @see 查询所有历史活动
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String production() throws Exception{
		@SuppressWarnings("rawtypes")
		Map request = (Map)ActionContext.getContext().get("request");
		Pager page = new Pager(getPageNow(), productionService.allnum());//查询所有数目
		request.put("page",page);//把page放入request
		productions = productionService.allProductionByPage(pageNow, pageSize);//查询list
		return "productionSX";

	}

	/*-----------------------get set------------------------*/
	public int getSearchtype() {
		return searchtype;
	}
	public void setSearchtype(int searchtype) {
		this.searchtype = searchtype;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Production getProduction() {
		return production;
	}
	public void setproduction(Production production) {
		this.production = production;
	}
	public List<Production> getProductions() {
		return productions;
	}
	public void setProductions(List<Production> productions) {
		this.productions = productions;
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

	public Service_Product getService_product() {
		return service_product;
	}

	public void setService_product(Service_Product service_product) {
		this.service_product = service_product;
	}

	public List<Service_Product> getService_products() {
		return service_products;
	}

	public void setService_products(List<Service_Product> service_products) {
		this.service_products = service_products;
	}

	public void setProduction(Production production) {
		this.production = production;
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
}
