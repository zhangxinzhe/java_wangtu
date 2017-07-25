package net.zdsoft.chnmaster.service.impl;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.config.Config;
import net.zdsoft.common.config.NetstudyConfigService;

@Service("netstudyConfigService")
public class NetstudyConfigServiceImpl implements NetstudyConfigService {

    @Override
    public String getParam(String key) {
        return Config.getParam(key);
    }

    @Override
    public long getVersionId() {
        return Config.getVersionId();
    }

}
