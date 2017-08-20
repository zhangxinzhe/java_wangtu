/*
 * @(#)AppRewardAction.java    Created on 2017年7月17日
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

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.chnmaster.action.common.CmsPageAction;
import net.zdsoft.chnmaster.entity.wangtu.Catalog;
import net.zdsoft.chnmaster.entity.wangtu.Order;
import net.zdsoft.chnmaster.entity.wangtu.Reward;
import net.zdsoft.chnmaster.entity.wangtu.RewardBidding;
import net.zdsoft.chnmaster.entity.wangtu.RewardPicture;
import net.zdsoft.chnmaster.enums.wangtu.BiddingStatus;
import net.zdsoft.chnmaster.enums.wangtu.OrderType;
import net.zdsoft.chnmaster.enums.wangtu.PushMsgTypeEnum;
import net.zdsoft.chnmaster.enums.wangtu.RewardStatus;
import net.zdsoft.chnmaster.service.account.AccountService;
import net.zdsoft.chnmaster.service.sms.SmsPushMsgService;
import net.zdsoft.chnmaster.service.wangtu.CatalogService;
import net.zdsoft.chnmaster.service.wangtu.OrderService;
import net.zdsoft.chnmaster.service.wangtu.RewardBiddingService;
import net.zdsoft.chnmaster.service.wangtu.RewardService;
import net.zdsoft.common.alipay.entity.WapAlipayParam;
import net.zdsoft.common.alipay.service.AlipayApiService;
import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.dao.queryCondition.EqualCondition;
import net.zdsoft.common.dao.queryCondition.LikeCondition;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.account.Account;
import net.zdsoft.common.enums.OrderStatus;
import net.zdsoft.common.enums.PayDeviceType;
import net.zdsoft.common.enums.PayType;
import net.zdsoft.common.filesystem.util.FileSystemUtil;
import net.zdsoft.common.utils.URLUtil;
import net.zdsoft.common.utils.UUIDUtils;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月17日 下午1:44:19 $
 */
@Scope("prototype")
@Controller
public class AppRewardAction extends CmsPageAction {

    private static final long serialVersionUID = 1L;

    private List<Reward> rewardList;
    private long catalogId;
    private long rewardId;
    private double price;
    private double unfinishPrice;
    private Reward reward;
    private long biddingId;
    private String rewardTitle;
    private PayType payType;
    private WapAlipayParam alipayParam;
    private String payInfo;
    private File[] rewardFiles;
    private String rewardPictureIds;
    private String[] rewardFilesFileName;
    private String[] rewardFilesContentType;
    private long orderId;

    @Resource
    private RewardService rewardService;
    @Resource
    private RewardBiddingService rewardBiddingService;
    @Resource
    private CatalogService catalogServiec;
    @Resource
    private OrderService orderService;
    @Resource
    private SmsPushMsgService smsPushMsgService;
    @Resource
    private AccountService accountService;
    @Resource
    private AlipayApiService alipayApiService;

    /**
     * 悬赏列表
     */
    public void listReward() {
        System.out.println(NetstudyConfig.getParam("rewardpercent"));
        Map<String, Object> json = new HashMap<String, Object>();
        List<QueryCondition> cons = new ArrayList<QueryCondition>();
        if (catalogId > 0) {
            cons.add(new EqualCondition("C.ID", catalogId, Types.INTEGER));
        }
        // 未完成的悬赏
        cons.add(new EqualCondition("R.reward_status", RewardStatus.PUBLISH.getValue(), Types.INTEGER));
        if (!StringUtils.isBlank(rewardTitle)) {
            cons.add(new LikeCondition("R.title", rewardTitle));
        }
        getPage().setRowNum(8);
        rewardList = rewardService.getRewardsByCondition(cons, this.getPage());
        List<Catalog> catalogs = catalogServiec.listCatalog();
        json.put("list", rewardList);
        json.put("catalogs", catalogs);
        json.put("page", getPage());
        printJsonMap(json);
        return;
    }

    /**
     * 详情
     */
    public void rewardDetail() {
        Map<String, Object> json = new HashMap<String, Object>();
        Reward reward = rewardService.getRewardById(rewardId);
        json.put("reward", reward);
        printJsonMap(json);
        return;
    }

    /**
     * 竞价详情
     */
    public void biddingDetail() {
        Map<String, Object> json = new HashMap<String, Object>();
        Reward reward = rewardService.getUserBiddingReward(rewardId, getUser().getId());
        json.put("reward", reward);
        printJsonMap(json);
        return;
    }

    /**
     * 分类列表
     */
    public void listCatalogs() {

        Map<String, Object> json = new HashMap<String, Object>();
        List<Catalog> catalogs = catalogServiec.listCatalog();
        json.put("catalogs", catalogs);
        printJsonMap(json);
        return;
    }

