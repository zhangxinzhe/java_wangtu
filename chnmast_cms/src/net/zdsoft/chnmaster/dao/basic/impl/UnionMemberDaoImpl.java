/*
 * @(#)UnionMemberDaoImpl.java    Created on 2016年5月17日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * ${user}$
 */
package net.zdsoft.chnmaster.dao.basic.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.basic.UnionMemberDao;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.dao.queryCondition.QueryConditionBuilder;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.user.UnionMember;
import net.zdsoft.common.entity.user.mapper.UnionMemberRowMapper;
import net.zdsoft.common.enums.AuditStatusType;
import net.zdsoft.keel.util.Validators;

/**
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年5月17日 上午10:27:09 $
 */
@Repository("unionMemberDao")
public class UnionMemberDaoImpl extends BaseDaoImpl implements UnionMemberDao {

    private static final String update_union_member_by_id = "basic.update_union_member_by_id";

    private static final String get_union_member_list = "basic.get_union_member_list";
    private static final String get_union_member_by_id = "basic.get_union_member_by_id";
    private static final String count_union_member_by_union_code = "basic.count_union_member_by_union_code";
    private static final String update_union_member_audit_status_and_unionc_code_by_id = "basic.update_union_member_audit_status_and_unionc_code_by_id";
    private static final String update_union_member = "basic.update_union_member";
    private static final String get_union_member_max_union_code_by_condition = "basic.get_union_member_max_union_code_by_condition";

    @SuppressWarnings("unchecked")
    @Override
    public List<UnionMember> getUnionMemberListByCondition(List<QueryCondition> condition, PageDto page) {
        QueryConditionBuilder builder = new QueryConditionBuilder();
        builder.addConditions(condition);

        StringBuilder sql = new StringBuilder(getSql(get_union_member_list));
        if (!Validators.isEmpty(builder.buildCondition())) {
            sql.append(" WHERE " + builder.buildCondition());
        }
        sql.append(" ORDER BY APPLY_DATE DESC");

        if (null == page) {
            return this.find(sql.toString(), builder.buildParameters(), UnionMemberRowMapper.instance());
        }
        else {
            return this.findForPage(sql.toString(), builder.buildParameters(), UnionMemberRowMapper.instance(), page);
        }
    }

    @Override
    public UnionMember getUnionMemberById(long unionMemberId) {
        return (UnionMember) findForObject(getSql(get_union_member_by_id), new Object[] { unionMemberId },
                UnionMemberRowMapper.instance());
    }

    @Override
    public int countUnionCode(String unionCode) {
        return findForInt(getSql(count_union_member_by_union_code), new Object[] { unionCode });
    }

    @Override
    public int updateAuditStatusAndUnionCodeById(int auditStatus, String unionCode, long unionMemberId) {
        return executeUpdate(getSql(update_union_member_audit_status_and_unionc_code_by_id),
                new Object[] { auditStatus, unionCode, unionMemberId });
    }

    @Override
    public int update(UnionMember m) {
        return executeUpdate(getSql(update_union_member_by_id),
                new Object[] { m.getUserId(), m.getRealName(), m.getUnionMemberType().getValue(), m.getUnionCode(),
                        m.getSex().getValue(), m.getNation(), m.getBirthday(), m.getDegree(), m.getGraduateSchool(),
                        m.getMajor(), m.getTeacherYear(), m.getPolitical(), m.getEmail(), m.getWorkUnit(),
                        m.getWorkDept(), m.getWorkDuty(), m.getTelephone(), m.getRegionCode(), m.getAddress(),
                        m.getIntro(), m.getOpinion(), m.getPhotoFile(), m.getPaperFile(), m.getAgencyName(),
                        m.getAgencyAddress(), m.getAgencyYear(), m.getAgencyFund(), m.getAgencyBranchNum(),
                        m.getAgencyBrand(), m.getAgencyMode(), m.getAgencyFeature(), m.getAgencyEmployeeNum(),
                        m.getAgencyTraineeNum(), m.getAgencyPrincipal(), m.getApplyDate(),
                        m.getUnionAuditStatus().getValue(), m.getAuditDate(), m.getAuditRealName(), m.getAuditMsg(),
                        m.getId() });
    }

