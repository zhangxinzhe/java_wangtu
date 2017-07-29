package net.zdsoft.chnmaster.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.zdsoft.chnmaster.utils.LoginUtils;
import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.common.utils.URLUtil;

public class LoginFilter implements Filter {
    private static String IGNORE_PATH_PARAMETER = "ignorePath";

    /**
     * 登录页面
     */
    private static String LOGIN = "/login.htm";

    /**
     * 可以忽略的请求action集合
     */
    private final Set<String> ignorePaths = new HashSet<String>();

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // 浏览器不加入缓存设置
        res.addHeader("Cache-Control", "no-cache, must-revalidate");
        res.setDateHeader("Expires", 0);
        res.addHeader("Pragma", "no-cache");
        res.setCharacterEncoding("UTF-8");

        String action = URLUtil.getAction(req);
        String servletPath = req.getServletPath();
        if (URLUtil.isIgnorePath(servletPath, ignorePaths)) {
            // 访问登录页面且已经登录
            if (action.equals("login.htm") && LoginUtils.getInstance().isLogin(req)) {
                res.sendRedirect(req.getContextPath() + "/index.htm");
                return;
            }
            else {
                chain.doFilter(request, response);
                return;
            }
        }

        // 用户已经登录
        BaseUser baseUser = LoginUtils.getInstance().getUserFromRequest(req);
        if (null != baseUser) {
            chain.doFilter(request, response);
        }
        else {
            // 未登录
            // List<String> parameters = new ArrayList<String>();
            // for (Object key : req.getParameterMap().keySet()) {
            // parameters.add(key.toString() + "=" + req.getParameter(key.toString()));
            // }
            // StringBuilder redirectUrl = new StringBuilder();
            // redirectUrl.append(req.getRequestURL());
            // if (!parameters.isEmpty()) {
            // redirectUrl.append(PARMETER_CHARACTER);
            // redirectUrl.append(StringUtils.join(parameters, PARMETER_SPLIT_CHARACTER));
            // }
            String url = req.getContextPath() + LOGIN;
            // if (!action.equals("index.htm")) {
            // url += "?redirectUrl=" + URLEncoder.encode(redirectUrl.toString(), Constants.URL_ENCODE_TYPE);
            // }
            res.sendRedirect(url);
        }
    }

    /**
     * 初始化不需要过滤的action集合
     *
     */
    @Override
    public void init(FilterConfig conf) throws ServletException {
        String ignorePath = conf.getInitParameter(IGNORE_PATH_PARAMETER);

        if (ignorePath == null || ignorePath.equals("")) {
            return;
        }

        String[] paths = StringUtils.split(ignorePath, ",");

        for (String path : paths) {
            if (StringUtils.isNotBlank(path)) {
                ignorePaths.add(StringUtils.trim(path));
            }
        }
    }

    /**
     * 判断请求的action是否是不需要过滤
     *
     * @param action
     * @return
     */
    private boolean isIgnore(String action) {
        return ignorePaths.contains(action);
    }

}
