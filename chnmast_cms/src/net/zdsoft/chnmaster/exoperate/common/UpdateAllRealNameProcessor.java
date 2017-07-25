/*
 * @(#)UpdateAllRealNameProcessor.java    Created on 2015年12月8日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.exoperate.common;

import java.util.Map;

import org.springframework.stereotype.Controller;

import net.zdsoft.common.exoperate.ExoperateProcessor;

/**
 * 异步修改所有realname冗余字段
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年12月8日 下午2:21:45 $
 */
@SuppressWarnings("rawtypes")
@Controller
public class UpdateAllRealNameProcessor implements ExoperateProcessor {

    @Override
    public void process(String operateType, Map values) {
        Long id = (Long) values.get("id");
        String newRealName = (String) values.get("newRealName");
        // orderReportService.updateRealNameByUserId(id, newRealName);//前台修改
        // orderReturnService.updateRealNameByUserId(id, newRealName);//前台修改
    }

}
