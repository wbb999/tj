package cn.com.tj.byhy.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.tj.byhy.model.Production;
import cn.com.tj.byhy.service.ProductionService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 商品Action对象
 * 
 * @author DB 2016年4月8日
 */
@Scope("request")
@Controller("testAction")
public class TestAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pictureLink;//用来上传图片
	private String error;
	private String picture;
	private Map<Integer, String> map;
	
	private Production production;//作品对象
	private String errowMesg;//错误信息提示
	
	
	@Autowired
	@Qualifier("productionServiceImpl")
	private ProductionService productionService;//注入productionService


	/**
	 * 方法一：这个方法是上传图片用的
	 */
	private File myFile;
    private String contentType;
    private String fileName;
    private String imageFileName;
    private String caption;
    private static final int BUFFER_SIZE = 16 * 1024 ;
	private static void copy(File src, File dst) {
        try {
           InputStream in = null ;
           OutputStream out = null ;
            try {                
               in = new BufferedInputStream( new FileInputStream(src), BUFFER_SIZE);
               out = new BufferedOutputStream( new FileOutputStream(dst), BUFFER_SIZE);
                byte [] buffer = new byte [BUFFER_SIZE];
                while (in.read(buffer) > 0 ) {
                   out.write(buffer);
               } 
           } finally {
                if ( null != in) {
                   in.close();
               } 
                if ( null != out) {
                   out.close();
               } 
           } 
       } catch (Exception e) {
           e.printStackTrace();
       } 
   } 
	 
	 /**
	 * 方法二：这个方法是上传图片用的
	 */
   private static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf( "." );
        return fileName.substring(pos);
   } 
	/**
	 * 方法三：上传文件，图片也是文件一种
	 *  
	 */
   public  void uploadFile(){        
       imageFileName = new Date().getTime() + getExtention(fileName);
       File imageFile = new File(ServletActionContext.getServletContext().getRealPath( "/picture" ) + "/" + imageFileName);
       copy(myFile, imageFile);
   }
	/**
	 * 添加商品
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		
		System.out.println("save_addpicture");
		production.setId(1);
		production = productionService.singleProduction(production);
		uploadFile();
		System.out.println("picture"+picture);
		production.setPicture("/picture/"+picture);//添加图片地址到数据库
		if(productionService.update(production)){//添加操作
			errowMesg = "addpicture success!";//查询信息
			return "addpictureSX";
		}else{//添加失败
			errowMesg = "添加失败";//将错误信息传入error
			return "error";
		}
	}
	
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Map<Integer, String> getMap() {
		return map;
	}

	public void setMap(Map<Integer, String> map) {
		this.map = map;
	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
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

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
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
	
	
}
