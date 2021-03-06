/*
 * @(#)AppUserAction.java    Created on 2017年7月14日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.wangtu;

import java.io.File;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.MobilePageAction;
import net.zdsoft.chnmaster.entity.wangtu.Comment;
import net.zdsoft.chnmaster.entity.wangtu.Order;
import net.zdsoft.chnmaster.entity.wangtu.Reward;
import net.zdsoft.chnmaster.entity.wangtu.RewardBidding;
import net.zdsoft.chnmaster.enums.wangtu.BiddingStatus;
import net.zdsoft.chnmaster.enums.wangtu.OrderType;
import net.zdsoft.chnmaster.service.account.AccountService;
import net.zdsoft.chnmaster.service.basic.UserService;
import net.zdsoft.chnmaster.service.wangtu.CommentService;
import net.zdsoft.chnmaster.service.wangtu.OrderService;
import net.zdsoft.chnmaster.service.wangtu.RewardBiddingService;
import net.zdsoft.chnmaster.service.wangtu.RewardService;
import net.zdsoft.chnmaster.utils.CookieUtils;
import net.zdsoft.chnmaster.utils.LoginUtils;
import net.zdsoft.common.dao.queryCondition.EqualCondition;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.dao.queryCondition.UserDefinedCondition;
import net.zdsoft.common.entity.account.Account;
import net.zdsoft.common.entity.user.User;
import net.zdsoft.common.enums.OrderStatus;
import net.zdsoft.common.enums.PayType;
import net.zdsoft.common.enums.StatusEunm;
import net.zdsoft.common.enums.UserRegTypeEnum;
import net.zdsoft.common.enums.UserType;
import net.zdsoft.common.filesystem.util.FileSystemUtil;
import net.zdsoft.common.utils.Util;
import net.zdsoft.keel.util.UUIDUtils;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月14日 上午10:33:56 $
 */
@Scope("prototype")
@Controller
public class AppUserAction extends MobilePageAction {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;
    private String realName;
    private String telephone;
    private String rePassword;

    private File avatar;
    private String avatarFileName;
    private String avatarContentType;
    private String applyType = "alipay";// 提现方式 默认支付宝，bank:银行卡
    private String alipayAccount;
    private String bankName;
    private String fundsAccount;
    private String fundsName;
    private long commentId;
    private User updateUser;

    private double applyFounds;
    private long rewardId;
    private Comment comment;
    private long userId;

    private File[] commentFiles;
    private String[] commentFilesFileName;
    private String[] commentFilesContentType;

    @Resource
    private UserService userService;
    @Resource
    private AccountService accountService;
    @Resource
    private OrderService orderService;
    @Resource
    private CommentService commentService;
    @Resource
    private RewardService rewardService;
    @Resource
    private RewardBiddingService rewardBiddingService;

