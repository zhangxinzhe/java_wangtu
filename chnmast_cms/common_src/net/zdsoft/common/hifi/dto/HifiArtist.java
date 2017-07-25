/*
 * @(#)HifiArtist.java    Created on 2016年9月21日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.hifi.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 演出者
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年9月21日 上午10:23:38 $
 */
public class HifiArtist implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 艺人姓名
     */
    private String name;
    /**
     * 艺人别名
     */
    private String aliasName;
    /**
     * 艺人外文名
     */
    private String foreignName;
    /**
     * 艺人首字母缩写
     */
    private String firstChar;
    /**
     * 艺人性别，F为女性，M为男性，G为组合
     */
    private String gender;
    /**
     * 出生或出道时间
     */
    private Date beginDate;
    /**
     * 死亡或解散时间
     */
    private Date endDate;
    /**
     * 艺人图片url
     */
    private String imgUrl;
    /**
     * 艺人简介
     */
    private String introduction;

    // 接口中暂未使用参数:foreignName,popularity,albumcount,tagId

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
     * @return Returns the aliasName.
     */
    public String getAliasName() {
        return aliasName;
    }

    /**
     * @param aliasName
     *            The aliasName to set.
     */
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    /**
     * @return Returns the firstChar.
     */
    public String getFirstChar() {
        return firstChar;
    }

    /**
     * @param firstChar
     *            The firstChar to set.
     */
    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }

    /**
     * @return Returns the gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     *            The gender to set.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return Returns the beginDate.
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate
     *            The beginDate to set.
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * @return Returns the endDate.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            The endDate to set.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
     * @return Returns the foreignName.
     */
    public String getForeignName() {
        return foreignName;
    }

    /**
     * @param foreignName
     *            The foreignName to set.
     */
    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }

}
