/* 
 * @(#)SystemApp.java    Created on 2015-4-13
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.system;

import net.zdsoft.common.entity.BaseEntity;

/**
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2015-4-13 下午3:43:15 $
 */
public class SystemApp extends BaseEntity {
    private static final long serialVersionUID = -472363802533405076L;

    private String appName; // 子系统名称
    private String appDes; // 描述说明
    private String indexUrl; // 后台首页地址
    private int isUsing; // 是否启用（0未启用，1启用）

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDes() {
        return appDes;
    }

    public void setAppDes(String appDes) {
        this.appDes = appDes;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }

    public int getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(int isUsing) {
        this.isUsing = isUsing;
    }

}
