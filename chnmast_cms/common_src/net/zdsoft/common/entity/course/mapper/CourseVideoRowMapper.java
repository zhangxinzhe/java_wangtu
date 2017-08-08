/*
 * @(#)CourseVideoRowMapper.java    Created on 2016年5月24日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.course.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.course.CourseVideo;

/**
 * @author xiongwq
 * @version $Revision: 1.0 $, $Date: 2016年5月24日 下午2:27:00 $
 */
@SuppressWarnings("rawtypes")
public class CourseVideoRowMapper implements RowMapper {
    private static CourseVideoRowMapper rowMapper = new CourseVideoRowMapper();

    public static CourseVideoRowMapper instance() {
        return rowMapper;
    }

    @Override
    public CourseVideo mapRow(ResultSet rs, int arg1) throws SQLException {
        CourseVideo video = new CourseVideo();
        video.setId(rs.getLong("ID"));
        video.setCourseId(rs.getLong("COURSEID"));
        video.setCourseTimeId(rs.getLong("COURSE_TIMEID"));
        video.setVideoDuration(rs.getFloat("VIDEO_DURATION"));
        video.setVideoFile(rs.getString("VIDEO_FILE"));
        video.setFileName(rs.getString("FILE_NAME"));
        video.setFileExt(rs.getString("FILE_EXT"));
        video.setFileSize(rs.getLong("FILE_SIZE"));
        video.setPredictTime(rs.getTimestamp("PREDICT_TIME"));
        video.setModifyTime(rs.getTimestamp("MODIFY_TIME"));
        video.setPlayNum(rs.getLong("PLAY_NUM"));
        video.setUploadType(rs.getInt("UPLOAD_TYPE"));
        // 转换信息 未处理
        video.setRemark(rs.getString("REMARK"));
        return video;
    }

}
