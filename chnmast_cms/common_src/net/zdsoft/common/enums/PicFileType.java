/*
 * @(#)PicFileType.java    Created on 2015年12月4日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 *
 * @author wangly
 * @version $Revision: 1.0 $, $Date: 2015年12月4日 下午4:36:58 $
 */
public enum PicFileType {
    PICTURE(1, "图片"), VIDEO(2, "视频");
    private int value;
    private String nameValue;

    PicFileType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static PicFileType getValue(int value) {
        for (PicFileType type : PicFileType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getNameValue() {
        return nameValue;
    }

    public void setNameValue(String nameValue) {
        this.nameValue = nameValue;
    }
}
