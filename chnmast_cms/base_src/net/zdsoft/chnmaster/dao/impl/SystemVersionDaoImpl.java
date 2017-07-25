/* 
 * @(#)SystemVersionDaoImpl.java    Created on 2013-8-9
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.SystemVersionDao;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.entity.system.SystemVersion;
import net.zdsoft.common.entity.system.mapper.SystemVersionRowMapper;

/**
 * @author y
 * @version $Revision: 1.0 $, $Date: 2013-8-9 下午4:35:03 $
 */
@Repository("systemVersionDao")
public class SystemVersionDaoImpl extends BaseDaoImpl implements SystemVersionDao {
    private static final String get_max_system_version = "system.get_max_system_version";
    private static final String get_max_system_version_id = "system.get_max_system_version_id";
    private static final String get_all_system_version = "system.get_all_system_version";
    private static final String get_system_version_byids = "system.get_system_version_byids";
    private static final String add_system_version = "system.add_system_version";
    private static final String update_system_version = "system.update_system_version";
    private static final String get_cur_dbDate = "system.get_cur_dbDate";

    @Override
    public long getMaxSystemVersionId() {
        Long id = (Long) findForObject(getSql(get_max_system_version_id), new Object[] {},
                SystemVersionRowMapper.instanceVersionId());
        if (id != null) {
            return id.longValue();
        }
        else {
            return 0;
        }
    }

    @Override
    public SystemVersion getMaxSystemVersion() {
        return (SystemVersion) this.findForObject(getSql(get_max_system_version), new Object[] {},
                SystemVersionRowMapper.instanceVersion());
    }

    @Override
    public Map<Long, SystemVersion> getSystemVersionMap() {
        return findForMap(getSql(get_all_system_version), SystemVersionRowMapper.instanceVersionMapRowMapper());
    }

    @Override
    public List<SystemVersion> getSystemVersionByids(Long[] ids) {
        String prefixSql = getSql(get_system_version_byids);
        String postfixSql = " ORDER BY UPGRADEDATE DESC";

        List<SystemVersion> list = findForInSQL(prefixSql, null, ids,
                SystemVersionRowMapper.instanceVersionMultiRowMapper(), postfixSql);

        return list;
    }

    @Override
    public int saveSystemVersion(SystemVersion version) {
        return executeUpdate(getSql(add_system_version),
                new Object[] { version.getId(), version.getEdition(), version.getClient(), version.getDescription(),
                        version.getUpgradeDate(), version.getVersion(), version.getBuild(), version.getDescFile() });
    }

    @Override
    public int updateSystemVersion(SystemVersion version) {
        return executeUpdate(
                getSql(update_system_version),
                new Object[] { version.getEdition(), version.getClient(), version.getDescription(),
                        version.getUpgradeDate(), version.getVersion(), version.getBuild(), version.getDescFile(),
                        version.getId() });
    }

    @Override
    public Date getCurDbDate() {
        Date curDbDate = (Date) findForObject(getSql(get_cur_dbDate), new Object[] {},
                SystemVersionRowMapper.instanceCurDbDate());
        return curDbDate;
    }

}
