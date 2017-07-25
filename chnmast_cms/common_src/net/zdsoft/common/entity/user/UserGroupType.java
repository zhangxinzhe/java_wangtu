/*
 * @(#)UserGroupType.java    Created on 2016年6月13日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.user;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年6月13日 上午10:24:51 $
 */
public class UserGroupType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String title;

    /**
     * 是否可以删除
     */
    private YesNoType isCanDel;

    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Returns the isCanDel.
     */
    public YesNoType getIsCanDel() {
        return isCanDel;
    }

    /**
     * @param isCanDel
     *            The isCanDel to set.
     */
    public void setIsCanDel(YesNoType isCanDel) {
        this.isCanDel = isCanDel;
    }

}
