/*
 * @(#)CatalogDaoImpl.java    Created on 2017年7月5日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.wangtu.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.wangtu.CatalogDao;
import net.zdsoft.chnmaster.entity.wangtu.Catalog;
import net.zdsoft.chnmaster.entity.wangtu.mapper.CatalogMapper;
import net.zdsoft.common.dao.BaseDaoImpl;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月5日 上午11:39:09 $
 */
@SuppressWarnings("unchecked")
@Repository("catalogDao")
public class CatalogDaoImpl extends BaseDaoImpl implements CatalogDao {

    @Override
    public int addCatalog(String cname) {
        String sql = "INSERT INTO T_CATALOG (CATANAME,IS_DELETE)VALUES(?,0)";
        return executeUpdate(sql, new Object[] { cname });
    }

    @Override
    public int deleteCatalog(long id) {
        String sql = "UPDATE T_CATALOG SET IS_DELETE=1 WHERE ID=? ";
        return executeUpdate(sql, new Object[] { id });
    }

    @Override
    public int updateCatalog(String cname, long id) {
        String sql = "UPDATE T_CATALOG SET CATANAME=? WHERE ID=?";
        System.out.println(sql);
        return executeUpdate(sql, new Object[] { cname, id });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Catalog> listCatalog() {
        String sql = "select * from t_catalog where is_delete = 0 ";
        return find(sql, null, CatalogMapper.instance());
    }

}
