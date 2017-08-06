package net.zdsoft.chnmaster.dao.sms.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.entity.wangtu.SmsPushMsg;


/**
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月13日 下午2:35:12 $
 */
public class SmsPushMsgDaoRowMapper extends SmsPushMsgRowMapper {
    public static PushMsgReadStatusRowMapper rowMapper = new PushMsgReadStatusRowMapper();

    static class PushMsgReadStatusRowMapper implements RowMapper<SmsPushMsg> {
        @Override
        public SmsPushMsg mapRow(ResultSet rs, int i) throws SQLException {
            SmsPushMsg pushMsg = getInstance().mapRow(rs, i);
            pushMsg.setReadStatus(rs.getInt("READ_STATUS"));
            pushMsg.setReadTime(rs.getTimestamp("READ_TIME"));
            pushMsg.setPushUserId(rs.getLong("PUSH_USER_ID"));
            return pushMsg;
        }

    }
}
