/*
 * @(#)CompeteAttendHljRowMapper.java    Created on 2016年5月6日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.compete.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.compete.CompeteAttendHlj;
import net.zdsoft.common.enums.CompeteApplyType;
import net.zdsoft.common.enums.CompeteTeamType;
import net.zdsoft.common.enums.CompeteType;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年5月6日 下午4:56:14 $
 */
public class CompeteAttendHljRowMapper implements RowMapper<CompeteAttendHlj> {

    private static CompeteAttendHljRowMapper rowMapper = new CompeteAttendHljRowMapper();
    public static CompeteAttendHljInfoRowMapper attendInfoRowMapper = new CompeteAttendHljInfoRowMapper();
    public static HljAttendInfoRowMapper hljAttendInfoRowMapper = new HljAttendInfoRowMapper();

    public static CompeteAttendHljRowMapper instance() {
        return rowMapper;
    }

    @Override
    public CompeteAttendHlj mapRow(ResultSet rs, int index) throws SQLException {
        CompeteAttendHlj entity = new CompeteAttendHlj();
        entity.setId(rs.getLong("ID"));
        entity.setCompeteId(rs.getLong("COMPETE_ID"));
        entity.setCompeteType(CompeteType.get(rs.getInt("COMPETE_TYPE")));
        entity.setUserId(rs.getLong("USER_ID"));
        entity.setTeamName(rs.getString("TEAMNAME"));
        entity.setTeamType(CompeteTeamType.get(rs.getInt("TEAM_TYPE")));
        entity.setBandType(rs.getString("BAND_TYPE"));
        entity.setUnitName(rs.getString("UNIT_NAME"));
        entity.setTeamLeader(rs.getString("TEAM_LEADER"));
        entity.setTeamNum(rs.getString("TEAM_NUM") == null ? null : rs.getInt("TEAM_NUM"));
        entity.setMajorName(rs.getString("MAJOR_NAME"));
        entity.setMajorNum(rs.getString("MAJOR_NUM") == null ? null : rs.getInt("MAJOR_NUM"));
        entity.setDrumName(rs.getString("DRUM_NAME"));
        entity.setDrunNum(rs.getString("DRUM_NUM") == null ? null : rs.getInt("DRUM_NUM"));
        entity.setManageNum(rs.getString("MANAGE_NUM") == null ? null : rs.getInt("MANAGE_NUM"));
        entity.setTotalNum(rs.getString("TOTAL_NUM") == null ? null : rs.getInt("TOTAL_NUM"));
        entity.setPhone(rs.getString("PHONE"));
        entity.setEmail(rs.getString("EMAIL"));
        entity.setPlace(rs.getString("PLACE"));
        entity.setPhotoFile(rs.getString("PHOTO_FILE"));
        entity.setTeamIntro(rs.getString("TEAM_INTRO"));
        entity.setMemberIntro(rs.getString("MEMBERS_INTRO"));
        entity.setApplyType(CompeteApplyType.get(rs.getInt("APPLY_TYPE")));
        entity.setAttendCode(rs.getString("ATTEND_CODE"));
        entity.setCreationTime(rs.getTimestamp("CREATION_TIME"));
        entity.setVoteNum(rs.getInt("VOTE_NUM"));
        entity.setVoteAddNum(rs.getInt("VOTE_ADD_NUM"));
        entity.setVoteScore(rs.getFloat("VOTE_SCORE"));
        entity.setSongIntro(rs.getString("SONG_INTRO"));
        return entity;
    }

    static class CompeteAttendHljInfoRowMapper implements RowMapper<CompeteAttendHlj> {
        @Override
        public CompeteAttendHlj mapRow(ResultSet rs, int i) throws SQLException {
            CompeteAttendHlj entity = rowMapper.mapRow(rs, i);
            entity.setBeginTime(rs.getTimestamp("BEGINTIME"));
            entity.setEndTime(rs.getTimestamp("ENDTIME"));
            entity.setIsDoing(YesNoType.get(rs.getInt("IS_DOING")));
            entity.setVoteBeginTime(rs.getTimestamp("VOTE_BEGINTIME"));
            entity.setVoteEndTime(rs.getTimestamp("VOTE_ENDTIME"));
            return entity;
        }
    }

    static class HljAttendInfoRowMapper implements RowMapper<CompeteAttendHlj> {
        @Override
        public CompeteAttendHlj mapRow(ResultSet rs, int i) throws SQLException {
            CompeteAttendHlj entity = rowMapper.mapRow(rs, i);
            entity.setCompeteName(rs.getString("COMPETE_NAME"));
            entity.setCompeteBatch(rs.getString("COMPETE_BATCH"));
            entity.setBeginTime(rs.getTimestamp("BEGINTIME"));
            entity.setEndTime(rs.getTimestamp("ENDTIME"));
            entity.setIsDoing(YesNoType.get(rs.getInt("IS_DOING")));
            return entity;
        }
    }
}
