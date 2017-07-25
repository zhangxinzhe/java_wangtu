/*
 * @(#)SignUtils.java    Created on 2016年3月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.wechatpay.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;

public class SignUtils {
    /**
     * 获取签名算法
     *
     * @param map
     * @return
     */
    public static String getSign(Map<String, Object> map, boolean isApp) {
        ArrayList<String> list = new ArrayList<String>();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (!"sign".equals(key) && value != null) {
                if (value instanceof String) {
                    String item = (String) value;
                    if (StringUtils.isBlank(item)) {
                        continue;
                    }
                }
                list.add(key + "=" + value + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        if (isApp) {
            result += "key=" + NetstudyConfig.getParam(BaseConstants.WECHATPAY_APP_VERIFY_KEY);
        }
        else {
            result += "key=" + NetstudyConfig.getParam(BaseConstants.WECHATPAY_VERIFY_KEY);
        }

        result = MD5.MD5Encode(result).toUpperCase();
        return result;
    }
}
