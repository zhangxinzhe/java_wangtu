/*
 * @(#)RewardPicture.java    Created on 2017年7月18日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu;

import net.zdsoft.common.entity.BaseEntity;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月18日 下午1:59:25 $
 */
public class RewardPicture extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private long rewardId;
    private String filePath;

    public long getRewardId() {
        return rewardId;
    }

    public void setRewardId(long rewardId) {
        this.rewardId = rewardId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
