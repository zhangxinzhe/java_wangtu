/* 
 * @(#)SystemVersion.java    Created on 2013-8-9
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.system;

import java.util.Date;

/**
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2013-8-9 下午4:15:37 $
 */
public class SystemVersion {

    private long id;
    private String edition; // 版本名称（标准版、XX定制版）
    private String client; // 哪个用户需求修改
    private String description; // 修改概述
    private Date upgradeDate; // 更新时间
    private String version; // 版本号
    private String build; // 发布号
    private String descFile; // 修改描述文件（file目录：sysfile/upgrade/{version}-{build}.txt）

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpgradeDate() {
        return upgradeDate;
    }

    public void setUpgradeDate(Date upgradeDate) {
        this.upgradeDate = upgradeDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getDescFile() {
        return descFile;
    }

    public void setDescFile(String descFile) {
        this.descFile = descFile;
    }

}
