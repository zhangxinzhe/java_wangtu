/*
 * @(#)HifiAlbum.java    Created on 2016年9月21日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.hifi.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 专辑详情
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年9月21日 上午11:15:18 $
 */
public class HifiAlbum implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 专辑名称
     */
    private String name;
    /**
     * 专辑中文名称（历史问题）
     */
    private String cn_name;
    /**
     * 封面大图url
     */
    private String bigimg;
    /**
     * 封面小图url
     */
    private String smallimg;
    /**
     * 专辑简介
     */
    private String introduction;
    /**
     * 发行时间
     */
    private Date publishTime;
    /**
     * 语种
     */
    private String language;
    /**
     * 音质图片url
     */
    private String technology;
    /**
     * 演出者Id
     */
    private String artistId;
    /**
     * 演出者姓名
     */
    private String artistName;
    /**
     * 发行方名字
     */
    private String companyName;
    /**
     * 专辑大小（磁盘容量）
     */
    private float size;
    /**
     * 歌曲数量
     */
    private int musicCount;
    /**
     * 专辑时长（如49分25秒：00:49:25）
     */
    private String playTime;
    /**
     * 专辑价钱
     */
    private float price;
    /**
     * 延伸阅读URL
     */
    private String reference;
    /**
     * 产品ID
     */
    private String productId;
    /**
     * Album_AudioCategory_File的ID，购买后下载专辑时用到
     */
    private String aafid;
    /**
     * 是否有全部flac高清音乐格式（0否、1是）
     */
    private boolean isfullflac;
    /**
     * 专辑中歌曲列表
     */
    private List<HifiMusic> musicList;

    // 接口中暂未使用参数:bitDepth,quality,score,state,goods,kwid

    /**************************** get、set方法 *************************/
    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the cn_name.
     */
    public String getCn_name() {
        return cn_name;
    }

    /**
     * @param cn_name
     *            The cn_name to set.
     */
    public void setCn_name(String cn_name) {
        this.cn_name = cn_name;
    }

    /**
     * @return Returns the bigimg.
     */
    public String getBigimg() {
        return bigimg;
    }

    /**
     * @param bigimg
     *            The bigimg to set.
     */
    public void setBigimg(String bigimg) {
        this.bigimg = bigimg;
    }

    /**
     * @return Returns the smallimg.
     */
    public String getSmallimg() {
        return smallimg;
    }

    /**
     * @param smallimg
     *            The smallimg to set.
     */
    public void setSmallimg(String smallimg) {
        this.smallimg = smallimg;
    }

    /**
     * @return Returns the introduction.
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * @param introduction
     *            The introduction to set.
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * @return Returns the publishTime.
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * @param publishTime
     *            The publishTime to set.
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * @return Returns the language.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language
     *            The language to set.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return Returns the technology.
     */
    public String getTechnology() {
        return technology;
    }

    /**
     * @param technology
     *            The technology to set.
     */
    public void setTechnology(String technology) {
        this.technology = technology;
    }

    /**
     * @return Returns the artistName.
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * @param artists
     *            The artistName to set.
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * @return Returns the companyName.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     *            The companyName to set.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return Returns the size.
     */
    public float getSize() {
        return size;
    }

    /**
     * @param size
     *            The size to set.
     */
    public void setSize(float size) {
        this.size = size;
    }

    /**
     * @return Returns the musicCount.
     */
    public int getMusicCount() {
        return musicCount;
    }

    /**
     * @param musicCount
     *            The musicCount to set.
     */
    public void setMusicCount(int musicCount) {
        this.musicCount = musicCount;
    }

    /**
     * @return Returns the playTime.
     */
    public String getPlayTime() {
        return playTime;
    }

    /**
     * @param playTime
     *            The playTime to set.
     */
    public void setPlayTime(String playTime) {
        this.playTime = playTime;
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

    /**
     * @return Returns the reference.
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference
     *            The reference to set.
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return Returns the productId.
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     *            The productId to set.
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return Returns the aafid.
     */
    public String getAafid() {
        return aafid;
    }

    /**
     * @param aafid
     *            The aafid to set.
     */
    public void setAafid(String aafid) {
        this.aafid = aafid;
    }

    /**
     * @return Returns the isfullflac.
     */
    public boolean isIsfullflac() {
        return isfullflac;
    }

    /**
     * @param isfullflac
     *            The isfullflac to set.
     */
    public void setIsfullflac(boolean isfullflac) {
        this.isfullflac = isfullflac;
    }

    /**
     * @return Returns the musicList.
     */
    public List<HifiMusic> getMusicList() {
        return musicList;
    }

    /**
     * @param musicList
     *            The musicList to set.
     */
    public void setMusicList(List<HifiMusic> musicList) {
        this.musicList = musicList;
    }

    /**
     * @return Returns the artistId.
     */
    public String getArtistId() {
        return artistId;
    }

    /**
     * @param artistId
     *            The artistId to set.
     */
    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

}
