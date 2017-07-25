package net.zdsoft.common.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;

import freemarker.template.SimpleDate;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ValueContextFactory {

    private static ValueContextFactory instance = new ValueContextFactory();

    private static final String DOMAIN_KEY = "domain";
    private static final String SITE_NAME = "sitename";

    private static Map domains = new HashMap();

    private static TimeMillionTemplateMethod timeMillion = new TimeMillionTemplateMethod();
    private static String TIME_MILLION_METHOD = "time_million";

    static {
        domains.put(BaseConstants.DOMAIN_HOME, NetstudyConfig.getParam(BaseConstants.DOMAIN_HOME));
        domains.put(BaseConstants.DOMAIN_CMS, NetstudyConfig.getParam(BaseConstants.DOMAIN_CMS));
    }

    public static ValueContextFactory instance() {
        return instance;
    }

    /**
     * 创建一个ValueContext
     *
     * @return
     */
    public synchronized ValueContext getValueContext() {
        ValueContext context = new DefaultValueContext();

        context.put(DOMAIN_KEY, domains);
        context.put(TIME_MILLION_METHOD, timeMillion);
        context.put(SITE_NAME, NetstudyConfig.getParam(BaseConstants.SITE_HOME_SITE_NAME));

        return context;
    }

    class DefaultValueContext implements ValueContext {
        private final Map root = new HashMap();

        @Override
        public Object get(String key) {
            return root.get(key);
        }

        @Override
        public void put(String key, Object value) {
            root.put(key, value);
        }

    }

    /**
     * 将date类型转化为数字的静态模板方法 该方法名为time_million，该方法只接受一个date类型的参数
     *
     * @author fangb
     *
     */
    static class TimeMillionTemplateMethod implements TemplateMethodModelEx {

        @Override
        public Object exec(List arg0) throws TemplateModelException {
            SimpleDate date = (SimpleDate) arg0.get(0);
            return date.getAsDate().getTime();
        }

    }
}
