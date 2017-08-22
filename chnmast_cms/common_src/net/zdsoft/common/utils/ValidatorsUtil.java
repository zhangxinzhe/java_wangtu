package net.zdsoft.common.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import net.zdsoft.keel.util.Validators;

/**
 * 数据验证工具类（参照base.js中Validator控件的简单实现）
 *
 * @author liyixi
 *
 */
public class ValidatorsUtil extends Validators {

    private static final String REG_URL = "^(http|https|ftp)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";

    private static final String CACHE_SERVER = "^([A-Za-z0-9][A-Za-z0-9_-]*)(\\.[A-Za-z0-9][A-Za-z0-9_-]*)+(\\:+?(\\d+))$";

    private static Map<String, String> registerMap = new HashMap<String, String>();

    private static Map<String, String> tipMap = new HashMap<String, String>();

    static {
        registerMap.put("validate-url", "validateUrl");
        registerMap.put("config-max-length", "configMaxLength");
        registerMap.put("config-cache-server", "configCacheServer");
        registerMap.put("config-file-path", "configFilePath");
        registerMap.put("config-int-range", "configIntRange");
        registerMap.put("config-pic-type-value", "configPicTypeValue");
        registerMap.put("validate-number", "validateNumber");
        registerMap.put("required", "required");

        tipMap.put("validate-url", "请输入有效的URL地址");
        tipMap.put("config-max-length", "最大长度为%s,当前长度为%s");
        tipMap.put("config-cache-server", "格式如 server.cache.netstudy5:11211");
        tipMap.put("config-file-path", "要以/结尾，如：/opt/data/netstudy/");
        tipMap.put("config-int-range", "输入值应该为 %s 至 %s 的整数");
        tipMap.put("config-pic-type-value", "格式如 xx:小学;cz:初中");
        tipMap.put("validate-number", "请输入有效的数字");
        tipMap.put("required", "请输入值");
    }

    private ValidatorsUtil() {
    };

    /**
     * 根据验证规则CODE验证VALUE值
     *
     * @param code
     * @param value
     * @return null：验证通过，反之为通过，且返回值为提示消息
     * @throws Exception
     */
    public static String validator(String code, String value) throws Exception {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        String[] temp = StringUtils.split(code, "-");
        String key = temp[0];
        List<Integer> args = null;
        for (int i = 1; i < temp.length; i++) {
            String t = temp[i];
            if (isNumber(t)) {
                if (args == null) {
                    args = new ArrayList<Integer>();
                }
                args.add(Integer.parseInt(t));
            }
            else {
                key += ("-" + t);
            }
        }
        String methodName = registerMap.get(key);
        if (StringUtils.isBlank(methodName)) {
            throw new Exception("未配置的验证项！");
        }
        Method validatorMe = ValidatorsUtil.class.getMethod(methodName,
                new Class[] { String.class, String.class, List.class });
        return (String) validatorMe.invoke(null, new Object[] { key, value == null ? "" : value, args });
    }

    /**
     * 格式化消息i
     *
     * @param code
     * @param args
     * @return
     */
    private static final String format(final String code, List<Integer> args) {
        String template = tipMap.get(code) == null ? "" : tipMap.get(code);
        if (args != null) {
            for (int arg : args) {
                template = template.replaceFirst("%s", String.valueOf(arg));
            }
        }
        return template;
    }

    public static String validateUrl(final String code, final String value, List<Integer> args) {
        boolean result = value.matches(REG_URL);
        String msg = null;
        if (!result) {
            msg = format(code, args);
        }
        return msg;
    }

    public static String configMaxLength(final String code, final String value, List<Integer> args) {
        String msg = null;
        int length = 0;
        if (StringUtils.isNotBlank(value)) {
            for (int i = 0; i < value.length(); i++) {
                char c = value.charAt(i);
                if (c > 255) {
                    length += 2;
                }
                else {
                    length++;
                }
            }
        }
        int maxLen = args.get(0);
        if (length > maxLen) {
            args.add(length);
            msg = format(code, args);
        }
        return msg;
    }

    public static String configCacheServer(final String code, final String value, List<Integer> args) {
        return value.matches(CACHE_SERVER) ? null : format(code, args);
    }

    public static String configFilePath(final String code, final String value, List<Integer> args) {
        return value.matches("(.*)\\/$") ? null : format(code, args);
    }

    public static String configIntRange(final String code, final String value, List<Integer> args) {
        if (Validators.isNumber(value)) {
            int val = Integer.parseInt(value);
            return (val >= args.get(0) && val <= args.get(1)) ? null : format(code, args);
        }
        else {
            return format(code, args);
        }
    }

    public static String configPicTypeValue(final String code, final String value, List<Integer> args) {
        return value.matches("^([\\s\\S]+:[\u4e00-\u9fa5]+)(;[\\s\\S]+:[\u4e00-\u9fa5]+)?$") ? null
                : format(code, args);
    }

    public static String validateNumber(final String code, final String value, List<Integer> args) {
        return isNumber(value) ? null : format(code, args);
    }

    public static String required(final String code, final String value, List<Integer> args) {
        return StringUtils.isNotBlank(value) ? null : format(code, args);
    }
    //
    // public static void main(String[] args) throws Exception {
    // System.out.println(CmsValidators.validator("validate-url", "http://www.netstudy5.dev"));
    // System.out.println(CmsValidators.validator("config-max-length-8", "123课后网"));
    // System.out.println(CmsValidators.validator("config-cache-server", "server.cache.netstudy5:11211"));
    // System.out.println(CmsValidators.validator("config-file-path", "/opt/data/netstudy/"));
    // System.out.println(CmsValidators.validator("config-int-range-0-1", "5"));
    // System.out.println(CmsValidators.validator("config-pic-type-value", "cz:初中;gz:高中"));
    // System.out.println(CmsValidators.validator("validate-number", "1f"));
    // System.out.println(CmsValidators.validator("required", ""));
    // }

}
