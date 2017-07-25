package net.zdsoft.chnmaster.entity.system.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.entity.system.SystemUser;
import net.zdsoft.chnmaster.enums.CmsUserType;
import net.zdsoft.common.enums.YesNoType;

@SuppressWarnings("rawtypes")
public class SystemUserRowMapper implements RowMapper {

    private static final SystemUserRowMapper mapper = new SystemUserRowMapper();
    public static SystemUserIdRowMapper systemUserIdRowMapper = new SystemUserIdRowMapper();

    public static SystemUserRowMapper instance() {
        return mapper;
    }

    static class SystemUserIdRowMapper implements RowMapper<Long> {
        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getLong("ID");
        }
    }

    @Override
    public Object mapRow(ResultSet rs, int rownum) throws SQLException {
        SystemUser userDto = new SystemUser();
        userDto.setId(rs.getLong("ID"));
        userDto.setUserName(rs.getString("USERNAME"));
        userDto.setRealName(rs.getString("REALNAME"));
        userDto.setRemark(rs.getString("REMARK"));
        userDto.setBindingEmail(rs.getString("BINDINGEMAIL"));
        userDto.setPassword(rs.getString("PASSWORD"));
        userDto.setCreateTime(rs.getTimestamp("CREATETIME"));
        userDto.setUserType(CmsUserType.get(rs.getInt("USERTYPE")));
        userDto.setIsFreeze(YesNoType.get(rs.getInt("ISFREEZE")));
        userDto.setIsDeleted(YesNoType.get(rs.getInt("ISDELETED")));
        userDto.setType(rs.getInt("USERTYPE"));
        return userDto;
    }
}
