/*
 * @(#)RewardServiceImpl.java    Created on 2017年7月11日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.wangtu.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.basic.UserDao;
import net.zdsoft.chnmaster.dao.wangtu.RewardBiddingDao;
import net.zdsoft.chnmaster.dao.wangtu.RewardDao;
import net.zdsoft.chnmaster.entity.wangtu.Order;
import net.zdsoft.chnmaster.entity.wangtu.Reward;
import net.zdsoft.chnmaster.entity.wangtu.RewardBidding;
import net.zdsoft.chnmaster.entity.wangtu.RewardPicture;
import net.zdsoft.chnmaster.enums.wangtu.BiddingStatus;
import net.zdsoft.chnmaster.enums.wangtu.RewardStatus;
import net.zdsoft.chnmaster.service.account.AccountService;
import net.zdsoft.chnmaster.service.basic.UserService;
import net.zdsoft.chnmaster.service.wangtu.OrderService;
import net.zdsoft.chnmaster.service.wangtu.RewardPictureService;
import net.zdsoft.chnmaster.service.wangtu.RewardService;
import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.account.Account;
import net.zdsoft.common.entity.user.User;
import net.zdsoft.common.filesystem.util.FileSystemUtil;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月11日 上午11:01:21 $
 */
@Service("rewardService")
public class RewardServiceImpl implements RewardService {

    @Resource
    private RewardDao rewardDao;
    @Resource
    private RewardBiddingDao rewardBiddingDao;
    @Resource
    private RewardPictureService rewardPictureService;
    @Resource
    private OrderService orderService;
    @Resource
    private AccountService accountService;
    @Resource
    private UserService userService;


    @Override
    public List<Reward> getRewardsByCondition(List<QueryCondition> condistions, PageDto page) {
        List<Reward> rewards = rewardDao.getRewardsByCondition(condistions, page);
        List<RewardPicture> pics = null;
        List<RewardBidding> biddings = null;
        for (Reward reward : rewards) {
            pics = rewardPictureService.getListByRewardId(reward.getId());
            if (!CollectionUtils.isEmpty(pics)) {
                reward.setPicturePath(pics.get(0).getFilePath());
            }
            biddings = rewardBiddingDao.getRewardBiddingByRewardId(reward.getId());
            if (biddings != null && biddings.size() > 0) {
                reward.setBiddingNum(biddings.size());
            }
        }
        return rewards;
    }

    @Override
    public int addReward(Reward reward, File[] files) throws Exception {
        reward.setId(rewardDao.generatePrimaryKey());
        if (files != null && files.length > 0) {

            List<RewardPicture> pics = new ArrayList<RewardPicture>();
            for (int i = 0; i < files.length; i++) {
                RewardPicture pic = new RewardPicture();
                String systimeStr = System.currentTimeMillis() + i + ".png";
                String filePath = "/upload/reward/picture/" + reward.getId() + "/" + systimeStr;
                FileSystemUtil.saveFile(files[i], filePath);
                pic.setRewardId(reward.getId());
                pic.setFilePath(filePath);
                pics.add(pic);
            }
            // 保存图片
            rewardPictureService.addPucture(pics);
        }

        return rewardDao.addReward(reward);
    }

