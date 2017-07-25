/*
 * @(#)RewardPictureServiceImpl.java    Created on 2017年7月18日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.wangtu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.wangtu.RewardPictureDao;
import net.zdsoft.chnmaster.entity.wangtu.RewardPicture;
import net.zdsoft.chnmaster.service.wangtu.RewardPictureService;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月18日 下午2:08:40 $
 */
@Service("rewardPictureService")
public class RewardPictureServiceImpl implements RewardPictureService {

    @Resource
    private RewardPictureDao rewardPictureDao;

    @Override
    public List<RewardPicture> getListByRewardId(long rewardId) {
        return rewardPictureDao.getListByRewardId(rewardId);
    }

    @Override
    public int deletePictureByIds(Long[] ids) {
        return rewardPictureDao.deletePictureByIds(ids);
    }

    @Override
    public int addPucture(List<RewardPicture> pics) {
        return rewardPictureDao.addPucture(pics);
    }

}
