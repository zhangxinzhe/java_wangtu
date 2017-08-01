/*
 * @(#)RewardServiceImpl.java    Created on 2017年7月11日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.wangtu.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.wangtu.RewardBiddingDao;
import net.zdsoft.chnmaster.dao.wangtu.RewardDao;
import net.zdsoft.chnmaster.entity.wangtu.Reward;
import net.zdsoft.chnmaster.entity.wangtu.RewardBidding;
import net.zdsoft.chnmaster.entity.wangtu.RewardPicture;
import net.zdsoft.chnmaster.enums.wangtu.RewardStatus;
import net.zdsoft.chnmaster.service.wangtu.RewardPictureService;
import net.zdsoft.chnmaster.service.wangtu.RewardService;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
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
    public int updateReward(Reward reward, File[] files,String rewardPictureIds) throws Exception{
    	//更新之前上传的图片
    	List<RewardPicture> rewardPictures = rewardPictureService.getListByRewardId(reward.getId());
    	if(CollectionUtils.isNotEmpty(rewardPictures)){
    		Map<Long,String> pathMap = new HashMap<Long,String>();
    		for (RewardPicture rewardPicture : rewardPictures) {
    			pathMap.put(rewardPicture.getId(), rewardPicture.getFilePath());
			}
    		
    		//获取需要删除的id
    		List<Long> deletedIds = new ArrayList<Long>();
    		List<String> deletedPaths = new ArrayList<String>();
    		if(StringUtils.isNotBlank(rewardPictureIds)){
            	String[] ids = rewardPictureIds.split(",");
            	if(ArrayUtils.isNotEmpty(ids)){
            		try {
            			String path = null;
    					for (int i = 0; i < ids.length; i++) {
    						long id = Long.parseLong(ids[i]);
    						if((path =pathMap.get(id)) != null){
    							deletedIds.add(id);
    							deletedPaths.add(path);
    						}
    					}
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
            	}
            }else{
        		String path = null;
        		Set<Long> idSet = pathMap.keySet();
        		for (Long id : idSet) {
        			path = pathMap.get(id);
        			deletedIds.add(id);
        			deletedPaths.add(path);
				}
        	}
    		
    		//删除数据库和文件
    		if(CollectionUtils.isNotEmpty(deletedIds)){
        		for (String path : deletedPaths) {
        			if(FileSystemUtil.fileExists(path)){
    					FileSystemUtil.deleteFile(path);
    				}
				}
        		rewardPictureService.deletePictureByIds(deletedIds.toArray(new Long[]{}));
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
        return rewardDao.getMyRewardBidding(userId, page);
    }

    @Override
    public int updateRewardStatus(long id, RewardStatus status) {
        return rewardDao.updateRewardStatus(id, status);
    }

}
