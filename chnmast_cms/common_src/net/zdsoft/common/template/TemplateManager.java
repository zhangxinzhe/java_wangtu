package net.zdsoft.common.template;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zdsoft.common.upload.FilePathBuilder;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateModel;

/**
 *
 * @author fangb
 *
 */
public class TemplateManager {
    private static Logger log = LoggerFactory.getLogger(TemplateManager.class);

    private static final String ENCODE = "UTF-8";

    private static TemplateManager manager = new TemplateManager();

    private final Configuration configuration;

    public static TemplateManager instance() {
        return manager;
    }

    public TemplateManager() {
        configuration = new Configuration();
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        if (System.getProperty("os.name").startsWith("Windows")) {
            filePath = filePath.substring(1);
        }
        filePath += "conf" + FilePathBuilder.SEPERATOR + "template" + FilePathBuilder.SEPERATOR;
        try {
            // configuration.setDirectoryForTemplateLoading(new File(
            // "\\\\192.168.0.156\\0_155_eis3_store\\store\\netstudy5\\" + UploadUtil.getTemplateFolder()));

            configuration.setDirectoryForTemplateLoading(new File(filePath));
        }
        catch (Exception e) {
            log.error("加载模板配置失败，错误文件目录：{}", filePath, e);
        }

        configuration.setObjectWrapper(new DefaultObjectWrapper());
    }

    /**
     * 处理模板
     *
     * @param context
     * @param templateName
     *            模板文件的名字，在该系统中，模板统一放在template目录下， 所以如果模板名为test.ftl,则该参数为test.ftl。如果该模板在template的某个子目录下，例如：email
     *            则，该参数应为email//test.ftl
     *
     * @return 返回处理后的内容
     */
    public String build(ValueContext context, String templateName) throws RuntimeException {
        StringWriter writer = new StringWriter();

        Template template = null;

        try {
            template = configuration.getTemplate(templateName, ENCODE);

            template.process(createModel(context), writer);

        }
        catch (Exception e) {
            log.error(String.format("处理模板[%s]失败", templateName), e);
            throw new RuntimeException(e);
        }
        finally {
            writer.flush();
            try {
                writer.close();
            }
            catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }

        return writer.toString();
    }

    private TemplateModel createModel(ValueContext context) {
        return new ValueContextHashModel(context, configuration.getObjectWrapper());
    }
}
