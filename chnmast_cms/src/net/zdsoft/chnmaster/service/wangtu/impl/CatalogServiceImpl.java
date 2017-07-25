/*
 * @(#)CatalogServiceImpl.java    Created on 2017年7月5日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.wangtu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.wangtu.CatalogDao;
import net.zdsoft.chnmaster.entity.wangtu.Catalog;
import net.zdsoft.chnmaster.service.wangtu.CatalogService;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月5日 上午11:38:23 $
 */
@Service("catalogService")
public class CatalogServiceImpl implements CatalogService {

    @Resource
    private CatalogDao catalogDao;

    @Override
    public int addCatalog(String cname) {
        return catalogDao.addCatalog(cname);
    }

    @Override
    public int deleteCatalog(long id) {
        return catalogDao.deleteCatalog(id);
    }

    @Override
    public int updateCatalog(String cname, long id) {
        return catalogDao.updateCatalog(cname, id);
    }

    @Override
    public List<Catalog> listCatalog() {
        return catalogDao.listCatalog();
    }

}