    @Override
    public String generateMaxUnionCode(String code) {
        return (String) findForObject(getSql(get_union_member_max_union_code_by_condition),
                new Object[] { code + "______" }, new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet rs, int arg1) throws SQLException {
                        return rs.getString("MAX_VALUE");
                    }
                });
    }

    @Override
    public int updateUnionMember(UnionMember member) {
        return executeUpdate(getSql(update_union_member),
                new Object[] { member.getRealName(), member.getSex() == null ? 0 : member.getSex().getValue(),
                        member.getNation(), member.getBirthday(), member.getDegree(), member.getGraduateSchool(),
                        member.getMajor(), member.getTeacherYear() == null ? 0 : member.getTeacherYear(),
                        member.getPolitical(), member.getEmail(), member.getWorkUnit(), member.getWorkDept(),
                        member.getWorkDuty(), member.getTelephone(), member.getRegionCode(), member.getAddress(),
                        member.getIntro(), member.getOpinion(), member.getPhotoFile(), member.getPaperFile(),
                        member.getAgencyName(), member.getAgencyAddress(),
                        member.getAgencyYear() == null ? 0 : member.getAgencyYear(), member.getAgencyFund(),
                        member.getAgencyBranchNum() == null ? 0 : member.getAgencyBranchNum(), member.getAgencyBrand(),
                        member.getAgencyMode(), member.getAgencyFeature(),
                        member.getAgencyEmployeeNum() == null ? 0 : member.getAgencyEmployeeNum(),
                        member.getAgencyTraineeNum() == null ? 0 : member.getAgencyTraineeNum(),
                        member.getAgencyPrincipal(), member.getReferrer(), member.getId() });
    }

    @Override
    public boolean isExistsOtherMemByUserIdAndAudit(long userId, long exceptId) {
        String sql = "SELECT COUNT(1) FROM T_USER_UNION_MEMBER WHERE USERID = ? AND AUDIT_STATUS = 2 AND ID != ?";
        return findForInt(sql, new Object[] { userId, exceptId }) > 0 ? true : false;
    }

    @Override
    public int updateAuditStatus(long unionMemberId, int auditStu, String code, String realName, String auditMsg) {
        String sql = "UPDATE T_USER_UNION_MEMBER SET UNION_CODE = ?, AUDIT_STATUS = ? ,AUDIT_DATE = ?,AUDIT_REALNAME = ?,AUDIT_MSG = ? WHERE ID = ? ";
        Object[] args = {};
        if (auditStu == 2 || auditStu == 3) {// 通过/不通过审核
            sql = sql.concat(" AND  AUDIT_STATUS  = 1 ");
            args = new Object[] { code, auditStu, new Date(), realName, auditMsg, unionMemberId };
        }
        else if (auditStu == 1) {// 取消审核
            sql = sql.concat(" AND  AUDIT_STATUS  IN(2,3) ");
            args = new Object[] { code, auditStu, null, "", auditMsg, unionMemberId };
        }
        else {
            return 0;
        }
        return executeUpdate(sql, args);
    }

    @Override
    public int getMemberCountOfAuditing(boolean isTeam) {
        String sql = "SELECT COUNT(1) FROM T_USER_UNION_MEMBER WHERE AUDIT_STATUS = ?";
        if (isTeam) {
            sql += " AND UNION_TYPE IN (3, 4)";
        }
        else {
            sql += " AND UNION_TYPE IN (1, 2)";
        }
        return findForInt(sql, new Object[] { AuditStatusType.AUDITING.getValue() });
    }
}
