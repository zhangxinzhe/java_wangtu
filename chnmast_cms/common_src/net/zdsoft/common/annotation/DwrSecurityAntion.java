package net.zdsoft.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * dwr安全性过滤辅助注解
 * @author liyixi
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DwrSecurityAntion {
    
    //是否忽略该调用,默认过滤该调用
    public boolean ignore() default false;
    
    //可忽略的参数，默认没有忽略值，此处的值为参数的位置下标，从0开始
    public int[] index() default {};
    
    //调用方式
    public int model() default -1;
}