    /**
     * 登录
     */
    public void userLogin() {
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
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("msg", "success");
        json.put("avatarFile", systemUser.getAvatarFile());
        json.put("id", systemUser.getId());
        json.put("realName", systemUser.getRealName());

        // 将userType赋值给type
        systemUser.setType(systemUser.getUserType().getValue());
        LoginUtils.getInstance().writeUser(getRequest(), getResponse(), systemUser);
        printJsonMap(json);
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
        u.setRealName(realName);
        u.setPhone(telephone);
        u.setPassword(Util.encodePassword(password));
        u.setCreateTime(new Date());
        u.setIsCancel(StatusEunm.NORMAL);
        u.setUserType(UserType.PERSONAL);
        u.setRegisterType(UserRegTypeEnum.BACK_ADD);
        long id = userService.addUserAndSendMessage(u);
        if (id > 0) {

            User systemUser = userService.getUserByUserNameAndPassward(userName, Util.encodePassword(password));

            // 将userType赋值给type
            systemUser.setType(systemUser.getUserType().getValue());
            LoginUtils.getInstance().writeUser(getRequest(), getResponse(), systemUser);
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
        printMsg("success");

    }

    private String validateRegister() {
        if (StringUtils.isBlank(this.telephone)) {
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
        User u = userService.getUserByUserName(userName);
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
            setAvatar(avatar);
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
     * 更新用户信息
     */
    public void updateUserInfo() {
        if (null == getUser()) {
            printMsg("请先登录！");
            return;
        }
        if (null == updateUser) {
            printMsg("保存失败，请重试！");
            return;
        }
        if (StringUtils.isEmpty(updateUser.getRealName())) {
            printMsg("请输入姓名！");
            return;
        }
        if (null == updateUser.getSex()) {
            printMsg("请输入性别！");
            return;
        }
        if (StringUtils.isEmpty(updateUser.getIndustry())) {
            printMsg("请输入行业！");
            return;
        }
        if (null == updateUser.getBirthday()) {
            printMsg("请输入生日日期！");
            return;
        }
        updateUser.setId(getUser().getId());
        int i = userService.updateAppUser(updateUser);
        if (i <= 0) {
            printMsg("保存失败，请重试！");
            return;
        }
        printMsg("success");
    }

    /**
     * 账户信息
     */
    public void account() {
        Map<String, Object> json = new HashMap<String, Object>();
        if (getUser() != null) {
            Account account = accountService.getAccountById(getUser().getId());
            json.put("funds", account.getFunds());
        }
        printJsonMap(json);
    }

    /**
     * 申请提现
     */
    public void accountApply() {
        if (getUser() == null) {
            printMsg("请先登录！");
            return;
        }
        Account account = accountService.getAccountById(getUser().getId());
        if (account == null) {
            printMsg("没有您的余额信息！");
            return;
        }
        if (account.getFunds() <= 0) {
            printMsg("账户余额为0，不能提现！");
            return;
        }

        if ("alipay".equals(applyType)) {
            if (StringUtils.isBlank(fundsAccount)) {
                printMsg("请输入支付宝账号！");
                return;
            }
            account.setAlipayAccount(fundsAccount);

        }
        else {
            if (StringUtils.isBlank(this.bankName)) {
                printMsg("请输入银行名称！");
                return;
            }
            if (StringUtils.isBlank(this.fundsName)) {
                printMsg("请输入转账姓名！");
                return;
            }
            if (StringUtils.isBlank(this.fundsAccount)) {
                printMsg("请输入银行账号！");
                return;
            }
            account.setBankUserName(fundsName);
            account.setBankAccount(fundsAccount);
            account.setBankName(bankName);

        }
        // 查询用户未完成的提现订单
        Order order = orderService.getUserFoundsOrder(getUser().getId(), OrderStatus.UNPAY);
        if (order != null) {
            printMsg("您的提现申请还在处理中，请稍后再试！");
            return;
        }
        order = new Order();
        if ("alipay".equals(applyType)) {
            order.setRemark("支付宝");
        }
        else {
            order.setRemark("银行转账");
        }
        order.setUserId(getUser().getId());
        order.setRelationId(getUser().getId());
        order.setCreationTime(new Date());
        order.setPayAmount(account.getFunds());
        order.setOrderType(OrderType.FOUNDS_BACK);
        order.setStatus(OrderStatus.UNPAY);
        order.setPayType(PayType.OFFLINE);
        order.setTradeNo(UUIDUtils.newId());

        long orderId = orderService.addOrder(order);
        if (orderId > 0) {
            printMsg("success");
            accountService.updateAccount(account);
            return;
        }
        else {
            printMsg("提现失败 请重试！");
            return;
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
        if (userId <= 0) {
            userId = getUser().getId();
        }
        User u = userService.getUserById(userId);

        // u.setBirthday(u.getBirthday());// 代码待
        int serviceAttitude = commentService.getUserServiceAttitudeAVG(userId);
        int serviceQuility = commentService.getUserServiceQuityAVG(userId);
        u.setComprehensiveScore(serviceAttitude / 20.0f + serviceQuility / 20.0f);
        u.setServiceAttitude(serviceAttitude / 10.0f);
        u.setServiceQuility(serviceQuility / 10.0f);

        json.put("userInfo", u);
        printJsonMap(json);
    }

    /**
     * 回复
     */
    private String replyContent;

    public void replyComment() {
        if (StringUtils.isEmpty(replyContent)) {
            printMsg("请输入回复内容！");
            return;
        }

        int i = commentService.saveCommentReply(commentId, replyContent);
        if (i > 0) {
            printMsg("success");
            return;
        }
        printMsg("回复失败，请重试！");

    }

    // 获取评价人基本信息
    public void getCommentUserInfo() {
        // 通过rewardId，获取接单人信息
        Reward reward = rewardService.getRewardById(rewardId);
        if (null == reward) {
            printMsg("悬赏信息不存在！");
            return;
        }
        RewardBidding bidding = rewardBiddingService.getChooseBiddingByRewardId(rewardId);
        User user = null;
        if (bidding != null) {
            user = userService.getUserById(bidding.getUserId());
        }
        // User user = userService.getUserById(userId);
        // User user = new User();
        // user.setId(1);
        // user.setAvatarFile("/upload/avatar/11501951205568.png");
        // user.setRealName("韩庆仁");

        Map<String, Object> json = new HashMap<String, Object>();
        json.put("userInfo", user);
        json.put("rewardId", rewardId);
        printJsonMap(json);
    }

    // 新增评论
    public void addComment() {
        if (null == getUser()) {
            printMsg("请先登录！");
            return;
        }
        // Reward reward = rewardService.getRewardById(comment.getRewardId());
        RewardBidding bidding = rewardBiddingService.getChooseBiddingByRewardId(comment.getRewardId());
        if (null == bidding) {
            printMsg("悬赏数据不存在！");
            return;
        }
        if (bidding.getStatus() != BiddingStatus.FINISH) {
            printMsg("当前悬赏还未完成，不能评价！");
            return;
        }

        comment.setUserId(getUser().getId());
        comment.setReviewerId(bidding.getUserId());
        comment.setCommentTime(new Date());
        comment.setContent("");
        int i = 0;
        try {
            i = commentService.addComment(comment, commentFiles);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (i > 0) {
            printMsg("success");
            return;
        }
        printMsg("评价失败，请重试!");

    }

    /**
     * 获取评论信息
     */
    private String commentType = "all";
    private boolean hasContent;

    public void getUserComments() {
        // commentType 查询类型
        // all,appease,notAppease,hasPic
        // hasContent 只显示有评论的
        if (userId <= 0) {
            userId = getUser().getId();
        }

        List<QueryCondition> cons = new ArrayList<QueryCondition>();
        cons.add(new EqualCondition("t.reviewer_id", userId, Types.INTEGER));
        if ("appease".equals(commentType)) {
            cons.add(new EqualCondition("t.is_satisfy", 1, Types.INTEGER));
        }
        else if ("notAppease".equals(commentType)) {
            cons.add(new EqualCondition("t.is_satisfy", 0, Types.INTEGER));
        }
        if (hasContent) {
            cons.add(new UserDefinedCondition("service_quility_content IS NOT NULL and service_quility_content != ''",
                    new Object[] {}, new int[] {}));
            cons.add(new UserDefinedCondition("service_attitude_content IS NOT NULL and service_attitude_content != ''",
                    new Object[] {}, new int[] {}));
        }
        List<Comment> comments = commentService.listCommentByUserId(cons);
        // for (int i = 0; i < 4; i++) {
        // Comment comment = new Comment();
        // comment.setId(i);
        // comment.setUserId(2);
        // comment.setReviewerId(1);
        // comment.setServiceQuality(5.8f);
        // comment.setServiceAttitude(2.1f);
        // comment.setServiceAttitudeContent("dddddddddddddddddddd");
        // comment.setServiceQualityContent(
        // "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        // comment.setCommentTime(new Date());
        // if ((i % 5) == 0) {
        // comment.setReplyContent(
        // "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        // }
        // comment.setReplyTime(new Date());
        // comment.setUserName("哈哈哈哈");
        // comments.add(comment);
        //
        // if (i == 3) {
        // List<CommentPicture> commentPictures = new ArrayList<CommentPicture>();
        // CommentPicture pic = new CommentPicture();
        // pic.setPicturePath("qr23r.png");
        // commentPictures.add(pic);
        // comment.setCommentPictures(commentPictures);
        // pic = new CommentPicture();
        // pic.setPicturePath("qr23r.png");
        // commentPictures.add(pic);
        // comment.setCommentPictures(commentPictures);
        // pic = new CommentPicture();
        // pic.setPicturePath("qr23r.png");
        // commentPictures.add(pic);
        // comment.setCommentPictures(commentPictures);
        // }
        // }
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("comments", comments);
        json.put("contentTypeAll", commentService.getAllUserCommentCount(userId));
        json.put("contentTypeAppease", commentService.getUserSatisfyCount(userId, 1));
        json.put("contentTypeNotAppease", commentService.getUserSatisfyCount(userId, 0));
        // json.put("contentTypeHasPic", 11);
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

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public String getApplyType() {
        return applyType;
    }

    public double getApplyFounds() {
        return applyFounds;
    }

    public void setApplyFounds(double applyFounds) {
        this.applyFounds = applyFounds;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public boolean isHasContent() {
        return hasContent;
    }

    public void setHasContent(boolean hasContent) {
        this.hasContent = hasContent;
    }

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public long getRewardId() {
        return rewardId;
    }

    public void setRewardId(long rewardId) {
        this.rewardId = rewardId;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public File[] getCommentFiles() {
        return commentFiles;
    }

    public void setCommentFiles(File[] commentFiles) {
        this.commentFiles = commentFiles;
    }

    public String[] getCommentFilesFileName() {
        return commentFilesFileName;
    }

    public void setCommentFilesFileName(String[] commentFilesFileName) {
        this.commentFilesFileName = commentFilesFileName;
    }

    public String[] getCommentFilesContentType() {
        return commentFilesContentType;
    }

    public void setCommentFilesContentType(String[] commentFilesContentType) {
        this.commentFilesContentType = commentFilesContentType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getFundsAccount() {
        return fundsAccount;
    }

    public void setFundsAccount(String fundsAccount) {
        this.fundsAccount = fundsAccount;
    }

    public String getFundsName() {
        return fundsName;
    }

    public void setFundsName(String fundsName) {
        this.fundsName = fundsName;
    }

}
