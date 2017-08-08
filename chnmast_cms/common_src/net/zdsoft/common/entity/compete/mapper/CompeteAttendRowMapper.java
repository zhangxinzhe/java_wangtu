/*
 * @(#)CompeteAttendRowMapper.java    Created on 2016年4月11日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.compete.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.compete.CompeteAttend;
import net.zdsoft.common.enums.AuditStatus;
import net.zdsoft.common.enums.CompeteApplyType;
import net.zdsoft.common.enums.CompeteGroupType;
import net.zdsoft.common.enums.OrderStatus;
import net.zdsoft.common.enums.SexType;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年4月11日 下午1:47:04 $
 */
@SuppressWarnings("rawtypes")
public class CompeteAttendRowMapper implements RowMapper {

    private static CompeteAttendRowMapper rowMapper = new CompeteAttendRowMapper();
    public static CompeteAttendInfoRowMapper attendInfoRowMapper = new CompeteAttendInfoRowMapper();

    public static CompeteAttendRowMapper instance() {
        return rowMapper;
    }

    @Override
    public CompeteAttend mapRow(ResultSet rs, int i) throws SQLException {
        CompeteAttend entity = new CompeteAttend();
        entity.setId(rs.getLong("ID"));
        entity.setCompeteId(rs.getLong("COMPETE_ID"));
        entity.setUserId(rs.getLong("USER_ID"));
        entity.setRealName(rs.getString("REALNAME"));
        entity.setSex(SexType.get(rs.getInt("SEX")));
        entity.setBirthday(rs.getTimestamp("BIRTHDAY"));
        entity.setPhotoFile(rs.getString("PHOTO_FILE"));
        entity.setMobilePhone(rs.getString("MOBILEPHONE"));
        entity.setEmail(rs.getString("EMAIL"));
        entity.setSchoolName(rs.getString("SCHOOL_NAME"));
        entity.setUnitName(rs.getString("UNIT_NAME"));
        entity.setApplyType(CompeteApplyType.get(rs.getInt("APPLY_TYPE")));
        entity.setGroupType(CompeteGroupType.get(rs.getInt("GROUP_TYPE")));
        entity.setAttendCode(rs.getString("ATTEND_CODE"));
        entity.setCreationTime(rs.getTimestamp("CREATION_TIME"));
        entity.setPayAmount(rs.getFloat("PAY_AMOUNT"));
        entity.setOrderStatus(OrderStatus.getOrderStatus(rs.getInt("ORDER_STATUS")));
        entity.setVideoUrl(rs.getString("VIDEO_URL"));
        entity.setAuditStatus(AuditStatus.get(rs.getInt("AUDIT_STATUS")));
        entity.setVoteNum(rs.getInt("VOTE_NUM"));
        entity.setVoteAddNum(rs.getInt("VOTE_ADD_NUM"));
        entity.setPlayNum(rs.getInt("PLAY_NUM"));
        entity.setRemark(rs.getString("REMARK"));
        return entity;
    }

    static class CompeteAttendInfoRowMapper implements RowMapper<CompeteAttend> {
        @Override
        public CompeteAttend mapRow(ResultSet rs, int i) throws SQLException {
            CompeteAttend entity = rowMapper.mapRow(rs, i);
            entity.setCompeteName(rs.getString("COMPETE_NAME"));
            entity.setCompeteFee(rs.getFloat("COMPETE_FEE"));
            entity.setBeginTime(rs.getTimestamp("BEGINTIME"));
            entity.setEndTime(rs.getTimestamp("ENDTIME"));
            entity.setIsDoing(YesNoType.get(rs.getInt("IS_DOING")));
            entity.setVoteBeginTime(rs.getTimestamp("VOTE_BEGINTIME"));
            entity.setVoteEndTime(rs.getTimestamp("VOTE_ENDTIME"));

            return entity;
        }
    }

}
