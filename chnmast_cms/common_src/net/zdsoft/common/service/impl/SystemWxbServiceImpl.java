/*
 * @(#)SystemWxbServiceImpl.java    Created on 2015-5-22
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import net.zdsoft.common.cache.BaseCacheServiceImpl;
import net.zdsoft.common.cache.CacheCall.CacheObjectParam;
import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.constant.DataKeyConstants;
import net.zdsoft.common.constant.DataTypeConstants;
import net.zdsoft.common.filesystem.util.FileSystemUtil;
import net.zdsoft.common.service.SystemWxbService;
import net.zdsoft.common.utils.IniReader;

/**
 *
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015-5-22 下午3:39:14 $
 */
@Service("systemWxbService")
public class SystemWxbServiceImpl extends BaseCacheServiceImpl implements SystemWxbService {
    private final String WXB_UPDATE_DIR = "update/";

    @Override
    public String getWxbPcClientUrl() {
        return getObjectFromCache(new CacheObjectParam<String>() {

            @Override
            public String fetchKey() {
                return DataKeyConstants.GET_WXB_PC_CLIENT_URL;
            }

            @Override
            public String getDataType() {
                return DataTypeConstants.SYSTEM_WXB;
            }

            @Override
            public Long getAgencyId() {
                return 0l;
            }

            @Override
            public String fetchObject() {
                try {
                    return getWxbClientUrl(BaseConstants.WXB_PC_CLIENT_FILE_NAME, BaseConstants.WXB_PC_CLIENT_KEY);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });

    }

    @Override
    public String getWxbAndroidClientUrl() {
        return getObjectFromCache(new CacheObjectParam<String>() {

            @Override
            public String fetchKey() {
                return DataKeyConstants.GET_WXB_ANDROID_CLIENT_URL;
            }

            @Override
            public String getDataType() {
                return DataTypeConstants.SYSTEM_WXB;
            }

            @Override
            public Long getAgencyId() {
                return 0l;
            }

            @Override
            public String fetchObject() {
                try {
                    return getWxbClientUrl(BaseConstants.WXB_ANDROID_CLIENT_FILE_NAME,
                            BaseConstants.WXB_ANDROID_CLIENT_KEY);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });

    }

    @Override
    public String getWxbClientUpdateUrl() {
        return getWxbUpdateUrl();
    }

    /**
     * 获取无限宝更新目录
     *
     * @return
     */
    private String getWxbUpdateUrl() {
        return getObjectFromCache(new CacheObjectParam<String>() {

            @Override
            public String fetchKey() {
                return DataKeyConstants.GET_WXB_CLIENT_UPDATE_URL;
            }

            @Override
            public String getDataType() {
                return DataTypeConstants.SYSTEM_WXB;
            }

            @Override
            public Long getAgencyId() {
                return 0l;
            }

            @Override
            public String fetchObject() {
                try {
                    return getWxbClientUrl(BaseConstants.WXB_PC_CLIENT_FILE_NAME, BaseConstants.WXB_CLIENT_UPDATE_KEY);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

    /**
     * 获取无限宝客户端路径
     *
     * @param readFileName
     * @param key
     * @param useBeat
     * @return
     * @throws Exception
     */
    private String getWxbClientUrl(String readFileName, String key) throws Exception {
        // 无限宝文件目录
        String clientDirectory = null;
        // 无限宝使用通用版
        clientDirectory = NetstudyConfig.getParam(BaseConstants.WXB_CLIENT_DIRECTORY);
        // 过滤前后空格，并且判断目录是否已/结尾
        clientDirectory = clientDirectory.trim();
        if (!clientDirectory.endsWith(BaseConstants.SEPERATOR)) {
            clientDirectory = clientDirectory + BaseConstants.SEPERATOR;
        }

        // 判断无限宝是否是新版本文件
        String fileName = null;
        String readFileUrl = null;
        // 获取无限宝Android配置目录，先获取更新目录
        if (BaseConstants.WXB_ANDROID_CLIENT_FILE_NAME.equals(readFileName)) {
            String updateUrl = getWxbUpdateUrl();
            // 过滤前后空格，并且判断目录是否已/结尾
            updateUrl = updateUrl.trim();
            if (!updateUrl.endsWith(BaseConstants.SEPERATOR)) {
                updateUrl = updateUrl + BaseConstants.SEPERATOR;
            }
            readFileUrl = updateUrl + NetstudyConfig.getParam(readFileName);
        }
        else {
            readFileUrl = clientDirectory + NetstudyConfig.getParam(readFileName);
        }
        // 判断文件是否存在
        if (!FileSystemUtil.fileExists(readFileUrl)) {
            // 如果获取无限宝安卓客户端，则直接返回
            if (BaseConstants.WXB_ANDROID_CLIENT_FILE_NAME.equals(readFileName)) {
                return fileName;
            }
            // 如果是老版更新目录，则直接返回
            if (BaseConstants.WXB_CLIENT_UPDATE_KEY.equals(key)) {
                return clientDirectory + WXB_UPDATE_DIR;
            }

            // 判断update文件是否存在
            readFileUrl = clientDirectory + WXB_UPDATE_DIR + NetstudyConfig.getParam(readFileName);
            if (!FileSystemUtil.fileExists(readFileUrl)) {
                return fileName;
            }
        }
        // 从配置文件中获取信息
        BufferedReader reader = new BufferedReader(new InputStreamReader(FileSystemUtil.getFileAsStream(readFileUrl)));
        key = NetstudyConfig.getParam(key);
        // 读取配置信息
        fileName = IniReader.read(reader, key);
        return clientDirectory + fileName;
    }
}
