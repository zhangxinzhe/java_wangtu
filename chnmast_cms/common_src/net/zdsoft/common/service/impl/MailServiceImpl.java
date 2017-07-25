package net.zdsoft.common.service.impl;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.service.MailService;
import net.zdsoft.common.template.TemplateManager;
import net.zdsoft.common.template.ValueContext;
import net.zdsoft.common.template.ValueContextFactory;

public class MailServiceImpl implements MailService {

    private static final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

    private JavaMailSenderImpl mailSenderImpl;
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 发邮件线程池的大小
     */
    private static final int POOL_SIZE = 20;

    private final ExecutorService sendExecutService = Executors.newFixedThreadPool(POOL_SIZE);

    private static final String charset = "GBK";

    private void initMailSender() {
        if (mailSenderImpl == null) {
            System.out.println("****************邮件服务器初始化 开始*******************");
            mailSenderImpl = new JavaMailSenderImpl();
            mailSenderImpl.setHost(NetstudyConfig.getParam("mail.host"));
            mailSenderImpl.setUsername(NetstudyConfig.getParam("mail.username"));
            mailSenderImpl.setPassword(NetstudyConfig.getParam("mail.password"));

            Properties prop = new Properties();
            prop.setProperty("mail.smtp.auth", "true");
            mailSenderImpl.setJavaMailProperties(prop);
            System.out.println("****************邮件服务器初始化 结束*******************");
        }
    }

    @Override
    public void send(String email, String subject, String content) {
        sendExecutService.submit(new SendExecutor(email, subject, content));
    }

    @Override
    public void send(String[] emails, String subject, String content) {
        for (String email : emails) {
            send(email, subject, content);
        }
    }

    class SendExecutor implements Runnable {

        private final String email;
        private final String subject;
        private String content;

        public SendExecutor(String email, String subject, String content) {
            this.email = email;
            this.subject = subject;
            this.content = content;
        }

        @Override
        public void run() {
            initMailSender();
            MimeMessage message = mailSenderImpl.createMimeMessage();

            MimeMessageHelper helper;
            try {
                helper = new MimeMessageHelper(message, true, charset);

                helper.setFrom(NetstudyConfig.getParam("mail.from"));
                helper.setTo(email);
                helper.setSubject(subject);
                String html = "";
                try {
                    ValueContext ctx = ValueContextFactory.instance().getValueContext();
                    ctx.put("content", content);
                    ctx.put("DOMAIN_FILE", NetstudyConfig.getParam(BaseConstants.DOMAIN_FILE));

                    html = TemplateManager.instance().build(ctx, "mailTemplate.ftl");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                if (StringUtils.isNotEmpty(html)) {
                    content = html;
                }
                helper.setText(content, true);

                mailSenderImpl.send(message);
            }
            catch (MessagingException e) {
                log.error(String.format("发送到%s，主题为%s的邮件失败", email, subject), e);
            }
        }
    }

}
