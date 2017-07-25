/*
 * @(#)UserDaoImpl.java    Created on 2015年11月4日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.basic.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.basic.UserTeacherDao;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.dao.queryCondition.QueryConditionBuilder;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.user.UserTeacher;
import net.zdsoft.common.entity.user.mapper.UserTeacherRowMapper;
import net.zdsoft.common.enums.AuditStatusType;
import net.zdsoft.keel.jdbc.MapRowMapper;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2015年11月4日 下午2:22:07 $
 */
@SuppressWarnings("unchecked")
@Repository("userTeacherDao")
public class UserTeacherDaoImpl extends BaseDaoImpl implements UserTeacherDao {

    private static final String list_user_teacher = "user.list_user_teacher";
    private static final String add_user_teacher = "user.add_user_teacher";
    private static final String get_no_recommend_teacher_num = "user.get_no_recommend_teacher_num";
    private static final String batch_delete_teacher = "user.batch_delete_teacher";
    private static final String get_user_teacher_by_id = "user.get_user_teacher_by_id";
    private static final String update_user_teacher = "user.update_user_teacher";
    private static final String update_teacher_recommend_by_ids = "user.update_teacher_recommend_by_ids";
    private static final String update_teacher_recommendSeq_by_id = "user.update_teacher_recommendSeq_by_id";
    private static final String update_tutor_by_id = "user.update_tutor_by_id";
    private static final String get_user_teacher_id_and_real_name = "user.get_user_teacher_id_and_real_name";

    @Override
    public List<UserTeacher> getUserTeacherListByCondition(List<QueryCondition> conditions, boolean isTeacherRec,
            PageDto page) {
        StringBuffer sb = new StringBuffer(getSql(list_user_teacher));
        QueryConditionBuilder builder = new QueryConditionBuilder();
        builder.addConditions(conditions);
        if (StringUtils.isNotBlank(builder.buildCondition())) {
            sb.append(" AND ");
            sb.append(builder.buildCondition());
        }
        if (isTeacherRec) {
            sb.append(" ORDER BY UT.RECOMMENDSEQ ,UT.RECOMMENDTIME DESC");
        }
        else {
            sb.append(" ORDER BY UT.CREATETIME DESC");
        }

        if (page == null) {
            return find(sb.toString(), builder.buildParameters(), UserTeacherRowMapper.baseRowMapper);
        }
        else {
            return findForPage(sb.toString(), builder.buildParameters(), UserTeacherRowMapper.baseRowMapper, page);
        }
    }

    @Override
    public int addUserTeacher(UserTeacher teacher) {
        return executeUpdate(getSql(add_user_teacher),
                new Object[] { teacher.getId(), teacher.getRealName(), teacher.getTitle(), teacher.getIdCode(),
                        teacher.getIntroduction(), teacher.getPhotoFile(), teacher.getTeachSubject(),
                        teacher.getTeachType().getValue(), teacher.getRegionCode(), teacher.getCreateTime() });
    }

    @Override
    public int getNoRecommendTeacherNum(Long[] teacherIds) {
        StringBuffer sql = new StringBuffer(getSql(get_no_recommend_teacher_num));
        for (int i = 0; i < teacherIds.length; i++) {
            sql.append(teacherIds[i]);
            if (i != teacherIds.length - 1) {
                sql.append(",");
            }
        }
        sql.append(")");

        return findForInt(sql.toString(), null);
    }

    @Override
    public int batchDeleteTeacher(Long[] teacherIds) {
        return executeUpdateForInSQL(getSql(batch_delete_teacher), null, teacherIds);
    }

    @Override
    public UserTeacher getUserTeacherById(long teacherId) {
        return (UserTeacher) findForObject(getSql(get_user_teacher_by_id), new Object[] { teacherId },
                UserTeacherRowMapper.baseRowMapper);
    }

    @Override
    public int updateUserTeacher(UserTeacher teacher) {
        return executeUpdate(getSql(update_user_teacher),
                new Object[] { teacher.getRealName(), teacher.getTitle(), teacher.getIdCode(),
                        teacher.getIntroduction(), teacher.getPhotoFile(), teacher.getTeachSubject(),
                        teacher.getTeachType().getValue(), teacher.getRegionCode(), teacher.getId() });
    }

    @Override
    public int updateTeacherRecommend(Long[] ids, boolean isRecommend) {
        Date now = null;
        if (isRecommend) {
            now = new Date();
        }
        return executeUpdateForInSQL(getSql(update_teacher_recommend_by_ids), new Object[] { isRecommend, now }, ids);
    }

    @Override
    public int updateTeacherRecommendSeq(long id, int recommendSeq) {
        return executeUpdate(getSql(update_teacher_recommendSeq_by_id), new Object[] { recommendSeq, id });
    }

    @Override
    public UserTeacher getUserTeacherInfoById(long id) {
        String sql = getSql(get_user_teacher_by_id);
        return (UserTeacher) findForObject(sql, new Object[] { id }, UserTeacherRowMapper.instance());
    }

    @Override
    public int updateAuditStatus(long tutorId, int auditStu, String auditMsg, String auditRealName) {
        String sql = "UPDATE T_USER_TEACHER SET AUDIT_STATUS = ? ,AUDIT_DATE = ?,AUDIT_REALNAME = ?,AUDIT_MSG = ?,SHOW_GUIDE = ? WHERE ID = ?  ";
        Object[] args = {};
        if (auditStu == 2 || auditStu == 3) {// 通过/不通过审核
            sql = sql.concat(" AND  AUDIT_STATUS  = 1 ");
            if (auditStu == 2) {
                args = new Object[] { auditStu, new Date(), auditRealName, auditMsg, 1, tutorId };

            }
            else {
                args = new Object[] { auditStu, new Date(), auditRealName, auditMsg, 0, tutorId };

            }
        }
        else if (auditStu == 1) {// 取消审核
            sql = sql.concat(" AND  AUDIT_STATUS  IN(2,3) ");
            args = new Object[] { auditStu, null, "", "", 0, tutorId };
        }
        else {
            return 0;
        }
        return executeUpdate(sql, args);
    }

    @Override
    public int updateMusicTuorById(UserTeacher tutor) {
        return executeUpdate(getSql(update_tutor_by_id),
                new Object[] { tutor.getRealName(), tutor.getTitle(), tutor.getPhotoFile(), tutor.getTeachSubject(),
                        tutor.getIntroduction(), tutor.getRegionCode(), tutor.getNation(), tutor.getBirthday(),
                        tutor.getWorkUnit(), tutor.getPolitical(), tutor.getAddress(), tutor.getPhone(),
                        tutor.getMasterSchool(), tutor.getDoctorSchool(), tutor.getId() });
    }

    @Override
    public Map<Long, String> getIdAndRealName() {
        return findForMap(getSql(get_user_teacher_id_and_real_name), new MapRowMapper<Long, String>() {

            @Override
            public Long mapRowKey(ResultSet rs, int arg1) throws SQLException {
                return rs.getLong("ID");
            }

            @Override
            public String mapRowValue(ResultSet rs, int arg1) throws SQLException {
                return rs.getString("REALNAME");
            }

        });
    }

    @Override
    public int getTutorCountOfAuditing() {
        String sql = "SELECT COUNT(1) FROM T_USER_TEACHER WHERE AUDIT_STATUS = ?";
        return findForInt(sql, new Object[] { AuditStatusType.AUDITING.getValue() });
    }
}
