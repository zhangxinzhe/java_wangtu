/*
 * @(#)AppUserAction.java    Created on 2017年7月14日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.wangtu;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsBaseAction;
import net.zdsoft.chnmaster.service.basic.UserService;
import net.zdsoft.chnmaster.utils.CookieUtils;
import net.zdsoft.chnmaster.utils.LoginUtils;
import net.zdsoft.common.entity.user.User;
import net.zdsoft.common.enums.StatusEunm;
import net.zdsoft.common.enums.UserRegTypeEnum;
import net.zdsoft.common.enums.UserType;
import net.zdsoft.common.filesystem.util.FileSystemUtil;
import net.zdsoft.common.utils.Util;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月14日 上午10:33:56 $
 */
@Scope("prototype")
@Controller
public class AppUserAction extends CmsBaseAction {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;
    private String realName;
    private String telephone;
    private String rePassword;

    private File avatar;
    private String avatarFileName;
    private String avatarContentType;

    @Resource
    private UserService userService;

    public void userLogin() {

        if (getUser() != null) {
            printMsg("success");
            return;
        }

        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            printMsg("用户名和密码都不能为空！");
            return;
        }

        if (!validateLoginUser()) {
            printMsg("用户名格式不对！");
            return;
        }

        User systemUser = userService.getUserByUserNameAndPassward(userName, Util.encodePassword(password));
        if (null == systemUser) {

            printMsg("用户名或密码错误！");
            return;
        }
        // 如果用户已冻结
        if (systemUser.getIsCancel() == StatusEunm.CANCEL) {
            printMsg("对不起，此账户已被注销！");
            return;
        }

        // 将userType赋值给type
        systemUser.setType(systemUser.getUserType().getValue());
        LoginUtils.getInstance().writeUser(getRequest(), getResponse(), systemUser);
        printMsg("success");
    }

    private boolean validateLoginUser() {
        if (Util.byteLength(userName) > 30) {
            return false;
        }
        userName = userName.trim();
        return true;
    }

    public void userRegister() {
        String str = validateRegister();
        if (!StringUtils.isBlank(str)) {
            printMsg(str);
            return;
        }
        User u = new User();

        u.setUserName(userName);
        u.setPassword(Util.encodePassword(password));
        u.setCreateTime(new Date());
        u.setIsCancel(StatusEunm.NORMAL);
        u.setUserType(UserType.PERSONAL);
        u.setRegisterType(UserRegTypeEnum.BACK_ADD);
        long id = userService.addUserAndSendMessage(u);
        if (id > 0) {
            printMsg("success");
        }
        else {
            printMsg("注册失败，请重试！");
        }
        return;
    }

    /**
     * 退出
     *
     * @return
     */
    public void logout() {
        CookieUtils.removeCookie(getRequest(), getResponse(), LoginUtils.getInstance().getUserInfoCookieName());
        // mainDomain = Config.getParam(BaseConstants.DOMAIN_HOME);
        print("success");
        // return SUCCESS;
    }

    private String validateRegister() {
        if (StringUtils.isBlank(this.userName)) {
            return "手机号不能为空！";
        }
        if (StringUtils.isBlank(this.telephone)) {
            return "手机号不能为空！";
        }
        if (StringUtils.isBlank(this.password)) {
            return "密码不能为空！";
        }

        if (!password.equals(rePassword)) {
            return "两次密码输入不同！";
        }
        // 判断用户是否存在
        User u = userService.getUserByUserName(telephone);
        if (u != null) {
            return "用户名已经被注册！";
        }
        return "";
    }

    /**
     * 修改头像
     */
    public void changeAvatar() {
        if (getUser() == null) {
            printMsg("请先登录！");
            return;
        }
        if (avatar == null) {
            printMsg("上传图片不存在！");
            return;
        }
        String fileNewName = "/upload/avatar/" + getUser().getId() + "" + System.currentTimeMillis() + ".png";
        try {
            FileSystemUtil.saveFile(avatar, fileNewName);
            userService.updateUserAvatarUser(fileNewName, getUser().getId());
            printMsg("success");
        }
        catch (Exception e) {
            printMsg("保存失败请重试！");
        }

    }

    /**
     * 获取用户个人信息
     */
    public void getUserInfo() {
        Map<String, Object> json = new HashMap<String, Object>();
        if (getUser() == null) {
            json.put("isLogin", false);
            printJsonMap(json);
            return;
        }
        else {
            json.put("isLogin", true);
        }
        User u = userService.getUserById(getUser().getId());
        json.put("userInfo", u);
        printJsonMap(json);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }

    public String getAvatarFileName() {
        return avatarFileName;
    }

    public void setAvatarFileName(String avatarFileName) {
        this.avatarFileName = avatarFileName;
    }

    public String getAvatarContentType() {
        return avatarContentType;
    }

    public void setAvatarContentType(String avatarContentType) {
        this.avatarContentType = avatarContentType;
    }

}
