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

import net.zdsoft.chnmaster.dao.basic.UserDao;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.dao.queryCondition.QueryConditionBuilder;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.user.User;
import net.zdsoft.common.entity.user.mapper.UserRowMapper;
import net.zdsoft.common.enums.SexType;
import net.zdsoft.common.enums.StatusEunm;
import net.zdsoft.common.enums.UserRegTypeEnum;
import net.zdsoft.common.enums.UserType;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.common.utils.Util;
import net.zdsoft.keel.jdbc.MapRowMapper;
import net.zdsoft.keel.jdbc.MultiRowMapper;
import net.zdsoft.keel.util.DateUtils;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2015年11月4日 下午2:22:07 $
 */
@SuppressWarnings("unchecked")
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    private static final String list_user = "user.list_user";
    private static final String validate_username = "user.validate_username";
    private static final String add_user = "user.add_user";
    private static final String update_reset_user_password = "user.update_reset_user_password";
    private static final String update_reset_user_password_by_ids = "user.update_reset_user_password_by_ids";
    private static final String update_user_cancel_status_by_agencyids = "user.update_user_cancel_status_by_agencyids";
    private static final String update_user_by_agency = "user.update_user_by_agency";
    private static final String delete_user_by_agencyids = "user.delete_user_by_agencyids";
    private static final String update_user_cancel_status = "user.update_user_cancel_status";
    private static final String delete_user_by_ids = "user.delete_user_by_ids";
    private static final String update_user_by_id = "user.update_user_by_id";
    private static final String get_user_map_by_ids = "user.get_user_map_by_ids";
    private static final String get_user_by_id = "user.get_user_by_id";
    private static final String get_count_user_by_username = "user.get_count_user_by_username";
    private static final String update_user_for_assistant = "user.update_user_for_assistant";
    private static final String get_user_pwd_by_ids = "user.get_user_pwd_by_ids";
    private static final String update_is_member_by_id = "user.update_is_member_by_id";
    private static final String update_is_union_member_by_id = "user.update_is_union_member_by_id";
    private static final String update_is_tutor_by_id = "user.update_is_tutor_by_id";
    private static final String isExists_mainphone_is_bind = "user.isExists_mainphone_is_bind";
    private static final String update_user_for_student = "user.update_user_for_student";
    private static final String get_id_and_real_name = "user.get_id_and_real_name";
    private static final String update_hifi_member = "user.update_hifi_member";

    @Override
    public boolean validateUserName(String username) {
        return findForInt(getSql(validate_username), new Object[] { username }) > 0 ? true : false;
    }

    @Override
    public int addUser(User user) {
        if (user.getId() <= 0) {
            user.setId(generatePrimaryKey("T_USER"));
        }
        int sex = SexType.UNKNOW.getValue();
        if (user.getSex() != null) {
            sex = user.getSex().getValue();
        }
        return executeUpdate(getSql(add_user),
                new Object[] { user.getId(), user.getUserName(), user.getPassword(), user.getRealName(),
                        user.getSchoolName(), user.getCreateTime(), user.getUserType().getValue(), user.getSpellName(),
                        user.getShortSpell(), user.getAgencyId(), user.getRemark(), user.getPhone(), sex, user.getQq(),
                        user.getEmail(), user.getGroupTypeId() });
    }

    @Override
    public int updateResetUserPassword(Long[] agencyIds) {
        return executeUpdateForInSQL(getSql(update_reset_user_password), new Object[] { Util.encodeFixPassword() },
                agencyIds);
    }

    @Override
    public int updateResetUserPasswordByIds(Long[] ids) {
        return executeUpdateForInSQL(getSql(update_reset_user_password_by_ids),
                new Object[] { Util.encodeFixPassword() }, ids);
    }

    @Override
    public int updateUserCancelStatusByAgencyId(Long[] agencyIds, StatusEunm status) {
        return executeUpdateForInSQL(getSql(update_user_cancel_status_by_agencyids), new Object[] { status.getValue() },
                agencyIds);
    }

    @Override
    public int updateUserByAgency(long agencyId, String password) {
        return executeUpdate(getSql(update_user_by_agency), new Object[] { password, agencyId });
    }

    @Override
    public List<User> getUserListByCondition(List<QueryCondition> conditions, PageDto page) {
        StringBuffer sb = new StringBuffer(getSql(list_user));
        QueryConditionBuilder builder = new QueryConditionBuilder();
        builder.addConditions(conditions);
        if (StringUtils.isNotBlank(builder.buildCondition())) {
            sb.append(" WHERE ");
        }
        sb.append(builder.buildCondition());
        sb.append(" ORDER BY CREATETIME DESC");
        if (page == null) {
            return find(sb.toString(), builder.buildParameters(), UserRowMapper.instance());
        }
        else {
            return findForPage(sb.toString(), builder.buildParameters(), UserRowMapper.instance(), page);
        }
    }

    @Override
    public int deleteUserByAgencyIds(Long[] agencyIds) {
        return executeUpdateForInSQL(getSql(delete_user_by_agencyids), null, agencyIds);
    }

    @Override
    public long generatePrimaryKey() {
        return generatePrimaryKey("T_USER");
    }

    @Override
    public int updateUserCancelStatus(Long[] agencyIds, StatusEunm status) {
        return executeUpdateForInSQL(getSql(update_user_cancel_status), new Object[] { status.getValue() }, agencyIds);
    }

    @Override
    public int deleteUserByIds(Long[] ids) {
        return executeUpdateForInSQL(getSql(delete_user_by_ids), null, ids);
    }

    @Override
    public int updateUserById(long id, String realname, String password) {
        return executeUpdate(getSql(update_user_by_id), new Object[] { realname, password, id });
    }

    @Override
    public Map<Long, User> getUserMapByIds(Long[] ids) {
        return findForInSQL(getSql(get_user_map_by_ids), null, ids, UserRowMapper.userMapRowMap);
    }

    @Override
    public User getUserById(long userId) {
        return (User) findForObject(getSql(get_user_by_id), new Object[] { userId }, UserRowMapper.instance());
    }

    @Override
    public int getUserCountByUserName(String userName, long userId) {
        String sql = getSql(get_count_user_by_username);
        Object[] param = null;
        if (userId != 0) {
            sql += " AND ID <> ? ";
            param = new Object[] { userName, userId };
        }
        else {
            param = new Object[] { userName };
        }
        return findForInt(sql, param);
    }

    @Override
    public int updateUser4Assistant(User assistant) {
        return this.executeUpdate(getSql(update_user_for_assistant),
                new Object[] { assistant.getUserName(), assistant.getPassword(), assistant.getRealName(),
                        assistant.getSpellName(), assistant.getShortSpell(), assistant.getRemark(),
                        assistant.getId() });
    }

    @Override
    public List<User> getUserPwdByIds(Long[] ids) {
        return findForInSQL(getSql(get_user_pwd_by_ids), null, ids, new MultiRowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("ID"));
                user.setPassword(Util.decodePassword(rs.getString("PASSWORD")));
                return user;
            }
        });
    }

    @Override
    public int updataIsMemberState(long userId, YesNoType isMember) {
        return executeUpdate(getSql(update_is_member_by_id), new Object[] { isMember.getValue(), userId });
    }

    @Override
    public int updataIsUnionMemberState(long userId, YesNoType isUnionMember) {
        return executeUpdate(getSql(update_is_union_member_by_id), new Object[] { isUnionMember.getValue(), userId });
    }

    @Override
    public int updataIsTutorState(long userId, YesNoType isTutor) {
        return executeUpdate(getSql(update_is_tutor_by_id), new Object[] { isTutor.getValue(), userId });
    }

    @Override
    public int updateIsTutor(long tutorId, YesNoType type) {
        String sql = "UPDATE T_USER SET IS_TUTOR = ? WHERE ID = ?";
        return executeUpdate(sql, new Object[] { type.getValue(), tutorId });
    }

    @Override
    public Boolean mainPhoneIsBind(String phone, UserType userType) {
        return findForInt(getSql(isExists_mainphone_is_bind), new Object[] { phone, userType.getValue() }) > 0 ? true
                : false;
    }

    @Override
    public int updateUser4TutorTeahcer(long id, SexType sex, String email) {
        String sql = "UPDATE T_USER SET SEX = ?,EMAIL=? WHERE ID = ?";
        return executeUpdate(sql, new Object[] { sex.getValue(), email, id });
    }

    @Override
    public User getUserByUserName(String userName) {
        String sql = "SELECT * FROM T_USER WHERE USERNAME = ? ";
        return (User) findForObject(sql, new Object[] { userName }, UserRowMapper.instance());
    }

    @Override
    public boolean isExistsGroupTypeByGroupTypeId(long groupTypeId) {
        String sql = "SELECT COUNT(1) FROM T_USER WHERE GROUP_TYPE_ID = ?";
        return findForInt(sql, new Object[] { groupTypeId }) > 0 ? true : false;
    }

    @Override
    public long updateUser4Student(User student) {
        return executeUpdate(getSql(update_user_for_student),
                new Object[] { student.getRealName(), student.getPhone(), student.getSex().getValue(), student.getQq(),
                        student.getEmail(), student.getRemark(), student.getPassword(), student.getGroupTypeId(),
                        student.getId(), UserRegTypeEnum.BACK_ADD.getValue() });
    }

    @Override
    public Map<Long, String> getIdAndRealName() {
        return findForMap(getSql(get_id_and_real_name), new MapRowMapper<Long, String>() {

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
    public void updateHifiMember() {
        this.executeUpdate(getSql(update_hifi_member), new Object[] { new Date() });
    }

    @Override
    public int hifiDateAdd4Member(long userId, int days) {
        String sql = "UPDATE T_USER T SET T.HIFI_MEMBER_DATE=date_add(HIFI_MEMBER_DATE, INTERVAL ? day) WHERE T.ID=? AND T.IS_HIFI_MEMBER=1";
        return executeUpdate(sql, new Object[] { days, userId });
    }

    @Override
    public int hifiDateAdd4Common(long userId, int days) {
        String sql = "UPDATE T_USER T SET T.IS_HIFI_MEMBER=1,T.HIFI_MEMBER_DATE=? WHERE T.ID=? AND T.IS_HIFI_MEMBER=0";
        Date now = new Date();
        return executeUpdate(sql, new Object[] { DateUtils.addDay(DateUtils.getEndDate(now), days), userId });
    }

    @Override
    public int hifiDateUpdate(long userId, Date endDate) {
        String sql = "UPDATE T_USER T SET T.IS_HIFI_MEMBER=1,T.HIFI_MEMBER_DATE=? WHERE T.ID=? ";
        return executeUpdate(sql, new Object[] { endDate, userId });
    }

    @Override
    public User getUserByUserNameAndPassward(String userName, String passward) {
        String sql = "SELECT * FROM T_USER WHERE USERNAME = ? AND PASSWORD=? ";
        return (User) findForObject(sql, new Object[] { userName, passward }, UserRowMapper.instance());
    }

    @Override
    public long updateUserAvatarUser(String avatarPath, long userId) {
        String sql = "UPDATE T_USER SET AVATAR_FILE =? WHERE ID=? ";
        return executeUpdate(sql, new Object[] { avatarPath, userId });
    }

    @Override
    public int updateAppUser(User user) {
        String sql = "UPDATE T_USER SET REALNAME =?,SEX=?,BIRTHDAY=?,industry=?,PHONE=? WHERE ID=? ";
        return executeUpdate(sql, new Object[] { user.getRealName(), user.getSex().getValue(), user.getBirthday(),
                user.getIndustry(), user.getPhone(), user.getId() });
    }

}
