package cn.com.tj.byhy.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 图片上传
 * 
 * @author wubeibei 2019/1/6
 */
public class PictureUpload extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 方法一：这个方法是上传图片用的
	 */
    private static final int BUFFER_SIZE = 16 * 1024 ;
	private void copy(File src, File dst) {
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
    private String getExtention(String fileName) {
         int pos = fileName.lastIndexOf( "." );
         return fileName.substring(pos);
    } 
	/**
	 * 方法三：上传文件，图片也是文件一种
	 *  
	 */
    public  String uploadFile(File myFile,String fileName,String path,String imageFileName){        
        imageFileName = new Date().getTime() + getExtention(fileName);
        File imageFile = new File(path + imageFileName);
        copy(myFile, imageFile);
        return imageFileName;
    } 

}
