/*
 * @(#)OrderDaoImpl.java    Created on 2017年7月25日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.wangtu.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.wangtu.OrderDao;
import net.zdsoft.chnmaster.entity.wangtu.Order;
import net.zdsoft.chnmaster.entity.wangtu.mapper.OrderMapper;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.dao.queryCondition.QueryConditionBuilder;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.enums.OrderStatus;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月25日 下午1:52:37 $
 */
@SuppressWarnings("unchecked")
@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

    @Override
    public int addOrder(Order order) {
        String sql = "INSERT INTO T_ORDER (ID,TRADE_NO,RELATION_ID,USER_ID,PAY_AMOUNT,CREATION_TIME,"
                + "ORDER_STATUS,PAY_TYPE,ORDER_TYPE,REMARK)VALUES(?,?,?,?,?,?,?,?,?,?)";
        return this.executeUpdate(sql,
                new Object[] { order.getId(), order.getTradeNo(), order.getRelationId(), order.getUserId(),
                        order.getPayAmount(), new Date(), order.getStatus().getValue(), order.getPayType().getValue(),
                        order.getOrderType().getValue(), order.getRemark() });
    }

    @Override
    public Order getOrderByOrderId(long id) {
        String sql = "SELECT * FROM T_ORDER WHERE ID=? ";
        return (Order) findForObject(sql, new Object[] { id }, OrderMapper.instance());
    }

    @Override
    public int updateOrderStatus(long id, OrderStatus status) {
        String sql = "UPDATE T_ORDER SET ORDER_STATUS = ? WHERE ID=? ";
        return executeUpdate(sql, new Object[] { status.getValue(), id });
    }

    @Override
    public long generatePrimaryKey() {

        return this.generatePrimaryKey("T_ORDER");
    }

    @Override
    public Order getOrderByUserAndRewardId(long userId, long rewardId) {
        String sql = "SELECT * FROM T_ORDER WHERE USER_ID=? AND RELATION_ID= ? ";
        return (Order) this.findForObject(sql, new Object[] { userId, rewardId }, OrderMapper.instance());
    }

    @Override
    public Order getUserFoundsOrder(long userId, OrderStatus status) {
        String sql = "SELECT * FROM T_ORDER WHERE USER_ID=? AND RELATION_ID=? AND ORDER_STATUS = ?";
        return (Order) findForObject(sql, new Object[] { userId, userId, status.getValue() }, OrderMapper.instance());
    }

    @Override
    public List<Order> listOrder(List<QueryCondition> cons, PageDto page) {
        String sql = "SELECT O.*,A.*,u.username,u.realname FROM T_ORDER O ,T_USER U,T_ACCOUNT A WHERE O.USER_ID=U.ID AND U.ID=A.ID ";
        QueryConditionBuilder builder = new QueryConditionBuilder();
        builder.addConditions(cons);
        String paramstr = builder.buildCondition();
        if (StringUtils.isNotBlank(paramstr)) {
            sql += " and " + paramstr;
        }
        if (null == page) {
            return this.find(sql, builder.buildParameters(), OrderMapper.fundsMapper);
        }
        return this.findForPage(sql, builder.buildParameters(), OrderMapper.fundsMapper, page);
    }

    @Override
    public Order getFinishOrderByUserIdAndRewardId(long userId, long rewardId) {
        String sql = "SELECT * FROM T_ORDER WHERE USER_ID=? AND RELATION_ID=? AND ORDER_STATUS=?";
        return (Order) findForObject(sql, new Object[] { userId, rewardId, OrderStatus.SUCCESS },
                OrderMapper.instance());
    }

}
