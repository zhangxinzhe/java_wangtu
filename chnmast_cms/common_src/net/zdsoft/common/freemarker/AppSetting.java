/*
 * author dongzk
 */

package net.zdsoft.common.freemarker;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.keel.util.StringUtils;

public class AppSetting {
    private static AppSetting appSetting = new AppSetting();

    public static AppSetting getInstance() {
        return appSetting;
    }

    protected AppSetting() {
    }

    /**
     * 得到系统参数值
     *
     * @param key
     * @return
     */
    public String getParam(String key) {
        return NetstudyConfig.getParam(key);
    }

    /**
     * 得到当前系统最大的版本号ID
     *
     * @return
     */
    public long getVersionId() {
        return NetstudyConfig.getVersionId();
    }

    /**
     * 截取过长字符串，汉字视为两个字符长度
     *
     * @param value
     * @param length
     * @return
     */
    public String subStr(String value, int length) {
        return StringUtils.cutOut(value, length, "…");
    }

    public String parseEditor(String text) {
        if (org.apache.commons.lang.StringUtils.isBlank(text)) {
            return "";
        }
        text = text.replaceAll("<p>", "").replaceAll("</p>", "<br/>");
        if (text.length() - 5 > 0 && text.lastIndexOf("<br/>") == text.length() - 5) {
            text = text.substring(0, text.length() - 5);
        }
        return text;
    }
}
