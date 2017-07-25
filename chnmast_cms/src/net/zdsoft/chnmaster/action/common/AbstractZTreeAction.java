/* 
 * @(#)AbstractTreeAction.java    Created on 2015-3-19
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.common;

import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.chnmaster.entity.common.Node;
import net.zdsoft.keel.util.Validators;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015-3-19 下午4:12:27 $
 */
public abstract class AbstractZTreeAction<T> extends CmsPageAction {

    private static final long serialVersionUID = 757530532688305103L;

    public abstract List<T> getData();

    public abstract Node toNode(T t);

    public List<Node> getNodes() {
        List<T> data = getData();
        // List<Node> nodes = new ArrayList<Node>();
        List<Node> nodes = new LinkedList<Node>();
        if (data != null) {
            for (T t : data) {
                nodes.add(toNode(t));
            }
        }
        return nodes;
    }

    public void respone2json() throws Exception {
        List<Node> node = getNodes();
        if (Validators.isEmpty(node)) {
            response.getWriter().print("");
        }
        else {
            // 文档语法不正确
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            
            String nodes = JSONObject.toJSONString(node).toString();
            response.getWriter().print(nodes);
        }
    }

}
