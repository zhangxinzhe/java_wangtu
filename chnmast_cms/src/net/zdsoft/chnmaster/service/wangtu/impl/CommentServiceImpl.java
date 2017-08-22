/*
 * @(#)CommentServiceImpl.java    Created on 2017年8月7日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.wangtu.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import net.zdsoft.chnmaster.dao.wangtu.CommentDao;
import net.zdsoft.chnmaster.entity.wangtu.Comment;
import net.zdsoft.chnmaster.entity.wangtu.CommentPicture;
import net.zdsoft.chnmaster.service.wangtu.CommentPictureService;
import net.zdsoft.chnmaster.service.wangtu.CommentService;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.filesystem.util.FileSystemUtil;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年8月7日 上午10:25:13 $
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentPictureService commentPictureService;
    @Resource
    private CommentDao commentDao;

    @Override
    public List<Comment> listCommentByUserId(long userId) {
        List<Comment> list = commentDao.listCommentByUserId(userId);
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        for (Comment c : list) {
            c.setCommentPictures(commentPictureService.getListByCommentId(c.getId()));
            if (c.getIsAnonymous()) {
                c.setUserName("匿名");
            }
        }
        return list;
    }

    @Override
    public int addComment(Comment comment, File[] files) throws Exception {
        comment.setId(commentDao.getKeyId());
        if (files != null && files.length > 0) {

            List<CommentPicture> pics = new ArrayList<CommentPicture>();
            for (int i = 0; i < files.length; i++) {
                CommentPicture pic = new CommentPicture();
                String systimeStr = System.currentTimeMillis() + i + ".png";
                String filePath = "/upload/comment/picture/" + comment.getId() + "/" + systimeStr;
                FileSystemUtil.saveFile(files[i], filePath);
                pic.setCommentId(comment.getId());
                pic.setPicturePath(filePath);
                pics.add(pic);
            }
            // 保存图片
            commentPictureService.addPucture(pics);
        }

        return commentDao.addComment(comment);
    }

    @Override
    public int saveCommentReply(long commentId, String reply) {
        return commentDao.saveCommentReply(commentId, reply);
    }

    @Override
    public int deleteComment(long commentId) {
        List<CommentPicture> pics = commentPictureService.getListByCommentId(commentId);
        commentPictureService.deletePicturesByComment(commentId);
        if (!CollectionUtils.isEmpty(pics)) {
            try {
                for (CommentPicture p : pics) {
                    FileSystemUtil.deleteFile(p.getPicturePath());
                }
            }
            catch (Exception e) {

            }

        }
        return commentDao.deleteComment(commentId);
    }

    @Override
    public int getAllUserCommentCount(long userId) {
        return commentDao.getAllUserCommentCount(userId);
    }

    @Override
    public int getUserSatisfyCount(long userId, int isSatisfy) {
        return commentDao.getUserSatisfyCount(userId, isSatisfy);
    }

    @Override
    public List<Comment> listCommentByUserId(List<QueryCondition> cons) {
        List<Comment> list = commentDao.listCommentByUserId(cons);
        if (!CollectionUtils.isEmpty(list)) {
            for (Comment t : list) {
                t.setCommentPictures(commentPictureService.getListByCommentId(t.getId()));
            }
        }
        return list;
    }

    @Override
    public int getUserServiceQuityAVG(long userId) {
        return commentDao.getUserServiceQuityAVG(userId);
    }

    @Override
    public int getUserServiceAttitudeAVG(long userId) {

        return commentDao.getUserServiceAttitudeAVG(userId);
    }

}
