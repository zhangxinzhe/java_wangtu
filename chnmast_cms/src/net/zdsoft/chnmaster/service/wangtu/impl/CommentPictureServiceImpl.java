/*
 * @(#)CommentPictureServiceImpl.java    Created on 2017年8月7日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.wangtu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.wangtu.CommentPictureDao;
import net.zdsoft.chnmaster.entity.wangtu.CommentPicture;
import net.zdsoft.chnmaster.service.wangtu.CommentPictureService;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年8月7日 上午10:07:40 $
 */
@Service("commentPictureService")
public class CommentPictureServiceImpl implements CommentPictureService {

    @Resource
    private CommentPictureDao commentPictureDao;

    @Override
    public List<CommentPicture> getListByCommentId(long commentId) {

        return commentPictureDao.getListByCommentId(commentId);
    }

    @Override
    public int addPucture(List<CommentPicture> pic) {

        return commentPictureDao.addPucture(pic);
    }

    @Override
    public int deletePicturesByComment(long commentId) {

        return commentPictureDao.deletePicturesByComment(commentId);
    }

}
