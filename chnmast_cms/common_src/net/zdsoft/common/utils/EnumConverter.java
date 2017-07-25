package net.zdsoft.common.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ognl.DefaultTypeConverter;

/**
 * 枚举类型的转换器
 *
 * <p>
 * 可以将request中提交的枚举类型字符串值参数转换为对应的枚举类型。
 *
 * <p>
 * 例如有参数为<code>type=YES</code>，那么action中的对应的type属性即为 YesNoType类型，经过转换，action的属性type的值就为YesNoType.YES。
 *
 * <p>
 * 这样做的目的是涉及到枚举的东西永远告别int的困扰。
 *
 * @author fangb
 * @date 2010-3-4 上午07:13:51
 */
public class EnumConverter extends DefaultTypeConverter {

    private static final Logger log = LoggerFactory.getLogger(EnumConverter.class);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Object convertValue(Map ctx, Object o, Class toType) {
        if (toType == String.class) {
            return o.toString();
        }
        else {
            String value = ((String[]) o)[0];
            try {
                if (StringUtils.isNotBlank(value)) {
                    return Enum.valueOf(toType, value);
                }
                return null;
            }
            catch (IllegalArgumentException e) {
                log.error("无法得到类型为：{}，值为：{}的枚举类型。", new Object[] { toType.getName(), value }, e);
                return null;
            }
        }
    }
}
