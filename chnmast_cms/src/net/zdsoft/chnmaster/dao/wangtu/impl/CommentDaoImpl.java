/*
 * @(#)CommentDaoImpl.java    Created on 2017年8月7日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.wangtu.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.wangtu.CommentDao;
import net.zdsoft.chnmaster.entity.wangtu.Comment;
import net.zdsoft.chnmaster.entity.wangtu.mapper.CommentMapper;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.dao.queryCondition.QueryConditionBuilder;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年8月7日 上午10:29:26 $
 */
@SuppressWarnings("unchecked")
@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoImpl implements CommentDao {

    @Override
    public long getKeyId() {
        return this.generatePrimaryKey("t_comment");
    }

    @Override
    public List<Comment> listCommentByUserId(long userId) {
        String sql = "select t.*,u.username,u.AVATAR_FILE from t_comment t ,t_user u where u.id=t.user_id and t.user_id= ? order by t.comment_time desc limit 20 ";
        return this.find(sql, new Object[] { userId }, CommentMapper.instane());
    }

    @Override
    public int addComment(Comment comment) {
        String sql = "insert into t_comment (id,user_id,reviewer_id,content,service_quility,"
                + "service_quility_content," + "service_attitude,service_attitude_content,"
                + "comment_time,reply_content,reply_time,is_satisfy,remark,reward_id,is_anonymous)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return this.executeUpdate(sql,
                new Object[] { comment.getId(), comment.getUserId(), comment.getReviewerId(), comment.getContent(),
                        comment.getServiceQuality(), comment.getServiceQualityContent(), comment.getServiceAttitude(),
                        comment.getServiceAttitudeContent(), comment.getCommentTime(), comment.getReplyContent(),
                        comment.getReplyTime(),comment.getIsSatisfy(), comment.getRemark(), comment.getRewardId(),comment.getIsAnonymous()});
    }

    @Override
    public int saveCommentReply(long commentId, String reply) {
        String sql = "update t_comment set reply_content=? ,reply_time=? where id=? ";
        return this.executeUpdate(sql, new Object[] { reply, new Date(), commentId });
    }

    @Override
    public int deleteComment(long commentId) {
        String sql = "delete from t_comment where id= ?";
        return this.executeUpdate(sql, new Object[] { commentId });
    }

    @Override
    public int getAllUserCommentCount(long userId) {
        String sql = "SELECT COUNT(1) FROM T_COMMENT WHERE USER_ID=?";
        return this.findForInt(sql, new Object[] { userId });
    }

    @Override
    public int getUserSatisfyCount(long userId, int isSatisfy) {
        String sql = "SELECT COUNT(1) FROM T_COMMENT WHERE USER_ID=? and is_satisfy=? ";
        return this.findForInt(sql, new Object[] { userId, isSatisfy });
    }

    @Override
    public List<Comment> listCommentByUserId(List<QueryCondition> cons) {
        String sql = "select t.*,u.username,u.AVATAR_FILE from t_comment t ,t_user u where u.id=t.user_id ";
        QueryConditionBuilder builder = new QueryConditionBuilder();
        builder.addConditions(cons);
        String param = builder.buildCondition();
        if (StringUtils.isNotBlank(param)) {
            sql += " and " + param;
        }
        sql += " order by t.comment_time desc limit 20 ";

        return this.find(sql, builder.buildParameters(), CommentMapper.instane());
    }

}
