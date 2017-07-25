package net.zdsoft.common.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.CollectionUtils;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.exoperate.ApplicationContextHolder;
import net.zdsoft.common.resource.SqlResources;
import net.zdsoft.common.template.TemplateManager;
import net.zdsoft.common.template.ValueContext;
import net.zdsoft.common.template.ValueContextFactory;
import net.zdsoft.common.utils.WarningMailUtil;
import net.zdsoft.keel.jdbc.MapRowMapper;
import net.zdsoft.keel.jdbc.MultiRowMapper;
import net.zdsoft.keel.util.DateUtils;
import net.zdsoft.keel.util.Pagination;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class AbstractDao {
    protected static final Logger log = LoggerFactory.getLogger(AbstractDao.class);

    private static final long TIME_LIMIT = 3000L;

    // order by的正则表达式
    private static final String ORDER_REGEX = "(order|ORDER)\\s+(by|BY)\\s+(\\w+[.|_|'|,|(|)|:|\\w*]*(\\s+[DESC|desc|ASC|asc]*)*)(\\s*,\\s*\\w+[.|_|'|,|(|)|:|\\w*]*(\\s+[DESC|desc|ASC|asc]*)*)*\\s*";
    private static final String BLANK_CHAR = " ";

    private final Map<String, String> timeoutSqls = new HashMap<String, String>();

    private static final String generate_primary_key = "system.generate_primary_key";

    /**
     * 获取sql
     *
     * @param key
     * @return
     */
    protected static final String getSql(String key) {
        return SqlResources.getValue(key);
    }

    /**
     * 获取主键
     *
     * @param tableName
     * @return
     */
    public long generatePrimaryKey(String tableName) {
        if (StringUtils.isEmpty(tableName)) {
            tableName = "";
        }
        return findForLong(getSql(generate_primary_key), new Object[] { tableName.toUpperCase() });
    }

    public List<Map> findForPage(String sql, Object[] params, PageDto pageDto) {
        if (params == null) {
            return queryByPage(sql, null, null, null, pageDto);
        }

        return queryByPage(sql, params, null, null, pageDto);
    }

    public List<?> findForPage(String sql, Object[] params, int startIndex, int rowNum) {
        List<?> results;
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        results = getJdbcTemplate().queryForList(getPageQuerySql(sql, startIndex, rowNum), params);

        checker.end();
        return results;
    }

    public List<?> findForPage(String sql, Object[] params, RowMapper rowMapper, int startIndex, int rowNum) {
        List<?> results;
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        results = getJdbcTemplate().query(getPageQuerySql(sql, startIndex, rowNum), params, rowMapper);

        checker.end();

        return results;
    }

    public List<?> findForPage(String sql, Object[] params, int[] argTypes, int startIndex, int rowNum) {
        List<?> results;
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        results = getJdbcTemplate().queryForList(getPageQuerySql(sql, startIndex, rowNum), params, argTypes);

        checker.end();

        return results;
    }

    public List<?> findForPage(String sql, Object[] params, int[] argTypes, RowMapper rowMapper, int startIndex,
            int rowNum) {
        List<?> results;
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        results = getJdbcTemplate().query(getPageQuerySql(sql, startIndex, rowNum), params, argTypes, rowMapper);

        checker.end();

        return results;
    }

    public List<?> find(String sql, Object[] params) {
        List<?> results;
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        results = getJdbcTemplate().queryForList(sql, params);

        checker.end();

        return results;
    }

    public Object findForObject(String sql, Object[] params, RowMapper mapper) {
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        List results = getJdbcTemplate().query(sql, params, mapper);

        checker.end();

        if (CollectionUtils.isEmpty(results)) {
            return null;
        }

        return results.get(0);
    }

    public Object findForObject(String sql, Object[] params, int[] argTypes, RowMapper rowMapper) {
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        List results = getJdbcTemplate().query(sql, params, argTypes, rowMapper);

        checker.end();

        if (CollectionUtils.isEmpty(results)) {
            return null;
        }

        return results.get(0);
    }

    public int executeUpdate(String sql, Object[] params) {
        int result;
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        result = getJdbcTemplate().update(sql, params);

        checker.end();

        return result;
    }

    public int executeUpdate(String sql, Object[] params, int[] argTypes) {
        int result;
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        result = getJdbcTemplate().update(sql, params, argTypes);

        checker.end();

        return result;
    }

    public List<?> find(String sql, Object[] params, int[] argTypes) {
        List<?> results;
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        results = getJdbcTemplate().queryForList(sql, params, argTypes);

        checker.end();

        return results;
    }

    public List find(String sql, Object[] params, RowMapper rowMapper) {
        List results;
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        results = getJdbcTemplate().query(sql, params, rowMapper);

        checker.end();

        return results;
    }

    public List find(String sql, Object[] params, int[] argTypes, RowMapper rowMapper) {
        List results;
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        results = getJdbcTemplate().query(sql, params, argTypes, rowMapper);

        checker.end();

        return results;
    }

    public List findForPage(String sql, Object[] params, RowMapper rowMapper, PageDto pageDto) {
        if (params == null) {
            return queryByPage(sql, null, null, rowMapper, pageDto);
        }

        return queryByPage(sql, params, getUnkownArgTypes(params.length), rowMapper, pageDto);
    }

    public List<Map> findForPage(String sql, Object[] params, int[] argTypes, PageDto pageDto) {
        return queryByPage(sql, params, argTypes, null, pageDto);
    }

    public List findForPage(String sql, Object[] params, int[] argTypes, RowMapper rowMapper, PageDto pageDto) {
        return queryByPage(sql, params, argTypes, rowMapper, pageDto);
    }

    public int findForInt(String sql, Object[] params) {
        int result;
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        result = getJdbcTemplate().queryForInt(sql, params);

        checker.end();

        return result;
    }

    public int findForInt(String sql, Object[] params, int[] argTypes) {
        int result;
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        result = getJdbcTemplate().queryForInt(sql, params, argTypes);

        checker.end();

        return result;
    }

    public long findForLong(String sql, Object[] params) {
        long result;
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        result = getJdbcTemplate().queryForLong(sql, params);

        checker.end();

        return result;
    }

    public long findForLong(String sql, Object[] params, int[] argTypes) {
        long result;
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        result = getJdbcTemplate().queryForLong(sql, params, argTypes);

        checker.end();

        return result;
    }

    public <K, V> Map<K, V> findForMap(String sql, MapRowMapper<K, V> mapRowMapper) {
        Map<K, V> result;
        TimeChecker checker = new TimeChecker(sql, new Object[] {});
        checker.start();

        result = getJdbcBaseDao().queryForMap(sql, mapRowMapper);

        checker.end();
        return result;
    }

    public <K, V> Map<K, V> findForMap(String sql, Object[] args, int[] argTypes, MapRowMapper<K, V> mapRowMapper) {
        Map<K, V> result;
        TimeChecker checker = new TimeChecker(sql, new Object[] {});
        checker.start();

        result = getJdbcBaseDao().queryForMap(sql, args, argTypes, mapRowMapper);

        checker.end();
        return result;
    }

    public <K, V> Map<K, V> findForMap(final String sql, final Object[] args, final int[] argTypes,
            final MapRowMapper<K, V> mapRowMapper, final Pagination page) {
        Map<K, V> result;
        TimeChecker checker = new TimeChecker(sql, new Object[] {});
        checker.start();

        result = getJdbcBaseDao().queryForMap(sql, args, argTypes, mapRowMapper, page);
        checker.end();
        return result;
    }

    public <K, V> Map<K, V> findForMap(String sql, Object[] args, MapRowMapper<K, V> mapRowMapper) {
        Map<K, V> result;
        TimeChecker checker = new TimeChecker(sql, new Object[] {});
        checker.start();

        result = getJdbcBaseDao().queryForMap(sql, args, mapRowMapper);

        checker.end();
        return result;
    }

    public <K, V> Map<K, V> findForInSQL(String prefix, Object[] prefixArgs, Object[] inArgs,
            MapRowMapper<K, V> mapRowMapper) {
        Map<K, V> result;
        TimeChecker checker = new TimeChecker(prefix, new Object[] {});
        checker.start();

        result = getJdbcBaseDao().queryForInSQL(prefix, prefixArgs, inArgs, mapRowMapper);

        checker.end();
        return result;
    }

    public <K, V> Map<K, V> findForInSQL(String prefix, Object[] prefixArgs, Object[] inArgs,
            MapRowMapper<K, V> mapRowMapper, String postfix) {
        Map<K, V> result;
        TimeChecker checker = new TimeChecker(prefix + " " + postfix, new Object[] {});
        checker.start();

        result = getJdbcBaseDao().queryForInSQL(prefix, prefixArgs, inArgs, mapRowMapper, postfix);

        checker.end();
        return result;
    }

    public <T> List<T> findForInSQL(String prefix, Object[] prefixArgs, Object[] inArgs,
            MultiRowMapper<T> multiRowMapper) {
        List<T> results;
        TimeChecker checker = new TimeChecker(prefix, new Object[] {});
        checker.start();

        results = getJdbcBaseDao().queryForInSQL(prefix, prefixArgs, inArgs, multiRowMapper);

        checker.end();
        return results;
    }

    public <T> List<T> findForInSQL(String prefix, Object[] prefixArgs, Object[] inArgs,
            MultiRowMapper<T> multiRowMapper, String postfix) {
        List<T> results;
        TimeChecker checker = new TimeChecker(prefix + " " + postfix, new Object[] {});
        checker.start();

        results = getJdbcBaseDao().queryForInSQL(prefix, prefixArgs, inArgs, multiRowMapper, postfix);

        checker.end();
        return results;
    }

    public int executeUpdateForInSQL(String prefix, Object[] prefixArgs, Object[] inArgs) {
        int result;
        TimeChecker checker = new TimeChecker(prefix, new Object[] {});
        checker.start();

        result = getJdbcBaseDao().updateForInSQL(prefix, prefixArgs, inArgs);

        checker.end();
        return result;
    }

    public int executeUpdateForInSQL(String prefix, Object[] prefixArgs, Object[] inArgs, String postfix) {
        int result;
        TimeChecker checker = new TimeChecker(prefix + " " + postfix, new Object[] {});
        checker.start();

        result = getJdbcBaseDao().updateForInSQL(prefix, prefixArgs, inArgs, postfix);

        checker.end();
        return result;
    }

    public int[] batchUpdate(String sql, final BatchPreparedStatementSetter pss) {
        int[] results;
        TimeChecker checker = new TimeChecker(sql, new Object[] {});
        checker.start();

        results = getJdbcTemplate().batchUpdate(sql, pss);

        checker.end();
        return results;
    }

    /**
     * 执行存储过程(有返回值,输入、输出参数都为 String 型)
     *
     * @param sp_name
     *            String 存储过程名称
     * @param sp_param
     *            Vector 入参
     * @param ret_num
     *            int 返回的参数个数 *
     * @return retV List<String> 正常:返回List;出错:抛出异常
     */
    public List<String> executeProcedure(final String sp_name, final List<String> sp_param, final int ret_num) {

        Object obj = getJdbcTemplate().execute(new ConnectionCallback() {
            @Override
            public Object doInConnection(Connection con) throws SQLException, DataAccessException {

                int i_len = 0;
                String s_parm_str = "";
                List<String> retV = new ArrayList<String>();
                CallableStatement callableSTMT = null;
                i_len = sp_param.size();// 入参个数
                try {
                    for (int i = 0; i < (i_len + ret_num); i++) {
                        s_parm_str += "?,";
                    }

                    if (s_parm_str.length() > 0) {
                        // 去掉最后的','号
                        s_parm_str = s_parm_str.substring(0, s_parm_str.length() - 1);
                    }

                    callableSTMT = con.prepareCall("{call " + sp_name + "(" + s_parm_str + ")}");

                    callableSTMT.clearParameters();
                    // 设置入参
                    for (int i = 0; i < i_len; i++) {
                        callableSTMT.setObject(i + 1, sp_param.get(i));
                    }

                    // 设置出参
                    for (int i = 1; i <= ret_num; i++) {
                        callableSTMT.registerOutParameter(i + i_len, Types.VARCHAR);
                    }
                    // 执行
                    callableSTMT.execute();
                    for (int i = 1; i <= ret_num; i++) {
                        retV.add(callableSTMT.getString(i + i_len));
                    }

                }
                catch (SQLException e1) {
                    throw e1;

                }
                catch (DataAccessException e2) {
                    throw e2;
                }
                finally {
                    try {
                        if (callableSTMT != null) {
                            callableSTMT.close();
                            callableSTMT = null;
                        }
                    }
                    catch (SQLException e3) {
                        throw e3;

                    }
                    catch (DataAccessException e4) {
                        throw e4;
                    }
                }
                return retV;
            }
        });

        return (List<String>) obj;
    }

    class TimeChecker {
        long start;
        long timeUsed;
        String sql;
        Object[] args;

        public TimeChecker(String sql, Object[] args) {
            this.sql = sql;
            this.args = args;
        }

        public void start() {
            start = System.currentTimeMillis();
        }

        public void end() {
            // 如果该sql已经超时过了，就不再发超时邮件
            if (timeoutSqls.get(sql) != null) {
                return;
            }

            timeUsed = System.currentTimeMillis() - start;
            if (timeUsed > TIME_LIMIT) {
                timeoutSqls.put(sql, sql);

                printSqlWarning();
                sendSqlWarningEmail();
            }
        }

        /**
         * 输出执行sql超长的语句
         */
        private void printSqlWarning() {
            log.warn("执行sql时间超长警报({}ms)：{}", timeUsed, sql);
        }

        /**
         * 发送sql超长的警报邮件
         *
         * @param sql
         * @param args
         */
        private void sendSqlWarningEmail() {
            if (ApplicationContextHolder.getApplicationContext() == null) {
                return;
            }
            if (args == null) {
                args = new Object[0];
            }

            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg instanceof Date) {
                    args[i] = DateUtils.date2String(((Date) arg), "yyyy-MM-dd HH:mm:ss");
                }
                else if (arg instanceof Boolean && arg != null) {
                    args[i] = ((Boolean) arg).toString();
                }
                else if (arg instanceof Long) {
                    args[i] = ((Long) arg).toString();
                }
                else if (arg instanceof Integer) {
                    args[i] = ((Integer) arg).toString();
                }
            }

            try {
                StackTraceElement[] temp = Thread.currentThread().getStackTrace();
                StringBuffer methodPath = new StringBuffer();
                int length = 0;
                for (StackTraceElement item : temp) {
                    String className = item.getClassName();
                    if (className.startsWith("net.zdsoft")
                            && (className.indexOf(".impl.") > 0 || className.indexOf(".action.") > 0)) {
                        methodPath.append("<br/>类名：" + className + "；方法名：" + item.getMethodName() + "["
                                + item.getLineNumber() + "]");
                        length++;
                    }
                    if (length == 5) {
                        break;
                    }
                }

                ValueContext ctx = ValueContextFactory.instance().getValueContext();
                String domain = NetstudyConfig.getParam(BaseConstants.DOMAIN_HOME);
                ctx.put("domain", domain);
                ctx.put("timeLimit", TIME_LIMIT);
                ctx.put("sql", sql);
                ctx.put("args", args);
                ctx.put("timeUsed", timeUsed);
                ctx.put("methodPath", methodPath);
                String content = TemplateManager.instance().build(ctx, "sqlWarning.ftl");
                WarningMailUtil.instance().send("sql超时", content);
            }
            catch (Exception e) {
                log.error("发送sql超时警报邮件失败，检查file中是否存在sqlWarning.ftl文件");
            }
        }
    }

    /**
     * 根据原sql得到具有分页功能的sql
     *
     * @param sql
     * @return
     */
    protected String getPageQuerySql(String sql, int startIndex, int maxRows) {
        StringBuilder buffer = new StringBuilder();
        // mysql分页
        buffer.append(sql);
        buffer.append(" LIMIT ");
        buffer.append(startIndex - 1);
        buffer.append(",");
        buffer.append(maxRows);
        // oracle分页
        // buffer.append("select * from (select t.*,rownum num from ( ");
        // buffer.append(sql);
        // buffer.append(" ) t where rownum<=").append(startIndex + maxRows - 1);
        // buffer.append(") where num>=").append(startIndex);

        return buffer.toString();
    }

    /**
     * 根据原sql得到查找总数
     *
     * @param sql
     * @param params
     * @param argTypes
     * @return
     */
    private int getCountQuerySql(String sql, Object[] params, int[] argTypes) {
        StringBuilder buffer = new StringBuilder();
        if (sql.toUpperCase().indexOf(" GROUP ") > -1 || sql.toUpperCase().indexOf(" UNION ") > -1) {
            buffer.append("SELECT COUNT(1) FROM (");
            buffer.append(sql.replaceAll(ORDER_REGEX, BLANK_CHAR).toUpperCase());
            buffer.append(") as page_table_count");
        }
        else {
            int indexFrom = sql.toUpperCase().indexOf(" FROM ");
            String subSql = sql.substring(0, indexFrom + 6).toUpperCase().trim();
            int selectNum = 1;
            // 获取当前From的正确位置
            while (StringUtils.splitByWholeSeparator(subSql, "SELECT ").length > selectNum) {
                subSql = sql.substring(indexFrom + 6).toUpperCase();
                indexFrom += subSql.toUpperCase().indexOf(" FROM ") + 6;
                subSql = sql.substring(0, indexFrom + 6).toUpperCase().trim();
                selectNum++;
            }

            // 判断select中是否存在 ? 存在 则过滤相同数量的参数
            String selectSql = sql.substring(0, indexFrom);
            if (selectSql.indexOf("?") > -1) {
                int markNum = StringUtils.split(selectSql, "?").length - 1;
                params = Arrays.copyOfRange(params, markNum, params.length);
                if (argTypes != null) {
                    argTypes = Arrays.copyOfRange(argTypes, markNum, argTypes.length);
                }
            }

            buffer.append("SELECT COUNT(1) ");
            buffer.append(sql.replaceAll(ORDER_REGEX, BLANK_CHAR).substring(indexFrom));
        }

        return findForInt(buffer.toString(), params, argTypes);
    }

    /**
     * 分页查询
     * <li>如果rowMapper为null,则按照 <code>find(String sql, Object[] params, int[] argTypes)</code>查询</li>
     * <li>如果rowMapper不为null,则按照 <code>find(String sql, Object[] params, int[] argTypes,RowMapper rowMapper)</code> 查询
     * </li>
     *
     * @param sql
     * @param params
     * @param argTypes
     * @param rowMapper
     * @param pageDto
     * @return
     * @see #find(String, Object[], int[])
     * @see #find(String, Object[], int[], RowMapper)
     */
    private List queryByPage(String sql, Object[] params, int[] argTypes, RowMapper rowMapper, PageDto pageDto) {
        TimeChecker checker = new TimeChecker(sql, params);
        checker.start();

        // 得到查询的总记录数
        // pageDto.setTotalNum(findForInt(getCountQuerySql(sql), params, argTypes));
        pageDto.setTotalNum(getCountQuerySql(sql, params, argTypes));

        // 得到新的分页查询语句
        String pageSql = getPageQuerySql(sql, pageDto.getStartIndex(), pageDto.getRowNum());

        List results = null;

        if (rowMapper != null) {
            if (argTypes == null) {
                results = find(pageSql, params, rowMapper);
            }
            else {
                results = find(pageSql, params, argTypes, rowMapper);
            }
        }
        else if (argTypes == null) {
            results = find(pageSql, params);
        }
        else {
            results = find(pageSql, params, argTypes);
        }

        // 设置本次查询查到的记录行数
        if (CollectionUtils.isEmpty(results)) {
            pageDto.setSelectRows(0);
        }
        else {
            pageDto.setSelectRows(results.size());
        }

        checker.end();

        return results;
    }

    private int[] getUnkownArgTypes(int length) {
        int[] argTypes = new int[length];

        for (int i = 0; i < length; ++i) {
            argTypes[i] = JdbcUtils.TYPE_UNKNOWN;
        }

        return argTypes;
    }

    /**
     * 返回主键id
     *
     * @param sql
     * @param args
     * @return
     */
    public long saveEntityForKey(final String sql, final Object[] args) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                if (args != null) {
                    for (int i = 0; i < args.length; ++i) {
                        Object argValue = args[i];
                        if (argValue instanceof SqlParameterValue) {
                            SqlParameterValue paramValue = (SqlParameterValue) argValue;
                            StatementCreatorUtils.setParameterValue(ps, i + 1, paramValue, paramValue.getValue());
                        }
                        else {
                            StatementCreatorUtils.setParameterValue(ps, i + 1, JdbcUtils.TYPE_UNKNOWN, argValue);
                        }
                    }
                }
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    /**
     * 返回影响行数
     *
     * @param sql
     * @param args
     * @param entity
     * @return
     */
    public int saveEntity(final String sql, final Object[] args, BaseEntity entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int acount = getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                if (args != null) {
                    for (int i = 0; i < args.length; ++i) {
                        Object argValue = args[i];
                        if (argValue instanceof SqlParameterValue) {
                            SqlParameterValue paramValue = (SqlParameterValue) argValue;
                            StatementCreatorUtils.setParameterValue(ps, i + 1, paramValue, paramValue.getValue());
                        }
                        else {
                            StatementCreatorUtils.setParameterValue(ps, i + 1, JdbcUtils.TYPE_UNKNOWN, argValue);
                        }
                    }
                }
                return ps;
            }
        }, keyHolder);
        entity.setId(keyHolder.getKey().longValue());
        return acount;
    }

    public abstract JdbcTemplate getJdbcTemplate();

    public abstract JdbcBaseDao getJdbcBaseDao();

}
