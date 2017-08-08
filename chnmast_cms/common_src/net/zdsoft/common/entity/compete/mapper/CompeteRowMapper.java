/*
 * @(#)CompeteRowMapper.java    Created on 2016年4月9日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.compete.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.compete.Compete;
import net.zdsoft.common.enums.CompeteType;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年4月9日 下午1:35:30 $
 */
@SuppressWarnings("rawtypes")
public class CompeteRowMapper implements RowMapper {

    private static CompeteRowMapper rowMapper = new CompeteRowMapper();

    public static CompeteRowMapper instance() {
        return rowMapper;
    }

    @Override
    public Compete mapRow(ResultSet rs, int index) throws SQLException {
        Compete entity = new Compete();
        entity.setId(rs.getLong("ID"));
        entity.setCompeteType(CompeteType.get(rs.getInt("COMPETE_TYPE")));
        entity.setCompeteBatch(rs.getString("COMPETE_BATCH"));
        entity.setCompeteName(rs.getString("COMPETE_NAME"));
        entity.setRuleFile(rs.getString("RULE_FILE"));
        entity.setFormName(rs.getString("FORM_NAME"));
        entity.setFormFile(rs.getString("FORM_FILE"));
        entity.setCompeteFee(rs.getFloat("COMPETE_FEE"));
        entity.setAttendBeginTime(rs.getTimestamp("ATTEND_BEGINTIME"));
        entity.setAttendEndTime(rs.getTimestamp("ATTEND_ENDTIME"));
        entity.setVoteBeginTime(rs.getTimestamp("VOTE_BEGINTIME"));
        entity.setVoteEndTime(rs.getTimestamp("VOTE_ENDTIME"));
        entity.setBeginTime(rs.getTimestamp("BEGINTIME"));
        entity.setEndTime(rs.getTimestamp("ENDTIME"));
        entity.setCourseId(rs.getLong("COURSE_ID"));
        entity.setCreateTime(rs.getTimestamp("CREATETIME"));
        entity.setIsDoing(YesNoType.get(rs.getInt("IS_DOING")));
        entity.setRemark(rs.getString("REMARK"));

        return entity;
    }

}
