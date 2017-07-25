/*
 * @(#)upload.java    Created on 2015-3-21
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.filesystem.upload;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.chnmaster.filesystem.util.UploadFileUtil;

/**
 * 整块上传
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2015年11月17日 下午4:55:09 $
 */
public class TotalUpload {
    Logger log = LoggerFactory.getLogger(TotalUpload.class);

    /**
     * 上传整块
     *
     * @param policy
     * @param is
     * @return
     * @throws Exception
     */
    public JSONObject upload(JSONObject policy, InputStream is) throws Exception {
        JSONObject result = new JSONObject();
        String path = policy.getString("save-key");
        UploadFileUtil.saveFile(is, path);
        result.put("code", 200);
        result.put("path", path);
        return result;
    }
}
