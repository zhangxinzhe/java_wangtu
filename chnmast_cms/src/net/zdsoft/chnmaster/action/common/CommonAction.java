package net.zdsoft.chnmaster.action.common;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.chnmaster.service.system.SystemMobileService;

/**
 * action 基类
 *
 * @author
 * @version $Revision: 1.0 $, $Date: 2013-11-19 上午11:29:16 $
 */
@Scope("prototype")
@Controller
public class CommonAction extends CmsBaseAction {
    private static final long serialVersionUID = 1L;
    @Resource
    protected SystemMobileService systemMobileService;

    /**
     * 手机配置信息
     */
    public void mobileConfig() {
        JSONObject json = systemMobileService.getMobileConfig(0);
        print(json);
    }

    /**
     * 测试服务器是否能正常访问
     */
    public void server() {
        print(1);
    }

}
