package net.zdsoft.chnmaster.action.common;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.chnmaster.service.account.AccountService;
import net.zdsoft.chnmaster.service.system.SystemMobileService;
import net.zdsoft.chnmaster.utils.LoginUtils;
import net.zdsoft.common.action.BaseAction;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.common.enums.UserType;
import net.zdsoft.common.hifi.service.HifiService;
import net.zdsoft.common.service.SystemWxbService;
import net.zdsoft.common.utils.StringUtil;

/**
 * action 基类
 *
 * @author 逸汐
 * @version $Revision: 1.0 $, $Date: 2013-11-19 上午11:29:16 $
 */
public class MobileBaseAction extends BaseAction {
    /************************************* 类静态变量 *************************************************/
    private static final long serialVersionUID = 1L;
    protected static final String NO_PRIVACY = "noprivacy"; // 没有权限的访问结果
    protected static final String NOT_EXIST = "notexist";// 访问资源不存在的结果
    protected static final String INDEX = "index";

    /************************************* 类变量 *************************************************/
    protected Logger log = LoggerFactory.getLogger(getClass());
    protected String nowStr; // 当前时段
    private String appType;
    private String deviceType;
    private String chnmasterAndroidUrl;

    protected String token;// 微课对接id

    /************************************* 服务对象 *************************************************/

    @Resource
    protected SystemMobileService systemMobileService;

    @Resource
    private SystemWxbService systemWxbService;
    @Resource
    private HifiService hifiService;

    // @Resource
    // protected SmsSendVerifyCodeService smsSendVerifyCodeService;
    @Resource
    private AccountService accountService;

    /************************************* 类方法 *************************************************/
    public String getShowNickName() {
        String nickName = getUser().getRealName();
        if (StringUtils.isNotBlank(nickName)) {
            return nickName;
        }
        else {
            return StringUtil.getShowStr(getUser().getUserName());
        }
    }

    public String getActionName() {
        int index = requestUrl.lastIndexOf(BaseConstants.SEPERATOR);
        if (index >= 0) {
            return requestUrl.substring(index + 1);
        }
        return "";
    }

    /**
     * 初始化数据方法
     */
    @Override
    public void prepare() throws Exception {
        // 访问的URL
        requestUrl = getRequest().getServletPath();
    }

    /**
     * 用户设备类型:[web:web浏览器(苹果和安卓)，wk:微课app，chnmaster:音乐网]
     *
     * @return
     */
    public String getAppType() {
        if (StringUtils.isEmpty(appType)) {
            String userAgent = getRequest().getHeader("User-Agent").toLowerCase();
            if (userAgent.indexOf("chnmaster") > -1) {
                appType = "chnmaster";
            }
            else if (userAgent.indexOf("_weike") > -1) {
                appType = "wk";
            }
            else if (userAgent.indexOf("qqbrowser") > -1) {
                appType = "qq";
            }
            else if (userAgent.indexOf("micromessenger") > -1) {
                appType = "weixin";
            }
            else {
                appType = "web";
            }
        }
        return appType;
    }

    /**
     * 浏览器类型:[android:安卓，ios:苹果，pc:电脑]
     *
     * @return
     */
    public String getDeviceType() {
        if (StringUtils.isBlank(deviceType)) {
            String userAgent = getRequest().getHeader("User-Agent");
            if (StringUtils.isBlank(userAgent)) {
                userAgent = getRequest().getHeader("user-agent");
            }
            if (userAgent != null) {
                if (userAgent.indexOf("iPhone") > -1 || userAgent.indexOf("Mac") > -1) {
                    deviceType = "ios";
                }
                else if (userAgent.indexOf("Android") > -1 || userAgent.indexOf("Linux") > -1) {
                    deviceType = "android";
                }
                else {
                    deviceType = "pc";
                }
            }
            else {
                log.error("服务器获取user-agent失败！");
            }
        }
        return deviceType;
    }

    // /**
    // * 获取ios账户余额
    // *
    // * @return
    // */
    // public float getAccountBalance() {
    // if (getUser() == null) {
    // return 0;
    // }
    // Account a = accountService.getAccountById(getUser().getId());
    // if (null == a) {
    // return 0;
    // }
    // return a.getFunds();
    //
    // }

    /**
     * 返回用户是否为vip用户
     */
    public boolean getIsMember() {
        // 个人用户且为vip用户返回ture
        BaseUser user = getUser();
        return null != user && user.getType() == UserType.PERSONAL.getValue() && user.getIsMember();
    }

    // /**
    // * 压缩图片
    // */
    // public void imageComp(File srcFile, String desPath, int width, int height, boolean proportion, long userId) {
    // Map<String, Object> values = new HashMap<String, Object>();
    // values.put("srcFile", srcFile);
    // values.put("desPath", desPath);
    // values.put("width", width);
    // values.put("height", height);
    // values.put("proportion", proportion);
    // values.put("userId", userId);
    // exoperateProcessorDispatcher.dispatchProcessor("IMAGE_COMPRESS", values);
    // }

    /**
     * 压缩图片
     */
    public void imageComp(String srcPath, String desPath, int width, int height, boolean proportion, long userId) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("srcPath", srcPath);
        values.put("desPath", desPath);
        values.put("width", width);
        values.put("height", height);
        values.put("proportion", proportion);
        values.put("userId", userId);
        exoperateProcessorDispatcher.dispatchProcessor("IMAGE_COMPRESS2", values);
    }

    public String getChnmasterAndroidUrl() {
        JSONObject config = systemMobileService.getMobileConfig(0);
        if (config != null) {
            JSONObject android = (JSONObject) config.get("android");
            chnmasterAndroidUrl = android.getString("name");
        }
        return chnmasterAndroidUrl;
    }

    /**
     * 获取ios下载地址
     */
    public String getChnmasterIOSUrl() {
        JSONObject config = systemMobileService.getMobileConfig(0);
        String iosUrl = null;
        if (config != null) {
            JSONObject android = (JSONObject) config.get("ios");
            iosUrl = android.getString("url");
        }
        return iosUrl;
    }

    @Override
    public BaseUser getUser() {
        if (user == null) {
            user = LoginUtils.getInstance().getUserFromRequest(getRequest());
        }
        return user;
    }

}
