package net.zdsoft.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import net.zdsoft.common.config.NetstudyConfig;

public class MailUtil {

    private static MailUtil mailUtil = new MailUtil();

    private static final Logger log = LoggerFactory.getLogger(MailUtil.class);

    private JavaMailSenderImpl mailSenderImpl;

    // 邮件host
    private static final String MAIL_HOST = "mail.host";
    // 邮件的用户名
    private static final String MAIL_USERNAME = "mail.username";
    // 邮件的密码
    private static final String MAIL_PASSWORD = "mail.password";
    // 邮件的发送方
    private static final String MAIL_FROM = "mail.from";
    // 要过滤的邮箱
    private static final String MAIL_FILTER = "mail.filter";

    public static MailUtil instance() {
        return mailUtil;
    }

    /**
     * 邮箱初始化
     */
    private void initMailSender() {
        if (mailSenderImpl == null) {
            mailSenderImpl = new JavaMailSenderImpl();
            mailSenderImpl.setHost(NetstudyConfig.getParam(MAIL_HOST));
            mailSenderImpl.setUsername(NetstudyConfig.getParam(MAIL_USERNAME));
            String pwd = NetstudyConfig.getParam(MAIL_PASSWORD);
            try {
                DES des = new DES();
                pwd = des.decrypt(pwd);
            }
            catch (Exception e) {
                log.error("邮件密码解密错误！", e);
            }
            mailSenderImpl.setPassword(pwd);

            Properties prop = new Properties();
            prop.setProperty("mail.smtp.auth", "true");
            mailSenderImpl.setJavaMailProperties(prop);
        }
    }

    /**
     * 发邮件线程池的大小
     */
    private static final int POOL_SIZE = 20;

    private final ExecutorService sendExecutService = Executors.newFixedThreadPool(POOL_SIZE);

    private static final String charset = "GBK";

    /**
     * 发送邮件
     *
     * @param emails
     *            接收方的邮箱地址
     * @param subject
     *            发送主题
     * @param content
     *            发送内容
     */
    public void send(String email, String subject, String content, String person) {
        sendExecutService.submit(new SendExecutor(new String[] { email }, subject, content, person));
    }

    /**
     * 发送邮件
     *
     * @param emails
     *            接收方的邮箱地址
     * @param subject
     *            发送主题
     * @param content
     *            发送内容
     */
    public void send(String[] emails, String subject, String content, String person) {
        sendExecutService.submit(new SendExecutor(emails, subject, content, person));
    }

    class SendExecutor implements Runnable {

        private final String[] emails;
        private final String subject;
        private final String content;
        private final String person;

        public SendExecutor(String[] emails, String subject, String content, String person) {
            this.emails = emails;
            this.subject = subject;
            this.content = content;
            this.person = person;
        }

        @Override
        public void run() {
            initMailSender();
            MimeMessage message = mailSenderImpl.createMimeMessage();

            MimeMessageHelper helper;
            try {
                helper = new MimeMessageHelper(message, true, charset);

                try {
                    helper.setFrom(NetstudyConfig.getParam(MAIL_FROM), person);
                }
                catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                helper.setTo(emails);
                helper.setSubject(subject);
                helper.setText(content, true);
                mailSenderImpl.send(message);
            }
            catch (MessagingException e) {
                log.error(String.format("发送到%s，主题为%s的邮件失败", Arrays.toString(emails), subject), e);
            }
        }
    }

    /**
     * 检测邮箱是否是要过滤的邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkFilterMail(String email) {
        String[] emails = NetstudyConfig.getParam(MAIL_FILTER).split(",");
        email = email.trim();
        for (String mail : emails) {
            mail = mail.trim();
            if (email.equals(mail)) {
                return true;
            }
        }
        return false;
    }

}
