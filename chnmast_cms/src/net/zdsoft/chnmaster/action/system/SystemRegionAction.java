/*
 * @(#)SystemRegionAction.java    Created on 2015-4-16
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsPageAction;
import net.zdsoft.chnmaster.service.SystemRegionService;
import net.zdsoft.common.entity.system.SystemRegion;

/**
 * 获取地区信息action
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015-4-16 下午3:10:12 $
 */
@Scope("prototype")
@Controller
public class SystemRegionAction extends CmsPageAction {
    private static final long serialVersionUID = -5175833070006458814L;
    private static final String PROVINCE = "province";
    private static final String CITY = "city";
    private static final String COUNTY = "county";
    private List<SystemRegion> regions;
    private String regionType;
    private String regionCode;

    @Resource
    private SystemRegionService systemRegionService;

    /**
     * 根据regionType，获取相应的地区列表，用于异步调用
     *
     * @return
     */
    public String listRegions() {
        if (StringUtils.isNotEmpty(regionType)) {
            if (PROVINCE.equals(regionType)) {
                regions = systemRegionService.listProvinces();// 省
            }
            if (CITY.equals(regionType)) {
                regions = systemRegionService.listCitys(regionCode);// 市
            }
            if (COUNTY.equals(regionType)) {
                regions = systemRegionService.listCountys(regionCode);// 县
            }
        }
        if (regions != null) {
            printJson(regions);
        }
        else {
            printJson(null);
        }
        return null;
    }

    public List<SystemRegion> getRegions() {
        return regions;
    }

    public void setRegionType(String regionType) {
        this.regionType = regionType;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

}
