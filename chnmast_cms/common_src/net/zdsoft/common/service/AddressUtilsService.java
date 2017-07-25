/* 
 * @(#)AddressUtilsService.java    Created on 2015年6月10日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.service;

import java.util.Map;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015年6月10日 下午6:23:42 $
 */
public interface AddressUtilsService {
    /**
     * 获取ip 所在地区address，行政区划fullcode，所属运营商isp
     * 
     * @param ip
     * @return
     * @throws Exception
     */
    public Map<String, String> getAddress(String ip) throws Exception;
}
