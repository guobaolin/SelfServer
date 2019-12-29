package com.gbl;

import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by guobaolin on 2019/11/19.
 */
public class SendMailClientTest {

    // 发件人账号
    public static final String MY_EMAIL_ACCOUNT = "guobaolinmail@163.com";
    // 发件人密码
    public static final String MY_EMAIL_PASSWORD = "guobaolin163";

    // SMTP服务器(这里用的163 SMTP服务器)
    public static final String EMAIL_163_SMTP_HOST = "smtp.163.com";
    public static final String SMTP_163_PORT = "25";

    // 收件人
    public static final String RECEIVE_EMAIL_ACCOUNT = "1245682673@qq.com";

    @Test
    public void testSendMail() throws MessagingException {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", EMAIL_163_SMTP_HOST);
        properties.setProperty("mail.smtp.port", SMTP_163_PORT);
        properties.setProperty("mail.smtp.socketFactory.port", SMTP_163_PORT);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "SSL_FACTORY");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MY_EMAIL_ACCOUNT, MY_EMAIL_PASSWORD);
            }
        });
        session.setDebug(true);
        System.out.println("创建邮箱");
        MimeMessage message = new MimeMessage(session);
        // 发件人
        message.setFrom(new InternetAddress(MY_EMAIL_ACCOUNT));
        // 收件人和抄送人
        message.setRecipients(Message.RecipientType.TO, RECEIVE_EMAIL_ACCOUNT);
        message.setRecipients(Message.RecipientType.CC, MY_EMAIL_ACCOUNT);


        // 内容(这个内容还不能乱写,有可能会被SMTP拒绝掉;多试几次吧)
        message.setSubject("包裹");
        message.setContent("<h1>郭总，您好;<br/>您的包裹在前台</h1>", "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        System.out.println("准备发送");
        Transport.send(message);
    }


}
