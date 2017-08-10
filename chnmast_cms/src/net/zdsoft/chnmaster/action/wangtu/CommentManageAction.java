/*
 * @(#)CommentManageAction.java    Created on 2017年8月8日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.wangtu;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import net.zdsoft.chnmaster.action.common.CmsPageAction;
import net.zdsoft.chnmaster.entity.wangtu.Comment;
import net.zdsoft.chnmaster.service.wangtu.CommentService;
import net.zdsoft.common.dao.queryCondition.EqualCondition;
import net.zdsoft.common.dao.queryCondition.LikeCondition;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.enums.OrderStatus;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年8月8日 下午5:24:06 $
 */
public class CommentManageAction extends CmsPageAction {

    private static final long serialVersionUID = 1L;

    private List<Comment> commentList;
    private long commentId;
    private String userName;
    private String realName;

    @Resource
    private CommentService commentService;

    // 评论列表
    public String commentManage() {
        List<QueryCondition> cons = new ArrayList<QueryCondition>();
        cons.add(new EqualCondition("o.order_status", OrderStatus.UNPAY.getValue(), Types.INTEGER));
        if (StringUtils.isNotBlank(userName)) {
            cons.add(new LikeCondition("u.username", userName));
        }
        if (StringUtils.isNotBlank(realName)) {
            cons.add(new LikeCondition("u.realName", realName));
        }
        commentList = commentService.listCommentByUserId(cons);
        return SUCCESS;
    }

    // 删除评论
    public void deleteComment() {
        if (getUser() == null) {
            printMsg("请先登录！");
            return;
        }
        int i = commentService.deleteComment(commentId);
        if (i > 0) {
            printMsg("success");
            return;
        }
        printMsg("删除失败，请重试！");
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

}
