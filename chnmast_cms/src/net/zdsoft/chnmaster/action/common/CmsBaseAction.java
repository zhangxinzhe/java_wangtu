package net.zdsoft.chnmaster.action.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zdsoft.chnmaster.entity.system.SystemApp;
import net.zdsoft.chnmaster.entity.system.SystemModule;
import net.zdsoft.chnmaster.service.SystemVersionService;
import net.zdsoft.chnmaster.service.common.BaseService;
import net.zdsoft.chnmaster.service.system.SystemAppService;
import net.zdsoft.chnmaster.service.system.SystemModuleService;
import net.zdsoft.chnmaster.utils.LoginUtils;
import net.zdsoft.common.action.BaseAction;
import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.common.utils.URLUtil;
import net.zdsoft.keel.cache.CacheManager;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class CmsBaseAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected static final String NO_PRIVACY = "noprivacy"; // 没有权限的访问结果
    protected static final String NOT_EXIST = "notexist";// 访问资源不存在的结果
    protected static final String INDEX = "index";
    protected static final String HOME = "home";
    protected static final String TEACHER = "teacher";
    protected static final String STUDENT = "student";

    @Resource
    protected SystemModuleService systemModuleService;
    @Resource
    protected SystemAppService systemAppService;
    @Resource
    protected BaseService baseService;
    @Resource
    protected CacheManager cacheManager;
    @Resource
    protected SystemVersionService systemVersionService;

    protected long modId; // 请求地址所属的二级模块id
    protected long appId; // 请求地址所属的子系统id
    protected List<SystemApp> apps; // 当前用户能访问的子系统列表
    protected List<SystemModule> topModules; // 一级模块列表
    protected Map<Long, List<SystemModule>> subModuleMap; // 二级模块
    protected long topModId; // 当前模块的所属一级模块id
    protected long subModId; // 当前二级模块id
    protected SystemModule subModule; // 当前二级模块

    protected long id;
    protected Long[] ids;
    protected List<?> list;

    /**
     * 刷新缓存（不建议使用，请在更新数据时写代码手工实现刷新）
     *
     * @deprecated
     */
    @Deprecated
    public void flushAllCache() {
        cacheManager.flushAll();
    }

    /**
     * 初始化数据方法
     */
    @Override
    public void prepare() throws Exception {
        // 用户未登录
        if (null == getUser()) {
            return;
        }

        // 用户能访问的所有子系统
        apps = systemAppService.listSystemAppOfUser(getUser().getId());
        if (CollectionUtils.isEmpty(apps)) {
            log.error("你没有访问权限！");
            return;
        }

        // 获取当前访问的模块（可能是三级功能点模块）
        SystemModule curModule = null;
        if (modId > 0) {
            // 如果在访问的url中指定了模块id
            curModule = systemModuleService.getSystemModule(modId);
        }
        else {
            // 访问的URL
            requestUrl = getRequest().getServletPath();
            // 获取当前访问的系统模块
            curModule = systemModuleService.getSystemModule(requestUrl);
        }

        // 用户能访问的当前子系统的模块
        if (appId <= 0) {
            appId = (null == curModule) ? 1 : curModule.getAppId(); // 默认子系统为1
        }
        List<SystemModule> systemModules = systemModuleService.listSystemModuleOfUser(getUser().getId(), appId, false);
        if (CollectionUtils.isEmpty(systemModules)) {
            log.error("你没有访问权限！");
            return;
        }

        if (null == curModule) {
            log.debug("URI:{}没有配置对应的模块!", requestUrl);
        }
        else {
            // 找到当前模块的一级和二级模块id
            SystemModule parentModule = systemModuleService.getSystemModule(curModule.getParentId());
            if (null == parentModule) {
                // 访问的是一级模块
                topModId = curModule.getId();
            }
            else {
                // 访问的是二级模块
                if (parentModule.getParentId() == 0) {
                    topModId = parentModule.getId();
                    subModId = curModule.getId();
                }
                else {
                    SystemModule parentParentModule = systemModuleService.getSystemModule(parentModule.getParentId());
                    // 访问的是三级模块（即：页面中的功能点）
                    if (parentParentModule.getParentId() == 0) {
                        topModId = parentModule.getParentId();
                        subModId = parentModule.getId();
                    }
                    else {
                        topModId = parentParentModule.getParentId();
                        subModId = parentParentModule.getId();
                    }
                }
            }
        }

        // 获取一级和二级菜单
        topModules = new ArrayList<SystemModule>();
        subModuleMap = new HashMap<Long, List<SystemModule>>();
        for (SystemModule systemModule : systemModules) {
            if (systemModule.getIsOperate().getValue() == 1) {
                continue;
            }

            if (systemModule.getParentId() == 0) {
                // 一级菜单
                topModules.add(systemModule);
            }
            else {
                // 二级菜单
                List<SystemModule> subModules = subModuleMap.get(systemModule.getParentId());
                if (subModules == null) {
                    subModules = new ArrayList<SystemModule>();
                    subModuleMap.put(systemModule.getParentId(), subModules);
                }
                subModules.add(systemModule);

                if (systemModule.getId() == subModId) {
                    subModule = systemModule;
                }
            }
        }

        // 添加get参数
        Map<String, ?> requestValuesMap = request.getParameterMap();
        if (requestValuesMap != null) {
            Set<String> keysSet = requestValuesMap.keySet();
            StringBuffer valuesSB = new StringBuffer();
            for (String key : keysSet) {
                String[] values = (String[]) requestValuesMap.get(key);
                valuesSB.append("&" + key + "=" + values[0]);
            }
            if (StringUtils.isNotBlank(valuesSB.toString())) {
                requestUrl += "?" + valuesSB.substring(1);
            }
        }
    }

    /**
     * 同步记录操作日志<br>
     * agencyId是指操作数据所在的机构，如果没有所属机构（如：学校管理，代理商管理）请写0
     *
     * @param operate
     * @param agencyId
     */
    public void logOperate(String operate) {
        baseService.logOperate(operate, getUser().getId(), URLUtil.getIpAddr(request));
    }

    /**
     * 异步记录操作日志（以附加业务方式记录）<br>
     *
     * @param operate
     * @param agencyId
     */
    public void logOperateAsyn(String operate) {
        baseService.logOperateAsyn(operate, getUser().getId(), URLUtil.getIpAddr(request));
    }

    /**
     * 获取父模块下面第一个子模块的URL地址
     *
     * @param moduleIdKey
     * @return
     */
    protected String getUrl(long moduleIdKey) {
        String urlString = "";
        List<SystemModule> subSystemModuleList = systemModuleService.listSystemModuleOfParent(getUser().getId(),
                moduleIdKey);
        if (subSystemModuleList.size() > 0) {
            SystemModule systemModule = subSystemModuleList.get(0);
            urlString = systemModule.getUrl();
        }
        return urlString;
    }

    /**
     * 判断有没有模块或模块中功能点的操作权限
     *
     * @param muduleId
     * @return
     */
    public boolean checkUserMudule(long muduleId) {
        if (getUser() != null) {
            return systemModuleService.canVisitSystemModule(getUser().getId(), muduleId);
        }
        return false;
    }

    public String getSystemVersionMaxNo() {
        return systemVersionService.getMaxSystemVersionNo();
    }

    /**
     * 压缩图片
     */
    public void imageComp(String srcPath, String desPath, int width, int height, boolean proportion, long userId) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("srcPath", srcPath);
        values.put("desPath", desPath);
        values.put("width", width);
        values.put("height", height);
        values.put("proportion", proportion);
        values.put("userId", userId);
        exoperateProcessorDispatcher.dispatchProcessor("IMAGE_COMPRESS", values);
    }

    /**
     * 将ids数组组成字符串
     *
     * @param theIds
     * @return
     */
    public String idsToString(Long[] theIds) {
        if (null == theIds || theIds.length == 0) {
            return "";
        }
        String str = "";
        for (long id : theIds) {
            str += id + ",";
        }
        str.substring(0, str.lastIndexOf(",") - 1);
        return str;
    }

    public List<SystemModule> getTopModules() {
        return topModules;
    }

    public Map<Long, List<SystemModule>> getSubModuleMap() {
        return subModuleMap;
    }

    public long getModId() {
        return modId;
    }

    public void setModId(long modId) {
        this.modId = modId;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public List<SystemApp> getApps() {
        return apps;
    }

    public long getTopModId() {
        return topModId;
    }

    public long getSubModId() {
        return subModId;
    }

    public SystemModule getSubModule() {
        return subModule;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    @Override
    public BaseUser getUser() {
        if (user == null) {
            user = LoginUtils.getInstance().getUserFromRequest(getRequest());
        }
        return user;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

}
