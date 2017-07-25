/*
 * @(#)UpdateAllCourseNameProcessor.java    Created on 2016年6月20日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.exoperate.common;

import java.util.Map;

import org.springframework.stereotype.Controller;

import net.zdsoft.common.exoperate.ExoperateProcessor;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年6月20日 上午11:45:01 $
 */
@SuppressWarnings("rawtypes")
@Controller
public class UpdateAllCourseNameProcessor implements ExoperateProcessor {

    @Override
    public void process(String operateType, Map values) {
        // Long wareId = (Long) values.get("wareId");
        // String newWareName = (String) values.get("newWareName");
        // CourseContentType wareType = (CourseContentType) values.get("wareType");
        // if (wareId > 0 && StringUtils.isNotBlank(newWareName) && wareType != null) {
        // orderDetailService.updateWareNameByWareIdAndWareType(newWareName, wareId, wareType);
        // }
    }

}
