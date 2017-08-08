/*
 * @(#)IndexInfo.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.index;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.ContentType;
import net.zdsoft.common.enums.InfoType;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.common.utils.NetstudyFileUtils;

/**
 * 公告、新闻、热点
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午10:11:43 $
 */
public class IndexInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;
    /**
     * 信息类型（1公告、2新闻、3热点追踪、4视频回顾）
     */
    private InfoType infoType;
    /**
     * 链接类型（1外部链接、2维护内容）
     */
    private ContentType contentType;
    /**
     * 内容缩写
     */
    private String infoShort;
    /**
     * 链接地址
     */
    private String linkUrl;
    /**
     * 文件路径
     */
    private String localFile;
    /**
     * 是否显示
     */
    private YesNoType isShow;
    /**
     * 是否高亮
     */
    private YesNoType isLight;
    /**
     * 置新天数
     */
    private int newDays;
    /**
     * 排序号
     */
    private int orderNo;
    /**
     * 来源
     */
    private String comeFrom;
    /**
     * 来源链接
     */
    private String comeUrl;
    /**
     * 日期
     */
    private Date infoDate;
    /**
     * 编辑
     */
    private String editor;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 是否紧急
     */
    private YesNoType isUrgent;

    /**
     * 活动id
     */
    private long competeId;

    /**
     * 分享图片
     */
    private String sharePicFile;

    private String content;// 内容
    private IndexInfo preInfo;// 上一条
    private IndexInfo nextInfo;// 下一条

    /**
     * 压缩图片URL
     */
    private String smallPic;

    public long getCompeteId() {
        return competeId;
    }

    public void setCompeteId(long competeId) {
        this.competeId = competeId;
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
     * @return Returns the infoType.
     */
    public InfoType getInfoType() {
        return infoType;
    }

    /**
     * @param infoType
     *            The infoType to set.
     */
    public void setInfoType(InfoType infoType) {
        this.infoType = infoType;
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
     * @return Returns the isLight.
     */
    public YesNoType getIsLight() {
        return isLight;
    }

    /**
     * @param isLight
     *            The isLight to set.
     */
    public void setIsLight(YesNoType isLight) {
        this.isLight = isLight;
    }

    /**
     * @return Returns the newDays.
     */
    public int getNewDays() {
        return newDays;
    }

    /**
     * @param newDays
     *            The newDays to set.
     */
    public void setNewDays(int newDays) {
        this.newDays = newDays;
    }

    /**
     * @return Returns the orderNo.
     */
    public int getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo
     *            The orderNo to set.
     */
    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return Returns the createDate.
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     *            The createDate to set.
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return Returns the content.
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            The content to set.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return Returns the preInfo.
     */
    public IndexInfo getPreInfo() {
        return preInfo;
    }

    /**
     * @param preInfo
     *            The preInfo to set.
     */
    public void setPreInfo(IndexInfo preInfo) {
        this.preInfo = preInfo;
    }

    /**
     * @return Returns the nextInfo.
     */
    public IndexInfo getNextInfo() {
        return nextInfo;
    }

    /**
     * @param nextInfo
     *            The nextInfo to set.
     */
    public void setNextInfo(IndexInfo nextInfo) {
        this.nextInfo = nextInfo;
    }

    public String getInfoShort() {
        return infoShort;
    }

    public void setInfoShort(String infoShort) {
        this.infoShort = infoShort;
    }

    public YesNoType getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(YesNoType isUrgent) {
        this.isUrgent = isUrgent;
    }

    public String getComeFrom() {
        return comeFrom;
    }

    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom;
    }

    public String getComeUrl() {
        return comeUrl;
    }

    public void setComeUrl(String comeUrl) {
        this.comeUrl = comeUrl;
    }

    public Date getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(Date infoDate) {
        this.infoDate = infoDate;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    /**
     * @return Returns the sharePicFile.
     */
    public String getSharePicFile() {
        return sharePicFile;
    }

    /**
     * @param sharePicFile
     *            The sharePicFile to set.
     */
    public void setSharePicFile(String sharePicFile) {
        this.sharePicFile = sharePicFile;
    }

    /**
     * @return Returns the smallPic.
     */
    public String getSmallPic() {
        if (StringUtils.isEmpty(sharePicFile)) {
            return "";
        }
        return NetstudyFileUtils.builSmallFilePath(sharePicFile, "_small");
    }

    /**
     * @param smallPic
     *            The smallPic to set.
     */
    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }
}
