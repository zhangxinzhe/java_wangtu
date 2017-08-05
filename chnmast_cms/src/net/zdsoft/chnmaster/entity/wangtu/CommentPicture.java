/*
 * @(#)Reward.java    Created on 2017年7月8日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu;

import net.zdsoft.common.entity.BaseEntity;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月8日 下午12:53:45 $
 */
public class CommentPicture extends BaseEntity {
	private static final long serialVersionUID = 6042797958729958812L;
	private long  commentId;
	 private String  picturePath;
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	  
	 
}
