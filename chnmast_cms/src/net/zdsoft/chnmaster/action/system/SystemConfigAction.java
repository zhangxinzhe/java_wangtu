package net.zdsoft.chnmaster.action.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsPageAction;
import net.zdsoft.chnmaster.constant.ChnmasterModuleConstants;
import net.zdsoft.chnmaster.service.SystemConfigService;
import net.zdsoft.common.annotation.SecurityAntion;
import net.zdsoft.common.entity.system.SystemConfig;
import net.zdsoft.common.utils.ValidatorsUtil;
import net.zdsoft.keel.util.Validators;

/**
 * 系统参数
 * 
 * @author hongx
 */
@Scope("prototype")
@Controller
public class SystemConfigAction extends CmsPageAction {
    private static final long serialVersionUID = 6736779676178801894L;
    @Resource
    private SystemConfigService systemConfigService;
    private SystemConfig systemConfig;
    private List<SystemConfig> systemConfigs;
    private String code;
    private String configName;

    /**
     * 获取所有系统参数
     * 
     * @return
     * @throws Exception
     */
    public String systemConfigManage() throws Exception {
        systemConfigs = systemConfigService.listConfigs(configName);
        return SUCCESS;
    }

    /**
     * 编辑、修改参数
     * 
     * @return
     * @throws Exception
     */
    @SecurityAntion(params = { "systemConfig.value" })
    public String editSystemConfig() throws Exception {
        if (systemConfig == null) { // 进入页面
            if (StringUtils.isNotBlank(code)) {
                systemConfig = systemConfigService.getSystemConfigMap().get(code);
                if (systemConfig == null) {
                    return NOT_EXIST;
                }
                else if (systemConfig.getCanEdit() == false) {
                    // 不允许修改
                    return NO_PRIVACY;
                }
            }
            else {
                return NOT_EXIST;
            }
            return INPUT;
        }
        else {// 修改数据
            if (!validateSystemConfig()) {
                return INPUT;
            }
            systemConfigService.updateConfigInfo(systemConfig);
            logOperateAsyn("修改系统参数(" + systemConfig.getCode() + "," + systemConfig.getName() + ")");
        }
        systemConfigs = systemConfigService.listConfigs(configName);
        return SUCCESS;
    }

    /**
     * 验证参数数据
     * 
     * @return
     * @throws Exception
     */
    private boolean validateSystemConfig() throws Exception {
        SystemConfig old = systemConfigService.getSystemConfigMap().get(systemConfig.getCode());
        if (old == null) {
            addActionError("你要修改的参数不存在!");
            return false;
        }
        systemConfig.setName(old.getName());
        if ("".equals(systemConfig.getValue()) && !Validators.isEmpty(old.getValidate())) {
            addActionError("参数值不能为空!");
            return false;
        }
        if (net.zdsoft.keel.util.StringUtils.getRealLength(systemConfig.getValue()) > 200) {
            addActionError("参数值不能超过200个字节!");
            return false;
        }
        String msg = ValidatorsUtil.validator(old.getValidate(), systemConfig.getValue());
        if (msg != null) {
            addActionError(msg);
            return false;
        }
        if (net.zdsoft.keel.util.StringUtils.getRealLength(systemConfig.getDescription()) > 300) {
            addActionError("备注不能超过300个字节!");
            return false;
        }
        return true;
    }

    public boolean getCanEditConfig() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_CONFIG);
    }

    public SystemConfig getSystemConfig() {
        return systemConfig;
    }

    public void setSystemConfig(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    public List<SystemConfig> getSystemConfigs() {
        return systemConfigs;
    }

    public void setSystemConfigs(List<SystemConfig> systemConfigs) {
        this.systemConfigs = systemConfigs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }
}
