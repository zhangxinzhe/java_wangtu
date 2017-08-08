/*
 * @(#)OrderManageAction.java    Created on 2017年8月7日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.wangtu;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsPageAction;
import net.zdsoft.chnmaster.entity.wangtu.Order;
import net.zdsoft.chnmaster.service.wangtu.OrderService;
import net.zdsoft.common.dao.queryCondition.EqualCondition;
import net.zdsoft.common.dao.queryCondition.LikeCondition;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.enums.OrderStatus;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年8月7日 下午2:25:37 $
 */
@Scope("prototype")
@Controller
public class OrderManageAction extends CmsPageAction {

    private static final long serialVersionUID = 1L;

    private long userId;
    private long orderId;
    private String userName;
    private String realName;
    List<Order> orderList;

    @Resource
    private OrderService orderService;

    public String orderBackManage() {
        List<QueryCondition> cons = new ArrayList<QueryCondition>();
        cons.add(new EqualCondition("o.order_status", OrderStatus.UNPAY.getValue(), Types.INTEGER));
        if (StringUtils.isNotBlank(userName)) {
            cons.add(new LikeCondition("u.username", userName));
        }
        if (StringUtils.isNotBlank(realName)) {
            cons.add(new LikeCondition("u.realName", realName));
        }
        orderList = orderService.listOrder(cons, getPage());
        return SUCCESS;
    }

    // 完成提现
    public void finishOrder() {
        this.printMsg(orderService.updateOrderToFinish(orderId));
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

}
