/*
 * @(#)MusicTutorManage.java    Created on 2016年5月26日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * ${user}$
 */
package net.zdsoft.chnmaster.action.basic;

import java.io.File;
import java.io.InputStream;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsPageAction;
import net.zdsoft.chnmaster.constant.ChnmasterModuleConstants;
import net.zdsoft.chnmaster.service.basic.UserService;
import net.zdsoft.chnmaster.service.basic.UserTeacherService;
import net.zdsoft.common.dao.queryCondition.EqualCondition;
import net.zdsoft.common.dao.queryCondition.LeftLikeCondition;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.user.User;
import net.zdsoft.common.entity.user.UserTeacher;
import net.zdsoft.common.enums.AuditStatusType;
import net.zdsoft.common.enums.PathType;
import net.zdsoft.common.enums.SexType;
import net.zdsoft.common.filesystem.util.FileSystemUtil;
import net.zdsoft.common.upload.FilePathBuilder;
import net.zdsoft.common.utils.DownloadUtil;
import net.zdsoft.common.utils.NetstudyFileUtils;
import net.zdsoft.common.utils.Util;

/**
 * 基础数据-音乐导师模块
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年5月26日 下午4:08:08 $
 */
@Scope("prototype")
@Controller
public class MusicTutorManageAction extends CmsPageAction {

    private static final long serialVersionUID = 1L;

    private UserTeacher tutor;// 音乐导师 TEACH_TYPE=3

    private long tutorId;

    private int auditStu;// 审核状态（1取消审核，2审核通过，3审核不通过）
    private String auditMsg;// 审核不通过信息

    private int flag;// 区别照片类型
    @Resource
    private UserTeacherService userTeacherService;

    @Resource
    private UserService userService;

    /**
     * 列表
     *
     * @return
     */
    public String musicTutorManage() {
        if (!getCanMusicTutor()) {
            return NO_PRIVACY;
        }
        list = userTeacherService.getUserTeacherListByCondition(buildConditions(), false, getPage());
        return SUCCESS;
    }

    /**
     * 查看
     *
     * @return
     */
    public String viewMusicTutor() {
        tutor = userTeacherService.getUserTeacherInfoById(tutorId);
        User user = userService.getUserById(tutorId);
        if (null != user) {
            tutor.setSex(user.getSex());
            tutor.setEmail(user.getEmail());
        }
        return SUCCESS;
    }

    /**
     * 审核
     *
     * @return
     */
    public String auditMusicTutor() {
        Map<String, Object> json = new HashMap<>();
        int rs = 0;
        if (tutorId <= 0) {
            json.put("isSuccess", false);
            json.put("msg", "数据异常！请刷新页面或者重新登录再试！");
        }
        else if (auditStu == 2) {// 审核通过
            rs = userTeacherService.updateAuditStatus(tutorId, auditStu, auditMsg, getUser().getRealName());
            if (rs > 0) {
                json.put("isSuccess", true);
                json.put("msg", "审核通过操作成功！");
            }
            else {
                json.put("isSuccess", false);
                json.put("msg", "操作失败，请刷新页面重试！");
            }

        }
        else if (auditStu == 3) {// 审核不通过
            if (auditMsg == null || auditMsg == "") {
                json.put("isSuccess", false);
                json.put("msg", "审核信息不能为空！");
            }
            else {
                rs = userTeacherService.updateAuditStatus(tutorId, auditStu, auditMsg, getUser().getRealName());
                if (rs > 0) {
                    json.put("isSuccess", true);
                    json.put("msg", "审核不通过操作成功！");
                }
                else {
                    json.put("isSuccess", false);
                    json.put("msg", "操作失败，请刷新页面重试！");
                }
            }
        }
        else if (auditStu == 1) {// 取消审核
            rs = userTeacherService.updateAuditStatus(tutorId, auditStu, "", "");
            if (rs <= 0) {
                json.put("isSuccess", false);
                json.put("msg", "取消审核操作失败，请刷新页面重试！");
            }
            else {
                json.put("isSuccess", true);
                json.put("msg", "取消审核操作成功！");
            }
        }
        else {
            json.put("isSuccess", false);
            json.put("msg", "数据异常！请刷新页面或者重新登录再试！");
        }

        wirteJsonMessage(json);
        return NONE;
    }

