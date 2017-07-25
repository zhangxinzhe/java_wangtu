/*
 * @(#)StudentManageAction.java    Created on 2015年12月21日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.basic;

import java.io.IOException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsPageAction;
import net.zdsoft.chnmaster.constant.ChnmasterModuleConstants;
import net.zdsoft.chnmaster.service.SystemControlService;
import net.zdsoft.chnmaster.service.basic.UserGroupTypeService;
import net.zdsoft.chnmaster.service.basic.UserService;
import net.zdsoft.common.constant.ControlConstants;
import net.zdsoft.common.dao.queryCondition.EqualCondition;
import net.zdsoft.common.dao.queryCondition.LikeCondition;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.user.User;
import net.zdsoft.common.entity.user.UserGroupType;
import net.zdsoft.common.enums.SexType;
import net.zdsoft.common.enums.StatusEunm;
import net.zdsoft.common.enums.UserRegTypeEnum;
import net.zdsoft.common.enums.UserType;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.common.upload.FileValidationException;
import net.zdsoft.common.upload.RequestFileHandler;
import net.zdsoft.common.upload.SimpleFileValidator;
import net.zdsoft.common.upload.UploadFile;
import net.zdsoft.common.utils.URLUtil;
import net.zdsoft.common.utils.Util;
import net.zdsoft.keel.util.Validators;

/**
 * 个人用户
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年12月21日 下午4:27:40 $
 */
@Scope("prototype")
@Controller
public class StudentManageAction extends CmsPageAction {
    private static final long serialVersionUID = 1L;

    @Resource
    private UserService userService;
    @Resource
    private SystemControlService systemControlService;
    @Resource
    private UserGroupTypeService userGroupTypeService;

    private User student;
    private String idStr;
    private int flag;// 0:取消会员，1:升级会员
    private int isMemberFlag;

    /**
     * 个人用户
     */
    public String studentManage() {
        if (!getCanStu()) {
            return NO_PRIVACY;
        }
        if (student == null) {
            student = new User();
            student.setIsCancel(StatusEunm.NORMAL);// 默认条件：正常状态
        }

        list = userService.getUserListByCondition(buildeCondition(), getPage());
        return SUCCESS;
    }

    /**
     * 个人用户-查看
     */
    public String viewStudent() {
        student = userService.getUserById(id);
        student.setPassword(Util.decodePassword(student.getPassword()));
        return SUCCESS;
    }

    /**
     * 添加个人用户
     *
     * @return
     */
    public String addStudent() {
        if (student == null) {
            student = new User();
            return INPUT;
        }
        if (!validateStudent()) {
            return INPUT;
        }
        String pwd = student.getPassword().trim();
        if (student.getId() == 0) {// 新增
            student.setUserName(student.getPhone());
            student.setPassword(Util.encodePassword(pwd));
            student.setCreateTime(new Date());
            student.setIsCancel(StatusEunm.NORMAL);
            student.setUserType(UserType.PERSONAL);
            student.setRegisterType(UserRegTypeEnum.BACK_ADD);
            long id = userService.addUserAndSendMessage(student);
            if (id > 0) {
                logOperateAsyn("添加用户【" + student.getUserName() + "】成功");
            }
        }
        else {// 修改
            student.setPassword(Util.encodePassword(pwd));
            long id = userService.updateUser4Student(student);
            if (id > 0) {
                logOperateAsyn("修改用户【" + student.getUserName() + "】成功");
            }
        }

        return SUCCESS;
    }

    /**
     * 修改
     *
     * @return
     */
    public String editStudent() {
        student = userService.getUserById(id);
        if (student != null && StringUtils.isNotBlank(student.getPassword())) {
            student.setPassword(Util.decodePassword(student.getPassword()));
            UserGroupType type = userGroupTypeService.getUserGroupTypeById(student.getGroupTypeId());
            if (null != type) {
                student.setGroupTypeTitle(type.getTitle());
            }
        }
        return SUCCESS;
    }

    /**
     * 升级会员
     */
    public String isMember() {
        student = userService.getUserById(id);
        if (student == null) {
            replyDto.setErrorMsg("该用户不存在，请刷新页面重试！");
        }
        else {
            if (flag == 1) {// 升级会员
                int i = userService.updataIsMemberState(student.getId(), YesNoType.YES);
                if (i > 0) {
                    replyDto.setPromptMsg("升级会员成功！");
                    logOperateAsyn("【" + student.getId() + "," + student.getUserName() + "】升级会员成功");
                }
                else {
                    replyDto.setErrorMsg("用户状态已更新，请刷新页面！");
                }
            }
            else {// 取消会员
                int i = userService.updataIsMemberState(student.getId(), YesNoType.NO);
                if (i > 0) {
                    replyDto.setPromptMsg("取消会员成功！");
                    logOperateAsyn("【" + student.getId() + "," + student.getUserName() + "】取消会员成功");
                }
                else {
                    replyDto.setErrorMsg("用户状态已更新，请刷新页面！");
                }
            }
        }
        printJson(replyDto);
        return NONE;
    }

