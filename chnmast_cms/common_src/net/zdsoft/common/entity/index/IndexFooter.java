/*
 * @(#)IndexFoot.java    Created on 2016年11月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.index;

import java.io.Serializable;

import net.zdsoft.common.enums.ContentType;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author mengzs
 * @version $Revision: 1.0 $, $Date: 2016年11月25日 上午11:37:44 $
 */
public class IndexFooter implements Serializable {
    private static final long serialVersionUID = -6351531193389850579L;
    private long id;
    /**
     * 链接名
     */
    private String title;
    /**
     * 链接地址
     */
    private String linkUrl;
    /**
     * 是否显示
     */
    private YesNoType isShow;
    /**
     * 上传文件路径
     */
    private String localFile;

    /**
     * 链接类型
     */
    private ContentType contentType;
    /**
     * 显示顺序
     */
    private int displayOrder;

    /**************************** 扩展属性 *************************/
    // 内容
    private String content;

    /**
     * @return Returns the id.
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(long id) {
        this.id = id;
    }

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
     * @return Returns the localFile.
     */
    public String getLocalFile() {
        return localFile;
    }

    /**
     * @param localFile
     *            The localFile to set.
     */
    public void setLocalFile(String localFile) {
        this.localFile = localFile;
    }

    /**
     * @return Returns the contentType.
     */
    public ContentType getContentType() {
        return contentType;
    }

    /**
     * @param contentType
     *            The contentType to set.
     */
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    /**
     * @return Returns the content.
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content The content to set.
     */
    public void setContent(String content) {
        this.content = content;
    }

}
