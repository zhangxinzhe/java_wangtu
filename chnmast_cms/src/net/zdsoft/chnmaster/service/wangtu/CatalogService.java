/*
 * @(#)CoatalogService.java    Created on 2017年7月5日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.wangtu;

import java.util.List;

import net.zdsoft.chnmaster.entity.wangtu.Catalog;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月5日 上午11:24:38 $
 */
public interface CatalogService {
    public int addCatalog(String cname);

    public int deleteCatalog(long id);

    public int updateCatalog(String cname, long id);

    public List<Catalog> listCatalog();
}
