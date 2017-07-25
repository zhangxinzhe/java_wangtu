/*
 * @(#)UserDao.java    Created on 2015年11月4日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.basic;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.user.User;
import net.zdsoft.common.enums.SexType;
import net.zdsoft.common.enums.StatusEunm;
import net.zdsoft.common.enums.UserType;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2015年11月4日 下午2:21:34 $
 */
public interface UserDao {
    /**
     * 根据条件查询用户列表
     *
     * @param conditions
     * @param page
     * @return
     */
    public List<User> getUserListByCondition(List<QueryCondition> conditions, PageDto page);

    /**
     * 验证用户名是否已存在
     *
     * @param username
     * @return
     */
    public boolean validateUserName(String username);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    public int addUser(User user);

    /**
     * 根据培训基地ids重置用户密码
     *
     * @param agencyIds
     * @return
     */
    public int updateResetUserPassword(Long[] agencyIds);

    /**
     * 根据用户Ids重置密码
     *
     * @param ids
     * @return
     */
    public int updateResetUserPasswordByIds(Long[] ids);

    /**
     * 修改培训基地注销状态
     *
     * @param agencyIds
     * @param status
     * @return
     */
    public int updateUserCancelStatusByAgencyId(Long[] agencyIds, StatusEunm status);

    /**
     * 根据agencyId修改密码
     *
     * @param agencyId
     * @param password
     * @return
     */
    public int updateUserByAgency(long agencyId, String password);

    /**
     * 根据培训基地IDs删除用户
     *
     * @param agencyIds
     * @return
     */
    public int deleteUserByAgencyIds(Long[] agencyIds);

    /**
     * 修改培训基地注销状态
     *
     * @param userIds
     * @param status
     * @return
     */
    public int updateUserCancelStatus(Long[] userIds, StatusEunm status);

    /**
     * 获取主键
     *
     * @return
     */
    public long generatePrimaryKey();

    /**
     * 删除用户
     *
     * @param teacherIds
     */
    public int deleteUserByIds(Long[] ids);

    /**
     * 根据ID修改姓名和密码
     *
     * @param id
     * @param realname
     * @param password
     * @return
     */
    public int updateUserById(long id, String realname, String password);

    /**
     * 根据ids获取map<id,user>
     *
     * @param ids
     * @return
     */
    public Map<Long, User> getUserMapByIds(Long[] ids);

    /**
     * 根据id获取user
     *
     * @param userId
     * @return
     */
    public User getUserById(long userId);

    /**
     * 获取用户名相同的数（userId为0时 表示新增数据）
     *
     * @param userName
     * @param userId
     * @return
     */
    public int getUserCountByUserName(String userName, long userId);

    /**
     * 助教信息修改
     *
     * @param assistant
     * @return
     */
    public int updateUser4Assistant(User assistant);

    /**
     * 根据用户ids查询用户密码
     *
     * @param ids
     * @return
     */
    public List<User> getUserPwdByIds(Long[] ids);

    /**
     * 根据id修改个人用户会员状态（IS_MEMBER）
     *
     * @param userId
     * @return
     */
    public int updataIsMemberState(long userId, YesNoType isMember);

    /**
     * 根据id修改联合会会员状态（IS_UNION_MEMBER）
     *
     * @param userId
     * @return
     */
    public int updataIsUnionMemberState(long userId, YesNoType isUnionMember);

    /**
     * 根据id修改音乐导师状态（IS_TUTOR）
     *
     * @param userId
     * @return
     */
    public int updataIsTutorState(long userId, YesNoType isTutor);

    /**
     * 是否是音乐导师
     *
     * @param tutorId
     *
     * @param type
     * @return
     */
    public int updateIsTutor(long tutorId, YesNoType type);

    /**
     * 判断手机号是否已绑定用户
     *
     * @param phone
     * @param userType
     * @return
     */
    public Boolean mainPhoneIsBind(String phone, UserType userType);

    /**
     * 提交音乐导师时，更新修改
     *
     * @param id
     * @param sex
     * @param email
     * @return
     */
    public int updateUser4TutorTeahcer(long id, SexType sex, String email);

    /**
     * 根据用户名和用户类别获取user
     *
     * @param userName
     * @param type
     * @return
     */
    public User getUserByUserName(String userName);

    /**
     * 根据用户名和密码user
     *
     * @param userName
     * @return
     */
    public User getUserByUserNameAndPassward(String userName, String passward);

    /**
     * 根据分组类型验证是否存在记录
     *
     * @param groupTypeId
     * @return
     */
    public boolean isExistsGroupTypeByGroupTypeId(long groupTypeId);

    /**
     * 更新
     *
     * @param student
     * @return
     */
    public long updateUser4Student(User student);

    /**
     * 获取id 和名字
     *
     * @return
     */
    public Map<Long, String> getIdAndRealName();

    /**
     * 更新hifi会员状态
     */
    public void updateHifiMember();

    /**
     * hifi会员累加30天
     *
     * @param userId
     */
    public int hifiDateAdd4Member(long userId, int days);

    /**
     * 菲hifi会员加30天
     *
     * @param userId
     * @return
     */
    public int hifiDateAdd4Common(long userId, int days);

    /**
     * 修改hifi会员的到期时间
     *
     * @param userId
     * @param endDate
     * @return
     */
    public int hifiDateUpdate(long userId, Date endDate);

    /**
     * 修改头像
     *
     * @param student
     * @return
     */
    public long updateUserAvatarUser(String avatarPath, long userId);

}
