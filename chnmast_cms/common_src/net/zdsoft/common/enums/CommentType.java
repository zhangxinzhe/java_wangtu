/*
 * @(#)CommentType.java    Created on 2016年11月2日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 评论类型（1课程评论，2视频回顾评论）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年11月2日 下午1:50:50 $
 */
public enum CommentType {

    /**
     * 1课程评论
     */
    COURSE(1, "课程评论"),

    /**
     * 2视频回顾评论
     */
    VIDEO_REVIEW(2, "视频回顾评论");

    private int value;
    private String nameValue;

    CommentType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static CommentType getValue(int value) {
        for (CommentType type : CommentType.values()) {
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
