package net.zdsoft.common.entity.user.mapper;import java.sql.ResultSet;import java.sql.SQLException;import org.springframework.jdbc.core.RowMapper;import net.zdsoft.common.entity.user.User;import net.zdsoft.common.enums.SexType;import net.zdsoft.common.enums.StatusEunm;import net.zdsoft.common.enums.UserRegTypeEnum;import net.zdsoft.common.enums.UserType;import net.zdsoft.common.enums.YesNoType;import net.zdsoft.keel.jdbc.MapRowMapper;/** * 学员导入临时表【t_import_student】 * * @author * */@SuppressWarnings("rawtypes")public class UserRowMapper implements RowMapper {    private static UserRowMapper rowMapper = new UserRowMapper();    public static UserNameRowMap userNameRowMap = new UserNameRowMap();    public static UserMapRowMap userMapRowMap = new UserMapRowMap();    public static UserRowMapper instance() {        return rowMapper;    }    static class UserMapRowMap implements MapRowMapper<Long, User> {        @Override        public Long mapRowKey(ResultSet rs, int i) throws SQLException {            return rs.getLong("id");        }        @Override        public User mapRowValue(ResultSet rs, int i) throws SQLException {            return rowMapper.mapRow(rs, i);        }    }    static class UserNameRowMap implements MapRowMapper<Long, String> {        @Override        public Long mapRowKey(ResultSet rs, int i) throws SQLException {            return rs.getLong("id");        }        @Override        public String mapRowValue(ResultSet rs, int i) throws SQLException {            return rs.getString("userName");        }    }    @Override    public User mapRow(ResultSet rs, int i) throws SQLException {        User user = new User();        user.setId(rs.getLong("ID"));        user.setUserName(rs.getString("USERNAME"));        user.setPassword(rs.getString("PASSWORD"));        user.setQq(rs.getString("QQ"));        user.setEmail(rs.getString("EMAIL"));        user.setPhone(rs.getString("PHONE"));        user.setRealName(rs.getString("REALNAME"));        user.setSex(SexType.get(rs.getInt("SEX")));        user.setSchoolId(rs.getLong("SCHOOL_ID"));        user.setSchoolName(rs.getString("SCHOOLNAME"));        user.setCreateTime(rs.getTimestamp("CREATETIME"));        user.setHomePage(rs.getString("HOMEPAGE"));        user.setTelephone(rs.getString("TELEPHONE"));        user.setUserType(UserType.get(rs.getInt("USER_TYPE")));        user.setRegisterType(UserRegTypeEnum.get(rs.getInt("REGISTER_TYPE")));        user.setIsCancel(StatusEunm.get(rs.getInt("IS_CANCEL")));        user.setIsMember(rs.getBoolean("IS_MEMBER"));        user.setGroupTypeId(rs.getLong("GROUP_TYPE_ID"));        user.setIsUnionMember(YesNoType.get(rs.getInt("IS_UNION_MEMBER")));        user.setIsTutor(YesNoType.get(rs.getInt("IS_TUTOR")));        user.setIsHifiMember(YesNoType.get(rs.getInt("IS_HIFI_MEMBER")));        user.setHifiMemberDate(rs.getTimestamp("HIFI_MEMBER_DATE"));        user.setAvatarFile(rs.getString("AVATAR_FILE"));        user.setAvatarOriginalFile(rs.getString("AVATAR_ORIGINAL_FILE"));        user.setFindpwdDate(rs.getString("FINDPWD_DATE"));        user.setFundpwdTimes(rs.getInt("FINDPWD_TIMES"));        user.setSpellName(rs.getString("SPELLNAME"));        user.setShortSpell(rs.getString("SHORTSPELL"));        user.setAgencyId(rs.getLong("AGENCY_ID"));        user.setRemark(rs.getString("REMARK"));        user.setBirthday(rs.getTimestamp("BIRTHDAY"));        user.setIndustry(rs.getString("industry"));        return user;    }}