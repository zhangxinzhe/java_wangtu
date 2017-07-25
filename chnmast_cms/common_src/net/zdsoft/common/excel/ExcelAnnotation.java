/* 
 * @(#)ExcelAnnotation.java    Created on 2010-7-27
 * Copyright (c) 2010 ZDSoft Networks, Inc. All rights reserved.
 * $Id: ExcelAnnotation.java 17843 2010-07-28 06:38:29Z wangzb $
 */
package net.zdsoft.common.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义的Excel文件导入导出 的Annotation
 * 
 * 任何类中含有设置Annotation属性才会被导出和导入
 * 
 * @author wangzb
 * @version $Revision: 17843 $, $Date: 2010-07-28 14:38:29 +0800 (星期三, 28 七月 2010) $
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelAnnotation {
    /**
     * Excel文件导出时要显示的数据字段标题的名称
     * 
     * @return 标题的名称(默认为 标题)
     */

    public String exportTitleName() default "标题";

    /**
     * Excel文件导出导入时要显示的数据字段为Date类型时的显示格式
     * 
     * @return 日期时间的格式(默认为 yyyy-MM-dd HH:mm:ss)
     */

    public String dateTimeFormat() default "yyyy-MM-dd HH:mm:ss";

    /**
     * Excel文件导入时该标题对应的字段的最大长度(字节)
     * 
     * @return 该标题对应的字段的最大长度(字节)
     */

    public int maxLength() default 10000;

}
