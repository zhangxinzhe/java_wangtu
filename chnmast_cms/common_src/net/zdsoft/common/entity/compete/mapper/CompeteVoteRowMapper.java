/*
 * @(#)CompeteVoteRowMapper.java    Created on 2016年5月6日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.compete.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.compete.CompeteVote;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年5月6日 下午2:17:57 $
 * @param <T>
 */
public class CompeteVoteRowMapper implements RowMapper<CompeteVote> {

    private static CompeteVoteRowMapper rowMapper = new CompeteVoteRowMapper();

    public static CompeteVoteRowMapper instance() {
        return rowMapper;
    }

    @Override
    public CompeteVote mapRow(ResultSet rs, int index) throws SQLException {
        CompeteVote entity = new CompeteVote();
        entity.setId(rs.getLong("ID"));
        entity.setCompeteId(rs.getLong("COMPETE_ID"));
        entity.setAttendId(rs.getLong("ATTEND_ID"));
        entity.setUserId(rs.getLong("USER_ID"));
        entity.setIp(rs.getString("IP"));
        entity.setVoteTime(rs.getTimestamp("VOTE_TIME"));
        return entity;
    }

}
