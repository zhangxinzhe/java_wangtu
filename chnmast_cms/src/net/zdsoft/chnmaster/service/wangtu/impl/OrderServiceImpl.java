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
import net.zdsoft.chnmaster.enums.wangtu.BiddingStatus;
import net.zdsoft.chnmaster.enums.wangtu.OrderType;
import net.zdsoft.chnmaster.service.account.AccountService;
import net.zdsoft.chnmaster.service.wangtu.OrderService;
import net.zdsoft.chnmaster.service.wangtu.RewardBiddingService;
import net.zdsoft.chnmaster.service.wangtu.RewardService;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.account.Account;
import net.zdsoft.common.enums.OrderStatus;
import net.zdsoft.common.enums.PayType;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月25日 下午1:51:25 $
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private RewardService rewardService;
    @Resource
    private RewardBiddingService rewardBiddingService;

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

    @Override
    public Order getFinishOrderByUserIdAndRewardId(long userId, long rewardId) {

        return orderDao.getFinishOrderByUserIdAndRewardId(userId, rewardId);
    }

    @Override
    public String updateOrderToFinish(long orderId) {
        Order order = orderDao.getOrderByOrderId(orderId);
        if (order == null) {
            return "不存在订单数据！";
        }
        if (order.getStatus() == OrderStatus.SUCCESS) {
            return "success";
        }
        // 退回操作
        if (order.getOrderType() == OrderType.FOUNDS_BACK) {
            Account account = accountService.getAccountById(order.getUserId());
            if (account == null) {
                return "账户数据不存在！";
            }
            double remain = account.getFunds() - order.getPayAmount();
            remain = remain <= 0 ? 0 : remain;
            accountService.updateFundsByAccountId(account.getId(), remain);
            orderDao.updateOrderStatus(orderId, OrderStatus.SUCCESS);
            return "success";
        }
        else if (order.getOrderType() == OrderType.BIDDING_ORDER) {
            if (order.getPayType() == PayType.REMAIN) {
                Account account = accountService.getAccountById(order.getUserId());
                if (account == null) {
                    return "账户数据不存在！";
                }
                double remain = account.getFunds() - order.getPayAmount();
                remain = remain <= 0 ? 0 : remain;
                accountService.updateFundsByAccountId(account.getId(), remain);
            }
            orderDao.updateOrderStatus(orderId, OrderStatus.SUCCESS);
            rewardBiddingService.updateStatusById(order.getRelationId(), BiddingStatus.PAY);
            return "success";
        }
        return "操作失败，请重试！";
    }

    @Override
    public String updateBiddingOrderToFinish(long orderId) {
        Order order = orderDao.getOrderByOrderId(orderId);
        if (order == null) {
            return "不存在订单数据！";
        }
        if (order.getStatus() == OrderStatus.SUCCESS) {
            return "success";
        }
        return null;
    }

    @Override
    public int updateOrderPayType(long orderId, PayType payType) {

        return orderDao.updateOrderPayType(orderId, payType);
    }

    @Override
    public Order getOrderByTradeNo(String tradeNo) {

        return orderDao.getOrderByTradeNo(tradeNo);
    }

}
