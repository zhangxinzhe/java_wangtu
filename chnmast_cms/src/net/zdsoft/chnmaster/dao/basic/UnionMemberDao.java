/*
 * @(#)UnionMemberDao.java    Created on 2016年5月17日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * ${user}$
 */
package net.zdsoft.chnmaster.dao.basic;

import java.util.List;

import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.user.UnionMember;

public interface UnionMemberDao {

    /**
     * 分页获取列表信息
     *
     * @param conditons
     * @param page
     * @return
     */
    public List<UnionMember> getUnionMemberListByCondition(List<QueryCondition> conditions, PageDto page);

    /**
     * 根据id获取
     *
     * @param unionMemberId
     * @return
     */
    public UnionMember getUnionMemberById(long unionMemberId);

    /**
     * 计数union_code
     *
     * @param unionCode
     * @return
     */
    public int countUnionCode(String unionCode);

    /**
     * 跟id更新审核状态
     *
     * @param unionCode
     *
     * @param i
     * @param unionMemberId
     * @return
     */
    public int updateAuditStatusAndUnionCodeById(int auditStatus, String unionCode, long unionMemberId);

    /**
     * 更新
     *
     * @param m
     * @return
     */
    public int update(UnionMember m);

    /**
     * 获取最大的会员编号<br>
     * 注：左匹配code字符串右侧6位模糊搜索
     *
     * @param code
     * @return
     */
    public String generateMaxUnionCode(String code);

    /**
     * 修改联合会员
     *
     * @param member
     * @return
     */
    public int updateUnionMember(UnionMember member);

    /**
     * 判断用户是否有其他审核通过的记录（除去exceptId的记录）
     *
     * @param userId
     * @param exceptId
     * @return
     */
    public boolean isExistsOtherMemByUserIdAndAudit(long userId, long exceptId);

    /**
     * 更新审核状态
     *
     * @param unionMemberId
     * @param auditStu
     *            审核状态
     * @param code
     *            会员编号
     * @param realName
     *            审核人
     * @param auditMsg
     *            （条件：审核不通过）审核不通过的信息
     * @return
     */
    public int updateAuditStatus(long unionMemberId, int auditStu, String code, String realName, String auditMsg);

    /**
     * 获取正在审核中的记录数
     *
     * @return
     */
    public int getMemberCountOfAuditing(boolean isTeam);
}
