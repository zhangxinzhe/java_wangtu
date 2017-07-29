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

import net.zdsoft.chnmaster.action.common.CmsPageAction;
import net.zdsoft.chnmaster.entity.wangtu.Catalog;
import net.zdsoft.chnmaster.entity.wangtu.Order;
import net.zdsoft.chnmaster.entity.wangtu.Reward;
import net.zdsoft.chnmaster.entity.wangtu.RewardBidding;
import net.zdsoft.chnmaster.entity.wangtu.RewardPicture;
import net.zdsoft.chnmaster.enums.wangtu.BiddingStatus;
import net.zdsoft.chnmaster.enums.wangtu.OrderType;
import net.zdsoft.chnmaster.enums.wangtu.RewardStatus;
import net.zdsoft.chnmaster.service.wangtu.CatalogService;
import net.zdsoft.chnmaster.service.wangtu.OrderService;
import net.zdsoft.chnmaster.service.wangtu.RewardBiddingService;
import net.zdsoft.chnmaster.service.wangtu.RewardService;
import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.dao.queryCondition.EqualCondition;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.enums.OrderStatus;
import net.zdsoft.common.enums.PayType;
import net.zdsoft.common.filesystem.util.FileSystemUtil;
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

    private File[] rewardFiles;
    private String[] rewardFilesFileName;
    private String[] rewardFilesContentType;

    @Resource
    private RewardService rewardService;
    @Resource
    private RewardBiddingService rewardBiddingService;
    @Resource
    private CatalogService catalogServiec;
    @Resource
    private OrderService orderService;

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
        rewardList = rewardService.getRewardsByCondition(cons, this.getPage());
        List<Catalog> catalogs = catalogServiec.listCatalog();
        json.put("list", rewardList);
        json.put("catalogs", catalogs);
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
        RewardBidding bidding = new RewardBidding();
        bidding.setUserId(getUser().getId());
        bidding.setRewardId(rewardId);
        bidding.setPrice(price);
        bidding.setUnfinishPrice(unfinishPrice);
        bidding.setStatus(BiddingStatus.UNPAY);
        int i = rewardBiddingService.addRewardBidding(bidding);
        if (i > 0) {
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("msg", "success");
            json.put("rewardId", rewardId);
            json.put("price", price);
            json.put("pecent", NetstudyConfig.getParam("rewardpercent"));
            printMsg("success");
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
        RewardBidding rb = rewardBiddingService.getRewardBiddingByByRewardIdAndUserId(rewardId, getUser().getId());
        if (rb == null) {
            printMsg("您还未竞价！");
            return;
        }
        if (rb.getStatus() != BiddingStatus.UNPAY) {
            printMsg("你已支付！");
            return;
        }
        Order order = orderService.getOrderByUserAndRewardId(getUser().getId(), rewardId);
        long orderId = 0;
        if (order == null) {
            order = new Order();
            order.setUserId(getUser().getId());
            order.setRelationId(rewardId);
            order.setTradeNo(UUIDUtils.newId());
            order.setCreationTime(new Date());
            double percent = Double.valueOf(NetstudyConfig.getParam("rewardpercent"));
            double payAmount = rb.getPrice() * percent;
            payAmount = Math.round(payAmount * 100) * 0.01d;
            order.setPayAmount(payAmount);
            order.setOrderType(OrderType.BIDDING_ORDER);
            order.setStatus(OrderStatus.UNPAY);
            order.setPayType(PayType.UNPAY);
            orderId = orderService.addOrder(order);
        }
        else {
            orderId = order.getId();
        }
        if (orderId == 0) {
            printMsg("订单创建失败，请重试！");
            return;
        }
        else {
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("msg", "success");
            json.put("order", order);
            printJson(json);
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

        List<QueryCondition> cons = new ArrayList<QueryCondition>();
        cons.add(new EqualCondition("U.ID", getUser().getId(), Types.INTEGER));
        rewardList = rewardService.getRewardsByCondition(cons, this.getPage());
        json.put("list", rewardList);
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
        reward.setStatus(RewardStatus.PUBLISH);
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
        printMsg("发布失败，请重试！");
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
        if(reward.getDeadline() == null){
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

}
