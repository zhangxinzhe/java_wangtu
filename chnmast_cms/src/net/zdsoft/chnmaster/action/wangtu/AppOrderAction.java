/*

 * @(#)AppOrderAction.java    Created on 2017年7月19日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.wangtu;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.MobilePageAction;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月19日 下午5:21:30 $
 */
@Scope("prototype")
@Controller
public class AppOrderAction extends MobilePageAction {

    private static final long serialVersionUID = 1L;

    public void createOrder() {
        printJsonMap(new HashMap<String, Object>());
        return;
    }
}