    /**
     * 编辑
     *
     * @return
     */
    public String editMusicTutor() {
        if (tutorId > 0) {
            tutor = userTeacherService.getUserTeacherInfoById(tutorId);
            User user = userService.getUserById(tutorId);
            if (null != user) {
                tutor.setSex(user.getSex());
                tutor.setEmail(user.getEmail());
            }
        }
        else {
            tutor = new UserTeacher();
        }

        return SUCCESS;
    }

    /**
     * 保存
     *
     * @throws Exception
     */
    public String saveMusicTutor() throws Exception {
        Map<String, Object> json = new HashMap<>();
        // 验证
        String errorMsg = validateMusicTutor();
        if (errorMsg != null) {
            json.put("isSuccess", false);
            json.put("msg", errorMsg);
        }
        else if (tutor.getId() > 0) {// 更新
            if (StringUtils.isNotBlank(uploadTempFile)) {
                String fileName = null;
                fileName = getUser().getId() + "_photo." + Util.getSuffix(uploadTempFile);
                FilePathBuilder fpb = new FilePathBuilder(PathType.TEACHERPHOTO, tutor.getId(), fileName);

                tutor.setPhotoFile(NetstudyFileUtils.addCurrentTimeMark(fpb.buildUploadFilePath2()));
            }
            int rs = userTeacherService.updateMusicTuorById(tutor);
            if (rs > 0) {
                if (StringUtils.isNotBlank(uploadTempFile)) {
                    FileSystemUtil.copyFile(uploadTempFile, NetstudyFileUtils.deleteMark(tutor.getPhotoFile()));
                }
                json.put("isSuccess", true);
                json.put("msg", "保存成功！");

            }
            else {
                json.put("isSuccess", false);
                json.put("msg", "保存失败！请重试！");
            }

        }
        else {
            json.put("isSuccess", false);
            json.put("msg", "数据异常，请刷新页面重试！");
        }
        wirteJsonMessage(json);
        return NONE;
    }

    /**
     * 验证表单字段
     *
     * @return
     */
    private String validateMusicTutor() {
        if (tutor == null) {
            return "数据出错，请刷新页面重试！";
        }
        if (StringUtils.isBlank(tutor.getPhotoFile()) && StringUtils.isBlank(uploadTempFile)) {
            return "请上传个人照片！";
        }

        if (StringUtils.isBlank(tutor.getRealName())) {
            return "姓名不得为空！";
        }
        if (null == tutor.getSex()) {
            return "请选择性别！";
        }
        if (StringUtils.isBlank(tutor.getNation())) {
            return "民族不得为空！";
        }

        if (tutor.getBirthday() == null) {
            return "出生日期不得为空！";
        }

        if (StringUtils.isBlank(tutor.getWorkUnit())) {
            return "工作单位不得为空！";
        }

        if (StringUtils.isBlank(tutor.getTitle())) {
            return "职务及职称不得为空！";
        }

        if (StringUtils.isBlank(tutor.getPhone())) {
            return "联系电话不得为空！";
        }

        if (StringUtils.isBlank(tutor.getPolitical())) {
            return "政治面貌不得为空！";
        }

        if (StringUtils.isBlank(tutor.getEmail())) {
            return "邮箱不得为空！";
        }
        else if (!Util.checkEmail(tutor.getEmail())) {
            return "请输入合法的邮箱！";
        }

        if (StringUtils.isBlank(tutor.getAddress())) {
            return "联系地址不得为空！";
        }

        if (StringUtils.isBlank(tutor.getIntroduction())) {
            return "主要研究及教学方向不得为空！";
        }
        if (StringUtils.isBlank(tutor.getTeachSubject())) {
            return "研究成果及获奖情况不得为空！";
        }

        return null;
    }

