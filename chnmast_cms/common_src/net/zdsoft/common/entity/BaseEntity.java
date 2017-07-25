/* 
 * @(#)BaseEntity.java    Created on 2013-8-23
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity;

import java.io.Serializable;

/**
 * Entity基类
 * 
 * @author ll
 * @version $Revision: 1.0 $, $Date: 2013-8-23 下午1:22:32 $
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 6151514505966195605L;

    private long id;// 主键ID

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
