/*
 * @(#)HifiHomeColmn.java    Created on 2016年9月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.hifi.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 首页栏目
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年9月22日 下午4:12:35 $
 */
public class HifiHomeColumn implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 栏目id
     */
    private int menuId;
    /**
     * 栏目类型（暂时没发现使用情况）
     */
    private String menuType;
    /**
     * 栏目名
     */
    private String menuName;
    /**
     * 排序号
     */
    private int displayOrder;
    /**
     * 栏目内容列表
     */
    private List<HifiHomeColumnContent> contentList;

    /**
     * @return Returns the menuId.
     */
    public int getMenuId() {
        return menuId;
    }

    /**
     * @param menuId
     *            The menuId to set.
     */
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    /**
     * @return Returns the menuName.
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName
     *            The menuName to set.
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
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
     * @return Returns the contentList.
     */
    public List<HifiHomeColumnContent> getContentList() {
        return contentList;
    }

    /**
     * @param contentList
     *            The contentList to set.
     */
    public void setContentList(List<HifiHomeColumnContent> contentList) {
        this.contentList = contentList;
    }

    /**
     * @return Returns the menuType.
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * @param menuType
     *            The menuType to set.
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

}
