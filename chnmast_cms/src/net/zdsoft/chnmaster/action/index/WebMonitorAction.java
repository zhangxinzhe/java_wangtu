/*
 * @(#)WebMoniterAction.java    Created on 2015-7-10
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsBaseAction;
import net.zdsoft.chnmaster.service.SystemControlService;
import net.zdsoft.chnmaster.service.monitor.MonitorCacheService;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015-7-10 上午10:43:26 $
 */
@Scope("prototype")
@Controller
public class WebMonitorAction extends CmsBaseAction {
    private static final long serialVersionUID = 1L;

    @Resource
    private MonitorCacheService monitorCacheService;
    @Resource
    private SystemControlService systemControlService;

    private Date dateTime;
    private String controlKey;
    private Map<String, String> controlMap;

    @Override
    public String execute() {
        return SUCCESS;
    }

    // 刷新所有缓存
    public String cleanAllCache() {
        monitorCacheService.cleanCache();
        print(1);
        return null;
    }

    public String systemControlView() {
        controlMap = systemControlService.getSystemControlItem();
        return SUCCESS;
    }

    public String removeSystemControlItem() {
        if ("all".equals(controlKey)) {
            systemControlService.removeAllSystemControl();
        }
        else {
            systemControlService.removeSystemControlItem(controlKey);
        }
        return SUCCESS;
    }

    private void writeResult(String result) {
        PrintWriter writer = null;
        try {
            response.setContentType("text/html,charset=UTF-8");
            writer = response.getWriter();
            writer.print("<script type=\"text/javascript\">");
            writer.print("alert(' " + result + "');");
            writer.print("</script>");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            writer.close();
        }
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setControlKey(String controlKey) {
        this.controlKey = controlKey;
    }

    public Map<String, String> getControlMap() {
        return controlMap;
    }

}
