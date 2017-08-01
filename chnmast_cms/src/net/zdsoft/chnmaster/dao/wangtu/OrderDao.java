/*
 * @(#)OrderDao.java    Created on 2017年7月25日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.wangtu;

import java.util.List;

import net.zdsoft.chnmaster.entity.wangtu.Order;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.enums.OrderStatus;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月25日 下午1:51:53 $
 */
public interface OrderDao {

    /**
     * 获取主键
     *
     * @return
     */
    public long generatePrimaryKey();

    /**
     * 新增订单
     *
     * @param order
     * @return 订单id
     */
    public int addOrder(Order order);

    /**
     * 查询订单
     *
     * @param id
     * @return
     */
    public Order getOrderByOrderId(long id);

    /**
     * 订单状态修改
     *
     * @param id
     * @param status
     * @return
     */
    public int updateOrderStatus(long id, OrderStatus status);

    /**
     * order.relationId 关联 rewardId
     *
     * @param userId
     * @param rewardId
     * @return
     */
    public Order getOrderByUserAndRewardId(long userId, long rewardId);

    /**
     * 获取用户的提现订单
     *
     * @param userId
     * @return
     */
    public Order getUserFoundsOrder(long userId, OrderStatus status);

    /**
     * 查询提现订单
     *
     * @param id
     * @param status
     * @return
     */
    public List<Order> listOrder(List<QueryCondition> cons, PageDto page);

    /**
     * 获取用户竞价订单
     *
     * @param userId
     * @param rewardId
     * @return
     */
    public Order getFinishOrderByUserIdAndRewardId(long userId, long rewardId);
}
