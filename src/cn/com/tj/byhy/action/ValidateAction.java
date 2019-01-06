package cn.com.tj.byhy.action;

import org.springframework.stereotype.Controller;

import cn.com.tj.byhy.util.SendEmail;

/**
 * 2018/12/25
 * @see 邮件联系我们的Action
 * @Scope("request")
 * @author wubeibei
 *
 */
@Controller("validateAction")
public class ValidateAction {
	/*---------------------------对象-------------------------------*/
	private int flag = 0;// 当flag为1时,邮件已经发送,为2时,邮箱验证失败
	private String name;//联系客户名
	private String title;//联系标题
	private String mail;// 联系客户邮箱
	private String content;// 联系内容

	/*---------------------------方法-------------------------------*/
	/**
	 * @see 发送邮件
	 */
	public String sendEmail() throws Exception {
		System.out.println("sendEmail()");
		System.out.println("name:"+name);		
		System.out.println("title:"+title);
		System.out.println("mail:"+mail);
		System.out.println("content:"+content);
		String toMail="test_nju_edu2@163.com";
		// 发送邮件
		SendEmail.sendMail(toMail, name, title, mail, content);
		System.out.println("SendEmail.sendMail");
		// 邮件发送成功将sentEmail设为1
		flag = 1;
		return "sendEmailsuccess";
	}


	/*---------------------------get、set方法-------------------------------*/
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	


}