    /**
     * 新增竞价
     */
    public void addRewardBidding() {

        if (getUser() == null) {
            printMsg("请先登录");
            return;
        }
        Reward reward = rewardService.getRewardById(rewardId);
        if (reward == null) {
            printMsg("悬赏信息不存在！");
            return;
        }
        if (reward.getStatus() == RewardStatus.FINISH || reward.getStatus() == RewardStatus.DOING) {
            printMsg("悬赏已经被人抢走啦！");
            return;
        }
        if (rewardBiddingService.isApplyRewardByRewardIdAndUserId(rewardId, getUser().getId()) > 0) {
            printMsg("您已提交竞标！");
            return;
        }
        if (price == 0) {
            printMsg("竞价价格不能为0！");
            return;
        }
        if (reward.getUserId() == getUser().getId()) {
            printMsg("不能竞价自己发布的悬赏！");
            return;
        }

        RewardBidding bidding = new RewardBidding();
        bidding.setUserId(getUser().getId());
        bidding.setRewardId(rewardId);
        bidding.setPrice(price);
        bidding.setUnfinishPrice(unfinishPrice);
        bidding.setStatus(BiddingStatus.UNPAY);
        int i = rewardBiddingService.addRewardBidding(bidding);
        if (i > 0) {
            Map<String, Object> json = new HashMap<String, Object>();
            double platPercent = Double.parseDouble(NetstudyConfig.getParam("rewardpercent"));
            json.put("msg", "success");
            json.put("rewardId", rewardId);
            json.put("rewardPrice", price);
            json.put("platPrice", price * platPercent);
            Account accout = accountService.getAccountById(getUser().getId());
            double userBalance = 0;
            if (accout != null) {
                userBalance = accout.getFunds();
            }
            json.put("userBalance", userBalance);
            json.put("platPercent", (platPercent * 100) + "%");
            printJsonMap(json);
            return;
        }
        printMsg("竞价失败，请重试！");
        return;
    }

