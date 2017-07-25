/* 
 * @(#)JdbcBaseDao.java    Created on 2013-8-14
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.dao;

import java.util.List;
import java.util.Map;

import net.zdsoft.keel.jdbc.JdbcBasicDao;
import net.zdsoft.keel.jdbc.MapRowMapper;
import net.zdsoft.keel.jdbc.MultiRowMapper;
import net.zdsoft.keel.util.Pagination;

/**
 * 使用keel包中的特殊jdbc dao方法，如：queryForMap，queryForInSQL等
 * 
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2013-8-14 下午8:23:21 $
 */
public class JdbcBaseDao extends JdbcBasicDao {

    @Override
    public <K, V> Map<K, V> queryForInSQL(String prefix, Object[] prefixArgs, Object[] inArgs,
            MapRowMapper<K, V> mapRowMapper) {
        return super.queryForInSQL(prefix, prefixArgs, inArgs, mapRowMapper);
    }

    @Override
    public <K, V> Map<K, V> queryForInSQL(String prefix, Object[] prefixArgs, Object[] inArgs,
            MapRowMapper<K, V> mapRowMapper, String postfix) {
        return super.queryForInSQL(prefix, prefixArgs, inArgs, mapRowMapper, postfix);
    }

    @Override
    public <T> List<T> queryForInSQL(String prefix, Object[] prefixArgs, Object[] inArgs,
            MultiRowMapper<T> multiRowMapper) {
        return super.queryForInSQL(prefix, prefixArgs, inArgs, multiRowMapper);
    }

    @Override
    public <T> List<T> queryForInSQL(String prefix, Object[] prefixArgs, Object[] inArgs,
            MultiRowMapper<T> multiRowMapper, String postfix) {
        return super.queryForInSQL(prefix, prefixArgs, inArgs, multiRowMapper, postfix);
    }

    @Override
    public <K, V> Map<K, V> queryForMap(String sql, MapRowMapper<K, V> mapRowMapper) {
        return super.queryForMap(sql, mapRowMapper);
    }

    @Override
    public <K, V> Map<K, V> queryForMap(String sql, Object[] args, int[] argTypes, MapRowMapper<K, V> mapRowMapper) {
        return super.queryForMap(sql, args, argTypes, mapRowMapper);
    }

    @Override
    public <K, V> Map<K, V> queryForMap(String sql, Object[] args, int[] argTypes, MapRowMapper<K, V> mapRowMapper,
            Pagination page) {
        return super.queryForMap(sql, args, argTypes, mapRowMapper, page);
    }

    @Override
    public <K, V> Map<K, V> queryForMap(String sql, Object[] args, MapRowMapper<K, V> mapRowMapper) {
        return super.queryForMap(sql, args, mapRowMapper);
    }

    @Override
    public int updateForInSQL(String prefix, Object[] prefixArgs, Object[] inArgs) {
        return super.updateForInSQL(prefix, prefixArgs, inArgs);
    }

    @Override
    public int updateForInSQL(String prefix, Object[] prefixArgs, Object[] inArgs, String postfix) {
        return super.updateForInSQL(prefix, prefixArgs, inArgs, postfix);
    }

}
