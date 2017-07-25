/*
 * @(#)UserServiceImpl.java    Created on 2015年11月4日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.basic.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.config.Config;
import net.zdsoft.chnmaster.dao.basic.UserDao;
import net.zdsoft.chnmaster.entity.user.UserImport;
import net.zdsoft.chnmaster.service.account.AccountService;
import net.zdsoft.chnmaster.service.basic.UserGroupTypeService;
import net.zdsoft.chnmaster.service.basic.UserService;
import net.zdsoft.chnmaster.service.common.BaseServiceImpl;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.account.Account;
import net.zdsoft.common.entity.user.User;
import net.zdsoft.common.entity.user.UserGroupType;
import net.zdsoft.common.enums.PathType;
import net.zdsoft.common.enums.StatusEunm;
import net.zdsoft.common.enums.UserRegTypeEnum;
import net.zdsoft.common.enums.UserType;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.common.excel.ExcelImport;
import net.zdsoft.common.excel.ExportUtil;
import net.zdsoft.common.filesystem.util.FileSystemUtil;
import net.zdsoft.common.service.BeanSelfAware;
import net.zdsoft.common.upload.FilePathBuilder;
import net.zdsoft.common.upload.UploadFile;
import net.zdsoft.common.utils.OSUtil;
import net.zdsoft.common.utils.PinyinUtil;
import net.zdsoft.common.utils.Util;
import net.zdsoft.keel.util.Validators;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2015年11月4日 下午2:26:05 $
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements BeanSelfAware, UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private UserGroupTypeService userGroupTypeService;
    private UserService selfBean;
    @Resource
    private AccountService accountService;

    @Override
    public boolean validateUserName(String username) {
        return userDao.validateUserName(username);
    }

    @Override
    public int updateResetUserPassword(Long[] agencyIds) {
        return userDao.updateResetUserPassword(agencyIds);
    }

    @Override
    public int updateResetUserPasswordByIds(Long[] ids) {
        return userDao.updateResetUserPasswordByIds(ids);
    }

    @Override
    public List<User> getUserListByCondition(List<QueryCondition> conditions, PageDto page) {
        return userDao.getUserListByCondition(conditions, page);
    }

    @Override
    public List<User> getUserListByConditionWithGroupType(List<QueryCondition> conditions, PageDto page) {
        List<User> list = userDao.getUserListByCondition(conditions, page);
        List<Long> groupTypeIds = new ArrayList<Long>();
        for (User u : list) {
            if (u.getGroupTypeId() > 0) {
                groupTypeIds.add(u.getGroupTypeId());
            }
        }
        Map<Long, UserGroupType> groupMap = userGroupTypeService
                .getUserGroupTypeMap(groupTypeIds.toArray(new Long[groupTypeIds.size()]));
        for (User u : list) {
            UserGroupType type = groupMap.get(u.getGroupTypeId());
            if (type != null) {
                u.setGroupTypeTitle(type.getTitle());
            }
        }
        return list;
    }

    @Override
    public int updateUserCancelStatus(Long[] userIds, StatusEunm status) {
        return userDao.updateUserCancelStatus(userIds, status);
    }

    @Override
    public Map<Long, User> getUserMapByIds(Long[] ids) {
        return userDao.getUserMapByIds(ids);
    }

    @Override
    public void updateRealNameAsyn(String newRealName, long userId) {
        if (userId > 0) {
            Map<String, Object> values = new HashMap<String, Object>();
            values.put("newRealName", newRealName);
            values.put("id", userId);
            dispatchExoperate("UPDATE_ALL_REALNAME", values);
        }
    }

    @Override
    public User getUserById(long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public int getUserCountByUserName(String userName, long userId) {
        return userDao.getUserCountByUserName(userName, userId);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int updateUser4Assistant(User assistant) {
        User temp = userDao.getUserById(assistant.getId());
        if (null == temp) {
            return 0;
        }
        int i = userDao.updateUser4Assistant(assistant);

        // 异步修改 realname冗余字段
        if (i > 0 && !temp.getRealName().equals(assistant.getRealName())) {
            this.updateRealNameAsyn(assistant.getRealName(), assistant.getId());
        }
        return i;
    }

    @Override
    public int deleteUserByIds(Long[] ids) {
        return userDao.deleteUserByIds(ids);
    }

    @Override
    public List<User> getUserPwdByIds(Long[] ids) {
        return userDao.getUserPwdByIds(ids);
    }

    @Override
    public int updataIsMemberState(long userId, YesNoType isMember) {
        return userDao.updataIsMemberState(userId, isMember);
    }

    @Override
    public String importStudents(UploadFile file, long groupTypeId, BaseUser baseUser, String ip) throws Exception {
        /****************************************** 清除历史结果文件：S **************************************/
        FilePathBuilder build = new FilePathBuilder(PathType.IMP_STUDENT, baseUser.getId(), null, null);
        try {
            // 导入之前删除历史任务文件
            String path = build.buildImportResultFilePath();
            String[] files = FileSystemUtil.getFiles(path);
            if (!Validators.isEmpty(files)) {
                for (String fileName : files) {
                    FileSystemUtil.deleteFile(fileName);
                }
            }
        }
        catch (Exception e) {
            log.warn("删除历史文件异常,可能不存在该目录", e);
        }
        /****************************************** 清除历史结果文件：E **************************************/

        /********************************* 获取个人用户导入信息：S **************************************/
        List<UserImport> students = null;
        try {
            ExcelImport<UserImport> excelImport = new ExcelImport<UserImport>(UserImport.class);
            students = (ArrayList<UserImport>) excelImport.importExcelByJXL(file.getFile());
            if (students == null || students.isEmpty()) {
                return "false:空的导入文件！";
            }
        }
        catch (Exception e) {
            log.error("解析导入个人用户数据错误！", e);
            return "false:" + e.getMessage();
        }
        /********************************* 获取个人用户导入信息：E **************************************/
        int successCount = 0; // 导入成功数据
        for (int i = students.size() - 1; i >= 0; i--) {
            /********************************* 验证个人用户导入信息：S **************************************/
            UserImport userImport = students.get(i);
            User user = validatorImportUser(userImport);
            if (user == null) {
                continue;
            }
            else {
                user.setGroupTypeId(groupTypeId);
            }
            for (int j = i - 1; j >= 0; j--) {
                UserImport userImp = students.get(j);
                if (userImport.getPhone().equals(userImp.getPhone())) {
                    userImport.setResult("手机号码和第" + (j + 2) + "行手机号码重复!");
                    break;
                }
            }
            if (!Validators.isBlank(userImport.getResult())) {
                continue;
            }
            User u = userDao.getUserByUserName(userImport.getPhone());
            if (u != null) {
                userImport.setResult("该手机号已注册，无法生成再次生成用户！");
                continue;
            }
            if (validatePhone(user.getPhone(), UserType.PERSONAL)) {
                userImport.setResult("该手机号码已绑定用户！");
                continue;
            }

            /********************************* 验证个人用户导入信息：E **************************************/
            /********************************* 导入个人用户信息：S **************************************/
            try {
                selfBean.addUserAndSendMessage(user);
            }
            catch (Exception e) {
                log.error("导入个人用户：导入个人用户【{}】失败！", userImport.getRealName(), e);
                userImport.setResult("记录个人用户信息出错！");
                continue;
            }
            /********************************* 导入个人用户信息：E **************************************/
            userImport.setResult("导入成功");
            successCount++;
        }

        /****************************************** 生成结果文件：S **************************************/
        String realpath = OSUtil.getTempDir();
        // String suffix = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        String fileName = realpath + BaseConstants.SEPERATOR + "(导入结果)" + file.getFileRealName(); // 文件名
        File temp = new File(fileName);
        try {
            String[] fieldTitles = new String[] { "姓名", "手机号码", "结果" };
            String[] propertyNames = new String[] { "realName", "phone", "result" };
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("个人用户导入操作结果", students);
            ExportUtil exportUtil = new ExportUtil();
            exportUtil.exportXLSFile(fieldTitles, propertyNames, map, fileName, new FileOutputStream(temp));
            FileSystemUtil.saveFile(temp,
                    build.buildImportResultFilePath() + BaseConstants.SEPERATOR + "(导入结果)" + file.getFileRealName());
        }
        catch (Exception e) {
            log.error("导入结果文件生成失败！");
        }
        finally {
            if (temp.exists()) {
                temp.delete();
            }
        }
        /****************************************** 生成结果文件：E **************************************/
        logOperateAsyn("【" + baseUser.getRealName() + "】成功导入" + successCount + "个个人用户", baseUser.getId(), ip);
        return "true:导入个人用户成功" + successCount + "个，失败" + (students.size() - successCount) + "个,详细情况请查看结果文件！";
    }

    /**
     * 验证并构造用户信息
     *
     * @param userImport
     * @return
     */
    private User validatorImportUser(UserImport userImport) {
        if (Validators.isBlank(userImport.getPhone())) {
            userImport.setResult("手机号码为空");
            return null;
        }
        if (!Util.checkPhoneAll(userImport.getPhone())) {
            userImport.setResult("手机号码无效！");
            return null;
        }
        User user = new User();
        user.setId(userDao.generatePrimaryKey());
        String realName = userImport.getRealName();
        if (realName == null) {
            realName = "";
        }
        user.setRealName(realName);
        String phone = userImport.getPhone().trim();
        user.setPhone(phone);
        user.setUserName(phone);// 手机号作为用户名
        String pwd = phone.substring(phone.length() - 6, phone.length());
        user.setPassword(Util.encodePassword(pwd));// 手机号后6位为密码
        Date date = new Date();
        user.setCreateTime(date);
        user.setIsCancel(StatusEunm.NORMAL);
        user.setUserType(UserType.PERSONAL);
        user.setRegisterType(UserRegTypeEnum.BACK_ADD);
        user.setRemark("后台导入创建用户");
        if (StringUtils.isNotBlank(realName)) {
            user.setSpellName(String.valueOf(PinyinUtil.getPinyin(realName).toArray()[0]));
            user.setShortSpell(PinyinUtil.getPinyinFirst(realName));
        }
        return user;
    }

    @Override
    public Boolean validatePhone(String phone, UserType type) {
        return userDao.mainPhoneIsBind(phone, type);

    }

    @Override
    public String viewStudentResultExcel(Long id) {
        FilePathBuilder build = new FilePathBuilder(PathType.IMP_STUDENT, id, null, null);
        String path = build.buildImportResultFilePath();
        String info = "";
        try {
            if (Validators.isEmpty(path)) {
                return info;
            }
            String[] files = FileSystemUtil.getFiles(path);
            if (!Validators.isEmpty(files)) {
                File lastModifyFile = new File(files[files.length - 1]);// 最后一次创建的文件
                String downPath = files[files.length - 1];
                info = downPath + "#" + lastModifyFile.getName();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public boolean isExistsGroupTypeByGroupTypeId(long groupTypeId) {
        return userDao.isExistsGroupTypeByGroupTypeId(groupTypeId);
    }

    @Override
    public void setSelf(Object proxyBean) {
        selfBean = (UserService) proxyBean;

    }

    @Override
    public long updateUser4Student(User student) {
        return userDao.updateUser4Student(student);
    }

    /**
     * 发送短息
     *
     * @param content
     * @param u
     */
    // private void sendMessage(String content, User u) {
    // if (null == u || StringUtils.isEmpty(u.getPhone())) {
    // return;
    // }
    // Map<String, Object> value = new HashMap<String, Object>();
    // value.put("content", content);
    // value.put("phone", u.getPhone());
    // value.put("smsType", SmsType.COMPETEATTEND.getValue());
    // value.put("receiveId", u.getId());
    // if (null != u) {
    // value.put("userName", u.getUserName());
    // }
    // value.put("realName", u.getRealName());
    // exoperateProcessorDispatcher.dispatchProcessor("SMS_SEND_SINGLE", value);
    // }

    @Override
    public int addUserAndSendMessage(User user) {
        // TODO Auto-generated method stub
        user.setId(userDao.generatePrimaryKey());
        int num = userDao.addUser(user);
        Account account = new Account();
        account.setFunds(0.00f);
        // account.setFundsLocked(false);
        account.setModifyTime(new Date());
        account.setId(user.getId());
        accountService.addAccount(account);
        // account.set
        String content = Config.getParam(BaseConstants.CMS_BATCH_IMPORT_USER);
        String realName = user.getRealName();
        // if (StringUtils.isNotBlank(realName)) {
        // realName = user.getUserName();
        // }
        content = content.replace("${realname}", realName);
        content = content.replace("${username}", user.getUserName());
        content = content.replace("${password}", Util.decodePassword(user.getPassword()));
        // sendMessage(content, user);
        return num;
    }

    @Override
    public void updateHifiMember() {
        userDao.updateHifiMember();
    }

    @Override
    public int hifiDateAdd4Member(long userId, int days) {
        return userDao.hifiDateAdd4Member(userId, days);
    }

    @Override
    public int hifiDateAdd4Common(long userId, int days) {
        return userDao.hifiDateAdd4Common(userId, days);
    }

    @Override
    public int hifiDateUpdate(long userId, Date endDate) {
        return userDao.hifiDateUpdate(userId, endDate);
    }

    @Override
    public User getUserByUserNameAndPassward(String userName, String passward) {
        return userDao.getUserByUserNameAndPassward(userName, passward);
    }

    @Override
    public long updateUserAvatarUser(String avatarPath, long userId) {

        return userDao.updateUserAvatarUser(avatarPath, userId);
    }

}
