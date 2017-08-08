/*
 * @(#)AttendSongRowMapper.java    Created on 2016年4月9日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.compete.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.compete.AttendSong;
import net.zdsoft.common.enums.CompeteStage;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年4月9日 下午1:41:59 $
 */
@SuppressWarnings("rawtypes")
public class AttendSongRowMapper implements RowMapper {

    private static AttendSongRowMapper rowMapper = new AttendSongRowMapper();

    public static AttendSongRowMapper instance() {
        return rowMapper;
    }

    @Override
    public AttendSong mapRow(ResultSet rs, int i) throws SQLException {
        AttendSong entity = new AttendSong();
        entity.setId(rs.getInt("ID"));
        entity.setCompeteId(rs.getLong("COMPETE_ID"));
        entity.setAttendId(rs.getLong("ATTEND_ID"));
        entity.setStage(CompeteStage.get(rs.getInt("STAGE")));
        entity.setSongName(rs.getString("SONG_NAME"));
        entity.setSongAuthor(rs.getString("SONG_AUTHOR"));
        entity.setRemark(rs.getString("REMARK"));
        return entity;
    }

}
