/*
 * @(#)HifiArtist.java    Created on 2016年9月21日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.hifi.dto;

import java.io.Serializable;

/**
 * 演出者类型
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年9月21日 上午10:23:38 $
 */
public class HifiArtistType implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 类型名称
     */
    private String name;

    /**
     * 图片路径
     */
    private String image;

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
     * @return Returns the image.
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image
     *            The image to set.
     */
    public void setImage(String image) {
        this.image = image;
    }

}
