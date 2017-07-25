/*
 * @(#)InfoContentUtils.java    Created on 2016年1月14日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 富文本框编辑器content内容处理工具
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年1月14日 下午2:33:39 $
 */
public class UeditorUtils {

    private static Map<Character, Boolean> besideChar = new HashMap<Character, Boolean>();

    static {
        besideChar.put('·', true);
        besideChar.put('0', true);
        besideChar.put('1', true);
        besideChar.put('2', true);
        besideChar.put('3', true);
        besideChar.put('4', true);
        besideChar.put('5', true);
        besideChar.put('6', true);
        besideChar.put('7', true);
        besideChar.put('8', true);
        besideChar.put('9', true);
        besideChar.put('~', true);
        besideChar.put('!', true);
        besideChar.put('(', true);
        besideChar.put(')', true);
        besideChar.put('[', true);
        besideChar.put(']', true);
        besideChar.put('{', true);
        besideChar.put('}', true);
    }

    /**
     * 截取编辑器（带有html标签字符）文章中的连续lenth个中文字符串，如果没有超过，则返回最长段中文字符串
     *
     * @param content
     * @param lenth
     * @return
     */
    public static String getCutStrFromContent(String content, int lenth) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        List<String> cutContentContainer = new ArrayList<String>();
        boolean isInIgnore = true;// 是否忽略，如style中字体的中文
        boolean isInclude = false;// 中文括号中的字符忽略，保证中文括号的内容完整
        int maxLength = 0;// 标记最大长度（没有达到lenth长度的中文时才会被用到）
        int maxLengthIndex = 0;// 最大长度串索引
        int index = 0;
        StringBuffer temp = new StringBuffer();
        for (char c : content.toCharArray()) {
            boolean isChinese = CharUtils.isChinese(c);

            boolean besideBol = besideChar.get(c) == null ? false : besideChar.get(c);
            // "（）"括号中的内容不验证是否为中文字符
            if (c == '（') {
                isInclude = true;
            }
            if (c == '）') {
                isInclude = false;
            }
            if ((isChinese || besideBol || isInclude) && isInIgnore) {
                temp.append(c);
                if (temp.length() == lenth) {
                    return temp.toString();
                }
            }
            else {
                if (temp.length() != 0) {
                    cutContentContainer.add(temp.toString());
                    index++;
                    if (temp.toString().length() > maxLength) {
                        maxLength = temp.toString().length();
                        maxLengthIndex = index;
                    }
                    temp = new StringBuffer();
                }
                // <>区间的内容直接忽略
                if (c == '<') {
                    isInIgnore = false;
                }
                if (c == '>') {
                    isInIgnore = true;
                }
            }
        }
        if (CollectionUtils.isNotEmpty(cutContentContainer)) {
            return cutContentContainer.get(maxLengthIndex - 1);
        }
        return null;
    }

    public static void main(String[] args) {
        String content = "<p style=\"margin-top:7px;margin-bottom:7px;margin-top:auto;margin-bottom:auto;text-align:left;line-height:22px\"><span style=\";font-family:宋体;color:#333333;font-size:14px\">为贯彻落实习近平总书记在文艺工作座谈会上重要讲话精神，打造唱响兴安的精品力作，推进兴安盟文学艺术事业的发展，特面向全国开展“放歌草原·唱响中国梦”歌曲征集活动，方案如下：</span></p>";
        System.out.println(UeditorUtils.getCutStrFromContent(content, 70));

    }
}