    /**
     * 创建竞价订单
     *
     */
    public void createBiddingOrder() {
        if (getUser() == null) {
            printMsg("请先登录");
            return;
        }
        Reward reward = rewardService.getRewardById(rewardId);
        if (reward == null) {
            printMsg("悬赏信息不存在！");
            return;
        }
        RewardBidding rb = rewardBiddingService.getRewardBiddingByByRewardIdAndUserId(rewardId, getUser().getId(),
                BiddingStatus.UNPAY);
        if (rb == null) {
            printMsg("您还未竞价！");
            return;
        }
        // if (rb.getStatus() != BiddingStatus.UNPAY) {
        // printMsg("你已支付！");
        // return;
        // }
        if (null == payType) {
            printMsg("请选择支付方式！");
            return;
        }

        Order order = orderService.getOrderByUserAndRewardId(getUser().getId(), rewardId);
        long orderId = 0;
        if (order == null) {
            order = new Order();
            order.setUserId(getUser().getId());
            order.setRelationId(rb.getId());
            order.setTradeNo(UUIDUtils.newId());
            order.setCreationTime(new Date());
            double percent = Double.valueOf(NetstudyConfig.getParam("rewardpercent"));
            double payAmount = rb.getPrice() * percent;
            payAmount = Math.round(payAmount * 100) * 0.01d;
            order.setPayAmount(payAmount);
            order.setOrderType(OrderType.BIDDING_ORDER);
            order.setStatus(OrderStatus.UNPAY);
            order.setPayType(payType);
            orderId = orderService.addOrder(order);
        }
        else {
            orderId = order.getId();
            // 更新支付方式
            orderService.updateOrderPayType(orderId, payType);
        }
        if (payType == PayType.REMAIN) {
            // 检查余额
            Account account = accountService.getAccountById(getUser().getId());
            if (account.getFunds() < order.getPayAmount()) {
                printMsg("余额不足，请用其他方式支付！");
                return;
            }
        }
        if (orderId == 0) {
            printMsg("订单创建失败，请重试！");
            return;
        }
        else {
            if (PayType.REMAIN == payType) {
                String result = orderService.updateOrderToFinish(orderId);
                Map<String, Object> json = new HashMap<String, Object>();
                json.put("msg", result);
                json.put("order", order);
                json.put("isFinish", result.equals("success"));
                printJson(json);
                return;
            }
            else if (PayType.ALIPAY == payType) {
                Map<String, Object> json = new HashMap<String, Object>();
                try {
                    alipayAppPayMethod(order);
                    json.put("msg", "success");
                    json.put("order", order);
                    json.put("payInfo", payInfo);
                    printJson(json);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else {
                printMsg("支付失败，请重试！");
                return;
            }

        }

    }

    /**
     * 支付宝app支付
     *
     * @throws Exception
     */
    private void alipayAppPayMethod(Order order) throws Exception {
        // 订单信息
        alipayParam = new WapAlipayParam();
        // 获取手机网页即时到账授权
        Map<String, String> params = new HashMap<String, String>();
        params.put("notify_url",
                NetstudyConfig.getParam(BaseConstants.DOMAIN_CMS) + "/appReward/finishBiddingOrder.htm");
        params.put("out_trade_no", order.getTradeNo());
        // 组装支付宝title信息
        String subject = buildSubject();
        params.put("subject", subject);
        params.put("body", subject);
        params.put("total_fee", Double.toString(order.getPayAmount()));

        // 创建订单信息
        payInfo = alipayApiService.mobileSecuritypayPay(params, alipayParam);
    }

    /**
     * 组装支付宝title信息
     *
     * @return
     */
    private String buildSubject() {
        String leftStr = "[";
        String rightStr = "]";
        StringBuffer subject = new StringBuffer();

        subject.append(leftStr + "悬赏竞价支付" + rightStr);

        subject.append(leftStr
                + (StringUtils.isBlank(getUser().getRealName()) ? getUser().getUserName() : getUser().getRealName())
                + rightStr);
        return subject.toString();
    }

    public void finishBiddingOrder() {
        // Order order = orderService.getOrderByOrderId(orderId);
        if (orderId != 0) {
            String result = orderService.updateOrderToFinish(orderId);
            this.printMsg(result);
            return;
        }
        else {
            JSONObject paramsJson = null;
            try {
                paramsJson = alipayApiService.buildTradeNotifyParams(URLUtil.getParams(getRequest()));
                paramsJson.put("pay_type", PayDeviceType.ANDROID);// 支付宝网页支付
            }
            catch (Exception e) {
                log.error("支付宝返回信息解析失败！", e);
                // msg = "支付宝返回信息解析失败！";

            }
            String tradeNo = paramsJson.getString("out_trade_no");
            Order order = orderService.getOrderByTradeNo(tradeNo);
            orderService.updateOrderToFinish(order.getId());
        }

    }

    /**
     * 我发起的悬赏
     */
    public void myReward() {
        Map<String, Object> json = new HashMap<String, Object>();
        if (getUser() == null) {
            printJsonMap(json);
            return;
        }
        getPage().setRowNum(8);
        List<QueryCondition> cons = new ArrayList<QueryCondition>();
        cons.add(new EqualCondition("U.ID", getUser().getId(), Types.INTEGER));
        rewardList = rewardService.getRewardsByCondition(cons, this.getPage());
        json.put("list", rewardList);
        json.put("page", getPage());
        printJsonMap(json);
        return;
    }

    /**
     * 我竞价的悬赏
     */
    public void myRewardBidding() {
        Map<String, Object> json = new HashMap<String, Object>();
        if (getUser() == null) {
            printJsonMap(json);
            return;
        }

        rewardList = rewardService.getMyRewardBidding(getUser().getId(), getPage());
        json.put("list", rewardList);
        double platPercent = Double.parseDouble(NetstudyConfig.getParam("rewardpercent"));
        json.put("platPercent", platPercent * 100 + "%");

        Account accout = accountService.getAccountById(getUser().getId());
        double userBalance = 0;
        if (accout != null) {
            userBalance = accout.getFunds();
        }
        json.put("userBalance", userBalance);
        this.printJsonMap(json);
        return;
    }

    // 新增悬赏
    public void addReward() {
        if (getUser() == null) {
            printMsg("您还未登录！");
            return;
        }
        String str = validateReward();
        if (!StringUtils.isBlank(str)) {
            printMsg(str);
            return;
        }
        reward.setCreateTime(new Date());
        reward.setStatus(RewardStatus.CREATE);
        int i;
        try {
            i = rewardService.addReward(reward, rewardFiles);
        }
        catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        if (i > 0) {
            printMsg("success");
            return;
        }
        printMsg("新增失败，请重试！");
    }

    // 修改悬赏
    public void updateReward() {
        if (getUser() == null) {
            printMsg("您还未登录！");
            return;
        }
        if (reward == null || reward.getId() <= 0) {
            printMsg("悬赏信息查询失败！");
            return;
        }
        String str = validateReward();
        if (!StringUtils.isBlank(str)) {
            printMsg(str);
            return;
        }
        reward.setCreateTime(new Date());
        if (reward.getStatus() == null) {
            reward.setStatus(RewardStatus.CREATE);
        }

        int i;
        try {
            i = rewardService.updateReward(reward, rewardFiles, rewardPictureIds);
        }
        catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        if (i > 0) {
            printMsg("success");
            return;
        }
        printMsg("新增失败，请重试！");
    }

    /**
     * 删除悬赏
     *
     * @return
     */
    public void deleteReward() {
        if (rewardId == 0) {
            printMsg("悬赏数据不存在！");
            return;
        }
        Reward reward = rewardService.getRewardById(rewardId);
        if (reward.getStatus() != RewardStatus.CREATE) {
            printMsg("悬赏状态为" + reward.getStatus().getNameValue() + ",不能删除！");
            return;
        }
        // 删除朱表
        int i = rewardService.deleteReward(rewardId);
        if (i > 0) {
            // 删除图片文件
            if (reward.getPictures() != null && reward.getPictures().size() > 0) {
                try {
                    for (RewardPicture pic : reward.getPictures()) {
                        FileSystemUtil.deleteFile(pic.getFilePath());
                    }
                }
                catch (Exception e) {

                }
            }
            printMsg("success");
        }
        else {
            printMsg("删除失败，请重试！");
        }
    }

    /**
     * 撤销悬赏
     */
    public void cancelReward() {
        if (getUser() == null) {
            printMsg("请先登录");
            return;
        }
        printMsg(rewardService.cancelReward(rewardId));
    }

    // 取消竞价
    public void cancelBidding() {
        if (getUser() == null) {
            printMsg("请先登录");
            return;
        }
        printMsg(rewardBiddingService.updateCancelBiddingReward(biddingId, BiddingStatus.USER_CANCEL));
    }

    /**
     * 发布悬赏
     */
    public void publishReward() {
        if (rewardId == 0) {
            printMsg("悬赏数据不存在！");
            return;
        }
        if (getUser() == null) {
            printMsg("请先登录");
            return;
        }
        int i = rewardService.updateRewardStatus(rewardId, RewardStatus.PUBLISH);
        if (i <= 0) {
            printMsg("发布失败，请重试！");
            return;
        }
        printMsg("success");

    }

    // 选择竞价人
    public void chooseBidding() {
        if (getUser() == null) {
            printMsg("请先登录");
            return;
        }
        if (biddingId <= 0) {
            printMsg("请选择接单人");
            return;
        }
        smsPushMsgService.sendMsg("竞价通知", "您参与的竞价成功", PushMsgTypeEnum.BIDDING_SUCCESS, getUser(), new Long[] { 1l });
        int status = rewardBiddingService.updateStatusToChoosed(biddingId);
        if (status > 0) {
            printMsg("success");
        }
        else {
            printMsg("失败！");
        }

    }

    /**
     * 任务完成
     */
    public void rewardFinish() {
        if (rewardId == 0) {
            printMsg("悬赏数据不存在！");
            return;
        }
        if (getUser() == null) {
            printMsg("请先登录");
            return;
        }
        // if (1 == 1) {
        // printMsg("success");
        // return;
        // }
        if (rewardService.updateRewardStatus(rewardId, RewardStatus.FINISH) > 0) {

            printMsg("success");
        }
        else {
            printMsg("处理失败！");
        }

    }

    private String validateReward() {
        if (reward == null) {
            return "数据不存在！";
        }
        reward.setUserId(getUser().getId());
        if (reward.getCatalogId() == 0) {
            return "请选择分类";
        }
        if (StringUtils.isBlank(reward.getTitle())) {
            return "标题不能为空！";
        }
        if (StringUtils.isBlank(reward.getDescription())) {
            return "描述内容不能为空！";
        }
        if (StringUtils.isBlank(reward.getLocation())) {
            return "请输入地址！";
        }
        if (reward.getDeadline() == null) {
            return "期限不能为空！";
        }

        return null;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(long catalogId) {
        this.catalogId = catalogId;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    public List<Reward> getRewardList() {
        return rewardList;
    }

    public long getRewardId() {
        return rewardId;
    }

    public void setRewardId(long rewardId) {
        this.rewardId = rewardId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUnfinishPrice(double unfinishPrice) {
        this.unfinishPrice = unfinishPrice;
    }

    public File[] getRewardFiles() {
        return rewardFiles;
    }

    public void setRewardFiles(File[] rewardFiles) {
        this.rewardFiles = rewardFiles;
    }

    public String[] getRewardFilesFileName() {
        return rewardFilesFileName;
    }

    public void setRewardFilesFileName(String[] rewardFilesFileName) {
        this.rewardFilesFileName = rewardFilesFileName;
    }

    public String[] getRewardFilesContentType() {
        return rewardFilesContentType;
    }

    public void setRewardFilesContentType(String[] rewardFilesContentType) {
        this.rewardFilesContentType = rewardFilesContentType;
    }

    public void setRewardPictureIds(String rewardPictureIds) {
        this.rewardPictureIds = rewardPictureIds;
    }

    public void setBiddingId(long biddingId) {
        this.biddingId = biddingId;
    }

    public String getRewardTitle() {
        return rewardTitle;
    }

    public void setRewardTitle(String rewardTitle) {
        this.rewardTitle = rewardTitle;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

}
