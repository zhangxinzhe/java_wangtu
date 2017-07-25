package net.zdsoft.chnmaster.service.system;

import com.alibaba.fastjson.JSONObject;

/**
 * 获取手机端配置信息
 * 
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015-8-12 下午1:46:37 $
 */
public interface SystemMobileService {
    /**
     * 获取手机端配置信息
     * 
     * @param agencyId
     * @return
     */
    public JSONObject getMobileConfig(long agencyId);
}
