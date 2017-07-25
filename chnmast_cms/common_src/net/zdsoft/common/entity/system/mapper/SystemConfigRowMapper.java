package net.zdsoft.common.entity.system.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.system.SystemConfig;
import net.zdsoft.keel.jdbc.MapRowMapper;

public class SystemConfigRowMapper {
    private static SystemConfigEntityRowMapper rowMapper = new SystemConfigEntityRowMapper();
    private static SystemConfigMapRowMapper systemConfigMapRowMapper = new SystemConfigMapRowMapper();

    public static SystemConfigEntityRowMapper instance() {
        return rowMapper;
    }

    public static SystemConfigMapRowMapper instSystemConfigMapRowMapper() {
        return systemConfigMapRowMapper;
    }

    public static SystemConfig setFields(ResultSet rs) throws SQLException {
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setCode(rs.getString("CODE"));
        systemConfig.setName(rs.getString("NAME"));
        systemConfig.setValue(rs.getString("VALUE"));
        systemConfig.setDescription(rs.getString("DESCRIPTION"));
        systemConfig.setCanView(rs.getBoolean("CAN_VIEW"));
        systemConfig.setCanEdit(rs.getBoolean("CAN_EDIT"));
        systemConfig.setValidate(rs.getString("VALIDATE"));
        return systemConfig;
    }

    /**
     * 映射整个对象
     * 
     * @author dongzk
     * @version $Revision: 1.0 $, $Date: 2013-8-14 下午5:55:09 $
     */
    static class SystemConfigEntityRowMapper implements RowMapper<SystemConfig> {
        @Override
        public SystemConfig mapRow(ResultSet rs, int i) throws SQLException {
            return setFields(rs);
        }
    }

    /**
     * 映射成一个Map<code, AgencyConfig>
     * 
     * @author dongzk
     * @version $Revision: 1.0 $, $Date: 2013-8-14 下午5:56:07 $
     */
    static class SystemConfigMapRowMapper implements MapRowMapper<String, SystemConfig> {
        @Override
        public String mapRowKey(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString("CODE");
        }

        @Override
        public SystemConfig mapRowValue(ResultSet rs, int rowNum) throws SQLException {
            return setFields(rs);
        }
    }

}
