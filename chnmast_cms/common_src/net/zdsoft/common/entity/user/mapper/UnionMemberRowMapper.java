/*
 * @(#)UnionMemberRowMapper.java    Created on 2016年5月16日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * ${user}$
 */
package net.zdsoft.common.entity.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.user.UnionMember;
import net.zdsoft.common.enums.AuditStatusType;
import net.zdsoft.common.enums.SexType;
import net.zdsoft.common.enums.UnionMemberType;

@SuppressWarnings("rawtypes")
public class UnionMemberRowMapper implements RowMapper {

    private static UnionMemberRowMapper rowMapper = new UnionMemberRowMapper();

    public static UnionMemberRowMapper instance() {
        return rowMapper;
    }

    @Override
    public Object mapRow(ResultSet rs, int arg1) throws SQLException {
        UnionMember obj = new UnionMember();
        obj.setId(rs.getLong("ID"));
        obj.setUserId(rs.getLong("USERID"));
        obj.setRealName(rs.getString("REALNAME"));
        obj.setUnionMemberType(UnionMemberType.get(rs.getInt("UNION_TYPE")));
        obj.setUnionCode(rs.getString("UNION_CODE"));
        obj.setSex(SexType.get(rs.getInt("SEX")));
        obj.setNation(rs.getString("NATION"));
        obj.setBirthday(rs.getDate("BIRTHDAY"));
        obj.setDegree(rs.getString("DEGREE"));
        obj.setGraduateSchool(rs.getString("GRADUATE_SCHOOL"));
        obj.setMajor(rs.getString("MAJOR"));
        obj.setTeacherYear(rs.getString("TEACH_YEAR") == null ? null : rs.getFloat("TEACH_YEAR"));
        obj.setPolitical(rs.getString("POLITICAL"));
        obj.setEmail(rs.getString("EMAIL"));
        obj.setWorkUnit(rs.getString("WORK_UNIT"));
        obj.setWorkDept(rs.getString("WORK_DEPT"));
        obj.setWorkDuty(rs.getString("WORK_DUTY"));
        obj.setTelephone(rs.getString("TELEPHONE"));
        obj.setRegionCode(rs.getString("REGIONCODE"));
        obj.setAddress(rs.getString("ADDRESS"));
        obj.setReferrer(rs.getString("REFERRER"));
        obj.setIntro(rs.getString("INTRO"));
        obj.setOpinion(rs.getString("OPINION"));
        obj.setPhotoFile(rs.getString("PHOTO_FILE"));
        obj.setPaperFile(rs.getString("PAPER_FILE"));
        obj.setAgencyName(rs.getString("AGENCY_NAME"));
        obj.setAgencyAddress(rs.getString("AGENCY_ADDRESS"));
        obj.setAgencyYear(rs.getString("AGENCY_YEAR") == null ? null : rs.getFloat("AGENCY_YEAR"));
        obj.setAgencyFund(rs.getString("AGENCY_FUND"));
        obj.setAgencyBranchNum(rs.getString("AGENCY_BRANCH_NUM") == null ? null : rs.getInt("AGENCY_BRANCH_NUM"));
        obj.setAgencyBrand(rs.getString("AGENCY_BRAND"));
        obj.setAgencyMode(rs.getString("AGENCY_MODE"));
        obj.setAgencyFeature(rs.getString("AGENCY_FEATURE"));
        obj.setAgencyEmployeeNum(rs.getString("AGENCY_EMPLOYEE_NUM") == null ? null : rs.getInt("AGENCY_EMPLOYEE_NUM"));
        obj.setAgencyTraineeNum(rs.getString("AGENCY_TRAINEE_NUM") == null ? null : rs.getInt("AGENCY_TRAINEE_NUM"));
        obj.setAgencyPrincipal(rs.getString("AGENCY_PRINCIPAL"));
        obj.setApplyDate(rs.getTimestamp("APPLY_DATE"));
        obj.setUnionAuditStatus(AuditStatusType.get(rs.getInt("AUDIT_STATUS")));
        obj.setAuditDate(rs.getTimestamp("AUDIT_DATE"));
        obj.setAuditRealName(rs.getString("AUDIT_REALNAME"));
        obj.setAuditMsg(rs.getString("AUDIT_MSG"));
        return obj;
    }

}
