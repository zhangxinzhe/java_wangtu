/*
 * @(#)HifiMusic.java    Created on 2016年9月21日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.hifi.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 歌曲详情
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年9月21日 下午1:47:49 $
 */
public class HifiMusic implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 歌曲名字
     */
    private String name;
    /**
     * 演出者id
     */
    private String artistId;
    /**
     * 演出者姓名
     */
    private String artistName;
    /**
     * 歌曲小图图片url
     */
    private String smallimg;
    /**
     * 歌曲时长（如4分7秒：00:04:07）
     */
    private String playTimes;
    /**
     * 音质图片路径url
     */
    private String technology;
    /**
     * 下载地址url
     */
    private String downloadUrl;
    /**
     * map3试听地址url
     */
    private String listenUrl;
    /**
     * 16-bit flac试听/下载地址url（开通流媒体在线试听会员才能试听16bit flac格式）
     */
    private String playUrl;
    /**
     * 歌词url
     */
    private String lyrics;
    /**
     * 价钱
     */
    private float price;
    /**
     * flac格式的文件大小
     */
    private float size;
    /**
     * 是否有mp3
     */
    private boolean mp3Avail;
    /**
     * 产品Id
     */
    private String productid;
    /**
     * track_AudioCategory_File 的ID，购买后下载歌曲时用到
     */
    private String tafid;

    /**
     * 专辑Id
     */
    private String albumId;
    /**
     * 专辑名字
     */
    private String albumName;
    /**
     * 专辑图片url
     */
    private String albumImg;
    /**
     * 发行时间
     */
    private Date publishTime;
    /**
     * 语言
     */
    private String language;
    /**
     * 发行方名字
     */
    private String companyName;
    /**
     * 歌曲序号（专辑中排序）
     */
    private int trackno;

    // 接口中暂未使用参数:audioFileList,dtsurl,flacAvail,kwId,score,isFlac,state

    /***************** 辅助字段 ******************/
    private String acPlayUrl;// 实际播放地址
    private String musicType;// 音乐类型 mp3,16bit;
    private String token;// flac head验证字段

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
     * @return Returns the playTimes.
     */
    public String getPlayTimes() {
        return playTimes;
    }

    /**
     * @param playTimes
     *            The playTimes to set.
     */
    public void setPlayTimes(String playTimes) {
        this.playTimes = playTimes;
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
     * @return Returns the downloadUrl.
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * @param downloadUrl
     *            The downloadUrl to set.
     */
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    /**
     * @return Returns the listenUrl.
     */
    public String getListenUrl() {
        return listenUrl;
    }

    /**
     * @param listenUrl
     *            The listenUrl to set.
     */
    public void setListenUrl(String listenUrl) {
        this.listenUrl = listenUrl;
    }

    /**
     * @return Returns the playUrl.
     */
    public String getPlayUrl() {
        return playUrl;
    }

    /**
     * @param playUrl
     *            The playUrl to set.
     */
    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    /**
     * @return Returns the lyrics.
     */
    public String getLyrics() {
        return lyrics;
    }

    /**
     * @param lyrics
     *            The lyrics to set.
     */
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
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
     * @return Returns the mp3Avail.
     */
    public boolean isMp3Avail() {
        return mp3Avail;
    }

    /**
     * @param mp3Avail
     *            The mp3Avail to set.
     */
    public void setMp3Avail(boolean mp3Avail) {
        this.mp3Avail = mp3Avail;
    }

    /**
     * @return Returns the productid.
     */
    public String getProductid() {
        return productid;
    }

    /**
     * @param productid
     *            The productid to set.
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }

    /**
     * @return Returns the tafid.
     */
    public String getTafid() {
        return tafid;
    }

    /**
     * @param tafid
     *            The tafid to set.
     */
    public void setTafid(String tafid) {
        this.tafid = tafid;
    }

    /**
     * @return Returns the albumId.
     */
    public String getAlbumId() {
        return albumId;
    }

    /**
     * @param albumId
     *            The albumId to set.
     */
    public void setAlbumId(String albumId) {
        this.albumId = albumId;
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
     * @return Returns the albumImg.
     */
    public String getAlbumImg() {
        return albumImg;
    }

    /**
     * @param albumImg
     *            The albumImg to set.
     */
    public void setAlbumImg(String albumImg) {
        this.albumImg = albumImg;
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
     * @return Returns the trackno.
     */
    public int getTrackno() {
        return trackno;
    }

    /**
     * @param trackno
     *            The trackno to set.
     */
    public void setTrackno(int trackno) {
        this.trackno = trackno;
    }

    public String getAcPlayUrl() {
        return acPlayUrl;
    }

    public void setAcPlayUrl(String acPlayUrl) {
        this.acPlayUrl = acPlayUrl;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
