/*
 * @(#)HifiHomeColmnContent.java    Created on 2016年9月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.hifi.dto;

import java.io.Serializable;

/**
 * 首页栏目的推荐位内容
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年9月22日 下午4:13:08 $
 */
public class HifiHomeColumnContent implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 推荐位类型 <br>
     * Type为album：专辑，contentid为albumId <br>
     * Type为track：歌曲，contentid为trackId <br>
     * Type为musiclist：主题，contentid为musiclistId <br>
     * Type为url/video/activity：linkUrl为跳转的url <br>
     * Type为goods：商品，contentid为goodsId，作为实体商城的goodsId <br>
     * Type为broadcast：电台，contentId请调用接口9获取电台专辑列表
     */
    private String type;
    /**
     * 推荐位ID
     */
    private String contentId;
    /**
     * 推荐位的名称
     */
    private String contentTitle;
    /**
     * 推荐位图片
     */
    private String imgUrl;
    /**
     * 价格
     */
    private float price;
    /**
     * 排序号
     */
    private int displayOrder;
    /**
     * 专辑名
     */
    private String albumName;
    /**
     * 演出者名
     */
    private String artistName;
    /**
     * 外部链接地址
     */
    private String linkUrl;

    /**
     * @retur type为album：contentid为albumId <br>
     *        type为track：contentid为trackId <br>
     *        type为musiclist：contentid为musiclistId <br>
     *        type为url/video/activity：linkUrl为跳转的url <br>
     *        type为goods：contentid为goodsId，作为实体商城的goodsId <br>
     *        type为broadcast：contentId请调用接口9获取电台专辑列表
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return Returns the contentId.
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * @param contentId
     *            The contentId to set.
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * @return Returns the contentTitle.
     */
    public String getContentTitle() {
        return contentTitle;
    }

    /**
     * @param contentTitle
     *            The contentTitle to set.
     */
    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    /**
     * @return Returns the imgUrl.
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl
     *            The imgUrl to set.
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
     * @return Returns the albumName.
     */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * @param albumName
     *            The albumName to set.
     */
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    /**
     * @return Returns the artistName.
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * @param artistName
     *            The artistName to set.
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
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
     * @return Returns the price.
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price
     *            The price to set.
     */
    public void setPrice(float price) {
        this.price = price;
    }

}
