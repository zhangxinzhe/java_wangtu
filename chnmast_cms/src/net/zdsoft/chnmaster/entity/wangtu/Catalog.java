/*
 * @(#)Catalog.java    Created on 2017年7月5日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月5日 上午11:28:44 $
 */
public class Catalog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String cname;
    private YesNoType isDelete;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public YesNoType getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(YesNoType isDelete) {
        this.isDelete = isDelete;
    }

}
