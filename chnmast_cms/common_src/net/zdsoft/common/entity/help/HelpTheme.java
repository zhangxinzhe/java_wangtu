/*
 * @(#)HelpTheme.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.help;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.ContentType;
import net.zdsoft.common.enums.YesNoType;

/**
 * 帮助主题
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午10:06:28 $
 */
public class HelpTheme extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    private Long cataLogId;
    /**
     * 主题名称
     */
    private String themeName;
    /**
     * 显示顺序
     */
    private int displayOrder;
    /**
     * 内容类型（1-外部链接 2-编辑内容）
     */
    private ContentType linkType;
    /**
     * 外部链接地址
     */
    private String linkUrl;
    /**
     * 编辑内容文件路径
     */
    private String contentFile;
    /**
     * 是否显示
     */
    private YesNoType isShow = YesNoType.NO;

    /**
     * @return Returns the cataLogId.
     */
    public Long getCataLogId() {
        return cataLogId;
    }

    /**
     * @param cataLogId
     *            The cataLogId to set.
     */
    public void setCataLogId(Long cataLogId) {
        this.cataLogId = cataLogId;
    }

    /**
     * @return Returns the themeName.
     */
    public String getThemeName() {
        return themeName;
    }

    /**
     * @param themeName
     *            The themeName to set.
     */
    public void setThemeName(String themeName) {
        this.themeName = themeName;
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
     * @return Returns the linkType.
     */
    public ContentType getLinkType() {
        return linkType;
    }

    /**
     * @param linkType
     *            The linkType to set.
     */
    public void setLinkType(ContentType linkType) {
        this.linkType = linkType;
    }

    /**
     * @return Returns the linkUrl.
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * @param linkUrl
     *            The linkUrl to set.
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    /**
     * @return Returns the contentFile.
     */
    public String getContentFile() {
        return contentFile;
    }

    /**
     * @param contentFile
     *            The contentFile to set.
     */
    public void setContentFile(String contentFile) {
        this.contentFile = contentFile;
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

}
