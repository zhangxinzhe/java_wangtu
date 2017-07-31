/*
 * @(#)OrderServiceImpl.java    Created on 2017年7月25日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.wangtu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.wangtu.OrderDao;
import net.zdsoft.chnmaster.entity.wangtu.Order;
import net.zdsoft.chnmaster.service.wangtu.OrderService;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.enums.OrderStatus;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月25日 下午1:51:25 $
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Override
    public long addOrder(Order order) {
        order.setId(orderDao.generatePrimaryKey());
        int i = orderDao.addOrder(order);
        if (i <= 0) {
            return 0;
        }

        return order.getId();
    }

    @Override
    public Order getOrderByOrderId(long id) {
        return orderDao.getOrderByOrderId(id);
    }

    @Override
    public int updateOrderStatus(long id, OrderStatus status) {
        return orderDao.updateOrderStatus(id, status);
    }

    @Override
    public Order getOrderByUserAndRewardId(long userId, long rewardId) {
        return orderDao.getOrderByUserAndRewardId(userId, rewardId);
    }

    @Override
    public Order getUserFoundsOrder(long userId, OrderStatus status) {
        return orderDao.getUserFoundsOrder(userId, status);
    }

    @Override
    public List<Order> listOrder(List<QueryCondition> cons, PageDto page) {
        return orderDao.listOrder(cons, page);
    }

}
