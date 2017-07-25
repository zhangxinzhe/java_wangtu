package net.zdsoft.chnmaster.action.index;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsBaseAction;
import net.zdsoft.chnmaster.config.Config;
import net.zdsoft.chnmaster.entity.system.SystemUser;
import net.zdsoft.chnmaster.service.system.SystemUserService;
import net.zdsoft.chnmaster.utils.CookieUtils;
import net.zdsoft.chnmaster.utils.LoginUtils;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.common.utils.URLUtil;
import net.zdsoft.common.utils.Util;

@Scope("prototype")
@Controller
public class LoginAction extends CmsBaseAction {

    private static final long serialVersionUID = 1L;

    private String loginName;// 用户登录名(登录名或(绑定)手机或(绑定)邮箱)
    private String loginPassword;// 用户密码
    private int isLogin; // 是否登录操作
    private String redirectUrl = "/index.htm";

    private String mainDomain;// 主干的域名
    private String oldPassword;// 原密码
    private String newPassword;// 新密码

    @Resource
    private SystemUserService systemUserService;

    /**
     * 处理用户登录
     *
     * @return
     */

    public String login() {
        if (getUser() != null) {
            return SUCCESS;
        }

        // 如果不是登录操作，直接打开登录页
        if (isLogin == 0) {
            return INPUT;
        }

        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(loginPassword)) {
            addActionError("用户名和密码都不能为空！");
            return INPUT;
        }

        if (!validateLoginUser()) {
            addActionError("用户名格式不对！");
            return INPUT;
        }

        SystemUser systemUser = systemUserService.getSystemUser(loginName.trim(), loginPassword);
        if (null == systemUser) {
            addActionError("用户名或密码错误！");
            return INPUT;
        }
        // 如果用户已冻结
        if (systemUser.getIsFreeze() == YesNoType.YES) {
            addActionError("对不起，此账户已冻结！");
            return INPUT;
        }

        // 将userType赋值给type
        systemUser.setType(systemUser.getUserType().getValue());

        LoginUtils.getInstance().writeUser(getRequest(), getResponse(), systemUser);

        // 附加业务：记录日志
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("id", systemUser.getId());
        values.put("operateIp", URLUtil.getIpAddr(request));
        dispatchExoperate("S_LOGIN_LOG", values);

        return SUCCESS;
    }

    /**
     * 退出
     *
     * @return
     */
    public String logout() {
        CookieUtils.removeCookie(getRequest(), getResponse(), LoginUtils.getInstance().getUserInfoCookieName());
        mainDomain = Config.getParam(BaseConstants.DOMAIN_HOME);
        return SUCCESS;
    }

    /**
     * 修改密码
     *
     * @return
     */
    public String resetPword() {
        if (StringUtils.isBlank(oldPassword) && StringUtils.isBlank(newPassword)) {// 第一次进入
            return INPUT;
        }
        if (!((validatePassword(oldPassword, "旧") && validatePassword(newPassword, "新")))) {
            replyDto.setIsSuccess(false);
            return wirteJsonMessage(replyDto);
        }
        SystemUser systemUser = systemUserService.updateSystemUserOfPassword(getUser().getId(), oldPassword,
                newPassword);
        if (systemUser != null) {
            logOperateAsyn("修改用户名密码（" + systemUser.getId() + "," + systemUser.getUserName() + "）");
            replyDto.setIsSuccess(true);
            replyDto.setPromptMsg("密码修改成功，请重新登录！");
            return wirteJsonMessage(replyDto);
        }
        else {
            replyDto.setIsSuccess(false);
            replyDto.setErrorMsg("旧密码不正确!");
            return wirteJsonMessage(replyDto);
        }
    }

    /**
     * 验证登录帐号是否合法
     *
     * @return
     */
    private boolean validateLoginUser() {
        if (Util.byteLength(loginName) > 30) {
            return false;
        }
        loginName = loginName.trim();
        return true;
    }

    /**
     * 验证密码是否合法
     *
     * @return
     */
    private boolean validatePassword(String password, String name) {

        if (StringUtils.isBlank(password)) {
            replyDto.setErrorMsg(name + "密码不能为空!");
            return false;
        }
        int len = net.zdsoft.keel.util.StringUtils.getRealLength(password);
        if (len > 20 || len < 6) {
            replyDto.setErrorMsg(name + "密码长度只能为6~20位！");
            return false;
        }
        return true;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getMainDomain() {
        return mainDomain;
    }

    public void setIsLogin(int isLogin) {
        this.isLogin = isLogin;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
