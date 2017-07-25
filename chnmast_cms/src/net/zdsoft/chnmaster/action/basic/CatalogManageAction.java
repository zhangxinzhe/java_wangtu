/*
 * @(#)CatalogManageAction.java    Created on 2017年7月5日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.basic;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsPageAction;
import net.zdsoft.chnmaster.entity.wangtu.Catalog;
import net.zdsoft.chnmaster.service.wangtu.CatalogService;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月5日 上午11:47:20 $
 */
@Scope("prototype")
@Controller
public class CatalogManageAction extends CmsPageAction {

    private static final long serialVersionUID = 1L;
    private String cname;
    private List<Catalog> catalogList;

    private CatalogService catalogService;

    // 列表
    public String catalogManage() {
        catalogList = catalogService.listCatalog();
        return SUCCESS;
    }

    public String addCatalog() {
        if (StringUtils.isEmpty(cname)) {
            return SUCCESS;
        }
        catalogService.addCatalog(cname);
        catalogList = catalogService.listCatalog();
        return SUCCESS;
    }

    public String editCatalog() {
        if (StringUtils.isEmpty(cname)) {
            return SUCCESS;
        }
        catalogService.updateCatalog(cname, id);
        catalogList = catalogService.listCatalog();
        return SUCCESS;
    }

    public String deleteCatalog() {
        catalogService.deleteCatalog(id);
        catalogList = catalogService.listCatalog();
        return SUCCESS;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<Catalog> getCatalogList() {
        return catalogList;
    }

    public void setCatalogList(List<Catalog> catalogList) {
        this.catalogList = catalogList;
    }

    public CatalogService getCatalogService() {
        return catalogService;
    }

    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

}