    /**
     * 文件下载
     *
     * @throws Exception
     */
    public void downloadFile4MusicTutor() throws Exception {
        if (tutorId > 0) {
            tutor = userTeacherService.getUserTeacherById(tutorId);
            String tempFilePath = "";
            switch (flag) {
            case 1:
                if (StringUtils.isNotBlank(tutor.getIdcardFile())) {
                    tempFilePath = NetstudyFileUtils.deleteMark(tutor.getIdcardFile());
                }
                break;
            case 2:
                if (StringUtils.isNotBlank(tutor.getIdcardFileOther())) {
                    tempFilePath = NetstudyFileUtils.deleteMark(tutor.getIdcardFileOther());
                }
                break;
            case 3:
                if (StringUtils.isNotBlank(tutor.getHandIdcardFile())) {
                    tempFilePath = NetstudyFileUtils.deleteMark(tutor.getHandIdcardFile());
                }
                break;
            case 4:
                if (StringUtils.isNotBlank(tutor.getPhotoFile())) {
                    tempFilePath = NetstudyFileUtils.deleteMark(tutor.getPhotoFile());
                }
                break;
            default:
                break;
            }
            if (StringUtils.isNotBlank(tempFilePath)) {
                if (FileSystemUtil.fileExists(tempFilePath)) {
                    InputStream is = FileSystemUtil.getFileAsStream(tempFilePath);
                    File file = new File(tempFilePath);
                    DownloadUtil.download(is, file.getName(), 0);
                }
            }
        }
    }

    /**
     * 构建条件
     *
     * @return
     */
    private List<QueryCondition> buildConditions() {
        List<QueryCondition> conditions = new ArrayList<>();
        conditions.add(new EqualCondition("UT.TEACH_TYPE", 3, Types.TINYINT));
        if (tutor != null) {
            if (StringUtils.isNotBlank(tutor.getRealName())) {// 姓名
                conditions.add(new LeftLikeCondition("UT.REALNAME", tutor.getRealName()));
            }
            if (null != tutor.getAuditStatus()) {
                conditions.add(new EqualCondition("UT.AUDIT_STATUS", tutor.getAuditStatus().getValue(), Types.TINYINT));
            }
        }

        return conditions;
    }

    /**
     * 审核状态枚举
     */
    public AuditStatusType[] getTutorAuditStatus() {
        return new AuditStatusType[] { AuditStatusType.AUDITING, AuditStatusType.AUDITPASS,
                AuditStatusType.AUDITNOPASS };
    }

    /**
     * 性别
     */
    public SexType[] getSexTypes() {
        return new SexType[] { SexType.MAN, SexType.WOMAN };
    }

    /******************************* 权限相关 ****************************/
    /* 音乐导师 */
    public boolean getCanMusicTutor() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_BASIC_MUSICTUTOR);
    }

    /* 查看 */
    public boolean getCanViewMusicTutor() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_BASIC_MUSICTUTOR_VIEW);
    }

    /* 修改 */
    public boolean getCanEditMusicTutor() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_BASIC_MUSICTUTOR_EDIT);
    }

    /* 审核 */
    public boolean getCanAuditMusicTutor() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_BASIC_MUSICTUTOR_AUDIT);
    }

    public UserTeacher getTutor() {
        return tutor;
    }

    public void setTutor(UserTeacher tutor) {
        this.tutor = tutor;
    }

    public long getTutorId() {
        return tutorId;
    }

    public void setTutorId(long tutorId) {
        this.tutorId = tutorId;
    }

    public int getAuditStu() {
        return auditStu;
    }

    public void setAuditStu(int auditStu) {
        this.auditStu = auditStu;
    }

    public String getAuditMsg() {
        return auditMsg;
    }

    public void setAuditMsg(String auditMsg) {
        this.auditMsg = auditMsg;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}
