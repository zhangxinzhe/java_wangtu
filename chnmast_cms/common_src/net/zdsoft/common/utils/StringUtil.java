package net.zdsoft.common.utils;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

    /**
     * 根据full code得到后面去掉00的region code
     *
     * @param fullCode
     * @return
     */
    public static String getShortRegionCode(String fullCode) {
        // String bb = "510100".replaceAll("(\\d*[1-9])0+$","$1");

        if (StringUtils.isBlank(fullCode) || fullCode.length() != 6) {
            return null;
        }
        if (fullCode.endsWith("0000")) {
            return fullCode.substring(0, 2);
        }
        else if (fullCode.endsWith("00")) {
            return fullCode.substring(0, 4);
        }
        else {
            return fullCode;
        }
    }

    /**
     * 在短的region code后面补00得到6位的长region code
     *
     * @param shortCode
     * @return
     */
    public static String getFullRegionCode(String shortCode) {

        return rightPadZero(shortCode, 6);
    }

    /**
     * 在字符串右边填充0直到指定长度.
     *
     * @param str
     *            原始字符串
     * @param len
     *            总的长度
     */
    public static String rightPadZero(String str, int length) {
        if (StringUtils.isBlank(str)) {
            return str;
        }

        while (str.length() < length) {
            str = str + "0";
        }
        return str;
    }

    /**
     * 将字符转换为int类型数字，如果是非数字字符则转换为0
     *
     * @param number
     * @return
     */
    public static int toIntNumber(String number) {
        // 如果为空串则直接返回0
        if (StringUtils.isEmpty(number)) {
            return 0;
        }

        int intNumber = 0;
        try {
            intNumber = Integer.parseInt(number);
        }
        catch (NumberFormatException ex) {
        }

        return intNumber;
    }

    /**
     * 将字符转换为long类型数字，如果是非数字字符则转换为0
     *
     * @param number
     * @return
     */
    public static long toLongNumber(String number) {
        // 如果为空串则直接返回0
        if (StringUtils.isEmpty(number)) {
            return 0L;
        }

        long intNumber = 0L;
        try {
            intNumber = Long.parseLong(number);
        }
        catch (NumberFormatException ex) {
        }

        return intNumber;
    }

    /**
     * 获取安全字符串
     *
     * @return
     */
    public static String getShowStr(String str) {
        if (null == str) {
            return null;
        }
        int len = str.length();
        if (len <= 3) {
            str = str.substring(0, 1) + "**";
        }
        else if (len > 3 && len <= 7) {
            String newStr = str;
            str = str.substring(0, 3);
            for (int i = 0; i < newStr.substring(3).length(); i++) {
                str += "*";
            }
        }
        else if (len > 7 && len < 12) {
            str = str.substring(0, 3) + "****" + str.substring(7);
        }
        else {
            str = str.substring(0, 3) + "****" + str.substring(str.length() - 4);
        }
        return str;
    }

    /**
     * 计算字符长度 1个汉字2个字符
     *
     * @param str
     * @return
     */
    public static int getRealLength(String str) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        return net.zdsoft.keel.util.StringUtils.getRealLength(str);
    }

    public static void main(String[] args) {
        String str = "绑";

        System.out.println(getShowStr(str));
    }
}
