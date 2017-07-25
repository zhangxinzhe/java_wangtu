/*
 * @(#)Node.java    Created on 2015-3-19
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.common;

import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.freemarker.AppSetting;

/**
 * ztree节点
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015-3-19 下午1:48:06 $
 */
public class Node {
    private String id; // 节点id
    private String parentId; // 父节点id
    private String name; // 节点显示的名称
    private Object object; // 节点对象信息
    private Boolean isParent; // 记录是否为父节点
    private Boolean checked; // 节点是否选中
    private Boolean open; // 设置节点是否打开（针对父节点）

    // 以下三个为自定义图标。如果未set,取ztree默认图标
    private String iconClose;// 闭合节点图标 路径
    private String iconOpen;// 展开节点图标 路径
    private String icon;// 叶子节点图表 路径

    // 不常用参数
    private Boolean isHidden; // 是否隐藏节点
    private Boolean chkDisabled; // 设置节点是否禁用

    String cms = AppSetting.getInstance().getParam(BaseConstants.DOMAIN_CMS);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getChkDisabled() {
        return chkDisabled;
    }

    public void setChkDisabled(Boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getIconClose() {
        iconClose = cms + "/images/tree/close.png";
        return iconClose;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    public String getIconOpen() {
        iconOpen = cms + "/images/tree/open.png";
        return iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    public String getIcon() {
        icon = cms + "/images/tree/left.png";
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

}
