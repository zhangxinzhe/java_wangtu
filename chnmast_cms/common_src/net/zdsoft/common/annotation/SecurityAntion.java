package net.zdsoft.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * structs安全拦截器辅助注解
 * 注：VisitorType.AJAX 将短路target字段
 * @author liyixi
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SecurityAntion {
    
    //调用类型，包括表单提交和AJAX异步提交
    public enum VisitorType {SUBMIT, AJAX};
    
    //验证未通过返回Result
    public String target() default "subError";
    
    //可忽略的参数名称
    public String[] params() default {};
    
    //调用类型
    public VisitorType type() default VisitorType.SUBMIT;
    
    //调用方式
    public int model() default -1;
}
