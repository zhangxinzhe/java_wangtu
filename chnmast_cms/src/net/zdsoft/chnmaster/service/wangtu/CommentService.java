/*
 * @(#)CommentService.java    Created on 2017年8月7日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.wangtu;

import java.io.File;
import java.util.List;

import net.zdsoft.chnmaster.entity.wangtu.Comment;
import net.zdsoft.common.dao.queryCondition.QueryCondition;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年8月7日 上午10:18:30 $
 */
public interface CommentService {

    /**
     * 用户最近20条
     *
     * @param userId
     * @return
     */
    public List<Comment> listCommentByUserId(long userId);

    /**
     * 用户最近20条
     *
     * @param userId
     * @return
     */
    public List<Comment> listCommentByUserId(List<QueryCondition> cons);

    public int addComment(Comment comment, File[] files) throws Exception;

    public int saveCommentReply(long commentId, String reply);

    public int deleteComment(long commentId);

    /**
     * 用户所有的评论数
     *
     * @param userId
     * @return
     */
    public int getAllUserCommentCount(long userId);

    /**
     * 用户 满意、不满意数
     *
     * @param userId
     * @param isSatisfy
     * @return
     */
    public int getUserSatisfyCount(long userId, int isSatisfy);

}
