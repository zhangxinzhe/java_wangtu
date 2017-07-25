/*
 * @(#)RewardpictureService.java    Created on 2017年7月18日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.wangtu;

import java.util.List;

import net.zdsoft.chnmaster.entity.wangtu.RewardPicture;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月18日 下午2:06:23 $
 */
public interface RewardPictureService {

    /**
     * 列表
     *
     * @param rewardId
     * @return
     */
    public List<RewardPicture> getListByRewardId(long rewardId);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public int deletePictureByIds(Long[] ids);

    /**
     * 新增
     *
     * @param pics
     * @return
     */
    public int addPucture(List<RewardPicture> pic);
}
