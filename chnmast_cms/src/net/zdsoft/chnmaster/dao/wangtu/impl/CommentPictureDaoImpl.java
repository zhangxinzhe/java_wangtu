/*
 * @(#)CommentPictureDaoImpl.java    Created on 2017年8月7日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.wangtu.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.wangtu.CommentPictureDao;
import net.zdsoft.chnmaster.entity.wangtu.CommentPicture;
import net.zdsoft.chnmaster.entity.wangtu.mapper.CommentPictureMapper;
import net.zdsoft.common.dao.BaseDaoImpl;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年8月7日 上午10:09:22 $
 */
@SuppressWarnings("unchecked")
@Repository("commentPictureDao")
public class CommentPictureDaoImpl extends BaseDaoImpl implements CommentPictureDao {

    @Override
    public List<CommentPicture> getListByCommentId(long commentId) {
        String sql = "select * from t_comment_picture where reward_id=? ";
        return this.find(sql, new Object[] { commentId }, CommentPictureMapper.instance());
    }

    @Override
    public int addPucture(final List<CommentPicture> pic) {
        String sql = "insert into t_comment_picture (comment_id,picture_path)values(?,?)";
        return this.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public int getBatchSize() {

                return pic.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int index) throws SQLException {
                CommentPicture picture = pic.get(index);
                ps.setLong(1, picture.getCommentId());
                ps.setString(2, picture.getPicturePath());
            }

        }).length;
    }

    @Override
    public int deletePicturesByComment(long commentId) {
        String sql = "DELETE FROM t_comment_picture WHERE comment_id=? ";
        return this.executeUpdate(sql, new Object[] { commentId });
    }

}
