/*
 * @(#)HelpCatalog.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.help;

import java.util.List;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.YesNoType;

/**
 * 帮助分类
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午10:02:55 $
 */
public class HelpCatalog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 分类名称
     */
    private String catalogName;
    /**
     * 父级主题ID（顶级时为0）
     */
    private long parentId;
    /**
     * 显示顺序
     */
    private int displayOrder;
    /**
     * 是否显示
     */
    private YesNoType isShow = YesNoType.NO;

    //////////////////////////////////// 辅助字段////////////////////////////////
    private List<HelpCatalog> childCatalog;// 子节点

    /**
     * @return Returns the catalogName.
     */
    public String getCatalogName() {
        return catalogName;
    }

    /**
     * @param catalogName
     *            The catalogName to set.
     */
    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    /**
     * @return Returns the parentId.
     */
    public long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     *            The parentId to set.
     */
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return Returns the displayOrder.
     */
    public int getDisplayOrder() {
        return displayOrder;
    }

    /**
     * @param displayOrder
     *            The displayOrder to set.
     */
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * @return Returns the isShow.
     */
    public YesNoType getIsShow() {
        return isShow;
    }

    /**
     * @param isShow
     *            The isShow to set.
     */
    public void setIsShow(YesNoType isShow) {
        this.isShow = isShow;
    }

    public List<HelpCatalog> getChildCatalog() {
        return childCatalog;
    }

    public void setChildCatalog(List<HelpCatalog> childCatalog) {
        this.childCatalog = childCatalog;
    }

}