    /**
     * 个人用户-显示密码
     */
    public String showPwd() {
        String[] userIds = idStr.split(",");
        Long[] ids = new Long[userIds.length];
        for (int i = 0; i < userIds.length; i++) {
            ids[i] = Long.parseLong(userIds[i]);
        }
        List<User> users = userService.getUserPwdByIds(ids);
        printJson(users);
        return NONE;
    }

    /**
     * 个人用户-重置密码
     */
    public String resetPwd() {
        // 把String数组转化为Long数组
        String[] userIds = idStr.split(",");
        Long[] ids = new Long[userIds.length];
        for (int i = 0; i < userIds.length; i++) {
            ids[i] = Long.parseLong(userIds[i]);
        }
        int num = userService.updateResetUserPasswordByIds(ids);
        if (num > 0) {
            logOperateAsyn("个人用户[" + idStr + "]重置密码成功");
        }
        return NONE;
    }

    /**
     * 导入个人用户信息
     */
    public String importStudent() {
        // if (!getCanImpStudent()) {
        // return NO_PRIVACY;
        // }
        String msg = "";
        boolean isSuccess = false;
        if (StringUtils.isEmpty(idStr)) {
            msg = "请选择分组类型！";
        }
        else {
            // 验证上传文件
            UploadFile file = null;
            try {
                file = RequestFileHandler.handleFile(getRequest(), fileValidator);
            }
            catch (FileValidationException e) {
                msg = e.getMessage();
            }

            if (null == file) {
                if (Validators.isBlank(msg)) {
                    msg = "请选择需要导入的文件！";
                }
            }
            else {
                if (systemControlService.updateStatusToWorking(0L, ControlConstants.CONTROL_IMPORT_USER,
                        getUser().getUserName())) {
                    try {
                        long groupTypeId = Long.parseLong(idStr);
                        msg = userService.importStudents(file, groupTypeId, getUser(), URLUtil.getIpAddr(request));
                        String[] msgs = msg.split(":");
                        if (msgs != null && msgs.length == 2) {
                            if ("true".equals(msgs[0])) {
                                isSuccess = true;
                                msg = msgs[1];
                            }
                            else {
                                msg = msgs[1];
                            }
                        }
                    }
                    catch (Exception e) {
                        msg = "处理失败!原因是" + e.getMessage();
                        e.printStackTrace();
                    }
                    finally {
                        systemControlService.updateStatusToIdle(0L, ControlConstants.CONTROL_IMPORT_USER);
                    }
                }
                else {
                    msg = "当前有其他人正在导入个人用户，为避免用户信息重复，您暂时不能进行该操作，稍后重试。";
                }
            }
        }

        getResponse().setContentType("text/html; charset=UTF-8");
        try {
            getResponse().getWriter().print(
                    "<script type='text/javascript'>parent.showResult('" + msg + "', " + isSuccess + ");</script>");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }

    /**
     * excel导入文件验证器
     */
    private final static SimpleFileValidator fileValidator = new SimpleFileValidator() {
        /**
         * 提供合法的文件扩展名集合
         */
        @Override
        public Set<String> getAvailableSuffixes() {
            Set<String> suffixes = new HashSet<String>();
            suffixes.add("xls");
            return suffixes;
        }

        /**
         * 提供文件大小的上限
         */
        @Override
        public long getMaxSize() {
            return Long.MAX_VALUE;
        }
    };

    /**
     * 查看个人用户导入任务
     *
     * @param agencyId
     * @return
     */
    public String viewStudentResultExcel() {
        String info = userService.viewStudentResultExcel(getUser().getId());
        print(info);
        return NONE;
    }

    /**
     * 加载分组类型数据
     */
    public String loadGroupTypes() {
        list = userGroupTypeService.getUserGroupTypeList();
        return SUCCESS;
    }

    /**
     * 新增分组类型
     */
    public String addGroupType() {
        if (StringUtils.isEmpty(idStr)) {
            replyDto.setErrorMsg("类型名称不能为空！");
            printJson(replyDto);
            return NONE;
        }
        String title = idStr.trim();
        if (title.length() > 25) {
            replyDto.setErrorMsg("类型名称在25个字以内！");
            printJson(replyDto);
            return NONE;
        }
        UserGroupType entity = new UserGroupType();
        entity.setTitle(title);
        entity.setIsCanDel(YesNoType.YES);
        if (userGroupTypeService.saveUserGroupType(entity) <= 0) {
            replyDto.setErrorMsg("保存失败，请刷新页面重试！");
            printJson(replyDto);
            return NONE;
        }
        replyDto.setIsSuccess(true);
        printJson(replyDto);
        return NONE;
    }

    /**
     * 删除分组类别
     */
    public String delGroupType() {
        if (id <= 0) {
            replyDto.setErrorMsg("请选择需要删除的分类！");
            printJson(replyDto);
            return NONE;
        }
        UserGroupType entity = userGroupTypeService.getUserGroupTypeById(id);
        if (entity == null) {
            replyDto.setErrorMsg("删除失败，此分类已经不存在！");
            printJson(replyDto);
            return NONE;
        }
        if (!entity.getIsCanDel().getBooleanValue()) {
            replyDto.setErrorMsg("删除失败，默认分类无法删除！");
            printJson(replyDto);
            return NONE;
        }
        if (userService.isExistsGroupTypeByGroupTypeId(id)) {
            replyDto.setErrorMsg("删除失败，此分类已经被使用，无法删除！");
            printJson(replyDto);
            return NONE;
        }
        if (userGroupTypeService.deleteUserGroupTypeById(id) <= 0) {
            replyDto.setErrorMsg("删除失败，请刷新页面重试！");
            printJson(replyDto);
            return NONE;
        }
        replyDto.setIsSuccess(true);
        printJson(replyDto);
        return NONE;
    }

    /**
     * 构建查询条件
     */
    private List<QueryCondition> buildeCondition() {
        List<QueryCondition> conditions = new ArrayList<QueryCondition>();

        if (student.getGroupTypeId() > 0) {
            conditions.add(new EqualCondition("U.GROUP_TYPE_ID", student.getGroupTypeId(), Types.INTEGER));
        }
        if (StringUtils.isNotBlank(student.getUserName())) {
            conditions.add(new EqualCondition("U.USERNAME", student.getUserName(), Types.VARCHAR));
        }
        if (StringUtils.isNotBlank(student.getRealName())) {
            conditions.add(new LikeCondition("U.REALNAME", student.getRealName()));
        }
        if (isMemberFlag == 1) {
            conditions.add(new EqualCondition("U.IS_MEMBER", 1, Types.INTEGER));
        }
        else if (isMemberFlag == 2) {
            conditions.add(new EqualCondition("U.IS_MEMBER", 0, Types.INTEGER));
        }
        if (student.getIsCancel() != null) {
            conditions.add(new EqualCondition("U.IS_CANCEL", student.getIsCancel().getValue(), Types.INTEGER));
        }
        if (student.getRegisterType() != null) {
            conditions.add(new EqualCondition("U.REGISTER_TYPE", student.getRegisterType().getValue(), Types.INTEGER));
        }
        conditions.add(new EqualCondition("U.USER_TYPE", UserType.PERSONAL.getValue(), Types.INTEGER));
        return conditions;
    }

    /**
     * 用户信息校验
     *
     * @return
     */
    private boolean validateStudent() {
        if (StringUtils.isBlank(student.getPhone())) {
            addActionError("手机号码不能为空！");
            return false;
        }
        if (!Util.checkPhoneAll(student.getPhone())) {
            addActionError("请填写正确的手机号码！");
            return false;
        }
        if (student.getId() == 0) {
            if (userService.validatePhone(student.getPhone(), UserType.PERSONAL)) {
                addActionError("该手机号码已绑定用户！");
                return false;
            }
        }
        if (StringUtils.isNotBlank(student.getEmail()) && !Util.checkEmail(student.getEmail())) {
            addActionError("邮箱格式错误！");
            return false;
        }
        if (StringUtils.isNotBlank(student.getQq()) && !Util.checkQq(student.getQq())) {
            addActionError("请输入有效qq账号！");
            return false;
        }
        return true;
    }

    /**
     * 获取注销状态
     */
    public StatusEunm[] getStatusEunms() {
        return StatusEunm.values();
    }

    public YesNoType getYes() {
        return YesNoType.YES;
    }

    public YesNoType getNo() {
        return YesNoType.NO;
    }

    /**
     * 性别
     */
    public SexType[] getSexTypes() {
        SexType[] sexTypes = new SexType[] { SexType.MAN, SexType.WOMAN };
        return sexTypes;
    }

    public UserRegTypeEnum[] getRegTypes() {
        return UserRegTypeEnum.values();
    }

    /******************************* 权限相关 ****************************/
    /* 个人用户 */
    public boolean getCanStu() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_BASIC_STU);
    }

    /******************************* get set ****************************/

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getIsMemberFlag() {
        return isMemberFlag;
    }

    public void setIsMemberFlag(int isMemberFlag) {
        this.isMemberFlag = isMemberFlag;
    }

}
