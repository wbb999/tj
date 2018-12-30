package cn.com.tj.byhy.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.tj.byhy.model.Production;
import cn.com.tj.byhy.service.ProductionService;
import cn.com.tj.byhy.util.Pager;
import cn.com.tj.byhy.util.PictureUpload;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 2016/4/4
 * @see 作品相关的action
 * @author wubeibei
 *
 */
@Scope("request")
@Controller("productionAction")
public class ProductionAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	/*-----------------------对象----------------------------*/
	private Production production;//作品对象
	private List<Production> productions = null;//作品对象链表
	private String errowMesg;//错误信息提示
	private int pageNow=1;//当前页
	private int pageSize=8;//每页显示数据条数
	private String key="";//关键字查询
	
	//图片上传属性
	private File myFile;
    private String contentType;
    private String fileName;
    private String caption;
    private String imageFileName;

	@Autowired
	@Qualifier("productionServiceImpl")
	private ProductionService productionService;//注入productionService

	/*-----------------------方法----------------------------*/
	/**
	 * 作品添加
	 * @return
	 * @throws Exception
	 */
	public String toadd() throws Exception{
		System.out.println("production_toadd");
		return "toadd";
	}
	/**
	 * 作品图片添加
	 * @return
	 * @throws Exception
	 */
	public String toaddpicture() throws Exception{
		System.out.println("production_toaddpicture");
		production = productionService.singleProduction(production);
		return "toaddpicture";
	}
	/**
	 * 作品添加
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		System.out.println("production_add");
		System.out.println("production_name"+production.getName());
		System.out.println("production_time"+production.getTime());
		System.out.println("production_addr"+production.getAddress());
		System.out.println("production_detail"+production.getDetail());
//		String path="C:/java/workspace/TJ/WebContent/picture/";
//		PictureUpload upload = new PictureUpload();
//		String picture = upload.uploadFile(myFile, fileName, path,imageFileName);
//		System.out.println("picture"+picture);
		production.setPicture("//8080");//添加图片地址到数据库
		if(productionService.add(production)){//添加操作
			errowMesg = "add success!";//查询信息
			return "addSX";
		}else{//添加失败
			errowMesg = "添加失败";//将错误信息传入error
			return "error";
		}
	}
	
	/**
	 * 作品tupian添加
	 * @return
	 * @throws Exception
	 */
	public String addpicture() throws Exception{
		System.out.println("production_addpicture");
		//production.setId(1);
		System.out.println("production[0].id"+production.getId());
		production = productionService.singleProduction(production);
		System.out.println("production[0].name"+production.getName());
	//	String path = ServletActionContext.getServletContext().getRealPath( "/picture" ) + "/" ;
		String path="C:/java/workspace/TJ/WebContent/picture/";//picture在本机的绝对路径
		System.out.println("path"+path);
		PictureUpload p = new PictureUpload();
		String picture = p.uploadFile(myFile, fileName, path,imageFileName);
		System.out.println("picture"+picture);
		production.setPicture("picture/"+picture);//添加图片地址到数据库
		if(productionService.update(production)){//添加操作
			errowMesg = "addpicture success!";//查询信息
			return "addpictureSX";
		}else{//添加失败
			errowMesg = "添加失败";//将错误信息传入error
			return "error";
		}
	}
	
	
	
	
	/**
	 * 删除作品
	 * @return deleteSX
	 * @throws Exception
	 */
	public String delete()throws Exception{
		System.out.println("production_delete");
		production = productionService.singleProduction(production);
		if(productionService.delete(production)){//执行删除操作
			System.out.println("productionService.delete(production)");
			errowMesg = "delete success!";//查询信息
			return "deleteSX";//返回deleteSX
		}
		errowMesg = "删除失败";//删除失败将错误信息防入error
		return "error";
	}
	/**
	 * 作品修改前查询
	 * @return toupdateSX
	 * @throws Exception
	 */
	public String toupdate()throws Exception{
		System.out.println("toupdate id:"+production.getId());
		production = productionService.singleProduction(production);//查询单个作品
		System.out.println("production_name"+production.getName());
		System.out.println("production_time"+production.getTime());
		System.out.println("production_addr"+production.getAddress());
		System.out.println("production_detail"+production.getDetail());
		if(production != null){
			return "toupdateSX";//非空的话返回toupdateSX
		}else{
			errowMesg = "查询失败";//查询失败将错误信息防入error
			return "error";
		}
	}
	/**
	 * 作品更新
	 * @ return 
	 */
	public String update()throws Exception{
		//执行update操作
		System.out.println("update id:"+production.getId());
		System.out.println("production_name"+production.getName());
		System.out.println("production_time"+production.getTime());
		System.out.println("production_addr"+production.getAddress());
		System.out.println("production_detail"+production.getDetail());
		production.setPicture("1234");
		if(productionService.update(production)){
			errowMesg = "update success!";//查询信息
			return "updateSX";
		}else{
			errowMesg = "update fail!";
			return "error";
		}
	}
	/**
	 * 单个作品查找
	 * @return singleSX
	 * @throws Exception
	 */
	public String single()throws Exception{
		System.out.println("single id:"+production.getId());
		production = productionService.singleProduction(production);//根据id选取单个作品
		if(production!=null){//如果查询成功返回singleSX
			return "singleSX";
		}else{
			errowMesg = "查询失败";//查询失败将错误信息防入error
			return "error";
		}
	}
	/**
	 * 分页选取所有的作品
	 * @return selectSX
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String allProductionByPage() throws Exception{
		System.out.println("allProductionByPage");
		@SuppressWarnings("rawtypes")
		Map request = (Map)ActionContext.getContext().get("request");
		Pager page = new Pager(getPageNow(), productionService.allnum());//查询所有数目
		request.put("page",page);//把page放入request
		productions = productionService.allProductionByPage(pageNow, pageSize);//查询list
		System.out.println("allProductionByPagelist：size+"+productions.size());
		System.out.println("productions[0]====="+productions.get(0).getName());
		return "allproductionSX";//返回
	}

	/**
	 * 分页关键字查询的作品
	 * @return keysearchSX
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String keysearch() throws Exception{
		System.out.println("keysearchByPage");
		System.out.println("key:"+key);
		System.out.println("productionService.selectnum(key):"+productionService.selectnum(key));
		@SuppressWarnings("rawtypes")
		Map request = (Map)ActionContext.getContext().get("request");
		Pager page = new Pager(getPageNow(), productionService.selectnum(key));//查询所有数目
		request.put("page",page);//把page放入request
		productions = productionService.selectProductionByPage(key, pageNow, pageSize);//查询list
		System.out.println("keysearchByPage==productions.size():"+productions.size());
		return "keysearchSX";//返回
	}
	
	/*-----------------------get set----------------------------*/
	
	public String getErrowMesg() {
		return errowMesg;
	}
	public Production getProduction() {
		return production;
	}
	public void setProduction(Production production) {
		this.production = production;
	}
	public List<Production> getProductions() {
		return productions;
	}
	public void setProductions(List<Production> productions) {
		this.productions = productions;
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
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public void setMyFileContentType(String contentType) {
        this .contentType = contentType;
   } 
    public void setMyFileFileName(String fileName) {
        this .fileName = fileName;
   }
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
}
