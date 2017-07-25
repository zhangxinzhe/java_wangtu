/* 
 * @(#)SystemModule.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.system;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.YesNoType;

/**
 * 后台模块表【t_system_module】
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-26 上午11:20:44 $
 */
public class SystemModule extends BaseEntity {

    private static final long serialVersionUID = 7405999014323807997L;

    /**
     * 子系统ID
     */
    private long appId;

    /**
     * 名称
     */
    private String name;
    /**
     * 模块链接（不带域名）
     */
    private String url;
    /**
     * 描述
     */
    private String description;
    /**
     * 模块菜单中是否显示，默认不显示
     */
    private YesNoType isShow = YesNoType.NO;
    /**
     * 父模块id
     */
    private long parentId;
    /**
     * 是否公用模块（所有角色必须有的模块，不能去掉的），默认不是
     */
    private YesNoType isCommon = YesNoType.NO;
    /**
     * 是否功能点（页面中的某一个功能），默认不是
     */
    private YesNoType isOperate = YesNoType.NO;
    /**
     * 排序号
     */
    private long seq;

    // 扩展属性
    private String fullUrl; // 全的链接地址，带域名的url

    /**
     * 获取子系统ID
     * 
     * @return
     */
    public long getAppId() {
        return appId;
    }

    /**
     * 设置子系统ID
     * 
     * @param appId
     */
    public void setAppId(long appId) {
        this.appId = appId;
    }

    /**
     * 获取名称
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取模块链接
     * 
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置模块链接
     * 
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取描述
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取父模块id
     * 
     * @return
     */
    public long getParentId() {
        return parentId;
    }

    /**
     * 设置父模块id
     * 
     * @param parentId
     */
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取排序号
     * 
     * @return
     */
    public long getSeq() {
        return seq;
    }

    /**
     * 设置排序号
     * 
     * @param seq
     */
    public void setSeq(long seq) {
        this.seq = seq;
    }

    /**
     * 获取模块菜单中是否显示
     * 
     * @return
     */
    public YesNoType getIsShow() {
        return isShow;
    }

    /**
     * 设置模块菜单中是否显示
     * 
     * @param isShow
     */
    public void setIsShow(YesNoType isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取是否公用模块
     * 
     * @return
     */
    public YesNoType getIsCommon() {
        return isCommon;
    }

    /**
     * 获取是否功能点
     * 
     * @return
     */
    public YesNoType getIsOperate() {
        return isOperate;
    }

    /**
     * 设置是否公用模块
     * 
     * @param isCommon
     */
    public void setIsCommon(YesNoType isCommon) {
        this.isCommon = isCommon;
    }

    /**
     * 设置是否功能点
     * 
     * @param isOperate
     */
    public void setIsOperate(YesNoType isOperate) {
        this.isOperate = isOperate;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

}
