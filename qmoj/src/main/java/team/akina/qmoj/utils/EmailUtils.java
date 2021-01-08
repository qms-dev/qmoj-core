package team.akina.qmoj.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailUtils {

    @Autowired
    private JavaMailSender mailSender;


    @Value("${spring.mail.username}")
    private String form;

    @Async
    public Boolean sendEmail(String email, String code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();// 邮箱消息
            message.setSubject("邮箱测试"); // 邮件主题
            message.setText("您的验证码为:<a href=\"" + code + "\">注册</a>,有效时间一个小时！"); // 文本
            message.setTo(email);//发送给的邮箱
            message.setFrom("a1756095583@163.com"); //自己的邮箱
            mailSender.send(message); // 发送邮件！
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @Async
    public void sendHtmlMail(String email, String code) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(form);
            helper.setTo(form);
            helper.setSubject("邮箱测试");
            helper.setText("这是一条验证注册信息，点击<a href=\"" + code + "\">注册</a>开始注册,有效时间一个小时！", true);
            mailSender.send(message);
            //System.out.println("html格式邮件发送成功");
        } catch (Exception e) {
            //System.out.println("html格式邮件发送失败");
        }

    }
}