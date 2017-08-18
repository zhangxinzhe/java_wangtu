/*
 * @(#)UserService.java    Created on 2015年11月4日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.basic;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.user.User;
import net.zdsoft.common.enums.StatusEunm;
import net.zdsoft.common.enums.UserType;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.common.upload.UploadFile;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2015年11月4日 下午2:25:29 $
 */
public interface UserService {

    /**
     * 验证用户名是否已存在
     *
     * @param username
     * @return
     */
    public boolean validateUserName(String username);

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
     * 根据条件查询用户列表
     *
     * @param conditions
     * @param page
     * @return
     */
    public List<User> getUserListByCondition(List<QueryCondition> conditions, PageDto page);

    /**
     * 根据条件查询用户列表
     *
     * @param conditions
     * @param page
     * @return
     */
    public List<User> getUserListByConditionWithGroupType(List<QueryCondition> conditions, PageDto page);

    /**
     * 修改用户注销状态
     *
     * @param userIds
     * @param status
     * @return
     */
    public int updateUserCancelStatus(Long[] userIds, StatusEunm status);

    /**
     * 根据ids获取map<id,user>
     *
     * @param ids
     * @return
     */
    public Map<Long, User> getUserMapByIds(Long[] ids);

    /**
     * 异步修改realname<br>
     *
     * T_COURSE：TEA_REALNAME、ASS_REALNAME<br>
     * T_WXB_LOG：REALNAME
     */
    public void updateRealNameAsyn(String newRealName, long userId);

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
     * 新增用户
     *
     * @param user
     * @return
     */
    public int addUser(User user);

    /**
     * 新增用户并且发送短信
     *
     * @param user
     * @return
     */
    public int addUserAndSendMessage(User user);

    /**
     * 助教信息修改
     *
     * @param assistant
     * @return
     */
    public int updateUser4Assistant(User assistant);

    /**
     * 删除用户
     *
     * @param teacherIds
     */
    public int deleteUserByIds(Long[] ids);

    /**
     * 根据用户ids查询用户密码
     *
     * @param ids
     * @return
     */
    public List<User> getUserPwdByIds(Long[] ids);

    /**
     * 根据id修改个人用户会员状态
     *
     * @param userId
     * @return
     */
    public int updataIsMemberState(long userId, YesNoType isMember);

    /**
     * 导入个人用户
     *
     * @param file
     * @param baseUser
     * @param ip
     * @return
     * @throws Exception
     */
    public String importStudents(UploadFile file, long groupTypeId, BaseUser baseUser, String ip) throws Exception;

    /**
     * 验证手机号
     *
     * @param phone
     * @param type
     * @return
     */
    public Boolean validatePhone(String phone, UserType type);

    /**
     * 查看个人用户导入结果
     *
     * @param id
     * @return
     */
    public String viewStudentResultExcel(Long id);

    /**
     * 根据用户名和用户类别获取user
     *
     * @param userName
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
     * 后台更新
     *
     * @param student
     * @return
     */
    public long updateUser4Student(User student);

    /**
     * 更新hifi会员状态(过期会员检查)
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

    /**
     * 前台用户修改个人信息
     *
     * @param user
     * @return
     */
    public int updateAppUser(User user);
}
