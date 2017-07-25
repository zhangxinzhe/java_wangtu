package net.zdsoft.chnmaster.utils;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;

/**
 * 工具类
 *
 * @author winupon
 *
 */
public class Util {
    /**
     * 文件上传地址符解析器
     *
     * @author zhangxz
     * @version $Revision: 1.0 $, $Date: 2014-4-4 上午11:01:16 $
     */
    public static class UploadFilePathMarkParser {
        /**
         * 将content中的地址符转换成真实地址
         *
         * @param content
         * @return
         */
        public static String MarkToPath(String content) {
            return content.replaceAll(BaseConstants.DOMAIN_UPLOAD_FILE_MARK,
                    NetstudyConfig.getParam(BaseConstants.DOMAIN_UPLOAD_FILE));
        }

        /**
         * 将content中的真实地址转换成地址符
         *
         * @param content
         * @return
         */
        public static String PathToMark(String content) {
            return content.replaceAll(NetstudyConfig.getParam(BaseConstants.DOMAIN_UPLOAD_FILE),
                    BaseConstants.DOMAIN_UPLOAD_FILE_MARK);
        }
    }
}
