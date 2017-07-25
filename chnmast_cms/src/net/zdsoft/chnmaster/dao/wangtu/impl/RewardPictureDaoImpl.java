/*
 * @(#)RewardPictureDaoImpl.java    Created on 2017年7月18日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.wangtu.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.wangtu.RewardPictureDao;
import net.zdsoft.chnmaster.entity.wangtu.RewardPicture;
import net.zdsoft.chnmaster.entity.wangtu.mapper.RewardPictureMapper;
import net.zdsoft.common.dao.BaseDaoImpl;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月18日 下午2:11:58 $
 */
@SuppressWarnings("unchecked")
@Repository("rewardPictureDao")
public class RewardPictureDaoImpl extends BaseDaoImpl implements RewardPictureDao {

    @Override
    public List<RewardPicture> getListByRewardId(long rewardId) {
        String sql = "select * from t_reward_picture where reward_id=? ";
        return this.find(sql, new Object[] { rewardId }, RewardPictureMapper.instance());
    }

    @Override
    public int deletePictureByIds(Long[] ids) {
        String sql = "delete from t_reward_picture where id in ";
        return executeUpdateForInSQL(sql, null, ids);
    }

    @Override
    public int addPucture(final List<RewardPicture> pic) {
        String sql = "insert into t_reward (reward_id,picPath)values(?,?)";
        return this.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public int getBatchSize() {

                return pic.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int index) throws SQLException {
                RewardPicture picture = pic.get(index);
                ps.setLong(1, picture.getRewardId());
                ps.setString(2, picture.getFilePath());
            }

        }).length;
        // return this.executeUpdate(sql, new Object[] { pic.getRewardId(), pic.getFilePath() });
    }

}
