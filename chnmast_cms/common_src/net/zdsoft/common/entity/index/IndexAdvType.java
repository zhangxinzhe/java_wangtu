/*
 * @(#)IndexAdvType.java    Created on 2015年12月28日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.index;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.FieldType;

/**
 * T_INDEX_ADV_TYPE
 *
 * @author hanqr
 *
 * @version $Revision: 1.0 $, $Date: 2015年12月28日 下午2:27:15 $
 */
public class IndexAdvType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 分类名称（如伴奏音频：流行音乐、古典音乐...）
     */
    private String title;

    /**
     * 栏目类型（0轮播图片，1精彩视频，2伴奏音频）
     */
    private FieldType fieldType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

}
