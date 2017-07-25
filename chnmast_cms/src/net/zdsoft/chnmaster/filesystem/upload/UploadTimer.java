/*
 * @(#)upload.java    Created on 2015-3-21
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.filesystem.upload;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.chnmaster.filesystem.util.BlockNoteUtil;
import net.zdsoft.chnmaster.filesystem.util.UploadFileUtil;

/**
 * 上传定时器
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2015年11月17日 下午4:55:09 $
 */
public class UploadTimer {
    private static Logger log = LoggerFactory.getLogger(UploadTimer.class);
    private static Timer timer = null;
    private final static long clear_timer = 24 * 60 * 60 * 1000;// 24 * 60 * 60 * 1000l

    /**
     * 定时器清理过期的文件
     */
    public static void createClearTimer() {
        if (timer == null) {
            timer = new Timer(true);
            log.debug("过期临时文件清理启动");
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    String[] notes = BlockNoteUtil.getAllToken();
                    JSONObject jObject = null;
                    long now = new Date().getTime();
                    if (ArrayUtils.isEmpty(notes)) {
                        return;
                    }
                    for (String key : notes) {
                        try {
                            jObject = BlockNoteUtil.getNote(key);
                            if (jObject != null) {
                                // 过期
                                long expiration = jObject.getLongValue("expiration");
                                if (expiration < now) {
                                    log.debug("过期清理：{}", jObject.toString());
                                    String path = jObject.getString("path");
                                    // 删除文件夹和里面的内容
                                    UploadFileUtil.deleteFile(path);
                                    // 移除map里面的内容
                                    BlockNoteUtil.removeNote(key);
                                }
                            }
                        }
                        catch (Exception e) {
                            log.error(e.getMessage(), e);
                            log.debug("过期临时文件清理异常停止");
                        }
                    }
                }
                // 12点执行
                // }, new Date(new Date().getTime() / clear_timer * clear_timer), clear_timer);// 每天12点执行
            }, new Date(), clear_timer);// 立刻执行
        }
    }
}
