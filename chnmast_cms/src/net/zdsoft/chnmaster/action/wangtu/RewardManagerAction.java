/*
 * @(#)RewardManager.java    Created on 2017年7月11日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.wangtu;

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
import net.zdsoft.chnmaster.entity.wangtu.Reward;
import net.zdsoft.chnmaster.service.wangtu.RewardService;
import net.zdsoft.common.dao.queryCondition.LikeCondition;
import net.zdsoft.common.dao.queryCondition.QueryCondition;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月11日 下午2:57:30 $
 */
@Scope("prototype")
@Controller
public class RewardManagerAction extends CmsPageAction {

    private static final long serialVersionUID = 1L;

    private List<Reward> rewardList;
    private String realName;
    private String userName;
    private Date beginTime;
    private Date endTime;

    private Reward reward;

    @Resource
    private RewardService rewardService;

    public String rewardManage() {
        rewardList = rewardService.getRewardsByCondition(builderParam(), this.getPage());
        return SUCCESS;
    }

    private List<QueryCondition> builderParam() {
        List<QueryCondition> cons = new ArrayList<QueryCondition>();
        if (StringUtils.isNotBlank(realName)) {
            cons.add(new LikeCondition("U.realname", realName));
        }
        if (StringUtils.isNotBlank(userName)) {
            cons.add(new LikeCondition("U.USERNAME", userName));
        }
        return cons;

    }

    // 新增
    public void addReward() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("result", "fail");
        if (reward == null) {
            this.printJson(json);
            return;
        }
        String validateStr = validateReward();
        if (StringUtils.isNotBlank(validateStr)) {
            json.put("msg", validateStr);
            this.printJson(json);
            return;
        }
    }

    private String validateReward() {
        if (reward.getCatalogId() == 0) {
            return "请选择分类！";
        }
        if (StringUtils.isBlank(reward.getTitle())) {
            return "标题不能为空！";
        }
        if (StringUtils.isBlank(reward.getDescription())) {
            return "悬赏描述不能为空！";
        }
        if (StringUtils.isBlank(reward.getLocation())) {
            return "请输入地址！";
        }
        return null;
    }

    public List<Reward> getRewardList() {
        return rewardList;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

}
