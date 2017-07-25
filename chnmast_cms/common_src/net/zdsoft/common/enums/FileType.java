package net.zdsoft.common.enums;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;

/**
 *
 *
 * @author dongzk
 *
 */
public enum FileType {

    USER_FILE {
        @Override
        public String getStoreDir() {
            return NetstudyConfig.getParam(BaseConstants.CHNMASTER_FILE_PATH);
        }

        @Override
        public String getWebContext() {
            return NetstudyConfig.getParam(BaseConstants.CHNMASTER_USER_FILE_CONTEXT);
        }
    },

    SYS_FILE {
        @Override
        public String getStoreDir() {
            return NetstudyConfig.getParam(BaseConstants.CHNMASTER_FILE_PATH)
                    + NetstudyConfig.getParam(BaseConstants.CHNMASTER_SYSTEM_FILE_CONTEXT);
        }

        @Override
        public String getWebContext() {
            return NetstudyConfig.getParam(BaseConstants.CHNMASTER_SYSTEM_FILE_CONTEXT);
        }
    };

    /**
     * 得到该类型文件的总的存储目录。
     * <p>
     * 不同类型的文件的存储分开，比如用户上传的文件和系统所属的文件放在不同的目录
     *
     * @return
     */
    public abstract String getStoreDir();

    /**
     * 得到该类型文件的web contxt
     * <p>
     * 该属性在生成文件绝对url时需要用到
     *
     * @return
     */
    public abstract String getWebContext();

}
