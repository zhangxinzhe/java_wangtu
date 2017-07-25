/*
 * @(#)SupportAction.java    Created on 2014-12-10
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.index;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.service.SystemVersionService;
import net.zdsoft.common.action.BaseAction;
import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.keel.util.DateUtils;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2014-12-10 下午1:51:07 $
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class SupportAction extends BaseAction {
    protected Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private SystemVersionService systemVersionService;

    /*
     * 运维监控首页
     */
    @Override
    public String execute() {
        String versionNo = systemVersionService.getMaxSystemVersionNo();
        String jarVersionNo = "";
        String serverDate = DateUtils.date2String(new Date());
        StringBuffer sb = new StringBuffer();
        sb.append("版本号：" + (versionNo == null ? "" : versionNo) + "<br>");
        sb.append("程序包版本：" + (jarVersionNo == null ? "" : jarVersionNo) + "<br>");
        sb.append("服务器时间：" + (serverDate == null ? "" : serverDate) + "<br>");

        print(sb.toString());

        return null;
    }

    @Override
    public BaseUser getUser() {
        // TODO Auto-generated method stub
        return null;
    }
}
