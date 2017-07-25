/*
 * @(#)UserDao.java    Created on 2015年11月4日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.basic;

import java.util.List;
import java.util.Map;

import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.user.UserTeacher;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2015年11月4日 下午2:21:34 $
 */
public interface UserTeacherDao {

    /**
     * 根据条件查询教师列表
     *
     * @param conditions
     * @param isTeacherRec
     * @param page
     * @return
     */
    public List<UserTeacher> getUserTeacherListByCondition(List<QueryCondition> conditions, boolean isTeacherRec,
            PageDto page);

    /**
     * 新增名师大家
     *
     * @param teacher
     * @return
     */
    public int addUserTeacher(UserTeacher teacher);

    /**
     * 获取未被推荐到前台的名师大家数量
     *
     * @param teacherIds
     * @return
     */
    public int getNoRecommendTeacherNum(Long[] teacherIds);

    /**
     * 删除名师大家
     *
     * @param teacherIds
     */
    public int batchDeleteTeacher(Long[] teacherIds);

    /**
     * 根据ID获取教师
     *
     * @param teacherId
     * @return
     */
    public UserTeacher getUserTeacherById(long teacherId);

    /**
     * 修改教师
     *
     * @param teacher
     * @return
     */
    public int updateUserTeacher(UserTeacher teacher);

    /**
     * 修改教师推荐状态
     *
     * @param ids
     * @param isRecommend
     * @return
     */
    public int updateTeacherRecommend(Long[] ids, boolean isRecommend);

    /**
     * 修改排序号
     *
     * @param id
     * @param recommendSeq
     * @return
     */
    public int updateTeacherRecommendSeq(long id, int recommendSeq);

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public UserTeacher getUserTeacherInfoById(long id);

    /**
     * 更新审核状态
     *
     * @param tutorId
     * @param auditStu
     * @param auditMsg
     * @param auditRealName
     * @return
     */
    public int updateAuditStatus(long tutorId, int auditStu, String auditMsg, String auditRealName);

    /**
     * 更新音乐导师
     *
     * @param tutor
     * @return
     */
    public int updateMusicTuorById(UserTeacher tutor);

    /**
     * 获取id和realname
     *
     * @return
     */
    public Map<Long, String> getIdAndRealName();

    /**
     * 获取正在审核中的记录数
     *
     * @return
     */
    public int getTutorCountOfAuditing();

}
