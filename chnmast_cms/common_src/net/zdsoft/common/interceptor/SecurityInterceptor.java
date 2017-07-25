package net.zdsoft.common.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import net.zdsoft.common.annotation.SecurityAntion;
import net.zdsoft.common.annotation.SecurityAntion.VisitorType;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.properties.PropertiesHelper;
import net.zdsoft.common.utils.SecurityUtils;
import net.zdsoft.common.utils.URLUtil;
import net.zdsoft.common.utils.Util;

/**
 * structs安全性拦截器
 *
 * @author liyixi
 *
 */
public class SecurityInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 76559718834659895L;
    private final static Logger log = LoggerFactory.getLogger(SecurityInterceptor.class);

    // ACTION未设置自定义规则，并且验证未通过所执行的默认RESULT
    private final static String ERRORPAGE = "subError";

    // 不存在请求资源RESULT
    private final static String NOTEXIST = "notexist";

    // 调用类型为AJAX时返回标志
    private final static String NO_SECURITY = "NO_SECURITY";

    // 不进行过滤的路径或者地址集合
    private static Set<String> ignorePathSet = new HashSet<String>();

    // 安全性校验开启开关：true：开启 false : 关闭
    private static boolean turnOn;

    // 模式，1 若不符合规范则跳转到统一提示页面或者预设页面
    // 模式，2 过滤不安全内容
    private static int model;

    @Override
    public void init() {
        String strTurnOn = PropertiesHelper.getParam(BaseConstants.SECURITY_TURN);
        turnOn = StringUtils.isBlank(strTurnOn) ? true : Boolean.parseBoolean(strTurnOn);
        if (turnOn) {
            String ignorePath = PropertiesHelper.getParam(BaseConstants.SECURITY_IGNORE);
            ignorePath = ignorePath == null ? "" : ignorePath;
            if (StringUtils.isNotBlank(ignorePath)) {
                String[] paths = StringUtils.split(ignorePath.trim(), ",");
                for (String path : paths) {
                    if (StringUtils.isNotBlank(path)) {
                        ignorePathSet.add(StringUtils.trim(path));
                    }
                }
            }
            String strLevel = PropertiesHelper.getParam(BaseConstants.SECURITY_MODEL);
            model = StringUtils.isBlank(strLevel) ? 2 : Integer.parseInt(strLevel);
            log.debug("平台数据安全性拦截器初始化完成，默认模式：{};可通行地址或路径：【{}】", model, ignorePath);
        }
        else {
            log.warn("平台数据安全性拦截器当前处于关闭状态！");
        }
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        if (turnOn) {
            HttpServletRequest request = ServletActionContext.getRequest();
            // 验证请求路径
            if (!validatorPath(request)) {
                return NOTEXIST;
            }
            String path = request.getServletPath();
            // 验证是否是处理的地址
            if (URLUtil.isIgnorePath(path, ignorePathSet)) {
                log.debug("会话【{}.htm:{}】忽略地址{}", new Object[] { invocation.getProxy().getActionName(),
                        invocation.getProxy().getMethod(), path });
                return invocation.invoke();
            }
            // if(level == 2){
            // Object action = invocation.getAction();
            // if(action instanceof NoParameters){
            // 不需要进行赋值操作的ACTION，考虑到项目中不存在这种情况，所以暂时不做考虑
            // }
            // }

            Class<?> aCls = invocation.getAction().getClass();
            String methodName = invocation.getProxy().getMethod();
            // 获取方法注解对象
            SecurityAntion annotaion = aCls.getMethod(methodName, new Class<?>[] {})
                    .getAnnotation(SecurityAntion.class);

            // 不做过滤的参数名称集合
            Map<String, String> ignoreParamNames = null;
            // 默认调用类型为SUBMIT
            VisitorType visitorType = VisitorType.SUBMIT;
            // 调用模式
            int curModel = model;
            if (annotaion != null) {// 该方法配置了注解规则
                // 获取不做验证的参数名称集合
                String[] strIgnoreParamNames = annotaion.params();
                ignoreParamNames = new HashMap<String, String>();
                for (String param : strIgnoreParamNames) {
                    ignoreParamNames.put(param, param);
                }
                visitorType = annotaion.type();
                int _level = annotaion.model();
                if (_level == 1 || _level == 2) {
                    curModel = _level;
                }
            }
            // 开过过滤操作
            if (!securityFilter(invocation, ignoreParamNames, visitorType, curModel)) {
                if (visitorType == VisitorType.AJAX) {
                    return null;
                }
                else {
                    return annotaion == null ? ERRORPAGE : annotaion.target();
                }
            }
        }
        return invocation.invoke();
    }

    /**
     *
     * @param invocation
     * @param ignoreParamNames
     * @param visitorType
     * @param curLevel
     * @return true成功 false:失败
     */
    private boolean securityFilter(ActionInvocation invocation, final Map<String, String> ignoreParamNames,
            VisitorType visitorType, int curModel) {
        // 获取本次会话提交的参数
        ActionContext context = invocation.getInvocationContext();
        Map<String, Object> params = context.getParameters();
        if (params == null || params.isEmpty()) {
            return true;
        }
        List<String> notSecurityStr = new ArrayList<String>();
        // 数据清洗开始
        for (Object _key : params.keySet()) {
            String key = String.valueOf(_key);
            if (ignoreParamNames != null && ignoreParamNames.containsKey(key)) {
                log.debug("会话【{}.htm:{}】忽略参数key={}",
                        new Object[] { invocation.getProxy().getActionName(), invocation.getProxy().getMethod(), key });
                continue;
            }
            Object[] vals = (Object[]) params.get(key);
            if (vals != null) {
                for (Object _val : vals) {
                    if (_val instanceof String) {
                        String str = handle(key, (String) _val, visitorType, context, invocation);
                        if (str != null && curModel == 1) {
                            notSecurityStr.add(str);
                        }
                    }
                }
            }
        }
        if (curModel == 1 && notSecurityStr.size() > 0) {
            StringBuffer tip = new StringBuffer();
            for (String t : notSecurityStr) {
                tip.append(t).append("，");
            }
            tip.setLength(tip.length() - 1);
            if (visitorType == VisitorType.SUBMIT) {
                addActionMessage(invocation, NO_SECURITY + tip.toString());
            }
            else if (visitorType == VisitorType.AJAX) {
                String content = NO_SECURITY + tip.toString();
                wirteTextMessage(ServletActionContext.getResponse(), content);
            }
            return false;
        }
        return true;
    }

    /**
     * 过滤业务处理
     *
     * @param key
     * @param input
     * @param visitorType
     * @param context
     * @param invocation
     * @return
     */
    private String handle(String key, String input, VisitorType visitorType, ActionContext context,
            ActionInvocation invocation) {
        if (StringUtils.isNotBlank(input)) {
            boolean isUpdate = false;
            String nwVal = input;
            // 过滤emoji符号
            if (Util.hasEmoji(nwVal)) {
                nwVal = nwVal.replaceAll("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", "");
                isUpdate = true;
            }
            if (SecurityUtils.isExistsSpecialSymbols(input)) {
                nwVal = SecurityUtils.filterSpecialSymbols(nwVal);
                isUpdate = true;
            }
            if (SecurityUtils.isExistScriptTag(nwVal)) {
                nwVal = SecurityUtils.filterSepecialScriptTag(nwVal);
                isUpdate = true;
            }
            if (isUpdate) {
                log.debug("会话【{}.htm:{}】key={};srcParam={}【内容中发现危险内容】", new Object[] {
                        invocation.getProxy().getActionName(), invocation.getProxy().getMethod(), key, input });
                try {
                    context.getValueStack().setParameter(key, nwVal);
                }
                catch (RuntimeException e) {
                    log.debug("会话【{}.htm:{}】设置值【key={};srcParam={}】发生异常！", new Object[] {
                            invocation.getProxy().getActionName(), invocation.getProxy().getMethod(), key, input });
                }
                return input;
            }
        }
        return null;
    }

    /**
     * 验证请求路径
     *
     * @param path
     * @return
     */
    private boolean validatorPath(final HttpServletRequest request) {
        return SecurityUtils.ValidatorPath(request);
    }

    /**
     * 设置消息
     *
     * @param invocation
     * @param msg
     */
    private void addActionMessage(ActionInvocation invocation, String msg) {
        ActionSupport action = (ActionSupport) invocation.getAction();
        action.addActionMessage(msg);
    }

    /**
     * AJAX-向页面写文本
     *
     * @param message
     */
    private void wirteTextMessage(HttpServletResponse response, String msg) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(msg);
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
