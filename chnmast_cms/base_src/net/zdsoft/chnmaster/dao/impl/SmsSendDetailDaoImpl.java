/*
 * @(#)SmsSendDetailDaoImpl.java    Created on 2013-11-21
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.SmsSendDetailDao;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.entity.sms.SmsSendDetail;
import net.zdsoft.common.enums.ReceiveStatusEnum;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2013-11-21 下午03:55:01 $
 */
@Repository("smsSendDaoDetail")
public class SmsSendDetailDaoImpl extends BaseDaoImpl implements SmsSendDetailDao {
    private static final String add_sms_send_detail = "sms.add_sms_send_detail";
    private static final String update_sms_send_detail_status_by_sequence = "sms.update_sms_send_detail_status_by_sequence";
    private static final String update_sms_send_detail_status_by_sequences = "sms.update_sms_send_detail_status_by_sequences";

    @Override
    public SmsSendDetail getDetail(long id) {
        String sql = "SELECT * FROM T_SMS_SEND_DETAIL WHERE ID = ?";
        return (SmsSendDetail) findForObject(sql, new Object[] { id }, new RowMapper<SmsSendDetail>() {
            @Override
            public SmsSendDetail mapRow(ResultSet rs, int i) throws SQLException {
                SmsSendDetail detail = new SmsSendDetail();
                detail.setId(rs.getLong("id"));
                detail.setSendId(rs.getLong("send_id"));
                detail.setReceiveUserid(rs.getLong("receive_userid"));
                detail.setReceiveUsername("receive_username");
                detail.setReceiveRealname(rs.getString("receive_realname"));
                detail.setMobile(rs.getString("mobile"));
                detail.setReceiveStatus(ReceiveStatusEnum.get(rs.getInt("receive_status")));
                detail.setSequence(rs.getString("sequence"));
                detail.setBatchId(rs.getString("batch_id"));
                return detail;
            }

        });
    }

    @Override
    public int[] addSmsSendDetail(final long sendId, final List<SmsSendDetail> sendDetails) {
        return batchUpdate(getSql(add_sms_send_detail), new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                SmsSendDetail detail = sendDetails.get(i);
                ps.setLong(1, sendId);
                ps.setLong(2, detail.getReceiveUserid());
                ps.setString(3, detail.getReceiveUsername());
                ps.setString(4, detail.getReceiveRealname());
                ps.setString(7, detail.getMobile());
                ps.setInt(8, detail.getReceiveStatus().getValue());
                ps.setString(9, detail.getStatusDesc());
                ps.setString(10, detail.getSequence());
            }

            @Override
            public int getBatchSize() {
                return sendDetails.size();
            }
        });
    }

    @Override
    public int addSmsSendDetail(SmsSendDetail smsSendDetail) {
        return executeUpdate(getSql(add_sms_send_detail),
                new Object[] { smsSendDetail.getSendId(), smsSendDetail.getReceiveUserid(),
                        smsSendDetail.getReceiveUsername(), smsSendDetail.getReceiveRealname(),
                        smsSendDetail.getMobile(), smsSendDetail.getReceiveStatus().getValue(),
                        smsSendDetail.getStatusDesc(), smsSendDetail.getSequence() });
    }

    @Override
    public int updateSmsSendDetailStatusBySequence(String sequence, int gatewayType, ReceiveStatusEnum status,
            String otherReason) {
        return executeUpdate(getSql(update_sms_send_detail_status_by_sequence),
                new Object[] { gatewayType, status.getValue(), otherReason, sequence });
    }

    @Override
    public int updateSmsSendDetailStatusBySequences(String[] sequences, String batchId, int gatewayType,
            ReceiveStatusEnum status, String otherReason) {
        return executeUpdateForInSQL(getSql(update_sms_send_detail_status_by_sequences),
                new Object[] { gatewayType, status.getValue(), otherReason, batchId }, sequences);
    }

    @Override
    public int updateBatchIdBySequences(String[] sequences, int gatewayType, String batchId) {
        String sql = "UPDATE T_SMS_SEND_DETAIL SET BATCH_ID = ?, GATEWAY_TYPE = ? WHERE SEQUENCE IN";
        return executeUpdateForInSQL(sql, new Object[] { batchId, gatewayType }, sequences);
    }

    @Override
    public int updateSmsSendDetailStatusByBatchId(String batchId, int gatewayType, ReceiveStatusEnum status,
            String otherReason, String mobile) {
        String sql = "UPDATE T_SMS_SEND_DETAIL set receive_status = ?, status_desc =? WHERE BATCH_ID = ? AND GATEWAY_TYPE = ? AND mobile = ? ";
        return executeUpdate(sql, new Object[] { status.getValue(), otherReason, batchId, gatewayType, mobile });
    }
}
