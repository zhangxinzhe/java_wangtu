/*
 * @(#)SmsSendDaoImpl.java    Created on 2013-11-21
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.SmsSendDao;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.dao.queryCondition.QueryConditionBuilder;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.sms.SmsSend;
import net.zdsoft.common.entity.sms.mapper.SmsSendRowMapper;
import net.zdsoft.keel.util.Validators;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2013-11-21 下午03:36:44 $
 */
@Repository("smsSendDao")
public class SmsSendDaoImpl extends BaseDaoImpl implements SmsSendDao {

    private static final String add_sms_send = "sms.add_sms_send";
    private static final String get_sms_send = "sms.get_sms_send";

    @Override
    public Long addSmsSend(SmsSend smsSend) {
        return saveEntityForKey(getSql(add_sms_send),
                new Object[] { smsSend.getSendUserid(), smsSend.getSendRealname(), smsSend.getSendDate(),
                        smsSend.getContent(), smsSend.getSmsType().getValue(), smsSend.getSendStatus().getValue() });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SmsSend> getSmsSends(List<QueryCondition> queryConditions, PageDto page) {
        QueryConditionBuilder builder = new QueryConditionBuilder();
        builder.addConditions(queryConditions);

        StringBuilder sql = new StringBuilder(getSql(get_sms_send));
        if (!Validators.isEmpty(builder.buildCondition())) {
            sql.append(" AND " + builder.buildCondition());
        }
        sql.append(" ORDER BY S.ID DESC");
        if (null == page) {
            return this.find(sql.toString(), builder.buildParameters(), SmsSendRowMapper.basicRowMapper);
        }
        else {
            return this.findForPage(sql.toString(), builder.buildParameters(), SmsSendRowMapper.basicRowMapper, page);
        }
    }

    @Override
    public int countSmsSend(List<QueryCondition> queryConditions) {
        QueryConditionBuilder builder = new QueryConditionBuilder();
        builder.addConditions(queryConditions);

        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(1) FROM T_SMS_SEND SS, T_SMS_SEND_DETAIL SSD WHERE SS.ID = SSD.SEND_ID");
        if (!Validators.isEmpty(builder.buildCondition())) {
            sql.append(" AND " + builder.buildCondition());
        }
        return findForInt(sql.toString(), builder.buildParameters());
    }
}