    @Override
    public int updateReward(Reward reward, File[] files, String rewardPictureIds) throws Exception {
        // 更新之前上传的图片

        List<RewardPicture> rewardPictures = rewardPictureService.getListByRewardId(reward.getId());
        if (CollectionUtils.isNotEmpty(rewardPictures)) {
            // 获取需要的id
            Set<Long> idset = new HashSet<>();
            if (StringUtils.isNotBlank(rewardPictureIds)) {
                String[] ids = rewardPictureIds.split(",");
                if (ArrayUtils.isNotEmpty(ids)) {
                    try {
                        for (int i = 0; i < ids.length; i++) {
                            idset.add(Long.parseLong(ids[i]));
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // 获取要删除的id
            List<Long> deletedIds = new ArrayList<Long>();
            List<String> deletedPaths = new ArrayList<String>();
            for (RewardPicture rewardPicture : rewardPictures) {
                if (!idset.contains(rewardPicture.getId())) {
                    deletedIds.add(rewardPicture.getId());
                    deletedPaths.add(rewardPicture.getFilePath());
                }
            }

            // 删除数据库和文件
            if (CollectionUtils.isNotEmpty(deletedIds)) {
                for (String path : deletedPaths) {
                    if (FileSystemUtil.fileExists(path)) {
                        FileSystemUtil.deleteFile(path);
                    }
                }
                rewardPictureService.deletePictureByIds(deletedIds.toArray(new Long[] {}));
            }
        }

        // 保存图片
        if (files != null && files.length > 0) {
            List<RewardPicture> pics = new ArrayList<RewardPicture>();
            for (int i = 0; i < files.length; i++) {
                RewardPicture pic = new RewardPicture();
                String systimeStr = System.currentTimeMillis() + i + ".png";
                String filePath = "/upload/reward/picture/" + reward.getId() + "/" + systimeStr;
                FileSystemUtil.saveFile(files[i], filePath);
                pic.setRewardId(reward.getId());
                pic.setFilePath(filePath);
                pics.add(pic);
            }
            rewardPictureService.addPucture(pics);
        }

        return rewardDao.updateReward(reward);
    }

    @Override
    public int deleteReward(long id) {
        // 删除图片
        int i = rewardPictureService.deletePicturesByRewardId(id);
        if (i <= 0) {
            return i;
        }
        return rewardDao.deleteReward(id);
    }

    @Override
    public int editReward(Reward reward) {
        return rewardDao.editReward(reward);
    }

    @Override
    public Reward getRewardById(long rewardId) {
        Reward reward = rewardDao.getRewardById(rewardId);
        if (reward != null) {
            reward.setBiddingList(rewardBiddingDao.getRewardBiddingByRewardId(reward.getId()));
            reward.setPictures(rewardPictureService.getListByRewardId(rewardId));
        }
        return reward;
    }

    @Override
    public List<Reward> getMyRewardBidding(long userId, PageDto page) {
    	List<Reward> rewards = rewardDao.getMyRewardBidding(userId, page);
    	double platPercent = Double.parseDouble(NetstudyConfig.getParam("rewardpercent"));
    	for (Reward reward : rewards) {
    		reward.setPlatPrice(reward.getBiddingPrice() * platPercent);
		}
        return rewards;
    }

    @Override
    public int updateRewardStatus(long id, RewardStatus status) {
        if (RewardStatus.FINISH == status) {
            // 完成竞价
            RewardBidding bidding = rewardBiddingDao.getChooseBiddingByRewardId(id);
            rewardBiddingDao.updateStatusById(bidding.getId(), BiddingStatus.FINISH);
        }
        return rewardDao.updateRewardStatus(id, status);
    }

    @Override
    public Reward getUserBiddingReward(long rewardId, long userId) {
        Reward reward = rewardDao.getRewardById(rewardId);
        RewardBidding bidding = rewardBiddingDao.getSelectedRewardBidding(rewardId);
        if(bidding != null){
        	User user = userService.getUserById(bidding.getUserId());
        	bidding.setAvatarFile(user.getAvatarFile());
        }
        reward.setBiddingDetail(bidding);
        return reward;
    }

    @Override
    public String cancelReward(long rewardId) {
        Reward reward = rewardDao.getRewardById(rewardId);
        if (null == reward) {
            return "悬赏数据不存在！";
        }
        if (reward.getStatus() == RewardStatus.FINISH || reward.getStatus() == RewardStatus.CANCEL) {
            return "悬赏已经" + reward.getStatus().getNameValue() + "!";

        }
        int i = rewardDao.updateRewardStatus(rewardId, RewardStatus.CANCEL);
        if (i <= 0) {
            return "撤销失败，请重试！";
        }
        // 退回竞价金额
        List<RewardBidding> list = rewardBiddingDao.getRewardBiddingByRewardId(reward.getId());
        if (CollectionUtils.isNotEmpty(list)) {
            for (RewardBidding bidding : list) {
                // 修改竞价状态
                int m = rewardBiddingDao.updateStatusById(bidding.getId(), BiddingStatus.PUBLISHER_CANCEL);
                if (m > 0) {
                    // 查询竞价支付订单
                    Order order = orderService.getFinishOrderByUserIdAndRewardId(bidding.getUserId(),
                            bidding.getRewardId());
                    if (order != null) {
                        // 支付金额退回
                        Account a = accountService.getAccountById(bidding.getUserId());
                        // a.setFunds(a.getFunds() + order.getPayAmount());
                        accountService.updateFundsByAccountId(a.getId(), a.getFunds() + order.getPayAmount());
                    }
                }
            }
        }

        return "success";
    }

}
