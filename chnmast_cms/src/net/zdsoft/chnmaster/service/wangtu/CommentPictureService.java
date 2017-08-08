/*
 * @(#)CommentPictureService.java    Created on 2017年8月7日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.wangtu;

import java.util.List;

import net.zdsoft.chnmaster.entity.wangtu.CommentPicture;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年8月7日 上午10:06:16 $
 */
public interface CommentPictureService {

    /**
     * 列表
     *
     * @param rewardId
     * @return
     */
    public List<CommentPicture> getListByCommentId(long commentId);

    /**
     * 新增
     *
     * @param pics
     * @return
     */
    public int addPucture(List<CommentPicture> pic);

    /**
     * 删除评论的图片
     *
     * @param rewardId
     * @return
     */
    public int deletePicturesByComment(long commentId);
}
