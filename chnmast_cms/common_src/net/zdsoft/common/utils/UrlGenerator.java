package net.zdsoft.common.utils;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;

/**
 * URL生成器
 *
 * @author fangb
 */
public class UrlGenerator {
    private static final String TRAIN_COURSE_URL_RULE = "course.course_url_rule";

    /**
     * 得到课程详细页的url
     *
     * @param courseId
     * @return
     */
    public static String getCourseUrl(long courseId) {
        return String.format(NetstudyConfig.getParam(TRAIN_COURSE_URL_RULE),
                NetstudyConfig.getParam(BaseConstants.DOMAIN_HOME), "" + courseId);
    }
}
