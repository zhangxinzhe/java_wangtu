package net.zdsoft.common.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 单数据源时，连接单数据源所指向的数据库<br>
 * 多数据源时，连接主业务库netstudy<br>
 * 
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2015-3-3 下午6:41:04 $
 */
public class BaseDaoImpl extends AbstractDao implements BaseDao {

    @Resource(name = "jdbcTemplate")
    protected JdbcTemplate jdbcTemplate;

    @Resource(name = "jdbcBaseDao")
    private JdbcBaseDao jdbcBaseDao; // keel包中的net.zdsoft.keel.jdbc.JdbcBasicDao

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public JdbcBaseDao getJdbcBaseDao() {
        return jdbcBaseDao;
    }

}
