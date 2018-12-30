package cn.com.tj.byhy.util;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import java.util.Date;
import java.util.Properties;

public class SendEmail {
	 public static void sendMail(String toMail,String name,String title, String mail,String content) throws MessagingException {
	        // 配置发送邮件的环境属性
	        final Properties props = new Properties();
	        /*
	         * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
	         * mail.user / mail.from
	         */
	        // 表示SMTP发送邮件，需要进行身份验证
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.163.com");
	        // 发件人的账号
	        props.put("mail.user", "test_nju_edu@163.com");
	        // 访问SMTP服务时需要提供的密码
	        props.put("mail.password", "wbb123456");

	        // 构建授权信息，用于进行SMTP进行身份验证
	        Authenticator authenticator = new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                // 用户名、密码
	                String userName = props.getProperty("mail.user");
	                String password = props.getProperty("mail.password");
	                return new PasswordAuthentication(userName, password);
	            }
	        };
	        // 使用环境属性和授权信息，创建邮件会话
	        Session mailSession = Session.getInstance(props, authenticator);
	        // 创建邮件消息
	        MimeMessage message = new MimeMessage(mailSession);
	        // 设置发件人
	        InternetAddress form = new InternetAddress(
	                props.getProperty("mail.user"));
	        message.setFrom(form);

	        // 设置收件人
	        InternetAddress to = new InternetAddress(toMail);
	        message.setRecipient(RecipientType.TO, to);

	        // 设置抄送
//	        InternetAddress cc = new InternetAddress("1641751910@qq.com");
//	        message.setRecipient(RecipientType.CC, cc);

	        // 设置密送，其他的收件人不能看到密送的邮件地址
//	        InternetAddress bcc = new InternetAddress("aaaaa@163.com");
//	        message.setRecipient(RecipientType.CC, bcc);

	        // 设置邮件标题
	        message.setSubject("客户反馈信息");

	        // 设置邮件的内容体
	      //设置信件内容
			String con="";
			con = con +"客户姓名："+name+"\n";
			con = con +"标题："+title +"\n";
			con = con +"联系邮箱："+mail +"\n";
			con = con +"联系内容："+content +"\n";
	        message.setContent("客户反馈信息:"+con+"", "text/html;charset=UTF-8");
	        System.out.println("客户反馈信息:"+con);

	        // 发送邮件
	        Transport.send(message);
	    }
	 public static void sendMailmessageToUser(String toMail,String content) throws MessagingException {
	        // 配置发送邮件的环境属性
	        final Properties props = new Properties();
	        /*
	         * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
	         * mail.user / mail.from
	         */
	        // 表示SMTP发送邮件，需要进行身份验证
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.163.com");
	        // 发件人的账号
	        props.put("mail.user", "test_nju_edu@163.com");
	        // 访问SMTP服务时需要提供的密码
	        props.put("mail.password", "wbb123456");

	        // 构建授权信息，用于进行SMTP进行身份验证
	        Authenticator authenticator = new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                // 用户名、密码
	                String userName = props.getProperty("mail.user");
	                String password = props.getProperty("mail.password");
	                return new PasswordAuthentication(userName, password);
	            }
	        };
	        // 使用环境属性和授权信息，创建邮件会话
	        Session mailSession = Session.getInstance(props, authenticator);
	        // 创建邮件消息
	        MimeMessage message = new MimeMessage(mailSession);
	        // 设置发件人
	        InternetAddress form = new InternetAddress(
	                props.getProperty("mail.user"));
	        message.setFrom(form);

	        // 设置收件人
	        InternetAddress to = new InternetAddress(toMail);
	        message.setRecipient(RecipientType.TO, to);

	        // 设置抄送
//	        InternetAddress cc = new InternetAddress("1641751910@qq.com");
//	        message.setRecipient(RecipientType.CC, cc);

	        // 设置密送，其他的收件人不能看到密送的邮件地址
//	        InternetAddress bcc = new InternetAddress("aaaaa@163.com");
//	        message.setRecipient(RecipientType.CC, bcc);

	        // 设置邮件标题
	        message.setSubject("天津博宇鸿雁文化传媒有限公司:订单通知信息");

	        // 设置邮件的内容体
	      //设置信件内容
			String con="";
			con = con +"联系内容："+content +"\n";
	        message.setContent("订单通知信息:"+con+"", "text/html;charset=UTF-8");
	        System.out.println("订单通知信息:"+con);

	        // 发送邮件
	        Transport.send(message);
	    }


	public static void sendMai( String toMail,String name,String title, String mail,String content) throws Exception {
		Properties props = new Properties(); //可以加载一个配置文件
		System.out.println("sendMail");
		// 使用smtp：简单邮件传输协议
		props.put("mail.smtp.host", "smtp.qq.com"); //存储发送邮件服务器的信息
		props.put("mail.smtp.auth", "true");//同时通过验证
		System.out.println("sendMail1");

		Session session = Session.getInstance(props);//根据属性新建一个邮件会话
		
		System.out.println("sendMail2");
		MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象
		System.out.println("sendMail3");
		message.setFrom("test_nju_edu@163.com");//设置发件人的地址
		System.out.println("sendMail4");
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));//设置收件人,并设置其接收类型为TO
		System.out.println("sendMail5");
		message.setSubject("天津博宇鸿雁文化传媒有限公司:客户反馈信息");//设置标题
		System.out.println("sendMail6");
		//设置信件内容
		String con="";
		con = con +"客户姓名："+name+"\n";
		con = con +"标题："+title +"\n";
		con = con +"联系邮箱："+title +"\n";
		con = con +"联系内容："+title +"\n";
		System.out.println("sendMail7");
		
		message.setContent("客户反馈信息:"+con+"", "text/html;charset=gbk"); //发送HTML邮件，内容样式比较丰富
		System.out.println("sendMail8");
		message.setSentDate(new Date());//设置发信时间
		System.out.println("sendMail9");
		message.saveChanges();//存储邮件信息
		System.out.println("sendMail10");

		//发送邮件
		Transport transport = session.getTransport();
		System.out.println("sendMail11");
		//链接用户名和密码
		transport.connect("test_nju_edu@163.com", "wbb123456");
		System.out.println("链接用户名和密码");
		transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
		transport.close();
	}
}